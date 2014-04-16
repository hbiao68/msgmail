package adtec.adtec_message.service.impl;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import adtec.adtec_message.service.SwitchService;
import adtec.adtec_message.service.Adtec_messageService;
import adtec.init.AjmessageProperty;
import adtec.init.ProjectProperty;

public class Adtec_messageServiceImpl implements Adtec_messageService{

	private SwitchService msgservice;
	private boolean tag = false;

	public boolean isTag() {
		return tag;
	}

	public void setTag(boolean tag) {
		this.tag = tag;
	}


	public void setMsgservice(SwitchService msgservice) {
		this.msgservice = msgservice;
	}
	

	public SwitchService getMsgservice() {
		return msgservice;
	}


	//获取project.
	String switchImpl = ProjectProperty.getInstance().get("adtec.switchimpl");
	public String getswitchImpl(){
		if(null == this.switchImpl){
			switchImpl = ProjectProperty.getInstance().get("adtec.switchimpl");
		}
		return this.switchImpl;
	}
	

	@Override
	public SwitchService getService() {
		String clazzStr = getswitchImpl();
		
		if(clazzStr!=null && tag == false){
			try {
				Class clazz = Class.forName(clazzStr);
				SwitchService obj = (SwitchService)clazz.newInstance();
				this.setMsgservice(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.setTag(true);
		}
		
		return this.msgservice;
	}
}


