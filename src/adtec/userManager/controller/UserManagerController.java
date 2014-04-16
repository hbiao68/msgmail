package adtec.userManager.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import adtec.appManager.model.PageModel;
import adtec.categoryManager.model.Ta_category;
import adtec.init.CachFactory;
import adtec.privilege.model.Page;
import adtec.privilege.model.Resource;
import adtec.privilege.model.User;
import adtec.privilege.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserManagerController {

	Logger log = Logger.getLogger(UserManagerController.class);
	//UserManagerService userService;
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	/**
	 * 用户添加展示页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public ModelAndView showUserInsert(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("showUserInsert");
		ModelAndView mv = new ModelAndView();
		// 跳转到添加页面
		mv.setViewName("/userManager/userInsert");
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
	public String addRegisterAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("AddRegisterAction");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		//将密码进行MD5加密
		String userPasswordMd5 = Md5Util.getMD5String(userPassword);
//		User user = new User();
//		user.setUserName(userName);
//		user.setUserPassword(userPasswordMd5);
		String uuid = UUID.randomUUID().toString().replace("-", "");
		try {
			//userService.add(user);
			userService.insertUser(new User(uuid, userName, userPasswordMd5));
		} catch (Exception e) {
			log.error("添加用户失败");
		}
		//添加后的跳转提示
		String PromptMsg = "InsertMsg";
		
		//跳转到登陆页面
		return "redirect:/user/list.do?PromptMsg="+PromptMsg;
	}

	
	/**
	 * 校验帐号是否重复
	 * 
	 * @return
	 */
	@RequestMapping(value = "/checkUserName")
	public void checkUserName(HttpServletRequest request,
			HttpServletResponse response) {
		
		String userName = request.getParameter("userName");
		
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
	 * 查询的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView showUserList(HttpServletRequest request,
			HttpServletResponse response,Resource res) {
		String resid = "";
		if(res!=null && res.getResid() != null && res.getResid() != ""){
			resid = res.getResid().trim();
			request.getSession().setAttribute("resid", resid);
		}//如果传入了resid 资源id就 王session中set一下，覆盖一下以前的。否则不做操作（资源id还是以前的）。
		

	
		
		
		log.debug("showcategoryManagerList");
		ModelAndView mv = new ModelAndView();
		// 获取条件查询的值
		String userName = request.getParameter("userName");
		
		//修改和添加后跳转的提示
		String PromptMsg = request.getParameter("PromptMsg");
		request.setAttribute("PromptMsg", PromptMsg);
		
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
				pageNow = Integer.parseInt(request.getParameter("pageJump"));
			}

		} else {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}

		if (userName == null || userName.equals("")) {
			userName = "";

		}
		
		// 将前端传递来的查询条件去掉空格
		String userNameTrim = userName.trim();
		
//		User user = new User();
//		user.setUserName(userNameTrim);

		PageModel pageModel = new PageModel();
		// 每页显示的记录数
		pageModel.setPageNow(pageNow);
		int pageSize = pageModel.getPageSize();
		int start = pageModel.getStart();
		/*int pageSize = 3;
		int start = (pageNow - 1) * pageSize;*/

		// 获取记录总数
		//int count = userService.queryCount(user);

		User user = new User();
		user.setPage(pageNow);
		user.setRows(pageSize);
		user.setStart(start);
		user.setUsername(userNameTrim);
		
		int count = 0;
		try {
			count = userService.queryUserCount(user);
		} catch (Exception e2) {
			log.error("queryUserCount error");
		}//查询总数
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

		request.setAttribute("userName", userNameTrim);

//		user.setStart(start);
//		user.setPageSize(pageSize);

		// 调用查询方法，查询出所要数据
		//List<User> rows = userService.queryUserListByUser(user);
		//Page page = new Page(pageNow, pageSize, null, null);//改成继承的方式了
		List<User> rows = new ArrayList<User>();
		try {
			rows = userService.queryAllUser(user);
		} catch (Exception e1) {
			log.error("queryAllUser error");
		}

		String json = ClassToJson.object2json(rows);
		request.setAttribute("json", json);
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("user", rows);
		// 跳转到cateList.jsp页面
		mv.setViewName("userManager/userList");
		return mv;
	}
	
	
	
	/**
	 * 删除整条数据
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/deluserAction", method = RequestMethod.POST)
	public void delUserAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("deluserAction");
		try {
//			int uid = Integer.parseInt(request.getParameter("uid"));
			String uid = request.getParameter("uid");
			//通过uid获取用户名称（userName）
			//User user = userService.findUserById(uid);
			User user = userService.queryUserByUserid(uid);
			String userName = user.getUsername();
//			String userName = user.getUserName();
			//从session中获取当前登陆者的名称（loginUserName）
			String loginUserName = (String)request.getSession().getAttribute("username");
			String msg = null;
			//如果当前删除的名称和正在登陆的名称相同，那么不允许删除该用户
			if(userName.equals(loginUserName) || userName.equals("admin")){
			}
			else{
				//userService.deleteUserById(uid);
				boolean b = userService.deleteUser(user);
				if(b)
				msg = "数据删除成功！";
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
		} catch (Exception ex) {
			log.error("删除数据失败");
		}
	}
	
	
	/**
	 * 显示用户详细信息
	 * @return
	 */
	@RequestMapping(value = "/findById")
	public ModelAndView showUserById(HttpServletRequest request,
			HttpServletResponse response, Ta_category ta_category) {
		log.debug("showUserManagerfindbyid");
		ModelAndView mv = new ModelAndView();
		try {
//			int uid = Integer.parseInt(request.getParameter("uid"));
//			User user = userService.findUserById(uid);
			String uid = request.getParameter("uid");
			request.setAttribute("user", uid);
			mv.setViewName("userManager/userFindById");
		} catch (Exception ex) {
			log.error("根据id查询用户出现异常");
		}
		return mv;
	}
	
	
	/**
	 * 修改展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/update")
	public ModelAndView showUserupdate(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("showUserupdate");
		ModelAndView mv = new ModelAndView();
		
		try {
			//修改和添加后跳转的提示
			String PromptPwdMsg = request.getParameter("PromptPwdMsg");
			request.setAttribute("PromptPwdMsg", PromptPwdMsg);
			//通过ID跳转到修改页面
//			int uid = Integer.parseInt(request.getParameter("uid"));
//			User user = userService.findUserById(uid);
			String uid = request.getParameter("uid");
			User user =  userService.queryUserByUserid(uid);
			request.setAttribute("user", user);
			mv.setViewName("userManager/userUpdate");
		} catch (Exception ex) {
			log.error("查询用户失败");
		}

		return mv;
	}
	
	/**
	 * 修改的处理方法
	 * @param request
	 * @param response
	 * @param user
	 *            对应的javabean
	 * @return
	 */
	@RequestMapping(value = "/updateAction", method = RequestMethod.POST)
	public String updateUserManagerAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("updateCategoryManagerAction");
