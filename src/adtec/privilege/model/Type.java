package adtec.privilege.model;

/**
 * 操作类型实体类
 * @author maojd
 * @date 9:31 2014/3/3
 */
public class Type extends Page{

	private String typeid;//类型id
	private String typename;//操作类型名
	private String common;//备注
	//private Page page;//分页对象，最为Type的一个属性
	
	/*public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}*/
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
}
