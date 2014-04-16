package adtec.privilege.dao;

import java.util.List;

import adtec.privilege.model.Role;

/**
 * 角色管理直接操作数据库接口
 * @author maojd
 * @date 11:19 2014/2/21
 */
public interface RoleDao {
	
	/**
	 * 查询所有角色
	 * @return 查询的结果集
	 */
	public List<Role> queryAllRole(Role role);

	/**
	 * 添加角色
	 * @param role 要添加的实体
	 */
	public void insertRole(Role role);
	
	/**
	 * 通过id查询一条角色
	 * @param roleid 角色id
	 * @return 查询的角色实体
	 */
	public Role queryRoleByRoleid(String roleid);

	/**
	 * 角色的条件查询
	 * @param role 角色对象作为查询条件
	 * @return List结果集
	 */
	public List<Role> queryRoleByObj(Role role);

	/**
	 * 角色修改
	 * @param role 角色实体
	 */
	public void updateRole(Role role);

	/**
	 * 删除角色
	 * @param role 要删除的角色
	 */
	public void deleteRole(Role role);

	/**
	 * 查询角色总数
	 * @return
	 */
	public int queryRoleCount(Role role);

	/**
	 * 批量删除角色
	 * @param idList 存放roleid的list
	 */
	public void deleteRolesById(List<String> idList);

	
	
	
}
