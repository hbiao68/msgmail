package adtec.privilege.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.privilege.dao.ColumnDao;
import adtec.privilege.dao.ResourceDao;
import adtec.privilege.model.Column;
import adtec.privilege.model.Resource;
import adtec.privilege.service.ColumnService;
import adtec.privilege.service.ResourceService;

/**
 * 消息邮局左侧菜单栏位的实现类
 * @author maojd
 * @date 14:03 2014/4/1
 */
@Transactional(propagation = Propagation.REQUIRED)
public class ColumnServiceImp implements ColumnService {

	private ColumnDao columnDao;
	private ResourceService resourceService;
	private Logger log = Logger.getLogger(ColumnServiceImp.class);
	public ColumnDao getColumnDao() {
		return columnDao;
	}
	public void setColumnDao(ColumnDao columnDao) {
		this.columnDao = columnDao;
	}
	public ResourceService getResourceService() {
		return resourceService;
	}
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	/**
	 * 查询所有菜单栏位（带分页）
	 */
	@Override
	public List<Column> queryAllColumn(Column column) throws Exception{
		List<Column> list = new ArrayList<Column>();
		try {
			list = columnDao.queryAllColumn(column);
			//循环遍历。把resource赋值给栏位 的资源对象
			for(Column columnNew:list){
				Resource resource = resourceService.queryResByResid(columnNew.getResid());//查询到资源赋值给 栏位的res对象
				columnNew.setResource(resource);
			}
		} catch (Exception e) {
			log.error("queryAllColumn error查询所有栏位出错");
			throw e;
		}
		return list;
	}
	
	/**
	 * 通过对象查询栏位
	 */
	@Override
	public List<Column> queryColumnByObj(Column column) {
		List<Column> list = new ArrayList<Column>();
		try {
			list = columnDao.queryColumnByObj(column);
			for(Column columnNew:list){
				columnNew.setResource(resourceService.queryResByResid(columnNew.getResid()));
			}
		} catch (Exception e) {
			log.error("queryColumnByObj error通过对象查询");
		}
		return list;
	}
	/**
	 * 查询栏位总数（带模糊查询）
	 */
	@Override
	public int queryColumnCount(Column column) throws Exception {
		int count = 0;
		try {
			count = columnDao.queryColumnCount(column);
		} catch (Exception e) {
			log.error("queryColumnCount error 查询栏位总数出错");
			throw e;
		}
		return count;
	}
	
	/**
	 * 添加栏位
	 */
	@Override
	public boolean insertColumn(Column column) throws Exception {
		boolean b = true;
		try {
			columnDao.insertColumn(column);
		} catch (Exception e) {
			b = false;
			log.error("insertColumn error 添加栏位出错");
			throw e;
		}
		return b;
	}
	/**
	 * 通过对象查询数量
	 */
	@Override
	public int queryCountByObj(Column column) throws Exception {
		int count = 0;
		try {
			count = columnDao.queryCountByObj(column);
		} catch (Exception e) {
			log.error("queryCountByObj error 通过对象查询数量");
			throw e;
		}
		return count;
	}
	
	/**
	 * 修改栏位的实现
	 */
	@Override
	public boolean updateColumn(Column column) throws Exception {
		boolean b = true;
		try {
			columnDao.updateColumn(column);
		} catch (Exception e) {
			b = false;
			log.error("updateColumn error 修改栏位信息出错");
			throw e;
		}
		return b;
	}
	/**
	 * 批量删除
	 */
	@Override
	public boolean deleteColumnsById(String ids) throws Exception {
		boolean b = true;
		try {
			List<String> idsList = new ArrayList<String>();
			String[] idsArray = ids.split(",");
			for(int i = 0;i<idsArray.length;i++){
				idsList.add(idsArray[i]);
			}
			
			columnDao.deleteColumnsById(idsList);
		} catch (Exception e) {
			b = false;
			log.error("deleteColumnsById error 批量删除出错");
		}
		return b;
	}
	
	

}
