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

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import adtec.init.CachFactory;
import adtec.privilege.model.Page;
import adtec.privilege.model.Privilege;
import adtec.privilege.model.Resource;
import adtec.privilege.model.RolePrivilege;
import adtec.privilege.model.Type;
import adtec.privilege.model.UserPrivilege;
import adtec.privilege.service.PrivilegeService;
import adtec.privilege.service.RolePrivilegeService;
import adtec.privilege.service.UserPrivilegeService;

/**
 * 权限管理controller类
 * 
 * @author maojd
 * @date 14:23 2014/2/24
 */
@Controller
@RequestMapping("/privilege")
public class PrivilegeController {

	private Logger log = Logger.getLogger(PrivilegeController.class);
	private PrivilegeService privilegeService;
	private UserPrivilegeService userPrivilegeService;
	private RolePrivilegeService rolePrivilegeService;
	private Map<String, Object> privilegeMap = CachFactory.getInstance()
			.getMapByKey("privilegeMap");

	private Map<String, Object> privilegeIdsMap = CachFactory.getInstance()
			.getMapByKey("privilegeIdsMap");
	public Map<String, Object> getPrivilegeIdsMap() {
		if (privilegeIdsMap == null) {
			privilegeIdsMap = CachFactory.getInstance()
					.getMapByKey("privilegeIdsMap");
		}
		return privilegeIdsMap;
	}

