package adtec.privilege.service;

import java.util.List;

import adtec.privilege.model.Role;
import adtec.privilege.model.User;
import adtec.privilege.model.UserRole;

/**
 * 用户角色管理service
 * @author maojd
 * @date 14:19 2014/2/25
 */
public interface UserRoleService {

	/**
	 * 查询所有用户角色
	 * @param userRole 查询条件（主要用分页page属性）
	 * @return 用户角色list结果集
	 * @throws Exception 查询出错
	 */
	public List<UserRole> queryAllUserRole(UserRole userRole) throws Exception;
	

	/**
	 * 查询所有角色 （在给用户添加角色时候）
	 * @return 角色结果集
	 * @throws Exception 查询出错
	 */
	public List<Role> queryAllRole() throws Exception;


	/**
	 * 给用户赋予角色
	 * @param userRole 要添加用户角色实体
	 * @return true添加成功，false 添加失败
	 */
	public boolean insertUserRole(UserRole userRole) throws Exception;


	/**
	 * 删除用户角色关系
	 * @param userRole
	 * @return true删除成功，false删除失败
	 */
	public boolean deleteUserRole(UserRole userRole) throws Exception;
	
	/**
	 * 条件查询
	 * @param userRole 对象作为查询条件
	 * @return 	符合条件的用户角色对象
	 * @throws Exception
	 */
	public List<UserRole> queryUserRoleByObj(UserRole userRole) throws Exception;

	
	/**
	 * 批量添加用户角色
	 * @param userRoleList
	 * @return
	 * @throws Exception
	 */
	public boolean batchInsertUserRoles(List<UserRole> userRoleList) throws Exception;


	/**
	 * 查询用户 角色对象是否存在
	 * @param userRoleNew
	 * @return true表示存在 false表示不存在
	 * @throws Exception
	 */
	public boolean queryUserRoleIfExists(UserRole userRole) throws Exception;


	/**
	 * 查询用户角色总数(带模糊查询)
	 * @return 总数量
	 * @throws Exception
	 */
	public int queryUserRoleCount(UserRole userRole) throws Exception;
	
	/**
	 * 通过对象查询数量（主要用于验证  用户是否开通角色等）
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	public int queryCountByObj(UserRole userRole) throws Exception;


	/**
	 * 批量删除用户角色对象
	 * @param userRoleids 要删除的ids(各个id直接通过 ',' 分隔)
	 * @return true 删除成功 false删除失败
	 */
	public boolean deleteUserRolesById(String userRoleids) throws Exception;
	
	/**
	 * 删除一个用户角色后，用户通过该角色获取的权限也删除
	 * @description 从全局的用户权限的 总map（userPrivlgIdsAllMap）中删除。
	 * @param rolePrivlg
	 * @return true移除成功 false移除出错
	 * @throws Exception
	 */
	public boolean removePlgFromUserPlgAllMap(UserRole userRole) throws Exception;

	
	/**
	 * 给用户赋予角色后，用户的权限相应增加
	 * @param userRole
	 */
	public boolean putPlgIntoUserPlgAllMap(UserRole userRole) throws Exception;

}
