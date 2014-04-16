package adtec.init;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 主要是记录WEB-INF/config/ajmessage/ajmessage.properties文件的信息
 * @author huangbiao
 */
public class AjmessageProperty {

	private static AjmessageProperty ajmessageProperty = null;
	private static Map<String,String> propertise = new HashMap<String,String>();
	
	public Map<String, String> getPropertise() {
		return propertise;
	}

	public void setPropertise(Map<String, String> propertise) {
		this.propertise = propertise;
	}

	private AjmessageProperty(){
		
	}
	
	public static synchronized AjmessageProperty getInstance(){
		if(null == ajmessageProperty){
			ajmessageProperty = new AjmessageProperty();
		}
		return ajmessageProperty;
	}
	
	/**
	 * 获取key对象
	 * @param key
	 * @return
	 */
	public String get(String key){
		if(propertise.get(key) != null){
			return propertise.get(key);
		}
		return null;
	}
	
	/**
	 * 添加key-value数据
	 * @param key
	 * @param value
	 */
	public void add(String key,String value){
		propertise.put(key, value);
	}
	
	/**
	 * 将Properties对象转为hashmap对象中缓存起来
	 * @param prop
	 */
	public void loadProperties(Properties prop){
		if(null == prop){
			return;
		}
		//遍历Properties对象，将其保存在map对象中
		if(!prop.isEmpty()){
			Set set = prop.keySet();
			Iterator it = set.iterator();
			while(it.hasNext()){
				String id = (String)it.next();
				if(propertise.get(id) == null){
					String value = prop.getProperty(id);
					propertise.put(id, value);
				}
			}
		}
		
	}
	
	
}
