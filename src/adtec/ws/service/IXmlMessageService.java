package adtec.ws.service;

import javax.jws.WebService;

@WebService
public interface IXmlMessageService {
	/**
	 * 
	 * @param xmlMsg
	 * @return true / false ，发送成功或者失败
	 */
	public String sendMessage(String xmlMsg);
	
}
