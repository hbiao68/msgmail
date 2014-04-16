package adtec.userManager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import adtec.accountManager.controller.Md5Util;
import adtec.appManager.controller.ClassToJson;
import adtec.categoryManager.model.Ta_category;
import adtec.init.CachFactory;
import adtec.init.ProjectProperty;
import adtec.privilege.model.Privilege;
import adtec.privilege.model.User;
import adtec.privilege.service.UserPrivilegeService;
import adtec.privilege.service.UserService;


@Controller
@RequestMapping(value = "/login")
public class LoginController {

	//UserManagerService userService;
	private UserService userService;
	private UserPrivilegeService userPrivilegeService;
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserPrivilegeService getUserPrivilegeService() {
		return userPrivilegeService;
	}

	public void setUserPrivilegeService(UserPrivilegeService userPrivilegeService) {
		this.userPrivilegeService = userPrivilegeService;
	}

	Logger log = Logger.getLogger(LoginController.class);

	/**
	 * 登陆校验
	 * 
	 * @return
	 */
	@RequestMapping(value = "/check")
	public void CheckLogin(HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		String userPasswordMd5 = Md5Util.getMD5String(password);
//		User user = new User();
//		user.setUserName(username);
//		user.setUserPassword(userPasswordMd5);
		//int count = userService.loginCheck(user);
		User user = new User(null, username, userPasswordMd5);
		List<User> list = new ArrayList<User>();
		try {
			list = userService.queryUserByObj(user);
			user = list.get(0);
		} catch (Exception e) {
			log.error("CheckLogin error 验证用户登陆出错");
		}
		int count = list.size();
		
		
		
		Map<String, Privilege> oneUserAllPrivlgMap = new HashMap<String,Privilege>();
		//资源+操作类型 作为key   权限对象value
		//9524c8320e714eca909607bf7d48b268
		try {
			oneUserAllPrivlgMap = userPrivilegeService.queryUserAllPrivlg(user);//resid+actionTypeId key   所有的权限实体是值
			if(!CachFactory.getInstance().isExist("userAllPrivlgMap")){
				CachFactory.getInstance().createCach("userAllPrivlgMap");//全局的 所用用户的所有权限   userid key    该用户的所有权限map是值
			};
			Map<String, Object> userAllPrivlgMap = CachFactory.getInstance().getMapByKey("userAllPrivlgMap");
			userAllPrivlgMap.put(user.getUserid(), oneUserAllPrivlgMap);
			
		} catch (Exception e) {
			log.error("queryUserAllPrivlg error 查询用户所有的权限");
		}
		boolean wholePrivlg = false;//全局的是否开启权限管理。默认开启.  userPrivlg || wholePrivlg   全局为false,对用户本来的权限不影响。全局为true,则页面永远为true
		String wholePrivlgStr = "";
		wholePrivlgStr = ProjectProperty.getInstance().get("project.privilegeManager");
		if(wholePrivlgStr != null){
			if("false".equals(wholePrivlgStr)){
				wholePrivlg = true;//全局为true,  userPrivlg || wholePrivlg 永远为true.则权限管理失效
			}
		}
		if(count == 1){
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("oneUserAllPrivlgMap", oneUserAllPrivlgMap);
			request.getSession().setAttribute("wholePrivlg", wholePrivlg);
			
			//左边的菜单栏使用动态的形式。从menucolumn表查询出来数据，放到session中，在main页面获取
			Map<String, Object> columnMap = CachFactory.getInstance().getMapByKey("columnMap");
			request.getSession().setAttribute("columnMap", columnMap);
			
			
			//request.getSession().setAttribute("actionType", new DictEnum.ActionType());//数据字典，里面存放操作类型。
			
		}else{
			
		}
		String json = ClassToJson.object2json(count);
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 注册帐号展示页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		log.info("showRegister");
		ModelAndView mv = new ModelAndView();
		// 跳转到添加页面
		mv.setViewName("/userManager/register");
		return mv;
	}

	/**
	 * 注册帐号处理方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/insertAction", method = RequestMethod.POST)
	public String addRegisterAction(HttpServletRequest request, HttpServletResponse response) {
		log.info("AddRegisterAction");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		// 将密码进行MD5加密
		String userPasswordMd5 = Md5Util.getMD5String(userPassword);
//		User user = new User();
//		user.setUserName(userName);
//		user.setUserPassword(userPasswordMd5);
		String uuid = UUID.randomUUID().toString().replace("-", "");
		User user = new User(uuid, userName, userPasswordMd5);
		try {
			boolean b = userService.insertUser(user);
		} catch (Exception e) {
			log.error("添加用户失败");
		}

		// 跳转到登陆页面
		return "redirect:/login.html";
	}

	/**
	 * 校验用户名是否重复,如果重复返回true，不重复返回false
	 * @return
	 */
	@RequestMapping(value = "/checkUserName")
	public void checkUserName(HttpServletRequest request, HttpServletResponse response) {

		String userName = request.getParameter("userName");

//		boolean isRepeat = userService.checkUserIsExistsByName(userName);
		List<User> list = new ArrayList<User>();
		try {
			list = userService.queryUserByObj(new User(null, userName, null));
		} catch (Exception e) {
			log.error("queryUserByObj error");
		}
		boolean isRepeat = false;//默认不存在
		if(list.size()>0){
			isRepeat = true;//存在
		}
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(new Boolean(isRepeat).toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			log.error("检验账号是否重复失败");
		}
	}

	/**
	 * 注销
	 * @return
	 */
	@RequestMapping(value = "/onLogout")
	public void onLogout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
	}

	/**
	 * 获取登陆者的名字
	 * @return
	 */
	@RequestMapping(value = "/getUserName")
	public void getUserName(HttpServletRequest request, HttpServletResponse response) {
		//User user = (User) request.getSession().getAttribute("user");
		User user = (User) request.getSession().getAttribute("user");
		String userName = "";
		if(user!=null){
			//userName = user.getUserName();
			userName = user.getUsername();
		}
		

		String json = ClassToJson.object2json(userName);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(json);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			log.error("响应数据流异常");
		}
	}

	/**
	 * 登陆者详细信息的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findUserInfo")
	public ModelAndView showFindUserInfo(HttpServletRequest request, HttpServletResponse response, Ta_category ta_category) {
		log.info("showFindUserInfo");
		ModelAndView mv = new ModelAndView();
		//User user = (User) request.getSession().getAttribute("user");
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("user", user);
		mv.setViewName("userManager/userFindById");
		return mv;
	}

}
