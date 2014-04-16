package adtec.privilege.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
import adtec.privilege.model.Type;
import adtec.privilege.model.User;
import adtec.privilege.model.UserPrivilege;
import adtec.privilege.service.PrivilegeService;
import adtec.privilege.service.UserPrivilegeService;

/**
 * 用户、权限  管理的controller,用来直接接受页面请求
 * @author maojd
 * @date 14:49 2014/2/26
 */

@Controller
@RequestMapping(value="/userPrivilege")
public class UserPrivilegeController {

	private UserPrivilegeService userPrivilegeService;
	private PrivilegeService privilegeService;
	private Map<String, Object> userPrivilegeMap = CachFactory.getInstance().getMapByKey("userPrivilegeMap");//通过用户权限id主键存放的map
	private Map<String, Object> userPrivlgIdsMap = CachFactory.getInstance().getMapByKey("userPrivlgIdsMap");//用户id + 权限id 存放的map
	private Map<String, Object> userPrivlgIdsAllMap = CachFactory.getInstance().getMapByKey("userPrivlgIdsAllMap");//用户所有权限（包括通过角色获取到的权限）       用户id + 权限id 存放的map
	
	private Logger log = Logger.getLogger(UserPrivilegeController.class);
	public UserPrivilegeService getUserPrivilegeService() {
		return userPrivilegeService;
	}
	public void setUserPrivilegeService(UserPrivilegeService userPrivilegeService) {
		this.userPrivilegeService = userPrivilegeService;
	}
	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}
	public Map<String, Object> getUserPrivilegeMap() {
		if(userPrivilegeMap == null){
			userPrivilegeMap = CachFactory.getInstance().getMapByKey("userPrivilegeMap");
		}
		return userPrivilegeMap;
	}

	public void setUserPrivilegeMap(Map<String, Object> userPrivilegeMap) {
		this.userPrivilegeMap = userPrivilegeMap;
	}
	public Map<String, Object> getUserPrivlgIdsMap() {
		if(userPrivlgIdsMap == null){
			userPrivlgIdsMap = CachFactory.getInstance().getMapByKey("userPrivlgIdsMap");
		}
		return userPrivlgIdsMap;
	}
	public void setUserPrivlgIdsMap(Map<String, Object> userPrivlgIdsMap) {
		this.userPrivlgIdsMap = userPrivlgIdsMap;
	}
	
	public Map<String, Object> getUserPrivlgIdsAllMap() {
		if(userPrivlgIdsAllMap == null){
			userPrivlgIdsAllMap = CachFactory.getInstance().getMapByKey("userPrivlgIdsAllMap");
		}
		return userPrivlgIdsAllMap;
	}
	public void setUserPrivlgIdsAllMap(Map<String, Object> userPrivlgIdsAllMap) {
		this.userPrivlgIdsAllMap = userPrivlgIdsAllMap;
	}
	/**
	 * 查询所有 用户、权限 跳转
	 * @return 返回到用户权限关系查看页面
	 */
	@RequestMapping(value="/queryAllUserPrivilege")
	public String queryAllUserPrivilege(Map<String, Object> map){
		
//		List<UserPrivilege> list = new ArrayList<UserPrivilege>();
		/*try {
//			list = userPrivilegeService.queryAllUserPrivilege();
//			map.put("userPrivilegeList", list);
			map.put("userPrivilegeList", getUserPrivilegeMap());
		} catch (Exception e) {
			log.error("queryAllUserPrivilege error 查询所有用户、权限 出错");
		}*/
		List<UserPrivilege> userRolePrivlgList = new ArrayList<UserPrivilege>();
		try {
			userRolePrivlgList = userPrivilegeService.queryAllUserRolePrivilege();//查询用户所有权限（用户--> 角色 --> 权限）
			map.put("userPrivilegeList", userRolePrivlgList);
		} catch (Exception e) {
			log.error("queryAllUserRolePrivilege error 查询所有用户角色权限出错");
		}
		return "privilege/userPrivilege/userPrivilegeList";
	}
	
	
	/**
	 * 查询所有 用户、权限（权限关联的资源信息也查询了出来）
	 * @param page 分页对象
	 * @param request 获取参数
	 * @param response 输入信息
	 */
	@RequestMapping(value="/queryAllUserPrivilegeAction",method={RequestMethod.POST,RequestMethod.GET})
	public void queryAllUserPrivilegeAction(UserPrivilege userPrivilege,User user,HttpServletRequest request,HttpServletResponse response){
		
//		UserPrivilege userPrivilege = new UserPrivilege();
//		userPrivilege.setPage(page);
		userPrivilege.setUser(user);
		
		List<UserPrivilege> userPrivilegeList  = new ArrayList<UserPrivilege>();
		//分页查询
		try {
			userPrivilegeList = userPrivilegeService.queryAllUserPrivilege(userPrivilege);
		} catch (Exception e) {
			log.error("queryAllUserPrivilege error 查询所有用户权限对象出错");
		}
		
		int count = 0;
		try {
			count = userPrivilegeService.queryUserPrivCount(userPrivilege);
		} catch (Exception e) {
			log.error("queryUserPrivCount error 查询用户权限数量出错");
		}
		
		Map<String, Object> userPrivlgEasyUIMap = new HashMap<String, Object>();
		userPrivlgEasyUIMap.put("rows", userPrivilegeList);
		userPrivlgEasyUIMap.put("total", count);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(JSONObject.fromObject(userPrivlgEasyUIMap).toString());//输入的是json对象
			pw.flush();
		} catch (IOException e) {
			//e.printStackTrace();
			log.error("输出json出错");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		
		
	}
	
	/**
	 * 获取用户所有的权限（包括通过角色获取的权限）
	 * @param page 分页对象
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/queryAllUserRolePrivlgAction",method={RequestMethod.POST})
	public void queryAllUserRolePrivlgAction(Page page,HttpServletRequest request,HttpServletResponse response){
		
		//获取所有的用户权限
		Map<String, Object> userPrivlgIdsAllMap = CachFactory.getInstance().getMapByKey("userPrivlgIdsAllMap");
		//遍历的方法获取 把map变成list
		/*List<UserPrivilege> list = new ArrayList<UserPrivilege>();
		
		Collection<Object> c = userPrivlgIdsAllMap.values();
		Iterator<Object> it = c.iterator();
		for(;it.hasNext();){
			UserPrivilege userPrivlg = (UserPrivilege) it.next();
			list.add(userPrivlg);
		}*/
		List<Map.Entry> list = new ArrayList<Map.Entry>();
		Set<Entry<String, Object>> set = userPrivlgIdsAllMap.entrySet();
		//System.out.println(set);//set  key=value  key=value(Object)
		list.addAll(set); 
		
		List<UserPrivilege> listOnePage = new ArrayList<UserPrivilege>();
		if(page!=null){
			int start = page.getStart();
			int end = page.getStart()+page.getRows();//开始条数  + pageSize (rows属性就是pageSize)
			if(end > list.size()){
				end = list.size();
			}
			for(int i = start;i<end;i++){
				listOnePage.add((UserPrivilege) list.get(i).getValue());//添加到listOnePage 一页数据
				//Map.Entry e = list.get(i);    userPri = e.getValue()
			}
		}
		
		
		//输出到页面
		Map<String, Object> userRolePrivlgEasyUIMap = new HashMap<String, Object>();
		userRolePrivlgEasyUIMap.put("rows", listOnePage);
		userRolePrivlgEasyUIMap.put("total", list.size());
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(JSONObject.fromObject(userRolePrivlgEasyUIMap).toString());//输入的是json key Object的字符串
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
	 * 删除一条用户、权限记录
	 * @param userPrivilege 要删除的实体
	 * @return 返回查看页面
	 */
	@RequestMapping(value="/deleteUserPrivilege")
	public String deleteUserPrivilege(UserPrivilege userPrivilege){
		
		try {
			boolean b = userPrivilegeService.deleteUserPrivilege(userPrivilege);
			if(b){//相应的两个总容器也删除
				getUserPrivilegeMap().remove(userPrivilege.getUserPlgId());
				getUserPrivlgIdsMap().remove(userPrivilege.getUserid()+userPrivilege.getPrivilegeid());
				getUserPrivlgIdsAllMap().remove(userPrivilege.getUserid()+userPrivilege.getPrivilegeid());
			}
		} catch (Exception e) {
			log.error("deleteUserPrivilege error 删除用户、权限关系出错");
		}
		return "redirect:/userPrivilege/queryAllUserPrivilege.do";
	}
	
	
	/**
	 * 批量删除 用户权限数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteUserPrivlgsById",method={RequestMethod.POST,RequestMethod.GET})
	public String deleteUserPrivlgsById(HttpServletRequest request){
		String ids = request.getParameter("ids");
		if(ids != null && ids != "" && ids != "undefined"){
			try {
				boolean b = userPrivilegeService.deleteUserPrivlgsById(ids);
				if(b){
					//批量删除成功的话，两个全局的map也remove掉删除的对象
					String [] idsArray = ids.split(",");
					for(int i = 0; i < idsArray.length; i++ ){
						//getUserMap().remove(idsArray[i]);
						UserPrivilege userPrivlgRemove = (UserPrivilege) getUserPrivilegeMap().get(idsArray[i]);
						getUserPrivilegeMap().remove(idsArray[i]);
						getUserPrivlgIdsMap().remove(userPrivlgRemove.getUserid()+userPrivlgRemove.getPrivilegeid());
						getUserPrivlgIdsAllMap().remove(userPrivlgRemove.getUserid()+userPrivlgRemove.getPrivilegeid());
					}
				}
				
			} catch (Exception e) {
				log.error("deleteUsersById error 批量删除出错");
			}
		}
		
		return "redirect:/userPrivilege/queryAllUserPrivilege.do";
	}
	
	/**
	 * 插入用户权限页面跳转
	 * @param map 把资源数据放入map中，在添加权限界面用
	 * @return 
	 */
	@RequestMapping(value="/insertUserPrivilege")
	public String insertUserPrivilege(Map<String, Object> map){
		
		try {
			// List<Resource> list = privilegeService.queryAllResources();
			// map.put("resList", list);
			Map<String, Object> resMap = CachFactory.getInstance().getMapByKey(
					"resMap");
			Map<String, Object> typeMap = CachFactory.getInstance()
					.getMapByKey("typeMap");
			map.put("resList", resMap);
			map.put("typeList", typeMap);
		} catch (Exception e) {
			log.error("insertPrivilege error 添加权限时候查询资源出错");
		}
		
		return "privilege/userPrivilege/userPrivilegeInsert";
	}
	
	/**
	 * 添加用户 权限的实现方法
	 * @param userPrivilege
	 * @return
	 */
	@RequestMapping(value="/insertUserPrivilegeAction",method={RequestMethod.POST})
	public String insertUserPrivilegeAction(
			HttpServletRequest request,boolean actionValue){
		//批量添加权限，批量添加用户、权限
		
		//1. 用户List
		String userStr = request.getParameter("userRows");
		if(userStr == null || userStr == "undefined"){
			userStr = "";
		}
		JSONArray userJsonArray = JSONArray.fromObject(userStr);
		List<User> userList = JSONArray.toList(userJsonArray, User.class);
		
		
		//2.批量处理权限数据privlgAddUserList
		//权限list。添加用户权限的时候，验证权限是否存在，存在的话添加到 privlgAddUserList。
		//不存在的话，记录，批量添加。之后 也添加到   privlgAddUserList
		List<Privilege> privlgAddUserList = new ArrayList<Privilege>();//要给用户添加的权限list
		
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
						
						privlgAddUserList.add(privlg);//记录下来，等给用户开通权限
					}else{
						//否则，记录下来。
						/*
						Resource resource = (Resource) CachFactory.getInstance().getMapByKey("resMap").get(privlg.getResid());
						privlg.setResource(resource);
						Type type = (Type) CachFactory.getInstance().getMapByKey("typeMap").get(privlg.getActionType());
						privlg.setType(type);
						*/
						privlg = ifExistsPrivlg;//存在的这个权限
						privlgAddUserList.add(privlg);
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
		
		
		
		//3. 权限处理完成以后，批量添加用户、权限
		if(userList != null && privlgAddUserList != null){
			List<UserPrivilege> userPrivlgList = new ArrayList<UserPrivilege>();
			for(User user:userList){
				for(Privilege privlg:privlgAddUserList){
					UserPrivilege userPrivlg = new UserPrivilege();
					userPrivlg.setUserid(user.getUserid());//设置用户id
					userPrivlg.setPrivilegeid(privlg.getPrivilegeid());//设置权限id 
					try {
						//通过 userid 和 privlgid综合验证 是否存在。
						//boolean ifExists = userPrivilegeService.queryUserPrivlgIfExists(userPrivlg);
						UserPrivilege ifExistsUserPrivlg = (UserPrivilege) CachFactory.getInstance().getMapByKey("userPrivlgIdsMap").get(userPrivlg.getUserid()+userPrivlg.getPrivilegeid());
						
						if(ifExistsUserPrivlg == null){ //如果不存在.set一个主键，添加
							String uuid = UUID.randomUUID().toString().replace("-", "");
							userPrivlg.setUserPlgId(uuid);
							
							//添加时候也把user 和 privlg属性给  用户、权限对象。 便于批量添加成功后，往 userRoleMap中放
							userPrivlg.setUser(user);
							userPrivlg.setPrivilege(privlg);
							userPrivlgList.add(userPrivlg); //把符合条件的 用户、权限 添加到list,批量添加
						}
					} catch (Exception e) {
						log.error("queryUserRoleIfExists error 验证用户角色是否存在出错");
					}
					
				}
				
			}
			
			//遍历完成以后，把符合条件的（避免重复赋予某个角色）批量添加
			try {
				boolean b = userPrivilegeService.batchInsertUserPrivlgs(userPrivlgList);
				if(b){
					//添加成功以后，往userPrivilegeMap中放入数据
					for(UserPrivilege userPrivlg:userPrivlgList){
						getUserPrivilegeMap().put(userPrivlg.getUserPlgId(), userPrivlg);
						getUserPrivlgIdsMap().put(userPrivlg.getUserid()+userPrivlg.getPrivilegeid(), userPrivlg);
						getUserPrivlgIdsAllMap().put(userPrivlg.getUserid()+userPrivlg.getPrivilegeid(), userPrivlg);
					}
				}
			} catch (Exception e) {
				log.error("batchInsertUserRoles error 批量添加用户权限出错");
			}
			
		}
		
		/*String uuid = UUID.randomUUID().toString().replace("-", "");
		userPrivilege.setUserPlgId(uuid);
		try {
			//改成批量
			boolean b = userPrivilegeService.insertUserPrivilege(userPrivilege);
			
			
			if(b){//相应的总容器中也变化
				User user = (User) CachFactory.getInstance().getMapByKey("userMap").get(userPrivilege.getUserid());
				userPrivilege.setUser(user);
				
				Privilege privilege = (Privilege) CachFactory.getInstance().getMapByKey("privilegeMap").get(userPrivilege.getPrivilegeid());
				userPrivilege.setPrivilege(privilege);
				
				getUserPrivilegeMap().put(userPrivilege.getUserPlgId(), userPrivilege);
			}
		} catch (Exception e) {
			log.error("insertUserPrivilegeAction error 插入用户权限出错");
		}*/
		
		return "redirect:/userPrivilege/queryAllUserPrivilege.do";
	}
}
