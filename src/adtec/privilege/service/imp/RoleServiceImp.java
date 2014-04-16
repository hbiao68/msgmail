package adtec.privilege.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adtec.privilege.dao.RoleDao;
import adtec.privilege.model.Role;
import adtec.privilege.service.RoleService;

@Transactional(propagation = Propagation.REQUIRED)
public class RoleServiceImp implements RoleService{

	private RoleDao roleDao;
	Logger log = Logger.getLogger(RoleServiceImp.class);
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> queryAllRole(Role role) throws Exception {
		List<Role> list = new ArrayList<Role>();
		try {
			list = roleDao.queryAllRole(role);
		} catch (Exception e) {
			log.error("queryAllRole error");
			throw e;
		}
		return list;
	}

	@Override
	public boolean insertRole(Role role) throws Exception {
		boolean b = true;
		try {
			roleDao.insertRole(role);
		} catch (Exception e) {
			b = false;
			log.error("添加角色出错 insertRole error");
			throw e;
		}
		return b;
	}

	@Override
	public List<Role> queryRoleByObj(Role role) throws Exception {
		List<Role> list = new ArrayList<Role>();
		try {
			list = roleDao.queryRoleByObj(role);
			/*if(list.size()>0){
				roleRes = list.get(0);
			}*/
		} catch (Exception e) {
			log.error("通过role对象查询出错 queryRoleByObj error");
			throw e;
		}
		return list;
	}

	@Override
	public boolean updateRole(Role role) throws Exception {
		boolean b = true;
		try {
			
			roleDao.updateRole(role);
		
		} catch (Exception e) {
			b = false;
			log.error("修改角色出错 updateRole error");
			throw e;
		}
		return b;
	}

	@Override
	public boolean deleteRole(Role role) throws Exception {
		boolean b = true;
		try {
			roleDao.deleteRole(role);
		} catch (Exception e) {
			log.error("删除角色出错  deleteRole error ");
			b = false;
			throw e;
		}
		return b;
	}

	@Override
	public Role queryRoleByRoleid(String roleid) throws Exception {
		Role role = new Role();
		try {
			role = roleDao.queryRoleByRoleid(roleid);
		} catch (Exception e) {
			log.error("queryRoleByRoleid  error 通过id查询一条角色记录出错");
			throw e;
		}
		return role;
	}

	/**
	 * 查询角色总数（带模糊查询）
	 */
	@Override
	public int queryRoleCount(Role role) throws Exception {
		int i = 0;
		try {
			i = roleDao.queryRoleCount(role);
		} catch (Exception e) {
			log.error("queryRoleCount error 查询角色数量出错");
			throw e;
		}
		return i;
	}

	/**
	 * 批量删除角色
	 */
	@Override
	public boolean deleteRolesById(String roleids) throws Exception {
		boolean b = true;
		try {
			List<String> idList = new ArrayList<String>();
			for(int i = 0; i < roleids.split(",").length; i++ ){
				idList.add(roleids.split(",")[i]);
			}
			roleDao.deleteRolesById(idList);
		} catch (Exception e) {
			log.error("deleteRolesById error 批量删除角色出错");
			b = false;
			throw e;
		}
		return b;
	}
	
	
}
