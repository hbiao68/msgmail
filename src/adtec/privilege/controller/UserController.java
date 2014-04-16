package adtec.privilege.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import adtec.accountManager.controller.Md5Util;
import adtec.init.CachFactory;
import adtec.privilege.model.Page;
import adtec.privilege.model.User;
import adtec.privilege.model.UserPrivilege;
import adtec.privilege.model.UserRole;
import adtec.privilege.service.UserPrivilegeService;
import adtec.privilege.service.UserRoleService;
import adtec.privilege.service.UserService;

/**
 * 用户的controller类，用户出来servlet请求
 * @author maojd
 * @date 14:30 2014/2/20
 */
@Controller
@RequestMapping("/user") 
public class UserController {
	

	private UserService userService;
	private UserPrivilegeService userPrivilegeService;
	private UserRoleService userRoleService;
	
	private Logger log = Logger.getLogger(UserController.class);
	
	//userMap是在项目启动完成查询出来的所有 用户数据。全部通过  userid   user的形式保存到userMap中
	private Map<String, Object> userMap = CachFactory.getInstance().getMapByKey("userMap");
	
	public Map<String, Object> getUserMap() {
		if(userMap == null){
			userMap = CachFactory.getInstance().getMapByKey("userMap");
		}
		return userMap;
	}
	public void setUserMap(Map<String, Object> userMap) {
		this.userMap = userMap;
	}
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
	
