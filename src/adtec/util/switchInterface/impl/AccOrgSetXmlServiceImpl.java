package adtec.util.switchInterface.impl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import adtec.adtec_message.service.SwitchService;
import adtec.init.ProjectProperty;
import adtec.util.switchInterface.AccOrgSetXmlService;
import adtec.util.switchInterface.SwitchInterfaceService;

public class AccOrgSetXmlServiceImpl implements AccOrgSetXmlService {
	
	private SwitchInterfaceService swtservice;
	//做标记，默认为false,当切换到外部数据库的帐号时，修改为true
	private boolean tag = false;
	
	public SwitchInterfaceService getSwtservice() {
		return swtservice;
	}

	public void setSwtservice(SwitchInterfaceService swtservice) {
		this.swtservice = swtservice;
	}
	
	public boolean isTag() {
		return tag;
	}

	public void setTag(boolean tag) {
		this.tag = tag;
	}

	//获取project.properties中adtec.accorgsetxmlimpl的属性值
	String switchImpl = ProjectProperty.getInstance().get("adtec.accorgsetxmlimpl");
	public String getswitchImpl(){
		if(null == this.switchImpl){
			switchImpl = ProjectProperty.getInstance().get("adtec.accorgsetxmlimpl");
		}
		return this.switchImpl;
	}
	
	@Override
	public SwitchInterfaceService getSwtService() {
		String clazzStr = getswitchImpl();
		
		if(clazzStr!=null && tag == false){
			try {
				//加载类
				Class clazz = Class.forName(clazzStr);
				SwitchInterfaceService obj = (SwitchInterfaceService)clazz.newInstance();
				this.setSwtservice(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.setTag(true);
		}
		return this.swtservice;
	}

}
