package adtec.organizationManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.accountManager.dao.Ta_Account_cateNameDao;
import adtec.categoryManager.dao.Ta_categoryDao;
import adtec.categoryManager.model.Ta_category;
import adtec.organizationManager.dao.OrganizationDao;
import adtec.organizationManager.entity.Organization;
import adtec.organizationManager.service.OrganizationService;

/**
 * 机构管理
 * @author maojd
 * @description 机构管理的service实现类
 */
@Transactional(propagation = Propagation.REQUIRED)
public class OrganizationServiceImpl implements OrganizationService{

	Logger log = Logger.getLogger(OrganizationServiceImpl.class);
	private OrganizationDao orgDao;
	private Ta_categoryDao ta_categoryDao;
	private Ta_Account_cateNameDao ta_Account_cateNameDao = null;

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
	
	public void setTa_Account_cateNameDao(
			Ta_Account_cateNameDao ta_Account_cateNameDao) {
		this.ta_Account_cateNameDao = ta_Account_cateNameDao;
	}

	public Ta_Account_cateNameDao getTa_Account_cateNameDao() {
		return ta_Account_cateNameDao;
	}

	//查询level.
	@Override
	public List<Organization> queryOrgLevelByOrg (Organization org) throws Exception{

		
		List<Organization> list = new ArrayList<Organization>();
		try {
			list = orgDao.queryOrgLevelByOrg(org);
		} catch (Exception e) {
			log.error("通过机构查询该下一等级的机构出错");
		}
		log.debug("queryOrgLevelByOrg " + org + "  ok");
		return list;
	}

	//添加机构
	@Override
	public boolean addOrg(Organization org) throws Exception{
		log.debug("addOrg"+org);
		try {
			orgDao.addOrg(org);
		} catch (Exception e) {
			log.error("添加机构错误addOrg " + org + "  error");
			throw e;
		}
		log.debug("添加机构成功 addOrg" + org + "  ok");
		return true;
	}

	//通过id删除
	@Override
	public boolean deleteOrgByOrgId(int orgId) throws Exception{
		
		try {
			orgDao.deleteOrgByOrgId(orgId);
		} catch (Exception e) {
			log.error("deleteOrgByOrgId " + orgId + "  error  通过orgId删除机构失败");
			throw e;
		}
		log.debug("deleteOrg"+orgId+"  ok");
		return true;
	}

	//通过id查询一条
	@Override
	public Organization queryOrgById(int orgId) throws Exception{
		log.debug("queryOrgById"+orgId);
		Organization org = new Organization();
		List<Organization> list = new ArrayList<Organization>();
		try {
			list = orgDao.queryOrgById(orgId);
			if(list.size()>0){
				org = (Organization) list.get(0);
			}else{
				//org = new Organization();
			}
		} catch (Exception e) {
			log.error("queryOrgById error 通过id查询机构出错");
			throw e;
		}
		log.debug("queryOrgById"+orgId+"  ok" + org);
		return org;
	}

	//修改
	@Override
	public void updateOrg(Organization org) throws Exception {
		
		try {
			orgDao.updateOrg(org);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("updateOrg"+org+"  error 修改机构出错");
			throw e;
		}
		log.debug("updateOrg"+org+"  ok 修改机构成功");
	}

	//清空数据
	@Override
	public boolean deleteAll() throws Exception{
		
		try {
			orgDao.deleteAll();
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("orgDao.deleteAll  error 清除机构失败");
			throw e;
		}
		log.debug("orgDao.deleteAll  ok		清除机构成功");
		return true;
		
	}

	//批量添加
	@Override
	public void batchInsertOrg(List<Organization> list) throws Exception {
		
		log.debug("batchInsertOrg"+list);
		try {
			//this.deleteAll();
			orgDao.batchInsertOrg(list);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("");
			throw e;
		}
		
	}

	//查询所有机构
	@Override
	public List<Organization> queryAllOrg() throws Exception{
		
		log.debug("queryAllOrg");
		List<Organization> list = new ArrayList<Organization>();
		try {
			list = orgDao.queryAllOrg();
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("queryAllOrg()  error  查询所有机构出错");
			throw e;
		}
		return list;
	}

	//查询全国 这条记录
	@Override
	public Organization queryOrgRoot() throws Exception{
		
		log.debug("queryOrgRoot");
		List<Organization> list = new ArrayList<Organization>();
		Organization org = new Organization();
		try {
			list = orgDao.queryOrgRoot();
			if(list.size()>0){
				org = (Organization) list.get(0);
			}else{
				
			}
		} catch (Exception e) {
			log.error("queryOrgRoot  error   查询全国机构出错");
			throw e;
		}
		return org;
	}

	//由一个机构查询所有子机构（包括本身）
	@Override
	public List<Organization> querySubOrgListByOrg(Organization org) throws Exception{
		
		List<Organization> list = new ArrayList<Organization>();
		try {
			list = orgDao.querySubOrgListByOrg(org);
		} catch (Exception e) {
			log.error("querySubOrgListByOrg  error 通过一个机构查询所有子机构（包括本身）出错");
			throw e;
		}
		return list;
	}

	//查询所有 终端类型
	//@SuppressWarnings("unchecked")
	@Override
	public List<Ta_category> queryAllcateName() throws Exception{
		List<Ta_category> list = new ArrayList<Ta_category>();
		try {
			list = this.ta_categoryDao.queryforcateName();
		} catch (Exception e) {
			log.error("queryAllcateName error 查询所有 终端类型出错");
			throw e;
		}
		return list;
	}

	@Override
	public int queryCountByOrgId(Map<String, String> map) throws Exception{
		int count = 0;
		try {
			count = ta_Account_cateNameDao.queryCountByOrgId(map);
		} catch (Exception e) {
			log.error("ta_Account_cateNameDao.queryCountByOrgId  error  查询机构下账号数量出错");
			throw e;
		}
		return count;
	}

	
}
