package adtec.adtec_message.service;

import java.util.List;
import java.util.Map;

import adtec.adtec_message.model.AJMessage;

public interface SwitchService {
	
	/**
	 * 通过xml来获取xml中所有的参数信息
	 * @param xmlText	xml字符串
	 * @return	解析后得到的list
	 * @author lj
	 */
	public List<AJMessage> getMsgByXml(String xmlText);
	
	/**
	 * 通过获取到的参数list来判断是给机构发送消息还是给个人发送消息
	 * 2014年2月19日 12:36:49
	 * @param list
	 * @return 返回true 代表给机构发送 返回false 代表给个人发送消息
	 * @author lj
	 */
	public boolean isOrgOrAcc(List<AJMessage> list);
	
	/**
	 * 通过机构参数来获取该机构下所有的帐号
	 * 2014年2月19日 13:31:21
	 * @param list
	 * @return 返回值为帐号组成的list集合
	 * @author lj
	 */
	public List<Map<String,Object>> getAccByOrg(List<AJMessage> list);
	
	/**
	 * 通过xml本身的参数信息以及获取到的帐号信息从新拼装成xml
	 * 2014年2月19日 16:55:04
	 * @param list
	 * @return xml
	 * @author lj
	 */
	public String getXmlByMsg(List<Map<String,Object>> accList,List<AJMessage> list);
	
	
	/**
	 * 通过xml本身的参数信息以及权限认证后得到的帐号信息从新拼装成xml
	 * 2014年2月19日 16:55:04
	 * @param list
	 * @return xml
	 * @author lj
	 */
	public String getXmlByAppMsg(List<String> accappList,List<AJMessage> list);
	
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

	
}
