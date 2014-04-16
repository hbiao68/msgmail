package adtec.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 显示当前JSP页面
 * @author huangbiao
 */
public class LocalFileName extends TagSupport {

	/**
	 * 是否显示内容
	 * true 显示
	 * false 不显示
	 */
	private String isShow;
	
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	@Override
	public int doStartTag() throws JspException {
		
		if("false".equals(isShow)){
			return Tag.SKIP_BODY;
		}
		
		JspWriter jspWriter = this.pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
		String   uri= request.getRequestURI();   
		uri=uri.substring(uri.lastIndexOf("/")+1);
		//＜meta name="currentPageName" contect=""＞
		try {
			jspWriter.println("<meta name=\"currentPageName\" contect=\""+uri+"\">");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.EVAL_BODY_INCLUDE;
	}
}
