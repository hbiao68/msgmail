package adtec.organizationManager.service;

import java.util.List;
import java.util.Map;

import adtec.categoryManager.model.Ta_category;
import adtec.organizationManager.entity.Organization;

/**
 * OrganizationService 机构管理
 * @author maojd
 *@description 机构管理的service
 */
public interface OrganizationService {

	
	
	/**
	 * queryOrgLevelByOrg   由一条机构查询直接子机构（下一级）
	 * @param org  one Organization 一条机构信息
	 * @return	   list,机构下所有子机构
	 * @throws Exception 
	 */
	public List<Organization> queryOrgLevelByOrg(Organization org) throws Exception;
	
	/**
	 * queryAllOrg  查询所有机构
	 * @return 		返回查询结果List
	 * @throws Exception 查询所有结果出错
	 */
	public List<Organization> queryAllOrg() throws Exception;
	
	/**
	 * queryOrgById	通过orgId查询一条机构信息
	 * @param orgId orgId主键，机构的唯一标识
	 * @return      获取List的第一条 返回一条机构
	 * @throws Exception 抛出异常表示查询失败
	 */
	public Organization queryOrgById(int orgId) throws Exception;
	
	/**
	 * queryOrgRoot 查询root节点（查询全国这条记录）
	 * @return      List的第一条（也只有一条）Organization
	 * @throws Exception 查询root节点（查询全国这条记录）出错
	 */	
	public Organization queryOrgRoot() throws Exception;
	
	/**
	 * addOrg 添加机构
	 * @param org 需要添加的机构实体
	 * @return true表示添加成功      方法抛出异常表示添加失败（没有返回false）
	 * @throws Exception 
	 */
	public boolean addOrg(Organization org) throws Exception;
	
	/**
	 * batchInsertOrg 批量添加机构信息（eg:机构导入）
	 * @param list	  要添加的所有机构的List集合	
	 */
	public void batchInsertOrg(List<Organization> list) throws Exception;
	
	/**
	 * updateOrg 修改机构信息
	 * @param org	需要修改的机构实体
	 * @throws Exception 抛出异常表示修改机构失败
	 */
	public void updateOrg(Organization org) throws Exception;
	
	/**
	 * deleteOrg 通过id删除一条机构记录
	 * @param orgId 机构的id
	 * @return true表示删除成功		抛出异常表示删除失败（没有返回false）
	 * @throws Exception 
	 */
	public boolean deleteOrgByOrgId(int orgId) throws Exception;
	
	/**
	 * deleteAll 删除所有机构
	 * @throws Exception  抛出异常表示清除机构失败
	 */
	public boolean deleteAll() throws Exception;
	
	/**
	 * querySubOrgListByOrg 由一个机构查询 所有子机构（包括本身）
	 * @param org 作为查询条件的机构实体
	 * @return    所有子机构List结果集
	 * @throws Exception 由一个机构查询子机构出错
	 */
	public List<Organization> querySubOrgListByOrg(Organization org) throws Exception;
	
	/**
	 * queryAllcateName查询所有终端类型
	 * @return List 所用终端种类list集合
	 * @throws Exception 查询所有终端类型出错
	 */
	public List<Ta_category> queryAllcateName() throws Exception;
	
	/**
	 * 通过机构号 orgId 查询机构下的账号数量
	 * @author maojd
	 * date:2013-12-24 11:24
	 * @descrip map中 	("cateName",cateName)	("orgId",orgId)	
	 * @param cateName 终端名
	 * @param orgId	机构号
	 * @return 账号数量
	 * @throws Exception  查询机构下账号数量出错
	 */
	public int queryCountByOrgId(Map<String, String> map) throws Exception;
}
