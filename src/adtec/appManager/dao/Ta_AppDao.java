package adtec.appManager.dao;

import java.util.List;
import java.util.Map;

import adtec.appManager.model.Ta_App;

/**
 * 
 * @FileName: Ta_AppDao
 * 
 * @FileType: Interface
 * 
 * @Date: 2013年10月21日
 * 
 * @Author: 李季
 * 
 * @Description: 业务信息接口
 */
public interface Ta_AppDao {

	/**
	 * 添加业务
	 * 
	 * @param ta_App
	 */
	public void insertApp(Ta_App ta_App)throws Exception;

	/**
	 * 单一业务查询
	 * 
	 * @param appid
	 * @return
	 */
	public Ta_App findAppById(int appid);

	/**
	 * 删除业务
	 * 
	 * @param appid
	 */
	public void deleteAppById(int appid)throws Exception;

	/**
	 * 修改业务
	 * 
	 * @param ta_App
	 */
	public void updateAppById(Ta_App ta_App)throws Exception;

	/**
	 * 查询业务为业务开通使用
	 * 
	 * @param ta_id
	 * @return
	 */
	public List<Ta_App> queryforRelation(String ta_id);

	/**
	 * 为检查新添加业务检验
	 * 
	 * @param appName
	 * @return
	 */
	public int queryforcheckNewAppName(Ta_App ta_App);

	/**
	 * 批量删除数据
	 * 
	 * @param list
	 */
	public void batchdelete(List<Integer> list)throws Exception;

	/**
	 * 分页查询所有已经有帐号开通的业务
	 * 
	 * @param ta_App
	 * @return
	 */
	public List<Ta_App> queryOpenAppByTaApp(Ta_App ta_App);

	/**
	 * 分页查询所有没有有帐号开通的业务
	 * 
	 * @param ta_App
	 * @return
	 */
	public List<Ta_App> queryUnOpenAppByTaApp(Ta_App ta_App);

	/**
	 * 分页查询所有业务
	 * 
	 * @param ta_App
	 * @return
	 */
	public List<Ta_App> queryAppByTaApp(Ta_App ta_App);


	/**
	 * 查询所有业务
	 * 
	 * @param ta_App
	 * @return
	 */
	public List<Ta_App> queryAPPforCountByTaApp(Ta_App ta_App);

	/**
	 * 查询所有已经有帐号开通的业务
	 * 
	 * @param ta_App
	 * @return
	 */
	public List<Ta_App> queryOpenAppforCountByTaApp(Ta_App ta_App);

	/**
	 * 查询所有没有有帐号开通的业务
	 * 
	 * @param ta_App
	 * @return
	 */
	public List<Ta_App> queryUnOpenAppforCountByTaApp(Ta_App ta_App);
	
	/**
	 * 查询所有的业务
	 * @return
	 */
	public List<Ta_App> queryAllAppNameByTa_id(String ta_id);
	
	/**
	 * 查询某一终端下是否有业务
	 * @param ta_id
	 * @return
	 */
	public int queryAppforTa_idCount(String ta_id);

}
