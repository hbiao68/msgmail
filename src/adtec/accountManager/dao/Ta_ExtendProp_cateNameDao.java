package adtec.accountManager.dao;


import org.apache.ibatis.annotations.Param;
import adtec.accountManager.model.Ta_ExtendProp_cateName;

/**
 * 
 * @FileName: Ta_ExtendProp_cateNameDao
 * 
 * @FileType: Interface
 * 
 * @Date: 2013年10月17日
 * 
 * @Author: 李季
 * 
 * @Description: 端点信息接口
 */
public interface Ta_ExtendProp_cateNameDao {

	/**
	 * 添加帐号的扩张属性
	 * 
	 * @param cateName
	 * @param ta_ExtendProp_cateName
	 */
	public void insertforTa_ExtendProp_cateName(
			@Param("cateName") String cateName,
			@Param("ta_ExtendProp_cateName") Ta_ExtendProp_cateName ta_ExtendProp_cateName)throws Exception;

	/**
	 * 修改帐号的扩张属性
	 * 
	 * @param cateName
	 * @param ta_ExtendProp_cateName
	 */
	public void updateforTa_ExtendProp_cateName(
			@Param("cateName") String cateName,
			@Param("ta_ExtendProp_cateName") Ta_ExtendProp_cateName ta_ExtendProp_cateName)throws Exception;
}
