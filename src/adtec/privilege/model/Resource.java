package adtec.privilege.model;

public class Resource extends Page{
	
	private String resid;//资源的唯一标示
	private String resname;//资源的名称
	private String commen;//备注
	//private Page page;//分页对象
	/*public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}*/
	public String getResid() {
		return resid;
	}
	public void setResid(String resid) {
		this.resid = resid;
	}
	public String getResname() {
		return resname;
	}
	public void setResname(String resname) {
		this.resname = resname;
	}
	public String getCommen() {
		return commen;
	}
	public void setCommen(String commen) {
		this.commen = commen;
	}
	public Resource() {
	}
	public Resource(String resid, String resname, String commen) {
		this.resid = resid;
		this.resname = resname;
		this.commen = commen;
	}
	
		
	
}
