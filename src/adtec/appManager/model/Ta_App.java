package adtec.appManager.model;

public class Ta_App {

	private int appid;	//主键

	private String appName;	 //业务名称

	private String appDomain;  //业务服务器域名

	private String ta_id;   //终端分类表（ta_category表）的主键

	private Ta_App ta_App;   

	private int start;   //分页用，从哪条数据开始
	
	private int pageSize;   //分页用，每页显示多少条数据
	
	private String ef;  //下拉列表标记使用
	
	private String cateDesc;

	public String getCateDesc() {
		return cateDesc;
	}

	public void setCateDesc(String cateDesc) {
		this.cateDesc = cateDesc;
	}

	public String getEf() {
		return ef;
	}

	public void setEf(String ef) {
		this.ef = ef;
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

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDomain() {
		return appDomain;
	}

	public void setAppDomain(String appDomain) {
		this.appDomain = appDomain;
	}

	public String getTa_id() {
		return ta_id;
	}

	public void setTa_id(String ta_id) {
		this.ta_id = ta_id;
	}

	public Ta_App getTa_App() {
		return ta_App;
	}

	public void setTa_App(Ta_App ta_App) {
		this.ta_App = ta_App;
	}

}
