<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>


<title>Insert title here</title>
</head>
<body>

<%-- <form action="${pageContext.request.contextPath}/rolePrivilege/insertRolePrivilegeAction.do" method="post">
<table>
	<tr>
		<td>角色id：</td>
		<td><input type="text" name="roleid">  </td>
	</tr>
	
	<tr>
		<td>权限id：</td>
		<td><input type="text" name="privilegeid"></td>
	</tr>

</table>

<input type="submit" value="保存" >
</form> --%>

<div class="easyui-panel" title="选择角色" collapsible="true" style="background:#fafafa;height:210px;">  
     <!-- 显示角色的div -->
	<table id="listTable">
	</table>
</div>


<div class="easyui-panel" title="选择权限" collapsible="true" style="background:#fafafa;height:210px;">
    <!-- 显示权限div -->
	<ul id="listTree">  
		<c:forEach items="${resList}" var="resource" varStatus="vs">
			
			<!-- 循环资源 -->
			<li id = "${resource.value.resid}"  >
			<%-- <input type="checkbox" name="resid" value="${resource.value.resid}"> --%>
			<span>${resource.value.resname}</span> 
				
				
				<ul>
					<!-- 循环操作类型 -->
					<c:forEach items="${typeList}" var="type" varStatus="vs">
						<li id="${resource.value.resid}    ${type.value.typeid}"> <!-- 前32位是资源id,后32位是操作类型的id -->
						<%-- <input type="checkbox" name="actionType" value="${type.value.typeid}"> --%>
						<span>${type.value.typename}</span> 
						</li>
					</c:forEach>
				</ul>
			</li>
		</c:forEach>
	</ul>
	
		
		<select style="display:none;" id="actionValue" name="actionValue">
			<option value="true">开启</option>
			<option value="false">关闭</option>
		</select>
</div>


<div border="false" class="easyui-panel" collapsible="true" style="padding-top:5px;">
	<a href="#" class="easyui-linkbutton" id="btn" iconCls="icon-save" onclick="insertRolePrivilegeAction()">赋予权限</a> 
</div>

</body>
</html>

<script>

/**
 * 初始化角色数据
 */
 $(function(){
		$('#listTable').datagrid({
			url: '${pageContext.request.contextPath}/role/queryAllRoleAction.do',
			fit : true,
			//title:'角色管理',
			fitColumns:true,
			border:false,
			//sortName:'rolename',//field
			pagination:true,
			pageSize:5,
			pageList:[5,10,20,50],
			columns:[[  
			          {field:'roleid',title:'角色id',checkbox:true,width:100},  
			          {field:'rolename',title:'角色名',sortable:true,width:100},
			      ]]
			
		});
		
});

//初始化资源tree	 
 $('#listTree').tree({
	checkbox:true,
	onLoadSuccess:function(node, data){
		//关闭所有结点
		$('#listTree').tree('collapseAll');
	}
});

/**
 * 添加角色 权限数据
 */
function insertRolePrivilegeAction(){
	
	var roleRows = $('#listTable').datagrid('getSelections');//用户数据
	var nodes = $('#listTree').tree('getChecked');//资源 和 操作类型（其实就是权限）数据
	//console.info(roleRows);
	if(nodes != "" && nodes != null && nodes != undefined){
		//var s = [];//父
		var chiIds = "";//子节点
		for(var i=0; i<nodes.length; i++){
			if(nodes[i].children != undefined){
				//说明有子节点。即：本身为父节点（资源）
				//s.push(nodes[i].text);
			}else{
				//chiIds.push(nodes[i].id.replace(/\s+/g,"")); //去掉所有的空格。来到后来截取。前32位是 资源id,后32位是 操作类型的id
				chiIds += nodes[i].id.replace(/\s+/g,"") + ",";//拼接成字符串，后台便于处理
			}
			
		}
		
		var actionValue = $('#actionValue').val();
	    $.ajax({
	 	   url: "${pageContext.request.contextPath}/rolePrivilege/insertRolePrivilegeAction.do",
	 	   data: {"roleRows":JSON.stringify(roleRows),"resActionIds":chiIds,"actionValue":actionValue},
	 	   type:"POST",
	 	   success: function(msg){
	 		   //$('#listTable').datagrid('reload');//刷新当前页
	 		  window.location.href = "${pageContext.request.contextPath}/rolePrivilege/queryAllRolePrivilege.do";
	 	   }
	 	 });
	}
	
}


</script>
