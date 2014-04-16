package adtec.account.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import adtec.account.model.Account;
import adtec.accountManager.controller.Md5Util;
import adtec.appManager.controller.ClassToJson;
import adtec.privilege.model.Resource;
import adtec.util.switchInterface.AccOrgSetXmlService;

/**
 * @FileName: AccountController
 * 
 * @FileType: Class
 * 
 * @Date: 2014年3月7日  2014年3月7日 10:07:24
 * 
 * @Author: lj
 * 
 * @Description: 帐号管理Controller（为发送消息使用）
 * 
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {

/*	AccountService accountService;
	
	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}*/
	
	private AccOrgSetXmlService accorgsetxmlservice;
	
	public AccOrgSetXmlService getAccorgsetxmlservice() {
		return accorgsetxmlservice;
	}


	public void setAccorgsetxmlservice(AccOrgSetXmlService accorgsetxmlservice) {
		this.accorgsetxmlservice = accorgsetxmlservice;
	}

	Logger log = Logger.getLogger(AccountController.class);

	
	
	/**
	 * 添加业务的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/accInsert")
	public ModelAndView showaccountAdd() {
		log.debug("showaccountAdd");
		ModelAndView mv = new ModelAndView();
		//跳转到添加展示的jsp页面
		mv.setViewName("/account/accInsert");
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
	public String addAccountAction(Account account) {
		log.debug("addAccountAction");
		//跳转提示
		String PromptMsg = null;
		try {
			/*boolean tag = accountService.getaccService().addAccount(account);*/
			boolean tag = accorgsetxmlservice.getSwtService().addAccount(account);
			if(tag){
				PromptMsg = "InsertMsg";
				log.debug("帐号添加成功！");
			}
		} catch (Exception e) {
			PromptMsg = "failMsg";
			log.error("帐号添加失败！");
		}
		//跳转到查询的Controller
		return "redirect:/account/accList.do?PromptMsg="+PromptMsg;
	}
	
	 /**
	  * 查询的展示界面
	  * @param request
	  * @param response
	  * @return
	  */
	@RequestMapping(value = "/accList")
	public ModelAndView showaccountList(HttpServletRequest request,
			HttpServletResponse response,Account account,Resource res) {
		String resid = "";
		if(res!=null && res.getResid() != null && res.getResid() != ""){
			resid = res.getResid().trim();
			request.getSession().setAttribute("resid", resid);
		}//如果传入了resid 资源id就 王session中set一下，覆盖一下以前的。否则不做操作（资源id还是以前的）。
		
		log.debug("showaccountList");
		ModelAndView mv = new ModelAndView();
		try {
			//添加或修改时的提示信息
			String PromptMsg = request.getParameter("PromptMsg");
			/*account = accountService.getaccService().queryAccountByAccount(account);*/
			account = accorgsetxmlservice.getSwtService().queryAccountByAccount(account);
			request.setAttribute("account", account);
			request.setAttribute("PromptMsg", PromptMsg);
		} catch (Exception e) {
			log.error("没有查询到数据！   accountList error");
			e.printStackTrace();
		}
		//跳转到list查询的jsp页面
		mv.setViewName("/account/accList");
		return mv;
	}
	
	
		/**
		 *  * 修改展示界面
		 * 
		 * @return
		 */
		@RequestMapping(value = "/accUpdate")
		public ModelAndView showaccountupdate(HttpServletRequest request,
				HttpServletResponse response) {
			log.debug("showaccountupdate");
			ModelAndView mv = new ModelAndView();
			try {
				//修改和添加后跳转的提示
				String PromptPwdMsg = request.getParameter("PromptPwdMsg");
				request.setAttribute("PromptPwdMsg", PromptPwdMsg);
				String username = request.getParameter("username");
//				Account account = accountService.getaccService().findAccountByUsername(username);
				Account account = accorgsetxmlservice.getSwtService().findAccountByUsername(username);
				request.setAttribute("account", account);
			} catch (Exception e) {
				log.error("通过username没有查询到数据！   showaccountupdate error");
				e.printStackTrace();
			}
			//跳转到修改的jsp页面
			mv.setViewName("/account/accUpdate");
			return mv;
		}
		
		/**
		 * 修改的处理方法
		 * 
		 * @param request
		 * @param response
		 *            
		 * @return
		 */
		@RequestMapping(value = "/updateAction", method = RequestMethod.POST)
		public String updateaccountAction(HttpServletRequest request,
				HttpServletResponse response,Account account) {
			log.debug("updateaccountAction");
			String PromptMsg = null;
			try {
//				boolean tag = accountService.getaccService().updateAccountByAccount(account);
				boolean tag = accorgsetxmlservice.getSwtService().updateAccountByAccount(account);
				if(tag){
					PromptMsg = "UpdateMsg";
					log.debug("帐号修改成功！");
				}
			} catch (Exception e) {
				PromptMsg = "failMsg";
				log.error("帐号修改失败！");
			}
			
			//跳转到查询的Controller
			return "redirect:/account/accList.do?PromptMsg="+PromptMsg;
		}
	
		/**
		 *  * 单一实例查询界面
		 * 
		 * @return
		 */
		@RequestMapping(value = "/accFindById")
		public ModelAndView showaccountFindById(HttpServletRequest request,
				HttpServletResponse response) {
			log.debug("showaccountFindById");
			ModelAndView mv = new ModelAndView();
			try {
				String username = request.getParameter("username");
//				Account account = accountService.getaccService().findAccountByUsername(username);
				Account account = accorgsetxmlservice.getSwtService().findAccountByUsername(username);
				request.setAttribute("account", account);
			} catch (Exception e) {
				log.error("通过username没有查询到数据！   showaccountFindById error");
				e.printStackTrace();
			}
			//跳转到修改的jsp页面
			mv.setViewName("/account/accFindById");
			return mv;
		}
		
		/**
		 * 删除整条数据
		 * 
		 * @param user
		 * @return
		 */
		@RequestMapping(value = "/delaccAction", method = RequestMethod.POST)
		public void delAction(HttpServletRequest request,
				HttpServletResponse response) {
			log.debug("delAction");
			try {
				String username = request.getParameter("username");
//				boolean tag = accountService.getaccService().deleteAccountByAccount(username);
				boolean tag = accorgsetxmlservice.getSwtService().deleteAccountByAccount(username);
				
				String json = ClassToJson.object2json(tag);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.write(json);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				log.error("响应数据流异常");
				e.printStackTrace();
			} catch (Exception e) {
				log.error("数据删除失败！");
				e.printStackTrace();
			}
			
		}
		
	/**
	 *   校验帐号是否重复
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/checkusername", method = RequestMethod.POST)
	public void checkusername(HttpServletRequest request,
			HttpServletResponse response){
		log.debug("checkusername");
		try {
			String username = request.getParameter("username");
			String usernameTrim = username.trim();
//			int count = accountService.getaccService().checkusernameCountByusername(usernameTrim);
			int count = accorgsetxmlservice.getSwtService().checkusernameCountByusername(usernameTrim);
			String json = ClassToJson.object2json(count);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error("checkusername查询出错！");
			e.printStackTrace();
		}
		
				
	}
	
	/**
	 *  * 修改密码展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updatePwd")
	public ModelAndView showaccountPwdupdate(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("showaccountPwdupdate");
		ModelAndView mv = new ModelAndView();
		String username = request.getParameter("usernamePwd");
		request.setAttribute("username", username);
		//跳转到修改的jsp页面
		mv.setViewName("/account/accUpdatePwd");
		return mv;
	}
	/**
	 * 校验原有密码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/checkCurrentEncryptedPassword", method = RequestMethod.POST)
	public void checkCurrentEncryptedPassword(HttpServletRequest request,
			HttpServletResponse response){
		try {
			log.debug("checkCurrentEncryptedPassword");
			String username = request.getParameter("username");
			//获取原有密码
			String CurrentEncryptedPassword = request.getParameter("CurrentEncryptedPassword");
			//将原有密码MD5加密
			String CurrentEncryptedPasswordMd5 =  Md5Util.getMD5String(CurrentEncryptedPassword);
			System.out.println("fdsa"+CurrentEncryptedPasswordMd5);
			Account account = new Account();
			account.setUsername(username);
			account.setEncryptedpassword(CurrentEncryptedPasswordMd5);
//			int count = accountService.getaccService().checkEncryptedPassword(account); //count等于1代表密码正确，count等于0代表密码错误
			int count = accorgsetxmlservice.getSwtService().checkEncryptedPassword(account); //count等于1代表密码正确，count等于0代表密码错误
			String json = ClassToJson.object2json(count);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error("checkCurrentEncryptedPassword查询出错！");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 修改密码的处理方法
	 * 
	 * @param request
	 * @param response
	 *            
	 * @return
	 */
	@RequestMapping(value = "/updatePwdAction", method = RequestMethod.POST)
	public String updatePwdAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("updatePwdAction");
		//修改密码的提示
		String PromptPwdMsg = null;
		String username = request.getParameter("username");
		try {
			String NewEncryptedPassword = request.getParameter("NewEncryptedPassword");
			String NewEncryptedPasswordMd5 = Md5Util.getMD5String(NewEncryptedPassword);
			Account account = new Account();	
			account.setUsername(username);
			account.setEncryptedpassword(NewEncryptedPasswordMd5);
//			boolean tag = accountService.getaccService().updateAccountPwdByAccount(account);
			boolean tag = accorgsetxmlservice.getSwtService().updateAccountPwdByAccount(account);
			if(tag){
				PromptPwdMsg = "UpdateMsg";
				log.debug("帐号修改成功！");
			}
		} catch (Exception e) {
			PromptPwdMsg = "failMsg";
			log.error("帐号修改失败！");
		}
		
		//跳转到查询的Controller
		return "redirect:/account/accUpdate.do?username="+username+"&PromptPwdMsg="+PromptPwdMsg;
												
	}
	
}
