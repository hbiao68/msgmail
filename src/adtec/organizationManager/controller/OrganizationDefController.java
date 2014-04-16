package adtec.organizationManager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import adtec.organizationManager.entity.OrganizationDef;
import adtec.organizationManager.service.OrganizationDefService;
import adtec.privilege.model.Resource;
import adtec.privilege.service.ResourceService;
/**
 * 机构等级Controller类
 * @author maojd
 *
 */
@Controller
@RequestMapping(value="/organizationDefManager")
public class OrganizationDefController {

	Logger log = Logger.getLogger(OrganizationDefController.class);
	/*
	 * 加上注解	spring可以自动生成get set方法，get，set可以省略。
	 * 不加注解，就一定要有get，set方法
	 */
	/**
	 * 机构等级service
	 */
	@Autowired				
	private OrganizationDefService orgDefService;
	private ResourceService resourceService; 
	
	public OrganizationDefService getOrgDefService() {
		return orgDefService;
	}


	public void setOrgDefService(OrganizationDefService orgDefService) {
		this.orgDefService = orgDefService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}


	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}


	/**
	 * 查询所有
	 * @param map 把机构等级List集合放到map
	 * @return "organizationManager/organizationDef/orgDefList"
	 */
	@RequestMapping(value="/queryAllOrgDef")
	public String queryAllOrgDef(Map<String, Object> map,HttpServletRequest request,Resource res) {
		String resid = "";
		if(res!=null && res.getResid() != null && res.getResid() != ""){
			resid = res.getResid().trim();
			request.getSession().setAttribute("resid", resid);
		}//如果传入了resid 资源id就 王session中set一下，覆盖一下以前的。否则不做操作（资源id还是以前的）。
		
		
		
		List<OrganizationDef> list = new ArrayList<OrganizationDef>(); 
			try {
				list = orgDefService.queryAllOrgDef();
			} catch (Exception e) {
				log.error("orgDefService.queryAllOrgDef()  error");
			}
		
		List<Integer> idList = new ArrayList<Integer>();
		for(int i=0;i<list.size();i++){
			int j = list.get(i).getLevel_id();
			idList.add(j);
		}
		int maxId =0;
		if(idList.size() != 0){
			maxId = Collections.max(idList);
		}
		
		map.put("maxId", maxId);//取出最大id。jsp以便判断是否最大，允许删除
		
		String sucMsg = "";
		sucMsg =  request.getParameter("sucMsg");
		//System.out.println(sucMsg);
		request.setAttribute("sucMsg", sucMsg);
		
		map.put("organizationDefs", list);
		log.debug("queryAllOrgDef");
		return "organizationManager/organizationDef/orgDefList";
	}

	

	/**
	 * 添加机构等级
	 * @param org 需要添加的机构等级 实体
	 * @return "redirect:/organizationDefManager/queryAllOrgDef.do"
	 */
	//添加等级 信息
	@RequestMapping(value="/addOrgDef",method={RequestMethod.POST})
	public String addOrgDef(OrganizationDef org) {
		
		/*if(org.getName() == ""){
			JOptionPane.showMessageDialog(null, "名称不能为空");
		}else{

			orgDefService.addOrgDef(org);
			JOptionPane.showMessageDialog(null, "添加成功");
		}*/
		String sucMsg = "";
		org.setName(org.getName().trim());
		try {
			boolean b = orgDefService.addOrgDef(org);
			if(b){
				sucMsg = "addOrgDefSuc";
			}
		} catch (Exception e) {
			sucMsg = "addOrgDefError";
			log.error("orgDefService.addOrgDef"+org+"  error");
			
		}
	
		
		log.debug("addOrgDef"+org);
		return "redirect:/organizationDefManager/queryAllOrgDef.do?sucMsg="+sucMsg;
	}
	
	/**
	 * 根据id删除等级信息
	 * @param level_id 机构等级id
	 * @param request 获取机构id
	 * @return
	 */
	@RequestMapping(value="/deleteOrgDef")
	public void deleteOrgDef(int level_id,HttpServletRequest request,HttpServletResponse response){
		level_id = new Integer(request.getParameter("level_id"));
		String sucMsg = "";	//记录用户操作成功是否得信息
		try {
			boolean b = orgDefService.deleteOrgDefById(level_id);//ture表示添加成功。捕获到异常表示添加失败。没有true
			if(b){
				sucMsg = "deleteOrgDefSuc";
			}			
		} catch (Exception e) {
			log.error("orgDefService.deleteOrgDefById" + level_id + "error");
			sucMsg = "deleteOrgDefError";
		}
		log.debug("deleteOrgDef"+level_id);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			// pw.print(jsonData);
			pw.write(sucMsg);
			pw.flush();
		} catch (IOException e) {
			log.error("pw.write(jsonData.toString())  error");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		
		//return "redirect:/organizationDefManager/queryAllOrgDef.do?sucMsg="+sucMsg;//改成ajax的方式删除
		
		
	}

	/**
	 * 修改 等级信息
	 * @param orgDef 机构等级实体
	 * @return
	 */
	
	@RequestMapping(value="/updateOrgDef",method={RequestMethod.POST})
	public String updateOrgDef(OrganizationDef orgDef,HttpServletRequest request){
		log.debug("updateOrgDef"+orgDef);
		String sucMsg = "";
		
		orgDef.setName(orgDef.getName().trim());
		try {
			orgDefService.updateOrgDef(orgDef);
			sucMsg = "updateOrgDefSuc";
		} catch (Exception e) {
			log.error("orgDefService.updateOrgDef"+orgDef+"  error");
			sucMsg = "updateOrgDefError";
		}
		
		
		return "redirect:/organizationDefManager/queryAllOrgDef.do?sucMsg="+sucMsg;
		//return "redirect:/organizationDefManager/queryAllOrgDef.do";
	}
	
	/**
	 * 机构等级 跳转
	 * @param request	获取需要修改的机构登记 level_id
	 * @param map 		需要修改的机构 放入map
	 * @return     "organizationManager/organizationDef/orgDefUpdate"
	 */
	//
	@RequestMapping(value="/updateOrgDefPage")
	public String updateOrgDefPage(HttpServletRequest request,Map<String, Object> map){
		int level_id = new Integer(request.getParameter("level_id"));//从url获取level_id
		
		OrganizationDef orgDef = new OrganizationDef();
		try {
			orgDef = orgDefService.queryOrgDefById(level_id);
		} catch (Exception e) {
			log.error("orgDefService.queryOrgDefById " + level_id + "  error");
		}
		map.put("orgDef", orgDef);
		
		/*JSONArray orgDefJson = JSONArray.fromObject(orgDef);
		String str = orgDefJson.toString();
		request.setAttribute("orgDefJson", str);
		*/
		//System.out.println(str);
		log.debug("updateOrgDef"+orgDef);
		return "organizationManager/organizationDef/orgDefUpdate";
	}
	
	/**
	 * 验证机构分级设置的名称是否重复
	 * @param request
	 * @param response
	 * @return void
	 */
	@RequestMapping(value = "/isRepeatOrgDefName")
	public void isRepeatOrgDefName(HttpServletRequest request,HttpServletResponse response){
		String orgDefName = request.getParameter("orgDefName");
		orgDefName = orgDefName.trim();
		if(orgDefName == null){
			orgDefName = "";
		}
		String isRepeatOrgDefNameMsg = "false";//false表示不重复 
		
		List<OrganizationDef> list = new ArrayList<OrganizationDef>();
		
		try {
			list = orgDefService.queryAllOrgDef();
		} catch (Exception e1) {
			//e1.printStackTrace();
			log.error("验证机构分级设置 名称是否重复时  查询所有出错");
		}
		
		
		for(int i = 0;i < list.size();i++){
			OrganizationDef orgDef = list.get(i);
			if(orgDefName.equals(orgDef.getName())){
				isRepeatOrgDefNameMsg = "true";//true表示机构分级设置 名称重复
				break;
			}
		}
		log.debug("isRepeatOrgDefName 验证是否重复的的值是" + isRepeatOrgDefNameMsg);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			// pw.print(jsonData);
			pw.write(isRepeatOrgDefNameMsg);
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
