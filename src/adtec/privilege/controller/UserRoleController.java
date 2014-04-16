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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import adtec.init.CachFactory;
import adtec.privilege.model.Page;
import adtec.privilege.model.Role;
import adtec.privilege.model.User;
import adtec.privilege.model.UserRole;
import adtec.privilege.service.RolePrivilegeService;
import adtec.privilege.service.UserRoleService;

/**
 * 用户角色管理的controller层，处理页面的请求
 * @author maojd
 * @date 14:33 2014/2/25
 */
@Controller
@RequestMapping(value="/userRole")
public class UserRoleController {

	private UserRoleService userRoleService;
	private RolePrivilegeService rolePrivilegeService;
	private Logger log = Logger.getLogger(UserRoleController.class);
	private Map<String, Object> userRoleMap = CachFactory.getInstance().getMapByKey("userRoleMap");
	private Map<String, Object> userRoleIdsMap = CachFactory.getInstance().getMapByKey("userRoleIdsMap");
	
	public UserRoleService getUserRoleService() {
		return userRoleService;
	}
	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}
	public RolePrivilegeService getRolePrivilegeService() {
		return rolePrivilegeService;
	}
	public void setRolePrivilegeService(RolePrivilegeService rolePrivilegeService) {
		this.rolePrivilegeService = rolePrivilegeService;
	}
	public Map<String, Object> getUserRoleMap() {
		if(userRoleMap == null){
			userRoleMap = CachFactory.getInstance().getMapByKey("userRoleMap");
		}
		return userRoleMap;
	}
	public void setUserRoleMap(Map<String, Object> userRoleMap) {
		this.userRoleMap = userRoleMap;
	}
	
	public Map<String, Object> getUserRoleIdsMap() {
		if(userRoleIdsMap == null){
			userRoleIdsMap = CachFactory.getInstance().getMapByKey("userRoleIdsMap");
		}
		return userRoleIdsMap;
	}
	public void setUserRoleIdsMap(Map<String, Object> userRoleIdsMap) {
		this.userRoleIdsMap = userRoleIdsMap;
	}
	/**
	 * 查询所有用户角色 跳转
	 * @return 用户角色显示页面
	 */
	@RequestMapping(value="/queryAllUserRole")
	public String queryAllUserRole(Map<String, Object> map){
		
		/*try {
//			List<UserRole> list = userRoleService.queryAllUserRole();
//			map.put("userRoleList", list);
			map.put("userRoleList", getUserRoleMap());
		} catch (Exception e) {
			log.error("queryAllUserRole error 查询所有用户角色出错");
		}*/
		return "privilege/userRole/userRoleList";
	}
	
	/**
	 * 查询所有用户角色数据
	 * @param page 获取到分页信息
	 * @param request 获取参数
	 * @param response
	 */
	@RequestMapping(value="/queryAllUserRoleAction")
	public void queryAllUserRoleAction(UserRole userRole,User user,Role role,HttpServletRequest request,HttpServletResponse response){

		//查询所有用户角色
		List<UserRole> userRoleList = new ArrayList<UserRole>();
//		UserRole userRole = new UserRole();
//		userRole.setPage(page);
		userRole.setUser(user);
		userRole.setRole(role);
		
		try {
			userRoleList = userRoleService.queryAllUserRole(userRole);
		} catch (Exception e) {
			log.error("queryAllUserRoleAction error 查询所有用户角色出错");
		}
		
		int count = 0;
		try {
			count = userRoleService.queryUserRoleCount(userRole);
		} catch (Exception e1) {
			log.error("查询用户数量出错");
		}
		
		
		
		Map<String, Object> userRoleEasyUIMap = new HashMap<String, Object>();
		userRoleEasyUIMap.put("rows", userRoleList);
		userRoleEasyUIMap.put("total", count);
		
		//输出出去
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(JSONObject.fromObject(userRoleEasyUIMap).toString());//输入的是json key Object的字符串
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
	 * 给用户添加角色跳转
	 * @param map 将查询出来的角色数据放到map中
	 * @return 跳转到添加页面
	 */
	@RequestMapping(value="/insertUserRole",method={RequestMethod.POST,RequestMethod.GET})
	public String insertUserRole(Map<String, Object> map){
		//数据直接在页面通过ajax 分页获取
		/*
//		List<Role> roleList = new ArrayList<Role>();
		try {
//			roleList = userRoleService.queryAllRole();
//			map.put("roleList", roleList);
			Map<String, Object> userMap = CachFactory.getInstance().getMapByKey("userMap");
			
			Map<String, Object> roleMap = CachFactory.getInstance().getMapByKey("roleMap");
			
			map.put("roleList", roleMap);
			map.put("userList", userMap);
			
		} catch (Exception e) {
			log.error("insertUserRoleAction error");
		}*/
		return "privilege/userRole/userRoleInsert";
	}
	
	/**
	 * 给用户添加角色的实现（批量添加）
	 * 
	 * @return 用户角色的显示页面
	 */
	@RequestMapping(value="/insertUserRoleAction",method={RequestMethod.POST,RequestMethod.GET})
	public String insertUserRoleAction(HttpServletRequest request){
		
		/*User user = new User();//用户名作为查询条件
		String userid = userRole.getUserid();
		user.setUserid(userid);
		*/
		String userStr = request.getParameter("userRows");
		String roleStr = request.getParameter("roleRows");
		if(userStr == null || userStr == "undefined"){
			userStr = "";
		}
		if(roleStr == null || roleStr == "undefined"){
			roleStr = "";
		}
		JSONArray userJsonArray = JSONArray.fromObject(userStr);
		JSONArray roleJsonArray = JSONArray.fromObject(roleStr);
		
		List<User> userList = JSONArray.toList(userJsonArray, User.class);
		List<Role> roleList = JSONArray.toList(roleJsonArray, Role.class);
		if(userList != null && roleList != null){
			List<UserRole> userRoleList = new ArrayList<UserRole>();
			for(User user:userList){
				for(Role role:roleList){
					UserRole userRoleNew = new UserRole();
					userRoleNew.setUserid(user.getUserid());//设置用户id
					userRoleNew.setRoleid(role.getRoleid());//设置角色id 
					try {
						//通过 userid 和 roleid综合验证 是否存在。
						boolean ifExists = userRoleService.queryUserRoleIfExists(userRoleNew);
						if(!ifExists){ //如果不存在.set一个主键，添加
							String uuid = UUID.randomUUID().toString().replace("-", "");
							userRoleNew.setUserRoleId(uuid);
							
							//添加时候也把user 和 role属性给 userRole对象。 便于批量添加成功后，往 userRoleMap中放
							userRoleNew.setUser(user);
							userRoleNew.setRole(role);
							userRoleList.add(userRoleNew); //把符合条件的userRole添加到list,批量添加
						}
					} catch (Exception e) {
						log.error("queryUserRoleIfExists error 验证用户角色是否存在出错");
					}
					
				}
				
			}
			
			//遍历完成以后，把符合条件的（避免重复赋予某个角色）批量添加
			try {
				boolean b = userRoleService.batchInsertUserRoles(userRoleList);
				if(b){
					//添加成功以后，往userRoleMap中放入数据
					for(UserRole userRole:userRoleList){
						getUserRoleMap().put(userRole.getUserRoleId(), userRole);
						getUserRoleIdsMap().put(userRole.getUserid()+userRole.getRoleid(), userRole);
						
						//给用户赋予角色后，该角色有的权限也会相应获得
						userRoleService.putPlgIntoUserPlgAllMap(userRole);
					}
				}
			} catch (Exception e) {
				log.error("batchInsertUserRoles error 批量添加用户角色出错");
			}
		}
	
		/*
		String[] userss = request.getParameterValues("userRows");
		String [] userids = request.getParameterValues("userid");
		
		String [] roleids = request.getParameterValues("roleid");
		
		if(userids != null && roleids != null){
			List<UserRole> userRoleList = new ArrayList<UserRole>();
			for(String userid:userids){//把选中的用户遍历一下
				for(String roleid:roleids){ //选中的角色遍历一下
					UserRole userRoleNew = new UserRole();
					userRoleNew.setUserid(userid);
					userRoleNew.setRoleid(roleid);
					try {
						//通过 userid 和 roleid综合验证 是否存在。
						boolean ifExists = userRoleService.queryUserRoleIfExists(userRoleNew);
						if(!ifExists){ //如果不存在.set一个主键，添加
							
							String uuid = UUID.randomUUID().toString().replace("-", "");
							userRoleNew.setUserRoleId(uuid);
							userRoleList.add(userRoleNew); //把符合条件的userRole添加到list,批量添加
						}
					} catch (Exception e) {
						log.error("queryUserRoleIfExists error 验证用户角色是否存在出错");
					}
				}
				
			}
			
			//遍历完成以后，把符合条件的（避免重复赋予某个角色）批量添加
			try {
				boolean b2 = userRoleService.batchInsertUserRoles(userRoleList);
			} catch (Exception e) {
				log.error("batchInsertUserRoles error 批量添加用户角色出错");
			}
		}
		*/
		
		
		
		/*User userResult = new User(); 
		try {
			//userResult = userRoleService.queryUserByObj(user); //通过username查询user对象（主要用userid，给userRoleId赋值）
			userResult = (User) CachFactory.getInstance().getMapByKey("userMap").get(userRole.getUserid()); //通过username查询user对象（主要用userid，给userRoleId赋值）
			//userRole.setUserid(userResult.getUserid());		//设置要添加的用户
		} catch (Exception e) {
			log.error("queryUserByObj error  通过username查询user出错");
		}
		
		try {
			
			String uuid = UUID.randomUUID().toString().replace("-", "");
			userRole.setUserRoleId(uuid);
			
			boolean b = userRoleService.insertUserRole(userRole);
			
			if(b){//存放用户角色数据map容器也放入相应数据
				userRole.setUser(userResult);
				Role role = (Role) CachFactory.getInstance().getMapByKey("roleMap").get(userRole.getRoleid());
				userRole.setRole(role);
				
				getUserRoleMap().put(userRole.getUserRoleId(), userRole);
			}
		} catch (Exception e) {
			log.error("insertUserRole error 给用户赋予角色出错");
		}*/
		
		return "redirect:/userRole/queryAllUserRole.do";
	}

	/**
	 * 删除用户角色
	 * @param userRole 要删除的用户角色
	 * @return
	 */
	@RequestMapping(value="/deleteUserRole")
	public String deleteUserRole(UserRole userRole){
		
		try {
			boolean b = userRoleService.deleteUserRole(userRole);
			if(b){
				getUserRoleMap().remove(userRole.getUserRoleId());
				getUserRoleIdsMap().remove(userRole.getUserid()+userRole.getRoleid());
				
				//用户没有了角色，用户对象的权限可能也会减少 roleid  --> 多个权限id
				userRoleService.removePlgFromUserPlgAllMap(userRole);
			
			}
		} catch (Exception e) {
			log.error("deleteUserRole error 删除用户角色出错");
		}
		
		return "redirect:/userRole/queryAllUserRole.do";
	}
	
	/**
	 * 批量删除用户角色对象
	 * @param request 获取要删除的对象
	 * @return  返回显示页面
	 */
	@RequestMapping(value="deleteUserRolesById",method={RequestMethod.POST,RequestMethod.GET})
	public String deleteUserRolesById(HttpServletRequest request){
		String userRoleids = request.getParameter("userRoleids");//获取ids  各个id之间通过','分隔
		try {
			boolean b = userRoleService.deleteUserRolesById(userRoleids);
			if(b){
				
				UserRole userRole = new UserRole();
				//把userRoleMap中也remove掉相应的对象
				String [] userRoleidsArray = userRoleids.split(",");
				for(int i = 0; i < userRoleidsArray.length; i++ ){
					userRole = (UserRole) getUserRoleMap().get(userRoleidsArray[i]);//获取移除的 用户角色对象
					
					getUserRoleMap().remove(userRoleidsArray[i]);
					userRoleService.removePlgFromUserPlgAllMap(userRole);
				}
			}
			
		} catch (Exception e) {
			log.error("deleteUserRolesById error 批量删除用户角色对象");
		}
		
		return "redirect:/userRole/queryAllUserRole.do";
	}
	
}
