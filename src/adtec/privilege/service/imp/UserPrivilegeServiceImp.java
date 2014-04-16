package adtec.privilege.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.init.CachFactory;
import adtec.privilege.dao.UserDao;
import adtec.privilege.dao.UserPrivilegeDao;
import adtec.privilege.model.Privilege;
import adtec.privilege.model.Role;
import adtec.privilege.model.RolePrivilege;
import adtec.privilege.model.User;
import adtec.privilege.model.UserPrivilege;
import adtec.privilege.model.UserRole;
import adtec.privilege.service.PrivilegeService;
import adtec.privilege.service.RolePrivilegeService;
import adtec.privilege.service.UserPrivilegeService;
import adtec.privilege.service.UserRoleService;
import adtec.privilege.service.UserService;

/**
 * 用户、权限 关联表服务层的实现类
 * 
 * @author maojd
 * @date 14:42 2014/2/26
 */
@Transactional(propagation = Propagation.REQUIRED)
public class UserPrivilegeServiceImp implements UserPrivilegeService {

	private UserPrivilegeDao userPrivilegeDao;
	private UserService userService;
	private PrivilegeService privilegeService;

	private UserRoleService userRoleService;//用户角色service。通过用户查询角色 
	private RolePrivilegeService rolePrivilegeService;//角色权限service.通过角色查询权限
	private Logger log = Logger.getLogger(UserPrivilegeServiceImp.class);

	public UserPrivilegeDao getUserPrivilegeDao() {
		return userPrivilegeDao;
	}

	public void setUserPrivilegeDao(UserPrivilegeDao userPrivilegeDao) {
		this.userPrivilegeDao = userPrivilegeDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}

	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
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
	
	@Override
	public List<UserPrivilege> queryAllUserPrivilege(UserPrivilege userPrivilege) throws Exception {
		List<UserPrivilege> list = new ArrayList<UserPrivilege>();
		try {
			list = userPrivilegeDao.queryAllUserPrivilege(userPrivilege);
		} catch (Exception e) {
			log.error("queryAllUserPrivilege error 查询用户、权限关系出错");
			throw e;
		}

		if (list.size() > 0) {
			for (UserPrivilege userPrivilegeNew : list) {

				User user = userService.queryUserByUserid(userPrivilegeNew
						.getUserid());// 通过外键查询出来user
				userPrivilegeNew.setUser(user); // 把user对象作为属性 set给 用户、权限对象

				// 把权限对象，通过外键关联查询出来，作为属性set给 用户、权限对象
				Privilege privilege = privilegeService
						.queryPrivilegeById(userPrivilegeNew.getPrivilegeid());
				userPrivilegeNew.setPrivilege(privilege);

			}
			
			
		
			

		}
		//通过用户查询到角色，通过角色查询出来权限，也封装成用户权限对象 放到list里面
		/*List<User> userList = new ArrayList<User>();
		
		userList = userService.queryAllUser(null);
		
		List<UserPrivilege> userPrivilegeList = new ArrayList<UserPrivilege>();
		userPrivilegeList = this.queryUserPrivilegeByRole(userList);
		list.addAll(userPrivilegeList);
		*/
		return list;

	}

	@Override
	public boolean deleteUserPrivilege(UserPrivilege userPrivilege)
			throws Exception {
		boolean b = true;
		try {
			userPrivilegeDao.deleteUserPrivilege(userPrivilege);
		} catch (Exception e) {
			b = false;
			log.error("deleteUserPrivilege error");
		}
		return b;
	}

	@Override
	public boolean insertUserPrivilege(UserPrivilege userPrivilege)
			throws Exception {
		boolean b = true;
		try {
			userPrivilegeDao.insertUserPrivilege(userPrivilege);
		} catch (Exception e) {
			b = false;
			log.error("insertUserPrivilege error 添加用户、权限数据出错");
			throw e;
		}
		return b;
	}

	/**
	 * 通过角色查询用户的权限信息
	 */
	/*@Override
	public List<UserPrivilege> queryUserPrivilegeByRole(List<User> userList)
			throws Exception {
		List<UserPrivilege> list = new ArrayList<UserPrivilege>();
		if (userList.size()>0) {
			for(User user:userList){

				//通过userid获取到userRole(用户就、角色)对象
				UserRole userRole = userRoleService.queryUserRoleByObj(new UserRole(null, user.getUserid(), null, null, null));
				
				//通过roleid获取到 rolePrivilege(角色、权限)对象
				RolePrivilege rolePrivilege = rolePrivilegeService.queryRolePrivilegeByObj(new RolePrivilege(null, userRole.getRoleid(), null, null, null));
				
				//获取到权限
				if(rolePrivilege.getPrivilege()!=null){
					String userPlgId = UUID.randomUUID().toString().replace("-", "");
					//组装一个 用户、权限对象
					UserPrivilege userPrivilege = new UserPrivilege(userPlgId, user.getUserid(), rolePrivilege.getPrivilegeid(), user, rolePrivilege.getPrivilege());
					list.add(userPrivilege);
				}
				Privilege privilege = new Privilege();
				String privilegeId = rolePrivilege.getPrivilegeid();
				
				if(( privilegeId != null) && (privilegeId != "") ){
					//privilege = privilegeService.queryPrivilegeById(rolePrivilege.getPrivilegeid());
					
					
					String userPlgId = UUID.randomUUID().toString().replace("-", "");
					//组装一个 用户、权限对象
					UserPrivilege userPrivilege = new UserPrivilege(userPlgId, user.getUserid(), privilege.getPrivilegeid(), user, privilege);
					list.add(userPrivilege);
				}
				
				
			}
		}
		
		return list;
	}*/

