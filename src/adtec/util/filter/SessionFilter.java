package adtec.util.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SessionFilter extends OncePerRequestFilter {

	private static Set<String> exclude = new HashSet<String>();

	@Override
	protected void initFilterBean() throws ServletException {
		// TODO Auto-generated method stub
		super.initFilterBean();

		String[] notFilter = new String[] { "login.html",
				"/myspring/login/check.do", "/myspring/login/insert.do",
				"/myspring/login/insertAction.do","/myspring/login/getUserName.do","/myspring/login/checkUserName.do" };
		for (String uri : notFilter) {
			exclude.add(uri);
		}
	}

	/**
	 * 添加不用过滤的uri
	 * @param uri
	 */
	public static void addExculde(String uri) {
		if (null != uri && !"".equals(uri)) {
			exclude.add(uri);
		}
	}

	/**
	 * 删除不用过滤的uri
	 * @param uri
	 */
	public static void delExculde(String uri) {
		if (null != uri && !"".equals(uri)) {
			exclude.remove(uri);
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 不过滤的uri
//		String[] notFilter = new String[] { "login.html",
//				"/myspring/login/check.do", "/myspring/login/insert.do",
//				"/myspring/login/insertAction.do", };

		// 请求的uri
		String uri = request.getRequestURI();

		// uri中包含background时才进行过滤
		if (uri.indexOf("main") != -1 || uri.indexOf(".do") != -1) {
			// 是否过滤
			if (uri.equals("/myspring/main.html")) {
				boolean doFilter = true;
				for (String s : exclude) {
					if (uri.indexOf(s) != -1) {
						// 如果uri中包含不过滤的uri，则不进行过滤
						doFilter = false;
						break;
					}
				}
				if (doFilter) {
					// 执行过滤
					// 从session中获取登录者实体
					Object obj = request.getSession().getAttribute("user");
					if (null == obj) {
						// 如果session中不存在登录者实体，则弹出框提示重新登录
						// 设置request和response的字符集，防止乱码
						request.setCharacterEncoding("UTF-8");
						response.setCharacterEncoding("UTF-8");
						PrintWriter out = response.getWriter();
						String loginPage = "/myspring/login.html";
						StringBuilder builder = new StringBuilder();
						builder.append("<script type=\"text/javascript\">");
						builder.append("window.location.href='");
						builder.append(loginPage);
						builder.append("';");
						builder.append("</script>");
						out.print(builder.toString());
					} else {
						// 如果session中存在登录者实体，则继续
						filterChain.doFilter(request, response);
					}
				} else {
					// 如果不执行过滤，则继续
					filterChain.doFilter(request, response);
				}
			} else {
				boolean doFilter = true;
				for (String s : exclude) {
					if (uri.indexOf(s) != -1) {
						// 如果uri中包含不过滤的uri，则不进行过滤
						doFilter = false;
						break;
					}
				}
				if (doFilter) {
					// 执行过滤
					// 从session中获取登录者实体
					Object obj = request.getSession().getAttribute("user");
					if (null == obj) {
						// 如果session中不存在登录者实体，则弹出框提示重新登录
						// 设置request和response的字符集，防止乱码
						request.setCharacterEncoding("UTF-8");
						response.setCharacterEncoding("UTF-8");
						PrintWriter out = response.getWriter();
						String loginPage = "../login.html";
						StringBuilder builder = new StringBuilder();
						builder.append("<script type=\"text/javascript\">");
						builder.append("window.location.href='");
						builder.append(loginPage);
						builder.append("';");
						builder.append("</script>");
						out.print(builder.toString());
					} else {
						// 如果session中存在登录者实体，则继续
						filterChain.doFilter(request, response);
					}
				} else {
					// 如果不执行过滤，则继续
					filterChain.doFilter(request, response);
				}
			}
		} else {
			// 如果uri中不包含background，则继续
			filterChain.doFilter(request, response);
		}
	}
}
