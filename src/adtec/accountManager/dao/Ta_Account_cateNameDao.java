package adtec.accountManager.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import adtec.accountManager.model.Ta_Account_cateName;
import adtec.categoryManager.model.Ta_category;


/**
 * 
 * @FileName:      Ta_Account_cateNameDao
 *
 * @FileType:      Interface
 *
 * @Date:          2013年10月17日
 * 
 * @Author:        李季
 * 
 * @Description:   端点信息接口
 */
public interface Ta_Account_cateNameDao {

	/**
	 * 添加新帐号
	 * @param cateName
	 * @param ta_Account_cateName
	 */
	public void insertforTa_Account_cateName(@Param("cateName")String cateName,@Param("ta_Account_cateName")Ta_Account_cateName ta_Account_cateName)throws Exception;
	
	/**
	 * 分页查询所有帐号的信息
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryTa_Account_cateName(@Param("cateName")String cateName,@Param("ta_Account_cateName")Ta_Account_cateName ta_Account_cateName);
	
	/**
	 * 查询单一帐号的详细信息
	 * @param cateName
	 * @param id
	 * @return
	 */
	public Ta_Account_cateName findById(@Param("cateName")String cateName,@Param("id")int id);
	
	/**
	 * 修改帐号状态
	 * @param cateName
	 * @param ta_Account_cateName
	 */
	public void updatefordelflagByAccountName(@Param("cateName")String cateName,@Param("ta_Account_cateName")Ta_Account_cateName ta_Account_cateName)throws Exception;
	
	/**
	 * 修改帐号信息
	 * @param cateName
	 * @param ta_Account_cateName
	 */
	public void updateTa_Account_cateNameById(@Param("cateName")String cateName,@Param("ta_Account_cateName")Ta_Account_cateName ta_Account_cateName)throws Exception;
	
	/**
	 * 查询出端点分类扩展属性
	 * @param cateName
	 * @return
	 */
	public List<Ta_category> queryforTa_extendpropdef(@Param("cateName")String cateName);
	
	/**
	 * 对新添加的帐号进行校验
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
	public int queryforcheckaccountNameByaccountName(@Param("cateName")String cateName,@Param("ta_Account_cateName")Ta_Account_cateName ta_Account_cateName);
	
	/**
	 * 查询某一机构下面所有的帐号
	 * @param cateName
	 * @param orgId
	 * @return
	 */
	public List<Ta_Account_cateName> queryforOrg(@Param("cateName")String cateName,@Param("orgId")int orgId);
	
	/**
	 * 批量修改状态
	 * @param cateName
	 * @param list
	 * @param delflag
	 */
	public void batchupdate(@Param("cateName")String cateName,@Param("list")List<Integer> list,@Param("delflag")String delflag)throws Exception;
	
	/**
	 * 查询某一表（ta_account_XXX）是否有数据
	 * @param cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryForTa_idCountBycateName(@Param("cateName")String cateName);
	
	/**
	 * 分页查询所有已经开通业务的帐号
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryOpenAppTa_Account_cateName(@Param("cateName")String cateName,@Param("ta_Account_cateName")Ta_Account_cateName ta_Account_cateName);

	/**
	 * 分页查询所有尚未开通业务的帐号
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryUnOpenAppTa_Account_cateName(@Param("cateName")String cateName,@Param("ta_Account_cateName")Ta_Account_cateName ta_Account_cateName);
	
	/**
	 * 删除重复添加的并且delflag值为2的数据
	 * @param cateName
	 * @param ta_Account_cateName
	 */
	public void deleteForDelflagById(@Param("cateName")String cateName,@Param("ta_Account_cateName")Ta_Account_cateName ta_Account_cateName)throws Exception;
	
	/**
	 * 通过accountName查询id
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
	public Ta_Account_cateName queyrforDelflag(@Param("cateName")String cateName,@Param("ta_Account_cateName")Ta_Account_cateName ta_Account_cateName);
	
	

	/**
	 * 查询所有帐号的信息
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryTa_Account_cateNameforCount(@Param("cateName")String cateName,@Param("ta_Account_cateName")Ta_Account_cateName ta_Account_cateName);
	
	
	/**
	 * 查询所有已经开通业务的帐号
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryOpenAppTa_Account_cateNameforCount(@Param("cateName")String cateName,@Param("ta_Account_cateName")Ta_Account_cateName ta_Account_cateName);

	/**
	 * 查询所有尚未开通业务的帐号
	 * @param cateName
	 * @param ta_Account_cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryUnOpenAppTa_Account_cateNameforCount(@Param("cateName")String cateName,@Param("ta_Account_cateName")Ta_Account_cateName ta_Account_cateName);
	
	/**
	 * 获取新增主键
	 * @return
	 */
	public int findNewKeyId(@Param("cateName") String cateName);

	/**
	 * 查询机构下 的账号数量
	 * @author maojd
	 * date:2013-12-24 11:24
	 * 
	 * @param cateName终端名
	 * @param orgId	机构号
	 * @return 	机构数量
	 * 
	 */
	public int queryCountByOrgId(Map<String, String> map);
	
	/**
	 * 通过帐号来查询该帐号所属机构
	 * @return
	 */
	public String findOrgIdByAccount(@Param("cateName") String cateName,@Param("accountName") String accountName);
}
