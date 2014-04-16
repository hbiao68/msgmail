package adtec.privilege.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.privilege.dao.TypeDao;
import adtec.privilege.model.Role;
import adtec.privilege.model.Type;
import adtec.privilege.service.TypeService;

/**
 * 操作类型的实现类
 * @author maojd
 * @date 9:41 2014/3/3
 */
@Transactional(propagation = Propagation.REQUIRED)
public class TypeServiceImp implements TypeService {

	private TypeDao typeDao;
	private Logger log = Logger.getLogger(TypeServiceImp.class);
	public TypeDao getTypeDao() {
		return typeDao;
	}
	public void setTypeDao(TypeDao typeDao) {
		this.typeDao = typeDao;
	}

	@Override
	public List<Type> queryAllType(Type type) throws Exception{
		List<Type> list = new ArrayList<Type>();
		try {
			list = typeDao.queryAllType(type);
		} catch (Exception e) {
			log.error("queryAllType error查询所有操作类型出错");
			throw e;
		}
		return list;
	}
	
	@Override
	public boolean insertType(Type type) throws Exception {
		boolean b = true;
		try {
			typeDao.insertType(type);
		} catch (Exception e) {
			b = false;
			log.error("insertType error");
			throw e;
		}
		return b;
	}
	@Override
	public Type queryTypeById(String typeid) throws Exception {
		Type type = new Type();
		try {
			type = typeDao.queryTypeById(typeid);
		} catch (Exception e) {
			log.error("queryTypeById error 通过id查询操作类型出错");
			throw e;
		}
		return type;
	}
	@Override
	public boolean updateType(Type type) throws Exception {
		boolean b = true;
		try {
			typeDao.updateType(type);
		} catch (Exception e) {
			b = false;
			log.error("updateType error修改操作类型出错");
			throw e;
		}
		return b;
	}
	@Override
	public boolean deleteType(Type type) throws Exception {
		boolean b = true;
		try {
			typeDao.deleteType(type);
		} catch (Exception e) {
			log.error("deleteType error 删除操作类型出错");
			throw e;
		}
		return b;
	}
	/**
	 * 查询总数（包括模糊查询）
	 */
	@Override
	public int queryTypeCount(Type type) throws Exception {
		int count = 0;
		try {
			count = typeDao.queryTypeCount(type);
		} catch (Exception e) {
			log.error("queryTypeCount error 查询操作类型数量出错");
			throw e;
		}
		return count;
	}
	@Override
	public boolean deleteUsersById(String typeids) throws Exception {
		boolean b = true;
		try {
			List<String> idList = new ArrayList<String>();
			for(int i = 0; i < typeids.split(",").length; i++ ){
				idList.add(typeids.split(",")[i]);
			}
			typeDao.deleteUsersById(idList);
		} catch (Exception e) {
			b = false;
			log.error("deleteUsersById error通过id批量删除出错");
			throw e;
		}
		return b;
	}
	@Override
	public List<Role> queryTypeByObj(Type type) throws Exception {
		List<Role> list = new ArrayList<Role>();
		try {
			list = typeDao.queryTypeByObj(type);
		} catch (Exception e) {
			log.error("queryTypeByObj error 通过对象查询出错");
			throw e;
		}
		return list;
	}

}
