package adtec.privilege.model;

import java.io.Serializable;


/**
 * 菜单栏位 model类
 * @author maojd
 * @description 每添加一条记录，消息邮局的左侧菜单栏添加一条导航功能
 * @date 13:25 2014/4/1
 */
public class Column extends Page implements Serializable{
	/**
	 * 栏位要保存到session中，素有序列化一下
	 */
	private static final long serialVersionUID = 6441541365198076836L;
	private String columnId;//栏位主键id
	private String columnName;//栏位名
	private String columnUrl;//点击对应的mapping.发送到controller的url地址
	private String resid;//资源表的主键 。该栏位属于某个资源
	
	private Resource resource;// 资源实体。便于获取资源名等信息
	//private Page page;//权限管理的分页使用
	
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getResid() {
		return resid;
	}
	public void setResid(String resid) {
		this.resid = resid;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnUrl() {
		return columnUrl;
	}
	public void setColumnUrl(String columnUrl) {
		this.columnUrl = columnUrl;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	/*public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}*/
	public Column() {
	}
	
	public Column(String columnId, String columnName,
			String columnUrl, String resid, Resource resource) {
		this.columnId = columnId;
		this.columnName = columnName;
		this.columnUrl = columnUrl;
		this.resid = resid;
		this.resource = resource;
	}
	
}
