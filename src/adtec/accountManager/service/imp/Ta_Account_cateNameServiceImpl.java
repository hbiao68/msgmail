package adtec.accountManager.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.accountManager.dao.Ta_Account_cateNameDao;
import adtec.accountManager.dao.Ta_ExtendProp_cateNameDao;
import adtec.categoryManager.dao.Ta_categoryDao;
import adtec.categoryManager.model.Ta_category;
import adtec.organizationManager.dao.OrganizationDao;

import adtec.accountManager.model.Ta_Account_cateName;
import adtec.accountManager.model.Ta_ExtendProp_cateName;

import adtec.accountManager.service.Ta_Account_cateNameService;
import adtec.appManager.dao.Ta_App_relationDao;
import adtec.appManager.model.Ta_App_relation;

/**
 * @FileName: Ta_Account_cateNameServiceImpl
 * 
 * @FileType: Class
 * 
 * @Date: 2013年10月22日
 * 
 * @Author: 李季
 * 
 * @Description: 端点帐号管理实现类
 * 
 */
@Transactional(propagation=Propagation.REQUIRED)
public class Ta_Account_cateNameServiceImpl implements
		Ta_Account_cateNameService {

	Logger log = Logger.getLogger(Ta_Account_cateNameServiceImpl.class);
	
	private Ta_Account_cateNameDao ta_Account_cateNameDao = null;

	private Ta_categoryDao ta_categoryDao = null;

	private Ta_ExtendProp_cateNameDao ta_ExtendProp_cateNameDao = null;

	private Ta_App_relationDao ta_App_relationDao = null;

	private OrganizationDao orgDao = null;

	public OrganizationDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(OrganizationDao orgDao) {
		this.orgDao = orgDao;
	}

	public Ta_App_relationDao getTa_App_relationDao() {
		return ta_App_relationDao;
	}

	public void setTa_App_relationDao(Ta_App_relationDao ta_App_relationDao) {
		this.ta_App_relationDao = ta_App_relationDao;
	}

	public Ta_ExtendProp_cateNameDao getTa_ExtendProp_cateNameDao() {
		return ta_ExtendProp_cateNameDao;
	}

	public void setTa_ExtendProp_cateNameDao(
			Ta_ExtendProp_cateNameDao ta_ExtendProp_cateNameDao) {
		this.ta_ExtendProp_cateNameDao = ta_ExtendProp_cateNameDao;
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

	/**
	 * 添加方法 在某一端点分类下添加一个新帐号
	 */
	@Override
	public boolean add(Ta_Account_cateName ta_Account_cateName,
			Ta_ExtendProp_cateName ta_ExtendProp_cateName, String cateName)throws Exception {

		String status = "1";   //表示新添加帐号的状态
		String delflag = "1";  //表示新添加帐号的使用情况，1为正常使用，0为已经停用该帐号
		ta_Account_cateName.setStatus(status);
		ta_Account_cateName.setDelflag(delflag);

		try {
			//通过新填入的帐号查询已经停用的帐号中是否存在
			Ta_Account_cateName map = this.ta_Account_cateNameDao.queyrforDelflag(cateName,
					ta_Account_cateName);
			//如果存在删除已存在的帐号
			if (map != null) {
				int id = map.getId();
				ta_Account_cateName.setId(id);
				this.ta_Account_cateNameDao.deleteForDelflagById(cateName, ta_Account_cateName);
			}
			//添加新帐号
			this.ta_Account_cateNameDao.insertforTa_Account_cateName(cateName, ta_Account_cateName);
			//获取出扩展属性的个数，并填入数据库
			String str1 = ta_ExtendProp_cateName.getProp1();
			if (str1 == null || str1.equals("")) {
				ta_ExtendProp_cateName.setProp1(null);
			}
			String str2 = ta_ExtendProp_cateName.getProp2();
			if (str2 == null || str2.equals("")) {
				ta_ExtendProp_cateName.setProp2(null);
			}
			String str3 = ta_ExtendProp_cateName.getProp3();
			if (str3 == null || str3.equals("")) {
				ta_ExtendProp_cateName.setProp3(null);
			}
			String str4 = ta_ExtendProp_cateName.getProp4();
			if (str4 == null || str4.equals("")) {
				ta_ExtendProp_cateName.setProp4(null);
			}
			String str5 = ta_ExtendProp_cateName.getProp5();
			if (str5 == null || str5.equals("")) {
				ta_ExtendProp_cateName.setProp5(null);
			}
			String str6 = ta_ExtendProp_cateName.getProp6();
			if (str6 == null || str6.equals("")) {
				ta_ExtendProp_cateName.setProp6(null);
			}
			String str7 = ta_ExtendProp_cateName.getProp7();
			if (str7 == null || str7.equals("")) {
				ta_ExtendProp_cateName.setProp7(null);
			}
			String str8 = ta_ExtendProp_cateName.getProp8();
			if (str8 == null || str8.equals("")) {
				ta_ExtendProp_cateName.setProp8(null);
			}
			String str9 = ta_ExtendProp_cateName.getProp9();
			if (str9 == null || str9.equals("")) {
				ta_ExtendProp_cateName.setProp9(null);
			}
			String str10 = ta_ExtendProp_cateName.getProp10();
			if (str10 == null || str10.equals("")) {
				ta_ExtendProp_cateName.setProp10(null);
			}
			//获取新添加帐号的主键
			int id = this.ta_Account_cateNameDao.findNewKeyId(cateName);
			ta_ExtendProp_cateName.setId(id);
			//为新添加的帐号添加对应属性
			this.ta_ExtendProp_cateNameDao.insertforTa_ExtendProp_cateName(cateName, ta_ExtendProp_cateName);
			log.debug("帐号添加成功！");
			return true;
		} catch (Exception e) {
			log.error("帐号添加失败！");
			throw e;
		}
		
	}

	/**
	 * 查询方法 分页查询某一端点类型下所有帐号的信息
	 */
	@Override
	public List<Ta_Account_cateName> queryTa_Account_cateName(Ta_Account_cateName ta_Account_cateName, String cateName) {

		return this.ta_Account_cateNameDao.queryTa_Account_cateName(cateName, ta_Account_cateName);
	}

	/**
	 * 查询方法 查询某一帐号的详细信息
	 */
	@Override
	public Ta_Account_cateName findById(String cateName, int id) {
		return this.ta_Account_cateNameDao.findById(cateName, id);
	}

	/**
	 * 删除方法 删除某一帐号
	 */

	@Override
	public boolean del(String cateName, Ta_Account_cateName ta_Account_cateName)throws Exception {
		try {
			this.ta_Account_cateNameDao.updatefordelflagByAccountName(cateName,
					ta_Account_cateName);
			log.debug("帐号删除成功！");
			return true;
		} catch (Exception e) {
			log.error("帐号删除失败！");
			throw e;
		}
	}

	/**
	 * 修改方法 修改某一帐号的信息
	 */
	@Override
	public boolean update(Ta_Account_cateName ta_Account_cateName,
			Ta_ExtendProp_cateName ta_ExtendProp_cateName, String cateName)throws Exception{

		try {
			String str1 = ta_ExtendProp_cateName.getProp1();
			if (str1 == null || str1.equals("")) {
				ta_ExtendProp_cateName.setProp1(null);
			}
			String str2 = ta_ExtendProp_cateName.getProp2();
			if (str2 == null || str2.equals("")) {
				ta_ExtendProp_cateName.setProp2(null);
			}
			String str3 = ta_ExtendProp_cateName.getProp3();
			if (str3 == null || str3.equals("")) {
				ta_ExtendProp_cateName.setProp3(null);
			}
			String str4 = ta_ExtendProp_cateName.getProp4();
			if (str4 == null || str4.equals("")) {
				ta_ExtendProp_cateName.setProp4(null);
			}
			String str5 = ta_ExtendProp_cateName.getProp5();
			if (str5 == null || str5.equals("")) {
				ta_ExtendProp_cateName.setProp5(null);
			}
			String str6 = ta_ExtendProp_cateName.getProp6();
			if (str6 == null || str6.equals("")) {
				ta_ExtendProp_cateName.setProp6(null);
			}
			String str7 = ta_ExtendProp_cateName.getProp7();
			if (str7 == null || str7.equals("")) {
				ta_ExtendProp_cateName.setProp7(null);
			}
			String str8 = ta_ExtendProp_cateName.getProp8();
			if (str8 == null || str8.equals("")) {
				ta_ExtendProp_cateName.setProp8(null);
			}
			String str9 = ta_ExtendProp_cateName.getProp9();
			if (str9 == null || str9.equals("")) {
				ta_ExtendProp_cateName.setProp9(null);
			}
			String str10 = ta_ExtendProp_cateName.getProp10();
			if (str10 == null || str10.equals("")) {
				ta_ExtendProp_cateName.setProp10(null);
			}
			this.ta_Account_cateNameDao.updateTa_Account_cateNameById(cateName, ta_Account_cateName);
			this.ta_ExtendProp_cateNameDao.updateforTa_ExtendProp_cateName(cateName, ta_ExtendProp_cateName);
			log.debug("帐号修改成功！");
			return true;
		} catch (Exception e) {
			log.error("帐号修改失败！");
			throw e;
		}
	}

	/**
	 * 查询方法 查询出cateName(也就是端点名称)为端点管理的选择表做数据
	 */
	@Override
	public List<Ta_category> queryforcateName() {

		return this.ta_categoryDao.queryforcateName();
	}

	/**
	 * 查询方法 查询出端点分类扩展属性
	 */
	@Override
	public List<Ta_category> queryforTa_extendpropdef(String cateName) {

		return this.ta_Account_cateNameDao.queryforTa_extendpropdef(cateName);
	}

	/**
	 * 查询方法 //通过端点的名称来获取端点的主键（ta_id）
	 */
	@Override
	public Ta_category queryforta_idBycateName(String cateName) {

		return this.ta_categoryDao.queryforta_idBycateName(cateName);
	}

	/**
	 * 查询方法 对新添加的帐号进行校验
	 */
	@Override
	public int queryforcheckaccountNameByaccountName(Ta_Account_cateName ta_Account_cateName,
			String cateName) {
		return this.ta_Account_cateNameDao.queryforcheckaccountNameByaccountName(cateName,
				ta_Account_cateName);
	}

	/**
	 * 删除方法 批量删除
	 */
	@Override
	public boolean batchUpdate(String cateName, List<Integer> list, String delflag)throws Exception {
		this.ta_Account_cateNameDao.batchupdate(cateName, list, delflag);
		return true;
	}

	/**
	 * 查询方法 通过accountName和ta_id来查询accountName是否存在
	 */
	@Override
	public List<Ta_App_relation> queryForCheckAccountName(Ta_App_relation ta_App_relation) {
		return this.ta_App_relationDao
				.queryForCheckAccountName(ta_App_relation);
	}

	/**
	 * 查询方法 分页查询所有已经开通业务的帐号
	 */
	@Override
	public List<Ta_Account_cateName> queryOpenAppTa_Account_cateName(String cateName,
			Ta_Account_cateName ta_Account_cateName) {
		return this.ta_Account_cateNameDao.queryOpenAppTa_Account_cateName(cateName,
				ta_Account_cateName);
	}

	/**
	 * 查询方法 分页查询所有尚未开通业务的帐号
	 */
	@Override
	public List<Ta_Account_cateName> queryUnOpenAppTa_Account_cateName(String cateName,
			Ta_Account_cateName ta_Account_cateName) {
		return this.ta_Account_cateNameDao.queryUnOpenAppTa_Account_cateName(cateName,
				ta_Account_cateName);
	}

	@Override
	public Map<String,Object> queryOrgName(String orgName) {
		return this.orgDao.queryOrgName(orgName);
	}

/*	*//**
	 * 删除方法 删除重复添加的并且delflag值为2的数据
	 *//*
	@Override
	public boolean deleteForDelflag(String cateName,
			Ta_Account_cateName ta_Account_cateName) {
		this.ta_Account_cateNameDao.deleteForDelflag(cateName,
				ta_Account_cateName);
		return true;
	}*/

/*	*//**
	 * 查询方法 通过accountName查询id
	 *//*
	@Override
	public Map<String,Object> queyrforDelflag(String cateName,
			Ta_Account_cateName ta_Account_cateName) {

		return this.ta_Account_cateNameDao.queyrforDelflag(cateName,
				ta_Account_cateName);
	}
*/

	/**
	 * 查询方法 查询某一端点类型下所有帐号的信息
	 */
	@Override
	public List<Ta_Account_cateName> queryTa_Account_cateNameforCount(Ta_Account_cateName ta_Account_cateName,
			String cateName) {

		return this.ta_Account_cateNameDao.queryTa_Account_cateNameforCount(cateName,
				ta_Account_cateName);
	}

	/**
	 * 查询方法 查询所有已经开通业务的帐号
	 */
	@Override
	public List<Ta_Account_cateName> queryOpenAppTa_Account_cateNameforCount(String cateName,
			Ta_Account_cateName ta_Account_cateName) {
		return this.ta_Account_cateNameDao.queryOpenAppTa_Account_cateNameforCount(cateName,
				ta_Account_cateName);
	}

	/**
	 * 查询方法 查询所有尚未开通业务的帐号
	 */
	@Override
	public List<Ta_Account_cateName> queryUnOpenAppTa_Account_cateNameforCount(String cateName,
			Ta_Account_cateName ta_Account_cateName) {
		return this.ta_Account_cateNameDao.queryUnOpenAppTa_Account_cateNameforCount(cateName,
				ta_Account_cateName);
	}

	/**
	 * 通过账号查询开通业务个数
	 * @param map  存放所需参数accountName.
	 * @return 开通业务数量
	 * @author maojd 
	 * update date:2013-12-25 11:39
	 */
	@Override
	public int queryAppNumByAccountName(Map<String, String> map) {
		int i = 0;
		try {
			i = ta_App_relationDao.queryAppNumByAccountName(map);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("通过帐号查询开通业务个数失败！");
		}
		return i;
	}

}
