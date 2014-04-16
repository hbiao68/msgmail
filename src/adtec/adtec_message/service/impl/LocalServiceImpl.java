package adtec.adtec_message.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import adtec.accorgrelation.dao.AccountorgrelationDao;
import adtec.adtec_message.model.AJMessage;


import adtec.adtec_message.util.DictEnum;
import adtec.adtec_message.util.UtilXml;

import adtec.adtec_message.util.UtilGetMsg;

import adtec.organizationManager.dao.OrganizationDao;
import adtec.organizationManager.entity.Organization;
import adtec.adtec_message.service.SwitchService;
import adtec.appManager.dao.Ta_App_relationDao;


public class LocalServiceImpl implements SwitchService{

	
	private OrganizationDao orgDao;
	
	private AccountorgrelationDao accountorgrelationDao;
	
	private Ta_App_relationDao ta_App_relationDao;
	

	public Ta_App_relationDao getTa_App_relationDao() {
		return ta_App_relationDao;
	}

	public void setTa_App_relationDao(Ta_App_relationDao ta_App_relationDao) {
		this.ta_App_relationDao = ta_App_relationDao;
	}

	public AccountorgrelationDao getAccountorgrelationDao() {
		return accountorgrelationDao;
	}

	public void setAccountorgrelationDao(AccountorgrelationDao accountorgrelationDao) {
		this.accountorgrelationDao = accountorgrelationDao;
	}

	public OrganizationDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(OrganizationDao orgDao) {
		this.orgDao = orgDao;
	}
	

	Logger log = Logger.getLogger(LocalServiceImpl.class);
	
	static String ROOT_ELEMENT_NAME = DictEnum.XMLNode.ROOT_ELEMENT_NAME;//root 消息集合名称
	static String MSG_ELEMENT_NAME = DictEnum.XMLNode.MSG_ELEMENT_NAME;//消息名称
	static String APP_ELEMENT_NAME = DictEnum.XMLNode.APP_ELEMENT_NAME;//应用名称
	static String ORG_ELEMENT_NAME = DictEnum.XMLNode.ORG_ELEMENT_NAME;//机构名称
	static String ACC_ELEMENT_NAME = DictEnum.XMLNode.ACC_ELEMENT_NAME;//用户账号
	static String CONTENT_ELEMENT_NAME = DictEnum.XMLNode.CONTENT_ELEMENT_NAME;//消息内容
	
