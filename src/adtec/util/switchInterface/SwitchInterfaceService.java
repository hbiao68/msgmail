package adtec.util.switchInterface;

import java.util.List;
import java.util.Map;

import adtec.accorgrelation.model.Accountorgrelation;
import adtec.account.model.Account;
import adtec.adtec_message.model.AJMessage;

public interface SwitchInterfaceService {
	
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
	
	
	/**
	 * 将帐号添入到机构下
	 * @param accountorgrelation
	 * @return
	 * @throws Exception
	 */
	public boolean insertAccountorgrelation(Accountorgrelation accountorgrelation)throws Exception;
	
	/**
	 * 删除帐号和机构的关系
	 * @param relationid
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAccountorgrelation(int relationid)throws Exception;
	
	/**
	 * 修改帐号所在机构
	 * @param accountorgrelation
	 * @return
	 * @throws Exception
	 */
	public boolean updateAccountorgrelation(Accountorgrelation accountorgrelation)throws Exception;
	
	/**
	 * 分页查询数据
	 * @param accountorgrelation
	 * @return
	 * @throws Exception
	 */
	public Accountorgrelation queryAccOrgRelByAccOrgRel(Accountorgrelation accountorgrelation)throws Exception;
	
	/**
	 * 单一实例查询
	 * @param relationid
	 * @return
	 * @throws Exception
	 */
	public Accountorgrelation findAccOrgRelByRelationid(int relationid)throws Exception;
	
	/**
	 * 批量删除数据
	 * @throws Exception
	 */
	public boolean batchdelete(Accountorgrelation accountorgrelation)throws Exception;
	
	/**
	 * 通过xml来获取xml中所有的参数信息
	 * @param xmlText	xml字符串
	 * @return	解析后得到的list
	 * @author lj
	 */
	public List<AJMessage> getMsgByXml(String xmlText);
	
	/**
	 * 通过获取到的参数list来判断是给机构发送消息还是给个人发送消息
	 * @param list
	 * @return 返回true 代表给机构发送 返回false 代表给个人发送消息
	 */
	public boolean isOrgOrAcc(List<AJMessage> list);
	
	/**
	 * 通过机构参数来获取该机构下所有的帐号
	 * @param list
	 * @return 返回值为帐号组成的list集合
	 */
	public List<Map<String,Object>> getAccByOrg(List<AJMessage> list);
	
	/**
	 * 通过xml本身的参数信息以及获取到的帐号信息从新拼装成xml
	 * @param list
	 * @return xml
	 * @author lj
	 */
	public String getXmlByMsg(List<Map<String,Object>> accList,List<AJMessage> list);
	
	/**
	 * 通过xml参数中的app信息对所有帐号进行验证，获取有权限的帐号
	 * 2014年3月20日 14:12:01
	 * @param list
	 * @return list
	 * @author lj
	 */
	public List<String> getAccByAccApp(List<String> accList,List<AJMessage> list);
	
	/**
	 * 获取拼接好的xml中的所有帐号信息
	 * @return
	 */
	public List<String> getAllAccByXml(List<AJMessage> list);
	
	/**
	 * 通过xml本身的参数信息以及权限认证后得到的帐号信息从新拼装成xml
	 * 2014年2月19日 16:55:04
	 * @param list
	 * @return xml
	 * @author lj
	 */
	public String getXmlByAppMsg(List<String> accappList,List<AJMessage> list);
}
