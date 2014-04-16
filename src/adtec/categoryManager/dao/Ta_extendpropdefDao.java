package adtec.categoryManager.dao;

import adtec.categoryManager.model.Ta_extendpropdef;

/**
 * @FileName: Ta_extendpropdefDao
 * 
 * @FileType: Interface
 * 
 * @Date: 2013年10月13日
 * 
 * @Author: 李季
 * 
 * @Description: 端点描述接口
 * 
 */
public interface Ta_extendpropdefDao {

	/**
	 * 添加终端分类扩展属性的数据
	 * 
	 * @param ta_extendpropdef
	 */
	public void insertforTa_extendpropdef(Ta_extendpropdef ta_extendpropdef)throws Exception;
	
	/**
	 * 根据端点分类的主键（ta_id）,删除某些数据
	 * 
	 * @param ta_id
	 */
	public void deleteTa_extendpropdefByTa_id(String ta_id)throws Exception;
}
