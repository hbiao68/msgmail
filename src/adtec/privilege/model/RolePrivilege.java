package adtec.privilege.model;

/**
 * 角色权限实体类
 * @author maojd
 * @date 11:01 2014/2/27
 */
public class RolePrivilege extends Page{

	private String rolePlgId;//角色权限 id
	private String roleid;//角色id
	private String privilegeid;//权限id
	private Role role;			//把role(角色)对象作为rolePrivilege(角色权限)的一个属性,便于页面取值  
	private Privilege privilege;//把privilege(权限对象)作为  角色权限的一个属性， 便于页面取值
	//private Page page;			//分页对象作为角色权限对象的属性，便于分页查询
	
	public String getRolePlgId() {
		return rolePlgId;
	}
	public void setRolePlgId(String rolePlgId) {
		this.rolePlgId = rolePlgId;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getPrivilegeid() {
		return privilegeid;
	}
	public void setPrivilegeid(String privilegeid) {
		this.privilegeid = privilegeid;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	
	/*public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}*/
	public RolePrivilege(){
		
	}
	public RolePrivilege(String rolePlgId, String roleid, String privilegeid,
			Role role, Privilege privilege) {
		this.rolePlgId = rolePlgId;
		this.roleid = roleid;
		this.privilegeid = privilegeid;
		this.role = role;
		this.privilege = privilege;
	}
	
}
