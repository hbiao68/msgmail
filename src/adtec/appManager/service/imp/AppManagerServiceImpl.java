package adtec.appManager.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.appManager.model.Ta_App;
import adtec.appManager.dao.Ta_AppDao;
import adtec.appManager.dao.Ta_App_relationDao;
import adtec.appManager.service.AppManagerService;

import adtec.categoryManager.dao.Ta_categoryDao;
import adtec.categoryManager.model.Ta_category;
import adtec.ta_org_relationManager.dao.Ta_org_relationDao;

/**
 * @FileName: CategoryManagerServiceImpl
 * 
 * @FileType: Class
 * 
 * @Date: 2013年10月21日
 * 
 * @Author: 李季
 * 
 * @Description: 业务管理实现类
 * 
 */
@Transactional(propagation=Propagation.REQUIRED)
public class AppManagerServiceImpl implements AppManagerService {
	
	Logger log = Logger.getLogger(AppManagerServiceImpl.class);

	private Ta_AppDao ta_AppDao = null;

	private Ta_categoryDao ta_categoryDao = null;

	private Ta_App_relationDao ta_App_relationDao = null;
	
	private Ta_org_relationDao ta_org_relationDao = null;

	public Ta_org_relationDao getTa_org_relationDao() {
		return ta_org_relationDao;
	}

	public void setTa_org_relationDao(Ta_org_relationDao ta_org_relationDao) {
		this.ta_org_relationDao = ta_org_relationDao;
	}

	public Ta_App_relationDao getTa_App_relationDao() {
		return ta_App_relationDao;
	}

	public void setTa_App_relationDao(Ta_App_relationDao ta_App_relationDao) {
		this.ta_App_relationDao = ta_App_relationDao;
	}

	public Ta_categoryDao getTa_categoryDao() {
		return ta_categoryDao;
	}

	public void setTa_categoryDao(Ta_categoryDao ta_categoryDao) {
		this.ta_categoryDao = ta_categoryDao;
	}

	public Ta_AppDao getTa_AppDao() {
		return ta_AppDao;
	}

	public void setTa_AppDao(Ta_AppDao ta_AppDao) {
		this.ta_AppDao = ta_AppDao;
	}

	/**
	 * 添加方法 添加业务
	 */
	@Override
	public boolean addforApp(Ta_App ta_App)throws Exception {
		try {
			this.ta_AppDao.insertApp(ta_App);
			log.debug("业务添加成功！");
			return true;
		} catch (Exception e) {
			log.error("业务添加失败！");
			throw e;
		}
	}

	/**
	 * 查询方法 查询出ta_id做下拉列表使用
	 */
	@Override
	public List<Ta_category> queryforcateName() {
		return this.ta_categoryDao.queryforcateName();
	}

	/**
	 * 查询方法 查询所有业务
	 */
	@Override
	public Ta_App findAppById(int appid) {
		return this.ta_AppDao.findAppById(appid);
	}

	/**
	 * 删除方法 删除数据
	 */
	@Override
	public boolean deleteAppById(int appid)throws Exception {
		try {
			this.ta_AppDao.deleteAppById(appid);
			log.debug("业务删除成功！");
			return true;
		} catch (Exception e) {
			log.error("业务删除失败！");
			throw e;
		}
	}

	/**
	 * 修改方法 修改某一业务信息
	 */
	@Override
	public boolean updateAppById(Ta_App ta_App)throws Exception {
		try {
			this.ta_AppDao.updateAppById(ta_App);
			log.debug("业务修改成功！");
			return true;
		} catch (Exception e) {
			log.error("业务修改失败！");
			throw e;
		}
	}

	/**
	 * 查询方法 为检查新添加业务检验
	 */
	@Override
	public int queryforcheckNewAppName(Ta_App ta_App) {
		return this.ta_AppDao.queryforcheckNewAppName(ta_App);
	}

	/**
	 * 删除方法 批量删除数据
	 */
	@Override
	public boolean batchdelete(List<Integer> list)throws Exception {
		try {
			this.ta_AppDao.batchdelete(list);
			log.debug("业务批量删除成功！");
			return true;
		} catch (Exception e) {
			log.error("业务批量删除失败！");
			throw e;
		}
	}

	/**
	 * 查询方法 通过appid查询ta_app_relation 表中的appid是否存在
	 */
	@Override
	public int queryForCheckAppid(int appid) {
		return this.ta_App_relationDao.queryForCheckAppid(appid);
	}

	/**
	 * 分页查询方法 查询所有已经有帐号开通的业务
	 */
	@Override
	public List<Ta_App> queryOpenAppByTaApp(Ta_App ta_App) {
		return this.ta_AppDao.queryOpenAppByTaApp(ta_App);
	}

	/**
	 * 分页查询方法 查询所有没有帐号开通的业务
	 */
	@Override
	public List<Ta_App> queryUnOpenAppByTaApp(Ta_App ta_App) {
		return this.ta_AppDao.queryUnOpenAppByTaApp(ta_App);
	}

	/**
	 * 分页查询所有业务
	 */
	@Override
	public List<Ta_App> queryAppByTaApp(Ta_App ta_App) {

		return this.ta_AppDao.queryAppByTaApp(ta_App);
	}


	/**
	 * 查询方法 查询所有业务
	 */

	@Override
	public List<Ta_App> queryAPPforCountByTaApp(Ta_App ta_App) {
		return this.ta_AppDao.queryAPPforCountByTaApp(ta_App);
	}

	/**
	 * 查询方法 查询所有已经有帐号开通的业务
	 */
	@Override
	public List<Ta_App> queryOpenAppforCountByTaApp(Ta_App ta_App) {
		return this.ta_AppDao.queryOpenAppforCountByTaApp(ta_App);
	}

	/**
	 * 查询方法 查询所有没有帐号开通的业务
	 */
	@Override
	public List<Ta_App> queryUnOpenAppforCountByTaApp(Ta_App ta_App) {
		return this.ta_AppDao.queryUnOpenAppforCountByTaApp(ta_App);
	}

	
	/**
	 * 
	 * 通过appid查询该业务下的机构数量（业务被多少机构使用）
	 * @author maojd
	 * date:15:43 2013/12/25
	 * @param appid 业务id
	 * @return	机构数量
	 */
	@Override
	public int queryOrgNumByAppid(int appid) {
		int orgNum = 0;
		try {
			orgNum = ta_org_relationDao.queryOrgNumByAppid(appid);
		} catch (Exception e) {
			log.error("通过appid查询该业务下的机构数量失败！");
		//	e.printStackTrace();
		}
		return orgNum;
	}

	
	@Override
	public String queryCateDescByTa_id(String ta_id) {
		String cateDesc = ta_categoryDao.queryCateDescByTa_id(ta_id);	
		return cateDesc;
	}

}
