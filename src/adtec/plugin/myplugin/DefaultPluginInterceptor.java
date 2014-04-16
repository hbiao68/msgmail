package adtec.plugin.myplugin;

import javax.servlet.ServletContext;
import adtec.organizationManager.service.OrganizationService;
import adtec.plugin.MessageInterceptor;

public class DefaultPluginInterceptor extends MessageInterceptor {

	//构造方法必须要有用户的请求对象

	public DefaultPluginInterceptor(ServletContext servletContext) {
		super(servletContext);
	}

	@Override
	public String handle(String xmlMsg) {
		//处理xmlMsg
		if(xmlMsg == null){
			xmlMsg = "";
		}else{
			
//			log.info(orgServiceBean);
			try {
				OrganizationService orgServiceBean = (OrganizationService)this.getApplicationContext().getBean("orgServiceBean"); 
				java.util.List list = (java.util.List)orgServiceBean.queryAllcateName();
				System.out.println("DefaultPluginInterceptor : " + list.size());
//				log.info(list.size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			xmlMsg = xmlMsg + " : DefaultPluginInterceptor";
			
		}
		
//		ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(ServletContext sc); 
//		ac1.getBean("beanId"); 
		
		//判断是否还有下一个拦截器链，这个是系统动态给的，自己不用关心，所有插件的代码都可以这么写
		if(this.getNextInterceptor() != null){
			xmlMsg = this.getNextInterceptor().handle(xmlMsg);
		}
		
		return xmlMsg;
	}

}
