package adtec.privilege.service;

import java.util.List;

import adtec.privilege.model.Role;

/**
 * 角色管理的service
 * @author maojd
 * @date 11:20 2014/2/21
 */
public interface RoleService {

	/**
	 * 查询所有角色(带模糊查询)
	 * @return 查询结果集
	 */
	public List<Role> queryAllRole(Role role) throws Exception;
	
	/**
	 * 添加角色
	 * @param role
	 * @return true表示添加成功  false表示添加失败
	 */
	public boolean insertRole(Role role) throws Exception;
	
	/**
	 * 通过角色id查询一条
	 * @return 
	 * @throws Exception
	 */
	public Role queryRoleByRoleid(String roleid) throws Exception;
	
	/**
	 * 条件查询 通过一个role的相关属性，获取到一个role对象
	 * @param role 查询条件
	 * @return	
	 */
	public List<Role> queryRoleByObj(Role role) throws Exception;

	/**
	 * 角色修改
	 * @param role 角色实体
	 * @return true表示修改成功，false表示修改失败
	 */
	public boolean updateRole(Role role) throws Exception;

	/**
	 * 删除角色
	 * @param role 要删除的角色
	 * @return true表示修改成功，false表示修改失败
	 */
	public boolean deleteRole(Role role) throws Exception;

	/**
	 * 查询角色总数(带模糊查询)
	 * @return 总数个数
	 * @throws Exception
	 */
	public int queryRoleCount(Role role) throws Exception;

	/**
	 * 批量删除角色
	 * @param roleids 字符串 （主键通过','分隔）
	 * @return 
	 * @throws Exception
	 */
	public boolean deleteRolesById(String roleids) throws Exception;


	
}
