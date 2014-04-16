package adtec.appManager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import adtec.accountManager.model.Ta_Account_cateName;
import adtec.appManager.model.PageModel;
import adtec.appManager.model.Ta_App;
import adtec.appManager.model.Ta_App_relation;
import adtec.appManager.service.Ta_App_relationService;
import adtec.categoryManager.model.Ta_category;
import adtec.privilege.model.Resource;
import adtec.privilege.service.ResourceService;
import adtec.ta_org_relationManager.model.Ta_org_relation;
@Controller
@RequestMapping(value = "/relation")
public class Ta_App_relationController {

	Logger log = Logger.getLogger(Ta_App_relationController.class);
	
	private Ta_App_relationService ta_App_relationService;
	private ResourceService resourceService; 

	public Ta_App_relationService getTa_App_relationService() {
		return ta_App_relationService;
	}

	public void setTa_App_relationService(
			Ta_App_relationService ta_App_relationService) {
		this.ta_App_relationService = ta_App_relationService;
	}
	public ResourceService getResourceService() {
		return resourceService;
	}
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 *  批量删除方法
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/batchdelete")
	public void batchdelete(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("batchdelete");
		String accountName = request.getParameter("accountName");
		String ta_id = request.getParameter("ta_id");
		//通过ta_id来获取cateName的值
		Ta_category ins = ta_App_relationService
				.findcateNameByta_id(ta_id);
		String cateName = ins.getCateName();
		//删除的提示信息
		String delMsg = null;
		try {
			//从前台获取的accountName的个数如果大于1，那么挨个都删除，如果是1个，那么直接删除就OK
			if (accountName.length() > 1) {
				for (int i = 0; i < accountName.split(",").length; i++) {
					String j = accountName.split(",")[i];
					Ta_App_relation ta_App_relation = new Ta_App_relation();
					ta_App_relation.setAccountName(j);
					ta_App_relationService.deleteTa_App_relationByaccountName(ta_App_relation);
				}
				ta_App_relationService.batchdelete(accountName, cateName);
			} else {
				Ta_App_relation ta_App_relation = new Ta_App_relation();
				ta_App_relation.setAccountName(accountName);
				ta_App_relationService.deleteTa_App_relationByaccountName(ta_App_relation);
			}
			delMsg = "数据删除成功！";
			log.debug("开通的业务删除成功！");
		} catch (Exception e1) {
			delMsg = "网络故障！";
			log.error("开通的业务删除失败！");
		}
		String json = ClassToJson.object2json(delMsg);
		try {
			response.setContentType("text/html;charset=utf-8");
			/*response.getWriter().write(json);*/
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 校验帐号是否存在
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/check")
	public ModelAndView checkUserName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("checkUserName");
		String accountName = request.getParameter("accountName");
		String ta_id = request.getParameter("ta_id");
		String cateName = "";
		
		Ta_category ins = ta_App_relationService.findcateNameByta_id(ta_id);
		if(ins !=null){
			cateName = ins.getCateName();
		}
		System.out.println("ins:" + ins + "    -------ta_id:" + ta_id);
		
		if((cateName != "") && cateName !=null){
			Ta_Account_cateName ta_Account_cateName = new Ta_Account_cateName();
			ta_Account_cateName.setAccountName(accountName);
			int count = ta_App_relationService.queryforcheck(ta_Account_cateName,
					cateName);
			String json = ClassToJson.object2json(count);
			try {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.write(json);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				log.error("校验帐号是否存在失败！");
			}
		}
		
		return null;
	}

	/**
	 * 校验 校验帐号是否重复
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkback")
	public ModelAndView checkbackUserName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String accountName = request.getParameter("accountName");
		String ta_id = request.getParameter("ta_id");
		Ta_App_relation ta_App_relation = new Ta_App_relation();
		ta_App_relation.setAccountName(accountName);
		ta_App_relation.setTa_id(ta_id);
		int count = ta_App_relationService.queryforCheckNewAccoutNameByAccountName(ta_App_relation);
		String json = ClassToJson.object2json(count);
		/*response.getWriter().print(json);*/
		try {
			response.setContentType("text/html;charset=utf-8");
			/*response.getWriter().write(json);*/
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			//e.printStackTrace();
			log.error("校验帐号是否重复失败！");
		}

		return null;
	}
	
	
	/**
	 * 校验该帐号所在的机构是否已经开通该业务
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkAccountApp")
	public ModelAndView checkAccountApp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("checkAccountApp");
		String accountName = request.getParameter("accountName");
		String ta_id = request.getParameter("ta_id");
		String[] appids = request.getParameterValues("appid");

		//通过ta_id来获取cateName
		Ta_category ins = ta_App_relationService.findcateNameByta_id(ta_id);
		String cateName = ins.getCateName();
		//通过帐号和cateName来获取该帐号所在机构
		String orgId = ta_App_relationService.findOrgIdByAccount(cateName, accountName);

		int count = 0;
		for(int i=0;i<appids.length;i++){
			int appid = Integer.parseInt(appids[i]);
			Ta_org_relation ta_org_relation = new Ta_org_relation();
			ta_org_relation.setAppid(appid);
			ta_org_relation.setOrgId(orgId);
			ta_org_relation.setTa_id(ta_id);
			count = ta_App_relationService.queryAppCountByOrgRelation(ta_org_relation);
			if(count >0){
				break;
			}
		}
		String json = ClassToJson.object2json(count);
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			log.error("校验帐号是否存在失败！");
		}
		return null;
	}
	

	/**
	 * 添加业务开通的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/relationInsert")
	public ModelAndView showRelationAdd(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("showRelationAdd");
		ModelAndView mv = new ModelAndView();
		// 获取ta_id
		String ta_id = request.getParameter("ta_id");
		request.setAttribute("ta_id", ta_id);
		// 通过ta_id获取所有该终端下的业务组成checkbox
		List<Ta_App> rows = ta_App_relationService
				.queryforRelation(ta_id);
		request.setAttribute("rows", rows);
		// 跳转到添加的jsp页面
		mv.setViewName("/appManager/relation/relationInsert");
		return mv;
	}

	/**
	 * 添加业务开通的处理方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/insertAction", method = RequestMethod.POST)
	public String addRelationAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("addRelationAction");
		String ta_id = request.getParameter("ta_id");
		request.setAttribute("ta_id", ta_id);
		String accountName = request.getParameter("accountName"); // 1
		//获取checkbox选中的值(选中的业务)
		String[] appids = request.getParameterValues("appid"); // 1
		//得到被选中业务的长度（appids的长度）
		// 添加后提示信息
		String PromptMsg = "";
		if(appids !=null){
			int size = appids.length;

			//将要开通的业务信息放到list中
			List<Ta_App_relation> list = new ArrayList<Ta_App_relation>();
			try {
				//循环为某个帐号开通业务
				for (int i = 0; i < size; i++) {
					int c = Integer.parseInt(appids[i]);
					Ta_App_relation ta_App_relation = new Ta_App_relation();
					ta_App_relation.setAccountName(accountName);
					ta_App_relation.setAppid(c);
					ta_App_relation.setTa_id(ta_id);
					list.add(ta_App_relation);
				}
				//批量开通业务
				ta_App_relationService.add(list);
				PromptMsg = "InsertMsg";
				log.debug("业务开通成功！");
			}  catch (Exception e) {
				PromptMsg = "failMsg";
				log.error("业务开通失败！");
			}
			request.setAttribute("ta_id", ta_id);
		}
		
		
		// 跳转到list的Controller
		return "redirect:/relation/relationList.do?ta_id=" + ta_id + "&PromptMsg="
				+ PromptMsg;
	}

	/**
	 * 查询展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/relationList")
	public ModelAndView showRelationList(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> map,Resource res) {
		String resid = "";
		if(res!=null && res.getResid() != null && res.getResid() != ""){
			resid = res.getResid().trim();
			request.getSession().setAttribute("resid", resid);
		}//如果传入了resid 资源id就 王session中set一下，覆盖一下以前的。否则不做操作（资源id还是以前的）。
		
		
		log.debug("showRelationList");
		ModelAndView mv = new ModelAndView();
		// 查询出所有的终端做选择表
		List<Ta_category> list = ta_App_relationService
				.queryforcateName();
		if (list == null || list.size() == 0) {
			String NoData = "没有数据，请先添加终端信息！";
			String json = ClassToJson.object2json(NoData);
			request.setAttribute("json", json);
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(json);
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {

			// 添加或修改时的提示信息
			String PromptMsg = request.getParameter("PromptMsg");
			request.setAttribute("PromptMsg", PromptMsg);

			String ta_id = request.getParameter("ta_id");
			Ta_category ta_category = list.get(0);
			if (ta_id == null || ta_id == "") {
				ta_id = ta_category.getTa_id();
			}

			Ta_category mapCateName = ta_App_relationService
					.findcateNameByta_id(ta_id);
			String cateName = mapCateName.getCateName();

			map.put("list", list);

			request.setAttribute("ta_id", ta_id);

			// 获取条件查询的值
			String accountName = request.getParameter("accountName");
			String orgName = request.getParameter("orgName");
			String orgId;

			// 获取当前页数
			int pageNow = 1;
			if (request.getParameter("pageNow") == null
					|| "".equals(request.getParameter("pageNow"))
					|| request.getParameter("pageNow").equals("undefined")) {
				if (request.getParameter("pageJump") == null
						|| "".equals(request.getParameter("pageJump"))
						|| request.getParameter("pageJump").equals("undefined")
						) {
					pageNow = 1;
				} else {
					pageNow = Integer
							.parseInt(request.getParameter("pageJump"));
				}

			} else {
				pageNow = Integer.parseInt(request.getParameter("pageNow"));
			}

			if (accountName == null || accountName.equals("")) {
				accountName = "";
			}
			String accountName1 = accountName.trim();
			if (orgName == null || orgName.equals("")) {

				orgId = "";
				orgName = "";
				Ta_App_relation ta_App_relation = new Ta_App_relation();
				ta_App_relation.setTa_id(ta_id);
				ta_App_relation.setAccountName(accountName1);

				Ta_Account_cateName ta_Account_cateName = new Ta_Account_cateName();
				ta_Account_cateName.setOrgId(orgId);

				PageModel pageModel = new PageModel();
				
				// 每页显示的记录数
				pageModel.setPageNow(pageNow);
				int pageSize = pageModel.getPageSize();
				int start = pageModel.getStart();
				

				ta_App_relation.setStart(start);
				ta_App_relation.setPageSize(pageSize);

				List<Ta_App_relation> relationAll = ta_App_relationService
						.queryTa_App_relationforCount(ta_App_relation, ta_Account_cateName,
								cateName);

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

				List<Ta_App_relation> relation = ta_App_relationService.queryforTa_App_relation(ta_App_relation,
						ta_Account_cateName, cateName);
				request.setAttribute("relation", relation);

				request.setAttribute("pageModel", pageModel);

				request.setAttribute("accountName", accountName1);
				request.setAttribute("orgName", orgName);

				String json = ClassToJson.object2json(relation);
				request.setAttribute("json", json);
				try {
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write(json);
				} catch (IOException e) {

					e.printStackTrace();
				}
			} else {

				String tid = request.getParameter("input");
				if (tid.indexOf("_") != -1) {
					int index = tid.indexOf("_");// 点击全国的时候id是 0_xxxxxx共15位，所以截取
					tid = tid.substring(0, index);
					orgId = tid;

				} else {
					orgId = tid;// 不是全国，queryById之后，再查询list

				}

				Ta_App_relation ta_App_relation = new Ta_App_relation();
				ta_App_relation.setTa_id(ta_id);
				ta_App_relation.setAccountName(accountName1);

				Ta_Account_cateName ta_Account_cateName = new Ta_Account_cateName();
				ta_Account_cateName.setOrgId(orgId);

				// 每页显示的记录数
				int pageSize = 3;
				int start = (pageNow - 1) * pageSize;

				ta_App_relation.setStart(start);
				ta_App_relation.setPageSize(pageSize);
				List<Ta_App_relation> relationAll = ta_App_relationService.queryTa_App_relationforCount(
						ta_App_relation, ta_Account_cateName, cateName);
				// 获取记录总数
				int count = relationAll.size();

				// 获取总页数
				// int pageCount=count/pageSize;
				int pageCount = count % pageSize == 0 ? count / pageSize
						: count / pageSize + 1;

				List<Ta_App_relation> relation = ta_App_relationService.queryforTa_App_relation(ta_App_relation,
						ta_Account_cateName, cateName);
				request.setAttribute("relation", relation);

				request.setAttribute("pageNow", pageNow);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageUp", pageNow - 1);
				request.setAttribute("pageDown", pageNow + 1);

				request.setAttribute("accountName", accountName1);
				request.setAttribute("orgName", orgName);

				String json = ClassToJson.object2json(relation);
				request.setAttribute("json", json);
				try {
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write(json);
				} catch (IOException e) {

					e.printStackTrace();
				}

			}

		}

		// 跳转到查询的jsp页面
		mv.setViewName("/appManager/relation/relationList");
		return mv;
	}

	/**
	 * 单一查询的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/relationFindById")
	public ModelAndView showRelationbyid(HttpServletRequest request,
			HttpServletResponse response, Ta_App_relation ta_App_relation) {
		log.debug("showRelationbyid");
		ModelAndView mv = new ModelAndView();
		String ta_id = request.getParameter("ta_id");

		String accountName = request.getParameter("accountName");
		ta_App_relation.setTa_id(ta_id);
		ta_App_relation.setAccountName(accountName);
		// 通过ta_id获取所有该终端下的业务组成checkbox
		List<Ta_App> rows = ta_App_relationService.queryforRelation(ta_id);
		request.setAttribute("rows", rows);
		//获取某一帐号开通业务的详细信息
		List<Ta_App_relation> list = ta_App_relationService.findTa_App_relationbyTa_App_relation(ta_App_relation);

		//循环比较，获得已经开通的业务，作出标记，在前台显示
		for (int i = 0; i < rows.size(); i++) {
			Ta_App ins1 = rows.get(i);
			ins1.getAppid();
			for (int j = 0; j < list.size(); j++) {
				Ta_App_relation ins2 = list.get(j);
				if (ins2.getAppid() == ins1.getAppid()) {
					ins1.setEf("1");
				}
			}
		}
		request.setAttribute("list", list);
		request.setAttribute("ta_id", ta_id);

		Ta_App_relation map = list.get(0);
		request.setAttribute("map", map);
		// 跳转到单一实例查询的jsp页面
		mv.setViewName("/appManager/relation/relationFindById");

		return mv;
	}

	/**
	 * 删除整条数据
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delAction")
	public void delAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("delAction");
		String ta_id = request.getParameter("ta_id");
		String accountName = request.getParameter("accountName");
		//删除提示信息
		String delMsg = null;
		try {
			Ta_App_relation ta_App_relation = new Ta_App_relation();
			ta_App_relation.setAccountName(accountName);
			//调用删除方法
			ta_App_relationService.deleteTa_App_relationByaccountName(ta_App_relation);
			delMsg = "数据删除成功！";
			log.debug("开通的业务删除成功！");
		} catch (Exception e1) {
			delMsg = "网络故障！";
			log.error("开通的业务删除失败！");
		}
		request.setAttribute("ta_id", ta_id);
		String json = ClassToJson.object2json(delMsg);
		try {
			response.setContentType("text/html;charset=utf-8");
			/*response.getWriter().write(json);*/
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/relationUpdate")
	public ModelAndView showAppManagerupdate(HttpServletRequest request,
			HttpServletResponse response, Ta_App_relation ta_App_relation) {
		log.info("showAppManagerupdate");
		ModelAndView mv = new ModelAndView();

		String ta_id = request.getParameter("ta_id");

		String accountName = request.getParameter("accountName");
		ta_App_relation.setTa_id(ta_id);
		ta_App_relation.setAccountName(accountName);
		// 通过ta_id获取所有该终端下的业务组成checkbox
		List<Ta_App> rows = ta_App_relationService.queryforRelation(ta_id);
		request.setAttribute("rows", rows);
		//获取某一帐号开通业务的详细信息
		List<Ta_App_relation> list = ta_App_relationService.findTa_App_relationbyTa_App_relation(ta_App_relation);
		//循环比较，获得已经开通的业务，作出标记，在前台显示
		for (int i = 0; i < rows.size(); i++) {
			Ta_App ins1 = rows.get(i);
			ins1.getAppid();
			for (int j = 0; j < list.size(); j++) {
				Ta_App_relation ins2 = list.get(j);
				if (ins2.getAppid()==ins1.getAppid()) {
					ins1.setEf("1");
				}
			}
		}

		request.setAttribute("list", list);
		request.setAttribute("ta_id", ta_id);

		Ta_App_relation map =list.get(0);
		request.setAttribute("map", map);
		// 跳转到修改的jsp页面
		mv.setViewName("/appManager/relation/relationUpdate");
		return mv;
	}

