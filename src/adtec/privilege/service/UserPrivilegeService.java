package adtec.privilege.service;

import java.util.List;
import java.util.Map;

import org.apache.catalina.mbeans.RoleMBean;

import adtec.privilege.model.Privilege;
import adtec.privilege.model.RolePrivilege;
import adtec.privilege.model.User;
import adtec.privilege.model.UserPrivilege;

/**
 * 用户、权限 关联表的服务层接口
 * @author maojd
 * @date 14:41 2014/2/26
 */

public interface UserPrivilegeService {

	/**
	 * 查询所有用户、权限（权限关联资源） 关系  带分页
	 * @return
	 */
	public List<UserPrivilege> queryAllUserPrivilege(UserPrivilege userPrivilege) throws Exception;

	/**
	 * 删除 一条用户、权限 关系记录
	 * @param userPrivilege 
	 * @return true删除成功 false删除失败
	 * @throws Exception
	 */
	public boolean deleteUserPrivilege(UserPrivilege userPrivilege) throws Exception;

	/**
	 * 添加一条用户、权限数据
	 * @param userPrivilege 要添加的实体
	 * @return	true添加成功 false添加失败
	 */
	public boolean insertUserPrivilege(UserPrivilege userPrivilege) throws Exception;
	
	
	/**
	 * 通过角色查询用户权限
	 * @param userList 用户集合
	 * @return	userPrivilegeList用户权限集合
	 * @throws Exception
	 */
	/*public List<UserPrivilege> queryUserPrivilegeByRole(List<User> userList) throws Exception;*/

	/**
	 * 查询角色权限总数量(带模糊查询)
	 * @return 数量值
	 * @throws Exception
	 */
	public int queryUserPrivCount(UserPrivilege userPrivilege) throws Exception;
	
	/**
	 * 通过对象，查询数量（主要用于验证权限等是否被其他表使用）
	 * @return 数量
	 * @throws Exception
	 */
	public int queryCountByObj(UserPrivilege userPrivlg) throws Exception;
	/**
	 * 验证用户权限对象是否存在 （通过主键验证 或者通过  userid privilegeid综合验证）
	 * @param userPrivlg 要验证的对象
	 * @return true表示存在，false表示不存在
	 * @throws Exception
	 */
	public boolean queryUserPrivlgIfExists(UserPrivilege userPrivlg) throws Exception;

	/**
	 * 用户权限的批量添加
	 * @param userPrivlgList 要添加的用户、权限集合
	 * @return true表示批量添加成功  false表示添加失败
	 */
	public boolean batchInsertUserPrivlgs(List<UserPrivilege> userPrivlgList) throws Exception;

	/**
	 * 用户 权限的批量删除
	 * @param ids 各个id之间通过","分隔
	 * @return true表示删除成功  false删除失败
	 * @throws Exception
	 */
	public boolean deleteUserPrivlgsById(String userPrivlgIds) throws Exception;

	/**
	 * 查询用户角色权限 即：用户的权限 和 通过角色获取的权限
	 * @return
	 * @throws Exception
	 */
	public List<UserPrivilege> queryAllUserRolePrivilege() throws Exception;


	/**
	 * 通过用户查询用户的所有权限
	 * @return 用户的权限结果集合
	 * @throws Exception
	 */
	public Map<String, Privilege> queryUserAllPrivlg(User user) throws Exception;
	
	
}
