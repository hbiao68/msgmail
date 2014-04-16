package adtec.categoryManager.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import adtec.accountManager.model.Ta_Account_cateName;
import adtec.appManager.controller.ClassToJson;
import adtec.appManager.model.PageModel;
import adtec.categoryManager.model.Ta_category;
import adtec.categoryManager.model.Ta_extendpropdef;
import adtec.categoryManager.service.CategoryManagerService;
import adtec.init.CachFactory;
import adtec.privilege.model.Privilege;
import adtec.privilege.model.Resource;
import adtec.privilege.model.User;
import adtec.privilege.service.ResourceService;

/**
 * @FileName: CategoryManagerController
 * 
 * @FileType: Class
 * 
 * @Date: 2013年10月13日
 * 
 * @Author: 李季
 * 
 * @Description: 终端分类管理Controller
 * 
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryManagerController {

	private CategoryManagerService categoryManagerService;
	private ResourceService resourceService;
	public CategoryManagerService getCategoryManagerService() {
		return categoryManagerService;
	}

	public void setCategoryManagerService(
			CategoryManagerService categoryManagerService) {
		this.categoryManagerService = categoryManagerService;
	}
	public ResourceService getResourceService() {
		return resourceService;
	}
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	Logger log = Logger.getLogger(CategoryManagerController.class);

	/**
	 * 校验 对新添加的终端信息进行校验
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/check")
	public ModelAndView checknewcateName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("checknewcateName");
		String cateName = request.getParameter("cateName");

		try {
			// 校验新添加的终端（cateName）在数据库中是否存在
			int count = categoryManagerService.queryforCheckcateNameBycateName(cateName);
			// 通过json将查询结果传递到前台页面段
			String json = ClassToJson.object2json(count);
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error("检验新添加的终端信息是否重复失败");
		}
		return null;
	}

	/**
	 * 查询的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cateList")
	public ModelAndView showcategoryManagerList(HttpServletRequest request,
			HttpServletResponse response,Resource res) {
		String resid = "";
		if(res!=null && res.getResid() != null && res.getResid() != ""){
			resid = res.getResid().trim();
			request.getSession().setAttribute("resid", resid);
		}//如果传入了resid 资源id就 王session中set一下，覆盖一下以前的。否则不做操作（资源id还是以前的）。
		
		
		
		
		log.debug("showcategoryManagerList");
		ModelAndView mv = new ModelAndView();
		//跳转提示信息
		String PromptMsg = "";
		// 获取条件查询的值
		
			String cateName = request.getParameter("cateName");
			String cateDesc = request.getParameter("cateDesc");
			// 查询条件终端描述（cateDesc）做成下拉列表
			List<Ta_category> ins = categoryManagerService.queryforcateDesc();
			PromptMsg = request.getParameter("PromptMsg");
			
			request.setAttribute("ins", ins);

			// 获取当前页数
			int pageNow = 1;
			try {
			if (request.getParameter("pageNow") == null
					|| request.getParameter("pageNow").equals("")
					|| request.getParameter("pageNow").equals("undefined")) {
				if (request.getParameter("pageJump") == null
						|| request.getParameter("pageJump").equals("")
						|| request.getParameter("pageJump").equals("undefined")) {
					pageNow = 1;
				} else {
					pageNow = Integer.parseInt(request.getParameter("pageJump"));
				}

			} else {
				pageNow = Integer.parseInt(request.getParameter("pageNow"));
			}
		} catch (NumberFormatException e) {
			PromptMsg = "jumpMsg";
			log.error("分页跳转传递参数异常");
		}
		request.setAttribute("PromptMsg", PromptMsg);

			if (cateName == null || cateName.equals("")) {
				cateName = "";

			}
			if (cateDesc == null || cateDesc.equals("")) {
				cateDesc = "";
			}
			// 将前端传递来的查询条件去掉空格
			String cateNameTrim = cateName.trim();
			String cateDescTrim = cateDesc.trim();
			Ta_category ta_category = new Ta_category();
			ta_category.setCateName(cateNameTrim);
			ta_category.setCateDesc(cateDescTrim);

			PageModel pageModel = new PageModel();
			// 每页显示的记录数
			pageModel.setPageNow(pageNow);
			int pageSize = pageModel.getPageSize();
			int start = pageModel.getStart();

			ta_category.setStart(start);
			ta_category.setPageSize(pageSize);

			// 获取记录总数
			int count = categoryManagerService.queryCount(ta_category);
			pageModel.setCount(count);

			// 获取总页数
			int pageCount = pageModel.getTotalPages();
			
			int pageUp = pageNow - 1;
			pageModel.setPageUp(pageUp);
			int pageDown = pageNow + 1;
			pageModel.setPageDown(pageDown);
			pageModel.setPageNow(pageNow);
			pageModel.setPageCount(pageCount);
			
			
			request.setAttribute("pageModel", pageModel);

			request.setAttribute("cateName", cateNameTrim);
			request.setAttribute("cateDesc", cateDescTrim);

			ta_category.setStart(start);
			ta_category.setPageSize(pageSize);

			// 调用查询方法，查询出所要数据
			List<Ta_category> rows = categoryManagerService.queryAllByTa_category(ta_category);
			request.setAttribute("category", rows);

		// 跳转到cateList.jsp页面
		mv.setViewName("categoryManager/cateList");
		return mv;
	}

	/**
	 * 单一查询的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cateFindById")
	public ModelAndView showCategoryManagerfindbyid(HttpServletRequest request,
			HttpServletResponse response,
			Map<String, Object> map) {
		log.debug("showCategoryManagerfindbyid");
		ModelAndView mv = new ModelAndView();
		try {
			String ta_id = request.getParameter("ta_id");
			//通过ta_id查询某一终端以及其扩张属性定义的数据
			List<Ta_category> rows1 = categoryManagerService.findAllByTa_id(ta_id);
			Ta_category ta_category = rows1.get(0);

			//如果ins的长度小于6，代表没有扩张属性定义的数据 
			if (ta_category.getCateName() == null) {
				map.put("rows", ta_category);
			} else {
				map.put("rows", ta_category);
				mv.addObject("rows1", rows1);
			}
			mv.setViewName("categoryManager/cateFindById");
		} catch (Exception ex) {
			//ex.printStackTrace();
			log.error("通过ta_id查询单一终端的详细信息失败");
		}
		return mv;
	}

	/**
	 * 修改展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cateUpdate")
	public ModelAndView showCategoryManagerupdate(HttpServletRequest request,
			HttpServletResponse response,
			Map<String, Object> map) {
		log.debug("showCategoryManagerupdate");
		ModelAndView mv = new ModelAndView();
		try {
			String level_id = request.getParameter("ta_id");
			//通过ta_id查询某一终端所有的信息（包括其扩张属性定义的数据。左连接的方法查询，其实查询出来一个list） 
			List<Ta_category> rows1 = categoryManagerService.findAllByTa_id(level_id);
			Ta_category ta_category = rows1.get(0);

			//如果ins的长度小于6，代表没有扩张属性定义的数据 
			if (ta_category.getCateName() == null) {
				map.put("rows", ta_category);
			} else {
				map.put("rows", ta_category);
				mv.addObject("rows1", rows1);
			}

			mv.setViewName("categoryManager/cateUpdate");

		} catch (Exception ex) {
			//ex.printStackTrace();
			log.error("通过ta_id跳转到修改页面失败");
		}

		return mv;
	}

	/**
	 * 添加用户的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cateInsert")
	public ModelAndView showCategoryManagerAdd() {
		ModelAndView mv = new ModelAndView();

		log.debug("showCategoryManagerAdd");
		//跳转到添加页面
		mv.setViewName("/categoryManager/cateInsert");
		return mv;
	}

	/**
	 * 修改的处理方法
	 * 
	 * @param request
	 * @param response
	 * @param user
	 *            对应的javabean
	 * @return
	 */
	@RequestMapping(value = "/updateAction", method = RequestMethod.POST)
	public String updateCategoryManagerAction(HttpServletRequest request,
			HttpServletResponse response, Ta_category ta_category,
			Ta_extendpropdef ta_extendpropdef) {
		log.debug("updateCategoryManagerAction");
		//跳转的提示信息
		String PromptMsg = "";
		try {
			//获取category的主键值
			String ta_id = request.getParameter("ta_id");
			//调用修改方法
			categoryManagerService.update(ta_category, ta_extendpropdef, request,
					ta_id);
			PromptMsg = "UpdateMsg";
		} catch (Exception e) {
			PromptMsg = "failMsg";
			//e.printStackTrace();
			log.error("通过ta_id修改终端的信息失败！");
		}
		
		return "redirect:/category/cateList.do?PromptMsg="+PromptMsg;
	}

	/**
	 * 添加用户的处理方法
	 * 
	 * @param request
	 * @param response
	 * @param user
	 *            对应的javabean
	 * @return
	 */

	@RequestMapping(value = "/insertAction", method = RequestMethod.POST)
	public String addCategoryManagerAction(HttpServletRequest request,
			HttpServletResponse response, Ta_category ta_category,
			Ta_extendpropdef ta_extendpropdef) {
		log.debug("addCategoryManagerAction");

		//跳转提示信息
		String PromptMsg = null;
		try {
			//调用添加方法
			boolean tag = categoryManagerService.add(ta_category, ta_extendpropdef, request);

			if(tag){
				PromptMsg = "InsertMsg";	
			}
			else{
				PromptMsg = "failMsg";
			}
		} catch (Exception e) {
			PromptMsg = "failMsg";
			//e.printStackTrace();
			log.error("添加终端失败！");
		}
		
		//添加成功后，跳转到查询页面
		return "redirect:/category/cateList.do?PromptMsg="+PromptMsg;
	}

	/**
	 * 删除整条数据
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delCategoryAction", method = RequestMethod.POST)
	public void delCategoryManagerAction(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("delCategoryManagerAction");
		try {
			String ta_id = request.getParameter("ta_id");

			//通过主键获取cateName
			Ta_category map = categoryManagerService.findcateNameByta_id(ta_id);
			String cateName = map.getCateName();
			//通过cateName查询该终端下是否有帐号
			List<Ta_Account_cateName> list = categoryManagerService.queryForTa_idCountBycateName(cateName);
			Ta_Account_cateName ins = list.get(0);
			int i = Integer.parseInt(ins.getTa_id());
			//通过ta_id查询该终端下是否有业务
			int j = categoryManagerService.queryAppforTa_idCount(ta_id);
			//如果该终端下既没有业务，也没有帐号，则允许删除
			//删除提示信息
			String msg = null;
			int count = i+j;
			if (count == 0) {
				categoryManagerService.delete(cateName, ta_id);
				msg = "数据删除成功！";

			} else {
				msg = "该终端类型下面有数据，无法删除！";
			}
			//将提示信息放入json中
			String json = ClassToJson.object2json(msg);
			
				response.setContentType("text/html;charset=utf-8");
				/*response.getWriter().write(json);*/
				PrintWriter pw = response.getWriter();
				pw.write(json);
				pw.flush();
				pw.close();
		} catch (Exception ex) {
			log.error("删除终端信息失败！");
			//ex.printStackTrace();
		}

	}
}
