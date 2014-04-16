package adtec.privilege.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.privilege.dao.ResourceDao;
import adtec.privilege.model.Resource;
import adtec.privilege.service.ResourceService;

/**
 * 资源管理service实现
 * @author maojd
 * @date 15:50 2014/2/21
 */
@Transactional(propagation = Propagation.REQUIRED)
public class ResourceServiceImp implements ResourceService {
	
	private Logger log = Logger.getLogger(ResourceServiceImp.class);
	private ResourceDao resourceDao;

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	
	@Override
	public List<Resource> queryAllResource(Resource resource) throws Exception {
		List<Resource> list = new ArrayList<Resource>();
		try {
			list = resourceDao.queryAllResource(resource);
		} catch (Exception e) {
			log.error("查询所有资源出错   queryAllResource error");
			throw e;
		}
		return list;
	}

	@Override
	public Resource queryResByResid(String resid) throws Exception {
		Resource resource = new Resource();
		try {
			resource = resourceDao.queryResByResid(resid);
		} catch (Exception e) {
			log.error("queryResByResid error 通过res查询资源出错");
			throw e;
		}
		return resource;
	}

	@Override
	public boolean updateRes(Resource res) throws Exception {
		boolean b = true;
		try {
			resourceDao.updateRes(res);
		} catch (Exception e) {
			b = false;
			log.error("updateRes error 资源修改出错");
			throw e;
		}
		return b;
	}

	@Override
	public boolean insertRes(Resource resource) throws Exception{
		boolean b = true;
		try {
			resourceDao.insertRes(resource);
		} catch (Exception e) {
			b = false;
			log.error("insertRes error 添加资源出错");
			throw e;
		}
		return b;
	}

	@Override
	public boolean deleteRes(Resource res) throws Exception{
		boolean b = true;
		try {
			resourceDao.deleteRes(res);
		} catch (Exception e) {
			b = false;
			log.error("deleteRes error 删除资源出错");
			throw e;
		}
		return b;
	}

	/**
	 * 查询资源数量（带模糊查询）
	 */
	@Override
	public int queryResourceCount(Resource resource) throws Exception {
		int count = 0;
		try {
			count = resourceDao.queryResourceCount(resource);
		} catch (Exception e) {
			log.error("queryResourceCount error 查询资源数量出错");
			throw e;
		}
		return count;
	}

	/**
	 * 批量删除
	 */
	@Override
	public boolean deleteRessById(String resids) throws Exception {
		boolean b = true;
		try {
			List<String> idList = new ArrayList<String>();
			for(int i = 0; i < resids.split(",").length; i++ ){
				idList.add(resids.split(",")[i]);
			}
			resourceDao.deleteRessById(idList);
		} catch (Exception e) {
			log.error("deleteRessById error 批量删除资源出错");
			b = false;
			throw e;
		}
		return b;
	}

	/**
	 * 通过对象查询资源集合
	 */
	@Override
	public List<Resource> queryResourceByObj(Resource res) throws Exception {
		List<Resource> list = new ArrayList<Resource>();
		try {
			list = resourceDao.queryResByObj(res);
		} catch (Exception e) {
			log.error("queryResourceByObj error 通过");
			throw e;
		}
		return list;
	}


}
