package adtec.account.dao;


import java.util.List;
import java.util.Map;

import adtec.account.model.Account;

/**
 * 
 * @FileName:      AccountDao
 *
 * @FileType:      Interface
 *
 * @Date:          2014年3月6日 2014年3月6日 18:19:01
 * 
 * @Author:        李季
 * 
 * @Description:   帐号信息接口
 */
public interface AccountDao {
	
	/**
	 * 添加帐号信息
	 * @param account
	 * @throws Exception
	 */
	public void insertAccount(Account account)throws Exception; 
	
	/**
	 * 删除帐号
	 * @param account
	 * @throws Exception
	 */
	public void deleteAccount(String username)throws Exception;
	
	/**
	 * 修改帐号 
	 * @param account
	 * @throws Exception
	 */
	public void updateAccount(Account account)throws Exception;
	
	/**
	 * 修改帐号密码
	 * @param account
	 * @throws Exception
	 */
	public void updateAccountPwd(Account account)throws Exception;
	
	/**
	 * 分页查询所有帐号
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public List<Account> queryAccountByAccount(Account account)throws Exception;
	
	/**
	 * 查询所有帐号
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public List<Account> queryAccountforCountByAccount(Account account)throws Exception;
	
	/**
	 * 单一帐号信息查询
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public Account findAccountByUsername(String username)throws Exception;
	
	/**
	 * 通过username校验该帐号名是否存在
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
