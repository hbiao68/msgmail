package adtec.appManager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import adtec.appManager.model.PageModel;
import adtec.appManager.model.Ta_App;
import adtec.appManager.service.AppManagerService;
import adtec.categoryManager.model.Ta_category;
import adtec.privilege.model.Resource;
import adtec.privilege.service.ResourceService;

/**
 * @FileName: AppManagerController
 * 
 * @FileType: Class
 * 
 * @Date: 2013年10月25日
 * 
 * @Author: 李季
 * 
 * @Description: 业务管理Controller
 * 
 */
@Controller
@RequestMapping(value = "/appManager")
public class AppManagerController {

	private AppManagerService appManagerService;
	private ResourceService resourceService; 
	public AppManagerService getAppManagerService() {
		return appManagerService;
	}
	public void setAppManagerService(AppManagerService appManagerService) {
		this.appManagerService = appManagerService;
	}
	public ResourceService getResourceService() {
		return resourceService;
	}
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	Logger log = Logger.getLogger(AppManagerController.class);

	/**
	 * 业务的批量删除
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/batchdelete")
	public void batchdelete(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("batchdelete");
		// @ResponseBody
		String id = request.getParameter("ids");
		/*String appState = request.getParameter("appState");*/
		List<Integer> list = new ArrayList<Integer>();
		
		
		//maojd update date:10:54 2013/12/26	验证该业务是否已经被机构使用，没有机构开通此可以才可以删除
	//	log.info("batchdelete appids is:"+id);
		int accNum = 0;	//业务下的账号数量
		int orgNum = 0; //业务下的机构数量
		String ass = "";//需要返回的信息。是否可以删除
		
		if(id.length()>0){
			for (int i = 0; i < id.split(",").length; i++) {
				//j 业务id (appid)
				int j = Integer.parseInt(id.split(",")[i]);
				
				//通过appid查询该业务该多少账号使用
				accNum = appManagerService.queryForCheckAppid(j);
				
				log.info("batchdelete appid is:"+j+".    accNum is:"+accNum);
				
				if(accNum == 0){
					//如果该业务没有被账号使用，查询是否被某机构使用。即：查询此业务的机构数量
					
					orgNum = appManagerService.queryOrgNumByAppid(j);
					
					if(orgNum==0){
						list.add(j);
					}else{
						//业务下有机构
						ass = "不许删除，请选择尚无帐号开通的业务进行删除！";
						break;
					}
					
				}else{
					//业务下有账号break
					ass = "不许删除，请选择尚无帐号开通的业务进行删除！";
					break;
				}
			}
			
			//循环完了，ass 还是空，说明 验证过程没有出现禁止删除情况
			if(ass == ""){
				try {
					appManagerService.batchdelete(list);
					ass = "数据删除成功！";
					log.debug("业务删除成功！");
				} catch (Exception e) {
					ass = "网络故障！";
					log.error("业务删除失败！");
				}
				
			}else{
				//不做处理
			}
			
			//ass处理完毕。输出json
			String json = ClassToJson.object2json(ass);
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
		
	}

	/**
	 * 为检查新添加业务校验
	 * 
	 * @return
	 */
	@RequestMapping(value = "/check")
	public ModelAndView checkappName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("checkappName");
		String appName = request.getParameter("appName");
		String ta_id = request.getParameter("ta_id");
		Ta_App ta_App = new Ta_App();
		ta_App.setAppName(appName);
		ta_App.setTa_id(ta_id);
		int count = appManagerService.queryforcheckNewAppName(ta_App);

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

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加业务的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/appInsert")
	public ModelAndView showAppManagerAdd(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("showAppManagerAdd");
		ModelAndView mv = new ModelAndView();
		// 获取下拉列表内容
		List<Ta_category> rows = appManagerService.queryforcateName();
		request.setAttribute("rows", rows);
		//跳转到添加展示的jsp页面
		mv.setViewName("/appManager/app/appInsert");
		return mv;
	}

	/**
	 * 添加业务的处理方法
	 * 
	 * @param request
	 * @param response
	 * @param ta_App
	 *            对应的javabean
	 * @return
	 */