	public UserRoleService getUserRoleService() {
		return userRoleService;
	}
	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}
	/**
	 * 查询所有用户
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryAllUserAction")
	public void queryAllUserAction(User user,HttpServletRequest request,HttpServletResponse response){
		//System.out.println(page2.getSort());
		//map.put("userList", getUserMap());
		//log.debug("queryAllUser UserSize is:" + list.size());
		//封装一份分页对象
	/*	String pageNow = request.getParameter("page"); //当前页
		String pageSize = request.getParameter("rows");//页数
		String order = request.getParameter("order");//	asc升序   desc降序
		String sort = request.getParameter("sort");//username userid
		*/
		//Page page = new Page(new Integer(pageNow), new Integer(pageSize));
		//Page page = new Page(new Integer(pageNow), new Integer(pageSize), order, sort);
		
		//分页查询数据
		List<User> userList = new ArrayList<User>();
		/*User user = new User();
		user.setPage(page);*/
		try {
			userList = userService.queryAllUser(user);//分页查询
		} catch (Exception e) {
			log.error("分页查询所有用户出错");
		}
		
		
		int count = 0;
		try {
			count = userService.queryUserCount(user);
		} catch (Exception e) {
			log.error("查询用户数量出错");
		}

		Map<String, Object> userEasyUIMap = new HashMap<String,Object>();
		userEasyUIMap.put("rows", userList);//easyUI需要用的rows
		userEasyUIMap.put("total", count); //easyUI需要用的总数
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			// pw.print(jsonData);
			//pw.write(JSONArray.fromObject(userMap).toString()); //输出的全部是字符串  
			pw.write(JSONObject.fromObject(userEasyUIMap).toString());//输入的是json key Object的字符串
			pw.flush();
		} catch (IOException e) {
			//e.printStackTrace();
			log.error("输出json出错");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * 跳转到用户显示页面
	 * @return
	 */
	@RequestMapping(value = "/queryAllUser",method={RequestMethod.GET,RequestMethod.POST})
	public String queryAllUser(){
	
		return "privilege/user/userList";
	}
	
	/**
	 * 添加用户跳转
	 * @return 跳转到添加页面
	 */
	@RequestMapping(value = "/insertUser")
	public String insertUser(){
		return "privilege/user/userInsert";
	}
	
	/**
	 * 添加用户实现
	 * @param user 要添加的用户实体
	 * @return	返回用户显示页面
	 */
	@RequestMapping(value = "/insertUserAction" ,method = {RequestMethod.POST,RequestMethod.GET})
	public void insertUserAction(User user,HttpServletResponse response){
		String uuid = UUID.randomUUID().toString().replace("-", "");
		user.setUserid(uuid);
		user.setPassword(Md5Util.getMD5String(user.getPassword()));
		String infoMsg = "";
		try {
			boolean b = userService.insertUser(user);
			if(b){
				getUserMap().put(user.getUserid(), user);//userMap也添加进去
				infoMsg = "addSuc";
				log.debug("userInsertAction ok");
			}else{
				infoMsg = "addError";
				log.debug("userInsertAction failed");
			}
		} catch (Exception e) {
			infoMsg = "addError";
			log.error("userInsertAction error");
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(infoMsg);
			pw.flush();
		} catch (IOException e) {
			log.error("pw.write(jsonData.toString())  error");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		
		//return "redirect:/user/queryAllUser.do";
	} 
	
	/**
	 * 跳转到用户修改页面
	 * @param user 页面根据属性自动封装的user
	 * @return
	 */
	@RequestMapping(value = "/updateUser")
	public String updateUser(User user,Map<String, User> map){
		try {
			//User userRes = userService.queryUserByUserid(user.getUserid());//只用到userid属性，其他属性都是空
			User userRes = (User) getUserMap().get(user.getUserid());//从存放user对象的userMap容器中直接获取。不再查询数据库
			map.put("user", userRes);
		} catch (Exception e) {
			log.error("updateUser error");
		}
		return "privilege/user/userUpdate";
	}
	
	/**
	 * 用户修改的实现
	 * @param user 传入修改的用户实体
	 * 
	 */
	@RequestMapping(value = "/updateUserAction",method = {RequestMethod.POST,RequestMethod.GET})
	public void updateUserAction(User user,HttpServletResponse response){
		String infoMsg = "";
		try {
			boolean b = userService.updateUser(user);
			if(b){
				infoMsg = "updateSuc";
				getUserMap().remove(user.getUserid());	//把 存放user对象的userMap容器中也修改一下
				getUserMap().put(user.getUserid(), user);
			}else{
				infoMsg = "updateError";
			}
			
			log.debug("updateUserAction " + b);
		} catch (Exception e) {
			log.error("updateUserAction error");
			infoMsg = "updateError";
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(infoMsg);
			pw.flush();
		} catch (IOException e) {
			log.error("pw.write(jsonData.toString())  error");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		
		//return "redirect:/user/queryAllUser.do";
	}
	
	/**
	 * 删除用户
	 * @param user 要删除的实体（主要通过userid删除
	 * @return 重定向到用户显示页面
	 */
	@RequestMapping(value="/deleteUser",method = {RequestMethod.POST,RequestMethod.GET})
	public String deleteUser(String userids,User user){
		System.out.println(userids);
		try {
			boolean b = userService.deleteUser(user);
			if(b){
				getUserMap().remove(user.getUserid());//把 存放user对象的userMap容器中也删除一下
			}
		} catch (Exception e) {
			log.error("deleteUser error");
		}
		return "redirect:/user/queryAllUser.do";
	}
	
	/**
	 * 通过id批量删除用户 
	 * @param request获取页面传递的参数
	 */
	@RequestMapping(value="/deleteUsersById",method = {RequestMethod.POST,RequestMethod.GET})
	public void deleteUsersById(HttpServletRequest request,HttpServletResponse response){
		String userids = request.getParameter("userids");
		String infoMsg = "";
		try {
			String[] idsArray = userids.split(",");
			for(int i = 0; i < idsArray.length; i++ ){
				int userPrivlgCount = userPrivilegeService.queryCountByObj(new UserPrivilege(null, idsArray[i], null, null, null));
				if(userPrivlgCount>0){//一旦查询出来，有用户在用该权限，则返回
					infoMsg = "isUsed";
					break;
				}
				
				int userRoleCount = userRoleService.queryCountByObj(new UserRole(null, idsArray[i], null, null, null));
				if(userRoleCount > 0){
					infoMsg = "isUsed";
					break;
				}
				
			}
			//如果用户没有开通什么权限或者角色
			if(!"isUsed".equals(infoMsg)){
				boolean b = userService.deleteUsersById(userids);
				if(b){
					infoMsg = "delSuc";//删除成功
					for(int i = 0; i < idsArray.length; i++ ){
						getUserMap().remove(idsArray[i]);
					}
				}else{
					infoMsg = "delError";
				}
			}
			
			
		} catch (Exception e) {
			log.error("deleteUsersById error 批量删除出错");
			infoMsg = "delError";
		}
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(infoMsg);
			pw.flush();
		} catch (IOException e) {
			log.error("pw.write(jsonData.toString())  error");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		//return "redirect:/user/queryAllUser.do";
		
	}
	
	/**
	 * 验证用户是否存在
	 * @param user
	 */
	@RequestMapping(value="/userIfExit",method={RequestMethod.POST,RequestMethod.GET})
	public void userIfExit(User user,HttpServletResponse response){
		String infoMsg = "";
		try {
			List<User> userList = userService.queryUserByObj(user);
			if(userList.size()>0){
				infoMsg = "isUsed";
			}
		} catch (Exception e) {
			log.error("userIfExit error验证用户是否存在出错");
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(infoMsg);
			pw.flush();
		} catch (IOException e) {
			log.error("pw.write(jsonData.toString())  error");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	
	/**
	 * 修改的时候验证用户是否存在
	 * @param user
	 */
	@RequestMapping(value="/updateUserIfExit",method={RequestMethod.POST,RequestMethod.GET})
	public void updateUserIfExit(User user,HttpServletResponse response){
		String infoMsg = "";
		try {
			List<User> userList = userService.queryUserByObj(user);
			if(userList.size()>1){//加入只有一条的话，则为本身。
				infoMsg = "isUsed";
			}
		} catch (Exception e) {
			log.error("userIfExit error验证用户是否存在出错");
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(infoMsg);
			pw.flush();
		} catch (IOException e) {
			log.error("pw.write(jsonData.toString())  error");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	/**
	 * 通过对象查询数量
	 * @param user
	 * @param response
	 */
	@RequestMapping(value="/queryCountByObj",method={RequestMethod.POST,RequestMethod.GET})
	public void queryCountByObj(User user,HttpServletResponse response){
		int infoMsg = 0;
		try {
			List<User> userList = userService.queryUserByObj(user);
			if(userList.size()>0){//加入只有一条的话，则为本身。
				infoMsg = userList.size();
			}
		} catch (Exception e) {
			log.error("userIfExit error验证用户是否存在出错");
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(infoMsg+"");
			pw.flush();
		} catch (IOException e) {
			log.error("pw.write(jsonData.toString())  error");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
}

