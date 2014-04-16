package adtec.privilege.model;

/**
 * 用户角色表
 * @author huangbiao
 *
 */
public class UserRole extends Page{
	
	private String userRoleId;//主键
	private String userid;//用户ID
	private String roleid;//角色ID
	private User user;//用户对象。多表查询。要通过userid获取user对象的username属性
	private Role role;//角色对象。关联角色表 通过roleid获取role对象 和role.rolename
	//private Page page;//分页对象。
	
	/*public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}*/
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public UserRole(){
		
	}
	public UserRole(String userRoleId, String userid, String roleid, User user,
			Role role) {
		super();
		this.userRoleId = userRoleId;
		this.userid = userid;
		this.roleid = roleid;
		this.user = user;
		this.role = role;
	}
	
}
