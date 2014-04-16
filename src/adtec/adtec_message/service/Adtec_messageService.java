package adtec.adtec_message.service;


import adtec.adtec_message.service.SwitchService;

public interface Adtec_messageService {
	/**
	 * 切换内部帐号与外部系统帐号接口
	 * @return
	 */
	public SwitchService getService();
}
