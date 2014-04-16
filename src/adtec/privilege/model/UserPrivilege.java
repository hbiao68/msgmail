package adtec.privilege.model;

/**
 * 用户权限表
 * @author huangbiao
 *
 */
public class UserPrivilege extends Page{

	private String userPlgId;//用户权限表id
	private String userid;//用户id
	private String privilegeid;//权限id
	private User user;			//把用户作为 用户、权限 的一个属性。页面便于获取用户信息
	private Privilege privilege;//把权限作为  用户、权限的一个属性，页面便于获取权限信息
	//private Page page;//分页对象
	
	/*public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}*/
	public String getUserPlgId() {
		return userPlgId;
	}
	public void setUserPlgId(String userPlgId) {
		this.userPlgId = userPlgId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPrivilegeid() {
		return privilegeid;
	}
	public void setPrivilegeid(String privilegeid) {
		this.privilegeid = privilegeid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	public UserPrivilege(){
		
	}
	public UserPrivilege(String userPlgId, String userid, String privilegeid,
			User user, Privilege privilege) {
		this.userPlgId = userPlgId;
		this.userid = userid;
		this.privilegeid = privilegeid;
		this.user = user;
		this.privilege = privilege;
	}
	
}
