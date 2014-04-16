package adtec.categoryManager.model;

public class Ta_category {
	
	private String ta_id;  //主键

	private String cateName;  //终端名称

	private String importClass;  //导入类

	private String authClass;  //认证类

	private String cateDesc;  //终端类型

	private Ta_category ta_category;

	private int start;   //分页用，从哪条数据开始
	
	private int pageSize;   //分页用，每页显示多少条数据

	private String propName;
	private String propDesc;
	private String propdefid;
	
	private int prop_index;
	
	
	
	
	
	public int getProp_index() {
		return prop_index;
	}

	public void setProp_index(int prop_index) {
		this.prop_index = prop_index;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getPropDesc() {
		return propDesc;
	}

	public void setPropDesc(String propDesc) {
		this.propDesc = propDesc;
	}

	public String getPropdefid() {
		return propdefid;
	}

	public void setPropdefid(String propdefid) {
		this.propdefid = propdefid;
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

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getImportClass() {
		return importClass;
	}

	public void setImportClass(String importClass) {
		this.importClass = importClass;
	}

	public String getAuthClass() {
		return authClass;
	}

	public void setAuthClass(String authClass) {
		this.authClass = authClass;
	}

	public String getCateDesc() {
		return cateDesc;
	}

	public void setCateDesc(String cateDesc) {
		this.cateDesc = cateDesc;
	}

	public Ta_category getTa_category() {
		return ta_category;
	}

	public void setTa_category(Ta_category ta_category) {
		this.ta_category = ta_category;
	}

}
