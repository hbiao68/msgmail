package adtec.util.switchInterface.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import adtec.accorgrelation.model.Accountorgrelation;
import adtec.accorgrelation.service.SwitchAccOrgRelService;
import adtec.account.model.Account;
import adtec.account.service.SwitchAccService;
import adtec.adtec_message.model.AJMessage;
import adtec.adtec_message.service.SwitchService;
import adtec.util.switchInterface.SwitchInterfaceService;

public class LocalInterfaceServiceImpl implements SwitchInterfaceService {
	
	Logger log = Logger.getLogger(LocalInterfaceServiceImpl.class);
	
	private SwitchAccOrgRelService localaccorgService;
	
	private SwitchAccService localaccService;
	
	private SwitchService localsetmsgService;

	public SwitchAccOrgRelService getLocalaccorgService() {
		return localaccorgService;
	}

	public void setLocalaccorgService(SwitchAccOrgRelService localaccorgService) {
		this.localaccorgService = localaccorgService;
	}

	public SwitchAccService getLocalaccService() {
		return localaccService;
	}

	public void setLocalaccService(SwitchAccService localaccService) {
		this.localaccService = localaccService;
	}

	public SwitchService getLocalsetmsgService() {
		return localsetmsgService;
	}

	public void setLocalsetmsgService(SwitchService localsetmsgService) {
		this.localsetmsgService = localsetmsgService;
	}
	

	
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
		try {
			this.localaccService.addAccount(account);
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
			this.localaccService.deleteAccountByAccount(username);
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
			this.localaccService.updateAccountByAccount(account);
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
			this.localaccService.updateAccountPwdByAccount(account);
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
			return this.localaccService.queryAccountByAccount(account);
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
			return this.localaccService.findAccountByUsername(username);
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
			return this.localaccService.checkusernameCountByusername(username);
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
			return this.localaccService.checkEncryptedPassword(account);
		} catch (Exception e) {
			log.error("查询失败！ checkEncryptedPassword error");
			throw e;
		}
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
		try {
			this.localaccorgService.insertAccountorgrelation(accountorgrelation);
			log.debug("帐号机构关系添加成功！");
			return true;
		} catch (Exception e) {
			log.error("帐号机构关系添加失败！");
			throw e;
		}
	}
	
	/**
	 * 删除帐号和机构的关系
	 */
	@Override
	public boolean deleteAccountorgrelation(int relationid) throws Exception {
		try {
			this.localaccorgService.deleteAccountorgrelation(relationid);
			log.debug("帐号机构关系删除成功！");
			return true;
		} catch (Exception e) {
			log.error("帐号机构关系删除失败！");
			throw e;
		}
	}
	
	/**
	 * 修改帐号所在机构
	 */
	@Override
	public boolean updateAccountorgrelation(
			Accountorgrelation accountorgrelation) throws Exception {
		try {
			this.localaccorgService.updateAccountorgrelation(accountorgrelation);
			log.debug("帐号机构关系修改成功！");
			return true;
		} catch (Exception e) {
			log.error("帐号机构关系修改失败！");
			throw e;
		}
	}
	
	@Override
	public Accountorgrelation queryAccOrgRelByAccOrgRel(
			Accountorgrelation accountorgrelation) throws Exception {
		try {
			return this.localaccorgService.queryAccOrgRelByAccOrgRel(accountorgrelation);
		} catch (Exception e) {
			log.error("分页查询数据失败！ queryAccOrgRelByAccOrgRel error");
			throw e;
		}
	}
	
	/**
	 * 单一实例查询
	 */
	@Override
	public Accountorgrelation findAccOrgRelByRelationid(int relationid)
			throws Exception {
		try {
			return this.localaccorgService.findAccOrgRelByRelationid(relationid);
		} catch (Exception e) {
			log.error(" 单一实例查询失败   findAccOrgRelByRelationid error");
			throw e;
		}
	}
	
	/**
	 * 批量删除数据
	 */
	
	@Override
	public boolean batchdelete(Accountorgrelation accountorgrelation)
			throws Exception {
		try {
			this.localaccorgService.batchdelete(accountorgrelation);
			return true;
		} catch (Exception e) {

			log.error("批量删除失败！");
			throw e;
		}
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
		return this.localsetmsgService.getMsgByXml(xmlText);
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
		return this.localsetmsgService.isOrgOrAcc(list);
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
		return this.localsetmsgService.getAccByOrg(list);
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
		return this.localsetmsgService.getXmlByMsg(accList, list);
	}
	
	/**
	 * 通过xml参数中的app信息对所有帐号进行验证，获取有权限的帐号
	 * 2014年3月20日 14:12:01
	 * @param list
	 * @return list
	 * @author lj
	 */
	@Override
	public List<String> getAccByAccApp(List<String> accList, List<AJMessage> list) {
		return this.localsetmsgService.getAccByAccApp(accList, list);
	}
	
	/**
	 * 获取拼接好的xml中的所有帐号信息
	 * @return
	 */	
	@Override
	public List<String> getAllAccByXml(List<AJMessage> list) {
		return this.localsetmsgService.getAllAccByXml(list);
	}
	/**
	 * 通过xml本身的参数信息以及权限认证后得到的帐号信息从新拼装成xml
	 * 2014年2月19日 16:55:04
	 * @param list
	 * @return xml
	 * @author lj
	 */
	@Override
	public String getXmlByAppMsg(List<String> accappList, List<AJMessage> list) {
		return this.localsetmsgService.getXmlByAppMsg(accappList, list);
	}
	
}
