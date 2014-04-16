package adtec.accorgrelation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import adtec.accorgrelation.model.Accountorgrelation;
import adtec.appManager.controller.ClassToJson;
import adtec.privilege.model.Resource;
import adtec.util.switchInterface.AccOrgSetXmlService;

/**
 * @FileName: AccountorgrelationController
 * 
 * @FileType: Class
 * 
 * @Date: 2014年3月7日  2014年3月7日 10:07:24
 * 
 * @Author: lj
 * 
 * @Description: 机构帐号关系Controller
 * 
 */
@Controller
@RequestMapping(value = "/accorgrelation")
public class AccountorgrelationController {

/*	AccountorgrelationService accountorgrelationService;
	

	public AccountorgrelationService getAccountorgrelationService() {
		return accountorgrelationService;
	}

	public void setAccountorgrelationService(
			AccountorgrelationService accountorgrelationService) {
		this.accountorgrelationService = accountorgrelationService;
	}*/
	
	private AccOrgSetXmlService accorgsetxmlservice;
	
	

	public AccOrgSetXmlService getAccorgsetxmlservice() {
		return accorgsetxmlservice;
	}


	public void setAccorgsetxmlservice(AccOrgSetXmlService accorgsetxmlservice) {
		this.accorgsetxmlservice = accorgsetxmlservice;
	}

	Logger log = Logger.getLogger(AccountorgrelationController.class);

	
	
	/**
	 * 添加业务的展示界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/accorgInsert")
	public ModelAndView showAccountorgrelationAdd() {
		log.debug("showAccountorgrelationAdd");
		ModelAndView mv = new ModelAndView();
		//跳转到添加展示的jsp页面
		mv.setViewName("/accorgrelation/accorgInsert");
		return mv;
	}
	
	
	/**
		 * 添加业务的处理方法
		 * 
		 * @param request
		 * @param response
		 * @param ta_App
		 *            对应的javabean
		 * @return
		 */

		@RequestMapping(value = "/insertAction", method = RequestMethod.POST)
		public String addAccountorgrelationAction(Accountorgrelation accountorgrelation) {
			log.debug("addAccountorgrelationAction");
			//跳转提示
			String PromptMsg = null;
			try {
				/*boolean tag = accorgsetxmlservice.getaccService().insertAccountorgrelation(accountorgrelation);*/
				boolean tag = accorgsetxmlservice.getSwtService().insertAccountorgrelation(accountorgrelation);
				if(tag){
					PromptMsg = "InsertMsg";
					log.debug("帐号添加成功！");
				}
			} catch (Exception e) {
				PromptMsg = "failMsg";
				log.error("帐号添加失败！");
			}
			//跳转到查询的Controller
			return "redirect:/accorgrelation/accorgList.do?PromptMsg="+PromptMsg;
		}
		/**
		  * 查询的展示界面
		  * @param request
		  * @param response
		  * @return
		  */
		@RequestMapping(value = "/accorgList")
		public ModelAndView showaccountList(HttpServletRequest request,
				HttpServletResponse response,Accountorgrelation accountorgrelation,Resource res) {
			String resid = "";
			if(res!=null && res.getResid() != null && res.getResid() != ""){
				resid = res.getResid().trim();
				request.getSession().setAttribute("resid", resid);
			}//如果传入了resid 资源id就 王session中set一下，覆盖一下以前的。否则不做操作（资源id还是以前的）。
			
			
			log.debug("showaccountList");
			ModelAndView mv = new ModelAndView();
			try {
				//添加或修改时的提示信息
				String PromptMsg = request.getParameter("PromptMsg");
				String tid = request.getParameter("input");
				if(tid == null){
					accountorgrelation.setOrgId(0);
				}else if (tid.indexOf("_") != -1) {
					int index = tid.indexOf("_");// 点击全国的时候id是 0_xxxxxx共15位，所以截取
					tid = tid.substring(0, index);
					accountorgrelation.setOrgId(Integer.parseInt(tid));
					/*
					 * org.setOrgId(orgId); org.setOrgName("全国");
					 * //是全国，直接queryOrgLevel
					 */
				} else {
					accountorgrelation.setOrgId(Integer.parseInt(tid));// 不是全国，queryById之后，再查询list
				}
				/*accountorgrelation = accountorgrelationService.getaccService().queryAccOrgRelByAccOrgRel(accountorgrelation);*/
				accountorgrelation = accorgsetxmlservice.getSwtService().queryAccOrgRelByAccOrgRel(accountorgrelation);
				request.setAttribute("accountorgrelation", accountorgrelation);
				request.setAttribute("PromptMsg", PromptMsg);
			} catch (Exception e) {
				log.error("没有查询到数据！   accountList error");
				e.printStackTrace();
			}
			//跳转到list查询的jsp页面
			mv.setViewName("/accorgrelation/accorgList");
			return mv;
		}	
		
