package adtec.account.service.impl;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import adtec.account.dao.AccountDao;
import adtec.account.model.Account;
import adtec.account.service.AccountService;
import adtec.account.service.SwitchAccService;
import adtec.accountManager.controller.Md5Util;
import adtec.adtec_message.service.SwitchService;

import adtec.appManager.model.PageModel;
import adtec.init.ProjectProperty;

/**
 * @FileName: AccountServiceImpl
 * 
 * @FileType: Class
 * 
 * @Date: 2014年3月6日 2014年3月6日 18:32:34
 * 
 * @Author: ;lj
 * 
 * @Description: 帐号管理实现类（为发送消息使用）
 * 
 */
@Transactional(propagation=Propagation.REQUIRED)
public class AccountServiceImpl implements AccountService {
	
	private SwitchAccService accservice;
	private boolean tag = false;
	
	public boolean isTag() {
		return tag;
	}

	public void setTag(boolean tag) {
		this.tag = tag;
	}

	public SwitchAccService getAccservice() {
		return accservice;
	}

	public void setAccservice(SwitchAccService accservice) {
		this.accservice = accservice;
	}


	Logger log = Logger.getLogger(AccountServiceImpl.class);
	
	//获取project.
	String switchaccImpl = ProjectProperty.getInstance().get("adtec.switchaccimpl");
	public String getswitchImpl(){
		if(null == this.switchaccImpl){
			switchaccImpl = ProjectProperty.getInstance().get("adtec.switchaccimpl");
		}
		return this.switchaccImpl;
	}
	

	@Override
	public SwitchAccService getaccService() {
		String clazzStr = getswitchImpl();
		
		if(clazzStr!=null && tag == false){
			try {
				Class clazz = Class.forName(clazzStr);
				SwitchAccService obj = (SwitchAccService)clazz.newInstance();
				this.setAccservice(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.setTag(true);
		}
		return this.accservice;
	}

}
