package adtec.categoryManager.service.imp;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.accountManager.dao.Ta_Account_cateNameDao;
import adtec.accountManager.model.Ta_Account_cateName;
import adtec.appManager.dao.Ta_AppDao;
import adtec.categoryManager.dao.Ta_categoryDao;
import adtec.categoryManager.dao.Ta_extendpropdefDao;
import adtec.categoryManager.model.Ta_category;
import adtec.categoryManager.model.Ta_extendpropdef;
import adtec.categoryManager.service.CategoryManagerService;

/**
 * @FileName: CategoryManagerServiceImpl
 * 
 * @FileType: Class
 * 
 * @Date: 2013年10月13日
 * 
 * @Author: 李季
 * 
 * @Description: 端点分类管理实现类
 * 
 */
@Transactional(propagation=Propagation.REQUIRED)
public class CategoryManagerServiceImpl implements CategoryManagerService {
	
	Logger log = Logger.getLogger(CategoryManagerServiceImpl.class);
	
	private Ta_categoryDao ta_categoryDao = null;
	private Ta_extendpropdefDao ta_extendpropdefDao = null;

	private Ta_AppDao ta_AppDao = null;

	private Ta_Account_cateNameDao ta_Account_cateNameDao = null;

	public Ta_AppDao getTa_AppDao() {
		return ta_AppDao;
	}

	public void setTa_AppDao(Ta_AppDao ta_AppDao) {
		this.ta_AppDao = ta_AppDao;
	}

	public Ta_Account_cateNameDao getTa_Account_cateNameDao() {
		return ta_Account_cateNameDao;
	}

	public void setTa_Account_cateNameDao(
			Ta_Account_cateNameDao ta_Account_cateNameDao) {
		this.ta_Account_cateNameDao = ta_Account_cateNameDao;
	}

	public Ta_categoryDao getTa_categoryDao() {
		return ta_categoryDao;
	}

	public void setTa_categoryDao(Ta_categoryDao ta_categoryDao) {
		this.ta_categoryDao = ta_categoryDao;
	}

	public Ta_extendpropdefDao getTa_extendpropdefDao() {
		return ta_extendpropdefDao;
	}

	public void setTa_extendpropdefDao(Ta_extendpropdefDao ta_extendpropdefDao) {
		this.ta_extendpropdefDao = ta_extendpropdefDao;
	}

	/**
	 * 删除方法 删除整条数据以及对应的两张表
	 */
	@Override
	public boolean delete(String cateName, String ta_id)throws Exception {

		try {
			// 删除ta_id对应的以cateName为命名的TA_ExtendProp_XXX表
			this.ta_categoryDao.delTA_ExtendProp(cateName);
			// 删除ta_id对应的以cateName为命名的TA_Account_XXX表
			this.ta_categoryDao.delTA_Account(cateName);
			// 根据外键ta_id删除Ta_extendpropdef表中对应的数据
			this.ta_extendpropdefDao.deleteTa_extendpropdefByTa_id(ta_id);
			// 根据主键ta_id删除Ta_category表中对应的数据
			this.ta_categoryDao.deleteTa_categoryByTa_id(ta_id);
			log.debug("终端删除成功！");
			return true;
		} catch (Exception e) {
			log.error("终端删除失败！");
			throw e;
		}
	}

	/**
	 * 查询方法 查询所有数据
	 */

	@Override
	public List<Ta_category> queryAllByTa_category(Ta_category ta_category) {

		return this.ta_categoryDao.queryAllByTa_category(ta_category);
	}

	/**
	 * 添加方法 添加一条数据同时以数据的名称创建两张表
	 */
	@Override
	@Transactional
	public boolean add(Ta_category ta_category,
			Ta_extendpropdef ta_extendpropdef, HttpServletRequest request)throws Exception {
		try {
			// 获取UUID来作为主键值（ta_id的值）
			String id = UUID.randomUUID().toString().replace("-", "");
			ta_category.setTa_id(id);
			// 获取由request从Controller带过来的值
			String cateName = request.getParameter("cateName");
			String cateDesc = request.getParameter("cateDesc");
			String importClass = request.getParameter("importClass");
			String authClass = request.getParameter("authClass");

			// 将所获取到的值去掉空格放入ta_category对象中
			String cateNameTrim = cateName.trim();
			ta_category.setCateName(cateNameTrim);
			ta_category.setCateDesc(cateDesc.trim());
			ta_category.setImportClass(importClass.trim());
			ta_category.setAuthClass(authClass.trim());

			// 调用Ta_category的添加方法
			this.ta_categoryDao.insertforTa_category(ta_category);
			// 获取新添加的cateName的值来作为添加表的表名
			String cateNameForCreatTable = ta_category.getCateName();

			// 获取新增ta_id的值
			String ta_id = ta_category.getTa_id();
			// 将新增ta_id的值座位从表（Ta_extendpropdef表）的外键
			ta_extendpropdef.setTa_id(ta_id);

			// 通过request获取前台页面端Ta_extendpropdef表对应各个属性的值
			String propName[] = request.getParameterValues("propName");
			String prop_index1[] = request.getParameterValues("prop_index1");
			String propDesc[] = request.getParameterValues("propDesc");
			// 将获取到的值依次添入数据库
			for (int i = 1; i < prop_index1.length; i++) {

				ta_extendpropdef.setPropDesc(propDesc[i].trim());
				ta_extendpropdef.setProp_index(Integer.parseInt(prop_index1[i]));
				ta_extendpropdef.setTa_id(ta_id);
				ta_extendpropdef.setPropName(propName[i].trim());
				// 将前端所有获取到的信息添加到端点描述中
				this.ta_extendpropdefDao.insertforTa_extendpropdef(ta_extendpropdef);
			}
			// 添加时候创建表
			this.ta_categoryDao.createTA_Account(cateNameForCreatTable);
			/*this.ta_categoryDao.createTA_Account_accountName(cateNameForCreatTable);*/
			this.ta_categoryDao.createTA_ExtendProp(cateNameForCreatTable);
			log.debug("添加终端成功！");
			return true;
		} catch (Exception ex) {

			String cateName = request.getParameter("cateName");
			String ta_id = ta_category.getTa_id();
			// 删除ta_id对应的以cateName为命名的TA_ExtendProp_XXX表
			this.ta_categoryDao.delTA_ExtendProp(cateName);
			// 删除ta_id对应的以cateName为命名的TA_Account_XXX表
			this.ta_categoryDao.delTA_Account(cateName);
			// 根据外键ta_id删除Ta_extendpropdef表中对应的数据
			this.ta_extendpropdefDao.deleteTa_extendpropdefByTa_id(ta_id);
			// 根据主键ta_id删除Ta_category表中对应的数据
			this.ta_categoryDao.deleteTa_categoryByTa_id(ta_id);
			//ex.printStackTrace();
			log.error("添加终端失败！");
			throw ex;
		}
	}

