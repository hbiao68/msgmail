package adtec.privilege.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.privilege.dao.UserDao;
import adtec.privilege.model.User;
import adtec.privilege.service.UserService;

@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImp implements UserService {

	private UserDao userDao;
	private Logger log = Logger.getLogger(UserServiceImp.class);
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public List<User> queryAllUser(User user) throws Exception{
		List<User> list = new ArrayList<User>();
		try {
			list = userDao.queryAllUser(user);
		} catch (Exception e) {
			log.error("queryAllUser error");
			throw e;
		}
		return list;
	}

	@Override
	public boolean insertUser(User user) throws Exception{
		boolean b = true;
		try {
			userDao.insertUser(user);
		} catch (Exception e) {
			log.error("insertUser error");
			b = false;
			throw e;
		}
		return b;
	}

	
	
	@Override
	public List<User> queryUserByObj(User user) throws Exception {
		List<User> list = new ArrayList<User>();
		try {
			list  = userDao.queryUserByObj(user);
			/*if(list.size()>0){
				userRes = list.get(0);
			}*/
		} catch (Exception e) {
			log.error("queryUserByObj error");
			throw e;
		}
		return list;
	}

	@Override
	public boolean updateUser(User user) throws Exception{
		boolean b = true;
		try {
			userDao.updateUser(user);
		} catch (Exception e) {
			b = false;
			log.error("updateUser error");
			throw e;
		}
		return b;
	}

	@Override
	public boolean deleteUser(User user) throws Exception{
		boolean b = true;
		try {
			userDao.deleteUser(user);
		} catch (Exception e) {
			b = false;
			log.error("deleteUser error");
			throw e;
		}
		return b;
	}

	@Override
	public User queryUserByUserid(String userid) throws Exception {
		User user = new User();
		try {
			user = userDao.queryUserByUserid(userid);
		} catch (Exception e) {
			log.error("queryUserByUserid error 通过userid查询用户出错");
			throw e;
		}
		return user;
	}

	/**
	 * 查询用户总数（包括模糊查询）
	 */
	@Override
	public int queryUserCount(User user) throws Exception {
		int count = 0;
		try {
			count = userDao.queryUserCount(user);
		} catch (Exception e) {
			log.error("queryUserCount error查询用户数量出错");
			e.printStackTrace();
		}
		
		return count;
	}

	/**
	 * 通过用户主键批量删除
	 * @param userids 主键数组
	 */
	@Override
	public boolean deleteUsersById(String userids) throws Exception {
		boolean b = true;
		try {
			List<String> idList = new ArrayList<String>();
			for(int i = 0; i < userids.split(",").length; i++ ){
				idList.add(userids.split(",")[i]);
			}
			userDao.deleteUsersById(idList);
		} catch (Exception e) {
			log.error("deleteUsersById error 批量删除用户出错");
			b = false;
			throw e;
		}
		return b;
	}

	
}
