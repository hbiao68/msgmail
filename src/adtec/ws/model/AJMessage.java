package adtec.ws.model;

public class AJMessage {
	//账号 usercount@jdchat/spark
	private String messageid;
	//发送信息的内容
	private String content;
	//添加的参数
	private String param1;
	
	
	public String getMessageid() {
		return messageid;
	}
	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	
}
