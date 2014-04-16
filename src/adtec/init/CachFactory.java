package adtec.init;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂模式，主要用来创建map缓存数据库相应数据
 * @author huangbiao
 * 
 */
public class CachFactory {
	
	private static CachFactory cachFactory = null;
	private Map<String,Map<String, Object>> factoryMap = new HashMap<String,Map<String, Object>>();
	
	private CachFactory(){
	}
	
	public static CachFactory getInstance(){
		if(cachFactory == null){
			cachFactory = new CachFactory();
		}
		return cachFactory;
	}
	
	/**
	 * 创建key为cachName的Map对象
	 * @param cachName
	 * @return
	 */
	public Map<String,Object> createCach(String cachName){
		Map<String, Object> map = new HashMap<String,Object>();
		factoryMap.put(cachName, map);
		return map;
	}
	
	/**
	 * 判断是否存在key为cachName的map
	 * @param cachName
	 * @return
	 */
	public boolean isExist(String cachName){
		if(factoryMap.containsKey(cachName)){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取key为cachName的map对象
	 * @param cachName
	 * @return
	 */
	public Map<String, Object> getMapByKey(String cachName){
		if(factoryMap.containsKey(cachName)){
			return factoryMap.get(cachName);
		}
		return null;
	}
	
	
	
}
