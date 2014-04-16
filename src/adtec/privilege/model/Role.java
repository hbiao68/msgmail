package adtec.privilege.model;

/**
 * 角色表
 * @author huangbiao
 *
 */
public class Role extends Page{
	
	private String roleid;//角色id
	private String rolename;//角色名称
	//private Page page;
	/*public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}*/
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	
}
