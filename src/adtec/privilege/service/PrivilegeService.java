package adtec.privilege.service;

import java.util.List;

import adtec.privilege.model.Privilege;
import adtec.privilege.model.Resource;


/**
 * 权限管理service接口
 * @author maojd
 * @date 14:35 2014/2/24
 */
public interface PrivilegeService {

	/**
	 * 查询所有权限
	 * @return true查询成功，false查询失败
	 */
	public List<Privilege> queryAllPrivilege(Privilege privilege) throws Exception;

	/**
	 * 查询资源
	 * @return 资源list结果集
	 * @throws Exception
	 */
	public List<Resource> queryAllResources() throws Exception;

	/**
	 * 添加权限
	 * @param privilege
	 * @return
	 */
	public boolean insertPrivilege(Privilege privilege) throws Exception;

	

	/**
	 * 通过权限id查询 权限及资源名
	 * @param string
	 * @return
	 */
	public Privilege queryPrivilegeById(String privilegeId) throws Exception;



	/**
	 * 权限的修改
	 * @param privilege
	 * @return 
	 */
	public boolean updatePrivilege(Privilege privilege) throws Exception;

	/**
	 * 删除一条 权限记录
	 * @param privilege 要删除的权限实体
	 * @return 
	 */
	public boolean deletePrivilege(Privilege privilege) throws Exception;

	/**
	 * 查询权限总数量（包括模糊查询）
	 * @return 数量
	 * @throws Exception
	 */
	public int queryPrivlgCount(Privilege privlg) throws Exception;

	/**
	 * 批量添加权限
	 * @param privlgList 要添加的list集合
	 * @return true表示添加成功 false添加失败
	 */
	public boolean batchInsertPrivilege(List<Privilege> privlgList) throws Exception;

	/**
	 * 通过主键 privilegeid 或者   资源id 、操作类型 验证是否存在
	 * @param privilege
	 * @return
	 */
	public boolean queryPrivlgIfExists(Privilege privilege) throws Exception;

	/**
	 * 批量删除
	 * @param privilegeids
	 * @return true删除成功 false删除失败
	 */
	public boolean deletePrivilegesById(String privilegeids) throws Exception;

	/**
	 * 通过对象查询数量（主要用于验证，eg:验证资源是否开通了某些权限）
	 * @param privilege
	 * @return
	 */
	public int queryCountByObj(Privilege privilege) throws Exception;
	
}
