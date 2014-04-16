package adtec.accorgrelation.model;

import adtec.appManager.model.PageModel;
import adtec.organizationManager.entity.Organization;
import java.util.List;

/**
 * @author admin
 *
 */
public class Accountorgrelation {
	
	private int relationid;   //帐号机构关系表的主键
	
	private int orgId;   //外键，机构表的主键
	
	private String username;  //外键，帐号表的主键
	
	private Accountorgrelation accountorgrelation;

	private int start;   //分页用，从哪条数据开始
	
	private int pageSize;   //分页用，每页显示多少条数据
	
	private PageModel pageModel;
	
	private Organization organization;
	
	private List<Integer> list;   //里面放所有的orgId
	
	private List<Accountorgrelation> accorgList;   //里面放所有查询出来的数据
	
	private String orgName ;  //查询条件的orgName
	
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public List<Accountorgrelation> getAccorgList() {
		return accorgList;
	}

	public void setAccorgList(List<Accountorgrelation> accorgList) {
		this.accorgList = accorgList;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	public Accountorgrelation getAccountorgrelation() {
		return accountorgrelation;
	}

	public void setAccountorgrelation(Accountorgrelation accountorgrelation) {
		this.accountorgrelation = accountorgrelation;
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

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	

}
