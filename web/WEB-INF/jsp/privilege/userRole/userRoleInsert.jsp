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

<%-- <form action="${pageContext.request.contextPath}/userRole/insertUserRoleAction.do" method="post">
<table>
	<!-- <tr>
		<td>用户名：</td>
		<td><input type="text" name="user.username">  </td>
	</tr> -->
	<tr>
		<td>用户：</td>
		<td>
			<select name="userid">
				<c:if test="${not empty userList }">
					<c:forEach items="${userList}" var="user">
						<option value="${user.value.userid}">${user.value.username}</option>
					</c:forEach>
				</c:if>
			</select>
			<c:forEach items="${userList}" var="user" varStatus="vs">
				<input type="checkbox" name="userid" value="${user.value.userid}">
				${vs.index+1}.&nbsp;${user.value.username}
				<br>
			</c:forEach>
		</td>
		
	</tr>
	
	
	<tr>
		<td>角色：</td>
		<td>
			<select name="roleid">
				<c:if test="${not empty roleList }">
					<c:forEach items="${roleList}" var="role">
						<option value="${role.value.roleid}">${role.value.rolename}</option>
					</c:forEach>
				</c:if>
			</select>
			<c:forEach items="${roleList}" var="role" varStatus="vs">
				<input type="checkbox" name="roleid" value="${role.value.roleid}">
				${vs.index+1}.&nbsp;${role.value.rolename}
				<br>
			</c:forEach>
		</td>
		
	</tr>

</table>

<input type="submit" value="保存" >
</form> --%>

<div class="easyui-panel" title="选择用户" collapsible="true" style="background:#fafafa;height:210px;">  
     <!-- 显示用户的div -->
	<table id="userListTable">
	</table>
</div>

<div class="easyui-panel" title="选择角色" collapsible="true" style="background:#fafafa;height:210px;">
     <!-- 显示角色div -->
	<table id="roleListTable">
	</table>
</div>

<div border="false" class="easyui-panel" collapsible="true" style="padding-top:5px;">
	<a href="#" class="easyui-linkbutton" id="btn" iconCls="icon-save" onclick="insertUserRoleAction()">赋予角色</a> 
</div>

</body>
</html>
<script>

/**
 * 显示用户
 */
$(function(){
	$('#userListTable').datagrid({
		url: '${pageContext.request.contextPath}/user/queryAllUserAction.do',
		fit : true,
		fitColumns:true,
		border:false,
		sortName:'username',//field
		pagination:true,
		pageSize:5,
		pageList:[5,10,20,50],
		columns:[[  
		          {field:'userid',title:'用户id',checkbox:true,width:100},  
		          {field:'username',title:'用户名',sortable:true,width:100}
		      ]]
		
	});
	
});

/**
 * 显示角色
 */
 $(function(){
		$('#roleListTable').datagrid({
			url: '${pageContext.request.contextPath}/role/queryAllRoleAction.do',
			fit : true,
			fitColumns:true,
			border:false,
			//sortName:'rolename',//field
			pagination:true,
			pageSize:5,
			pageList:[5,10,20,50],
			columns:[[  
			          {field:'roleid',title:'角色id',checkbox:true,width:100},  
			          {field:'rolename',title:'角色名',sortable:true,width:100},
			      ]],
			
		});
		
	});

/**
 * 批量开通
 */
 function insertUserRoleAction(){
	var userRows = $('#userListTable').datagrid('getSelections');
	//console.info(userRows);
	//console.info(JSON.stringify(userRows));
	var roleRows = $('#roleListTable').datagrid('getSelections');
	$.ajax({
 	   url: "${pageContext.request.contextPath}/userRole/insertUserRoleAction.do",
 	   data: {"userRows":JSON.stringify(userRows),"roleRows":JSON.stringify(roleRows)},
 	   type:"POST",
 	   success: function(msg){
 		  // $('#listTable').datagrid('reload');//返回用户角色显示页
 		  window.location.href = "${pageContext.request.contextPath}/userRole/queryAllUserRole.do";
 	   }
 	 });
}
 
</script>