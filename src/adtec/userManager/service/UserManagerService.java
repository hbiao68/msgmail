package adtec.userManager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import adtec.userManager.model.User;

import java.util.Map;

/**
 * @FileName: UserManagerService
 * 
 * @FileType: Interface
 * 
 * @Date: 2013年12月20日
 * 
 * @Author: 李季
 * 
 * @Description: 用户管理
 * 
 */
public interface UserManagerService {


	/**
	 * 
	 * 添加用户方法
	 * 
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	
	@Transactional
	public boolean add(User user) throws Exception;
	
	/**
	 * 登陆校验
	 * @param user
	 * @return
	 */
	
	public int loginCheck(User user);

	/**
	 * 校验用户名是否重复,如果重复返回true，不重复返回false
	 * @param name
	 * @return
	 */
	public boolean checkUserIsExistsByName(String name);
	
	/**
	 * 查询所有用户信息
	 * @param user
	 * @return
	 */
	public List<User> queryUserListByUser(User user);
	
	/**
	 * 单一用户信息查询
	 * @param uid
	 * @return
	 */
	public User findUserById(int uid) throws Exception;
	

	/**
	 * 根据传递过来的对象修改user
	 * @param user 需要修改的内容
	 * @return
	 */
	public boolean updateUserByUser(User user) throws Exception;
	
	/**
	 * 删除某一用户
	 * @param uid
	 * @return
	 */
	
	public boolean deleteUserById(int uid) throws Exception;
	
	/**
	 * 查询所有的记录数
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
	public boolean UpdatePwd(User user);
	
	/**
	 * 获取登陆者的信息
	 * @param userName
	 * @return
	 */
	
	public User findUserByName(String userName);

	
}