//		int uid = Integer.parseInt(request.getParameter("uid"));
		String uid = request.getParameter("uid");
		String userName = request.getParameter("userName");
//		User user = new User();
//		user.setUid(uid);
//		user.setUserName(userName);
		//使用事务管理修改的用户记录
		try {
			//userService.updateUserByUser(user);
			boolean b = userService.updateUser(new User(uid, userName, null));
			if(b){
				Map<String, Object> userMap = CachFactory.getInstance().getMapByKey("userMap");
				userMap.remove(uid);
				User userNew = userService.queryUserByUserid(uid);
				userMap.put(userNew.getUserid(), userNew);
			}
		} catch (Exception e) {
			log.error("修改用户失败");
		}
		//修改后的提示
		String PromptMsg = "UpdateMsg";
		return "redirect:/user/list.do?PromptMsg="+PromptMsg;
	}
	
	
	/**
	 * 修改密码展示页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updatePwd")
	public ModelAndView showUserupdatePwd(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("showUserupdatePwd");
		ModelAndView mv = new ModelAndView();
		//修改和添加后跳转的提示
		String PromptPwdMsg = request.getParameter("PromptPwdMsg");
		request.setAttribute("PromptPwdMsg", PromptPwdMsg);
		//通过ID跳转到修改密码页面
		String uid = request.getParameter("uid");
		request.setAttribute("userid", uid);
		/*try {
			int uid = Integer.parseInt(request.getParameter("uid"));
			request.setAttribute("uid", uid);
		} catch (NumberFormatException e) {
			log.error("接收传递过来的ID不为数字，转换失败");
		}*/
		// 跳转到添加页面
		mv.setViewName("/userManager/userUpdatePwd");
		return mv;
	}
	
	/**
	 * 修改密码的处理方法
	 * 
	 * @param request
	 * @param response
	 * @param user
	 *            对应的javabean
	 * @return
	 */
	@RequestMapping(value = "/updatePwdAction", method = RequestMethod.POST)
	public String updatePwdUserManagerAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("updatePwdUserManagerAction");