	@RequestMapping(value = "/insertAction", method = RequestMethod.POST)
	public String addAppManagerAction(HttpServletRequest request,
			HttpServletResponse response, Ta_App ta_App) {
		log.debug("addAppManagerAction");
		//跳转提示
		String PromptMsg = null;
		try {
			String appName = request.getParameter("appName");
			String appDomain = request.getParameter("appDomain");
			String ta_id = request.getParameter("ta_id");

			//去掉空格
			String appNameTrim = appName.trim();
			String appDomainTrim = appDomain.trim();

			ta_App.setTa_id(ta_id);
			ta_App.setAppName(appNameTrim);
			ta_App.setAppDomain(appDomainTrim);
			//调用添加方法
			appManagerService.addforApp(ta_App);
			PromptMsg = "InsertMsg";
			log.debug("业务添加成功！");
		} catch (Exception e) {
			PromptMsg = "failMsg";
			log.error("业务添加失败！");
		}
		//跳转到查询的Controller
		return "redirect:/appManager/appList.do?PromptMsg="+PromptMsg;
	}

	/**
	 * 查询的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/appList")
	public ModelAndView showAppManagerList(HttpServletRequest request,
			HttpServletResponse response,Resource res) {
		String resid = "";
		if(res!=null && res.getResid() != null && res.getResid() != ""){
			resid = res.getResid().trim();
			request.getSession().setAttribute("resid", resid);
		}//如果传入了resid 资源id就 王session中set一下，覆盖一下以前的。否则不做操作（资源id还是以前的）。
		
		
		
		log.debug("showAppManagerList");
		ModelAndView mv = new ModelAndView();

		//获取所有的终端来做下拉列表
		List<Ta_category> rows = appManagerService.queryforcateName();

		//是否有终端的提示信息
		String dataMsg = null;
		//如果没有终端，跳转到终端分类管理页面
		if (rows == null || rows.size() == 0) {
			dataMsg = "没有数据，请先添加终端信息！";
			request.setAttribute("dataMsg", dataMsg);
		} else {
			request.setAttribute("rows", rows);
			//添加或修改时的提示信息
			String PromptMsg = request.getParameter("PromptMsg");
			request.setAttribute("PromptMsg", PromptMsg);
			
			//获取条件查询的值
			String appName = request.getParameter("appName");
			String ta_id = request.getParameter("ta_id");
			String appState = request.getParameter("appState");
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
					pageNow = Integer
							.parseInt(request.getParameter("pageJump"));
				}

			} else {
				pageNow = Integer.parseInt(request.getParameter("pageNow"));
			}

			if (appName == null) {
				appName = "";
				ta_id = "";
			}
			//获取的查询条件的值去掉空格
			String appNameTrim = appName.trim();

			Ta_App ta_App = new Ta_App();
			ta_App.setAppName(appNameTrim);
			ta_App.setTa_id(ta_id);

			PageModel pageModel = new PageModel();
			// 每页显示的记录数
			pageModel.setPageNow(pageNow);
			int pageSize = pageModel.getPageSize();
			int start = pageModel.getStart();

			ta_App.setStart(start);
			ta_App.setPageSize(pageSize);

			//分页查询获取每一页的数据
			List<Ta_App> appManager = new ArrayList<Ta_App>();
			//获取所有的数据，用来获取所有数据的条数
			List<Ta_App> appManagerAll = new ArrayList<Ta_App>();
			//业务开通的状态（appState）:1代表所有已有帐号开通的业务，2代表所有尚未有帐号开通业务的业务
			if (appState == "1" || "1".equals(appState)) {
				appManager = appManagerService.queryOpenAppByTaApp(ta_App);
				appManagerAll = appManagerService.queryOpenAppforCountByTaApp(ta_App);
			} else if (appState == "2" || "2".equals(appState)) {
				appManager = appManagerService.queryUnOpenAppByTaApp(ta_App);
				appManagerAll = appManagerService.queryUnOpenAppforCountByTaApp(ta_App);
			} else {
				appManager = appManagerService.queryAppByTaApp(ta_App);
				appManagerAll = appManagerService.queryAPPforCountByTaApp(ta_App);
			}
	
			// 获取记录总数
			int count = appManagerAll.size();
			pageModel.setCount(count);
			// 获取总页数
			int pageCount = pageModel.getTotalPages();

			int pageUp = pageNow - 1;
			pageModel.setPageUp(pageUp);
			int pageDown = pageNow + 1;
			pageModel.setPageDown(pageDown);
			pageModel.setPageNow(pageNow);
			pageModel.setPageCount(pageCount);

			request.setAttribute("pageModel", pageModel);
			request.setAttribute("appManager", appManager);
			request.setAttribute("ta_id", request.getParameter("ta_id"));
			request.setAttribute("appName", appName);
			request.setAttribute("appState", appState);

			dataMsg = "该终端下有业务";
			request.setAttribute("dataMsg", dataMsg);

		}

		//跳转到list查询的jsp页面
		mv.setViewName("/appManager/app/appList");

		return mv;
	}

	/**
	 * 单一查询的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/appFindById")
	public ModelAndView showAppManagerbyid(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("showAppManagerbyid");
		ModelAndView mv = new ModelAndView();
		
		//maojd update date:16:20 2013/12/26		通过ta_id查询cateDesc终端描述，在单一实例查询页面用。去掉查询所有种类
		/*List rows = appManagerService.queryforcateName();
		request.setAttribute("rows", rows);*/
		
