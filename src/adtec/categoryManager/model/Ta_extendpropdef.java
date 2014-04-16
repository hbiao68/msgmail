package adtec.categoryManager.model;

public class Ta_extendpropdef {

	private int propdefid;  //主键

	private String ta_id;   //外键，终端表的主键

	private int prop_index;  //扩展属性的序号

	private String propName;  //扩展属性的名称

	private String propDesc;  //属性描述

	private Ta_extendpropdef ta_extendpropdef;

	public int getPropdefid() {
		return propdefid;
	}

	public void setPropdefid(int propdefid) {
		this.propdefid = propdefid;
	}

	public String getTa_id() {
		return ta_id;
	}

	public void setTa_id(String ta_id) {
		this.ta_id = ta_id;
	}

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

	public Ta_extendpropdef getTa_extendpropdef() {
		return ta_extendpropdef;
	}

	public void setTa_extendpropdef(Ta_extendpropdef ta_extendpropdef) {
		this.ta_extendpropdef = ta_extendpropdef;
	}

}