		/**
		 * 删除整条数据
		 * 
		 * @param user
		 * @return
		 */
		@RequestMapping(value = "/delAction", method = RequestMethod.POST)
		public void delAction(HttpServletRequest request,
				HttpServletResponse response) {
			log.debug("delAction");
			try {
				int relationid = Integer.parseInt(request.getParameter("relationid"));
				/*boolean tag = accountorgrelationService.getaccService().deleteAccountorgrelation(relationid);*/
				boolean tag = accorgsetxmlservice.getSwtService().deleteAccountorgrelation(relationid);
				
				String json = ClassToJson.object2json(tag);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.write(json);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				log.error("响应数据流异常");
				e.printStackTrace();
			} catch (Exception e) {
				log.error("数据删除失败！");
				e.printStackTrace();
			}
			
		}
		
		
		/**
		 * 批量删除整条数据
		 * 
		 * @param user
		 * @return
		 */
		@RequestMapping(value = "/batchdeleteAction", method = RequestMethod.POST)
		public void batchdeleteAction(HttpServletRequest request,
				HttpServletResponse response) {
			log.debug("batchdeleteAction");
			try {
				String id = request.getParameter("ids");
				Accountorgrelation accountorgrelation = new Accountorgrelation();
				List<Integer> list = new ArrayList<Integer>();
					for (int i = 0; i < id.split(",").length; i++) {
						int j = Integer.parseInt(id.split(",")[i]);
						list.add(j);
				}
				accountorgrelation.setList(list);
				/*boolean tag = accountorgrelationService.getaccService().batchdelete(accountorgrelation);*/
				boolean tag = accorgsetxmlservice.getSwtService().batchdelete(accountorgrelation);
				
				String json = ClassToJson.object2json(tag);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.write(json);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				log.error("响应数据流异常");
				e.printStackTrace();
			} catch (Exception e) {
				log.error("数据删除失败！");
				e.printStackTrace();
			}
			
		}
		
		
		
		
		/**
		 *  * 单一实例查询界面
		 * 
		 * @return
		 */
		@RequestMapping(value = "/accorgFindById")
		public ModelAndView showaccountorgrelationFindById(HttpServletRequest request,
				HttpServletResponse response) {
			log.debug("showaccountorgrelationFindById");
			ModelAndView mv = new ModelAndView();
			try {
				int relationid = Integer.parseInt(request.getParameter("relationid"));
				/*Accountorgrelation accountorgrelation = accountorgrelationService.getaccService().findAccOrgRelByRelationid(relationid);*/
				Accountorgrelation accountorgrelation = accorgsetxmlservice.getSwtService().findAccOrgRelByRelationid(relationid);
				request.setAttribute("accountorgrelation", accountorgrelation);
			} catch (Exception e) {
				log.error("通过relationid没有查询到数据！   showaccountorgrelationFindById error");
				e.printStackTrace();
			}
			//跳转到修改的jsp页面
			mv.setViewName("/accorgrelation/accorgFindById");
			return mv;
		}
		
		/**
		 *  * 修改展示界面
		 * 
		 * @return
		 */
		@RequestMapping(value = "/accorgUpdate")
		public ModelAndView showaccountorgrelationupdate(HttpServletRequest request,
				HttpServletResponse response) {
			log.debug("showaccountupdate");
			ModelAndView mv = new ModelAndView();
			try {
				int relationid = Integer.parseInt(request.getParameter("relationid"));
				/*Accountorgrelation accountorgrelation = accountorgrelationService.getaccService().findAccOrgRelByRelationid(relationid);*/
				Accountorgrelation accountorgrelation = accorgsetxmlservice.getSwtService().findAccOrgRelByRelationid(relationid);
				request.setAttribute("accountorgrelation", accountorgrelation);
			} catch (Exception e) {
				log.error("通过relationid没有查询到数据！   showaccountorgrelationupdate error");
				e.printStackTrace();
			}
			//跳转到修改的jsp页面
			mv.setViewName("/accorgrelation/accorgUpdate");
			return mv;
		}
		
		/**
		 * 修改的处理方法
		 * 
		 * @param request
		 * @param response
		 *            
		 * @return
		 */
		@RequestMapping(value = "/updateAction", method = RequestMethod.POST)
		public String updateccountorgrelationAction(HttpServletRequest request,
				HttpServletResponse response,Accountorgrelation accountorgrelation) {
			log.debug("updateccountorgrelationAction");
			String PromptMsg = null;
			try {
				/*boolean tag = accountorgrelationService.getaccService().updateAccountorgrelation(accountorgrelation);*/
				boolean tag = accorgsetxmlservice.getSwtService().updateAccountorgrelation(accountorgrelation);
				if(tag){
					PromptMsg = "UpdateMsg";
					log.debug("帐号机构关系修改成功！");
				}
			} catch (Exception e) {
				PromptMsg = "failMsg";
				log.error("帐号机构关系修改失败！");
			}
			
			//跳转到查询的Controller
			return "redirect:/accorgrelation/accorgList.do?PromptMsg="+PromptMsg;
		}
		
}
