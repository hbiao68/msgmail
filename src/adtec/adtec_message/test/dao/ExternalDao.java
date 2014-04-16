package adtec.adtec_message.test.dao;

import java.util.List;
import java.util.Map;

public interface ExternalDao {

	/**
	 * 通过层级结构获取主键
	 * 2014年3月18日 15:16:17
	 * @param map
	 * @return 主键
	 * @author lj
	 */
	public String queryorgIdByLevel(Map<String,String> map);
	
	public List<Map<String,Object>> queryAccountByorgIdList(List<String> list); 
	
}
