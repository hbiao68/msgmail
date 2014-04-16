package adtec.userManager.dao;

import adtec.userManager.model.User;

import java.util.List;

import java.util.Map;


/**
 * 
 * @FileName:      UserDao
 *
 * @FileType:      Interface
 *
 * @Date:          2013年12月20日
 * 
 * @Author:        李季
 * 
 * @Description:   用户管理
 */
public interface UserDao {

	/**
	 * 添加新用户
	 * @param cateName
	 * @param ta_Account_cateName
	 */
	public void insert(User user) throws Exception ;
	
	/**
	 * 登陆校验
	 * @param user
	 * @return
	 */
	public int loginCheck(User user);
	
	/**
	 * 校验用户名是否重复
	 * @param user
	 * @return
	 */
	public int checkUserIsExistsByName(String username);
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> query(User user);
	
	/**
	 * 单一用户查询
	 * @param uid
	 * @return
	 */
	public User findUserById(int uid) throws Exception;
	
	/**
	 * 根据传递过来的对象修改user
	 * @param user 需要修改的内容
	 * @return
	 */
	public void updateUserByUser(User user) throws Exception;
	
	/**
	 * 删除某一用户
	 * @param uid
	 * @return
	 */
	public void deleteUserById(int uid) throws Exception;
	
	/**
	 * 获取总的记录数
	 * @param user
	 * @return
	 */
	public int queryCount(User user);
	
	/**
	 * 校验输入的密码是否正确
	 * @return
	 */
	public int queryPwd(Map map);
	
	/**
	 * 修改密码
	 * @param user
	 */
	public void UpdatePwd(User user);
	
	/**
	 * 获取登陆者的信息
	 * @param userName
	 * @return
	 */
	public User findUserByName(String userName);

}
