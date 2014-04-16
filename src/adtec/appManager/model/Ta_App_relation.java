package adtec.appManager.model;

public class Ta_App_relation {

	private int relationid;  //主键

	private String accountName;  //帐号名称

	private int appid;  //外键，业务表的主键

	private String ta_id;  //外键，终端表（category表）的主键

	private Ta_App_relation ta_App_relation;

	private int start;   //分页用，从哪条数据开始
	
	private int pageSize;   //分页用，每页显示多少条数据

	private String appName;
	
	
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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

	public int getRelationid() {
		return relationid;
	}

	public void setRelationid(int relationid) {
		this.relationid = relationid;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public String getTa_id() {
		return ta_id;
	}

	public void setTa_id(String ta_id) {
		this.ta_id = ta_id;
	}

	public Ta_App_relation getTa_App_relation() {
		return ta_App_relation;
	}

	public void setTa_App_relation(Ta_App_relation ta_App_relation) {
		this.ta_App_relation = ta_App_relation;
	}

}
