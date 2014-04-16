package adtec.account.model;

import adtec.appManager.model.PageModel;
import java.util.List;

public class Account {
	
	private String username;   //登陆用户名
	
	private String plainpassword;
	
	private String encryptedpassword;   //登陆密码
	
	private String name;    //名称
	
	private String email;   //邮箱
	
	private String creationdate;    //创建用户时间
	
	private String modificationdate;   //修改时间

	private Account account;
	
	private int start;   //分页用，从哪条数据开始
	
	private int pageSize;   //分页用，每页显示多少条数据
	
	private PageModel pageModel;
	
	private List<Account> list;
	
	public List<Account> getList() {
		return list;
	}

	public void setList(List<Account> list) {
		this.list = list;
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlainpassword() {
		return plainpassword;
	}

	public void setPlainpassword(String plainpassword) {
		this.plainpassword = plainpassword;
	}

	public String getEncryptedpassword() {
		return encryptedpassword;
	}

	public void setEncryptedpassword(String encryptedpassword) {
		this.encryptedpassword = encryptedpassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getModificationdate() {
		return modificationdate;
	}

	public void setModificationdate(String modificationdate) {
		this.modificationdate = modificationdate;
	}
	
}
