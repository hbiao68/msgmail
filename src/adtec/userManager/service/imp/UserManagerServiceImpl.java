package adtec.userManager.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.userManager.dao.UserDao;
import adtec.userManager.model.User;
import adtec.userManager.service.UserManagerService;

/**
 * @FileName: UserManagerServiceImpl
 * 
 * @FileType: Class
 * 
 * @Date: 2013年12月20日
 * 
 * @Author: 李季
 * 
 * @Description: 用户管理实现类
 * 
 */
@Transactional(propagation=Propagation.REQUIRED)
public class UserManagerServiceImpl implements UserManagerService {
	
	Logger log = Logger.getLogger(UserManagerServiceImpl.class);

	private UserDao userdao;
		

	
	public UserDao getUserdao() {
		return userdao;
	}



	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}



	/**
	 * 用户添加方法
	 */
	@Override
	public boolean add(User user) throws Exception{
		try{
			this.userdao.insert(user);
			log.debug("this.userdao.insert(user)  ok");
		}catch(Exception e){
			log.error("this.userdao.insert(user)  error");
			throw e;
		}
		return true;
	}
	
	/**
	 * 登陆校验
	 */
	@Override
	public int loginCheck(User user) {
		return this.userdao.loginCheck(user);
	}
	
	/**
	 * 校验用户名是否重复,如果重复返回true，不重复返回false
	 */
	@Override
	public boolean checkUserIsExistsByName(String name) {
		int result = this.userdao.checkUserIsExistsByName(name);
		if(result <= 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 删除某一用户
	 */
	@Override
	public boolean deleteUserById(int uid) throws Exception{
		try {
			this.userdao.deleteUserById(uid);
		} catch (Exception e) {
			log.error("删除用户失败");
			throw e;
		}
		return true;
	}
	/**
	 * 单一用户详细信息查询
	 */
	 @Override
	public User findUserById(int uid) throws Exception {
		User user = null;
		try {
			user = this.userdao.findUserById(uid);
			return user;
		} catch (Exception e) {
			log.error("根据id查询用户失败");
			throw e;
		}
	}
	 
	 /**
	  * 查询所有用户信息
	  */
	 @Override
	public List<User> queryUserListByUser(User user) {
		return this.userdao.query(user);
	}
	 
	 @Override
	public boolean updateUserByUser(User user) throws Exception{
		try {
			this.userdao.updateUserByUser(user);
		} catch (Exception e) {
			log.error("更新user失败");
			throw e;
		}
		return true;
	}
	 
	 /**
	  * 获取所有用户记录数
	  */
	@Override
	public int queryCount(User user) {
		return this.userdao.queryCount(user);
	}
	
	/**
	 * 校验输入的密码是否正确
	 */
	@Override
	public int queryPwd(Map map) {
		return this.userdao.queryPwd(map);
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public boolean UpdatePwd(User user) {
		this.userdao.UpdatePwd(user);
		return true;
	}
	
	/**
	 * 获取登陆者的信息
	 */
	@Override
	public User findUserByName(String userName) {
		return this.userdao.findUserByName(userName);
	}

}
