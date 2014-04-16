package adtec.plugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MessageInteceptorManager {

	// 使用Map对象是为了防止添加定时器的时候添加重复了相同的拦截器
	private Map<String, MessageInterceptor> interceptors = new HashMap<String, MessageInterceptor>();
	
	//因为在添加、删除拦截器的时候是有条件的，因此通过addInterceptor()方法来添加插件，这样方便统一管理，因此就不会提供interceptorList的get和set方法
	//用来存储动态添加的拦截器对象，形成一条拦截器链
	private LinkedList<MessageInterceptor> interceptorList = new LinkedList<MessageInterceptor>();
	
	//用来记录不需要使用的插件，因为插件是存放在具体的某个目录下面的，而扫描插件的时候会将有效的插件添加到具体的应用中，(而不知道哪些插件是有用还是无用的)
	//但是如果我现在上传了插件，但是以后又不需要了，那么如何不使用已经上传了的插件呢
	//因为在添加、删除拦截器的时候是有条件的，因此通过addInterceptor()方法来添加插件，这样方便统一管理，因此就不会提供interceptorList的get和set方法
	private Map<String, MessageInterceptor> invalidInterceptors = new HashMap<String, MessageInterceptor>();
	

	private static MessageInteceptorManager messageInteceptorManager;

	ClassLoader loader = Thread.currentThread().getContextClassLoader();

	// 使用单例模式
	private MessageInteceptorManager() {
	}

	public static MessageInteceptorManager getInstance() {
		if (messageInteceptorManager == null) {
			messageInteceptorManager = new MessageInteceptorManager();
		}
		return messageInteceptorManager;
	}

	/**
	 * 根据MessageInterceptor的全称路径添加拦截器
	 * 
	 * @param clazz
	 */
	public void addInterceptor(String clazz) {
		MessageInterceptor messageInterceptor;
		try {
			System.out.println(interceptors);
			// 判断是否已经存在该拦截器
			if (!interceptors.containsKey(clazz)) {
				messageInterceptor = (MessageInterceptor) loader.loadClass(clazz).newInstance();
				interceptors.put(clazz, messageInterceptor);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 添加不可用的插件，即添加的插件类是不能使用的
	 * @param clazz
	 */
	public void addInvalidInterceptor(String clazz){
		
	}
	
	/**
	 * 删除不可用的插件，也就是说这个插件将来是可以使用的
	 * @param clazz
	 */
	public void removeInvalidInterceptor(String clazz){
		
	}

	/**
	 * 根据MessageInterceptor的全称路径添加拦截器
	 * 
	 * @param clazz
	 */
	public void addInterceptor(String clazz, MessageInterceptor interceptor) {
		// 判断是否已经存在该拦截器
		if (!interceptors.containsKey(clazz)) {
			interceptors.put(clazz, interceptor);
			//如果interceptorList的长度大于0，则表示里面的内容不为空，因此放在链表的最后面
			if(interceptorList.size()>0){
				//将新添加的拦截器放在链表的最后面
				interceptorList.getLast().setNextInterceptor(interceptor);
				interceptorList.addLast(interceptor);
			}else{
				//将动态获取的第一个拦截器放在List容器中，是第一个
				interceptorList.addLast(interceptor);
			}
		}

	}
	

	/**
	 * 删除指定的拦截器
	 * 
	 * @param clazz
	 */
	public void removeInterceptor(String clazz) {
		//如果指定的拦截器对象存在，则需要在链表中删除
		if(interceptors.containsKey(clazz)){
			interceptorList.remove(interceptors.containsKey(clazz));
		}
		interceptors.remove(clazz);
	}
	
	/**
	 * 
	 * 获取消息拦截器的个数
	 */
	public int getInterceptorSize(){
		return interceptors.size();
	}

	/**
	 * 处理发送过来的xml数据
	 * @param xmlStr
	 */
	public String interceptorsAction(String xmlStr) {
//		Collection<MessageInterceptor> collection = interceptors.values();
//		MessageInterceptor[] messageInterceptorArray = this.initInterceptorList();
//		int length = messageInterceptorArray.length;
//		if(length > 0){
//			messageInterceptorArray[0].handle(xmlStr);
//		}
//		ServletRequest request1 = request;
//		for(int i = 0; i < length; i++){
//			xmlStr = messageInterceptorArray[i].handle(xmlStr);
//		}
		
		//从链表的第一个拦截器开始处理字符串
		if(interceptorList.size()>0){
			xmlStr = interceptorList.get(0).handle(xmlStr);
		}
		
		return xmlStr;
	}
	
	/**
	 * 将拦截器串联起来，即使一个链表的数据结构
	 */
	/*public MessageInterceptor[] initInterceptorList(){
		Collection<MessageInterceptor> collection = interceptors.values();
		MessageInterceptor[] messageInterceptorArray = new MessageInterceptor[interceptors.size()];
		int i = 0;
		for (MessageInterceptor interceptor : collection) {
			messageInterceptorArray[i] = interceptor;
			if(i > 0){
				messageInterceptorArray[i-1].setNextInterceptor(messageInterceptorArray[i-1]);
			}
		}
		return messageInterceptorArray;
	}*/
	
	

}
