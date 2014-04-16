package adtec.appManager.service;

import adtec.appManager.model.Ta_App;
import adtec.categoryManager.model.Ta_category;

import java.util.*;

import org.springframework.transaction.annotation.Transactional;

/**
 * @FileName: AppManagerService
 * 
 * @FileType: Interface
 * 
 * @Date: 2013年10月21日
 * 
 * @Author: 李季
 * 
 * @Description: 业务管理功能点接口
 * 
 */
public interface AppManagerService {

	/**
	 * 添加业务
	 * 
	 * @param ta_App
	 * @return
	 */
	public boolean addforApp(Ta_App ta_App)throws Exception;

	/**
	 * 查询ta_id做下拉列表
	 * 
	 * @return
	 */
	public List<Ta_category> queryforcateName();

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
	 * @return
	 */
	@Transactional
	public boolean deleteAppById(int appid)throws Exception;

	/**
	 * 修改某一业务信息
	 * 
	 * @param ta_App
	 * @return
	 */
	@Transactional
	public boolean updateAppById(Ta_App ta_App)throws Exception;

	/**
	 * 为检查新添加业务检验
	 * 
	 * @param appName
	 * @return
	 */
	public int queryforcheckNewAppName(Ta_App ta_App);

	/**
	 * 批量删除方法
	 * 
	 * @param list
	 * @return
	 */
	@Transactional
	public boolean batchdelete(List<Integer> list)throws Exception;

	/**
	 * 通过appid查询ta_app_relation 表中的appid是否存在
	 * 
	 * @param appid
	 * @return
	 */
	public int queryForCheckAppid(int appid);

	/**
	 * 分页查询所有已经有帐号开通的业务
	 * 
	 * @param ta_App
	 * @return
	 */
	public List<Ta_App> queryOpenAppByTaApp(Ta_App ta_App);

	/**
	 * 分页查询所有没有帐号开通的业务
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
	 * 查询所有没有帐号开通的业务
	 * 
	 * @param ta_App
	 * @return
	 */
	public List<Ta_App> queryUnOpenAppforCountByTaApp(Ta_App ta_App);

	
	/**
	 * 
	 * 通过appid查询该业务下的机构数量（业务被多少机构使用）
	 * @author maojd
	 * date:15:41 2013/12/25
	 * @param appid 业务id
	 * @return	机构数量
	 */
	public int queryOrgNumByAppid(int appid);

	
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
