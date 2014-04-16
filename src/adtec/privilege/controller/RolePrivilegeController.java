package adtec.privilege.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import adtec.init.CachFactory;
import adtec.privilege.model.Page;
import adtec.privilege.model.Privilege;
import adtec.privilege.model.Resource;
import adtec.privilege.model.Role;
import adtec.privilege.model.RolePrivilege;
import adtec.privilege.model.Type;
import adtec.privilege.service.PrivilegeService;
import adtec.privilege.service.RolePrivilegeService;

/**
 * 角色、权限controller类 、用来处理页面发送的请求
 * @author maojd
 * @date 11:09 2014/2/27
 */
@Controller
@RequestMapping(value="/rolePrivilege")
public class RolePrivilegeController {

	private RolePrivilegeService rolePrivilegeService;
	private PrivilegeService privilegeService;
	private Map<String, Object> rolePlgMap = CachFactory.getInstance().getMapByKey("rolePlgMap");
	private Map<String, Object> rolePlgIdsMap = CachFactory.getInstance().getMapByKey("rolePlgIdsMap");
	
	private Logger log = Logger.getLogger(RolePrivilegeController.class);
	public RolePrivilegeService getRolePrivilegeService() {
		return rolePrivilegeService;
	}
	public void setRolePrivilegeService(RolePrivilegeService rolePrivilegeService) {
		this.rolePrivilegeService = rolePrivilegeService;
	}
	
	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}
	public Map<String, Object> getRolePlgMap() {
		if(rolePlgMap == null){
			rolePlgMap = CachFactory.getInstance().getMapByKey("rolePlgMap");
		}
		return rolePlgMap;
	}
	public void setRolePlgMap(Map<String, Object> rolePlgMap) {
		this.rolePlgMap = rolePlgMap;
	}
	
	public Map<String, Object> getRolePlgIdsMap() {
		if(rolePlgIdsMap == null){
			rolePlgIdsMap = CachFactory.getInstance().getMapByKey("rolePlgIdsMap");
		}
		return rolePlgIdsMap;
	}
	public void setRolePlgIdsMap(Map<String, Object> rolePlgIdsMap) {
		this.rolePlgIdsMap = rolePlgIdsMap;
	}
	/**
	 * 查询所有角色、权限关系 的跳转
	 * @return 返回显示页面
	 */
	@RequestMapping(value="/queryAllRolePrivilege")
	public String queryAllRolePrivilege(Map<String, Object> map){
		/*try {
//			List<RolePrivilege> list = rolePrivilegeService.queryAllRolePrivilege();
//			map.put("rolePlgList", list);
			map.put("rolePlgList", getRolePlgMap());
			
		} catch (Exception e) {
			log.error("queryAllRolePrivilege error 查询所有角色、权限出错");
			
		}*/
		return "privilege/rolePrivilege/rolePrivilegeList";
	}
	
	/**
	 * 查询所有 角色权限(带分页)
	 * @param page 分页对象
	 * @param request 获取分页等参数
	 * @param response 
	 */
	@RequestMapping(value="/queryAllRolePrivilegeAction",method={RequestMethod.POST,RequestMethod.GET})
	public void queryAllRolePrivilegeAction(RolePrivilege rolePrivlg,Role role,HttpServletRequest request,HttpServletResponse response){
		//分页查询
		List<RolePrivilege> rolePlgList  = new ArrayList<RolePrivilege>();
		/*RolePrivilege rolePrivlg = new RolePrivilege();
		rolePrivlg.setPage(page);*/
		rolePrivlg.setRole(role);
		
		
		try {
			rolePlgList = rolePrivilegeService.queryAllRolePrivilege(rolePrivlg);
		} catch (Exception e) {
			log.error("queryAllRolePrivilege error 分页查询角色权限出错");
		}
		
		
		//查询总数
		int count = 0;
		try {
			count = rolePrivilegeService.queryRolePrivlgCount(rolePrivlg);
		} catch (Exception e) {
			log.error("queryRolePrivlgCount error 查询角色权限出错");
		}
		
		
		//输出数据
		Map<String, Object> rolePlgEasyUIMap = new HashMap<String, Object>();
		rolePlgEasyUIMap.put("rows", rolePlgList);
		rolePlgEasyUIMap.put("total", count);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(JSONObject.fromObject(rolePlgEasyUIMap).toString());//输入的是json key Object的字符串
			pw.flush();
		} catch (IOException e) {
			//e.printStackTrace();
			log.error("输入json出错");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		
	}
	
	/**
	 * 添加角色、权限关系的跳转
	 * @param map 把 资源和操作类型 数据放入map容器中，便于页面取值
	 * @return
	 */
	@RequestMapping(value="/insertRolePrivilege")
	public String insertRolePrivilege(Map<String, Object> map){
		
		Map<String, Object> resMap = CachFactory.getInstance().getMapByKey(
				"resMap");
		Map<String, Object> typeMap = CachFactory.getInstance()
				.getMapByKey("typeMap");
		map.put("resList", resMap);
		map.put("typeList", typeMap);
		
		return "privilege/rolePrivilege/rolePrivilegeInsert";
	}
	
	/**
	 *  添加角色权限的实现
	 * @param request 从页面获取 角色，资源 操作类型值
	 * @param actionValue 从页面获取boolean类权限的的actionValue
	 * @return 返回数据显示页面
	 */
	@RequestMapping(value="/insertRolePrivilegeAction",method={RequestMethod.POST,RequestMethod.GET})
	public String insertRolePrivilegeAction(HttpServletRequest request,boolean actionValue){
		
		//1. 角色List
		String roleStr = request.getParameter("roleRows");
		if(roleStr == null || roleStr == "undefined"){
			roleStr = "";
		}
		JSONArray roleJsonArray = JSONArray.fromObject(roleStr);
		List<Role> roleList = JSONArray.toList(roleJsonArray, Role.class);
		
		
		
		//2. 批量处理权限数据privlgAddRoleList
		//权限list。添加角色权限的时候，验证权限是否存在，存在的话添加到 privlgAddRoleList。
		//不存在的话，记录，批量添加。之后 也添加到   privlgAddRoleList
		List<Privilege> privlgAddRoleList = new ArrayList<Privilege>();//要给用户添加的权限list
		
		String resActionIds = request.getParameter("resActionIds"); //获取到一个id ( 资源id+操作类型的id )
		resActionIds = resActionIds.replace(" ", "");//去掉空格
		if(resActionIds != null && resActionIds != "" && resActionIds != "undefined"){
			
			List<Privilege> privlgList = new ArrayList<Privilege>(); //要批量添加的，不存在的权限。
			String arrayIds [] = resActionIds.split(",");
			for(String resActId:arrayIds){
				
				String resid = resActId.substring(0, 32); //前32位是resid 资源id
				String actionType = resActId.substring(32, resActId.length()); //后32位是 操作类型id
		
				Privilege privlg = new Privilege();
				
				privlg.setResid(resid);
				privlg.setActionType(actionType);
				try {
					//ifExists = privilegeService.queryPrivlgIfExists(privlg);
					Privilege ifExistsPrivlg = (Privilege) CachFactory.getInstance().getMapByKey("privilegeIdsMap").get(resid+actionType);//
					
					if(ifExistsPrivlg == null){//如果不存在，批量添加
						String uuid = UUID.randomUUID().toString().replace("-", "");
						privlg.setPrivilegeid(uuid);
						privlg.setActionValue(actionValue);
						privlgList.add(privlg); //准备批量添加到权限数据库
						
						
						Resource resource = (Resource) CachFactory.getInstance().getMapByKey("resMap").get(privlg.getResid());
						privlg.setResource(resource);
						Type type = (Type) CachFactory.getInstance().getMapByKey("typeMap").get(privlg.getActionType());
						privlg.setType(type);
						
						privlgAddRoleList.add(privlg);//记录下来，等给用户开通权限
					}else{
						//否则，记录下来。
						
						privlg = ifExistsPrivlg;//存在的这个权限
						privlgAddRoleList.add(privlg);
					}
				} catch (Exception e) {
					log.error("queryPrivlgIfExists error 验证权限是否存在出错");
				}
				
			}
			
			
			//循环完以后，批量添加不存在的权限
			try {
				boolean b = privilegeService.batchInsertPrivilege(privlgList);
				
				//添加成功以后。把权限的全局 privilegeMap privilegeIdsMap两个容器也添加相应数据
				if(b){ 
					for(Privilege priAdd:privlgList){
						Map<String, Object> privilegeMap = CachFactory.getInstance().getMapByKey("privilegeMap");
						Map<String, Object> privilegeIdsMap = CachFactory.getInstance().getMapByKey("privilegeIdsMap");
						privilegeMap.put(priAdd.getPrivilegeid(), priAdd);
						privilegeIdsMap.put(priAdd.getResid()+priAdd.getActionType(), priAdd);
					}
				}
				
				log.debug("batchInsertPrivilege " + b);
			} catch (Exception e) {
				log.error("batchInsertPrivilege error批量添加权限出错");
			}
			
		}
		
		
		//3. 权限处理完成以后，批量添加角色、权限
		if(roleList != null && privlgAddRoleList != null){
			List<RolePrivilege> rolePrivlgList = new ArrayList<RolePrivilege>();
			for(Role role:roleList){
				for(Privilege privlg:privlgAddRoleList){
					RolePrivilege rolePrivlg = new RolePrivilege();
					rolePrivlg.setRoleid(role.getRoleid());			//set角色id
					rolePrivlg.setPrivilegeid(privlg.getPrivilegeid());//set权限id
					try {
						//通过 roleid 和 privlgid综合验证 是否存在。
						//RolePrivilege ifExistsRolePrivlg = (RolePrivilege) CachFactory.getInstance().getMapByKey("rolePlgIdsMap").get(rolePrivlg.getRoleid()+rolePrivlg.getPrivilegeid());
						RolePrivilege ifExistsRolePrivlg = (RolePrivilege) getRolePlgIdsMap().get(rolePrivlg.getRoleid()+rolePrivlg.getPrivilegeid());
						
						if(ifExistsRolePrivlg == null){ //如果不存在.set一个主键，添加
							String uuid = UUID.randomUUID().toString().replace("-", "");
							rolePrivlg.setRolePlgId(uuid);
							
							//添加时候也把role 和 privlg属性给  角色、权限对象。 便于批量添加成功后，往 userRoleMap中放
							rolePrivlg.setRole(role);
							rolePrivlg.setPrivilege(privlg);
							rolePrivlgList.add(rolePrivlg);//把符合条件的 角色、权限 添加到list,批量添加
						}
					} catch (Exception e) {
						log.error("queryUserRoleIfExists error 验证用户角色是否存在出错");
					}
					
				}
				
			}
			
			//遍历完成以后，把符合条件的（避免重复赋予某个角色）批量添加
			try {
				boolean b = rolePrivilegeService.batchInsertRolePrivlgs(rolePrivlgList);
				if(b){
					//添加成功以后，往userRoleMap中放入数据
					for(RolePrivilege rolePrivlg:rolePrivlgList){
						getRolePlgMap().put(rolePrivlg.getRolePlgId(), rolePrivlg);
						getRolePlgIdsMap().put(rolePrivlg.getRoleid()+rolePrivlg.getPrivilegeid(), rolePrivlg);
						//用户权限的总map中通过角色权限添加相应用户前
						rolePrivilegeService.putPlgIntoUserPlgAllMap(rolePrivlg);
					}
				}
			} catch (Exception e) {
				log.error("batchInsertUserRoles error 批量添加角色权限出错");
			}
			
		}
		
		
		/*String uuid = UUID.randomUUID().toString().replace("-", "");
		rolePrivilege.setRolePlgId(uuid);
		try {
			boolean b = rolePrivilegeService.insertRolePrivilege(rolePrivilege);
			if(b){//如果添加成功，总的map容器中也添加相应数据
				Role role = (Role) CachFactory.getInstance().getMapByKey("roleMap").get(rolePrivilege.getRoleid());
				rolePrivilege.setRole(role);
				Privilege privilege = (Privilege) CachFactory.getInstance().getMapByKey("privilegeMap").get(rolePrivilege.getPrivilegeid());
				rolePrivilege.setPrivilege(privilege);
				
				getRolePlgMap().put(rolePrivilege.getRolePlgId(), rolePrivilege);
				
			}
		} catch (Exception e) {
			log.error("insertRolePrivilegeAction error 添加角色 权限出错");
		}*/
		return "redirect:/rolePrivilege/queryAllRolePrivilege.do";
	}
	
	/**
	 * 删除角色、权限数据
	 * @param rolePrivilege 要删除的实体
	 * @return 返回数据显示页面
	 */
	@RequestMapping(value="/deleteRolePrivilege",method={RequestMethod.GET,RequestMethod.POST})
	public String deleteRolePrivilege(RolePrivilege rolePrivilege){
		
		try {
			boolean b = rolePrivilegeService.deleteRolePrivilege(rolePrivilege);
			if(b){
				getRolePlgMap().remove(rolePrivilege.getRolePlgId());
			}
		} catch (Exception e) {
			log.error("deleteRolePrivilege error 删除角色、权限出错");
		}
		return "redirect:/rolePrivilege/queryAllRolePrivilege.do";
	}
	
	/**
	 * 批量删除
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteRolePrivlgsById",method={RequestMethod.POST,RequestMethod.GET})
	public String deleteRolePrivlgsById(HttpServletRequest request){
		String ids = request.getParameter("ids");
		if(ids != null && ids != "" && ids != "undefined"){
			try {
				boolean b = rolePrivilegeService.deleteRolePrivlgsById(ids);
				if(b){
					//批量删除成功的话，两个全局的map也remove掉删除的对象
					String [] idsArray = ids.split(",");
					for(int i = 0; i < idsArray.length; i++ ){
						//getUserMap().remove(idsArray[i]);
						RolePrivilege rolePrivlgRemove  = (RolePrivilege) getRolePlgMap().get(idsArray[i]);//获取要移除的角色权限对象
						getRolePlgMap().remove(idsArray[i]);
						getRolePlgIdsMap().remove(rolePrivlgRemove.getRoleid()+rolePrivlgRemove.getPrivilegeid());
						rolePrivilegeService.removePlgFromUserPlgAllMap(rolePrivlgRemove);
						
					}
				}
				
			} catch (Exception e) {
				log.error("deleteUsersById error 批量删除出错");
			}
		}
		
		return "redirect:/userPrivilege/queryAllUserPrivilege.do";
	}
}