	/**
	 * 查询用户角色总数量（带模糊查询）
	 */
	@Override
	public int queryUserPrivCount(UserPrivilege userPrivilege) throws Exception {
		int count = 0;
		try {
			count = userPrivilegeDao.queryUserPrivCount(userPrivilege);
		} catch (Exception e) {
			log.error("queryUserPrivCount error 查询用户权限总数出错");
			throw e;
		}
		return count;
	}

	/**
	 * 查询用户权限对象是否存在
	 */
	@Override
	public boolean queryUserPrivlgIfExists(UserPrivilege userPrivlg)
			throws Exception {
		boolean b = false;//默认不存在
		try {
			List<UserPrivilege> list = userPrivilegeDao.queryUserPrivlgIfExists(userPrivlg);
			if(list.size()>0){
				b = true;//存在
			}
		} catch (Exception e) {
			log.error("queryUserPrivlgIfExists error 查询用户权限对象是否存在出错");
			throw e;
		}
		return b;
	}

	/**
	 * 批量添加用户权限
	 */
	@Override
	public boolean batchInsertUserPrivlgs(List<UserPrivilege> userPrivlgList)
			throws Exception {
		boolean b = true;
		try {
			userPrivilegeDao.batchInsertUserPrivlgs(userPrivlgList);
		} catch (Exception e) {
			b = false;
			log.error("batchInsertUserPrivlgs error 批量添加用户权限出错");
			throw e;
		}
		return b;
	}

	/**
	 * 批量删除用户权限对象
	 */
	@Override
	public boolean deleteUserPrivlgsById(String userPrivlgIds) throws Exception {
		boolean b = true;
		try {
			List<String> idList = new ArrayList<String>();
			for(int i = 0; i < userPrivlgIds.split(",").length; i++ ){
				idList.add(userPrivlgIds.split(",")[i]);
			}
			userPrivilegeDao.deleteUserPrivlgsById(idList);
		} catch (Exception e) {
			b = false;
			log.error("deleteUserPrivlgsById error 批量删除用户权限出错");
			throw e;
		}
		return b;
	}

	/**
	 * 查询用户角色权限(用户的所有权限 包括通过角色获取的权限)
	 * 还未实现
	 */
	@Override
	public List<UserPrivilege> queryAllUserRolePrivilege() throws Exception {
		List<UserPrivilege> userPrivlgList = this.queryAllUserPrivilege(null);
		/*List<User> userList = userService.queryAllUser(null);
		List<UserPrivilege> userRolePrivlgList = this.queryUserPrivilegeByRole(userList);
		for(UserPrivilege userPrivlg:userRolePrivlgList){
			UserPrivilege ifExitsUserPrivlg = CachFactory.
		}
		userPrivlgList.addAll(userRolePrivlgList);*/
		return userPrivlgList;
	}

	/**
	 * 通过用户查询用户所有权限
	 */
	@Override
	public Map<String, Privilege> queryUserAllPrivlg(User user) throws Exception {
		//List<UserPrivilege> list = new ArrayList<UserPrivilege>();
		Map<String, Privilege> mapPrivlg = new HashMap<String, Privilege>();//存放用户的权限
		Map<String, Object> userPrivlgIdsAllMap = CachFactory.getInstance().getMapByKey("userPrivlgIdsAllMap");//所用用户权限map
		
		
		Set<String> key = userPrivlgIdsAllMap.keySet();
		for(Iterator<String> i=key.iterator();i.hasNext(); ){
			String strKey = i.next();
			if(user.getUserid().equals(strKey.substring(0, 32))){//0 - 32  共32位数， map中的key是 userid + privlgid,所有截取前32位是userid
				UserPrivilege userPrivlg = (UserPrivilege) userPrivlgIdsAllMap.get(strKey);
				//mapPrivlg.put(userPrivlg.getPrivilegeid(), userPrivlg.getPrivilege());//把权限放到map中，传回去
				//把权限放到map中，传回去 . 资源+操作类型 作为key
				mapPrivlg.put(userPrivlg.getPrivilege().getResid()+userPrivlg.getPrivilege().getActionType(), userPrivlg.getPrivilege());
			}
		}
		
		//Map<String, Map<String, Privilege>> map = new HashMap<String, Map<String, Privilege>>(); 
		//map.put(user.getUserid(), mapPrivlg);//把用户的权限（map集合） 放到map中返回 
		return mapPrivlg;//用户的所有权限
	}

	/**
	 * 通过对象，查询数量（主要用于验证权限等是否被其他表使用）
	 */
	@Override
	public int queryCountByObj(UserPrivilege userPrivlg) throws Exception {
		int count = 0;
		try {
			count = userPrivilegeDao.queryCountByObj(userPrivlg);
		} catch (Exception e) {
			log.error("queryCountByObj error 查询数量出错");
			throw e;
		}
		return count;
	}

	
	
	
}