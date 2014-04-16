package adtec.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import adtec.privilege.model.Column;
import adtec.privilege.model.Privilege;
import adtec.privilege.model.Resource;
import adtec.privilege.model.Role;
import adtec.privilege.model.RolePrivilege;
import adtec.privilege.model.Type;
import adtec.privilege.model.User;
import adtec.privilege.model.UserPrivilege;
import adtec.privilege.model.UserRole;
import adtec.privilege.service.ColumnService;
import adtec.privilege.service.PrivilegeService;
import adtec.privilege.service.ResourceService;
import adtec.privilege.service.RolePrivilegeService;
import adtec.privilege.service.RoleService;
import adtec.privilege.service.TypeService;
import adtec.privilege.service.UserPrivilegeService;
import adtec.privilege.service.UserRoleService;
import adtec.privilege.service.UserService;

public class InitDataListener implements InitializingBean, ServletContextAware{

	
	private UserService userService;
	private RoleService roleService;
	private PrivilegeService privilegeService;
	private ResourceService resourceService;
	private UserPrivilegeService userPrivilegeService;
	private UserRoleService userRoleService;
	private RolePrivilegeService rolePrivilegeService;
	private TypeService typeService;
	private ColumnService columnService;
	private Logger log = Logger.getLogger(InitDataListener.class);
	

	@Override
	public void afterPropertiesSet() throws Exception {
		
		
	}
	@Override
	public void setServletContext(ServletContext arg) {
		
		this.initUserMap();
		this.initRoleMap();
		this.initPrivilegeMap();
		this.initResMap();
		this.initUserRoleMap();
		this.initUserPrivilegeMap();
		this.initRolePlgMap();
		this.initTypeMap();
		this.initColumnMap();
		this.initUserPrivlgIdsAllMap();
		
		//创建一个map，存在用户登陆的时候，获取到的所有权限  userid	key   权限map是vlaue(resid+actiontypeid,权限)
		CachFactory.getInstance().createCach("userAllPrivlgMap");
	}
	
	
	/**
	 * 初始化用户的总map
	 */
	public void initUserMap(){
		//userMap容器存放 userList（用户数据）
			List<User> userList = new ArrayList<User>(); 
			try {
				userList = userService.queryAllUser(null);
				Map<String, Object> userMap = CachFactory.getInstance().createCach("userMap");
				for(User user:userList){
					userMap.put(user.getUserid(), user);
				}
				
			} catch (Exception e) {
				log.error("queryAllUser error系统初始化查询所有用户出错");
			}
	}
	
	/**
	 * 初始化角色map
	 */
	public void initRoleMap(){
		//roleMap容器存放 roleList（角色数据）
				try {
					List<Role> roleList = roleService.queryAllRole(null);
					Map<String, Object> roleMap = CachFactory.getInstance().createCach("roleMap");
					for(Role role:roleList){
						roleMap.put(role.getRoleid(), role);
					}
				} catch (Exception e) {
					log.error("queryAllRole error查询所角色数据出错");
				}
	}
	
	/**
	 * 初始化权限map
	 */
	public void initPrivilegeMap(){
		//privilegeMap容器存放权限数据(privilegeList)     两个map
				try {
					List<Privilege> privilegeList = privilegeService.queryAllPrivilege(null);
					Map<String, Object> privilegeMap = CachFactory.getInstance().createCach("privilegeMap");//通过权限id为key存放权限数据
					Map<String, Object> privilegeIdsMap = CachFactory.getInstance().createCach("privilegeIdsMap");//通过 资源id + actionid 作为key
					for(Privilege privilege:privilegeList){
						privilegeMap.put(privilege.getPrivilegeid(), privilege);
						privilegeIdsMap.put(privilege.getResid()+privilege.getActionType(), privilege);
					}
				} catch (Exception e) {
					log.error("queryAllPrivilege error查询所有权限数据出错");
				}
	}
	
	/**
	 * 初始化资源map
	 */
	public void initResMap(){
		//resMap 容器存放全局的资源数据（resList）
		try {
			List<Resource> resList = resourceService.queryAllResource(null);
			Map<String, Object> resMap = CachFactory.getInstance().createCach("resMap");
			for(Resource res:resList){
				resMap.put(res.getResid(), res);
			}
		} catch (Exception e) {
			log.error("queryAllResource error 查询所有资源数据出错");
		}
	}
	
