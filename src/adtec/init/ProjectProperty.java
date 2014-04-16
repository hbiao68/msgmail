package adtec.init;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 主要是记录WEB-INF/config/project/project.properties文件的信息
 * @author maojd
 */
public class ProjectProperty {

	private static ProjectProperty projectProperty = null;
	private static Map<String, String> propertise = new HashMap<String, String>();
	
	public static Map<String, String> getPropertise() {
		return propertise;
	}
	public static void setPropertise(Map<String, String> propertise) {
		ProjectProperty.propertise = propertise;
	}
	
	private ProjectProperty(){
		
	}
	/**
	 * 懒汉式 单例模式，当发现ProjectProperty实例没有初始化的时候,初始化，并返回唯一实例
	 */
	public static synchronized ProjectProperty getInstance(){
		if(projectProperty == null){
			projectProperty = new ProjectProperty();
		}
		return projectProperty;
	}
	
	/**
	 * 通过key获取参数值
	 * @param key 
	 * @return 从配置文件获取到的值
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
	
	public void loadProperties(Properties prop){
		if(null == prop){
			return;
		}
		if(!prop.isEmpty()){
			Set set = prop.keySet();
			Iterator it = set.iterator();
			while(it.hasNext()){
				String id = (String) it.next();
				if(propertise.get(id) == null){
					String value = prop.getProperty(id);
					propertise.put(id, value);
				}
			}
		}
	}
}
