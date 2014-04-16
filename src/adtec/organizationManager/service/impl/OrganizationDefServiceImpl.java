package adtec.organizationManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.organizationManager.dao.OrganizationDefDao;
import adtec.organizationManager.entity.OrganizationDef;
import adtec.organizationManager.service.OrganizationDefService;

@Transactional(propagation = Propagation.REQUIRED)
public class OrganizationDefServiceImpl implements OrganizationDefService {

	Logger log = Logger.getLogger(OrganizationDefServiceImpl.class);
	OrganizationDefDao orgDefDao;

	public OrganizationDefDao getOrgDefDao() {
		return orgDefDao;
	}

	public void setOrgDefDao(OrganizationDefDao orgDefDao) {
		this.orgDefDao = orgDefDao;
	}

	// 查询所有
	@Override
	public List<OrganizationDef> queryAllOrgDef() throws Exception {
		List<OrganizationDef> list = new ArrayList<OrganizationDef>();
		try {
			list = orgDefDao.queryAllOrgDef();
		} catch (Exception e) {
			log.error("orgDefDao.queryAllOrgDef()  error");
			throw e;
		}
		log.debug("orgDefDao.queryAllOrgDef()   ok");
		return list;
	}

	// 添加等级 信息
	@Override
	public boolean addOrgDef(OrganizationDef org) throws Exception {
		try {
			orgDefDao.addOrgDef(org);
		} catch (Exception e) {
			log.error("orgDefDao.addOrgDef(org)  error");
			throw e;
		}
		log.debug("orgDefDao.addOrgDef" + org + "    ok");
		return true;
	}

	// 删除
	@Override
	public boolean deleteOrgDefById(int id) throws Exception {

		try {
			orgDefDao.deleteOrgDefById(id);
		} catch (Exception e) {
			log.error("orgDefDao.deleteOrgDefById(id)   error" + "  id is:"
					+ id);
			throw e;
		}
		log.debug("orgDefDao.deleteOrgDefById" + id + "  ok");
		return true;
	}

	// 修改 指定的机构等级信息
	@Override
	public boolean updateOrgDef(OrganizationDef org) throws Exception {
		try {
			orgDefDao.updateOrgDef(org);
		} catch (Exception e) {
			log.error("orgDefDao.updateOrgDef(org)   error");
			throw e;
		}
		log.debug("orgDefDao.updateOrgDef(org)" + org + "  ok");
		return true;
	}

	// 通过id取出一条数据
	@Override
	public OrganizationDef queryOrgDefById(int level_id) throws Exception {
		OrganizationDef orgDef = new OrganizationDef();
		try {
			orgDef = (OrganizationDef) orgDefDao.queryOrgDefById(level_id).get(0);
		} catch (Exception e) {
			log.error("orgDefDao.queryOrgDefById(level_id).get(0)  error");
			throw e;
		}
		
		log.debug("orgDefDao.queryOrgDefById(level_id).get(0)" + level_id
				+ "  ok");
		return orgDef;
	}

}
