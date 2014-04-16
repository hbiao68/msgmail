package adtec.accountManager.model;

public class Ta_Account_cateName {

	private int Id;  //主键

	private String orgId;  //外键，ta_organization(机构表)的主键

	private String accountName;  //帐号名称

	private String accountPwd;  //密码

	private String email;  //电子邮箱

	private String ta_id;  //外键，ta_category(终端表)的主键

	private String status;  //状态  

	private String delflag;  //标记

	private Ta_Account_cateName ta_Account_cateName;

	private int start;   //分页用，从哪条数据开始
	
	private int pageSize;   //分页用，每页显示多少条数据
	
	private String prop1;  //对于帐号的描述，最多十种，从prop1到prop10

	private String prop2;

	private String prop3;

	private String prop4;

	private String prop5;

	private String prop6;

	private String prop7;

	private String prop8;

	private String prop9;

	private String prop10;
	
	private String cateName;
	
	private String orgName;
	
	

	public String getProp1() {
		return prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public String getProp2() {
		return prop2;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}

	public String getProp3() {
		return prop3;
	}

	public void setProp3(String prop3) {
		this.prop3 = prop3;
	}

	public String getProp4() {
		return prop4;
	}

	public void setProp4(String prop4) {
		this.prop4 = prop4;
	}

	public String getProp5() {
		return prop5;
	}

	public void setProp5(String prop5) {
		this.prop5 = prop5;
	}

	public String getProp6() {
		return prop6;
	}

	public void setProp6(String prop6) {
		this.prop6 = prop6;
	}

	public String getProp7() {
		return prop7;
	}

	public void setProp7(String prop7) {
		this.prop7 = prop7;
	}

	public String getProp8() {
		return prop8;
	}

	public void setProp8(String prop8) {
		this.prop8 = prop8;
	}

	public String getProp9() {
		return prop9;
	}

	public void setProp9(String prop9) {
		this.prop9 = prop9;
	}

	public String getProp10() {
		return prop10;
	}

	public void setProp10(String prop10) {
		this.prop10 = prop10;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public Ta_Account_cateName getTa_Account_cateName() {
		return ta_Account_cateName;
	}

	public void setTa_Account_cateName(Ta_Account_cateName ta_Account_cateName) {
		this.ta_Account_cateName = ta_Account_cateName;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTa_id() {
		return ta_id;
	}

	public void setTa_id(String ta_id) {
		this.ta_id = ta_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

}
