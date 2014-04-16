package adtec.privilege.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.privilege.dao.PrivilegeDao;
import adtec.privilege.dao.ResourceDao;
import adtec.privilege.model.Privilege;
import adtec.privilege.model.Resource;
import adtec.privilege.model.Type;
import adtec.privilege.service.PrivilegeService;
import adtec.privilege.service.ResourceService;
import adtec.privilege.service.TypeService;

/**
 * 权限管理service的实现
 * @author maojd
 * @date 14:36 2014/2/24
 */
@Transactional(propagation = Propagation.REQUIRED)
public class PrivilegeServiceImp implements PrivilegeService {

	private PrivilegeDao privilegeDao;
	private ResourceService resourceService; //资源service
	private TypeService typeService;
	
	public ResourceService getResourceService() {
		return resourceService;
	}
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	Logger log = Logger.getLogger(PrivilegeServiceImp.class);
	
	public PrivilegeDao getPrivilegeDao() {
		return privilegeDao;
	}
	public void setPrivilegeDao(PrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}
	public TypeService getTypeService() {
		return typeService;
	}
	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}
	@Override
	public List<Privilege> queryAllPrivilege(Privilege privilege) throws Exception {
		List<Privilege> list = new ArrayList<Privilege>();
		try {
			list = privilegeDao.queryAllPrivilege(privilege); //权限结果集
			
			//遍历权限结果集，set一下资源属性
			for(Privilege privilegeNew:list){
				String resid = privilegeNew.getResid();//获取到权限的外键  （资源主键）
				Resource resource = resourceService.queryResByResid(resid);//通过id查询一条资源
				privilegeNew.setResource(resource);					//把资源对象 set给 权限的一个属性。在jsp中 privilege.resource.resname 
				
				Type type = typeService.queryTypeById(privilegeNew.getActionType());
				privilegeNew.setType(type);
			}
		} catch (Exception e) {
			log.error("queryAllPrivilege error 查询所有权限出错");
			throw e;
		}
		
		return list;
	}
	@Override
	public List<Resource> queryAllResources() throws Exception{
		List<Resource> list = new ArrayList<Resource>();
		
		list = resourceService.queryAllResource(new Resource());
		
		return list;
	}
	@Override
	public boolean insertPrivilege(Privilege privilege) throws Exception{
		boolean b = true;
		try {
			privilegeDao.insertPrivilege(privilege);
		} catch (Exception e) {
			b = false;
			log.error("insertPrivilege error 添加权限出错");
			throw e;
		}
		
		return b;
	}
	@Override
	public Privilege queryPrivilegeById(String privilegeId) throws Exception{
		Privilege privilege = new Privilege();
		try {
			privilege = privilegeDao.queryPrivilegeById(privilegeId);
			Resource resource = resourceService.queryResByResid(privilege.getResid());//通过权限外键查询一条资源
			privilege.setResource(resource);							//把资源作为属性赋值给 权限实体
		
			Type type = typeService.queryTypeById(privilege.getActionType());
			privilege.setType(type);
		} catch (Exception e) {
			log.error("queryPrivilegeById error 通过id查询权限出错");
			throw e;
		}
		
		return privilege;
	}

	@Override
	public boolean updatePrivilege(Privilege privilege) throws Exception {
		boolean b = true;
		try {
			privilegeDao.updatePrivilege(privilege);
		} catch (Exception e) {
			b = false;
			log.error("updatePrivilege error 修改权限出错");
			throw e;
		}
		return b;
	}
	@Override
	public boolean deletePrivilege(Privilege privilege) throws Exception {
		boolean b = true;
		try {
			privilegeDao.deletePrivilege(privilege);
		} catch (Exception e) {
			b = false;
			log.error("deletePrivilege error 删除权限记录出错");
		}
		return b;
	}
	
	/**
	 * 查询权限总数量（包括模糊查询）
	 */
	@Override
	public int queryPrivlgCount(Privilege privlg) throws Exception {
		int count = 0;
		try {
			count = privilegeDao.queryPrivlgCount(privlg);
		} catch (Exception e) {
			log.error("queryPrivlgCount error 查询权限总数量出错");
			throw e;
		}
		return count;
	}
	
	/**
	 * 批量添加权限
	 */
	@Override
	public boolean batchInsertPrivilege(List<Privilege> privlgList) throws Exception{
		boolean b = true;
		try {
			privilegeDao.batchInsertPrivilege(privlgList);
		} catch (Exception e) {
			b = false;
			log.error("batchInsertPrivilege error 批量添加权限出错");
			throw e;
		}
		return b;
	}
	
	/**
	 * 通过主键 privilegeid 或者   资源id 、操作类型 验证是否存在
	 */
	@Override
	public boolean queryPrivlgIfExists(Privilege privilege) throws Exception{
		boolean b = false;//默认不存在
		try {
			List<Privilege> list = privilegeDao.queryPrivlgIfExists(privilege);
			if(list.size()>0){
				b = true;//存在
			}
		} catch (Exception e) {
			log.error("queryPrivlgIfExists error 查询权限是否存在出错");
			throw e;
		}
		return b;
	}
	/**
	 * 批量删除权限
	 */
	@Override
	public boolean deletePrivilegesById(String privilegeids) throws Exception{
		boolean b = true;
		try {
			List<String> idList = new ArrayList<String>();
			for(int i = 0; i < privilegeids.split(",").length; i++ ){
				idList.add(privilegeids.split(",")[i]);
			}
			privilegeDao.deletePrivilegesById(idList);
		} catch (Exception e) {
			b = false;
			log.error("deletePrivilegesById error 批量删除权限出错");
		}
		return b;
	}
	
	/**
	 * 通过对象查询数量（主要用于验证，eg:验证资源是否开通了某些权限）
	 */
	@Override
	public int queryCountByObj(Privilege privilege) throws Exception {
		int count = 0;
		try {
			count = privilegeDao.queryCountByObj(privilege);
		} catch (Exception e) {
			log.error("queryCountByObj error 通过对象查询出错");
			throw e;
		}
		return count;
	}


	
}
