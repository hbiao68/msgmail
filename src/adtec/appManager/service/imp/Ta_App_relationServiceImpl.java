package adtec.appManager.service.imp;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import adtec.accountManager.model.Ta_Account_cateName;
import adtec.appManager.dao.Ta_AppDao;

import adtec.appManager.model.Ta_App;
import adtec.appManager.model.Ta_App_relation;
import adtec.appManager.dao.Ta_App_relationDao;
import adtec.appManager.service.Ta_App_relationService;

import adtec.accountManager.dao.Ta_Account_cateNameDao;

import adtec.categoryManager.dao.Ta_categoryDao;

import adtec.organizationManager.dao.OrganizationDao;
import adtec.ta_org_relationManager.dao.Ta_org_relationDao;
import adtec.ta_org_relationManager.model.Ta_org_relation;
import adtec.categoryManager.model.Ta_category;

/**
 * @FileName: Ta_App_relationServiceImpl
 * 
 * @FileType: Class
 * 
 * @Date: 2013年10月28日
 * 
 * @Author: 李季
 * 
 * @Description: 业务开通实现类
 * 
 */
@Transactional(propagation=Propagation.REQUIRED)
public class Ta_App_relationServiceImpl implements Ta_App_relationService {

	Logger log = Logger.getLogger(Ta_App_relationServiceImpl.class);
	
	private Ta_App_relationDao ta_App_relationDao = null;

	private Ta_AppDao ta_AppDao = null;

	private Ta_Account_cateNameDao ta_Account_cateNameDao = null;

	private Ta_categoryDao ta_categoryDao = null;

	private OrganizationDao orgDao = null;

	private Ta_org_relationDao ta_org_relationDao = null;
	
	
	public Ta_org_relationDao getTa_org_relationDao() {
		return ta_org_relationDao;
	}

	public void setTa_org_relationDao(Ta_org_relationDao ta_org_relationDao) {
		this.ta_org_relationDao = ta_org_relationDao;
	}

	public OrganizationDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(OrganizationDao orgDao) {
		this.orgDao = orgDao;
	}

	public Ta_categoryDao getTa_categoryDao() {
		return ta_categoryDao;
	}

	public void setTa_categoryDao(Ta_categoryDao ta_categoryDao) {
		this.ta_categoryDao = ta_categoryDao;
	}

	public Ta_Account_cateNameDao getTa_Account_cateNameDao() {
		return ta_Account_cateNameDao;
	}

	public void setTa_Account_cateNameDao(
			Ta_Account_cateNameDao ta_Account_cateNameDao) {
		this.ta_Account_cateNameDao = ta_Account_cateNameDao;
	}

	public Ta_AppDao getTa_AppDao() {
		return ta_AppDao;
	}

	public void setTa_AppDao(Ta_AppDao ta_AppDao) {
		this.ta_AppDao = ta_AppDao;
	}

	public Ta_App_relationDao getTa_App_relationDao() {
		return ta_App_relationDao;
	}

	public void setTa_App_relationDao(Ta_App_relationDao ta_App_relationDao) {
		this.ta_App_relationDao = ta_App_relationDao;
	}

	/**
	 * 添加方法 添加业务
	 */

	public boolean add(List<Ta_App_relation> list)throws Exception {
		try {
		//	this.ta_App_relationDao.insertforTa_App_relation(ta_App_relation);
			this.ta_App_relationDao.batchInsert(list);
			log.debug("业务开通成功！");
			return true;
		} catch (Exception e) {
			log.error("业务开通失败！");
			throw e;
		}
	}

	/**
	 *  查询出所有业务为业务开通使用
	 */
	public List<Ta_App> queryforRelation(String ta_id) {

		return this.ta_AppDao.queryforRelation(ta_id);
	}

	/**
	 * 查询方法 分页查询某一端点下所有帐号开通业务的情况
	 */
	public List<Ta_App_relation> queryforTa_App_relation(Ta_App_relation ta_App_relation,
			Ta_Account_cateName ta_Account_cateName, String cateName) {
		return this.ta_App_relationDao.queryforTa_App_relation(ta_App_relation,
				ta_Account_cateName, cateName);
	};

	/**
	 * 查询方法 为了检验做查询
	 */
	public int queryforcheck(Ta_Account_cateName ta_Account_cateName,
			String cateName) {
		return ta_Account_cateNameDao.queryforcheckaccountNameByaccountName(cateName, ta_Account_cateName);
	}

