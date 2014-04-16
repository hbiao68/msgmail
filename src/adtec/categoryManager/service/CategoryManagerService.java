package adtec.categoryManager.service;

import adtec.accountManager.model.Ta_Account_cateName;
import adtec.categoryManager.model.Ta_category;
import adtec.categoryManager.model.Ta_extendpropdef;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @FileName: CategoryManagerService
 * 
 * @FileType: Interface
 * 
 * @Date: 2013年10月17日
 * 
 * @Author: 李季
 * 
 * @Description: 端点分类管理功能点接口
 * 
 */
public interface CategoryManagerService {

	/**
	 * 删除方法（删除整条数据以及对应的两张表）
	 * 
	 * @param cateName
	 * @param ta_id
	 * @return
	 */
	public boolean delete(String cateName, String ta_id)throws Exception;

	/**
	 * 添加方法（添加一条终端类型数据的同时以数据的名称创建两张表）
	 * 
	 * @param ta_category
	 * @param ta_extendpropdef
	 * @param request
	 * @return
	 */

	public boolean add(Ta_category ta_category,
			Ta_extendpropdef ta_extendpropdef, HttpServletRequest request)throws Exception;

	/**
	 * 查询方法(查询所有终端类型数据)
	 * 
	 * @param ta_category
	 * @return
	 */
	public List<Ta_category> queryAllByTa_category(Ta_category ta_category);

	/**
	 * 查询某一终端的详细信息
	 * 
	 * @param ta_id
	 * @return
	 */
	public List<Ta_category> findAllByTa_id(String ta_id);

	/**
	 * 修改方法（修改终端表和扩张属性表这两张表里面所有允许修改的字段）
	 * 
	 * @param ta_category
	 * @param ta_extendpropdef
	 * @param request
	 * @param ta_id
	 * @return
	 */
	public boolean update(Ta_category ta_category,
			Ta_extendpropdef ta_extendpropdef, HttpServletRequest request,
			String ta_id)throws Exception;

	/**
	 * 对新添加的终端名称进行校验
	 * 
	 * @param cateName
	 * @return
	 */
	public int queryforCheckcateNameBycateName(String cateName);

	/**
	 * 条件查询时端点描述的下拉列表
	 * 
	 * @return
	 */
	public List<Ta_category> queryforcateDesc();

	/**
	 * 查询某一表（ta_account_XXX）是否有数据
	 * 
	 * @param cateName
	 * @return
	 */
	public List<Ta_Account_cateName> queryForTa_idCountBycateName(String cateName);

	/**
	 * 通过主键获取cateName
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
	 * 查询某一终端下是否有业务
	 */
	public int queryAppforTa_idCount(String ta_id);

}
