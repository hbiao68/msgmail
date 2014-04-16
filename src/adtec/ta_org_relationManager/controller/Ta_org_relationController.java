package adtec.ta_org_relationManager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import adtec.appManager.controller.ClassToJson;
import adtec.appManager.model.PageModel;
import adtec.appManager.model.Ta_App;
import adtec.categoryManager.model.Ta_category;
import adtec.organizationManager.entity.Organization;
import adtec.privilege.model.Resource;
import adtec.privilege.service.ResourceService;
import adtec.ta_org_relationManager.model.Ta_org_relation;
import adtec.ta_org_relationManager.service.Ta_org_relationService;

@Controller
@RequestMapping(value = "/ta_org_relation")
public class Ta_org_relationController {

	private Ta_org_relationService ta_org_relationService;
	private ResourceService resourceService; 
	public Ta_org_relationService getTa_org_relationService() {
		return ta_org_relationService;
	}

	public void setTa_org_relationService(
			Ta_org_relationService ta_org_relationService) {
		this.ta_org_relationService = ta_org_relationService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	Logger log = Logger.getLogger(Ta_org_relationController.class);

	/**
	 * 添加业务开通的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org_relationInsert")
	public ModelAndView showorg_RelationAdd(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("showorg_RelationAdd");
		ModelAndView mv = new ModelAndView();
		// 获取ta_id
		String ta_id = request.getParameter("ta_id");
		String appid = request.getParameter("appid");
		String appName = request.getParameter("appName");
		request.setAttribute("ta_id", ta_id);
		request.setAttribute("appid", appid);
		request.setAttribute("appName", appName);
		// 跳转到添加页面
		mv.setViewName("/ta_org_relationManager/org_relationInsert");
		return mv;
	}

	/**
	 * 为机构开通业务的处理方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/insertAction", method = RequestMethod.POST)
	public String AddTa_org_relationAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("AddTa_org_relationAction");

		String ta_id = request.getParameter("ta_id");
		// 从前端获取树状结构中机构id的值
		String id = request.getParameter("oid");
		if (id.length() >= 15 || id == null || id == "") {
			id = "0"; // 说明点击的时全国这个id不是数据库的，而是 tree里面获取的。
		}

		String orgId = new Integer(id).toString();
		// 通过机构Id号获取该机构所有信息
		Organization org = ta_org_relationService.queryOrgById(orgId);
		List<Organization> orgIdList = new ArrayList<Organization>();
		// 通过一个机构获取该机构下属的所有机构
		orgIdList = ta_org_relationService.querySubOrgListByOrg(org);

		int appid = Integer.parseInt(request.getParameter("appid"));
		request.setAttribute("appid", appid);

		// 该list中放批量添加中的所有所需数据
		List<Ta_org_relation> list = new ArrayList<Ta_org_relation>();
		// 该list里面放所有批量删除所需的机构主键
		List<String> listOrgId = new ArrayList<String>();
		// 该map中房所有重复添加了需要批量删除机构所需的数据
		Map<String, Object> insOrgId = new HashMap<String, Object>();
		Organization organization = new Organization();
		for (int j = 0; j < orgIdList.size(); j++) {
			// 循环获取每条机构的数据
			organization = (Organization) orgIdList.get(j);
			// 循环获取每个机构中的主键值
			String orgIdback = organization.getOrgId().toString();
			// 将所有获取到的数据放到Ta_org_relation实体中
			Ta_org_relation ta_org_relation = new Ta_org_relation();
			ta_org_relation.setAppid(appid);
			ta_org_relation.setOrgId(orgIdback);
			ta_org_relation.setTa_id(ta_id);
			// 将所有重复的的机构主键放入一个list中
			listOrgId.add(orgIdback);
			// 将循环获取的每一个实体放入list中
			list.add(ta_org_relation);
		}
		insOrgId.put("orgList", listOrgId);
		insOrgId.put("appid", appid);
		insOrgId.put("ta_id", ta_id);
		// 跳转页面的提示信息
		String InsertMsg = null;
		try {
			boolean tag = ta_org_relationService.batchInsert(insOrgId, list);
			if (tag) {
				InsertMsg = "insertMsg";
				log.debug("addRelationAction");
			} else {
				InsertMsg = "failMsg";
				log.debug("addRelationAction");
			}
		} catch (Exception e) {
			InsertMsg = "failMsg";
			log.error("机构添加失败！");

		}
		request.setAttribute("InsertMsg", InsertMsg);
		// 跳转到查询页面
		return "redirect:/ta_org_relation/orgRelationList.do?appid=" + appid
				+ "&InsertMsg=" + InsertMsg + "&ta_id="+ta_id;

	}

	/**
	 * 删除方法
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/delAction")
	public void delAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("delAction");
		String ta_id = request.getParameter("ta_id");
		int appid = Integer.parseInt(request.getParameter("appid"));
		String orgId = request.getParameter("orgId");
		// 通过机构Id号获取该机构所有信息
		Organization org = ta_org_relationService.queryOrgById(orgId);
		List<Organization> orgIdList = new ArrayList<Organization>();
		// 通过一个机构获取该机构下属的所有机构
		orgIdList = ta_org_relationService.querySubOrgListByOrg(org);

		// 该list里面放所有批量删除所需的机构主键
		List<Integer> list = new ArrayList<Integer>();
		Organization organization = new Organization();
		// 该map里面放批量删除所需的所有数据
		Map<String, Object> ins = new HashMap<String, Object>();

		for (int j = 0; j < orgIdList.size(); j++) {
			// 循环获取批量删除的机构主键
			organization = (Organization) orgIdList.get(j);
			int orgIdback = Integer
					.parseInt(organization.getOrgId().toString());

			list.add(orgIdback);
		}
		ins.put("orgList", list);
		ins.put("appid", appid);
		ins.put("ta_id", ta_id);
		String msg = null;
		try {
			boolean tag = ta_org_relationService.batchDelete(ins);
			if (tag) {
				msg = "数据删除成功！";
				log.debug("已开通业务删除成功！");
			} else {
				msg = "数据删除失败！";
				log.debug("已开通业务删除失败！");
			}

		} catch (Exception e) {
			msg = "网络故障！";
			log.error("已开通业务删除失败！");
		}

		String json = ClassToJson.object2json(msg);
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			log.error("response 响应 IOException异常");
		}
	}

	/**
	 * 查询展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/orgRelationList")
	public ModelAndView showTa_org_relationList(HttpServletRequest request,
			HttpServletResponse response,Resource res) {
		String resid = "";
		if(res!=null && res.getResid() != null && res.getResid() != ""){
			resid = res.getResid().trim();
			request.getSession().setAttribute("resid", resid);
		}//如果传入了resid 资源id就 王session中set一下，覆盖一下以前的。否则不做操作（资源id还是以前的）。
		
		
		
		log.debug("showTa_org_relationList");
		ModelAndView mv = new ModelAndView();
		// 获取所有的终端来选择表
		List<Ta_category> listCate = ta_org_relationService
				.queryforcateName();

		//是否有业务的提示信息
		String dataMsg = null;
		// 如果该终端不存在，直接返回并且跳转到终端添加页面
		if (listCate == null || listCate.size() == 0) {

			dataMsg = "没有数据，请先添加终端信息！";
			request.setAttribute("dataMsg", dataMsg);
			
		} else {
			request.setAttribute("listCate", listCate);
			// 默认选择第一个终端
			Ta_category insCate =  listCate.get(0);

			String ta_id = request.getParameter("ta_id");
			if (ta_id == null || ta_id.equals("")) {
				ta_id = insCate.getTa_id();
			}
			request.setAttribute("ta_id", ta_id);
			// 通过终端主键（ta_id）获取某一个终端下所有的业务信息
			List<Ta_App> listApp = ta_org_relationService
					.queryAllAppNameByTa_id(ta_id);
			if (listApp == null || listApp.size() == 0) {
				
			} else {
				String orgName = request.getParameter("orgName");
				String orgId = null;

				String appidBack = request.getParameter("appid");
				// 添加后跳转的提示信息
				String InsertMsg = request.getParameter("InsertMsg");
				request.setAttribute("InsertMsg", InsertMsg);

				// 默认选择第一个业务信息
				Ta_App ins = listApp.get(0);
				// 如果从页面端获取的appid为空，那么默认将第一个业务的appid赋予给appidBack
				if (appidBack == null || appidBack == "") {
					// 获取map中的appid的值
					appidBack = String.valueOf(ins.getAppid());
				}
				int appid = Integer.parseInt(appidBack);

				// 通过appid查询出某一业务的所有信息
				Ta_App insTa_id = ta_org_relationService
						.findAppByAppId(appid);

				// 获取业务名称
				String appName = insTa_id.getAppName();
				request.setAttribute("appid", appid);

				request.setAttribute("listApp", listApp);

				request.setAttribute("appName", appName);
				
				// 该list里面放条件查询中机构条件所需的机构主键
				List<Integer> list = new ArrayList<Integer>();
				// 条件查询，如果为空，则查询所有数据
				if (orgName == null || orgName.equals("")) {
					orgName = "";
					orgId = null;
				} else {
					// 从前端获取树状结构中机构id的值
					String tid = request.getParameter("oid");
					if (tid.indexOf("_") != -1) {
						int index = tid.indexOf("_");// 点击全国的时候id是
														// 0_xxxxxx共15位，所以截取
						tid = tid.substring(0, index);
						orgId = tid;

					} else {
						orgId = tid;// 不是全国，queryById之后，再查询list

					}
					// 条件查询有问题的地方 --- 李季
					
					  Organization org =ta_org_relationService.queryOrgById(orgId);
					  List<Organization> orgIdList  = ta_org_relationService.querySubOrgListByOrg(org);
						
						
						Organization organization = new Organization();

						for (int j = 0; j < orgIdList.size(); j++) {
							// 循环获取批量删除的机构主键
							organization = (Organization) orgIdList.get(j);
							int orgIdback = Integer
									.parseInt(organization.getOrgId().toString());

							list.add(orgIdback);
						}
				}

				
				  //System.out.println("orgIdList======="+list+"++++++++++");
				 

				// 获取当前页数
				int pageNow = 1;
				if (request.getParameter("pageNow") == null
						|| "".equals(request.getParameter("pageNow"))
						|| request.getParameter("pageNow").equals("undefined")
						) {
					if (request.getParameter("pageJump") == null
							|| "".equals(request.getParameter("pageJump"))
							|| request.getParameter("pageJump").equals("undefined")
							) {
						pageNow = 1;
					} else {
						pageNow = Integer.parseInt(request
								.getParameter("pageJump"));
					}

				} else {
					pageNow = Integer.parseInt(request.getParameter("pageNow"));
				}

				PageModel pageModel = new PageModel();
				// 每页显示的记录数
				pageModel.setPageNow(pageNow);
				int pageSize = pageModel.getPageSize();
				int start = pageModel.getStart();

/*				Ta_org_relation ta_org_relation = new Ta_org_relation();
				ta_org_relation.setStart(start);
				ta_org_relation.setPageSize(pageSize);

				ta_org_relation.setAppid(appid);*/

				// 该map里面放批量删除所需的所有数据
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", list);
				map.put("start", start);
				map.put("pageSize", pageSize);
				map.put("appid", appid);
				map.put("orgId", orgId);
				// 查询出所有开业业务的机构记录
				List<Ta_org_relation> relationAll = ta_org_relationService
						.queryAllByApp(map);
				// 获取记录总数
				int count = relationAll.size();
				pageModel.setCount(count);

				// 获取总页数
				int pageCount = pageModel.getTotalPages();

				int pageUp = pageNow - 1;
				pageModel.setPageUp(pageUp);
				int pageDown = pageNow + 1;
				pageModel.setPageDown(pageDown);
				pageModel.setPageNow(pageNow);
				pageModel.setPageCount(pageCount);

				request.setAttribute("orgName", orgName);
				request.setAttribute("pageModel", pageModel);

				// 得到了分页查询每页的的数据
				List<Ta_org_relation> orgRelation = ta_org_relationService
						.queryOrgByApp(map);
				request.setAttribute("orgRelation", orgRelation);

				dataMsg = "该终端下有业务";
				request.setAttribute("dataMsg", dataMsg);
			}
		}
		mv.setViewName("/ta_org_relationManager/orgRelationList");
		return mv;
	}

}
