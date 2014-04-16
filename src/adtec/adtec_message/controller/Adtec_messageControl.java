package adtec.adtec_message.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import adtec.adtec_message.service.Adtec_messageService;
import adtec.init.AjmessageProperty;
import adtec.plugin.MessageInteceptorManager;
import adtec.privilege.model.Resource;
import adtec.privilege.service.ResourceService;
import adtec.util.HttpUtil;

/**
 * 发送消息control类
 * 
 * @author maojd
 * 
 */
@Controller
@RequestMapping(value = "/adtec_message")
public class Adtec_messageControl {

	private Adtec_messageService adtec_messageService;
	private ResourceService resourceService; 
	
	
	public Adtec_messageService getAdtec_messageService() {
		return adtec_messageService;
	}

	public void setAdtec_messageService(Adtec_messageService adtec_messageService) {
		this.adtec_messageService = adtec_messageService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	Logger log = Logger.getLogger(Adtec_messageControl.class);
	
	String ip = AjmessageProperty.getInstance().get("ajmessage.url");
	String port = AjmessageProperty.getInstance().get("ajmessage.port");
	String XML_MESSAGE = "XmlMessage";
	
	public String getIp(){
		if(null == this.ip){
			ip = AjmessageProperty.getInstance().get("ajmessage.url");
		}
		return this.ip;
	}
	
	public String getPort() {
		if(null == port){
			port =AjmessageProperty.getInstance().get("ajmessage.port");
		}
		return port;
	}
	
	
//	public Adtec_messageControl(){
//		ip = AjmessageProperty.getInstance().get("ajmessage.url");
//		port = AjmessageProperty.getInstance().get("ajmessage.port");
//		XML_MESSAGE = "XmlMessage";
//	}

	/**
	 * 发送消息跳转到测试页面
	 * 
	 * @param request
	 * @param response
	 * @return 发送消息的测试页面 adtec_message/imjwchat-testim.jsp
	 */
	@RequestMapping(value = "/imjwchat")
	public String imjwchat(HttpServletRequest request, HttpServletResponse response,Resource res) {

		String resid = "";
		if(res!=null && res.getResid() != null && res.getResid() != ""){
			resid = res.getResid().trim();
			request.getSession().setAttribute("resid", resid);
		}//如果传入了resid 资源id就 王session中set一下，覆盖一下以前的。否则不做操作（资源id还是以前的）。
		
		
		return "adtec_message/imjwchat-testim";
	}

	/**
	 * 有发送者 接收者发送消息
	 * 
	 * @param request
	 * @param response
	 * @return 发送消息的测试页面 adtec_message/imjwchat-testim.jsp
	 */
	@RequestMapping(value = "/sendMessageFromToCont")
	public String sendMessageFromToCont(HttpServletRequest request, HttpServletResponse response) {
		String sender = request.getParameter("sender");
		String receiver = request.getParameter("receiver");
		String content = request.getParameter("content");
		String msg = "";
		// msg = mesageService.sendStringBySenderReciver(sender,receiver,
		// content);

		log.debug("1.带from to content      sender:" + sender + ".  receiver:" + receiver + ".   content:" + content + " msg is:" + msg);

		return "adtec_message/imjwchat-testim";
	}

	/**
	 * 只有接收者发送消息
	 * 
	 * @param request
	 * @param response
	 * @return 发送消息的测试页面 adtec_message/imjwchat-testim.jsp
	 */
	@RequestMapping(value = "/sendMessageToCont")
	public String sendMessageToCont(HttpServletRequest request, HttpServletResponse response) {
		String receiver = request.getParameter("receiver");
		String content = request.getParameter("content");
		String msg = "";
		// msg = mesageService.sendStringById(receiver, content);

		log.debug("2.测试发送消息            receiver:" + receiver + "   content:" + content + "    msg is:" + msg);

		return "adtec_message/imjwchat-testim";
	}

	/**
	 * 发送广播
	 * 
	 * @param request
	 * @param response
	 * @return 发送消息的测试页面 adtec_message/imjwchat-testim.jsp
	 */
	@RequestMapping(value = "/brdMessage")
	public String brdMessage(HttpServletRequest request, HttpServletResponse response) {
		String _brdrecv = request.getParameter("_brdrecv");// online在线用户 all
															// 所有用户
		String content = request.getParameter("content");
		String msg = "";
		if ("online".equals(_brdrecv)) {
			// msg = mesageService.broadcastAllOnlineByString(content);
		} else if ("all".equals(_brdrecv)) {
			// msg = mesageService.broadcastAllByString(content);
		}

		log.debug("3.测试广播消息           BroadCostReceiver:" + _brdrecv + ".   content:" + content + "msg is:" + msg);

		return "adtec_message/imjwchat-testim";
	}

	/**
	 * xml发送消息
	 * 
	 * @param request
	 * @param response
	 * @return 发送消息的测试页面 adtec_message/imjwchat-testim.jsp
	 */
	@RequestMapping(value = "/xmlMessage")
	public String xmlMessage(HttpServletRequest request, HttpServletResponse response) {
		String xmlText = request.getParameter("xmlTextArea");
		
		//String result = "";
//		if(null != xmlText && !"".equals(xmlText)){
		
//			System.out.println("MessageInteceptorManager.getInstance().interceptorsAction(param) : " + result);
	//	}

		if (xmlText != null && !"".equals(xmlText)) {
			xmlText = MessageInteceptorManager.getInstance().interceptorsAction(xmlText);
//			//1.通过xml来获取xml中所有的参数信息
//			List<AJMessage> list = adtec_messageService.getMsgByXml(xmlText);
//			//2.通过获取到的参数信息判断是该消息是发送给机构还是发送给帐号,如果tag等于true,是给机构发送消息，如果tag等于false，是给个人发送消息
//			boolean tag = adtec_messageService.isOrgOrAcc(list);
//			if(tag){
//				//3.通过机构参数来获取该机构下所有的帐号
//				List<Map<String,Object>> accList = adtec_messageService.getAccByOrg(list);
//				//4.将所有的帐号从新组成xml格式，发送给openfire
//				xmlText = adtec_messageService.getXmlByMsg(accList, list);
//			}
			Map<String,String> parames = new HashMap<String, String>();
			parames.put("xmlTextArea",xmlText);
			parames.put("action", XML_MESSAGE);
			//System.out.println(this + "----ip是："+ip);
			HttpUtil.http("http://" + getIp() + ":" + getPort() + "/plugins/adtec_message/imjwchatservlet", parames);
			log.debug("接收的xml字符串是："+xmlText);
		} else {
			log.debug("没有接收正确的xml消息");
		}
		
		return "adtec_message/imjwchat-testim";
	}

	/**
	 * 获取用户在线状态
	 * 
	 * @param request
	 * @param response
	 * @return 发送消息的测试页面 adtec_message/imjwchat-testim.jsp
	 */
	@RequestMapping(value = "/getUserStatus")
	public String getUserStatus(HttpServletRequest request, HttpServletResponse response) {

		String idUser = request.getParameter("_idUser");
		String status = ""; // online 在线 offline 离线
		// status = sysService.accPresence(idUser);
		log.debug("1.获取在线状态      idUser:" + idUser + "----状态是：" + status);

		return "adtec_message/imjwchat-testim";
	}

	/**
	 * 获取在线总数
	 * 
	 * @param request
	 * @param response
	 * @return 发送消息的测试页面 adtec_message/imjwchat-testim.jsp
	 */
	@RequestMapping(value = "/getCountOnlineUsers")
	public String getCountOnlineUsers(HttpServletRequest request, HttpServletResponse response) {
		int onLineNum = 0;
		// onLineNum = sysService.getAllAccountOnlineNum();
		log.debug("2.获取总数       getCountOnlineUsers:" + onLineNum);

		return "adtec_message/imjwchat-testim";
	}

}
