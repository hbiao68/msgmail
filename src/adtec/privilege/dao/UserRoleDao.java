package adtec.privilege.dao;

import java.util.List;

import adtec.privilege.model.UserRole;

/**
 * 用户角色管理dao接口。直接操作数据库接口
 * @author maojd
 * @date 14:15 2014/2/25
 */
public interface UserRoleDao {

	/**
	 * 查询所有用户角色关系
	 * @return 用户角色结果集
	 */
	public List<UserRole> queryAllUserRole(UserRole userRole);

	/**
	 * 添加一条用户角色关联数据
	 * @param userRole 要添加的用户角色实体
	 */
	public void insertUserRole(UserRole userRole);

	/**
	 * 删除一条 用户角色关系数据
	 * @param userRole
	 */
	public void deleteUserRole(UserRole userRole);
	
	/**
	 * 通过对象条件查询 用户角色对象
	 * @param uerRole 查询条件 
	 * @return 符合条件的 用户角色结果集
	 */
	public List<UserRole> queryUserRoleByObj(UserRole userRole);

	/**
	 * 批量添加用户角色对象
	 * @param userRoleList 要添加的list合集（里面是用户角色对象）
	 */
	public void batchInsertUserRoles(List<UserRole> userRoleList);

	/**
	 * 验证用户角色对象是否存在（通过主键验证，或者 userid roleid综合验证）
	 * @param userRole
	 * @return 
	 */
	public List<UserRole> queryUserRoleIfExists(UserRole userRole);

	/**
	 * 查询用户角色数据总 数量(带模糊查询)
	 * @return  数量
	 */
	public int queryUserRoleCount(UserRole userRole);

	/**
	 * 批量删除用户角色对象
	 * @param idList 要删除的用户角色主键（ userRoleId）集合
	 */
	public void deleteUserRolesById(List<String> idList);

	/**
	 * 通过对象查询数量（主要用于验证  用户是否开通角色等）
	 * @param userRole
	 * @return
	 */
	public int queryCountByObj(UserRole userRole);
	
}
