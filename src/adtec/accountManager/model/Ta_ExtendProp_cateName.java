package adtec.accountManager.model;

public class Ta_ExtendProp_cateName {

	private int Ta_extId;  //主键

	private int Id;  //外键，为Ta_Account_cateName（帐号表）的主键

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

	private Ta_ExtendProp_cateName ta_ExtendProp_cateName;

	public int getTa_extId() {
		return Ta_extId;
	}

	public void setTa_extId(int ta_extId) {
		Ta_extId = ta_extId;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

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

	public Ta_ExtendProp_cateName getTa_ExtendProp_cateName() {
		return ta_ExtendProp_cateName;
	}

	public void setTa_ExtendProp_cateName(
			Ta_ExtendProp_cateName ta_ExtendProp_cateName) {
		this.ta_ExtendProp_cateName = ta_ExtendProp_cateName;
	}

	

}
