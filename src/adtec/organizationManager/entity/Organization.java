package adtec.organizationManager.entity;

/**
 * Organization 机构信息
 * @author maojd
 * @description	Organization Grade bean
 */
public class Organization {

	private Integer orgId;
	private String orgName;
	private String level1;	//第一层机构
	private String level2;	//第二层机构
	private String level3;
	private String level4;
	private String level5;
	private String level6;
	private String level7;
	
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getLevel1() {
		return level1;
	}
	public void setLevel1(String level1) {
		this.level1 = level1;
	}
	public String getLevel2() {
		return level2;
	}
	public void setLevel2(String level2) {
		this.level2 = level2;
	}
	public String getLevel3() {
		return level3;
	}
	public void setLevel3(String level3) {
		this.level3 = level3;
	}
	public String getLevel4() {
		return level4;
	}
	public void setLevel4(String level4) {
		this.level4 = level4;
	}
	public String getLevel5() {
		return level5;
	}
	public void setLevel5(String level5) {
		this.level5 = level5;
	}
	public String getLevel6() {
		return level6;
	}
	public void setLevel6(String level6) {
		this.level6 = level6;
	}
	public String getLevel7() {
		return level7;
	}
	public void setLevel7(String level7) {
		this.level7 = level7;
	}
}
