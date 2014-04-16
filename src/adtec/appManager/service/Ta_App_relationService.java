package adtec.appManager.service;

import adtec.accountManager.model.Ta_Account_cateName;
import java.util.*;

import org.apache.ibatis.annotations.Param;

import adtec.appManager.model.Ta_App;
import adtec.appManager.model.Ta_App_relation;
import adtec.categoryManager.model.Ta_category;
import adtec.ta_org_relationManager.model.Ta_org_relation;

/**
 * @FileName: AppManagerService
 * 
 * @FileType: Interface
 * 
 * @Date: 2013年10月28日
 * 
 * @Author: 李季
 * 
 * @Description: 业务开通功能点接口
 * 
 */
public interface Ta_App_relationService {

	/**
	 * 添加端点开通的业务
	 * 
	 * @param ta_App_relation
	 * @return
	 */
	public boolean add(List<Ta_App_relation> list)throws Exception;

	/**
	 * 查询出所有业务为业务开通使用
	 * 
	 * @param ta_id
	 * @return
	 */
	public List<Ta_App> queryforRelation(String ta_id);

	/**
	 * 分页查询某一端点下所有帐号开通业务的情况
	 * 
	 * @param ta_id
	 * @param accountName
	 * @param cateName
	 * @param orgId
	 * @return
	 */
	public List<Ta_App_relation> queryforTa_App_relation(Ta_App_relation ta_App_relation,
			Ta_Account_cateName ta_Account_cateName, String cateName);

	/**
	 * 对开通业务的帐号进行检验
	 * 
	 * @param ta_Account_cateName
	 * @param cateName
	 * @return
	 */
	public int queryforcheck(Ta_Account_cateName ta_Account_cateName,
			String cateName);

	/**
	 * 对单一帐号详细信息进行查询
	 * 
	 * @param ta_App_relation
	 * @return
	 */
	public List<Ta_App_relation> findTa_App_relationbyTa_App_relation(Ta_App_relation ta_App_relation);

	/**
	 * 删除帐号
	 * 
	 * @param ta_App_relation
	 * @return
	 */
	public boolean deleteTa_App_relationByaccountName(Ta_App_relation ta_App_relation)throws Exception;

	/**
	 * 更新方法
	 * 
	 * @param ta_App_relation
	 * @return
	 */
	public boolean update(List<Ta_App_relation> list)throws Exception;

	/**
	 * 查询出ta_id(也就是端点id)为端点管理的选择表做数据
	 * 
	 * @return
	 */
	public List<Ta_category> queryforcateName();

	/**
	 * 通过主键获取cateName
	 * 
	 * @param ta_id
	 * @return
	 */
	public Ta_category findcateNameByta_id(String ta_id);

	/**
	 * 校验新开通业务的帐号是否重复
	 * 
	 * @param accountName
	 * @return
	 */
	public int queryforCheckNewAccoutNameByAccountName(Ta_App_relation ta_App_relation);

	/**
	 * 删除某个机构下面已经开通业务帐号的信息
	 * 
	 * @param appid
	 * @param orgId
	 * @param cateName
	 * @return
	 */
	public boolean deleteforOrg(int appid, int orgId, String cateName);

	/**
	 * 查询某一机构下面所有的帐号
	 * 
	 * @param cateName
	 * @param orgId
	 * @return
	 */
	public List<Ta_Account_cateName> queryforOrg(String cateName, int orgId);

	/**
	 * 为某个机构下面所有的帐号开通业务
	 * 
	 * @param list
	 * @return
	 */
	public boolean insertforOrg(List<Ta_App_relation> list);

	/**
	 * 批量删除数据
	 * 
	 * @param accountName
	 * @param cateName
	 * @return
	 */
	public boolean batchdelete(String accountName, String cateName);

	/**
	 * 查询某一端点下所有帐号开通业务的情况
	 * 
	 * @param ta_id
	 * @param accountName
	 * @param cateName
	 * @param orgId
	 * @return
	 */
	public List<Ta_App_relation> queryTa_App_relationforCount(Ta_App_relation ta_App_relation,
			Ta_Account_cateName ta_Account_cateName, String cateName);

	
	/**
	 * 查询开通的账号是否存在
	 * @param ta_App_relation	开通账号实体
	 * @return					查询账号数量		0不存在	1存在
	 * @author maojd
	 * update date: 17:49 2013/12/26
	 */
	public int queryAccExist(Ta_App_relation ta_App_relation);
	
	/**
	 * 通过帐号来查询该帐号所属机构
	 * @param cateName
	 * @param accountName
	 * @return
	 */
	public String findOrgIdByAccount(String cateName,String accountName);
	
	/**
	 * 查询该银行下是否开通了某些业务
	 * @param ta_org_relation
	 * @return
	 */
	public int queryAppCountByOrgRelation(Ta_org_relation ta_org_relation);

}
