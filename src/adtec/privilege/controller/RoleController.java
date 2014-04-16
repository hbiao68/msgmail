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

import adtec.init.CachFactory;
import adtec.privilege.model.Page;
import adtec.privilege.model.Role;
import adtec.privilege.model.RolePrivilege;
import adtec.privilege.model.UserRole;
import adtec.privilege.service.RolePrivilegeService;
import adtec.privilege.service.RoleService;
import adtec.privilege.service.UserRoleService;

/**
 * 角色管理的control类，用来处理页面请求
 * 
 * @author maojd
 * @date 11:24 2014/2/21
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

	private RoleService roleService;
	private UserRoleService userRoleService;
	private RolePrivilegeService rolePrivilegeService;
	private Map<String, Object> roleMap = CachFactory.getInstance()
			.getMapByKey("roleMap");
	Logger log = Logger.getLogger(RoleController.class);

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Map<String, Object> getRoleMap() {
		if (roleMap == null) {
			roleMap = CachFactory.getInstance().getMapByKey("roleMap");
		}
		return roleMap;
	}
	public void setRoleMap(Map<String, Object> roleMap) {
		this.roleMap = roleMap;
	}
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

	/**
	 * 查询所有角色
	 * @param page 分页对象
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/queryAllRoleAction")
	public void queryAllRoleAction(Role role, HttpServletRequest request,
			HttpServletResponse response) {
		// List<Role> list = new ArrayList<Role>();
		/*
		 * try { //list = roleService.queryAllRole(); //map.put("roleList",
		 * list); //map.put("roleList", getRoleMap());
		 * 
		 * } catch (Exception e) { log.error("queryAllRole error"); }
		 */
		// 分页查询数据
		List<Role> roleList = new ArrayList<Role>();
		try {
			roleList = roleService.queryAllRole(role);// 分页查询
		} catch (Exception e1) {
			log.error("分页查询所有角色出错");
		}

		int count = 0;
		try {
			count = roleService.queryRoleCount(role);
		} catch (Exception e1) {
			log.error("查询角色数量出错");
		}

		Map<String, Object> roleEasyUIMap = new HashMap<String,Object>();
		roleEasyUIMap.put("rows", roleList);// easyUI需要用的rows
		roleEasyUIMap.put("total", count); // easyUI需要用的总数

		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			// pw.print(jsonData);
			// pw.write(JSONArray.fromObject(userMap).toString()); //输出的全部是字符串
			pw.write(JSONObject.fromObject(roleEasyUIMap).toString());// 输入的是json key
																// Object的字符串
			pw.flush();
		} catch (IOException e) {
			// e.printStackTrace();
			log.error("输出json出错");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

	}

	/**
	 * 查询所有角色
	 * 
	 * @return roleList页面
	 */
	@RequestMapping(value = "/queryAllRole", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String queryAllRole(Map<String, Object> map) {

		return "privilege/role/roleList";
	}

	/**
	 * 添加角色跳转
	 * 
	 * @return 跳转到角色添加页面
	 */
	@RequestMapping(value = "/insertRole", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String insertRole() {
		return "privilege/role/roleInsert";
	}

	/**
	 * 添加角色方法实现
	 * 
	 * @param role
	 *            要添加的实体
	 * @return
	 */
	@RequestMapping(value = "/insertRoleAction", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void insertRoleAction(Role role,HttpServletResponse response) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		role.setRoleid(uuid);
		String infoMsg = "";
		try {
			boolean b = roleService.insertRole(role);
			if (b) {// 如果添加成功，存放角色的roleMap容器也添加相应对应实体
				getRoleMap().put(role.getRoleid(), role);
				infoMsg = "addSuc";
			}
		} catch (Exception e) {
			infoMsg = "addError";
			log.error("添加角色失败  insertRoleAction error");
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
		//return "redirect:/role/queryAllRole.do";
	}

	/**
	 * 角色修改跳转
	 * 
	 * @param role
	 *            角色属性作为查询条件
	 * @param map
	 *            查询结果放到map容器，传到页面
	 * @return 跳转到修改页面
	 */
	@RequestMapping(value = "/updateRole", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String updateRole(Role role, Map<String, Object> map) {
		try {
			// Role roleRes =
			// roleService.queryRoleByRoleid(role.getRoleid());//条件查询
			// role只有一个roleid属性
			Role roleRes = (Role) roleMap.get(role.getRoleid());// 直接从存放角色roleMap的容器的中取出。避免频繁操作数据库
			map.put("role", roleRes);
		} catch (Exception e) {
			log.error("获取role对象错误 updateRole error");
		}
		return "privilege/role/roleUpdate";
	}

	/**
	 * 角色修改 的实现
	 * 
	 * @param role
	 *            要修改的角色实体
	 * @return 角色显示页面
	 */
	@RequestMapping(value = "/updateRoleAction", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateRoleAction(Role role,HttpServletResponse response) {
		String infoMsg = "";
		boolean b = true;
		try {
			b = roleService.updateRole(role);
			if (b) {// 如果更新成功，存放角色的roleMap容器也随着更新
				infoMsg = "updateSuc";
				getRoleMap().remove(role.getRoleid());
				getRoleMap().put(role.getRoleid(), role);
			}
		} catch (Exception e) {
			infoMsg = "updateError";
			b = false;
			log.error("角色修改出错  updateRoleAction error");
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
		
		//return "redirect:/role/queryAllRole.do";
	}

	/**
	 * 删除角色
	 * 
	 * @param role要删除的角色
	 * @return 返回角色的显示页面
	 */
	@RequestMapping(value = "/deleteRole", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String deleteRole(Role role) {
		boolean b = true;
		try {
			b = roleService.deleteRole(role);
			if (b) {// 如果删除成功，存放角色的roleMap容器也移除相应数据
				getRoleMap().remove(role.getRoleid());
			}
		} catch (Exception e) {
			b = false;
			log.error("删除角色 出错  deleteRole error ");
		}
		return "redirect:/role/queryAllRole.do";
	}
	
	/**
	 * 批量删除
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteRolesById")
	public void deleteRolesById(HttpServletRequest request,HttpServletResponse response){
		
		String roleids = request.getParameter("roleids");
		String infoMsg = "";//记录用户操作的提示信息（删除成功与否）
		try {
			
			String[] idsArray = roleids.split(",");
			for(int i = 0; i < idsArray.length; i++ ){
				int userRoleCount = userRoleService.queryCountByObj(new UserRole(null, null, idsArray[i], null, null));
				if(userRoleCount>0){//一旦查询出来，有用户在用该角色，则返回
					infoMsg = "isUsed";
					break;
				}
				
				int rolePrivlgCount = rolePrivilegeService.queryCountByObj(new RolePrivilege(null, idsArray[i], null, null, null));
				
				if(rolePrivlgCount>0){//一旦查询出来，有用户在用该角色，则返回
					infoMsg = "isUsed";
					break;
				}
			}
			
			//如果角色没有被用户 使用，并且角色没有开通权限。删除
			if(!"isUsed".equals(infoMsg)){
				boolean b = roleService.deleteRolesById(roleids);
				System.out.println("1111111111");
				if(b){
					infoMsg = "delSuc";
					for(int i = 0; i < idsArray.length; i++ ){
						getRoleMap().remove(idsArray[i]);
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
		//return "redirect:/role/queryAllRole.do";
	}
	
	/**
	 * 通过对象查询数量
	 * @param role
	 * @param response
	 */
	@RequestMapping(value="/queryCountByObj",method={RequestMethod.POST,RequestMethod.GET})
	public void queryCountByObj(Role role,HttpServletResponse response){
		int infoMsg = 0;
		try {
			List<Role> list = roleService.queryRoleByObj(role);
			if(list.size()>0){//加入只有一条的话，则为本身。
				infoMsg = list.size();
			}
		} catch (Exception e) {
			log.error("queryCountByObj error通过对象查询数量出错");
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
