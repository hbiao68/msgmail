package adtec.organizationManager.dao;

import java.util.List;

import java.util.Map;

import adtec.organizationManager.entity.Organization;

/**
 * OrganizationDao 机构等级Dao接口
 * @author maojd
 * @description Organization Grade Dao Interface 机构等级 Dao接口，对数据库操作
 */
public interface OrganizationDao {

	/**
	 * queryOrgLevelByOrg   由一条机构查询直接子机构
	 * @param org  one Organization 一条机构信息
	 * @return	   list,机构下所有子机构
	 */
	public List<Organization> queryOrgLevelByOrg(Organization org);
	
	/**
	 * queryAllOrg  查询所有机构
	 * @return 		返回查询结果List
	 */
	public List<Organization> queryAllOrg();
	
	/**
	 * queryOrgById	通过orgId查询一条机构信息
	 * @param orgId orgId主键，机构的唯一标识
	 * @return      返回List集合
	 */
	public List<Organization> queryOrgById(int orgId);

	/**
	 * addOrg 添加机构
	 * @param org 需要添加的机构实体
	 */
	public void addOrg(Organization org);
	
	/**
	 * batchInsertOrg 批量添加机构信息（eg:机构导入）
	 * @param list	  要添加的所有机构的List集合	
	 */
	public void batchInsertOrg(List<Organization> list);
	
	/**
	 * deleteOrgByOrgId 通过id删除一条机构记录
	 * @param orgId 机构的id
	 */
	public void deleteOrgByOrgId(int orgId);
	
	/**
	 * updateOrg 修改机构信息
	 * @param org	需要修改的机构实体
	 */
	public void updateOrg(Organization org);
	
	/**
	 * deleteAll 删除所有机构
	 */
	public void deleteAll();
	
	/**
	 * queryOrgName 通过机构id获取orgName
	 * @param orgName 机构名称
	 * @return
	 */
	public Map<String,Object> queryOrgName(String orgName);
	
	
	/**
	 * queryOrgRoot 查询root节点（查询全国这条记录）
	 * @return      List
	 */
	public List<Organization> queryOrgRoot();
	
	
	/**
	 * querySubOrgListByOrg 由一个机构查询 所有子机构（包括本身）
	 * @param org 作为查询条件的机构实体
	 * @return    所有子机构List结果集
	 */
	public List<Organization> querySubOrgListByOrg(Organization org);
	
	/**
	 * 通过层级结构获取主键
	 * 2014年2月19日 14:17:18
	 * @param map
	 * @return 主键
	 * @author lj
	 */
	public String queryorgIdByLevel(Map<String,String> map);
}
