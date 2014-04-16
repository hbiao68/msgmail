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
public class ExternalAccServiceImpl implements SwitchAccService {
	
	Logger log = Logger.getLogger(ExternalAccServiceImpl.class);
	
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
			return true;
	}
	/**
	 * 删除帐号
	 */
	@Override
	public boolean deleteAccountByAccount(String username) throws Exception {
			return true;

	}
	
	/**
	 * 帐号修改
	 */
	@Override
	public boolean updateAccountByAccount(Account account) throws Exception {
			return true;
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public boolean updateAccountPwdByAccount(Account account) throws Exception {

			return true;
	}
	
	/**
	 * 分页查询数据
	 */
	@Override
	public Account queryAccountByAccount(Account account)
			throws Exception {
		return null;
	}
	
	
	/**
	 * 单一数据查询
	 */
	@Override
	public Account findAccountByUsername(String username) throws Exception {
		return null;
	}
	
	/**
	 * 通过username校验该帐号名是否存在
	 */
	@Override
	public int checkusernameCountByusername(String username) throws Exception {
		return 0;
	}
	
	/**
	 * 校验密码
	 */
	@Override
	public int checkEncryptedPassword(Account account) throws Exception {
		return 0;
	}

}