	/**
	 * 修改的处理方法
	 * 
	 * @param request
	 * @param response
	 * @param user
	 *            对应的javabean
	 * @return
	 */
	@RequestMapping(value = "/updateAction", method = RequestMethod.POST)
	public String updateAppManagerAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("updateddflglAction");
		String ta_id = request.getParameter("ta_id");
		request.setAttribute("ta_id", ta_id);
		String accountName = request.getParameter("accountName"); // 1

		String[] a = request.getParameterValues("appid"); // 1
		int size = a.length;

		//list中放置Ta_App_relation对象，里面放着所有选中的appid
		List<Ta_App_relation> list = new ArrayList<Ta_App_relation>();
		// 修改时的提示信息
		String PromptMsg = null;
		try {
			//循环将每一个appid取出来，与其他属性放入Ta_App_relation对象中
			for (int i = 0; i < size; i++) {
				int c = Integer.parseInt(a[i]);
				Ta_App_relation ta_App_relation = new Ta_App_relation();
				ta_App_relation.setAccountName(accountName);
				ta_App_relation.setAppid(c);
				ta_App_relation.setTa_id(ta_id);
				list.add(ta_App_relation);
			}
			//调用修改方法
			ta_App_relationService.update(list);
			PromptMsg = "UpdateMsg";
			log.debug("开通的业务修改成功！");
		} catch (Exception e) {
			PromptMsg = "failMsg";
			log.error("开通的业务修改失败！");
		}
		// 跳转到list的Controller
		return "redirect:/relation/relationList.do?ta_id=" + ta_id + "&PromptMsg="
				+ PromptMsg;
	}

	/**
	 * 为机构开通业务的处理方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/insertforOrgAction", method = RequestMethod.POST)
	public String RelationAddforOrgAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("RelationAddforOrgAction");

		String ta_id = request.getParameter("ta_id");
		String id = request.getParameter("input");
		if (id.length() >= 15 || id == null || id == "") {
			id = "0"; // 说明点击的时全国这个id不是数据库的，而是 tree里面获取的。
		}

		int orgId = new Integer(id);
		Ta_category ins = ta_App_relationService.findcateNameByta_id(ta_id); // 通过传递的ta_id获取cateName的值
		String cateName = ins.getCateName();

		String[] a = request.getParameterValues("appid"); // 获取checkbox选中的值
		int size = a.length; // 计算出checkbox的长度值

		for (int i = 0; i < size; i++) {
			int appid = Integer.parseInt(a[i]);
			ta_App_relationService.deleteforOrg(appid, orgId, cateName);
		}
		List<Ta_App_relation> list = new ArrayList<Ta_App_relation>();
		List<Ta_Account_cateName> aaa = ta_App_relationService.queryforOrg(cateName, orgId);

		//跳转提示信息
		String PromptMsg = null;
		if (aaa.size() == 0) {
			PromptMsg = "NoInsertMsg";

		} else {

			Ta_Account_cateName ta_Account_cateName = new Ta_Account_cateName();

			for (int x = 0; x < size; x++) {
				int appid = Integer.parseInt(a[x]);
				for (int y = 0; y < aaa.size(); y++) {
					ta_Account_cateName = aaa.get(y);
					String accountName = ta_Account_cateName.getAccountName();
					Ta_App_relation ta_App_relation = new Ta_App_relation();
					ta_App_relation.setTa_id(ta_id);
					ta_App_relation.setAppid(appid);
					ta_App_relation.setAccountName(accountName);
					list.add(ta_App_relation);
				}
			}
			ta_App_relationService.insertforOrg(list);
			// 添加后提示信息
			PromptMsg = "InsertMsg";
		}
		// 跳转到list的Controller
		return "redirect:/relation/relationList.do?PromptMsg=" + PromptMsg;
	}
}
