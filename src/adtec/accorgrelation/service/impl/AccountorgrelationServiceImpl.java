package adtec.accorgrelation.service.impl;

import java.util.ArrayList;
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

import adtec.accorgrelation.dao.AccountorgrelationDao;
import adtec.accorgrelation.model.Accountorgrelation;
import adtec.accorgrelation.service.AccountorgrelationService;
import adtec.accorgrelation.service.SwitchAccOrgRelService;
import adtec.account.model.Account;
import adtec.account.service.SwitchAccService;
import adtec.appManager.model.PageModel;
import adtec.init.ProjectProperty;
import adtec.organizationManager.dao.OrganizationDao;
import adtec.organizationManager.entity.Organization;

/**
 * @FileName: AccountorgrelationServiceImpl
 * 
 * @FileType: Class
 * 
 * @Date: 2014年3月10日 15:20:48
 * 
 * @Author: ;lj
 * 
 * @Description: 帐号机构关系实现类
 * 
 */
@Transactional(propagation=Propagation.REQUIRED)
public class AccountorgrelationServiceImpl implements AccountorgrelationService {
	
	private SwitchAccOrgRelService accorgservice;
	private boolean tag = false;
	
	public SwitchAccOrgRelService getAccorgservice() {
		return accorgservice;
	}

	public void setAccorgservice(SwitchAccOrgRelService accorgservice) {
		this.accorgservice = accorgservice;
	}

	public boolean isTag() {
		return tag;
	}

	public void setTag(boolean tag) {
		this.tag = tag;
	}
	

	Logger log = Logger.getLogger(AccountorgrelationServiceImpl.class);
	
	//获取project.
		String switchaccorgImpl = ProjectProperty.getInstance().get("adtec.switchaccorgimpl");
		public String getswitchImpl(){
			if(null == this.switchaccorgImpl){
				switchaccorgImpl = ProjectProperty.getInstance().get("adtec.switchaccorgimpl");
			}
			return this.switchaccorgImpl;
		}
		
	@Override
	public SwitchAccOrgRelService getaccService() {
		String clazzStr = getswitchImpl();
		
		if(clazzStr!=null && tag == false){
			try {
				Class clazz = Class.forName(clazzStr);
				SwitchAccOrgRelService obj = (SwitchAccOrgRelService)clazz.newInstance();
				this.setAccorgservice(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.setTag(true);
		}
		return accorgservice;
	}
}
