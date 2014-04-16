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
import adtec.privilege.model.Column;
import adtec.privilege.model.Page;
import adtec.privilege.model.Privilege;
import adtec.privilege.model.Resource;
import adtec.privilege.service.ColumnService;
import adtec.privilege.service.PrivilegeService;
import adtec.privilege.service.ResourceService;

/**
 * 资源管理的控制类  接受页面发送的请求
 * @author maojd
 * @date 15:52 2014/2/21
 */

@Controller
@RequestMapping("/resource") 
public class ResourceController {
	
	private Logger log = Logger.getLogger(ResourceController.class);
	private ResourceService resourceService;
	private PrivilegeService privilegeService;
	private ColumnService columnService;
	private Map<String, Object> resMap = CachFactory.getInstance().getMapByKey("resMap");
	
	public ResourceService getResourceService() {
		return resourceService;
	}
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	public Map<String, Object> getResMap() {
		if(resMap == null){
			resMap = CachFactory.getInstance().getMapByKey("resMap");
		}
		return resMap;
	}
	public void setResMap(Map<String, Object> resMap) {
		this.resMap = resMap;
	}
	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}
	public ColumnService getColumnService() {
		return columnService;
	}
	public void setColumnService(ColumnService columnService) {
		this.columnService = columnService;
	}
	/**
	 * 查询所所有资源跳转
	 * @return 返回到数据显示页面
	 */
	@RequestMapping(value = "/queryAllResource")
	public String queryAllResource(Map<String, Object> map){
		/*List<Resource> list = new ArrayList<Resource>();
		try {
			//list = resourceService.queryAllResource();
			//map.put("resList", list);
			map.put("resList", getResMap());
		} catch (Exception e) {
			log.error("queryAllResource  error  查询所有资源出错");
		}*/
		return "privilege/resource/resourceList";
	}
	
	/**
	 * 分页查询资源(带模糊查询)
	 * @param page 分页对象
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/queryAllResourceAction")
	public void queryAllResourceAction(Resource resource,HttpServletRequest request,HttpServletResponse response){
		
		//分页查询数据
		List<Resource> resourceList = new ArrayList<Resource>();
		try {
			resourceList = resourceService.queryAllResource(resource);// 分页查询
		} catch (Exception e1) {
			log.error("分页查询所有资源出错");
		}
		
		int count = 0;
		try {
			count = resourceService.queryResourceCount(resource);
		} catch (Exception e1) {
			log.error("查询资源数量出错");
		}
		
		Map<String, Object> resEasyUIMap = new HashMap<String, Object>();//map中放入rows,total便于esayUI前端展示
		resEasyUIMap.put("rows", resourceList);
		resEasyUIMap.put("total", count);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(JSONObject.fromObject(resEasyUIMap).toString());//输入的是json key Object的字符串
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
	 * 修改资源跳转
	 * @param res 要修改的资源，要resid去做查询
	 * @param map 把查询出来的资源，放入map容器中
	 * @return 返回到修改页面
	 */
	@RequestMapping(value="/updateRes",method={RequestMethod.POST,RequestMethod.GET})
	public String updateRes(Resource res,Map<String, Object> map){
		Resource resResult =  new Resource();
		try {
			//resResult = resourceService.queryResByResid(res.getResid());
			resResult = (Resource) getResMap().get(res.getResid());//直接从存放资源数据的map容器中取出数据，避免频繁操作数据库
			map.put("res", resResult);
		} catch (Exception e) {
			log.error("修改资源跳转时候查询错误   queryResByResid error");
		}
		return "privilege/resource/resourceUpdate";
	}
	
	/**
	 * 资源修改实现
	 * @param res传入修改的资源
	 * @return 返回资源显示页面
	 */
	@RequestMapping(value="/updateResAction",method={RequestMethod.POST,RequestMethod.GET})
	public void updateRoleAction(Resource res,HttpServletResponse response){
		boolean b = true;
		String infoMsg = "";
		try {
			b = resourceService.updateRes(res);
			if(b){
				infoMsg = "updateSuc";
				getResMap().remove(res.getResid());
				getResMap().put(res.getResid(), res);
			}
		} catch (Exception e) {
			b = false;
			infoMsg = "updateError";
			log.error("updateRoleAction error 资源修改出错");
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
		
		//return "redirect:/resource/queryAllResource.do";
	}
	
	/**
	 * 资源添加的跳转
	 * @return 跳转到jsp
	 */
	@RequestMapping(value= "/insertRes", method = {RequestMethod.POST,RequestMethod.GET})
	public String insertRes(){
		
		return "privilege/resource/resourceInsert";
	}
	
	/**
	 * 添加资源的实现
	 * @param resource要添加的资源
	 * @return
	 */
	@RequestMapping(value = "/resInsertAction", method = {RequestMethod.POST,RequestMethod.GET})
	public void resInsertAction(Resource res,HttpServletResponse response){
		//resourceService.addResource(resource);
		String uuid = UUID.randomUUID().toString().replace("-", "");
		res.setResid(uuid);
		String infoMsg = "";
		try {
			boolean b = resourceService.insertRes(res);
			if(b){
				infoMsg = "addSuc";
				getResMap().put(res.getResid(), res);
			}
		} catch (Exception e) {
			infoMsg = "addError";
			log.error("resAddAction error 添加资源错误 ");
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
		//return "redirect:/resource/queryAllResource.do";
	}
	
	/**
	 * 删除资源
	 * @param res要删除的资源
	 * @return 返回到资源的显示页面
	 */
	@RequestMapping(value="/deleteRes",method={RequestMethod.POST,RequestMethod.GET})
	public String deleteRes(Resource res){
		try {
			boolean b = resourceService.deleteRes(res);
			if(b){
				getResMap().remove(res.getResid());
			}
		} catch (Exception e) {
			log.error("deleteRes error 删除资源错误");
		}
		return "redirect:/resource/queryAllResource.do";
	}
	
	/**
	 * 批量删除
	 * @param request 获取页面参数
	 * @return
	 */
	@RequestMapping(value="/deleteRessById",method={RequestMethod.POST,RequestMethod.GET})
	public void deleteRessById(HttpServletRequest request,HttpServletResponse response){
		
		String resids = request.getParameter("resids");
		String infoMsg = "";//记录用户操作是否得成功的信息
		try {
			String[] idsArray = resids.split(",");
			for(int i = 0; i < idsArray.length; i++ ){
				int privlgCount = privilegeService.queryCountByObj(new Privilege(null, idsArray[i], null, true, null, null));
				if(privlgCount>0){//一旦查询出来，有用户在用该权限，则返回
					infoMsg = "isUsed";
					break;
				}
				
				int columnCount = columnService.queryCountByObj(new Column(null, null, null, idsArray[i], null));
				if(columnCount>0){
					infoMsg = "isUsed";
					break;
				}
				
			}
			if(!"isUsed".equals(infoMsg)){//没有被使用
				boolean b = resourceService.deleteRessById(resids);
				if(b){
					infoMsg = "delSuc";
					for(int i = 0; i < idsArray.length; i++ ){
						getResMap().remove(idsArray[i]);
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
		
		//return "redirect:/resource/queryAllResource.do";
	}
	
	/**
	 * 通过对象查询数量
	 * @param role
	 * @param response
	 */
	@RequestMapping(value="/queryCountByObj",method={RequestMethod.POST,RequestMethod.GET})
	public void queryCountByObj(Resource res,HttpServletResponse response){
		int infoMsg = 0;
		try {
			List<Resource> list = resourceService.queryResourceByObj(res);
			if(list.size()>0){//加入只有一条的话，则为本身。
				infoMsg = list.size();
			}
		} catch (Exception e) {
			log.error("queryCountByObj error通过对象查询数量出错");
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
	
	
	/**
	 * 查询所有 资源并输出，不带分页（在添加栏位的时候用）
	 * @param resource
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/queryAllResourceActionNoPage")
	public void queryAllResourceActionNoPage(Resource resource,HttpServletRequest request,HttpServletResponse response){
		
		List<Resource> resourceList = new ArrayList<Resource>();
		try {
			resourceList = resourceService.queryAllResource(null);
		} catch (Exception e1) {
			log.error("分页查询所有资源出错");
		}
		
		int count = 0;
		try {
			count = resourceService.queryResourceCount(null);
		} catch (Exception e1) {
			log.error("查询资源数量出错");
		}
		
		Map<String, Object> resEasyUIMap = new HashMap<String, Object>();//map中放入rows,total便于esayUI前端展示
		resEasyUIMap.put("rows", resourceList);
		resEasyUIMap.put("total", count);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(JSONObject.fromObject(resEasyUIMap).toString());//输入的是json key Object的字符串
			pw.flush();
		} catch (IOException e) {
			log.error("输出json出错");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		
	}
}
