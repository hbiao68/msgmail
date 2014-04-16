package adtec.privilege.model;

/**
 * 分页的model类
 * @author maojd
 * @description 属性名 为了与easyUI默认的属性名一样，control使用的时候可以自动封装成page对象。所以并没有使用pageNoew ,pageSize等名
 */
public class Page {

	protected int page = 1;//当前页
	protected int rows = 5;//每页的数量  
	protected int count;	//总数
	protected int start;
	protected String order;//	asc升序   desc降序
	protected String sort;//username userid等字段名

	public Page() {
	}
	public Page(int page, int rows) {
		this.page = page;
		this.rows = rows;
	}
	
	public Page(int page, int rows, String order, String sort) {
		this.page = page;
		this.rows = rows;
		this.order = order;
		this.sort = sort;
	}
	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public int getTotalPages() {
		return count % rows == 0 ? count / rows : count / rows + 1;
	}

	public int getStart() {
		start = (page - 1) * rows;
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
	
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

}
