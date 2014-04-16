package adtec.ta_org_relationManager.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.appManager.dao.Ta_AppDao;
import adtec.appManager.model.Ta_App;
import adtec.categoryManager.dao.Ta_categoryDao;
import adtec.categoryManager.model.Ta_category;
import adtec.organizationManager.dao.OrganizationDao;
import adtec.organizationManager.entity.Organization;
import adtec.ta_org_relationManager.dao.Ta_org_relationDao;

import adtec.ta_org_relationManager.model.Ta_org_relation;
import adtec.ta_org_relationManager.service.Ta_org_relationService;
import adtec.userManager.service.imp.UserManagerServiceImpl;


/**
 * @FileName: Ta_org_relationServiceImpl
 * 
 * @FileType: Class
 * 
 * @Date: 2013年12月06日
 * 
 * @Author: 李季
 * 
 * @Description: 机构业务开通实现类
 * 
 */
@Transactional(propagation=Propagation.REQUIRED)
public class Ta_org_relationServiceImpl implements Ta_org_relationService {

	
	Logger log = Logger.getLogger(Ta_org_relationServiceImpl.class);
	
	
	private Ta_org_relationDao ta_org_relationDao = null;
	
	private Ta_AppDao ta_AppDao = null;
	private OrganizationDao orgDao = null;
	private Ta_categoryDao ta_categoryDao = null;
	
	

	public Ta_categoryDao getTa_categoryDao() {
		return ta_categoryDao;
	}

	public void setTa_categoryDao(Ta_categoryDao ta_categoryDao) {
		this.ta_categoryDao = ta_categoryDao;
	}

	public OrganizationDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(OrganizationDao orgDao) {
		this.orgDao = orgDao;
	}

	public Ta_AppDao getTa_AppDao() {
		return ta_AppDao;
	}

	public void setTa_AppDao(Ta_AppDao ta_AppDao) {
		this.ta_AppDao = ta_AppDao;
	}

	public Ta_org_relationDao getTa_org_relationDao() {
		return ta_org_relationDao;
	}

	public void setTa_org_relationDao(Ta_org_relationDao ta_org_relationDao) {
		this.ta_org_relationDao = ta_org_relationDao;
	}

	/**
	 * 添加方法,为机构开通业务
	 */
	@Override
	public boolean batchInsert(Map<String,Object> insOrgId,List<Ta_org_relation> list)throws Exception {
			try {
				//删除机构已经开通的业务
				this.ta_org_relationDao.batchDelete(insOrgId);
				//将所有选中的机构从新开通业务
				this.ta_org_relationDao.batchInsert(list);
				log.debug("机构开通业务成功！");
			} catch (Exception e) {
				log.error("机构开通业务失败！");
				throw e;
			}
			return true;
	}
	
	
	/**
	 *  查询出所有业务为业务开通使用
	 */
	@Override
	public List<Ta_App> queryforRelation(String ta_id) {

		return this.ta_AppDao.queryforRelation(ta_id);
	}
	
	/**
	 * 由一个机构查询所有子机构（包括本身）
	 */
	@Override
	public List<Organization> querySubOrgListByOrg(Organization org) {
		List<Organization> list = new ArrayList<Organization>();
		try {
			list = orgDao.querySubOrgListByOrg(org);
		} catch (Exception e) {
			log.error("获取子机构信息失败");
		}
		return list;
	}
	
	/**
	 * 通过id查询该id的整条机构数据
	 */
	@Override
	public Organization queryOrgById(String orgId) {
		Organization org = new Organization();
		List<Organization> list = new ArrayList<Organization>();
		try {
			list = orgDao.queryOrgById(Integer.parseInt(orgId));
			if(list.size()>0){
				org = (Organization) list.get(0);
			}else{
			}
		} catch (Exception e) {
			log.error("通过id查询该id的整条机构数据失败");
		}
		return org;
	}
	
	/**
	 * 通过应用查询所有的机构
	 */
	@Override
	public List<Ta_org_relation> queryOrgByApp(Map<String,Object> map) {
		return this.ta_org_relationDao.queryOrgByApp(map);
	}
	
	/**
	 * 查询出ta_id(也就是端点id)为端点管理的选择表做数据
	 * 
	 * @return
	 */
	@Override
	public List<Ta_category> queryforcateName() {
		return this.ta_categoryDao.queryforcateName();
	}
	
	/**
	 * 查询出所有的业务
	 */
	@Override
	public List<Ta_App> queryAllAppNameByTa_id(String ta_id) {
		return this.ta_AppDao.queryAllAppNameByTa_id(ta_id);
	}
	
	/**
	 * 通过业务查询所有的机构
	 */
	@Override
	public List<Ta_org_relation> queryAllByApp(Map<String,Object> map) {
		return this.ta_org_relationDao.queryAllByApp(map);
	}
	
	/**
	 *通过appid查询出某一业务的所有信息 
	 */
	@Override
	public Ta_App findAppByAppId(int appid) {
		return this.ta_AppDao.findAppById(appid);
	}
	
	/**
	 * 删除机构已开通的业务
	 */
	@Override
	public boolean batchDelete(Map<String,Object> map)throws Exception {
		try{
			this.ta_org_relationDao.batchDelete(map);
			log.debug("已开通业务删除成功！");
			return true;
		}catch(Exception e){
			log.error("已开通业务删除失败！");
			throw e;
		}
	}
	
}
