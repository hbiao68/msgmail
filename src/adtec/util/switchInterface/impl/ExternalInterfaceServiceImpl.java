package adtec.util.switchInterface.impl;

import java.util.List;
import java.util.Map;

import adtec.accorgrelation.model.Accountorgrelation;
import adtec.account.model.Account;
import adtec.adtec_message.model.AJMessage;
import adtec.util.switchInterface.SwitchInterfaceService;

public class ExternalInterfaceServiceImpl implements SwitchInterfaceService {
	

	/****************************************************************************
	 *
	 *                               下面是发送消息所用帐号表的功能点	 
	 * 
	 ****************************************************************************/
	
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
	
	
	/****************************************************************************
	 *
	 *                               下面是机构帐号表的功能点	 
	 * 
	 ****************************************************************************/	
	
	
	/**
	 * 将帐号添入到机构下
	 */
	@Override
	public boolean insertAccountorgrelation(
			Accountorgrelation accountorgrelation) throws Exception {
		return true;
	}
	
	/**
	 * 删除帐号和机构的关系
	 */
	@Override
	public boolean deleteAccountorgrelation(int relationid) throws Exception {
		return true;
	}
	
	/**
	 * 修改帐号所在机构
	 */
	@Override
	public boolean updateAccountorgrelation(
			Accountorgrelation accountorgrelation) throws Exception {
		return true;
	}
	
	@Override
	public Accountorgrelation queryAccOrgRelByAccOrgRel(
			Accountorgrelation accountorgrelation) throws Exception {
		return null;
	}
	
	/**
	 * 单一实例查询
	 */
	@Override
	public Accountorgrelation findAccOrgRelByRelationid(int relationid)
			throws Exception {
		return null;
	}
	
	/**
	 * 批量删除数据
	 */
	
	@Override
	public boolean batchdelete(Accountorgrelation accountorgrelation)
			throws Exception {
		return true;
	}
	
	/****************************************************************************
	 *
	 *                               下面是发送xml消息功能点
	 * 
	 ****************************************************************************/		
	
	
	
	/**
	 * 通过xml来获取xml中所有的参数信息
	 * @param xmlText	xml字符串
	 * @return	解析后得到的list
	 * @author lj
	 */
	@Override
	public List<AJMessage> getMsgByXml(String xmlText) {
		return null;
	}
	
	/**
	 * 通过获取到的参数list来判断是给机构发送消息还是给个人发送消息
	 * 2014年2月19日 12:36:49
	 * @param list
	 * @return 返回true 代表给机构发送 返回false 代表给个人发送消息
	 * @author lj
	 */
	@Override
	public boolean isOrgOrAcc(List<AJMessage> list) {
		return true;
	}
	
	/**
	 * 通过机构参数来获取该机构下所有的帐号
	 * 2014年2月19日 13:31:35
	 * @param list
	 * @return 返回值为帐号组成的list集合
	 * @author lj
	 */
	@Override
	public List<Map<String,Object>> getAccByOrg(List<AJMessage> list) {
		return null;
	}
	
	
	
	/**
	 * 通过xml本身的参数信息以及获取到的帐号信息从新拼装成xml
	 * 2014年2月19日 16:55:20
	 * @param list
	 * @return xml
	 * @author lj
	 */
	
	@Override
	public String getXmlByMsg(List<Map<String,Object>> accList, List<AJMessage> list) {
		return null;
	}
	
	/**
	 * 通过xml参数中的app信息对所有帐号进行验证，获取有权限的帐号
	 * 2014年3月20日 14:12:01
	 * @param list
	 * @return list
	 * @author lj
	 */
	public List<String> getAccByAccApp(Map<String,Object> map){
		return null;
	}
	
	/**
	 * 获取拼接好的xml中的所有帐号信息
	 * @return
	 */	
	@Override
	public List<String> getAllAccByXml(List<AJMessage> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getXmlByAppMsg(List<String> accappList, List<AJMessage> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public List<String> getAccByAccApp(List<String> accList,
			List<AJMessage> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
