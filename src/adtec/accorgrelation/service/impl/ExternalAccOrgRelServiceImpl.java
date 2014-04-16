package adtec.accorgrelation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.accorgrelation.dao.AccountorgrelationDao;
import adtec.accorgrelation.model.Accountorgrelation;
import adtec.accorgrelation.service.SwitchAccOrgRelService;
import adtec.account.model.Account;
import adtec.appManager.model.PageModel;
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
public class ExternalAccOrgRelServiceImpl implements SwitchAccOrgRelService {
	
	Logger log = Logger.getLogger(ExternalAccOrgRelServiceImpl.class);
	
	
	/**
	 * 将帐号添入到机构下
	 */
	@Override
	public boolean insertAccountorgrelation(
			Accountorgrelation accountorgrelation) throws Exception {
		return true;
	}
	
	/**
	 * 删除帐号和机构的关系
	 */
	@Override
	public boolean deleteAccountorgrelation(int relationid) throws Exception {
		return true;
	}
	
	/**
	 * 修改帐号所在机构
	 */
	@Override
	public boolean updateAccountorgrelation(
			Accountorgrelation accountorgrelation) throws Exception {
		return true;
	}
	
	@Override
	public Accountorgrelation queryAccOrgRelByAccOrgRel(
			Accountorgrelation accountorgrelation) throws Exception {
		return null;
	}
	
	/**
	 * 单一实例查询
	 */
	@Override
	public Accountorgrelation findAccOrgRelByRelationid(int relationid)
			throws Exception {
		return null;
	}
	
	/**
	 * 批量删除数据
	 */
	
	@Override
	public boolean batchdelete(Accountorgrelation accountorgrelation)
			throws Exception {
		return true;
	}
}