	/**
	 * 查询方法 查询某一端点的详细信息
	 */
	@Override
	public List<Ta_category> findAllByTa_id(String ta_id) {

		return this.ta_categoryDao.findAllByTa_id(ta_id);
	}

	/**
	 * 修改方法 修改两张表里面所有允许修改的字段
	 */
	@Override
	public boolean update(Ta_category ta_category,
			Ta_extendpropdef ta_extendpropdef, HttpServletRequest request,
			String ta_id)throws Exception {
		try {
			// 将要修改的从表里对应的数据删除
			this.ta_extendpropdefDao.deleteTa_extendpropdefByTa_id(ta_id);
			// 更新主表数据
			String cateName = request.getParameter("cateName");
			String cateDesc = request.getParameter("cateDesc");
			String importClass = request.getParameter("importClass");
			String authClass = request.getParameter("authClass");
			ta_category.setCateName(cateName.trim());
			ta_category.setCateDesc(cateDesc.trim());
			ta_category.setImportClass(importClass.trim());
			ta_category.setAuthClass(authClass.trim());
			this.ta_categoryDao.updateTa_categoryByTa_id(ta_category);

			// 获取页面端更新好的从表数据，从新填入数据库
			String propName[] = request.getParameterValues("propName");
			String prop_index1[] = request.getParameterValues("prop_index1");
			String propDesc[] = request.getParameterValues("propDesc");
			// 循环添加页面段所有端点描述的数据
			for (int i = 1; i < prop_index1.length; i++) {
				Ta_extendpropdef ta_extendpropdef1 = new Ta_extendpropdef();
				ta_extendpropdef1.setPropDesc(propDesc[i].trim());
				ta_extendpropdef1.setProp_index(Integer.parseInt(prop_index1[i]));
				ta_extendpropdef1.setTa_id(ta_id);
				ta_extendpropdef1.setPropName(propName[i].trim());
				this.ta_extendpropdefDao.insertforTa_extendpropdef(ta_extendpropdef1);
				ta_extendpropdef1 = null;
			}
			log.debug("终端修改成功！");
			return true;
		} catch (Exception e) {
			log.error("终端修改失败！");
			throw e;
		}
		
	}

	/**
	 * 校验 为新添加的端点进行校验
	 */
	@Override
	public int queryforCheckcateNameBycateName(String cateName) {
		return this.ta_categoryDao.queryforCheckcateNameBycateName(cateName);
	}

	/**
	 * 查询方法 条件查询时端点描述的下拉列表
	 */
	@Override
	public List<Ta_category> queryforcateDesc() {
		return this.ta_categoryDao.queryforcateName();
	}

	/**
	 * 查询方法 查询某一表（ta_account_XXX）是否有数据
	 */
	@Override
	public List<Ta_Account_cateName> queryForTa_idCountBycateName(String cateName) {
		return this.ta_Account_cateNameDao.queryForTa_idCountBycateName(cateName);
	}

	/**
	 * 查询方法 通过ta_id查询cateName
	 */
	@Override
	public Ta_category findcateNameByta_id(String ta_id) {
		return this.ta_categoryDao.findcateNameByta_id(ta_id);
	}

	/**
	 * 查询所有的记录数
	 */
	@Override
	public int queryCount(Ta_category ta_category) {

		return this.ta_categoryDao.queryCount(ta_category);
	}
	
	/**
	 * 查询某一终端下是否有业务
	 */
	@Override
	public int queryAppforTa_idCount(String ta_id) {
		return this.ta_AppDao.queryAppforTa_idCount(ta_id);
	}

}
