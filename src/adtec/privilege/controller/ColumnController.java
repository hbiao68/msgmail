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

import org.apache.cxf.ws.mex.model._2004_09.GetMetadata;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import adtec.init.CachFactory;
import adtec.privilege.model.Column;
import adtec.privilege.model.Page;
import adtec.privilege.model.Resource;
import adtec.privilege.service.ColumnService;

/**
 * 分栏管理的controller类
 * @author maojd
 * @description 消息邮局左侧菜单栏
 * @date 14:18 2014/4/1
 */
@Controller
@RequestMapping("/column")
public class ColumnController {

	private ColumnService columnService;
	private Map<String, Object> columnMap = CachFactory.getInstance().getMapByKey("columnMap");
	private Logger log = Logger.getLogger(ColumnController.class);

	public ColumnService getColumnService() {
		return columnService;
	}
	public void setColumnService(ColumnService columnService) {
		this.columnService = columnService;
	}
	
	public Map<String, Object> getColumnMap() {
		if(columnMap == null){
			columnMap = CachFactory.getInstance().getMapByKey("columnMap");
		}
		return columnMap;
	}
	public void setColumnMap(Map<String, Object> columnMap) {
		this.columnMap = columnMap;
	}

	
	/**
	 * 查询用户权限 栏位信息
	 * @return 输出信息（main页面用栏位）
	 */
	@RequestMapping(value="/queryColumnPrivlgMap")
	public void queryColumnPrivlgMap(HttpServletRequest request,HttpServletResponse response){
	
		Map<String, Object> columnPrivlgMap = new HashMap<String, Object>();
		
		boolean wholePrivlg = false; 
		if(request.getSession().getAttribute("wholePrivlg") != null){
			wholePrivlg = (boolean) request.getSession().getAttribute("wholePrivlg");
		}
				
		Map<String, Object> columnMap = (Map<String, Object>) request.getSession().getAttribute("columnMap");
		Map<String, Object> oneUserAllPrivlgMap = (Map<String, Object>) request.getSession().getAttribute("oneUserAllPrivlgMap");
		
		columnPrivlgMap.put("wholePrivlg", wholePrivlg);//全局的boolean 权限是否开启
		columnPrivlgMap.put("columnMap", columnMap);	  //栏位名的map key:columnId value：栏位对象
		columnPrivlgMap.put("oneUserAllPrivlgMap", oneUserAllPrivlgMap);//用户所有权限 key resid+actionTypeId  所有的权限实体是值
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			// pw.print(jsonData);
			//pw.write(JSONArray.fromObject(userMap).toString()); //输出的全部是字符串  
			pw.write(JSONObject.fromObject(columnPrivlgMap).toString());//输入的是json key Object的字符串
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
	 * 不带分页查询所有
	 * @return 输出信息（main页面用栏位）
	 */
	@RequestMapping(value="/queryAllColumnActionNoPage")
	public void queryAllColumnActionNoPage(HttpServletRequest request,HttpServletResponse response){
	
		//分页查询
		List<Column> list = new ArrayList<Column>();
		
		try {
			list = columnService.queryAllColumn(null);
		} catch (Exception e) {
			log.error("queryAllColumn error分页查询栏位出错");
		}
		
		//查询总数
		int count = 0;
		try {
			count = columnService.queryColumnCount(null);
		} catch (Exception e) {
			log.error("queryColumnCount error 查询菜单栏位总数出错");
		}
		
		Map<String, Object> userEasyUIMap = new HashMap<String,Object>();
		userEasyUIMap.put("rows", list);//easyUI需要用的rows
		userEasyUIMap.put("total", count); //easyUI需要用的总数
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			// pw.print(jsonData);
			//pw.write(JSONArray.fromObject(userMap).toString()); //输出的全部是字符串  
			pw.write(JSONObject.fromObject(userEasyUIMap).toString());//输入的是json key Object的字符串
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
	 * 查询所有的跳转跳转到栏位的管理页面
	 * @return
	 */
	@RequestMapping(value="/queryAllColumn")
	public String queryAllColumn(){
	
		return "privilege/column/columnList";
	}
	
	
	/**
	 * 查询所有栏位的实现方法(带分页)。模糊查询也是调用此方法
	 * @return
	 */
	@RequestMapping(value="/queryAllColumnAction")
	public void queryAllColumnAction(Column column,Resource resource,HttpServletRequest request,HttpServletResponse response){
	
		//分页查询
		List<Column> list = new ArrayList<Column>();
		if(column != null && column.getSort() !=null && column.getSort().trim() != ""){
			column.setSort(column.getSort().toLowerCase());//排序字段全部转化为小写。避免Linux系统 排序字段报错
		}
		column.setResource(resource);
		
		
		try {
			list = columnService.queryAllColumn(column);
		} catch (Exception e) {
			log.error("queryAllColumn error分页查询栏位出错");
		}
		
		//查询总数
		int count = 0;
		try {
			count = columnService.queryColumnCount(column);
		} catch (Exception e) {
			log.error("queryColumnCount error 查询菜单栏位总数出错");
		}
		
		Map<String, Object> userEasyUIMap = new HashMap<String,Object>();
		userEasyUIMap.put("rows", list);//easyUI需要用的rows
		userEasyUIMap.put("total", count); //easyUI需要用的总数
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			// pw.print(jsonData);
			//pw.write(JSONArray.fromObject(userMap).toString()); //输出的全部是字符串  
			pw.write(JSONObject.fromObject(userEasyUIMap).toString());//输入的是json key Object的字符串
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
	 * 添加栏位的实现
	 * @param response 输出添加成功与否的信息
	 * @return
	 */
	@RequestMapping(value="/insertColumnAction",method={RequestMethod.POST,RequestMethod.GET})
	public void insertColumnAction(HttpServletResponse response,Column column){
		String uuid = UUID.randomUUID().toString().replace("-", "");
		column.setColumnId(uuid);
		String infoMsg = "";
		try {
			boolean b = columnService.insertColumn(column);
			if(b){
				infoMsg = "addSuc";
				getColumnMap().put(column.getColumnId(), column);//对应的总容器中也添加相应的栏位信息
			}else{
				infoMsg = "addError";
			}
		} catch (Exception e) {
			infoMsg = "addError";
			log.error("insertColumn  error 添加栏位出错");
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
		//return "redirect:/column/queryAllColumn.do";
	}
	
	
	/**
	 * 通过对象查询数量（用于验证是否存在）
	 * @param column
	 * @param response
	 */
	@RequestMapping(value="/queryCountByObj")
	public void queryCountByObj(Column column,HttpServletResponse response){
		
		int infoMsg = 0;
		try {
			infoMsg = columnService.queryCountByObj(column);
			/*if(list.size()>0){//加入只有一条的话，则为本身。
				infoMsg = list.size();
			}*/
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
	 * 修改栏位信息的实现
	 * @param column
	 * @param response
	 */
	@RequestMapping(value="/updateColumnAction",method={RequestMethod.POST})
	public void updateColumnAction(Column column,HttpServletResponse response){
		String infoMsg = "";
		boolean b = true;
		try {
			b = columnService.updateColumn(column);
			if(b){
				infoMsg = "updateSuc";
				getColumnMap().remove(column.getColumnId());//对应的总容器中也修改
				getColumnMap().put(column.getColumnId(), column);
			}
		} catch (Exception e) {
			infoMsg = "updateError";
			log.error("updateColumn error 修改栏位出错");
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
	}
	
	/**
	 * 修改的时候验证是否存在 栏位名（输出栏位名的数量，已经排除了本身）
	 * @param column
	 * @param response
	 */
	@RequestMapping(value="/updateQueryCountByObj")
	public void updateQueryCountByObj(Column column,HttpServletResponse response){
		int infoMsg = 0;
		try {
			List<Column> list = columnService.queryColumnByObj(new Column(null, column.getColumnName(), null, null, null));//传入了一个 columnName查询出来
			for(Column columnNew:list){//遍历一下。栏位id不等于 要修改的栏位id的时候（名字相等，id不相等），则infoMSg + 1
				if(!columnNew.getColumnId().equals(column.getColumnId())){
					infoMsg = infoMsg+1;
				}
			}
			/*if(list.size()>0){//加入只有一条的话，则为本身。
				infoMsg = list.size();
			}*/
		} catch (Exception e) {
			log.error("updateQueryCountByObj error通过对象查询数量出错");
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(infoMsg+"");//一定要转化为字符串
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
	 * 批量删除栏位
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/deleteColumnsById")
	public void deleteColumnsById(HttpServletRequest request,HttpServletResponse response){
		String ids = request.getParameter("ids");
		
		String infoMsg = "";
		boolean b;
		try {
			b = columnService.deleteColumnsById(ids);
			if(b){
				infoMsg = "delSuc";//删除成功
				String[] idsArray = ids.split(",");
				for(int i = 0; i < idsArray.length; i++ ){
					getColumnMap().remove(idsArray[i]);
				}
			}else{
				infoMsg = "delError";
			}
		} catch (Exception e) {
			infoMsg = "delError";
			log.error("deleteColumnsById error通过id批量栏位删除出错");
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
		
	}
}
