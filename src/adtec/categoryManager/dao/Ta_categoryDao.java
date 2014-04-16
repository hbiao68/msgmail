package adtec.categoryManager.dao;

import java.util.List;
import java.util.Map;

import adtec.categoryManager.model.Ta_category;

/**
 * 
 * @FileName: Ta_categoryDao
 * 
 * @FileType: Interface
 * 
 * @Date: 2013年10月13日
 * 
 * @Author: 李季
 * 
 * @Description: 端点信息接口
 */
public interface Ta_categoryDao {

	/**
	 * 添加终端类型
	 * 
	 * @param ta_category
	 */
	public void insertforTa_category(Ta_category ta_category);

	/**
	 * 查询所有数据
	 * 
	 * @param ta_category
	 * @return
	 */
	public List<Ta_category> queryAllByTa_category(Ta_category ta_category);

	/**
	 * 根据主键删除终端
	 * 
	 * @param ta_id
	 */
	public void deleteTa_categoryByTa_id(String ta_id);

	/**
	 * 更新数据
	 * 
	 * @param ta_category
	 */
	public void updateTa_categoryByTa_id(Ta_category ta_category)throws Exception;

	/**
	 * 单一数据查询
	 * 
	 * @param ta_id
	 * @return
	 */
	public List<Ta_category> findAllByTa_id(String ta_id);

	/**
	 * 创建TA_Account_XXX表
	 * 
	 * @param TA_Account
	 */
	public void createTA_Account(String TA_Account);

	/**
	 * 创建accoutName为索引
	 * 
	 * @param TA_Account
	 */
	public void createTA_Account_accountName(String TA_Account);

	/**
	 * 创建TA_ExtendProp_XXX表
	 * 
	 * @param TA_Account
	 */
	public void createTA_ExtendProp(String TA_Account);

	/**
	 * 删除TA_Account_XXX表
	 * 
	 * @param TA_Account
	 */
	public void delTA_Account(String TA_Account);

	/**
	 * 删除TA_ExtendProp_XXX表
	 * 
	 * @param TA_Account
	 */
	public void delTA_ExtendProp(String TA_Account);

	/**
	 * 查询出cateName(也就是端点名称)
	 * 
	 * @return
	 */
	public List<Ta_category> queryforcateName();

	/**
	 * 通过端点的名称(cateName)来获取端点的主键（ta_id）
	 * 
	 * @param cateName
	 * @return
	 */
	public Ta_category queryforta_idBycateName(String cateName);

	/**
	 * 对新添加的端点进行校验
	 * 
	 * @param cateName
	 * @return
	 */
	public int queryforCheckcateNameBycateName(String cateName);

	/**
	 * 通过主(ta_id)键获取cateName
	 * 
	 * @param ta_id
	 * @return
	 */
	public Ta_category findcateNameByta_id(String ta_id);

	/**
	 * 查询所有的记录数
	 * 
	 * @param ta_category
	 * @return
	 */
	public int queryCount(Ta_category ta_category);

	
	/**
	 * 通过ta_id查询终端描述(终端的名称)
	 * @author maojd
	 * date:16:08 2013/12/26
	 * @param ta_id	终端id
	 * @return	终端描述
	 * 
	 */
	public String queryCateDescByTa_id(String ta_id);
}
