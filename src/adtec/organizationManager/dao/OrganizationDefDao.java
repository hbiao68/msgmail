package adtec.organizationManager.dao;

import java.util.List;

import adtec.organizationManager.entity.OrganizationDef;

/**
 * OrganizationDefDao 机构分级设置
 * 机构等级信息分级说明
 * @author maojd
 * 
 */
public interface OrganizationDefDao {

	/**
	 * 取出所有等级表数据
	 * @return
	 */
	public List<OrganizationDef> queryAllOrgDef();
	
	/**
	 * 通过id取出一条数据
	 * @param level_id
	 * @return
	 */
	public List<OrganizationDef> queryOrgDefById(int level_id);

	/**
	 *  添加一个机构等级
	 * @param org 需要添加的机构等级
	 */
	public void addOrgDef(OrganizationDef org);

	/**
	 * 通过id删除一个机构等级
	 * @param id 机构等级id
	 */
	public void deleteOrgDefById(int id);

	/**
	 * 修改机构等级信息
	 * @param org 机构等级实体
	 */
	public void updateOrgDef(OrganizationDef org);
}
