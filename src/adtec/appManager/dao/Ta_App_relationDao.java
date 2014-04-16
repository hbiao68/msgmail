package adtec.appManager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import adtec.appManager.model.Ta_App_relation;
import adtec.accountManager.model.Ta_Account_cateName;



/**
 * 
 * @FileName: Ta_App_relationDao
 * 
 * @FileType: Interface
 * 
 * @Date: 2013年10月28日
 * 
 * @Author: 李季
 * 
 * @Description: 业务开通接口
 */
public interface Ta_App_relationDao {

	/**
	 * 添加端点开通的业务
	 * 
	 * @param ta_App_relation
	 */
	public void insertforTa_App_relation(Ta_App_relation ta_App_relation)throws Exception;

	/**
	 * 分页查询某一端点下所有帐号开通业务的情况
	 * 
	 * @param ta_id
	 * @param accountName
	 * @param cateName
	 * @param orgId
	 * @return
	 */
	public List<Ta_App_relation> queryforTa_App_relation(
			@Param("ta_App_relation") Ta_App_relation ta_App_relation,
			@Param("ta_Account_cateName") Ta_Account_cateName ta_Account_cateName,
			@Param("cateName") String cateName);

	/**
	 * 单一实例查询
	 * 
	 * @param ta_App_relation
	 * @return
	 */
	public List<Ta_App_relation> findTa_App_relationbyTa_App_relation(Ta_App_relation ta_App_relation);

	/**
	 * 删除单一数据
	 * 
	 * @param ta_App_relation
	 */
	public void deleteTa_App_relationByaccountName(Ta_App_relation ta_App_relation)throws Exception;

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
	 */
	public void deleteforOrg(@Param("appid") int appid,
			@Param("orgId") int orgId, @Param("cateName") String cateName);

	/**
	 * 为某个机构下面所有的帐号开通业务
	 * 
	 * @param list
	 */
	public void insertforOrg(List list);

	/**
	 * 批量删除数据
	 * 
	 * @param accountName
	 * @param cateName
	 */
	public void batchdelete(@Param("accountName") String accountName,
			@Param("cateName") String cateName);

	/**
	 * 通过appid查询ta_app_relation 表中的appid是否存在
	 * 
	 * @param appid
	 * @return
	 */
	public int queryForCheckAppid(int appid);

	/**
	 * 通过accountName和ta_id来查询accountName是否存在
	 * 
	 * @param ta_App_relation
	 * @return
	 */
	public List<Ta_App_relation> queryForCheckAccountName(Ta_App_relation ta_App_relation);

	/**
	 * 查询某一端点下所有帐号开通业务的情况
	 * 
	 * @param ta_id
	 * @param accountName
	 * @param cateName
	 * @param orgId
	 * @return
	 */
	public List<Ta_App_relation> queryTa_App_relationforCount(
			@Param("ta_App_relation") Ta_App_relation ta_App_relation,
			@Param("ta_Account_cateName") Ta_Account_cateName ta_Account_cateName,
			@Param("cateName") String cateName);
	
	
	
	/**
	 * 通过账号查询开通业务个数
	 * @param map  存放所需参数accountName.
	 * @return 开通业务数量
	 * @author maojd 
	 * update date:2013-12-25 11:25
	 */
	public int queryAppNumByAccountName(Map<String, String> map)throws Exception;

	
	
	/**
	 * 查询开通的账号是否存在
	 * @param ta_App_relation	开通账号实体
	 * @return					查询存在的数量 1存在  0不存在
	 * @author maojd
	 * update date: 17:49 2013/12/26
	 */
	public int queryAccExist(Ta_App_relation ta_App_relation)throws Exception;
	
	public void batchInsert(List<Ta_App_relation> list);
	
	public List<String> getAccByAccApp(Map<String,Object> map); 
	
}
