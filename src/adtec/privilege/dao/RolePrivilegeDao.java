package adtec.privilege.dao;

import java.util.List;

import adtec.privilege.model.RolePrivilege;

/**
 * 角色、权限管理dao层接口、直接操作数据库
 * @author maojd
 * @date 11:02 2014/2/27
 */
public interface RolePrivilegeDao {

	/**
	 * 查询所有角色、权限关系数据
	 * @param rolePrivlg 
	 * @return list结果集
	 */
	public List<RolePrivilege> queryAllRolePrivilege(RolePrivilege rolePrivlg);

	/**
	 * 添加角色、权限关系数据
	 * @param rolePrivilege 要添加的实体
	 */
	public void insertRolePrivilege(RolePrivilege rolePrivilege);

	/**
	 * 删除角色、权限数据
	 * @param rolePrivilege 要删除的实体
	 */
	public void deleteRolePrivilege(RolePrivilege rolePrivilege);
	
	/**
	 * 角色权限条件查询
	 * @param rolePrivilege
	 * @return 符合条件的 角色权限结果集
	 */
	public List<RolePrivilege> queryRolePrivilegeByObj(RolePrivilege rolePrivilege);

	/**
	 * 查询 角色权限总数（包括模糊查询）
	 * @return 角色权限总数
	 */
	public int queryRolePrivlgCount(RolePrivilege rolePrivilege);

	/**
	 * 批量删除角色权限 
	 * @param idList id集合
	 */
	public void deleteRolePrivlgsById(List<String> idList);

	/**
	 * 批量添加角色权限
	 * @param rolePrivlgList 要添加的角色 权限集合
	 */
	public void batchInsertRolePrivlgs(List<RolePrivilege> rolePrivlgList);

	/**
	 * 通过对象查询数量（主要用于验证  eg:验证权限是否被使用）
	 * @param rolePrivilege
	 * @return 
	 */
	public int queryCountByObj(RolePrivilege rolePrivilege);
}
