package adtec.appManager.model;

import adtec.init.ProjectProperty;

public class PageModel {

	private int pageNow;

	private int pageSize = 10;

	private int count;

	private int pageUp;

	private int pageDown;

	private int pageCount;
	
	private int pageJump;
	
	private PageModel pageModel;
	
	
	
	
	public int getPageJump() {
		return pageJump;
	}

	public void setPageJump(int pageJump) {
		this.pageJump = pageJump;
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	// 从web\WEB-INF\config\project\project.properties文件获取到分页数。在getPageSize方法中，给pageSize复制
	private String projectPageSize = ProjectProperty.getInstance().get(
			"project.pageSize");

	public String getProjectPageSize() {
		return projectPageSize;
	}

	public void setProjectPageSize(String projectPageSize) {
		this.projectPageSize = projectPageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public int getTotalPages() {
		return count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
	}

	public int getStart() {
		return (pageNow - 1) * pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageUp() {
		return pageUp;
	}

	public void setPageUp(int pageUp) {
		this.pageUp = pageUp;
	}

	public int getPageDown() {
		return pageDown;
	}

	public void setPageDown(int pageDown) {
		this.pageDown = pageDown;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		// 如果从web\WEB-INF\config\project\project.properties文件获取到分页数 是数字，就获取到 设置成pageSize,否则不操作
		if (projectPageSize!=null && PageModel.isNumOfStr(projectPageSize)) {
			pageSize = new Integer(projectPageSize);
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 验证字符串是否是数字
	 * @param str 要验证的字符串
	 * @return true表示是数字 flase表示不是数字
	 * @author maojd
	 */
	public static boolean isNumOfStr(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
