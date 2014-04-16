package adtec.organizationManager.service;

import java.util.List;
import adtec.organizationManager.entity.OrganizationDef;

/**
 * OrganizationDefService 机构等级说明service
 * 
 * @author maojd
 * 
 */
public interface OrganizationDefService {

	/**
	 * 查询所有机构等级
	 * @return
	 */
	public List<OrganizationDef> queryAllOrgDef() throws Exception;
    /**
     * 通过id取出一条 机构等级
     * @param level_id
     * @return
     */
	public OrganizationDef queryOrgDefById(int level_id) throws Exception;
	/**
	 * 添加一个机构等级
	 * @param org 机构等级实体
	 * @throws Exception 
	 */
	public boolean addOrgDef(OrganizationDef org) throws Exception;

	/**
	 * 通过id删除一个机构等级
	 * @param id 机构等级id
	 */
	public boolean deleteOrgDefById(int id) throws Exception;

	/**
	 * 修改机构等级orgDef
	 * @param org 机构等级实体
	 */
	public boolean updateOrgDef(OrganizationDef org) throws Exception;

}
