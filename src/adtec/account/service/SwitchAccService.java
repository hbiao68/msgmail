package adtec.account.service;

import java.util.List;

import adtec.account.model.Account;

/**
 * @FileName: AccountService
 * 
 * @FileType: Interface
 * 
 * @Date:  2014年3月6日 18:29:10
 * 
 * @Author: lj
 * 
 * @Description: 帐号管理接口（为发送消息使用）
 * 
 */
public interface SwitchAccService {
	
	
	/**
	 * 添加帐号信息
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public boolean addAccount(Account account)throws Exception;
	
	/**
	 * 删除帐号
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAccountByAccount(String username)throws Exception;
	
	/**
	 * 修改帐号 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public boolean updateAccountByAccount(Account account)throws Exception;
	
	/**
	 * 修改帐号密码
	 * @return
	 * @throws Exception
	 */
	public boolean updateAccountPwdByAccount(Account account)throws Exception;
	
	/**
	 * 分页查询所有帐号
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public Account queryAccountByAccount(Account account)throws Exception;
	
	
	/**
	 * 单一帐号信息查询
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public Account findAccountByUsername(String username)throws Exception;
	
	/**
	 * 通过username校验该帐号名是否存在
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public int checkusernameCountByusername(String username)throws Exception;
	
	/**
	 * 校验密码
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public int  checkEncryptedPassword(Account account)throws Exception;

	
}
