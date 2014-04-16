package adtec.plugin.myplugin;

import javax.servlet.ServletContext;

import adtec.organizationManager.service.OrganizationService;
import adtec.plugin.MessageInterceptor;

public class Second extends MessageInterceptor {

	public Second(ServletContext servletContext) {
		super(servletContext);
	}

	@Override
	public String handle(String xmlMsg) {
		//处理xmlMsg
		if(xmlMsg == null){
			xmlMsg = "";
		}else{
			xmlMsg = xmlMsg + " : Second";
		}
		
		try {
			OrganizationService orgServiceBean = (OrganizationService)this.getApplicationContext().getBean("orgServiceBean"); 
			java.util.List list = (java.util.List)orgServiceBean.queryAllcateName();
			System.out.println("Second : " + list.size());
//			log.info(list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//判断是否还有下一个拦截器链，这个是系统动态给的，自己不用关心，所有插件的代码都可以这么写
		if(this.getNextInterceptor() != null){
			//这里实际上是一个递归的算法
			xmlMsg = this.getNextInterceptor().handle(xmlMsg);
		}
		
		return xmlMsg;
	}

}
