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
import adtec.privilege.model.Role;
import adtec.privilege.model.Type;
import adtec.privilege.service.PrivilegeService;
import adtec.privilege.service.TypeService;

/**
 * 操作类型管理的controller类。用来接收和处理页面的请求
 * 
 * @author maojd
 * 
 */
@Controller
@RequestMapping("/type")
public class TypeController {

	private TypeService typeService;
	private PrivilegeService privilegeService;
	private Logger log = Logger.getLogger(TypeController.class);
	// 全局的存放 操作类型的map容器
	private Map<String, Object> typeMap = CachFactory.getInstance()
			.getMapByKey("typeMap");

	public TypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

	public Map<String, Object> getTypeMap() {
		if (typeMap == null) {
			typeMap = CachFactory.getInstance().getMapByKey("typeMap");
		}
		return typeMap;
	}

	public void setTypeMap(Map<String, Object> typeMap) {
		this.typeMap = typeMap;
	}
	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	/**
	 * 查询所有操作类型
	 * 
	 * @return 显示页面
	 */
	@RequestMapping(value = "/queryAllType")
	public String queryAllType() {
		// List<Type> list = new ArrayList<Type>();
		/*
		 * try { // list = typeService.queryAllType(); // map.put("typeList",
		 * list); map.put("typeList", getTypeMap()); } catch (Exception e) {
		 * log.error("queryAllType error查询所有操作类型出错"); }
		 */

		return "privilege/type/typeList";
	}

	/**
	 * 查询所有的实现（带分页 和 模糊查询）
	 * @param type
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/queryAllTypeAction")
	public void queryAllTypeAction(Type type, HttpServletRequest request,
			HttpServletResponse response) {

		// 分页查询数据
		List<Type> typeList = new ArrayList<Type>();
		/*Type type = new Type();
		type.setPage(page);*/
		try {
			typeList = typeService.queryAllType(type);// 分页查询
		} catch (Exception e1) {
			log.error("分页查询操作类型出错");
		}

		//查询总数
		int count = 0;
		try {
			count = typeService.queryTypeCount(type);
		} catch (Exception e) {
			log.error("queryTypeCount error查询操作类型数量出错");
		}
		
		
		Map<String, Object> typeEasyUIMap = new HashMap<String, Object>();
		typeEasyUIMap.put("rows", typeList);
		typeEasyUIMap.put("total", count);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(JSONObject.fromObject(typeEasyUIMap).toString());// 输入的是json key
																// Object的字符串
			pw.flush();
		} catch (IOException e) {
			log.error("输出json出错");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * 添加操作类型的跳转
	 * 
	 * @return 添加操作类型的页面
	 */
	@RequestMapping(value = "/insertType")
	public String insertType() {

		return "privilege/type/typeInsert";
	}

	/**
	 * 添加操作类型的实现
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/insertTypeAction", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void insertTypeAction(Type type,HttpServletResponse response) {
//		String uuid = UUID.randomUUID().toString().replace("-", "");
//		type.setTypeid(uuid);
		String infoMsg = "";
		try {
			boolean b = typeService.insertType(type);
			if (b) {
				infoMsg = "addSuc";
				getTypeMap().put(type.getTypeid(), type);
			}
		} catch (Exception e) {
			log.error("insertTypeAction error 添加操作类型错误");
			infoMsg = "addError";
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
		
		//return "redirect:/type/queryAllType.do";
	}

	/**
	 * 修改操作类型的跳转
	 * 
	 * @param type
	 *            要修改的数据（作为查询条件）
	 * @param map
	 *            要要修改的数据，放到map中，传给jsp页面
	 * @return 跳转到修改页面
	 */
	@RequestMapping(value = "/updateType", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String updateType(Type type, Map<String, Object> map) {
		Type typeResult = new Type();
		try {
			// typeResult = typeService.queryTypeById(type.getTypeid());
			typeResult = (Type) getTypeMap().get(type.getTypeid());
			map.put("type", typeResult);
		} catch (Exception e) {
			log.error("updateType error 修改操作类型的跳转出错");
		}

		return "privilege/type/typeUpdate";
	}

	/**
	 * 修改操作类型的实现
	 * 
	 * @param type
	 *            传入修改的实体
	 */
	@RequestMapping(value = "/updateTypeAction", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateTypeAction(Type type,HttpServletResponse response) {
		String infoMsg = "";
		try {
			boolean b = typeService.updateType(type);
			if (b) {
				infoMsg = "updateSuc";
				getTypeMap().remove(type.getTypeid());
				getTypeMap().put(type.getTypeid(), type);
			}
		} catch (Exception e) {
			infoMsg = "updateError";
			log.error("updateTypeAction error 修改操作类型出错");
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
		
		//return "redirect:/type/queryAllType.do";
	}

	/**
	 * 删除一条操作类型
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/deleteType", method = { RequestMethod.GET })
	public String deleteType(Type type) {
		try {
			boolean b = typeService.deleteType(type);
			if (b) {
				getTypeMap().remove(type.getTypeid());
			}
		} catch (Exception e) {
			log.error("deleteType error 删除操作类型出错");
		}
		return "redirect:/type/queryAllType.do";
	}
	
	/**
	 * 批量删除
	 * @param request 从页面获取参数
	 * @return 返回显示页面
	 */
	@RequestMapping(value = "/deleteTypesById",method={RequestMethod.POST,RequestMethod.GET})
	public void deleteTypesById(HttpServletRequest request,HttpServletResponse response){
		String typeids = request.getParameter("typeids");
		String infoMsg = "";
		try {
			//验证是否在使用
			String[] idsArray = typeids.split(",");
			for(int i = 0; i < idsArray.length; i++ ){
				int privlgCount = privilegeService.queryCountByObj(new Privilege(null, null, idsArray[i], true, null, null));
				if(privlgCount>0){//一旦查询出来，有用户在用该权限，则返回
					infoMsg = "isUsed";
					break;
				}
			}
			if(!"isUsed".equals(infoMsg)){
				boolean b = typeService.deleteUsersById(typeids);
				if(b){
					infoMsg = "delSuc";
					for(int i = 0; i < idsArray.length; i++ ){
						getTypeMap().remove(idsArray[i]);
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
		
		//return "redirect:/type/queryAllType.do";
	}
	
	
	/**
	 * 通过对象查询数量
	 * @param role
	 * @param response
	 */
	@RequestMapping(value="/queryCountByObj",method={RequestMethod.POST,RequestMethod.GET})
	public void queryCountByObj(Type type,HttpServletResponse response){
		int infoMsg = 0;
		try {
			List<Role> list = typeService.queryTypeByObj(type);
			if(list.size()>0){//加入只有一条的话，则为本身。
				infoMsg = list.size();
			}
		} catch (Exception e) {
			log.error("queryCountByObj error验证操作类型是否存在出错");
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(infoMsg+"");
			pw.flush();
		} catch (IOException e) {
			log.error("pw.write(jsonData.toString())  error");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
}
