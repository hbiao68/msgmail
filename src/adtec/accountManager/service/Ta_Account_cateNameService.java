package adtec.accountManager.service;

import adtec.accountManager.model.Ta_Account_cateName;
import adtec.accountManager.model.Ta_ExtendProp_cateName;
import adtec.appManager.model.Ta_App_relation;
import adtec.categoryManager.model.Ta_category;

import java.util.*;
/**
 * @FileName: Ta_Account_cateNameService
 * 
 * @FileType: Interface
 * 
 * @Date: 2013年10月22日
 * 
 * @Author: 李季
 * 
 * @Description: 端点帐号管理功能点接口
 * 
 */
public interface Ta_Account_cateNameService {

	/**
	 * 添加新帐号
	 * 
	 * @param ta_Account_cateName
	 * @param ta_ExtendProp_cateName
	 * @param cateName
	 * @return
	 */
	public boolean add(Ta_Account_cateName ta_Account_cateName,
			Ta_ExtendProp_cateName ta_ExtendProp_cateName, String cateName)throws Exception;

	/**
	 * 分页查询所有帐号的信息
	 * 
	 * @param ta_Account_cateName
	 * @param cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryTa_Account_cateName(Ta_Account_cateName ta_Account_cateName, String cateName);

	/**
	 * 单一实例查询
	 * 
	 * @param cateName
	 * @param id
	 * @return
	 */
	public Ta_Account_cateName findById(String cateName, int id);

	/**
	 * 删除帐号
	 * 
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
	public boolean del(String cateName, Ta_Account_cateName ta_Account_cateName)throws Exception;

	/**
	 * 更新帐号信息
	 * 
	 * @param ta_Account_cateName
	 * @param ta_ExtendProp_cateName
	 * @param cateName
	 * @return
	 */
	public boolean update(Ta_Account_cateName ta_Account_cateName,
			Ta_ExtendProp_cateName ta_ExtendProp_cateName, String cateName)throws Exception;

	/**
	 * 查询出cateName(也就是端点名称)为端点管理的选择表做数据
	 * 
	 * @return
	 */
	public List<Ta_category> queryforcateName();

	/**
	 * 查询出端点分类扩展属性
	 * 
	 * @param cateName
	 * @return
	 */
	public List<Ta_category> queryforTa_extendpropdef(String cateName);

	/**
	 * 通过端点的名称来获取端点的主键（ta_id）
	 * 
	 * @param cateName
	 * @return
	 */
	public Ta_category queryforta_idBycateName(String cateName);

	/**
	 * 对新添加的帐号进行校验
	 * 
	 * @param ta_Account_cateName
	 * @param cateName
	 * @return
	 */
	public int queryforcheckaccountNameByaccountName(Ta_Account_cateName ta_Account_cateName,
			String cateName);

	/**
	 * 批量修改状态
	 * 
	 * @param cateName
	 * @param list
	 * @param delflag
	 * @return
	 */
	public boolean batchUpdate(String cateName, List<Integer> list, String delflag)throws Exception;

	/**
	 * 通过accountName和ta_id来查询accountName是否存在
	 * 
	 * @param ta_App_relation
	 * @return
	 */
	public List<Ta_App_relation> queryForCheckAccountName(Ta_App_relation ta_App_relation);

	/**
	 * 分页查询所有已经开通业务的帐号
	 * 
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryOpenAppTa_Account_cateName(String cateName,
			Ta_Account_cateName ta_Account_cateName);

	/**
	 * 分页查询所有尚未开通业务的帐号
	 * 
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryUnOpenAppTa_Account_cateName(String cateName,
			Ta_Account_cateName ta_Account_cateName);

	/**
	 * 通过OrgName获取orgId的值
	 * 
	 * @param orgName
	 * @return
	 */
	public Map queryOrgName(String orgName);

	/**
	 * 删除重复添加的并且delflag值为2的数据
	 * 
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
/*	@Transactional
	public boolean deleteForDelflag(String cateName,
			Ta_Account_cateName ta_Account_cateName);
*/
/*	*//**
	 * 通过accountName查询id
	 * 
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 *//*
	public Map<String,Object> queyrforDelflag(String cateName,
			Ta_Account_cateName ta_Account_cateName);*/



	/**
	 * 查询所有帐号的信息
	 * 
	 * @param ta_Account_cateName
	 * @param cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryTa_Account_cateNameforCount(Ta_Account_cateName ta_Account_cateName,
			String cateName);

	/**
	 * 查询所有已经开通业务的帐号
	 * 
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryOpenAppTa_Account_cateNameforCount(String cateName,
			Ta_Account_cateName ta_Account_cateName);

	/**
	 * 查询所有尚未开通业务的帐号
	 * 
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryUnOpenAppTa_Account_cateNameforCount(String cateName,
			Ta_Account_cateName ta_Account_cateName);
	
	
	/**
	 * 通过账号查询开通业务个数
	 * @param map  存放所需参数accountName.
	 * @return 开通业务数量
	 * @author maojd 
	 * update date:2013-12-25 11:25
	 */
	public int queryAppNumByAccountName(Map<String, String> map);
}
