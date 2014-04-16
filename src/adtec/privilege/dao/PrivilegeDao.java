package adtec.privilege.dao;

import java.util.List;

import adtec.privilege.model.Privilege;

/**
 * 权限管理dao 直接操作数据库
 * @author maojd
 * @dete 14:25 2014/2/24
 */
public interface PrivilegeDao {

	/**
	 * 查询所有权限
	 * @return 查询的结果集
	 */
	public List<Privilege> queryAllPrivilege(Privilege privilege);

	/**
	 * 添加权限
	 * @param privilege
	 */
	public void insertPrivilege(Privilege privilege);

	/**
	 * 通过id查询权限
	 * @param privilegeId 权限主键id
	 * @return 查询出来的权限实体
	 */
	public Privilege queryPrivilegeById(String privilegeid);
	
	/**
	 * 通过权限对象查询
	 * @param privilege 查询条件可以是 主键，或者 资源id ，或者操作类型id。只要条件存在，查询时候，该条件就生效
	 * @return
	 */
	public List<Privilege> queryPrivilegeByObj(Privilege privilege);

	/**
	 * 修改权限
	 * @param privilege 权限记录实体
	 */
	public void updatePrivilege(Privilege privilege);

	/**
	 * 删除一条权限记录
	 * @param privilege 权限记录实体（通过主键id删除）
	 */
	public void deletePrivilege(Privilege privilege);

	/**
	 * 查询权限总数量(包括模糊查询)
	 * @return 数量
	 */
	public int queryPrivlgCount(Privilege privlg);

	/**
	 * 批量添加权限
	 * @param privlgList 权限的实体集合
	 */
	public void batchInsertPrivilege(List<Privilege> privlgList);

	/**
	 * 通过主键 privilegeid 或者   通过 资源id 、操作类型  综合查询（验证是否存在）
	 * @return 查询的list结果集
	 */
	public List<Privilege> queryPrivlgIfExists(Privilege privilege);

	/**
	 * 批量删除权限
	 * @param idList 要删除的权限id集合
	 */
	public void deletePrivilegesById(List<String> idList);

	/**
	 * 通过对象查询数量（主要用于验证，eg:验证资源是否开通了某些权限）
	 * @param privilege
	 * @return
	 */
	public int queryCountByObj(Privilege privilege);


	
}
