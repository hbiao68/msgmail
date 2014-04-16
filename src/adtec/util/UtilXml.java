package adtec.util;

import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import adtec.ws.constant.DictEnum;

public class UtilXml {
	
	static String ROOT_ELEMENT_NAME = DictEnum.XMLNode.ROOT_ELEMENT_NAME;//root 消息集合名称
	static String MSG_ELEMENT_NAME = DictEnum.XMLNode.MSG_ELEMENT_NAME;//消息名称
	static String APP_ELEMENT_NAME = DictEnum.XMLNode.APP_ELEMENT_NAME;//应用名称
	static String ORG_ELEMENT_NAME = DictEnum.XMLNode.ORG_ELEMENT_NAME;//机构名称
	static String ACC_ELEMENT_NAME = DictEnum.XMLNode.ACC_ELEMENT_NAME;//用户账号

	/**
	 * 判断用户输入的字符串是否合法,空字符串也是不合法的
	 * @param xmlMsg
	 * @return
	 */
	public static boolean isXmlStr(String xmlMsg){
		boolean result = true;
		Document doc = null;
		 try {
			doc = DocumentHelper.parseText(xmlMsg);
		} catch (DocumentException e) {
			e.printStackTrace();
			//log.info("传递的参数不是xml格式的字符串");
			System.out.println("传递的参数不是xml格式的字符串");
			result = false;
			
		} // 将字符串转为XML
		return result;
	}
	
	@Test
	public void isXmlStrTest(){
		//isXmlStr("");
		//isXmlStr("abc");
	}
	
	
	/**
	 * 获取xml的根节点
	 * @param doc
	 * @return
	 */
	public static String getRootName(Document doc){
		if(null != doc){
			Element rootElt = doc.getRootElement(); // 获取根节点
			return rootElt.getName();
		}
		return "";
	}
	
	/**
	 * 获取某个节点下面名字为name的Iterator对象
	 * @param element
	 * @param name
	 * @return
	 */
	public static Iterator getIteratorByName(Element element,String name){
		if(null != element && !"".equals(name)){
			return element.elementIterator(name);
		}else{
			System.out.println("参数不正确");
			return null;
		}
		
	}
	
	/**
	 * 获取属性值,如果不存在这个属性，则返回为null
	 * @param element
	 * @param name
	 * @return
	 */
	public static String getAttributeValueByName(Element element,String name){
		if(null != element && !"".equals(name)){
			return element.attributeValue(name);
		}else{
			System.out.println("参数不正确");
			return "";
		}
	}
	
	/**
	 * 获取属性节点对象
	 * @param element
	 * @param name
	 * @return
	 */
	public static Attribute getAttributeByName(Element element,String name){
		if(null != element && !"".equals(name)){
			return element.attribute(name);
		}else{
			System.out.println("参数不正确");
			return null;
		}
	}
	
	/**
	 * 获取节点的内容
	 * @param element
	 * @param name
	 * @return
	 */
	public static String getNodeValueByName(Element element,String name){
		if(null != element && !"".equals(name)){
			return element.elementText(name);
		}else{
			System.out.println("参数不正确");
			return "";
		}
	}
	
	/**
	 * 检验某个节点下面的数据
	 * @param element
	 * @return
	 */
	public static boolean checkDataByNodeName(Element element,String node){
		boolean result = true;
		/**
		 * APP_ELEMENT_NAME 一定要存在
		 * ORG_ELEMENT_NAME 和  ACC_ELEMENT_NAME 至少存在一个，如果两个都存在，那么账号默认选择用户账号信息。
		 */
		if(MSG_ELEMENT_NAME.equals(node)){
			String account = UtilXml.getAttributeValueByName(element, APP_ELEMENT_NAME);
			String orgid = UtilXml.getAttributeValueByName(element, ORG_ELEMENT_NAME);
			String app = UtilXml.getAttributeValueByName(element, ACC_ELEMENT_NAME);
			
			//app节点不能为空
			if(null == app || "".equals(app)){
				result = false;
			}
			//account和orgid两个不能同时为空
			if((null == account || "".equals(account)) || (null == orgid || "".equals(orgid))){
				result = false;
			}
			
		}
		return result;
	}
	
}
