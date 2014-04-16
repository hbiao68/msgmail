package adtec.plugin;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 对发送过来的XML数据进行层层过滤，最终返回自己需要的XML数据
 * @author huangbiao
 */
public abstract class MessageInterceptor {
	
	//下一个拦截器处理对象
	MessageInterceptor nextInterceptor;
	//拦截器的名称
	String name;
	//目的是为了获取web容器中的applicationContext对象（能获取spring配置的任意对象）,
	ServletContext servletContext;

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public MessageInterceptor getNextInterceptor() {
		return nextInterceptor;
	}

	public void setNextInterceptor(MessageInterceptor nextInterceptor) {
		this.nextInterceptor = nextInterceptor;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public MessageInterceptor(ServletContext servletContext){
		this.servletContext = servletContext;
	}

	/**
	 * 将请求传递给下一个拦截器类处理
	 * @param request 携带需要处理请求的信息
	 * @param intercpetor 指定给下一个拦截器处理
	 * @return 返回已经处理之后的请求结果
	 */
	public abstract String handle(String xmlMsg);
	
	public ApplicationContext getApplicationContext(){
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.servletContext); 
		return applicationContext;
	}
	
}
