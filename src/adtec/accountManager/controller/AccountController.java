package adtec.accountManager.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import adtec.accountManager.model.Ta_Account_cateName;
import adtec.accountManager.model.Ta_ExtendProp_cateName;
import adtec.accountManager.service.Ta_Account_cateNameService;
import adtec.appManager.controller.ClassToJson;
import adtec.appManager.model.PageModel;
import adtec.appManager.model.Ta_App_relation;
import adtec.categoryManager.model.Ta_category;
import adtec.organizationManager.service.OrganizationService;
import adtec.privilege.model.Resource;
import adtec.privilege.service.ResourceService;

/**
 * @FileName: AccountController
 * 
 * @FileType: Class
 * 
 * @Date: 2013年10月24日
 * 
 * @Author: 李季
 * 
 * @Description: 端点帐号管理Controller
 * 
 */
@Controller
@RequestMapping(value = "/accountManager")
public class AccountController {

	private Ta_Account_cateNameService ta_Account_cateNameService;
	private ResourceService resourceService; 
	@Autowired
	OrganizationService orgService;

	public OrganizationService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrganizationService orgService) {
		this.orgService = orgService;
	}

	public Ta_Account_cateNameService getTa_Account_cateNameService() {
		return ta_Account_cateNameService;
	}

	public void setTa_Account_cateNameService(
			Ta_Account_cateNameService ta_Account_cateNameService) {
		this.ta_Account_cateNameService = ta_Account_cateNameService;
	}
	public ResourceService getResourceService() {
		return resourceService;
	}
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	Logger log = Logger.getLogger(AccountController.class);

	/**
	 * 校验新添加的帐号是否存在
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/check")
	public ModelAndView checkaccountName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String accountName = request.getParameter("accountName");
			String cateName = request.getParameter("cateName");
			Ta_Account_cateName ta_Account_cateName = new Ta_Account_cateName();
			ta_Account_cateName.setAccountName(accountName);
			//调用方法校验新添加的帐号是否存在
			int count = ta_Account_cateNameService.queryforcheckaccountNameByaccountName(
					ta_Account_cateName, cateName);
			String json = ClassToJson.object2json(count);
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error("校验新添加的帐号是否存在失败！");
			//e.printStackTrace();
		}
		return null;
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
		String id = request.getParameter("ids");
//		String accountState = request.getParameter("accountState");
		String delflag = "2";
		String cateName = request.getParameter("cateName");
		String accountNames = request.getParameter("accountNames");
		
		String ass = "";
		/**
		 * 修改删除账号逻辑。
		 * 页面没有开通业务的账号直接删除，其他状态全部验证（查询一下账号开通数量）。
		 * @author maojd 
		 * update date:2013-12-24 11:24
		 */
		boolean appIsNull = true;
		for(int i = 0;i<accountNames.split(",").length;i++){
			String accountName = accountNames.split(",")[i];
			
			Map<String, String> map = new HashMap<String,String>();
			map.put("accountName", accountName);
			int appNum = ta_Account_cateNameService.queryAppNumByAccountName(map);
			//System.out.println(appNum);
			if(appNum > 0){
				ass = "不许删除，请选择尚未开通业务的帐号来删除！";
				log.debug("不许删除，请选择尚未开通业务的帐号来删除！");
				appIsNull = false;
				break;
			}
		}
		if(appIsNull){//验证了所有，appIsNull为true的话，则说明 所有选中账号都没开通业务，批量删除
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < id.split(",").length; i++) {
				int j = Integer.parseInt(id.split(",")[i]);
				list.add(j);
			}
			
			try {
				boolean tag = ta_Account_cateNameService.batchUpdate(cateName, list, delflag);
				if(tag){
					log.debug("数据删除成功！");
					ass = "数据删除成功！";
				}
			} catch (Exception e) {
				log.error("帐号删除失败！");
				ass = "网络故障！";
			}
			
		}
		
		//最后把是否允许删除的ass输出
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
	 * 添加用户的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/accountInsert")
	public ModelAndView showaccountManagerAdd(HttpServletRequest request,
			HttpServletResponse response, Map<String,Object> map) {
		log.debug("showaccountManagerAdd");
		ModelAndView mv = new ModelAndView();
		String cateName = request.getParameter("cateName");
		String id = request.getParameter("input"); // 从页面获取，点击 树，点击的时是哪个数据（id）
//		log.info("queryOrgLevel " + "id is " + id + ",length is " + id.length());

		if (id.length() >= 15 || id == null || id == "") {
			id = "0"; // 说明点击的时全国这个id不是数据库的，而是 tree里面获取的。
		}

		String orgId = id;

		Ta_Account_cateName ta_Account_cateName = new Ta_Account_cateName();
		ta_Account_cateName.setOrgId(orgId);
		request.setAttribute("orgId", orgId);
		request.setAttribute("cateName", cateName);

		//通过cateName(终端名称)，获取该终端下所有的扩张
		List<Ta_category> extendpropdef = ta_Account_cateNameService
				.queryforTa_extendpropdef(cateName);
		mv.addObject("extendpropdef", extendpropdef);

		//跳转到添加的页面（accountInsert.jsp）
		mv.setViewName("/accountManager/accountInsert");

		return mv;
	}

	/**
	 * 查询的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/accountList")
	public ModelAndView showaccountManagerList(HttpServletRequest request,
			HttpServletResponse response, Map<String,Object> map,Resource res) {
		String resid = "";
		if(res!=null && res.getResid() != null && res.getResid() != ""){
			resid = res.getResid().trim();
			request.getSession().setAttribute("resid", resid);
		}//如果传入了resid 资源id就 王session中set一下，覆盖一下以前的。否则不做操作（资源id还是以前的）。
			
		
		
		log.debug("showaccountManagerList");
		ModelAndView mv = new ModelAndView();

		//查询所有的终端名称来做选择表
		List<Ta_category> list = ta_Account_cateNameService.queryforcateName();
		//是否有帐号的提示信息
		String dataMsg = null;
		if (list == null || list.size() == 0) {
			dataMsg = "没有数据，请先添加终端信息！";
			
			request.setAttribute("dataMsg", dataMsg);
			} 
		else {
			String cateName = request.getParameter("cateName");
			if (cateName == null || cateName == "") {
				Ta_category ta_category = list.get(0);
				
				cateName = ta_category.getCateName();
			}

			map.put("list", list);

			request.setAttribute("cateName", cateName);

			//添加或修改时的提示信息
			String PromptMsg = request.getParameter("PromptMsg");
			request.setAttribute("PromptMsg", PromptMsg);
			
			//查询扩展属性来做Ta_extendpropdef表添加字段的标题
			List<Ta_category> extendpropdef = ta_Account_cateNameService
					.queryforTa_extendpropdef(cateName);

			request.setAttribute("extendpropdef", extendpropdef);
			//获取条件查询的值
			String accountName = request.getParameter("accountName");
			String email = request.getParameter("email");
			String orgName = request.getParameter("orgName");
			String accountState = request.getParameter("accountState");
			String orgId;
			if (accountName == null || accountName.equals("")) {
				accountName = "";
			}
			if (email == null || email.equals("")) {
				email = "";
			}
			String accountName1 = accountName.trim();
			String email1 = email.trim();
			String delflag;

			// 获取当前页数
			int pageNow = 1;
			if (request.getParameter("pageNow") == null
					|| request.getParameter("pageNow").equals("")
					|| request.getParameter("pageNow").equals("undefined")) {
				if (request.getParameter("pageJump") == null
						|| request.getParameter("pageJump").equals("")
						|| request.getParameter("pageJump").equals("undefined")) {
					pageNow = 1;
				} else {
					pageNow = Integer
							.parseInt(request.getParameter("pageJump"));
				}

			} else {
				pageNow = Integer.parseInt(request.getParameter("pageNow"));
			}

			if (request.getParameter("delflag") == null
					|| request.getParameter("delflag").equals("")
					|| request.getParameter("delflag") == "1") {
				delflag = "1";
			} else {
				delflag = request.getParameter("delflag");
			}
			if (orgName == null || orgName.equals("")) {
				orgId = "";
				orgName = "";
			} else {
				/**
				 * 获取机构id 即orgId
				 */
				String tid = request.getParameter("input");
				
				if (tid.indexOf("_") != -1) {
					int index = tid.indexOf("_");// 点击全国的时候id是 0_xxxxxx共15位，所以截取
					tid = tid.substring(0, index);
					orgId = tid;
					/*
					 * org.setOrgId(orgId); org.setOrgName("全国");
					 * //是全国，直接queryOrgLevel
					 */
				} else {
					orgId = tid;// 不是全国，queryById之后，再查询list
				}
			}
			Ta_Account_cateName ta_Account_cateName = new Ta_Account_cateName();
			ta_Account_cateName.setOrgId(orgId);
			ta_Account_cateName.setAccountName(accountName1);
			ta_Account_cateName.setEmail(email1);
			ta_Account_cateName.setDelflag(delflag);

			PageModel pageModel = new PageModel();
			// 每页显示的记录数
			pageModel.setPageNow(pageNow);
			int pageSize = pageModel.getPageSize();
			int start = pageModel.getStart();

			ta_Account_cateName.setStart(start);
			ta_Account_cateName.setPageSize(pageSize);

			List<Ta_Account_cateName> rows = new ArrayList<Ta_Account_cateName>();
			List<Ta_Account_cateName> rowsAll = new ArrayList<Ta_Account_cateName>();
			if (accountState == "1" || "1".equals(accountState)) {
				rows = ta_Account_cateNameService.queryOpenAppTa_Account_cateName(cateName,
						ta_Account_cateName);
				rowsAll = ta_Account_cateNameService.queryOpenAppTa_Account_cateNameforCount(cateName,
						ta_Account_cateName);
			} else if (accountState == "2" || "2".equals(accountState)) {
				rows = ta_Account_cateNameService.queryUnOpenAppTa_Account_cateName(cateName,
						ta_Account_cateName);
				rowsAll = ta_Account_cateNameService.queryUnOpenAppTa_Account_cateNameforCount(
						cateName, ta_Account_cateName);
			} else {
				rows = ta_Account_cateNameService.queryTa_Account_cateName(
						ta_Account_cateName, cateName);
				rowsAll = ta_Account_cateNameService.queryTa_Account_cateNameforCount(
						ta_Account_cateName, cateName);
			}

			// 获取记录总数
			int count = rowsAll.size();
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
			
			request.setAttribute("orgName", orgName);
			request.setAttribute("accountName", accountName1);
			request.setAttribute("email", email1);
			request.setAttribute("accountState", accountState);

			request.setAttribute("rows", rows);

			dataMsg = "该终端下有帐号";
			request.setAttribute("dataMsg", dataMsg);
		}
		
		

		//跳转到accountList查询页面
		mv.setViewName("accountManager/accountList");
		return mv;
	}

	/**
	 * 单一查询的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/accountFindById")
	public ModelAndView showaccountManagerfindbyid(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> map) {
		log.debug("showaccountManagerfindbyid");
		ModelAndView mv = new ModelAndView();
		try {
			String cateName = request.getParameter("cateName");
			String orgId = request.getParameter("orgId");
			//
			List<Ta_category> extendpropdef = ta_Account_cateNameService
					.queryforTa_extendpropdef(cateName);
			mv.addObject("extendpropdef", extendpropdef);
			request.setAttribute("cateName", cateName);
			request.setAttribute("orgId", orgId);
			int id = new Integer(request.getParameter("id"));
			Ta_Account_cateName ins = ta_Account_cateNameService.findById(cateName, id);
			map.put("ins", ins);

			//跳转到单一实例查询页面（accountFindById.jsp）
			mv.setViewName("accountManager/accountFindById");

		} catch (Exception ex) {
			//ex.printStackTrace();
			log.error("通过id查询单一帐号的详细信息失败！");
		}

		return mv;
	}

	/**
	 * 修改展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/accountUpdate")
	public ModelAndView showAccountManagerupdate(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> map) {
		log.debug("showAccountManagerupdate");
		ModelAndView mv = new ModelAndView();
		try {

			String cateName = request.getParameter("cateName");
			String pid = request.getParameter("input"); // 从页面获取，点击
														// 树，点击的时是哪个数据（id）
			log.info("queryOrgLevel " + "pid is " + pid + ",length is "
					+ pid.length());

			if (pid.length() >= 15 || pid == null || pid == "") {
				pid = "0"; // 说明点击的时全国这个id不是数据库的，而是 tree里面获取的。
			}

			int orgId = new Integer(pid);
			request.setAttribute("orgId", orgId);
			//通过cateName(终端名称)，获取该终端下所有的扩张
			List<Ta_category> extendpropdef = ta_Account_cateNameService
					.queryforTa_extendpropdef(cateName);

			mv.addObject("extendpropdef", extendpropdef);
			request.setAttribute("cateName", cateName);

			int id = new Integer(request.getParameter("Id"));
			//查询某一帐号的详细信息
			Ta_Account_cateName ins = ta_Account_cateNameService.findById(cateName, id);
			map.put("ins", ins);
			//跳转到修改页面（accountUpdate.jsp）
			mv.setViewName("accountManager/accountUpdate");

		} catch (Exception ex) {
		//	ex.printStackTrace();
			log.error("通过id跳转到修改页面失败！");
		}

		return mv;
	}

	/**
	 * 添加用户的处理方法
	 * 
	 * @param request
	 * @param response
	 * @param user
	 *            对应的javabean
	 * @return
	 */

	@RequestMapping(value = "/insertAction", method = RequestMethod.POST)
	public String addAccountManagerAction(HttpServletRequest request,
			HttpServletResponse response,
			Ta_Account_cateName ta_Account_cateName,
			Ta_ExtendProp_cateName ta_ExtendProp_cateName) {
		
		String cateName = request.getParameter("cateName");
		
		String accountName = request.getParameter("accountName");
		String accountNameTrim = accountName.trim();
		if("".equals(request.getParameter("accountPwd")) || null == request.getParameter("accountPwd")){
			
		}else{
			String accountPwd = request.getParameter("accountPwd");
			String accountPwdMd5 = Md5Util.getMD5String(accountPwd);
			ta_Account_cateName.setAccountPwd(accountPwdMd5);
		}

		ta_Account_cateName.setAccountName(accountNameTrim);
		
		String pid = request.getParameter("input");

		if (pid.length() >= 15 || pid == null || pid == "") {
			pid = "0"; // 说明点击的时全国这个id不是数据库的，而是 tree里面获取的。
		}
		String orgId = pid;
		request.setAttribute("orgId", orgId);
		request.setAttribute("cateName", cateName);
		//通过cateName来获取ta_id
		Ta_category ins = ta_Account_cateNameService.queryforta_idBycateName(cateName);
		log.debug("addAccountManagerAction " + "cateName:" + cateName + ".accountName:" + accountName + ".orgId:" + orgId);
		
		String ta_id = "";
		if(ins != null){
			ta_id = ins.getTa_id();
		}
		
		ta_Account_cateName.setTa_id(ta_id);
		ta_Account_cateName.setOrgId(orgId);
		//跳转提示信息
		String PromptMsg = null;
		try {
			ta_Account_cateNameService.add(ta_Account_cateName,
					ta_ExtendProp_cateName, cateName);
			PromptMsg = "InsertMsg";
			log.debug("数据添加成功！");
		} catch (Exception e) {
			PromptMsg = "failMsg";
			log.error("数据添加失败！");
		}
		//添加成功，跳转到list.do的Controller
		return "redirect:/accountManager/accountList.do?cateName=" + cateName+"&PromptMsg="+PromptMsg;
	}

	/**
	 * 删除处理
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delAction", method = RequestMethod.POST)
	public void delAccountManagerAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("delAccountManagerAction");
		String cateName = request.getParameter("cateName");
		String accountName = request.getParameter("accountName");
		//通过cateName来获取ta_id
		Ta_category map = ta_Account_cateNameService.queryforta_idBycateName(cateName);
		String ta_id = map.getTa_id();
		Ta_App_relation ta_App_relation = new Ta_App_relation();
		ta_App_relation.setAccountName(accountName);
		ta_App_relation.setTa_id(ta_id);
		//通过accountName和ta_id来查询accountName是否已开通业务，0代表没有，其他代表已经开通业务了
		List<Ta_App_relation> list = ta_Account_cateNameService
				.queryForCheckAccountName(ta_App_relation);
		Ta_App_relation ins = list.get(0);
		int i = Integer.parseInt(ins.getAccountName());
		//删除帐号就是把delflag的值变成2
		String delflag = "2";
		Ta_Account_cateName ta_Account_cateName = new Ta_Account_cateName();
		ta_Account_cateName.setDelflag(delflag);
		ta_Account_cateName.setAccountName(accountName);
		//删除的提示信息
		String delMsg = null;
		if (i == 0) {
			try {
				boolean tag = ta_Account_cateNameService.del(cateName, ta_Account_cateName);
				if(tag){
					delMsg = "数据删除成功！";
					log.debug("数据删除成功！");
				}else{
					delMsg = "数据删除失败！";
					log.debug("数据删除失败！");
				}
				
			} catch (Exception e1) {
				delMsg = "网络故障！";
				log.error("数据删除失败！");
			}
		} else {
			delMsg = "该帐号已经开通业务，无法删除！";
		}
		String json = ClassToJson.object2json(delMsg);
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
	 * 修改的处理方法
	 * 
	 * @param request
	 * @param response
	 * @param user
	 *            对应的javabean
	 * @return
	 */
	@RequestMapping(value = "/updateAction", method = RequestMethod.POST)
	public String updateAccountManagerAction(HttpServletRequest request,
			HttpServletResponse response,
			Ta_Account_cateName ta_Account_cateName,
			Ta_ExtendProp_cateName ta_ExtendProp_cateName) {
		log.debug("updateAccountManagerAction");
		String cateName = request.getParameter("cateName");
		String orgId = request.getParameter("orgId");
		/*String accountPwd1 = request.getParameter("accountPwd");
		String accountPwd2 = Md5Util.getMD5String(accountPwd1);
		String accountPwd = Md5Util.getMD5String(accountPwd2);*/
		//String accountPwd = request.getParameter("accountPwd");
		request.setAttribute("orgId", orgId);
		request.setAttribute("cateName", cateName);
		//request.setAttribute("accountPwd", accountPwd);
		int id = new Integer(request.getParameter("Id"));
		ta_Account_cateName.setId(id);
		//ta_Account_cateName.setAccountPwd(accountPwd);
		ta_ExtendProp_cateName.setId(id);
		//添加时的提示信息
		String PromptMsg = null;
		try {
			ta_Account_cateNameService.update(ta_Account_cateName,
					ta_ExtendProp_cateName, cateName);
			PromptMsg = "UpdateMsg";
			log.debug("数据修改成功！");
		} catch (Exception e) {
			PromptMsg = "failMsg";
			log.error("数据修改失败！");
		}
		//修改成功，跳转到list.do的Controller
		return "redirect:/accountManager/accountList.do?cateName=" + cateName+"&PromptMsg="+PromptMsg;
	}
	
	
	/**
	 * 通过账号查询开通业务个数
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/queryAppNumByAccountName")
	public void queryAppNumByAccountName(HttpServletRequest request,HttpServletResponse response){
		String accountName = request.getParameter("accountName");
		log.debug("queryAppNumByAccountName");
		int appNum = 0;
		Map<String, String> map = new HashMap<String,String>();
		map.put("accountName", accountName);
		appNum = ta_Account_cateNameService.queryAppNumByAccountName(map);
		//log.info("queryAppNumByAccountName accountName is:"+accountName+".   appNum is:"+appNum);
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(appNum);
			pw.flush();
			pw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	} 
	
	
}
