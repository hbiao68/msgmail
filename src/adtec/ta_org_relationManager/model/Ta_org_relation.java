package adtec.ta_org_relationManager.model;

public class Ta_org_relation {
	
	private int orgRelationId;	//主键
	
	private String orgId;  //外键，机构表（ta_organization表）的主键
	
	private int appid;   //外键，业务表（ta_app表）的主键
	
	private String ta_id;   //终端类型表（ta_category表）的主键
	
	private Ta_org_relation ta_org_relation;
	
	private int start;   //分页用，从哪条数据开始
	
	private int pageSize;   //分页用，每页显示多少条数据
	
	private String orgName;
	
	private String appName;
	
	
	
	
	
	

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

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

	public String getTa_id() {
		return ta_id;
	}

	public void setTa_id(String ta_id) {
		this.ta_id = ta_id;
	}

	public int getOrgRelationId() {
		return orgRelationId;
	}

	public void setOrgRelationId(int orgRelationId) {
		this.orgRelationId = orgRelationId;
	}



	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public Ta_org_relation getTa_org_relation() {
		return ta_org_relation;
	}

	public void setTa_org_relation(Ta_org_relation ta_org_relation) {
		this.ta_org_relation = ta_org_relation;
	}

}
