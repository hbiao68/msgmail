package adtec.organizationManager.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import adtec.init.ProjectProperty;
import adtec.organizationManager.controller.fileupload.FileEncodeReferee;
import adtec.organizationManager.entity.Organization;
import adtec.organizationManager.service.OrganizationService;
import adtec.privilege.model.Resource;
import adtec.privilege.service.ResourceService;


/**
 * OrganizationController 类对机构进行查询、增加、和删除等操作（相当于servlet）
 * @author maojd
 * @RequestMapping /organizationManager
 */
@Controller
@RequestMapping(value = "/organizationManager")
public class OrganizationController {

	
	Logger log = Logger.getLogger(OrganizationController.class);
	// @Autowired
	private ResourceService resourceService; 
	private OrganizationService orgService;
	/**
	 * 文件上传存放路径
	 */
	String path;
	/**
	 * 文件名
	 */
	String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public OrganizationService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrganizationService orgService) {
		this.orgService = orgService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * 由一条机构查询子的直接子机构
	 * @param map     
	 * @param request  
	 * @param response  
	 *
	 */

	// 树状结构的 请求.return json。主要在这里 判断 页面传过来的 是哪个等级
	@RequestMapping(value = "/queryOrgLevel")
	public void queryOrgLevelByOrg(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) {
		// @ResponseBody
		
		
		Organization org = new Organization();

		String id = "";
		id = request.getParameter("id"); // 从页面获取，点击 树，点击的时是哪个数据（id）
		log.debug("queryOrgLevel " + "id is " + id + ",length is " + id.length());
		int orgId = 0;
		/*
		 * if(id.length()>=15 || id == null || id =="" ){ id = "0";
		 * //说明点击的时全国这个id不是数据库的，而是 tree里面获取的。 }
		 */
		try {
			if (id.indexOf("_") != -1) {
				int index = id.indexOf("_");// 点击全国的时候id是 0_xxxxxx共15位，所以截取
				id = id.substring(0, index);
				orgId = new Integer(id);
				/*
				 * org.setOrgId(orgId); org.setOrgName("全国"); //是全国，直接queryOrgLevel
				 */
				
				org = orgService.queryOrgById(orgId);
				
			} else {
				orgId = new Integer(id);// 不是全国，queryById之后，再查询list
				org = orgService.queryOrgById(orgId);
				
			}
		}  catch (Exception e) {
			log.error("orgService.queryOrgById "+ orgId +" error 获取一条机构出错");
		}
		
		if(org == null){
			org = new Organization();
		}
		List<Organization> listLevel = new ArrayList<Organization>(); 
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			listLevel = orgService.queryOrgLevelByOrg(org);
			JSONArray jsonData = JSONArray.fromObject(listLevel);
			pw = response.getWriter();
			// pw.print(jsonData);
			pw.write(jsonData.toString());
			pw.flush();
			
		} catch (IOException e) {
			log.error("查询下一级机构 PrintWriter时出错");
		} catch (Exception e1) {
			log.error("查询下一级机构出错");
			
		}finally {
			if (pw != null) {
				pw.close();
			}
		}

		// System.out.println(jsonData.toString());

	}

	// 跳转到添加页面
	/*
	 * @RequestMapping(value="/addOrgPage") public String
	 * addOrgPage(HttpServletRequest request,Map<String, Object> map){
	 * log.debug("addOrgPage"); String id = request.getParameter("orgId"); String
	 * orgName = request.getParameter("orgName");
	 * 
	 * if(id.indexOf("_") != -1){ int index = id.indexOf("_");//点击全国的时候id是
	 * 0_xxxxxx共15位，所以截取 id = id.substring(0, index); } int orgId = new
	 * Integer(id); Organization org =
	 * orgService.queryOrgById(orgId);//由这个判断往哪个等级下添加 List listLevel =
	 * orgService.queryOrgLevel(org);//由org查询到一个list,便利list，最后一个org的下一个level
	 * 
	 * 
	 * int mark = 0; if(org.getLevel1() == null){ mark = 0; }
	 * 
	 * //这里判断有几个等级level。组装好下一个可以选择的level（排除掉）。 List listLevel =
	 * orgService.queryOrgLevel(org); for(int i=0;i<listLevel.size();i++){
	 * Organization orgLevel = (Organization) listLevel.get(i);
	 * if(orgLevel.getLevel1() == null){
	 * 
	 * } }
	 * 
	 * map.put("listLevel", listLevel); map.put("listLevel", listLevel);
	 * map.put("org", org);
	 * System.out.println("============================listLevel"
	 * +listLevel.size()+"===org"+org.getOrgName()); return
	 * "organizationManager/organization/orgAdd";
	 * 
	 * }
	 */

	/**
	 * 机构添加
	 * @param request 请求 获取机构名称，机构id
	 * @return redirect:/organizationManager/orgList.do
	 */
	// 机构的添加
	@RequestMapping(value = "/addOrg")
	public void addOrg(HttpServletRequest request,HttpServletResponse response) {
		log.debug("addOrg");
		Organization org = new Organization();// 要添加的org

		String orgName = request.getParameter("orgName");
		orgName = orgName.trim();
		String id = request.getParameter("orgId");
		if (id.indexOf("_") != -1) {
			int index = id.indexOf("_");// 点击全国的时候id是 0_xxxxxx共15位，所以截取
			id = id.substring(0, index);
		}
		int orgId = new Integer(id);
		Organization orgNew = new Organization();
		try {
			orgNew = orgService.queryOrgById(orgId);
		} catch (Exception e1) {
			log.error("");
		}

		org.setOrgName(orgName); // 设置setOrgName
									// next setOrgLevel X

		List<Organization> listLevel = new ArrayList<Organization>(); 
		try {
			listLevel = orgService.queryOrgLevelByOrg(orgNew);//查询子结构
			log.debug("orgService.queryOrgLevelByOrg " + orgNew + "  ok");
		} catch (Exception e) {
			log.error("通过机构查询下一级机构出错");
		} 
		
		
		Organization orgLast = new Organization();
		if (listLevel.size() > 0) {
			orgLast = (Organization) listLevel.get(listLevel.size() - 1); // 说明有子机构。取最后一个即可
		} else { // 没有子机构，就给orgNew(刚才拿过来的org)set下一个level 为00
			if (orgNew.getLevel1() == null) { // 全国没有字机构，orgNew(最后要赋值给orgLast)setLevel1
												// "0"
												// 下面会自动 +1，拼接 0
				orgNew.setLevel1("0");
			} else if (orgNew.getLevel1() != null && orgNew.getLevel2() == null) {// 某个，第一（省）级，下没子机构。
				orgNew.setLevel2("0");
			} else if (orgNew.getLevel1() != null && orgNew.getLevel2() != null
					&& orgNew.getLevel3() == null) {// 第二（市）级，下没子机构。
				orgNew.setLevel3("0");
			} else if (orgNew.getLevel1() != null && orgNew.getLevel2() != null
					&& orgNew.getLevel3() != null && orgNew.getLevel4() == null) {// 第3级，下没子机构。
				orgNew.setLevel4("0");
			} else if (orgNew.getLevel1() != null && orgNew.getLevel2() != null
					&& orgNew.getLevel3() != null && orgNew.getLevel4() != null
					&& orgNew.getLevel5() == null) {// 第4级，下没子机构。
				orgNew.setLevel5("0");
			} else if (orgNew.getLevel1() != null && orgNew.getLevel2() != null
					&& orgNew.getLevel3() != null && orgNew.getLevel4() != null
					&& orgNew.getLevel5() != null && orgNew.getLevel6() == null) {// 第5级，下没子机构。
				orgNew.setLevel6("0");
			} else if (orgNew.getLevel1() != null && orgNew.getLevel2() != null
					&& orgNew.getLevel3() != null && orgNew.getLevel4() != null
					&& orgNew.getLevel5() != null && orgNew.getLevel6() != null
					&& orgNew.getLevel7() == null) {// 第6级，下没子机构。
				orgNew.setLevel7("0");
			} else if (orgNew.getLevel1() != null && orgNew.getLevel2() != null
					&& orgNew.getLevel3() != null && orgNew.getLevel4() != null
					&& orgNew.getLevel5() != null && orgNew.getLevel6() != null
					&& orgNew.getLevel7() != null) {// 第7级，下没子机构。
				orgNew.setLevel7(null);
				try {
					listLevel = orgService.queryOrgLevelByOrg(orgNew);// 第七级。把level7置为null,再query.所以query之后，肯定有level7
				} catch (Exception e) {
					log.error("通过机构查询下一级机构出错");
				}
				orgNew = (Organization) listLevel.get(listLevel.size() - 1);
			}
			orgLast = orgNew;// 假如是最后一个等级（即该机构下没有子机构）
			
		}

		// 取最后一个元素，设置新元素。前面的level都一样，最后一个level number+1
		String level1 = orgLast.getLevel1();
		String level2 = orgLast.getLevel2();
		String level3 = orgLast.getLevel3();
		String level4 = orgLast.getLevel4();
		String level5 = orgLast.getLevel5();
		String level6 = orgLast.getLevel6();
		String level7 = orgLast.getLevel7();

		// orgLast最低应该是省行。也就是 level1不可能为空
		if (level1 != null && level2 == null) {
			int level = new Integer(orgLast.getLevel1());
			level = level + 1; // level 加 1
			String levelString = Integer.toString(level);
			if (level < 10) {
				levelString = "0" + level; // 拼接一个 0
				org.setLevel1(levelString);
			}
			org.setLevel1(levelString); // 否则，+1 后 直接set

		}
		// level2 第二等级（市行等级）
		else if (level1 != null && level2 != null && level3 == null) {
			int level = new Integer(orgLast.getLevel2());
			level = level + 1; // level 加 1
			String levelString = Integer.toString(level);
			if (level < 10) {
				levelString = "0" + level; // 拼接一个 0
				org.setLevel2(levelString);
			}
			org.setLevel1(orgLast.getLevel1());
			org.setLevel2(levelString); // 否则，+1 后 直接set

		}
		// 第三等级
		else if (level1 != null && level2 != null && level3 != null
				&& level4 == null) {
			int level = new Integer(orgLast.getLevel3());
			level = level + 1; // level 加 1
			String levelString = Integer.toString(level);
			if (level < 10) {
				levelString = "0" + level; // 拼接一个 0
				org.setLevel3(levelString);
			}
			org.setLevel1(orgLast.getLevel1());
			org.setLevel2(orgLast.getLevel2());
			org.setLevel3(levelString); // 否则，+1 后 直接set
		}
		// 第四等级
		else if (level1 != null && level2 != null && level3 != null
				&& level4 != null && level5 == null) {
			int level = new Integer(orgLast.getLevel4());
			level = level + 1; // level 加 1
			String levelString = Integer.toString(level);
			if (level < 10) {
				levelString = "0" + level; // 拼接一个 0
				org.setLevel4(levelString);
			}
			org.setLevel1(orgLast.getLevel1());
			org.setLevel2(orgLast.getLevel2());
			org.setLevel3(orgLast.getLevel3());
			org.setLevel4(levelString); // 否则，+1 后 直接set
		}
		// 第五等级
		else if (level1 != null && level2 != null && level3 != null
				&& level4 != null && level5 != null && level6 == null) {
			int level = new Integer(orgLast.getLevel5());
			level = level + 1; // level 加 1
			String levelString = Integer.toString(level);
			if (level < 10) {
				levelString = "0" + level; // 拼接一个 0
				org.setLevel5(levelString);
			}
			org.setLevel1(orgLast.getLevel1());
			org.setLevel2(orgLast.getLevel2());
			org.setLevel3(orgLast.getLevel3());
			org.setLevel4(orgLast.getLevel4());
			org.setLevel5(levelString); // 否则，+1 后 直接set
		}
		// 第六等级
		else if (level1 != null && level2 != null && level3 != null
				&& level4 != null && level5 != null && level6 != null
				&& level7 == null) {
			int level = new Integer(orgLast.getLevel6());
			level = level + 1; // level 加 1
			String levelString = Integer.toString(level);
			if (level < 10) {
				levelString = "0" + level; // 拼接一个 0
				org.setLevel6(levelString);
			}
			org.setLevel1(orgLast.getLevel1());
			org.setLevel2(orgLast.getLevel2());
			org.setLevel3(orgLast.getLevel3());
			org.setLevel4(orgLast.getLevel4());
			org.setLevel5(orgLast.getLevel5());
			org.setLevel6(levelString); // 否则，+1 后 直接set
		}

		// 第七等级
		else if (level1 != null && level2 != null && level3 != null
				&& level4 != null && level5 != null && level6 != null
				&& level7 != null) {
			int level = new Integer(orgLast.getLevel7());
			level = level + 1; // level 加 1
			String levelString = Integer.toString(level);
			if (level < 10) {
				levelString = "0" + level; // 拼接一个 0
				org.setLevel7(levelString);
			}
			org.setLevel1(orgLast.getLevel1());
			org.setLevel2(orgLast.getLevel2());
			org.setLevel3(orgLast.getLevel3());
			org.setLevel4(orgLast.getLevel4());
			org.setLevel5(orgLast.getLevel5());
			org.setLevel6(orgLast.getLevel6());
			org.setLevel7(levelString); // 否则，+1 后 直接set
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		String sucMsg = "";
		try {
			boolean b = orgService.addOrg(org);
			if(b){
				log.debug("添加机构成功 orgService.addOrg " + org + "  ok");
				sucMsg = "addOrgSuc";
			}
		} catch (Exception e) {
			log.error("添加机构错误 orgService.addOrg " + org + "  error");
			sucMsg = "addOrgError";
		}
		
		//输出添加成功或者失败的信息
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
		//用的ajax添加。success输出即可，不用重定向
		//return "redirect:/organizationManager/orgList.do";
	}

	/**
	 *  机构修改的跳转
	 * @param request 获取需要修改的机构 orgId
	 * @param map     通过orgId获取到 机构信息，放到map,传到下一个页面
	 * @return		organizationManager/organization/orgUpdate.jsp
	 */
	@RequestMapping(value = "/updateOrgPage")
	public String updateOrgPage(HttpServletRequest request,
			Map<String, Object> map) {
		log.debug("updateOrgPage");
		int orgId = new Integer(request.getParameter("orgId"));
		Organization org = new Organization(); 
		try {
			org = orgService.queryOrgById(orgId);
		} catch (Exception e) {
			log.error("orgService.queryOrgById "+ orgId +" error 获取一条机构出错");
		}

		map.put("org", org);

		return "organizationManager/organization/orgUpdate";
	}

	/**
	 * 机构的修改
	 * @param org	从修改页面获取的  自动封装成的对象
	 * @param request 
	 * @return "redirect:/organizationManager/orgList.do"
	 */
	@RequestMapping(value = "/updateOrg", method = RequestMethod.POST)
	public String updateOrg(Organization org, HttpServletRequest request) {
		log.debug("updateOrg" + org);
		// jsp获取一个orgId,和一个org(为了获取orgName)
		int orgId = new Integer(request.getParameter("orgId"));
		Organization orgNew = new Organization();
		try {
			orgNew = orgService.queryOrgById(orgId);
			orgNew.setOrgName(org.getOrgName().trim()); // jsp只是修改了org的orgName
		} catch (Exception e) {
			log.error("orgService.queryOrgById "+ orgId +" error 获取一条机构出错");
		}
		String sucMsg = "";
		try {
			orgService.updateOrg(orgNew);
			sucMsg = "updateOrgSuc";
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("updateOrg"+org+"  error 修改机构出错");
			sucMsg = "updateOrgError";
		}
		// return "redirect:/organizationManager/queryOrgLevel.do?id=0";

		
		
		return "redirect:/organizationManager/orgList.do?sucMsg="+sucMsg;
	}
	/**
	 * 机构的删除.删除以后返回上一个页面。重定向到展示页面
	 * @param request
	 * @return "redirect:/organizationManager/orgList.do"
	 */
	
	@RequestMapping(value = "/deleteOrg", method = RequestMethod.POST)
	public void deleteOrg(HttpServletRequest request,HttpServletResponse response) {
		int orgId = new Integer(request.getParameter("orgId"));
		String sucMsg = "deleteOrgSuc";
		try {
			 boolean b = orgService.deleteOrgByOrgId(orgId);//返回true表示删除成功，抛出异常表示删除失败。没有false
			 if(b){
				 sucMsg = "deleteOrgSuc";
			 }
		} catch (Exception e) {
			log.error("通过orgId删除失败");
			sucMsg = "deleteOrgError";
		}
		// return "redirect:/organizationManager/queryOrgLevel.do?id=0";
		
		//把删除成功与否的信息输出
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(sucMsg);
			pw.flush();
		} catch (IOException e) {
			log.error("删除机构 pw.write(sucMsg)出错");
		}finally{
			if (pw != null) {
				pw.close();
			}
		}
		//return "redirect:/organizationManager/orgList.do?sucMsg="+sucMsg;
	}

	/**
	 * 通过id查询一条机构信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/queryOrgById", method = RequestMethod.POST)
	public void queryOrgById(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("queryOrgById");
		String id = request.getParameter("id");

		if (id.indexOf("_") != -1) {
			int index = id.indexOf("_");// 点击全国的时候id是 0_xxxxxx共15位，所以截取
			id = id.substring(0, index);
		}

		int orgId = new Integer(id);
		Organization org = new Organization();
		try {
			org = orgService.queryOrgById(orgId);
		} catch (Exception e1) {
			log.error("orgService.queryOrgById "+ orgId +" error 获取一条机构出错");
		}
		JSONArray jsonData = JSONArray.fromObject(org);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			// pw.print(jsonData);
			pw.write(jsonData.toString());
			pw.flush();
		} catch (IOException e) {
			log.error("response.getWriter().print  error 通过id查询一条机构信息输出json出错");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

	}
	/**
	 * 查询root节点
	 * @param response
	 */
	@RequestMapping(value="/queryOrgRoot",method = RequestMethod.POST)
	public void queryOrgRoot(HttpServletResponse response){
		
		log.debug("queryOrgRoot");
		Organization org = new Organization();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			org = orgService.queryOrgRoot();
			pw = response.getWriter(); 
			pw.print(JSONArray.fromObject(org).toString());
			pw.flush();
		} catch (IOException e) {
			log.error("response.getWriter().print  error 输出json出错");
		} catch (Exception e) {
			log.error("queryOrgRoot  error  查询这个机构出错");
		} finally{
			if(pw != null){
				pw.close();
			}
		}
		
	}
	
	/**
	 * 查询所有机构
	 * @param response
	 */
	@RequestMapping(value="/queryAllOrg",method = RequestMethod.POST)
	public void queryAllOrg(HttpServletResponse response){
		log.debug("queryAllOrg");
		List<Organization> list = new ArrayList<Organization>();
		try {
			list = orgService.queryAllOrg();
		} catch (Exception e1) {
			//e1.printStackTrace();
			log.error("queryAllOrg  error 查询所有机构出错");
		}
		/*String s1 = "{id:1, pId:0, name:\"test1\" , open:true}";   
        String s2 = "{id:2, pId:1, name:\"test2\" , open:true}";   
        String s3 = "{id:3, pId:1, name:\"test3\" , open:true}";   
        String s4 = "{id:4, pId:2, name:\"test4\" , open:true}";   
       
        List<String> lstTree = new ArrayList<String>();   
        lstTree.add(s1);
        lstTree.add(s2);  
        lstTree.add(s3);   
        lstTree.add(s4);  */
        //利用Json插件将Array转换成Json格式
		response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = null;
		try {
			pw = response.getWriter(); 
			pw.print(JSONArray.fromObject(list).toString());
			pw.flush();
		} catch (IOException e) {
			log.error("response.getWriter().print  error 查询所有机构输出json出错");
		} finally{
			if(pw != null){
				pw.close();
			}
		}
	}

	/**
	 * 跳转到机构导入的list页面
	 * @return "organizationManager/organization/orgList"   省去.jsp
	 */
	@RequestMapping(value = "/orgList")
	public String orgList(HttpServletRequest request,Resource res) {
		String resid = "";
		if(res!=null && res.getResid() != null && res.getResid() != ""){
			resid = res.getResid().trim();
			request.getSession().setAttribute("resid", resid);
		}//如果传入了resid 资源id就 王session中set一下，覆盖一下以前的。否则不做操作（资源id还是以前的）。
		
		
		log.debug("orgList");
		String sucMsg = request.getParameter("sucMsg");//判断用户做的什么操作，提示成功信息所用
		//System.out.println(sucMsg);
		request.setAttribute("sucMsg", sucMsg);
		return "organizationManager/organization/orgList";
	}

	/**
	 * 机构导入的跳转
	 * @return "organizationManager/organization/orgUpload" 省去.jsp
	 */
	// 点击导入按钮 的弹窗
	@RequestMapping(value = "orgUploadPage")
	public String orgUploadPage() {
		log.debug("orgUploadPage");
		return "organizationManager/organization/orgUpload";
	}

	/**
	 * 文件上传   + 机构导入
	 * @param multipartRequest 获取页面文件
	 * @return  "organizationManager/organization/orgList"   
	 */
	// csv机构导入
	@RequestMapping(value = "/orgLeadingIn", method = RequestMethod.POST)
	public String orgLeadingIn(MultipartHttpServletRequest multipartRequest,HttpServletRequest request) {
		log.debug("orgLeadingIn");

		String sucMsg = "";
		try {

			MultipartFile file = multipartRequest.getFile("file"); // 获得文件
			String filename = file.getOriginalFilename(); // 获得文件名
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String timp = sdf.format(date);
			filename = filename.substring(0, filename.lastIndexOf("."))+timp+".csv";
			//给上传的文件 去掉后缀 ，加一个时间戳，在加上去.csv  组成新的文件名，避免文件重复
			
			InputStream fin = file.getInputStream(); // 获得输入流

			// String uploadPath = "D:\\JavaProject\\myspring\\web\\uploads";

			/*
			 * String relativelyPath=System.getProperty("user.dir"); String
			 * uploadPath = relativelyPath+"\\web\\uploads";
			 */
			/*String path = this.getClass().getResource("/").getPath();// 得到d:/tomcat/webapps/工程名WEB-INF/classes/路径
			path = path.substring(1, path.indexOf("WEB-INF/classes"));// 从路径字符串中取出工程路劲
			String uploadPath = path + "web\\uploads"; // tomcat工作目录下
			*/
			String realPath = request.getSession().getServletContext().getRealPath("/");
			//orgfile.path
			String pathAdd = ProjectProperty.getInstance().get("orgfile.path");
			if((null == pathAdd) || ("null" == pathAdd) || ("" == pathAdd)){
				pathAdd = "uploads";
			}
			String uploadPath = realPath+pathAdd;
			
			this.setPath(uploadPath);
			this.setFileName(filename);
			
			log.debug("orgLeadingIn " + "upload path is:" + uploadPath);
			if (!new File(uploadPath).isDirectory()) {
				new File(uploadPath).mkdirs(); // 创建文件夹
				log.debug("createNewFile" + uploadPath);
			}
			
			
			File f2 = new File(uploadPath, filename); // 创建文件（上传的文件）

			if (!f2.exists()) {
				f2.createNewFile();
				log.debug("createNewFile" + f2);
			}
			log.debug("uploadPath" + uploadPath);

			FileOutputStream fout = new FileOutputStream(f2);
			byte[] b = new byte[1024];
			int length = 0;
			while ((length = fin.read(b)) != -1) {
				fout.write(b,0, length); // 写出文件
			}

			if (fin != null) {
				fin.close(); // 关闭从web读取的输入流
			}
			if (fout != null) {
				fout.close(); // 关闭写文件的输出流
			}

			//orgService.deleteAll(); //文件上传完成，清空数据 转移到service中处理。使用事务，避免清除了数据成功，但是批量插入报错。
			
			/*
			 * new Thread 文件上传以后，用来处理数据的插入。以提高用户体验。不用一直等待
			 * getPath()	获取路径
			 * getFleName()	获取文件名
			 * 
			 */
			new Thread() {
				public void run() {

					//查询所有机构，用level拼接起来 010203作为key放到map中，在csv读取文件是 map.get(key)判断是否存在
					List<Organization> queryList = new ArrayList<Organization>();
					try {
						queryList = getOrgService().queryAllOrg();
						System.out.println(queryList.size());
					} catch (Exception e2) {
						//e2.printStackTrace();
						log.error("机构导入时查询所有机构出错");
					}
					Map<String, Organization> queryResultMap = new HashMap<String, Organization>();
					
					for(int i = 0;i<queryList.size();i++){
						Organization orgOld = queryList.get(i);
						//String levelStrOld = "";
						StringBuilder levelStrOld = new StringBuilder("");
						if(orgOld.getLevel1() != null){
							//levelStrOld += orgOld.getLevel1();
							levelStrOld.append(orgOld.getLevel1());
						}
						if(orgOld.getLevel2() != null){
							//levelStrOld += orgOld.getLevel2();
							levelStrOld.append(orgOld.getLevel2());
						}
						if(orgOld.getLevel3() != null){
							//levelStrOld += orgOld.getLevel3();
							levelStrOld.append(orgOld.getLevel3());
						}
						if(orgOld.getLevel4() != null){
							//levelStrOld += orgOld.getLevel4();
							levelStrOld.append(orgOld.getLevel4());
						}
						if(orgOld.getLevel5() != null){
							//levelStrOld += orgOld.getLevel5();
							levelStrOld.append(orgOld.getLevel5());
						}
						if(orgOld.getLevel6() != null){
							//levelStrOld += orgOld.getLevel6();
							levelStrOld.append(orgOld.getLevel6());
						}
						if(orgOld.getLevel7() != null){
							//levelStrOld += orgOld.getLevel7();
							levelStrOld.append(orgOld.getLevel7());
						}
						
						String levelKey = levelStrOld.toString();//数据库查询出来的queryList.get(i)的等级拼接
						//String str2 = levelStr.toString();	 //csv文件读取到的机构等级 拼接
						queryResultMap.put(levelKey, orgOld);
						
					}
					log.debug("查询数据库机构数量是    " + queryResultMap.size());
					//查询结束
					
					FileInputStream fis = null;
					BufferedReader br = null;
					// 读取文件并，并导入 数据库中
					try {
						
						File f2 = new File(getPath(), getFileName());

						FileEncodeReferee fer = new FileEncodeReferee(f2);//	获取文件编码格式
						String charSet = fer.getCharset();
						
						fis = new FileInputStream(f2);
						// 读取刚才 上次，写入的文件。
						br = new BufferedReader(new InputStreamReader(fis,charSet));
						String result;
						// 用来判断是否是标题，做的标记
						int count = 0;
						List<Organization> list = new ArrayList<Organization>();

						//读取csv文件
						boolean ifInsert = true; 
						while ((result = br.readLine()) != null) {
							//System.out.println(result);
							result = result.trim();
							if (count >= 0) { // 如果第一列是标题，改成>0.即不读取第一行
								if (!"".equals(result) && result.length() > 0) {
									// String[] strs =
									// {"","","","","","","",""};
									String[] strs = new String[9]; // 需要插入
																	// 用户的属性有几个就插入几个
									int resultLeng = result.split(",").length;
									if(resultLeng<2){
										ifInsert = false;//简单的判断用户的文件是否是正确的格式。最短长度也是2，假如<2，直接break
										break;
									}
									for (int i = 0; i < resultLeng; i++) {
										String str = result.split(",")[i];
										if (str.trim() == "") {
											str = null;
											strs[i] = str;
										} else {
											strs[i] = str;
										}
									}
									
									
									//判断是否重复
									StringBuilder levelStr = new StringBuilder("");
									for(int j = 2;j < strs.length;j++){//strs[0]:orgId 	strs[1]:orgName  	strs[2]:orgLevel1
										if(strs[j] != null){
											//levelStr += strs[j];
											levelStr.append(strs[j]);//读取csv文件，并拼接 level
										}
									}
									String levelStrNew = levelStr.toString();
									Organization orgIfRepeat = queryResultMap.get(levelStrNew);//通过map获取机构，获取的null,说明csv文件和 数据库不重复，添加到list	。不为null，说明存在，不做处理
									if(orgIfRepeat == null){
										//map.get(levelStrNew) 为null，说明数据库不存在
										
										Organization org = new Organization();
										try {

											//org.setOrgId(new Integer(strs[0]));
											org.setOrgName(strs[1]);
											org.setLevel1(strs[2]);
											org.setLevel2(strs[3]);
											org.setLevel3(strs[4]);
											org.setLevel4(strs[5]);
											org.setLevel5(strs[6]);
											org.setLevel6(strs[7]);
											org.setLevel7(strs[8]);

										} catch (Exception e) {
											//e.printStackTrace();
											//System.out.println("数据不正确，无法正确转换类型");
											log.error("数据不正确，无法正确转换类型");
										}
										list.add(org);
										//orgService.addOrg(org);
										
										
									}else{
										//重复了，不做操作
									}
									
								}
							}
							count++;
						}//继续读取下一行
						if(ifInsert == true){//如果该符合格式，就批量的插入
							orgService.batchInsertOrg(list);
							if (fis != null) {
								fis.close(); // 关闭写文件的输出流
							}
						}else{				//否则删除不符合格式的文件
							if (fis != null) {
								fis.close(); // 关闭写文件的输出流
							}
							if(f2.exists() && f2.isFile()){
								f2.delete();
								log.debug("删除" +getPath() + "目录下的文件：" + f2.getName() + "成功");
							}
						}
						
					} catch (FileNotFoundException e1) {
						//e1.printStackTrace();
						log.error("机构导入未找到文件");
						
					} catch (IOException e) {
						//e.printStackTrace();
						log.error("读取文件出错");
					}catch (Exception e) {
						//e.printStackTrace();
						log.error("机构导入出错");
					}finally{
						try {
							if (fis != null) {
								fis.close();
							}
						} catch (IOException e) {
							log.error("关闭输出流出错");
						} // 关闭写文件的输出流
						
					}

				}// run method end
			}.start();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			log.error("机构导入未找到文件");
			sucMsg = "orgLeadingInError";
			
		} catch (IOException e) {
			//e.printStackTrace();
			log.error("读取文件出错");
			sucMsg = "orgLeadingInError";
		} catch (Exception e2) {
			//e2.printStackTrace();
			log.error("机构导入失败");
			sucMsg = "orgLeadingInError";
		}
		request.setAttribute("sucMsg", sucMsg);
		return "organizationManager/organization/orgList";
	}
	
	/**
	 * 由一条机构信息，查询所有子机构（包括其本身）
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/querySubOrgListByOrgId")
	public void querySubOrgListByOrg(HttpServletRequest request,HttpServletResponse response) {
		log.debug("querySubOrgListByOrgId");
		String id = request.getParameter("id");

		if (id.indexOf("_") != -1) {
			int index = id.indexOf("_");// 点击全国的时候id是 0_xxxxxx共15位，所以截取
			id = id.substring(0, index);
		}
		int orgId = new Integer(id);
		System.out.println("获取的 orgId is "+orgId);
		Organization org = new Organization();
		try {
			org = orgService.queryOrgById(orgId);
		} catch (Exception e) {
			log.error("orgService.queryOrgById "+ orgId +" error 获取一条机构出错");
		}
		
		
		List<Organization> list = new ArrayList<Organization>();
		try {
			list = orgService.querySubOrgListByOrg(org);
		} catch (Exception e) {
			log.error("orgService.querySubOrgListByOrg(org)   error 由一条机构信息，查询所有子机构 出错");
		}
		
		
		
		
		/*System.out.println("list size 总数："+list.size());
		for(int i = 0;i<list.size();i++){
			System.out.println("orgId is :"+list.get(i).getOrgId()+"   orgName："+list.get(i).getOrgName());
		}
		*/
		
		
		//JSONArray jsonData = JSONArray.fromObject(list);
				//System.out.println(jsonData);
		/*response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			// pw.print(jsonData);
			pw.write(jsonData.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}*/
		
	}
	
	
	/**
	 * 通过orgId 查询账号数量
	 * @descript 查询某个终端下的账号数量，一旦大于0，返回。等于0继续查询下一张表
	 * @param request 从jsp获取值
	 */
	@RequestMapping(value = "/queryAllAcountByOrgId",method = RequestMethod.POST)
	public void queryAllAcountByOrgId(HttpServletRequest request,HttpServletResponse response){
		log.debug("queryAllAcountByOrgId");
		String orgId = request.getParameter("id");
		int count = 0;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(); 
		try {
			orgService.queryAllcateName();
		} catch (Exception e1) {
			log.error("orgService.queryAllcateName error 查询所有 终端类型");
		}
		for(int i=0;i<list.size();i++){
			Map<String, String> map = (Map<String, String>) list.get(i);
			String cateName = (String) map.get("cateName");
			map.put("orgId", orgId);
			try {
				count = orgService.queryCountByOrgId(map);
			} catch (Exception e1) {
				log.error("ta_Account_cateNameDao.queryCountByOrgId  error  查询机构下账号数量出错");
			}
			log.debug(map.get("cateName")+"下的账号 数量是："+count +"orgService.queryCountByOrgId" + map.get("cateName") + "  ok");
			if(count>0){
				//如果查询到该机构下有账号，直接输出数量break不再继续查询
				log.debug("queryAllAcountByOrgId number is not null."+"cateName is:"+cateName+"and the count is:"+count);
				
				JSONArray jsonData = JSONArray.fromObject(count);
				response.setContentType("text/html;charset=utf-8");
				
				PrintWriter pw = null;
				try {
					pw = response.getWriter();
					// pw.print(jsonData);
					pw.write(jsonData.toString());
					pw.flush();
				} catch (IOException e) {
					//e.printStackTrace();
					log.error("pw.write(jsonData.toString())  error. 通过orgId 查询账号数量输出出错");
				} finally {
					if (pw != null) {
						pw.close();
					}
				}
				
				break;
			}else{
				
			}
		}
		
		
		
		//最后遍历了所有的终端，账号的数量还是0  输出出去
		JSONArray jsonData = JSONArray.fromObject(count);
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			// pw.print(jsonData);
			pw.write(jsonData.toString());
			pw.flush();
		} catch (IOException e) {
			//e.printStackTrace();
			log.error("response.getWriter().print  error 通过orgId 查询账号数量输出出错");
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		
		
	}
	
	
	
	
}
