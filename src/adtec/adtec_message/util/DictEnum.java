package adtec.adtec_message.util;

import java.util.Map;

import adtec.util.JSONUtil;

public class DictEnum {

	public static class XMLNode {
		public static String ROOT_ELEMENT_NAME="msgs";//root 消息集合名称
		public static String MSG_ELEMENT_NAME="msg";//消息名称
		public static String APP_ELEMENT_NAME="app";//应用名称
		public static String ORG_ELEMENT_NAME="organization";//机构名称
		public static String ACC_ELEMENT_NAME="account";//用户账号
		
		public static String CONTENT_ELEMENT_NAME="context";//消息内容
		
		public static String RECEIVER_ELEMENT_NAME="receiver"; //消息的接受者,只有当receiver为org的时候，给机构发消息，如果receiver为acc时，给帐号发，如果receiver为空或不存在，默认是给帐号发消息。
		
		public static String RECVTYPE_ELEMENT_NAME="recv_type"; //接收者类型。online 或者 all
		
		public static String IS_SON_ORGLIST="isSonOrgList";  //发送给机构消息的类型，是给当前机构发送还是给该机构下所有的机构发送
	}
	
	/**
	 * 操作类型
	 * @author maojd
	 * @description	权限管理的资源操作类型。eg:查看、修改。
	 * @date 
	 */
	public static class ActionType{
		
		/**
		 * 查看
		 */
		public static String VIEW = "1001";
		

		/**
		 * 添加
		 */
		public static String ADD = "1002";
		
		/**
		 * 修改
		 */
		public static String UPDATE = "1003";
		
		/**
		 * 删除
		 */
		public static String DEL = "1004";

		/** 数据字典哈希表 **/
		public static Map dataMap = JSONUtil
				.parseJSON2Map("{\"1001\":\"查看\",\"1002\":\"添加\",\"1003\":\"修改\",\"1004\":\"删除\"}");
		
		
	}
}
