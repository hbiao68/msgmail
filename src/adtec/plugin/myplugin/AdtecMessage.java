package adtec.plugin.myplugin;

import javax.servlet.ServletContext;

import adtec.plugin.MessageInterceptor;
import adtec.util.switchInterface.AccOrgSetXmlService;
import adtec.adtec_message.service.Adtec_messageService;
import java.util.List;
import java.util.Map;
import adtec.adtec_message.model.AJMessage;

public class AdtecMessage extends MessageInterceptor {

	//构造方法必须要有用户的请求对象

	public AdtecMessage(ServletContext servletContext) {
		super(servletContext);
	}

	@Override
	public String handle(String xmlText) {
		//处理xmlMsg
		if(xmlText == null){
			xmlText = "";
		}else{
			try {
				AccOrgSetXmlService accorgsetxmlservice = (AccOrgSetXmlService)this.getApplicationContext().getBean("accorgsetxmlservice");
					//1.通过xml来获取xml中所有的参数信息
					List<AJMessage> list = accorgsetxmlservice.getSwtService().getMsgByXml(xmlText);
					//2.通过获取到的参数信息判断是该消息是发送给机构还是发送给帐号,如果tag等于true,是给机构发送消息，如果tag等于false，是给个人发送消息
					boolean tag = accorgsetxmlservice.getSwtService().isOrgOrAcc(list);
					if(tag){
						//3.通过机构参数来获取该机构下所有的帐号
						List<Map<String,Object>> accList = accorgsetxmlservice.getSwtService().getAccByOrg(list);
						//4.将得到的帐号从新组成xml格式，发送给openfire
						xmlText = accorgsetxmlservice.getSwtService().getXmlByMsg(accList, list);
					}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		//判断是否还有下一个拦截器链，这个是系统动态给的，自己不用关心，所有插件的代码都可以这么写
		if(this.getNextInterceptor() != null){
			xmlText = this.getNextInterceptor().handle(xmlText);
		}
		
		return xmlText;
	}

}
