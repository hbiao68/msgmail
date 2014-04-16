package adtec.privilege.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.init.CachFactory;
import adtec.privilege.dao.RolePrivilegeDao;
import adtec.privilege.model.Privilege;
import adtec.privilege.model.Role;
import adtec.privilege.model.RolePrivilege;
import adtec.privilege.model.UserPrivilege;
import adtec.privilege.model.UserRole;
import adtec.privilege.service.PrivilegeService;
import adtec.privilege.service.RolePrivilegeService;
import adtec.privilege.service.RoleService;
import adtec.privilege.service.UserRoleService;
import adtec.privilege.service.UserService;

/**
 * 角色、权限关联 service层实现类
 * @author maojd 
 * @date 11:08 2014/2/27
 */
@Transactional(propagation = Propagation.REQUIRED)
public class RolePrivilegeServiceImp implements RolePrivilegeService{

	private RolePrivilegeDao rolePrivilegeDao;//角色权限dao
	private RoleService roleService;		//角色service 外键关联。查询角色权限的  角色属性 需要用
	private PrivilegeService privilegeService;//权限service   查询角色权限的 权限属性用
	private UserRoleService userRoleService;			//通过 角色id， 
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}
	
	private Logger log = Logger.getLogger(RolePrivilegeServiceImp.class);
	public RolePrivilegeDao getRolePrivilegeDao() {
		return rolePrivilegeDao;
	}
	public void setRolePrivilegeDao(RolePrivilegeDao rolePrivilegeDao) {
		this.rolePrivilegeDao = rolePrivilegeDao;
	}
	public UserRoleService getUserRoleService() {
		return userRoleService;
	}
	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}
	@Override
	public List<RolePrivilege> queryAllRolePrivilege(RolePrivilege rolePrivlg) throws Exception{
		List<RolePrivilege> list = new ArrayList<RolePrivilege>();
		try {
			list = rolePrivilegeDao.queryAllRolePrivilege(rolePrivlg);//带分页的查询
			for(RolePrivilege rolePrivilege:list){
				//通过外键查询到角色,set给 角色权限的user属性
				Role role = roleService.queryRoleByRoleid(rolePrivilege.getRoleid());
				rolePrivilege.setRole(role);
				
				//通过外键查询到 权限对象，set给角色权限的 权限属性
				Privilege privilege = privilegeService.queryPrivilegeById(rolePrivilege.getPrivilegeid());
				rolePrivilege.setPrivilege(privilege);
				
			}
			
			
		} catch (Exception e) {
			log.error("queryAllRolePrivilege error 查询所有角色、权限关系出错");
			throw e;
		}
		
		return list;
	}
	@Override
	public boolean insertRolePrivilege(RolePrivilege rolePrivilege) throws Exception{
		boolean b = true;
		try {
			rolePrivilegeDao.insertRolePrivilege(rolePrivilege);
		} catch (Exception e) {
			b = false;
			log.error("");
			throw e;
		}
		return b;
	}
	@Override
	public boolean deleteRolePrivilege(RolePrivilege rolePrivilege)
			throws Exception {
		boolean b = true;
		try {
			rolePrivilegeDao.deleteRolePrivilege(rolePrivilege);
		} catch (Exception e) {
			b = false;
			log.error("deleteRolePrivilege error 删除角色、权限出错");
			throw e;
		}
		return b;
	}
	@Override
	public List<RolePrivilege> queryRolePrivilegeByObj(RolePrivilege rolePrivilege)
			throws Exception {
		List<RolePrivilege> list = new ArrayList<RolePrivilege>();
		RolePrivilege rolePrivilegeResult = new RolePrivilege();
		try {
			list = rolePrivilegeDao.queryRolePrivilegeByObj(rolePrivilege);
			for(int i = 0;i<list.size();i++){
				rolePrivilegeResult = list.get(i);
				//获取到role对象 set给 rolePrivilege的角色属性
				rolePrivilegeResult.setRole(roleService.queryRoleByRoleid(rolePrivilegeResult.getRoleid()));
				
				//获取到 privilege(权限)对象，set给 rolePrivilege(角色权限)对象的 权限属性
				Privilege privilege =  privilegeService.queryPrivilegeById(rolePrivilegeResult.getPrivilegeid());
				//rolePrivilegeResult.setPrivilege(privilegeService.queryPrivilegeById(rolePrivilegeResult.getPrivilegeid()));
				rolePrivilegeResult.setPrivilege(privilege);
				
			}
			
		} catch (Exception e) {
			log.error("queryRolePrivilegeByObj error 条件查询角色 权限出错");
			throw e;
		}
		return list;
	}
	/**
	 * 查询总数 包括模糊查询
	 */
	@Override
	public int queryRolePrivlgCount(RolePrivilege rolePrivilege) throws Exception {
		int count = 0;
		try {
			count = rolePrivilegeDao.queryRolePrivlgCount(rolePrivilege);
		} catch (Exception e) {
			log.error("queryRolePrivlgCount error 查询角色权限总数出错");
			throw e;
		}
		return count;
	}
	
	@Override
	public boolean deleteRolePrivlgsById(String ids) throws Exception {
		boolean b = true;
		try {
			List<String> idList = new ArrayList<String>();
			for(int i = 0; i < ids.split(",").length; i++ ){
				idList.add(ids.split(",")[i]);
			}
			rolePrivilegeDao.deleteRolePrivlgsById(idList);
		} catch (Exception e) {
			b = false;
			log.error("deleteRolePrivlgsById error批量删除角色权限粗错");
		}
		return b;
	}
	
	/**
	 * 批量添加
	 */
	@Override
	public boolean batchInsertRolePrivlgs(List<RolePrivilege> rolePrivlgList)
			throws Exception {
		boolean b = true;
		try {
			rolePrivilegeDao.batchInsertRolePrivlgs(rolePrivlgList);
		} catch (Exception e) {
			b = false;
			log.error("batchInsertRolePrivlgs error 批量添加角色 权限出错");
			throw e;
		}
		return b;
	}
	
	/**
	 * 给角色授权，相应的用户可能会获取更多的权限，添加到总的userPrivlgIdsAllMap 用户、权限中
	 */
	@Override
	public boolean putPlgIntoUserPlgAllMap(RolePrivilege rolePrivlg)
			throws Exception {
		boolean b = true;
		try {
			//角色添加了权限，用户对象的权限可能也会增加   用户<--- roleid  --> 多个权限id
			
			//验证用户本身是否有这个权限.(包括通过其他角色)
			Map<String, Object> userPrivlgIdsAllMap = CachFactory.getInstance().getMapByKey("userPrivlgIdsAllMap");
			RolePrivilege rolePrivlgCon = new RolePrivilege();//角色权限的查询条件
			rolePrivlgCon.setRoleid(rolePrivlg.getRoleid());
			
			List<UserRole> userRoleList = userRoleService.queryUserRoleByObj(new UserRole(null, null, rolePrivlgCon.getRoleid(), null, null));
			List<RolePrivilege> rolePrivlgList = this.queryRolePrivilegeByObj(rolePrivlgCon);//通过角色id  查询角色权限
			
			//获取到所有的userRole，看user是否有权限
			for(UserRole userRoleResult:userRoleList){
				for(RolePrivilege rolePrivlgResult:rolePrivlgList){
					UserPrivilege ifExitsUserPrivlg = (UserPrivilege) userPrivlgIdsAllMap.get(userRoleResult.getUserid()+rolePrivlgResult.getPrivilegeid());
					if(ifExitsUserPrivlg == null){
						//如果不存在，set一个主键，放到总map中
						String uuid = UUID.randomUUID().toString().replace("-", "");
						UserPrivilege userPrivlgAdd = new UserPrivilege(uuid, userRoleResult.getUserid(), rolePrivlgResult.getPrivilegeid(), userRoleResult.getUser(), rolePrivlgResult.getPrivilege());
						userPrivlgIdsAllMap.put(userRoleResult.getUserid()+rolePrivlgResult.getPrivilegeid(),userPrivlgAdd);
					}
				}
			}
		} catch (Exception e) {
			b = false;
			log.error("putPlgIntoUserPlgAllMap error通过角色权限给 用户总map添加数据出错");
			throw e;
		}
		return b;
	}
	
	/**
	 * 角色的权限删除以后，相应的用户 权限也会减少。存放用户所有权限的map中remove掉相应的权限
	 */
	@Override
	public boolean removePlgFromUserPlgAllMap(RolePrivilege rolePrivlg)
			throws Exception {
		boolean b = true;
		try {
			//验证用户本身是否有这个权限.
			Map<String, Object> userPrivlgIdsMap = CachFactory.getInstance().getMapByKey("userPrivlgIdsMap");
			Map<String, Object> userPrivlgIdsAllMap = CachFactory.getInstance().getMapByKey("userPrivlgIdsAllMap");
			//验证其他角色是否有这个权限
			Map<String, Object> rolePlgIdsMap = CachFactory.getInstance().createCach("rolePlgIdsMap");//通过 roleid+plgid
			
			RolePrivilege rolePrivlgCon = new RolePrivilege();//角色权限的查询条件
			rolePrivlgCon.setRoleid(rolePrivlg.getRoleid());
			
			List<UserRole> userRoleList = userRoleService.queryUserRoleByObj(new UserRole(null, null, rolePrivlgCon.getRoleid(), null, null));
			
			
			//获取到所有的userRole，看user本身是否有权限.遍历每一个用户    				目的是 ： 用户
			for(UserRole userRoleResult:userRoleList){
				UserPrivilege ifExitsUserPrivlg = (UserPrivilege) userPrivlgIdsMap.get(userRoleResult.getUserid()+rolePrivlg.getPrivilegeid());
				if(ifExitsUserPrivlg == null){
					//1. 用户本身没有权限。查询是否通过其他角色获取到权限
					
					//false 表示其他角色没有这个权限，true表示角色有这个权限
					boolean otherRolePrivlg = false;
					//如果用户本身没有权限，看用户是否通过其他的角色获取到了权限。 		目的是 ：其他的角色
					List<UserRole> userRoleListRole = userRoleService.queryUserRoleByObj(new UserRole(null, userRoleResult.getUserid(), null, null, null));
					for(UserRole userRoleRole:userRoleListRole){
						//排除用户当前的角色
						if(userRoleRole.getRoleid()!=userRoleResult.getRoleid()){//当前用户的角色 != 这个要删除的角色（rolePrivlg.getRoleid()）
							//验证角色是否有相应的权限
							RolePrivilege ifExistOtherRolePrivlg = (RolePrivilege) rolePlgIdsMap.get(userRoleRole.getRoleid()+rolePrivlg.getPrivilegeid());
							if(ifExistOtherRolePrivlg!=null){//只要不为空，就证明其他角色有这个权限。不用处理这个权限就可以了
								otherRolePrivlg = true;
								break;
							}
						}
					}
					
					//2. 如果其他角色没有这个权限,把这个 用户的相应的权限移除掉
					if(!otherRolePrivlg){
						userPrivlgIdsAllMap.remove(userRoleResult.getUserid()+rolePrivlg.getPrivilegeid());
					}
					
				}
				
			}//用户遍历结束
			
			
		} catch (Exception e) {
			b = false;
			log.error("removePlgFromUserPlgAllMap error通过角色权限删除总的用户权限map 出错");
			throw e;
		}
		return b;
	}
	
	/**
	 * 通过对象查询数量（主要用于查询其它标是否使用）
	 */
	@Override
	public int queryCountByObj(RolePrivilege rolePrivilege) throws Exception {
		int count = 0;
		try {
			count = rolePrivilegeDao.queryCountByObj(rolePrivilege);
		} catch (Exception e) {
			log.error("queryCountByObj error 通过对象查询数量出错");
			throw e;
		}
		return count;
	}

}
