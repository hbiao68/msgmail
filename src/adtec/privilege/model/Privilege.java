package adtec.privilege.model;

/**
 * 权限表
 * @author huangbiao
 *
 */
public class Privilege extends Page{
	
	private String privilegeid;//主键ID
	private String resid;//资源id
	/**
	01 添加
	02 修改
	03 删除
	04 查看
	05 打印
	 */
	private String actionType;  //操作类型主键
	private boolean actionValue;//action对应的值
	private Resource resource;// 资源
	private Type type;//操作类型对象
	//private Page page;//分页对象
	
	/*public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}*/
	public String getPrivilegeid() {
		return privilegeid;
	}
	public void setPrivilegeid(String privilegeid) {
		this.privilegeid = privilegeid;
	}
	public String getResid() {
		return resid;
	}
	public void setResid(String resid) {
		this.resid = resid;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public boolean isActionValue() {
		return actionValue;
	}
	public void setActionValue(boolean actionValue) {
		this.actionValue = actionValue;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Privilege() {
	}
	public Privilege(String privilegeid, String resid, String actionType,
			boolean actionValue, Resource resource, Type type) {
		this.privilegeid = privilegeid;
		this.resid = resid;
		this.actionType = actionType;
		this.actionValue = actionValue;
		this.resource = resource;
		this.type = type;
	}
	
}