	public void setPrivilegeIdsMap(Map<String, Object> privilegeIdsMap) {
		this.privilegeIdsMap = privilegeIdsMap;
	}

	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}

	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	public Map<String, Object> getPrivilegeMap() {
		if (privilegeMap == null) {
			privilegeMap = CachFactory.getInstance()
					.getMapByKey("privilegeMap");
		}
		return privilegeMap;
	}
	public void setPrivilegeMap(Map<String, Object> privilegeMap) {
		this.privilegeMap = privilegeMap;
	}
	public UserPrivilegeService getUserPrivilegeService() {
		return userPrivilegeService;
	}

	public void setUserPrivilegeService(UserPrivilegeService userPrivilegeService) {
		this.userPrivilegeService = userPrivilegeService;
	}
	
	public RolePrivilegeService getRolePrivilegeService() {
		return rolePrivilegeService;
	}

	public void setRolePrivilegeService(RolePrivilegeService rolePrivilegeService) {
		this.rolePrivilegeService = rolePrivilegeService;
	}


	/**
	 * 跳转到权限管理的总页面
	 * @param request 设置一下resid
	 * @param res 获取资源id
	 * @return 返回到权限管理的总页面
	 */
	@RequestMapping(value="/privilegeManagerList")
	public String privilegeManagerList(HttpServletRequest request,Resource res){
		/*
		String resid = "";
		if(res!=null && res.getResid() != null && res.getResid() != ""){
			resid = res.getResid().trim();
			request.getSession().setAttribute("resid", resid);
		}//如果传入了resid 资源id就 王session中set一下，覆盖一下以前的。否则不做操作（资源id还是以前的）。
		*/
		
		return "privilege/easyuiMain";
	}
	
	/**
	 * 查询所有权限跳转
	 * @param infoMsg 操作提示成功与否的信息
	 * @return 返回到显示页面
	 */
	@RequestMapping(value = "/queryAllPrivilege")
	public String queryAllPrivilege(Map<String, Object> map,String infoMsg,HttpServletRequest request) {
		// List<Privilege> list = new ArrayList<Privilege>();
		/*try {
			// list = privilegeService.queryAllPrivilege();
			// map.put("privilegeList", list);
			map.put("privilegeList", getPrivilegeMap());

		} catch (Exception e) {
			log.error("queryAllPrivilege error 查询所有权限出错");
		}*/
		if(infoMsg == null){
			infoMsg = "";
		}
		request.setAttribute("infoMsg", infoMsg);
		return "privilege/privilege/privilegeList";
	}

	/**
	 * 查询所有权限 (带分页)
	 * 
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryAllPrivilegeAction")
	public String queryAllPrivilegeAction(Privilege privlg,Resource resource,Type type,
			HttpServletRequest request, HttpServletResponse response) {
		// 分页查询数据
		List<Privilege> privlgList = new ArrayList<Privilege>();
		//排序字段全部转化为小写。避免Linux系统 排序字段报错
		if(privlg != null && privlg.getSort() != null && "" != privlg.getSort().trim()){
			privlg.setSort(privlg.getSort().toLowerCase());
		}
		privlg.setResource(resource);//资源作为查询条件
		privlg.setType(type);//操作类型也可能作为查询条件
		try {
			privlgList = privilegeService.queryAllPrivilege(privlg);
		} catch (Exception e1) {
			log.error("查询所有权限出错");
		}

		// 查询总数量
		int count = 0;
		try {
			count = privilegeService.queryPrivlgCount(privlg);
		} catch (Exception e) {
			log.error("queryPrivlgCount error 查询权限数量出错");
		}

		// 输出到页面
		Map<String, Object> privlgEasyUIMap = new HashMap<String, Object>();//放入rows数据，total便于 easyUI前端展示
		privlgEasyUIMap.put("rows", privlgList);
		privlgEasyUIMap.put("total", count);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(JSONObject.fromObject(privlgEasyUIMap).toString());// 输出的是json 对象
			pw.flush();
		} catch (IOException e) {
			// e.printStackTrace();
			log.error("输出json出错");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

		return "privilege/privilege/privilegeList";
	}

	/**
	 * 添加权限的跳转
	 * 
	 * @param map
	 *            把查询出来的资源方法map容器，传到jsp
	 * @return 添加资源界面
	 */
	@RequestMapping(value = "/insertPrivilege")
	public String insertPrivilege(Map<String, Object> map) {

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

		return "privilege/privilege/privilegeInsert";
	}

	/**
	 * 添加权限的实现方法
	 * 
	 * @param privilege
	 *            jsp获取的 权限
	 * @return 返回权限管理界面
	 */

	@RequestMapping(value = "/insertPrivilegeAction", method = {
			RequestMethod.POST, RequestMethod.GET })
	public String insertPrivilegeAction(HttpServletRequest request,
			boolean actionValue) {

		String resActionIds = request.getParameter("resActionIds"); //获取到一个id ( 资源id+操作类型的id )
		resActionIds = resActionIds.replace(" ", "");//去掉空格
		if(resActionIds != null && resActionIds != "" && resActionIds != "undefined"){
			List<Privilege> privlgList = new ArrayList<Privilege>();
			String arrayIds [] = resActionIds.split(",");
			for(String resActId:arrayIds){
				
				String resid = resActId.substring(0, 32); //前32位是resid 资源id
				String actionType = resActId.substring(32, resActId.length()); //后32位是 操作类型id
		
				Privilege privlg = new Privilege();
				
				privlg.setResid(resid);
				privlg.setActionType(actionType);
				boolean ifExists;
				try {
					ifExists = privilegeService.queryPrivlgIfExists(privlg);
					if(!ifExists){
						String uuid = UUID.randomUUID().toString().replace("-", "");
						privlg.setPrivilegeid(uuid);
						privlg.setActionValue(actionValue);
						privlgList.add(privlg);
					}
				} catch (Exception e) {
					log.error("queryPrivlgIfExists error 验证权限是否存在出错");
				}
				
			}
			
			//循环完以后，批量添加
			try {
				boolean b = privilegeService.batchInsertPrivilege(privlgList);
				
				
				//添加成功以后。把权限的全局 privilegeMap也添加相应数据
				if(b){ 
					for(Privilege priAdd:privlgList){
						//通过 resMap（全局的资源缓存容器）获取res资源对象，set给权限的res属性
						Resource resource = (Resource) CachFactory.getInstance().getMapByKey("resMap").get(priAdd.getResid());
						priAdd.setResource(resource);
						//获取到操作类型 对象，set给权限的操作类型属性
						Type type = (Type) CachFactory.getInstance().getMapByKey("typeMap").get(priAdd.getActionType());
						priAdd.setType(type);
						
						getPrivilegeMap().put(priAdd.getPrivilegeid(), priAdd);
						getPrivilegeIdsMap().put(priAdd.getResid()+priAdd.getActionType(), priAdd);
					}
				}
				
				log.debug("batchInsertPrivilege " + b);
			} catch (Exception e) {
				log.error("batchInsertPrivilege error批量添加权限出错");
			}
		}

		return "redirect:/privilege/queryAllPrivilege.do";
	}

	/**
	 * 权限修改的跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updatePrivilege", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String updatePrivilege(Privilege privilege, Map<String, Object> map) {

		String privilegeId = privilege.getPrivilegeid();
		try {
			// Privilege privilegeResult =
			// privilegeService.queryPrivilegeById(privilegeId);
			Privilege privilegeResult = (Privilege) getPrivilegeMap().get(
					privilegeId);// 从存放 权限的map中取值。避免频繁操作数据库
			map.put("privilege", privilegeResult);
		} catch (Exception e) {
			log.error("queryPrivilegeById error 修改权限时候，通过权限id查询出错");

		}

		return "privilege/privilege/privilegeUpdate";
	}

	/**
	 * 权限修改的实现 主要修改开启，还是关闭权限
	 * 
	 * @param privilege
	 * @return 返回权限展示页面
	 */
	@RequestMapping(value = "/updatePrivilegeAction", method = {
			RequestMethod.POST, RequestMethod.GET })
	public String updatePrivilegeAction(Privilege privilege) {
		try {
			boolean b = privilegeService.updatePrivilege(privilege);
			if (b) {// 修改成功，存放权限的map也修改

				getPrivilegeMap().remove(privilege.getPrivilegeid());
				getPrivilegeMap().put(privilege.getPrivilegeid(), privilege);
				
				getPrivilegeIdsMap().remove(privilege.getResid()+privilege.getActionType());
				getPrivilegeIdsMap().put(privilege.getPrivilegeid(), privilege);
			}
		} catch (Exception e) {
			log.error("updatePrivilegeAction error 修改权限出错");
		}

		return "redirect:/privilege/queryAllPrivilege.do";
	}

	/**
	 * 删除权限记录
	 * 
	 * @param privilege
	 * @return 返回权限查看界面
	 */
	@RequestMapping(value = "/deletePrivilege")
	public String deletePrivilege(Privilege privilege) {
		try {
			boolean b = privilegeService.deletePrivilege(privilege);
			if (b) {
				getPrivilegeMap().remove(privilege.getPrivilegeid());//一个map以主键为key
				getPrivilegeIdsMap().remove(privilege.getResid()+privilege.getActionType()); //一个idsMap以 资源id+操作类型id 作为key
			}
		} catch (Exception e) {
			log.error("deletePrivilege error 删除权限记录出错");
		}
		return "redirect:/privilege/queryAllPrivilege.do";
	}
	
	/**
	 * 批量删除
	 * @param request 获取要删除的 权限id(每个id之间通过','分隔)
	 * 
	 */
	@RequestMapping(value="/deletePrivilegesById",method={RequestMethod.POST,RequestMethod.GET})
	public void deletePrivilegesById(HttpServletRequest request,HttpServletResponse response){
		
		String privilegeids = request.getParameter("privilegeids");
		privilegeids = privilegeids.replace(" ", "");//去掉空格
		String infoMsg = "";//返回的信息
		try {
			//验证是否在使用
			String[] privilegeidsArray = privilegeids.split(",");
			for(int i = 0; i < privilegeidsArray.length; i++ ){
				int userPrivlgCount = userPrivilegeService.queryCountByObj(new UserPrivilege(null, null, privilegeidsArray[i], null, null));
				if(userPrivlgCount>0){//一旦查询出来，有用户在用该权限，则返回
					infoMsg = "isUsed";
					break;
				}
				
				int rolePrivlgCount = rolePrivilegeService.queryCountByObj(new RolePrivilege(null, null, privilegeidsArray[i], null, null));
				if(rolePrivlgCount>0){
					infoMsg = "isUsed";
					break;
				}
			}
			
			if(!"isUsed".equals(infoMsg)){//没有被使用
				boolean b = privilegeService.deletePrivilegesById(privilegeids);
				if(b){
					infoMsg = "delSuc";
					for(int i = 0; i < privilegeidsArray.length; i++ ){
						//两个map都remove掉 权限对象
						Privilege privilegeRemove = (Privilege) getPrivilegeMap().get(privilegeidsArray[i]);
						getPrivilegeMap().remove(privilegeidsArray[i]);	
						getPrivilegeIdsMap().remove(privilegeRemove.getResid()+privilegeRemove.getActionType());
					}
				}else{
					infoMsg = "delError";
				}
			}
			
			
			
		} catch (Exception e) {
			infoMsg = "delError";
			log.error("deleteUsersById error 批量删除出错");
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(infoMsg);
			pw.flush();
		} catch (IOException e) {
			log.error("pw.write(jsonData.toString())  error");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		
		//return "redirect:/privilege/queryAllPrivilege.do?infoMsg="+infoMsg;
	}
}
