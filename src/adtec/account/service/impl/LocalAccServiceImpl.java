package adtec.account.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.account.dao.AccountDao;
import adtec.account.model.Account;
import adtec.account.service.SwitchAccService;
import adtec.accountManager.controller.Md5Util;

import adtec.appManager.model.PageModel;

/**
 * @FileName: AccountServiceImpl
 * 
 * @FileType: Class
 * 
 * @Date: 2014年3月6日 2014年3月6日 18:32:34
 * 
 * @Author: ;lj
 * 
 * @Description: 帐号管理实现类（为发送消息使用）
 * 
 */
@Transactional(propagation=Propagation.REQUIRED)
public class LocalAccServiceImpl implements SwitchAccService {
	
	Logger log = Logger.getLogger(LocalAccServiceImpl.class);
	
	private AccountDao accountDao;
	
	public AccountDao getAccountDao() {
		return accountDao;
	}
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	/**
	 * 添加帐号信息
	 */
	@Override
	public boolean addAccount(Account account) throws Exception {
		try {
			//获取当前时间的毫秒数
			long now=System.currentTimeMillis();
			String creationdate = String.valueOf(now);
			String modificationdate = String.valueOf(now);
			//获取到前端的数据
			String username = account.getUsername();
			String name = account.getName();
			String email = account.getEmail();
			String pwd = account.getEncryptedpassword();
			//将页面段获取到的数据该去掉空格的去掉空格
			String usernameTrim = username.trim();
			String nameTrim = name.trim();
			String emailTrim = email.trim();
			//将密码进行md5加密
			String pwdMd5 = Md5Util.getMD5String(pwd);
			//将整理好的数据放入account对象中
			account.setUsername(usernameTrim);
			account.setCreationdate(creationdate);
			account.setEmail(emailTrim);
			account.setEncryptedpassword(pwdMd5);
			account.setModificationdate(modificationdate);
			account.setName(nameTrim);
			//掉用添加帐号的dao
			this.accountDao.insertAccount(account);
			log.debug("帐号添加成功！");
			return true;
		} catch (Exception e) {
			log.error("帐号添加失败！");
			throw e;
		}
	}
	/**
	 * 删除帐号
	 */
	@Override
	public boolean deleteAccountByAccount(String username) throws Exception {
		try {
			this.accountDao.deleteAccount(username);
			log.debug("帐号添加成功！");
			return true;
		} catch (Exception e) {
			log.error("帐号添加失败！");
			throw e;
		}
	}
	
	/**
	 * 帐号修改
	 */
	@Override
	public boolean updateAccountByAccount(Account account) throws Exception {
		try {
			String name = account.getName();
			String email = account.getEmail();
			String nameTrim = name.trim();
			String emailTrim = email.trim();
			account.setName(nameTrim);
			account.setEmail(emailTrim);
			this.accountDao.updateAccount(account);
			log.debug("帐号修改成功！");
			return true;
		} catch (Exception e) {
			log.error("帐号修改失败！");
			throw e;
		}
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public boolean updateAccountPwdByAccount(Account account) throws Exception {
		try {
			this.accountDao.updateAccountPwd(account);
			log.debug("密码修改成功！");
			return true;
		} catch (Exception e) {
			log.error("密码修改失败！");
			throw e;
		}
	}
	
	/**
	 * 分页查询数据
	 */
	@Override
	public Account queryAccountByAccount(Account account)
			throws Exception {
		try {
//			//获取条件查询的值
			String username = account.getUsername();
			String name = account.getName();
			String email = account.getEmail();
			if(username == null){
				username = "";
			}
			if(name == null){
				name = "";
			}
			if(email == null){
				email = "";
			}
			username = username.trim();
			name = name.trim();
			email = email.trim();
			PageModel pageModel = new PageModel();//account.getPageModel();
			int pageNow = 1;
			//int ass = account.getPageModel().getPageNow();
			// 获取当前页数
			if (pageModel.getPageNow() == 0
					|| "undefined".equals(pageModel.getPageNow())
					) {
				if (pageModel.getPageNow() == 0
						|| "undefined".equals(pageModel.getPageJump())
						) {
					pageNow = 1;
				} else {
					pageNow = pageModel.getPageJump();
				}

			} else {
				pageNow = pageModel.getPageNow();
			}
			// 每页显示的记录数
			pageModel.setPageNow(pageNow);
			int pageSize = pageModel.getPageSize();
			int start = pageModel.getStart();
			
			account.setUsername(username);
			account.setName(name);
			account.setEmail(email);
			account.setPageSize(pageSize);
			account.setStart(start);
			//获取所有数据的条数
			List<Account> accountCount = accountDao.queryAccountforCountByAccount(account);
			
			// 获取记录总数
			int count = accountCount.size();
			pageModel.setCount(count);
			// 获取总页数
			int pageCount = pageModel.getTotalPages();

			int pageUp = pageNow - 1;
			pageModel.setPageUp(pageUp);
			int pageDown = pageNow + 1;
			pageModel.setPageDown(pageDown);
			pageModel.setPageNow(pageNow);
			pageModel.setPageCount(pageCount);
			account.setPageModel(pageModel);
			List<Account> accountList = this.accountDao.queryAccountByAccount(account);
			account.setList(accountList);
			
			return account;
		} catch (Exception e) {
			log.error("分页查询数据失败！ queryAccountByAccount error");
			throw e;
		}
	}
	
	
	/**
	 * 单一数据查询
	 */
	@Override
	public Account findAccountByUsername(String username) throws Exception {
		try {
			return this.accountDao.findAccountByUsername(username);
		} catch (Exception e) {
			log.error("单一数据查询失败！ findAccountByUsername error");
			throw e;
		}
	}
	
	/**
	 * 通过username校验该帐号名是否存在
	 */
	@Override
	public int checkusernameCountByusername(String username) throws Exception {
		try {
			return this.accountDao.checkusernameCountByusername(username);
		} catch (Exception e) {
			log.error("查询失败！ checkusernameCountByusername error");
			throw e;
		}
	}
	
	/**
	 * 校验密码
	 */
	@Override
	public int checkEncryptedPassword(Account account) throws Exception {
		try {
			return this.accountDao.checkEncryptedPassword(account);
		} catch (Exception e) {
			log.error("查询失败！ checkEncryptedPassword error");
			throw e;
		}
	}

}