	/**
	 * 初始化用户角色map
	 */
	public void initUserRoleMap(){
		//userRoleMap容器存放用户角色userRoleList数据  	 两个map
				try{
					Map<String, Object> userRoleMap = CachFactory.getInstance().createCach("userRoleMap");
					Map<String, Object> userRoleIdsMap = CachFactory.getInstance().createCach("userRoleIdsMap");
					List<UserRole> userRoleList = userRoleService.queryAllUserRole(null);
					for(UserRole userRole:userRoleList){
						userRoleMap.put(userRole.getUserRoleId(), userRole);
						userRoleIdsMap.put(userRole.getUserid()+userRole.getRoleid(), userRole);
					}
				}catch(Exception e){
					log.error("queryAllUserRole error 查询所有用户角色出错");
				}
	}
	
	/**
	 * 初始化用户权限 map
	 */
	public void initUserPrivilegeMap(){
		//userPrivilegeMap容器 存放userPrivilegeList数据 用户权限关系     两个map
		try {
			Map<String, Object> userPrivilegeMap = CachFactory.getInstance().createCach("userPrivilegeMap");//以userPrivilegeid为key
			Map<String, Object> userPrivlgIdsMap = CachFactory.getInstance().createCach("userPrivlgIdsMap");//以 userid + privilegeid 为key
			List<UserPrivilege> userPrivilegeList = userPrivilegeService.queryAllUserPrivilege(null);
			for(UserPrivilege userPrivilege:userPrivilegeList){
				userPrivilegeMap.put(userPrivilege.getUserPlgId(), userPrivilege);
				userPrivlgIdsMap.put(userPrivilege.getUserid()+userPrivilege.getPrivilegeid(), userPrivilege);
			}
		} catch (Exception e) {
			log.error("queryAllUserPrivilege error 查询所有用户权限关系出错");
		}
	}
	
	
	/**
	 * 初始化角色权限map
	 * @return
	 */
	public void initRolePlgMap(){
		//rolePlgLMap 存放角色权限关系rolePlgList的map容器		两个map
		try {
			List<RolePrivilege> rolePlgList = rolePrivilegeService.queryAllRolePrivilege(null);
			Map<String, Object> rolePlgMap = CachFactory.getInstance().createCach("rolePlgMap");//通过rolePlg的主键
			Map<String, Object> rolePlgIdsMap = CachFactory.getInstance().createCach("rolePlgIdsMap");//通过 roleid+plgid
			for(RolePrivilege rolePlg:rolePlgList){
				rolePlgMap.put(rolePlg.getRolePlgId(), rolePlg);
				rolePlgIdsMap.put(rolePlg.getRoleid()+rolePlg.getPrivilegeid(), rolePlg);
			}
		} catch (Exception e) {
			log.error("queryAllRolePrivilege error 查询所有角色权限关系出错");
		}
	}
	
	
	/**
	 * 初始化操作类型的map
	 * @return
	 */
	public void initTypeMap(){
		//typeMap 容器存放 typeList数据（操作类型）
		try {
			
			Map<String, Object> typeMap = CachFactory.getInstance().createCach("typeMap");
			List<Type> typeList = typeService.queryAllType(null);
			for(Type type:typeList){
				typeMap.put(type.getTypeid(), type);
			}
		} catch (Exception e) {
			log.error("queryAllType error 查询所有操作类型出错");
		}
	}
	
