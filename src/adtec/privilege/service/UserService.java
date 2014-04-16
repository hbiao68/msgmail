package adtec.privilege.service;

import java.util.List;

import adtec.privilege.model.User;

/**
 * 用户管理的service
 * @author maojd
 * @date 14:15 2014/2/20
 */
public interface UserService {

	/**
	 * 查询所有用户(带分页)
	 * @return 用户结果集
	 * @throws Exception 
	 */
	public List<User> queryAllUser(User user) throws Exception;

	/**
	 * 添加用户
	 * @param user 用户实体
	 * @return true表示添加成功，false表示失败
	 * @throws Exception 
	 */
	public boolean insertUser(User user) throws Exception;

	/**
	 * 通过条件查询user
	 * @param user user对象的属性作为查询条件
	 * @return 合格条件的 用户对象
	 * @throws Exception 
	 */
	public List<User> queryUserByObj(User user) throws Exception;
	
	/**
	 * 通过id查询一条
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public User queryUserByUserid(String userid) throws Exception;

	/**
	 * 用户的修改
	 * @param user 传入要修改的user实体
	 * @return true表示修改成功 false表示修改失败
	 */
	public boolean updateUser(User user) throws Exception;

	/**
	 * 用户的删除
	 * @param user 要删除的用户实体
	 * @return true删除成功  false删除失败
	 */
	public boolean deleteUser(User user) throws Exception;

	/**
	 * 查询用户总数(包括模糊查询)
	 * @return 
	 * @throws Exception
	 */
	public int queryUserCount(User user) throws Exception;

	/**
	 * 批量删除用户
	 * @param userids
	 * @return
	 * @throws Exception
	 */
	public boolean deleteUsersById(String userids) throws Exception;
}