	static String RECEIVER_ELEMENT_NAME = DictEnum.XMLNode.RECEIVER_ELEMENT_NAME;//消息的接受者,只有当receiver为org的时候，给机构发消息，如果receiver为acc时，给帐号发，如果receiver为空或不存在，默认是给帐号发消息。
	static String RECVTYPE_ELEMENT_NAME = DictEnum.XMLNode.RECVTYPE_ELEMENT_NAME;//接收者类型。online 或者 all
	static String IS_SON_ORGLIST = DictEnum.XMLNode.IS_SON_ORGLIST; //发送给机构消息的类型，是给当前机构发送还是给该机构下所有的机构发送
	
	
	/**
	 * 通过xml来获取xml中所有的参数信息
	 * @param xmlText	xml字符串
	 * @return	解析后得到的list
	 * @author lj
	 */
	@Override
	public List<AJMessage> getMsgByXml(String xmlText) {
		List<AJMessage> ajmessages = new LinkedList<AJMessage>();
		
		//1、 判断客户端传递的字符串是否是合法的
		if (UtilXml.isXmlStr(xmlText)) {
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(xmlText);// 将字符串转为XML
				String rootName = UtilXml.getRootName(doc);
				
				//2、 检查根节点是否跟要求一样，进一步检查数据
				if(ROOT_ELEMENT_NAME.equals(rootName)){
					Element rootElt = doc.getRootElement(); // 获取根节点
					//获取跟节点下面的 msg 节点
					Iterator msgit = UtilXml.getIteratorByName(rootElt, MSG_ELEMENT_NAME);
					
					//3、将XML消息转为AJMessage对象，存储在List容器中
					
					while(msgit.hasNext()){
						Element msgEle = (Element)msgit.next();
						//节点名称
						//System.out.println(msgEle.getName());
						
						if(UtilXml.checkDataByNodeName(msgEle, MSG_ELEMENT_NAME)){
							AJMessage msg = new AJMessage();
							
							String account = UtilXml.getAttributeValueByName(msgEle, ACC_ELEMENT_NAME);
							String orgid = UtilXml.getAttributeValueByName(msgEle, ORG_ELEMENT_NAME);
							String app = UtilXml.getAttributeValueByName(msgEle, APP_ELEMENT_NAME);
							String receiver = UtilXml.getNodeValueByName(msgEle, RECEIVER_ELEMENT_NAME);
							String recv_type = UtilXml.getNodeValueByName(msgEle, RECVTYPE_ELEMENT_NAME);
							String content = UtilXml.getNodeValueByName(msgEle, CONTENT_ELEMENT_NAME);	
							String isSonOrgList = UtilXml.getNodeValueByName(msgEle, IS_SON_ORGLIST);
							
							msg.setAccount(account);
							msg.setApp(app);
							msg.setOrgid(orgid);
							msg.setReceiver(receiver);
							msg.setRecv_type(recv_type);
							msg.setIsSonOrgList(isSonOrgList);
							
							//如果一次传入消息数据过大，则消息不处理
							if(content.length()>2000){
								return null;
							}
							msg.setContent(content);
							ajmessages.add(msg);
						}else{
							continue;
						}
					}
				}else{
					log.error("根节点为"+rootName+",应该输入"+ROOT_ELEMENT_NAME);
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return ajmessages;
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
		boolean tag = false;
		if(list == null){
			return tag;
		}else{
			for(int i=0;i<list.size();i++){
				AJMessage aJMessage = list.get(i);
				String receiver = aJMessage.getReceiver();
				if("org".equals(receiver) && null != aJMessage.getOrgid()){
					tag = true; 
					break;
				}
			}
			return tag;
		}
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
		//放查询到的所有机构的ID
		List<String> orgList = new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			AJMessage aJMessage = list.get(i);
			String receiver = aJMessage.getReceiver();
			if("org".equals(receiver)){
				//获取到xml参数的orgId
				String orgid = aJMessage.getOrgid();
				//通过xml参数中的orgId获取机构的层级
				Map<String,String> map = UtilGetMsg.getLevel(orgid);
				//通过层级结构获取到机构的主键
				String orgIdKey = orgDao.queryorgIdByLevel(map);
				//获取到xml参数中的控制发送给机构还是该机构下所有自己够的信息
				String isSonOrgList = aJMessage.getIsSonOrgList();
				//如果isSUnOrgList为true，那么给该机构下所有子机构发送信息
				if("true".equals(isSonOrgList)){
					//获取该orgidkey的整个org信息
					List<Organization> orgIFOList = orgDao.queryOrgById(Integer.parseInt(orgIdKey));
					Organization org = orgIFOList.get(0);
					//获取该org极其子机构所有信息
					List<Organization> orgSunList = orgDao.querySubOrgListByOrg(org); 
					for(int j=0;j<orgSunList.size();j++){
						Organization orgSun = orgSunList.get(j);
						orgIdKey = String.valueOf(orgSun.getOrgId());
						orgList.add(orgIdKey);
					}
				}else{
					orgList.add(orgIdKey);
				}
			}
		}
		//通过orgIdKey查询到所有的帐号
		List<Map<String,Object>> accList = accountorgrelationDao.queryAccountByorgIdList(orgList);
		return accList;
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
		Document requestDoc = DocumentHelper.createDocument();
		Element root = requestDoc.addElement("msgs");
		Element msg = null;
		for(int i=0;i<list.size();i++){
			AJMessage aJMessage = list.get(i);
			if("org".equals(aJMessage.getReceiver()) && null != aJMessage.getOrgid()){
				for(int j=0;j<accList.size();j++){
					Map<String,Object> ins = accList.get(j);
					msg = root.addElement("msg");
					msg.addAttribute("app", aJMessage.getApp());
					msg.addAttribute("organization", aJMessage.getOrgid());
					msg.addAttribute("account", ins.get("username").toString());
					msg.addElement("context").setText(aJMessage.getContent());
					msg.addElement("receiver").setText("acc");
					if(null == aJMessage.getRecv_type() || "".equals(aJMessage.getRecv_type()) ){
					}else{
						msg.addElement("recv_type").setText(aJMessage.getRecv_type());
					}
				}
			}else{
					msg = root.addElement("msg");
					msg.addAttribute("app", aJMessage.getApp());
					if(null != aJMessage.getOrgid()){
						msg.addAttribute("organization", aJMessage.getOrgid());
					}
					msg.addAttribute("account",aJMessage.getAccount());
					msg.addElement("context").setText(aJMessage.getContent());
					if(null != aJMessage.getReceiver()){
						msg.addElement("receiver").setText("acc");
					}
					if(null == aJMessage.getRecv_type() || "".equals(aJMessage.getRecv_type()) ){
					}else{
						msg.addElement("recv_type").setText(aJMessage.getRecv_type());
					}


			}
		}
		return root.asXML();
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
		//该list中放所有有该app的帐号
		List<String> accappList = new ArrayList<String>();
		//将查询用的数据放入map中
		Map<String,Object> map = new HashMap<String,Object>();
		//将帐号的信息出来放入list中
		List<String> accValueList = new ArrayList<String>();
		for(int j=0;j<accList.size();j++){
			String accountName = accList.get(j);
			accValueList.add(accountName);
		}
		for(int i = 0; i < list.size(); i++){
			AJMessage aJMessage = list.get(i);
			String app = aJMessage.getApp();
			map.put("appName", app);
			map.put("list", accValueList);
			accappList = ta_App_relationDao.getAccByAccApp(map); 
		}
		return accappList;
	}
	
	/**
	 * 获取拼接好的xml中的所有帐号信息
	 * @return
	 */	
	@Override
	public List<String> getAllAccByXml(List<AJMessage> list) {
		List<String> allAccList = new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			AJMessage aJMessage = list.get(i);
			String acc = aJMessage.getAccount();
			allAccList.add(acc);
		}
		return allAccList;
	}
	
	
	/**
	 * 通过权限后重新发送xml
	 * @param accList
	 * @param list
	 * @return
	 */
	@Override
	public String getXmlByAppMsg(List<String> accappList, List<AJMessage> list) {
		Document requestDoc = DocumentHelper.createDocument();
		Element root = requestDoc.addElement("msgs");
		Element msg = null;
		for(int i=0;i<list.size();i++){
			AJMessage aJMessage = list.get(i);
			for(int j=0;j<accappList.size();j++){
				if(aJMessage.getAccount().equals(accappList.get(j))){
					msg = root.addElement("msg");
					msg.addAttribute("app", aJMessage.getApp());
					if(null != aJMessage.getOrgid()){
						msg.addAttribute("organization", aJMessage.getOrgid());
					}
					msg.addAttribute("account",aJMessage.getAccount());
					msg.addElement("context").setText(aJMessage.getContent());
					if(null != aJMessage.getReceiver()){
						msg.addElement("receiver").setText("acc");
					}
					if(null == aJMessage.getRecv_type() || "".equals(aJMessage.getRecv_type()) ){
					}else{
						msg.addElement("recv_type").setText(aJMessage.getRecv_type());
					}
				}
			}
		}
		return root.asXML();
	}
	
}