	/**
	 * 查询方法 对单一帐号详细信息进行查询
	 */
	public List<Ta_App_relation> findTa_App_relationbyTa_App_relation(Ta_App_relation ta_App_relation) {
		return this.ta_App_relationDao.findTa_App_relationbyTa_App_relation(ta_App_relation);
	}

	/**
	 * 删除方法 删除帐号信息
	 */
	public boolean deleteTa_App_relationByaccountName(Ta_App_relation ta_App_relation)throws Exception {

		try {
			this.ta_App_relationDao.deleteTa_App_relationByaccountName(ta_App_relation);
			log.debug("已开通的业务删除成功！");
			return true;
		} catch (Exception e) {
			log.error("已开通的业务删除失败！");
			throw e;
		}
	}

	/**
	 * 修改方法 修改某一帐号所开通业务的信息
	 */
	public boolean update(List<Ta_App_relation> list)throws Exception {

		try {
			Ta_App_relation ta_App_relation = list.get(0);
			this.ta_App_relationDao.deleteTa_App_relationByaccountName(ta_App_relation);
			this.ta_App_relationDao.batchInsert(list);
			log.debug("开通的业务修改成功！");
			return true;
		} catch (Exception e) {
			log.error("开通的业务修改失败！");
			throw e;
		}
	}
	
	/**
	 * 查询出ta_id(也就是端点id)为端点管理的选择表做数据
	 * 
	 * @return
	 */
	public List<Ta_category> queryforcateName() {
		return this.ta_categoryDao.queryforcateName();
	}

	/**
	 * 查询方法 通过主键（ta_id）获取cateName
	 */
	public Ta_category findcateNameByta_id(String ta_id) {

		return this.ta_categoryDao.findcateNameByta_id(ta_id);
	}

	/**
	 * 校验新开通业务的帐号是否重复
	 */
	public int queryforCheckNewAccoutNameByAccountName(Ta_App_relation ta_App_relation) {

		return this.ta_App_relationDao.queryforCheckNewAccoutNameByAccountName(ta_App_relation);
	}

	/**
	 * 删除方法 删除某个机构下面已经开通业务帐号的信息
	 */
	public boolean deleteforOrg(int appid, int orgId, String cateName) {
		this.ta_App_relationDao.deleteforOrg(appid, orgId, cateName);
		return true;
	};

	/**
	 * 查询方法 查询某一机构下面所有的帐号
	 */
	public List<Ta_Account_cateName> queryforOrg(String cateName, int orgId) {

		return this.ta_Account_cateNameDao.queryforOrg(cateName, orgId);
	}

	/**
	 * 添加方法 为某个机构下面所有的帐号开通业务
	 */
	@Override
	public boolean insertforOrg(List<Ta_App_relation> list) {

		this.ta_App_relationDao.insertforOrg(list);
		return true;
	}

	/**
	 * 删除方法 批量删除数据
	 */
	public boolean batchdelete(String accountName, String cateName) {
		this.ta_App_relationDao.batchdelete(accountName, cateName);
		return true;
	}

	/**
	 * 查询方法 查询某一端点下所有帐号开通业务的情况
	 */
	public List<Ta_App_relation> queryTa_App_relationforCount(Ta_App_relation ta_App_relation,
			Ta_Account_cateName ta_Account_cateName, String cateName) {
		return this.ta_App_relationDao.queryTa_App_relationforCount(ta_App_relation,
				ta_Account_cateName, cateName);
	}

	
	/**
	 * 查询要开通的账号是否存在
	 */
	public int queryAccExist(Ta_App_relation ta_App_relation) {
		int count = 0;
		try {
			count = ta_App_relationDao.queryAccExist(ta_App_relation);
		} catch (Exception e) {
			log.error("查询要开通的账号是否存在失败！");
		}
		return count;
	};
	
	/**
	 * 通过帐号来查询该帐号所属机构
	 */
	@Override
	public String findOrgIdByAccount(String cateName, String accountName) {
		return this.ta_Account_cateNameDao.findOrgIdByAccount(cateName, accountName);
	}
	
	/**
	 * 查询该银行下是否开通了某些业务
	 */
	@Override
	public int queryAppCountByOrgRelation(Ta_org_relation ta_org_relation) {
		return this.ta_org_relationDao.queryAppCountByOrgRelation(ta_org_relation);
	}
}