		int appid = new Integer(request.getParameter("appid"));
		Ta_App ins = appManagerService.findAppById(appid);
		String ta_id = ins.getTa_id();
		String cateDesc = appManagerService.queryCateDescByTa_id(ta_id);
		
		request.setAttribute("cateDesc", cateDesc);
		request.setAttribute("ins", ins);
		//跳转到单一实例查询的jsp页面
		mv.setViewName("/appManager/app/appFindById");
		return mv;
	}

	/**
	 * 删除整条数据
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delAction", method = RequestMethod.POST)
	public void delAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("delAction");
		int appid = new Integer(request.getParameter("appid"));
		int i = appManagerService.queryForCheckAppid(appid);
		//查询账号数量
		//maojd update date:16:14 2013/12/25	验证该业务是否已经被机构使用，没有机构开通此可以才可以删除
		int orgNum = appManagerService.queryOrgNumByAppid(appid);
		String ass = null;
		if (i == 0 && orgNum == 0) {
			try {
				appManagerService.deleteAppById(appid);
				ass = "数据删除成功！";
				log.debug("数据删除成功！");
			} catch (Exception e) {
				ass = "网络故障！";
				log.error("数据删除失败！");
			}
			
		} else {
			ass = "已有帐号开通该业务,无法删除！";
		}
		String json = ClassToJson.object2json(ass);
		try {
			response.setContentType("text/html;charset=utf-8");
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
	@RequestMapping(value = "/appUpdate")
	public ModelAndView showAppManagerupdate(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("showAppManagerupdate");
		ModelAndView mv = new ModelAndView();
		List<Ta_category> rows = appManagerService.queryforcateName();
		request.setAttribute("rows", rows);
		int appid = new Integer(request.getParameter("appid"));
		Ta_App ins = appManagerService.findAppById(appid);
		request.setAttribute("ins", ins);
		//跳转到修改的jsp页面
		mv.setViewName("/appManager/app/appUpdate");
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
			HttpServletResponse response, Ta_App ta_App) {
		log.debug("updateAppManagerAction");
		String PromptMsg = null;
		try {
			int appid = new Integer(request.getParameter("appid"));

			String appName = request.getParameter("appName");
			String appDomain = request.getParameter("appDomain");
			String ta_id = request.getParameter("ta_id");

			String appDomainTrim = appDomain.trim();

			ta_App.setTa_id(ta_id);
			ta_App.setAppName(appName);
			ta_App.setAppDomain(appDomainTrim);

			ta_App.setAppid(appid);
			appManagerService.updateAppById(ta_App);
			PromptMsg = "UpdateMsg";
			log.debug("业务修改成功！");
		} catch (Exception e) {
			PromptMsg = "failMsg";
			log.error("业务修改失败！");
		}
		
		//跳转到查询的Controller
		return "redirect:/appManager/appList.do?PromptMsg="+PromptMsg;
	}
}
