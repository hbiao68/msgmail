package adtec.privilege.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.init.CachFactory;
import adtec.privilege.dao.UserRoleDao;
import adtec.privilege.model.Role;
import adtec.privilege.model.RolePrivilege;
import adtec.privilege.model.User;
import adtec.privilege.model.UserPrivilege;
import adtec.privilege.model.UserRole;
import adtec.privilege.service.RolePrivilegeService;
import adtec.privilege.service.RoleService;
import adtec.privilege.service.UserRoleService;
import adtec.privilege.service.UserService;

/**
 * 用户角色管理的实现类
 * 
 * @author maojd
 * @date 14:21 2014/2/25
 */
@Transactional(propagation = Propagation.REQUIRED)
public class UserRoleServiceImp implements UserRoleService {

	private UserRoleDao userRoleDao;
	private UserService userService;
	private RoleService roleService;
	private RolePrivilegeService rolePrivilegeService;
	private Logger log = Logger.getLogger(UserRoleServiceImp.class);

	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}
	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public RolePrivilegeService getRolePrivilegeService() {
		return rolePrivilegeService;
	}
	public void setRolePrivilegeService(RolePrivilegeService rolePrivilegeService) {
		this.rolePrivilegeService = rolePrivilegeService;
	}

	/**
	 * 查询所有用户角色数据（带分页）
	 */
	@Override
	public List<UserRole> queryAllUserRole(UserRole userRole) throws Exception {
		List<UserRole> list = new ArrayList<UserRole>();
		try {
			list = userRoleDao.queryAllUserRole(userRole);
			for (UserRole userRoleNew : list) { // 便利用户角色list

				String userid = userRoleNew.getUserid();
				User user = userService.queryUserByUserid(userid); // 通过userid查询到user
																	// set给userRole对象
				userRoleNew.setUser(user);

				// 获取到role对象，set给userRole的role属性
				String roleid = userRoleNew.getRoleid();
				Role role = roleService.queryRoleByRoleid(roleid);
				userRoleNew.setRole(role);

			}
		} catch (Exception e) {
			log.error("queryAllUserRole error 查询用户角色出错");
			throw e;
		}

		return list;
	}

	@Override
	public List<Role> queryAllRole() throws Exception {
		List<Role> list = new ArrayList<Role>();
		list = roleService.queryAllRole(new Role());
		return list;
	}

	@Override
	public boolean insertUserRole(UserRole userRole) throws Exception {
		boolean b = true;
		try {
			userRoleDao.insertUserRole(userRole);
		} catch (Exception e) {
			b = false;
			log.error("insertUserRole error 添加用户角色实体出错");
			throw e;
		}
		return b;
	}


	@Override
	public boolean deleteUserRole(UserRole userRole) throws Exception {
		boolean b = true;
		try {
			userRoleDao.deleteUserRole(userRole);
		} catch (Exception e) {
			b = false;
			log.error("deleteUserRole error 删除用户角色关系数据出错");
			throw e;
		}
		return b;
	}

	@Override
	public List<UserRole> queryUserRoleByObj(UserRole userRole) throws Exception {
		
		UserRole userRoleResult = new UserRole();
		List<UserRole> list = new ArrayList<UserRole>();
		try {
			list = userRoleDao.queryUserRoleByObj(userRole);
			for(int i =0;i<list.size();i++){
				userRoleResult = list.get(i);
				// 获取到user对象set给userRole的user属性
				userRoleResult.setUser(userService
						.queryUserByUserid(userRoleResult.getUserid()));
				userRoleResult.setRole(roleService
						.queryRoleByRoleid(userRoleResult.getRoleid()));
			}
		} catch (Exception e) {
			log.error("queryUserRoleByObj error 通过用户角色实体 查询 用户角色出错");
			throw e;
		}

		return list;
	}

	/**
	 * 批量添加用户角色
	 */
	@Override
	public boolean batchInsertUserRoles(List<UserRole> userRoleList)
			throws Exception {
		boolean b = true;
		try {
			userRoleDao.batchInsertUserRoles(userRoleList);
		} catch (Exception e) {
			log.error("batchInsertUserRoles error 批量添加用户角色出错");
			b = false;
			throw e;
		}
		return b;
	}

	/**
	 * 查询用户角色对象是否存在(通过主键验证，或者通过 userid roleid统合验证)
	 */
	@Override
	public boolean queryUserRoleIfExists(UserRole userRole) throws Exception {
		boolean b = false;// 默认不存在
		try {
			List<UserRole> userRoleList = userRoleDao
					.queryUserRoleIfExists(userRole);
			if (userRoleList.size() > 0) {
				b = true;
			}
		} catch (Exception e) {
			log.error("queryUserRoleIfExists error 验证用户 角色是否存在出错");
			throw e;
		}

		return b;
	}

	/**
	 * 查询用户角色总数量（带模糊查询）
	 */
	@Override
	public int queryUserRoleCount(UserRole userRole) throws Exception {
		int count = 0;
		try {
			count = userRoleDao.queryUserRoleCount(userRole);
		} catch (Exception e) {
			log.error("queryUserRoleCount error 查询用户角色总数量出错");
			throw e;
		}
		return count;
	}

	/**
	 * 批量删除用户角色对象
	 */
	@Override
	public boolean deleteUserRolesById(String userRoleids) throws Exception {
		boolean b = true;
		try {
			List<String> idList = new ArrayList<String>();
			for(int i = 0; i < userRoleids.split(",").length; i++ ){
				idList.add(userRoleids.split(",")[i]);
			}
			userRoleDao.deleteUserRolesById(idList);
		} catch (Exception e) {
			b = false;
			log.error("deleteUserRolesById error");
			throw e;
		}
		return b;
	}

	/**
	 * 删除用户角色之后，把用户权限的map中 该角色的权限也删除
	 */
	@Override
	public boolean removePlgFromUserPlgAllMap(UserRole userRole)
			throws Exception {
		boolean b = true;
		try {
			Map<String, Object> userPrivlgIdsMap = CachFactory.getInstance().getMapByKey("userPrivlgIdsMap");
			Map<String, Object> userPrivlgIdsAllMap = CachFactory.getInstance().getMapByKey("userPrivlgIdsAllMap");
			
			//用户没有了角色，用户对象的权限可能也会减少 roleid  --> 多个权限id
			RolePrivilege rolePrivlgCon = new RolePrivilege();//查询条件
			rolePrivlgCon.setRoleid(userRole.getRoleid());
			List<RolePrivilege> rolePrivlgList = rolePrivilegeService.queryRolePrivilegeByObj(rolePrivlgCon);
			for(RolePrivilege rolePrivlgResult:rolePrivlgList){
				//验证用户本身是否有这个权限。有的话，不操作。 为null的话说明没有这个权限，角色删除以后 用户所有的权限map中remove掉
				
				UserPrivilege ifExitsUserPrivlg = (UserPrivilege) userPrivlgIdsMap.get(userRole.getUserid()+rolePrivlgResult.getPrivilegeid());
				if(ifExitsUserPrivlg == null){
					userPrivlgIdsAllMap.remove(userRole.getUserid()+rolePrivlgResult.getPrivilegeid());
				}
			}
		} catch (Exception e) {
			b = false;
			log.error("从全局的用户权限中移除权限出错");
			throw e;
		}
		
		return b;
	}
	
	/**
	 * 给用户赋予角色后，用户的权限相应增加
	 */
	@Override
	public boolean putPlgIntoUserPlgAllMap(UserRole userRole) throws Exception {
		boolean b = true;
		try {
			
			//验证用户本身是否有这个权限.
			Map<String, Object> userPrivlgIdsAllMap = CachFactory.getInstance().getMapByKey("userPrivlgIdsAllMap");
			
			//用户添加了角色，用户对象的权限可能也会增加 roleid  --> 多个权限id
			RolePrivilege rolePrivlgCon = new RolePrivilege();//查询条件
			rolePrivlgCon.setRoleid(userRole.getRoleid());
			List<RolePrivilege> rolePrivlgList = rolePrivilegeService.queryRolePrivilegeByObj(rolePrivlgCon);
			for(RolePrivilege rolePrivlgResult:rolePrivlgList){
				//验证用户本身是否有这个权限。有的话，不操作。 为null的话说明没有这个权限，角色添加以后 用户所有的权限map中也put进去
				
				UserPrivilege ifExitsUserPrivlg = (UserPrivilege) userPrivlgIdsAllMap.get(userRole.getUserid()+rolePrivlgResult.getPrivilegeid());
				if(ifExitsUserPrivlg == null){
					//如果不存在，set一个主键，放到总map中
					String uuid = UUID.randomUUID().toString().replace("-", "");
					UserPrivilege userPrivlgAdd = new UserPrivilege(uuid, userRole.getUserid(), rolePrivlgResult.getPrivilegeid(), userRole.getUser(), rolePrivlgResult.getPrivilege());
					userPrivlgIdsAllMap.put(userRole.getUserid()+rolePrivlgResult.getPrivilegeid(),userPrivlgAdd);
				}
			}
			
		} catch (Exception e) {
			b = false;
			log.error("putPlgIntoUserPlgAllMap error 向用户所有权限的map中添加 权限出错");
			throw e;
		}
		
		return b;
	}
	/**
	 * 通过对象查询数量（主要用于验证  用户是否开通角色等）
	 */
	@Override
	public int queryCountByObj(UserRole userRole) throws Exception {
		int count = 0;
		try {
			count = userRoleDao.queryCountByObj(userRole);
		} catch (Exception e) {
			log.error("queryCountByObj error 通过对象查询数量输错");
			throw e;
		}
		return count;
	}

}
