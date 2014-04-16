package adtec.privilege.dao;

import java.util.List;

import adtec.privilege.model.User;

/**
 * 管理用户 操作数据库接口
 * @author maojd
 * @date 14:10 2014/2/20
 */
public interface UserDao {

	/**
	 * 查询所有用户(带分页)
	 * @param user 用户对象作为参数
	 * @return 查询的结果集
	 */
	public List<User> queryAllUser(User user);

	/**
	 * 查询用户总数（包括模糊查询）
	 * @return 总数量
	 */
	public int queryUserCount(User user);
	
	/**
	 * 添加用户
	 * @param user 要添加的用户实体
	 */
	public void insertUser(User user);

	/**
	 * 通过userid查询一条
	 * @param userid 用户id
	 * @return user对象
	 */
	
	public User queryUserByUserid(String userid);
	
	/**
	 * 用户的条件查询
	 * @param user 对象的属性做查询条件
	 * @return	List结果集
	 */
	public List<User> queryUserByObj(User user);

	/**
	 * 用户修改
	 * @param user 要修改的对象
	 */
	public void updateUser(User user);

	/**
	 * 删除用户
	 * @param user
	 */
	public void deleteUser(User user);

	/**
	 * 通过id批量删除
	 * @param userids 主键之间通过','隔开
	 */
	public void deleteUsersById(List<String> idList);
}
