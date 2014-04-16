package adtec.adtec_message.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import adtec.adtec_message.model.AJMessage;
import adtec.adtec_message.service.SwitchService;
import adtec.adtec_message.util.DictEnum;
import adtec.adtec_message.util.UtilGetMsg;
import adtec.adtec_message.util.UtilXml;
import adtec.adtec_message.test.dao.ExternalDao;
import adtec.adtec_message.test.dao.impl.ExternalDaoImpl;


public class ExternalServiceImpl implements SwitchService{

	Logger log = Logger.getLogger(LocalServiceImpl.class);
	
	static String ROOT_ELEMENT_NAME = DictEnum.XMLNode.ROOT_ELEMENT_NAME;//root 消息集合名称
	static String MSG_ELEMENT_NAME = DictEnum.XMLNode.MSG_ELEMENT_NAME;//消息名称
	static String APP_ELEMENT_NAME = DictEnum.XMLNode.APP_ELEMENT_NAME;//应用名称
	static String ORG_ELEMENT_NAME = DictEnum.XMLNode.ORG_ELEMENT_NAME;//机构名称
	static String ACC_ELEMENT_NAME = DictEnum.XMLNode.ACC_ELEMENT_NAME;//用户账号
	static String CONTENT_ELEMENT_NAME = DictEnum.XMLNode.CONTENT_ELEMENT_NAME;//消息内容
	
	static String RECEIVER_ELEMENT_NAME = DictEnum.XMLNode.RECEIVER_ELEMENT_NAME;//消息的接受者,只有当receiver为org的时候，给机构发消息，如果receiver为acc时，给帐号发，如果receiver为空或不存在，默认是给帐号发消息。
	static String RECVTYPE_ELEMENT_NAME = DictEnum.XMLNode.RECVTYPE_ELEMENT_NAME;//接收者类型。online 或者 all
	
	
	@Override
	public List<Map<String, Object>> getAccByOrg(List<AJMessage> list) {
		//放查询到的所有机构的ID
		List<String> orgList = new ArrayList<String>();
		ExternalDao dao = new ExternalDaoImpl();
		for(int i=0;i<list.size();i++){
			AJMessage aJMessage = list.get(i);
			String receiver = aJMessage.getReceiver();
			if("org".equals(receiver)){
				//获取到xml参数的orgId
				String orgid = aJMessage.getOrgid();
				//通过xml参数中的orgId获取机构的层级
				Map<String,String> map = UtilGetMsg.getLevel(orgid);
				//通过层级结构获取到机构的主键
				String orgIdKey = dao.queryorgIdByLevel(map);
				orgList.add(orgIdKey);
			}
		}
		//通过orgIdKey查询到所有的帐号
		List<Map<String,Object>> accList = dao.queryAccountByorgIdList(orgList);
		return accList;
	}
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
							
							msg.setAccount(account);
							msg.setApp(app);
							msg.setOrgid(orgid);
							msg.setReceiver(receiver);
							msg.setRecv_type(recv_type);
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
	@Override
	public String getXmlByMsg(List<Map<String, Object>> accList,
			List<AJMessage> list) {

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
				msg.addAttribute("account", aJMessage.getAccount());
				msg.addElement("context").setText(aJMessage.getContent());
				if(null != aJMessage.getReceiver()){
					msg.addElement("receiver").setText(aJMessage.getReceiver());
				}
				if(null == aJMessage.getRecv_type() || "".equals(aJMessage.getRecv_type()) ){
				}else{
					msg.addElement("recv_type").setText(aJMessage.getRecv_type());
				}
			}
		}
		return root.asXML();
		}
	
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
	
	@Override
	public List<String> getAllAccByXml(List<AJMessage> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ExternalServiceImpl() {
		// TODO Auto-generated constructor stub
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