	/**
	 * 初始化栏位信息 map
	 * @return
	 */
	public void initColumnMap(){
		//columnMap 存放栏位信息的容器    key:columnId		value:column
		try {
			Map<String, Object> columnMap = CachFactory.getInstance().createCach("columnMap");
			List<Column> columnList = columnService.queryAllColumn(null);  
			for(Column column:columnList){
				columnMap.put(column.getColumnId(), column);
			}
		} catch (Exception e) {
			log.error("queryAllColumn error查询所有栏位出错");
		}
	}
	
	
	/**
	 * 初始化用户所有的权限（包括通过角色获取到的权限）
	 */
	public void initUserPrivlgIdsAllMap(){
		//用户所有权限userPrivlgIdsAllMap (所用的权限，包括通过角色获取得到的权限。key 是 userid+privlgid)
		//备注：这个方法要用到其他map，所有写到 userMap  userRoleMap  rolePlgMap的下面
		try {
			//创建一个map,存放用户所有的权限
			Map<String, Object> userPrivlgIdsAllMap = CachFactory.getInstance().createCach("userPrivlgIdsAllMap");
			
			//获取用户权限表的map,用户验证 用户的权限是否存在
			Map<String, Object> userPrivlgIdsMap = CachFactory.getInstance().getMapByKey("userPrivlgIdsMap");
			Map<String, Object> userPrivilegeMap = CachFactory.getInstance().getMapByKey("userPrivilegeMap");
			Map<String, Object> userMap = CachFactory.getInstance().getMapByKey("userMap");
			Map<String, Object> privilegeMap = CachFactory.getInstance().getMapByKey("privilegeMap");
			
			//首先把用户本身就有的权限userPrivlgMap 给userPrivlgIdsAllMap
			Collection<Object> cUserPrivilegeMap = userPrivilegeMap.values();
			Iterator<Object> itUserPrivilegeMap = cUserPrivilegeMap.iterator();
			for(;itUserPrivilegeMap.hasNext();){
				UserPrivilege userPrivlgOld = (UserPrivilege) itUserPrivilegeMap.next();
				userPrivlgIdsAllMap.put(userPrivlgOld.getUserid()+userPrivlgOld.getPrivilegeid(), userPrivlgOld);
			}
			
			
			Collection<Object> c = userMap.values();
			Iterator<Object> it = c.iterator();
			for(;it.hasNext();){
				User user = (User) it.next();
				UserRole userRole = new UserRole();
				userRole.setUserid(user.getUserid());
				//通过用户  获取到角色
				List<UserRole> userRoleList = userRoleService.queryUserRoleByObj(userRole);
				
				//便利一下userRoleList  目的：通过角色id  获取到权限
				for(UserRole userRoleResult:userRoleList){//userRoleResult遍历的 用户角色对象
					RolePrivilege rolePrivlg = new RolePrivilege();//查询条件
					rolePrivlg.setRoleid(userRoleResult.getRoleid());
					
					List<RolePrivilege> rolePrivlgList = rolePrivilegeService.queryRolePrivilegeByObj(rolePrivlg);
					
					//便利一下，把权限id 和 用户id联系起来
					for(RolePrivilege rolePrivlgResult:rolePrivlgList){//rolePrivlgResult 遍历的角色 权限对象
						UserPrivilege userPrivlg = (UserPrivilege) userPrivlgIdsMap.get(user.getUserid()+rolePrivlgResult.getPrivilegeid());
						if(userPrivlg == null){
							//假如不存在。给一个主键，添加到 用户所有权限的map中userPrivlgIdsAllMap
							Privilege privlg = (Privilege) privilegeMap.get(rolePrivlgResult.getPrivilegeid());
							String uuid = UUID.randomUUID().toString().replace("-", "");
							UserPrivilege userPrivlgAdd = new UserPrivilege(uuid, user.getUserid(), rolePrivlgResult.getPrivilegeid(), user, privlg);
							
							//最后，添加到总的 用户所有的 权限map中
							userPrivlgIdsAllMap.put(userPrivlgAdd.getUserid()+userPrivlgAdd.getPrivilegeid(), userPrivlgAdd);
						}
					}
				}
				
			}
		} catch (Exception e) {
			log.error("userPrivlgIdsAllMap error查询用户所有的权限 出错");
		}
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
	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}
	public ResourceService getResourceService() {
		return resourceService;
	}
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
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
	public RolePrivilegeService getRolePrivilegeService() {
		return rolePrivilegeService;
	}
	public void setRolePrivilegeService(RolePrivilegeService rolePrivilegeService) {
		this.rolePrivilegeService = rolePrivilegeService;
	}
	public TypeService getTypeService() {
		return typeService;
	}
	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}
	public ColumnService getColumnService() {
		return columnService;
	}
	public void setColumnService(ColumnService columnService) {
		this.columnService = columnService;
	}

}
