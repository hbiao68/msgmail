package adtec.adtec_message.model;

public class AJMessage {
	//用户账号
	private String account;
	//机构名称
	private String orgid;
	//应用名称
	private String app;
	//消息的接受者,只有当receiver为org的时候，给机构发消息，如果receiver为acc时，给帐号发，如果receiver为空或不存在，默认是给帐号发消息。
	private String receiver;
	//接收者类型。online 或者 all
	private String recv_type;
	//发送信息的内容
	private String content;
	
	//发送给机构消息的类型，是给当前机构发送还是给该机构下所有的机构发送
	private String isSonOrgList;




	public String getIsSonOrgList() {
		return isSonOrgList;
	}

	public void setIsSonOrgList(String isSonOrgList) {
		this.isSonOrgList = isSonOrgList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getRecv_type() {
		return recv_type;
	}

	public void setRecv_type(String recv_type) {
		this.recv_type = recv_type;
	}
	
	
	
}
