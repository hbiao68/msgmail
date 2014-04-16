package adtec.tag;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 分页标签
 * @author huangbiao
 */
public class PageTag extends TagSupport {

	private String url; // 链接地址
	private int curpage;// 当前页
	private int pagesize; // 页大小
	private int totalLines; // 总记录条数

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCurpage() {
		return curpage;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalLines() {
		return totalLines;
	}

	public void setTotalLines(int totalLines) {
		this.totalLines = totalLines;
	}

	/**
	 * 计算总页数
	 * @return
	 */
	private int totalPages() {
		int result = totalLines % pagesize == 0 ? totalLines / pagesize : totalLines / pagesize + 1;
		//如果传递的totalLines为0则让其为1
		return result == 0 ? 1 : result;
	}

	@Override
	public int doStartTag() throws JspException {

		JspWriter out = this.pageContext.getOut();
		// {0}:url, {1}:curpage, {2}:pagesize, {3}:第一页 上一页 下一页 最后一页
//		String link = "<a href='{0}?curpage={1}&pagesize={2}'>{3}</a>";
//		String link = "<A href=\"#\" onclick=\"return onSelect('${pageModel.pageUp }')\"><font id=\"pageUp\">上一页</font></A>";
		String link = "<A name=\"pageTag\" href=\"{0}?pageNow={1}\">{2}</A>";

		if (curpage == 0){
			curpage = 1;
		}

		if (pagesize == 0){
			pagesize = 5;
		}

		int totalPages = this.totalPages();
		if (curpage > totalPages){
			curpage = totalPages;
		}

		if (curpage < 1){
			curpage = 1;
		}

		String first = MessageFormat.format(link, url, "1", "第一页");
		String last = MessageFormat.format(link, url, String.valueOf(totalPages), "最后一页");
		//maojd update date:2014/02/17 18:08
		
		String previous = null;
		if (curpage <= 1) {
			previous = "上一页";
			first = "第一页";
		} else {
			previous = MessageFormat.format(link, url, String.valueOf(curpage - 1), "上一页");
		}

		String next = null;
		if (curpage >= totalPages) {
			next = "下一页";
			last = "最后一页";
		} else {
			next = MessageFormat.format(link, url, String.valueOf(curpage + 1), "下一页");
		}

		

		//${pageModel.pageNow }  ${pageModel.pageCount }  ${pageModel.pageUp} ${pageModel.pageDown}

		try {
			//String html = "{0} {1} {2} {3} 当前第{4}页 共{5}页 每页显示{6}条 共有{7}条";
			//html = MessageFormat.format(html, first, previous, next, last, String.valueOf(curpage), String.valueOf(totalPages), String.valueOf(pagesize), String.valueOf(totalLines));
			//{0}第一页  {1}上一页  {2}currentPage/{3}totalPage {4}下一页 {5}最后一页
			String outStr = "<div class=\"list_btline\"><div class=\"f_size selbar_bt barspace2\" style=\"height: 24px; padding-top: 3px\">" +
					"<DIV class=\"right\" style=\"padding-right:20px;\">" +
					"{0}&nbsp;" +
					"{1}&nbsp;" +
					"{2}/{3}页&nbsp;" +
					"{4}&nbsp;" +
					"{5}&nbsp;" +
					"<input type=\"text\" style=\"width: 20px;\" name=\"pageJump\" id=\"pageJump\"><A title=\"跳转到指定一页\" href=\"#\" " +
					"onclick=\"return onJumpSelect()\">跳转</A></DIV></div></div>";
			outStr = MessageFormat.format(outStr, first,previous, String.valueOf(curpage),String.valueOf(totalPages), next, last);
			out.println(outStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
}
