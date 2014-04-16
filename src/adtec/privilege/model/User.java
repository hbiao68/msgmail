package adtec.privilege.model;

/**
 * 用户表
 * @author huangbiao
 *
 */
public class User extends Page{
	
	private String userid;//用户主键
	private String username;//用户名
	private String password;//密码
	
	
	
	//private Page page;
	/*public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}*/
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(){
		
	}
	public User(String userid, String username, String password) {
		this.userid = userid;
		this.username = username;
		this.password = password;
	}
	

}
