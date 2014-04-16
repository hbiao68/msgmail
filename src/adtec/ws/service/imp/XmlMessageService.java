package adtec.ws.service.imp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import adtec.util.UtilXml;
import adtec.ws.constant.DictEnum;
import adtec.ws.model.AJMessage;
import adtec.ws.service.IXmlMessageService;

@WebService(endpointInterface = "adtec.ws.service.IXmlMessageService")
public class XmlMessageService implements IXmlMessageService {
	Logger log = Logger.getLogger(XmlMessageService.class);
	
	static String ROOT_ELEMENT_NAME = DictEnum.XMLNode.ROOT_ELEMENT_NAME;//root 消息集合名称
	static String MSG_ELEMENT_NAME = DictEnum.XMLNode.MSG_ELEMENT_NAME;//消息名称
	static String APP_ELEMENT_NAME = DictEnum.XMLNode.APP_ELEMENT_NAME;//应用名称
	static String ORG_ELEMENT_NAME = DictEnum.XMLNode.ORG_ELEMENT_NAME;//机构名称
	static String ACC_ELEMENT_NAME = DictEnum.XMLNode.ACC_ELEMENT_NAME;//用户账号
	static String CONTENT_ELEMENT_NAME = DictEnum.XMLNode.CONTENT_ELEMENT_NAME;//用户账号

	
	/*
<msgs><msg app='myapp' organization='myorg' account='myacc'><context aaa='ddd'>content_message</context></msg><msg app='' organization='' account=''><extparam1></extparam1><extparam2></extparam2><context></context></msg></msgs>
*/
	@Override
	public String sendMessage(String xmlMsg) {
		String result = "false";
		//1、 判断客户端传递的字符串是否是合法的
		if (UtilXml.isXmlStr(xmlMsg)) {
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(xmlMsg);// 将字符串转为XML
				String rootName = UtilXml.getRootName(doc);
				
				//2、 检查根节点是否跟要求一样，进一步检查数据
				if(ROOT_ELEMENT_NAME.equals(rootName)){
					Element rootElt = doc.getRootElement(); // 获取根节点
					//获取跟节点下面的 msg 节点
					Iterator msgit = UtilXml.getIteratorByName(rootElt, MSG_ELEMENT_NAME);
					
					//3、将XML消息转为AJMessage对象，存储在List容器中
					List ajmessages = new LinkedList<AJMessage>();
					while(msgit.hasNext()){
						Element msgEle = (Element)msgit.next();
						//节点名称
						//System.out.println(msgEle.getName());
						
						if(UtilXml.checkDataByNodeName(msgEle, MSG_ELEMENT_NAME)){
							String account = UtilXml.getAttributeValueByName(msgEle, APP_ELEMENT_NAME);
							String orgid = UtilXml.getAttributeValueByName(msgEle, ORG_ELEMENT_NAME);
							String app = UtilXml.getAttributeValueByName(msgEle, ACC_ELEMENT_NAME);
							AJMessage msg = new AJMessage();
							//account@domain/app
							String messageid = "";
							//如果用户账号和机构账号同时存在，则先提供用户账号
							//如果用户账号和机构账号同时存在，则先提供用户账号
							if(null != account && !"".equals(account)){
								messageid = account + "@domain" + "/" + app; 
							}else if(null != orgid && !"".equals(orgid)){
								messageid = orgid + "@domain" + "/" + app;
							}else{
								
							}
							String content = UtilXml.getNodeValueByName(msgEle, CONTENT_ELEMENT_NAME);
							System.out.println(content);
							msg.setMessageid(messageid);
							msg.setContent(content);
							ajmessages.add(msg);
						}else{
							continue;
						}
						
					}
				}else{
					log.error("根节点为"+rootName+",应该输入"+ROOT_ELEMENT_NAME);
				}
				result = "true";
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} else {
			return result;
		}
		return result;
	}

}