//		int uid = Integer.parseInt(request.getParameter("uid"));
		String uid = request.getParameter("uid");
		request.setAttribute("uid", uid);
		//获取原有密码
		String CurrentUserPassword = request.getParameter("CurrentUserPassword");
		//将原有密码MD5加密
		String CurrentUserPasswordMd5 =  Md5Util.getMD5String(CurrentUserPassword);
		
		//获取修改的新密码
		String NewUserPassword = request.getParameter("NewUserPassword");
		//将修改后的密码进行MD5加密
		String NewUserPasswordMd5 = Md5Util.getMD5String(NewUserPassword);
		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("uid", uid);
//		map.put("userPassword", CurrentUserPasswordMd5);
	
		//校验输入的原密码是否正确
//		int count = userService.queryPwd(map);
		int count = 0;
		try {
			count = userService.queryUserByObj(new User(uid, null, CurrentUserPasswordMd5)).size();
		} catch (Exception e) {
			log.error("queryUserByObj error验证用户密码出错");
		}
		
		//修改密码的提示
		String PromptPwdMsg = "updatePwdMsg";
		
		//等于0，代表原始密码输入错误，跳回到修改密码页面
		if(count == 0){
			return "redirect:/user/updatePwd.do?uid="+uid+"&PromptPwdMsg="+PromptPwdMsg;
		}
		//不等于0，代表原始密码输入正确，修改密码
		else{
//			User user = new User();
//			user.setUid(uid);
//			user.setUserPassword(NewUserPasswordMd5);
//			userService.UpdatePwd(user);
			try {
				boolean b = userService.updateUser(new User(uid, null, NewUserPasswordMd5));
				if(b){
					Map<String, Object> userMap = CachFactory.getInstance().getMapByKey("userMap");
					userMap.remove(uid);
					User userNew = userService.queryUserByUserid(uid);
					userMap.put(userNew.getUserid(), userNew);
				}
			} catch (Exception e) {
				log.error("updateUser error修改密码出错");
			}
			//User user = new User(uid, null, NewUserPasswordMd5, null);
			//修改密码成功，跳转到登陆页面
			request.getSession().removeAttribute("user");
			return "redirect:/user/update.do?uid="+uid+"&PromptPwdMsg="+PromptPwdMsg;
		}
		
	}
	
	
	/**
	 * 校验原有密码是否正确
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/checkCurrentUserName", method = RequestMethod.POST)
	public void checkCurrentUserName(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("checkCurrentUserName");
		try {
//			int uid = Integer.parseInt(request.getParameter("uid"));
			String uid = request.getParameter("uid");
			//获取原有密码
			String CurrentUserPassword = request.getParameter("CurrentUserPassword");
			//将原有密码MD5加密
			String CurrentUserPasswordMd5 =  Md5Util.getMD5String(CurrentUserPassword);
//			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("uid", uid);
//			map.put("userPassword", CurrentUserPasswordMd5);
		
			//校验输入的原密码是否正确
//			int count = userService.queryPwd(map);
			int count = userService.queryUserByObj(new User(uid, null, CurrentUserPasswordMd5)).size();
			String json = ClassToJson.object2json(count);
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(json);
			} catch (IOException e) {
				log.error("response.getWriter().write(json) error输出信息出错");
			}
		} catch (Exception ex) {
			log.error("checkCurrentUserName error 校验原有密码是否正确出错");
		}

	}
	
}
