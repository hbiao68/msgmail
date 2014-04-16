package adtec.privilege.service;

import java.util.List;

import adtec.privilege.model.RolePrivilege;

/**
 * 角色、权限关联 service层接口
 * @author maojd 
 * @date 11:07 2014/2/27
 */
public interface RolePrivilegeService {

	/**
	 * 查询所有角色、权限数据
	 * @return 角色、权限实体的list结果集
	 */
	public List<RolePrivilege> queryAllRolePrivilege(RolePrivilege rolePrivlg) throws Exception;

	/**
	 * 添加用户、角色数据
	 * @param rolePrivilege 数据实体
	 * @return true添加成功 false添加失败
	 */
	public boolean insertRolePrivilege(RolePrivilege rolePrivilege) throws Exception;

	/**
	 * 删除角色、权限数据
	 * @param rolePrivilege 要删除的数据实体
	 * @return true删除成功 false删除失败
	 * @throws Exception
	 */
	public boolean deleteRolePrivilege(RolePrivilege rolePrivilege) throws Exception;
	
	/**
	 * 条件查询角色权限
	 * @param rolePrivilege  对象作为查询条件
	 * @return 返回符合条件的结果
	 * @throws Exception
	 */
	public List<RolePrivilege> queryRolePrivilegeByObj(RolePrivilege rolePrivilege) throws Exception;

	/**
	 * 查询 角色、权限总数
	 * @return 总数量
	 * @throws Exception
	 */
	public int queryRolePrivlgCount(RolePrivilege rolePrivilege) throws Exception;
	
	/**
	 * 批量删除 角色、权限 通过id 
	 * @param ids 拼接的字符串(每个id直接通过","分割)
	 * @return true表示批量删除成功  false表示批量删除失败
	 */
	public boolean deleteRolePrivlgsById(String ids) throws Exception;

	/**
	 * 批量添加角色权限
	 * @param rolePrivlgList 要添加的 角色权限list集合
	 * @return true添加成功 false添加失败
	 * @throws Exception 
	 */
	public boolean batchInsertRolePrivlgs(List<RolePrivilege> rolePrivlgList) throws Exception;

	/**
	 * 给角色授权，相应的用户可能会获取更多的权限，添加到总的userPrivlgIdsAllMap 用户、权限中
	 * @param rolePrivlg
	 * @return
	 * @throws Exception
	 */
	public boolean putPlgIntoUserPlgAllMap(RolePrivilege rolePrivlg) throws Exception;

	/**
	 * 把角色的权限删除，对应的userPrivlgIdsAllMap 总的用户权限map中也删除用户对应的权限
	 * @param rolePrivlg
	 * @return
	 * @throws Exception
	 */
	public boolean removePlgFromUserPlgAllMap(RolePrivilege rolePrivlg) throws Exception;

	/**
	 * 通过对象查询数量（主要用于验证是否被其他表使用）
	 * @param rolePrivilege
	 * @return 数量
	 * @throws Exception
	 */
	public int queryCountByObj(RolePrivilege rolePrivilege) throws Exception;
}
