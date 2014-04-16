package adtec.privilege.dao;

import java.util.List;

import adtec.privilege.model.UserPrivilege;

/**
 * 用户权限 操作借口
 * @author maojd
 * @date 15:37 2014/2/20
 */
public interface UserPrivilegeDao {

	/**
	 * 查询所有 用户、权限关系
	 * @param userPrivilege 用户、权限对象做为查询条件（分页要用）
	 * @return 用户、权限关系list结果集
	 */
	public List<UserPrivilege> queryAllUserPrivilege(UserPrivilege userPrivilege);

	/**
	 * 删除一条用户、权限关系数据（通过主键id删除）
	 * @param userPrivilege
	 */
	public void deleteUserPrivilege(UserPrivilege userPrivilege);

	/**
	 * 添加一条用户、权限关系数据
	 * @param userPrivilege 要添加的实体
	 */
	public void insertUserPrivilege(UserPrivilege userPrivilege);

	/**
	 * 查询用户权限总数量（带模糊查询）
	 * @return 数量
	 */
	public int queryUserPrivCount(UserPrivilege userPrivilege);

	
	/**
	 * 查询用户、权限是否存在（通过主键 或者  用户id 权限id综合验证）
	 * @param userPrivlg 要验证是否存在的用户权限对象
	 * @return 查询的结果集 （空表示不存在，否则表示存在）
	 */
	public List<UserPrivilege> queryUserPrivlgIfExists(UserPrivilege userPrivlg);

	/**
	 * 批量添加用户、权限
	 * @param userPrivlgList
	 */
	public void batchInsertUserPrivlgs(List<UserPrivilege> userPrivlgList);

	/**
	 * 批量删除用户权限 
	 * @param idList 主键集合
	 */
	public void deleteUserPrivlgsById(List<String> idList);

	/**
	 * 通过对象，查询数量（主要用于验证权限等是否被其他表使用）
	 * @param userPrivlg
	 * @return 
	 */
	public int queryCountByObj(UserPrivilege userPrivlg);
	
}
