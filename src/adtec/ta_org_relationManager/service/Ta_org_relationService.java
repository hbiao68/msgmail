package adtec.ta_org_relationManager.service;

import java.util.*;

import adtec.appManager.model.Ta_App;
import adtec.categoryManager.model.Ta_category;
import adtec.organizationManager.entity.Organization;
import adtec.ta_org_relationManager.model.Ta_org_relation;

import org.springframework.transaction.annotation.Transactional;

/**
 * @FileName: Ta_org_relationService
 * 
 * @FileType: Interface
 * 
 * @Date: 2013年12月06日
 * 
 * @Author: 李季
 * 
 * @Description: 机构业务开通service接口
 * 
 */
public interface Ta_org_relationService {
	/**
	 * 添加方法,为机构开通业务
	 * 
	 * @param list
	 * @return
	 */
	public boolean batchInsert(Map<String,Object> insOrgId,List<Ta_org_relation> list)throws Exception;
	
	/**
	 * 查询出所有业务为业务开通使用
	 * 
	 * @param ta_id
	 * @return
	 */
	public List<Ta_App> queryforRelation(String ta_id);
	
	/**
	 * 由一个机构 查询所有子机构（包括本身）
	 * @param org
	 * @return
	 */
	public List<Organization> querySubOrgListByOrg(Organization org);
	
	/**
	 * 通过id查询
	 * @param orgId
	 * @return
	 */
	public Organization queryOrgById(String orgId);
	
	/**
	 * 通过应用查询所有的机构
	 * @return
	 */
	public List<Ta_org_relation> queryOrgByApp(Map<String,Object> map);
	
	/**
	 * 查询出ta_id(也就是端点id)为端点管理的选择表做数据
	 * 
	 * @return
	 */
	public List<Ta_category> queryforcateName();
	
	/**
	 * 查询出所有的业务
	 * @return
	 */
	public List<Ta_App> queryAllAppNameByTa_id(String ta_id);
	
	/**
	 * 通过业务查询所有的机构
	 * @param ta_org_relation
	 * @return
	 */
	public List<Ta_org_relation> queryAllByApp(Map<String,Object> map);
	
	/**
	 * 通过appid查询出某一业务的所有信息
	 * @param appid
	 * @return
	 */
	public Ta_App findAppByAppId(int appid);
	
	/**
	 * 删除机构已开通的业务
	 * @param list
	 */
	public boolean batchDelete(Map<String,Object> map)throws Exception;
	

}
