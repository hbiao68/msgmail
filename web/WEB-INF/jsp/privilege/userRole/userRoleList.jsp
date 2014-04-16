<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <table>
	<tr>
		<td>id</td>
		<td>用户名</td>
		<td>角色名</td>
		<td>操作</td>
	</tr>

	<c:if test="${empty userRoleList }">
		<div id="tips_bar">
			<div class="nomail"><b>当前没有数据</b></div>
		</div>
	</c:if>
	<c:if test="${not empty userRoleList }">
		<c:forEach items="${userRoleList}" var="userRole">
			<tr style="height: 20px;">
				<td width="10%" title="${vs.index+1 }">${vs.index+1 }</td>
				<td title="${userRole.value.userRoleId }">
					${userRole.value.userRoleId}
				</td>
				
				<td title="${userRole.userRoleid }">
					${userRole.userRoleid}
				</td>
				<td title="${userRole.value.userRole.userRolename }">
					${userRole.value.userRole.userRolename }
				</td>
				
				<td title="${userRole.roleid }">
					${userRole.roleid }
				</td>
				<td title="${userRole.value.role.rolename}">
					${userRole.value.role.rolename}
				</td>
				
				<td>
					<a href="${pageContext.request.contextPath}/userRole/updateUserRole.do?userRoleId=${userRole.userRoleId}">修改</a>
					
					<a href="${pageContext.request.contextPath}/userRole/deleteUserRole.do?userRoleId=${userRole.value.userRoleId }">删除</a>
				</td>
			</tr>
		</c:forEach>
	</c:if>
</table>

<input type="button" value="添加" onclick="insertUserRole()">
 --%>

<!-- 查询的工具栏 -->
<div id="toolbar"  class="datagrid-toolbar" data-options="">
   <form id="searchForm">
   		<table>
   			<tr>
   				<td colspan="2">用户名：<input name="username" type="text"></td>
   				<td colspan="2">角色名：<input name="rolename" type="text"></td>
   				<td colspan="2">
   					<a onclick="javascript:searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
   				</td>
   				<td>
   					<a onclick="javascript:cleanSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
   				</td>
   			</tr>
   			<tr>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:insertUserRole()">增加</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:deleteUserRole()">删除</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:updateUserRole()">修改</a></td>
   				<td></td>
   			</tr>
   		</table>
   		
   </form>
</div>


<!-- 显示数据的div -->
<table id="listTable">
</table>



</body>
</html>

<script>

function searchList(){
	 $('#listTable').datagrid('load',{
		 username:$('#searchForm [name=username]').val(),
		 rolename:$('#searchForm [name=rolename]').val()
	 });
}

function cleanSearch(){
	$('#listTable').datagrid('load',{});
	//$('#searchForm').find('input').val('');
	$('#searchForm input').val('');
}

/**
 * 显示用户角色
 */
$(function(){
	$('#listTable').datagrid({
		url: '${pageContext.request.contextPath}/userRole/queryAllUserRoleAction.do',
		fit : true,
		title:'用户角色管理',
		fitColumns:true,
		border:false,
		//sortName:'userRolename',//field
		pagination:true,
		pageSize:5,
		pageList:[5,10,20,50],
		columns:[[  
		          {field:'userRoleId',title:'用户角色id',checkbox:true,width:100},  
		          {field:'userid',title:'用户名',sortable:true,width:100,
		        	  formatter: function (value, row,index) {
		        		  return row.user.username;
	                  }
		          },  
		          {field:'roleid',title:'角色名',width:100,
		        	  formatter: function (value,row,index) {
	                      return row.role.rolename;
	                  }  
		          }
		      ]],
		toolbar:'#toolbar'
		/* toolbar:[{
		        	text:'增加',
		        	iconCls:'icon-add',
		        	handler:function(){
		        		insertUserRole();
			        }
		        },{
		        	text:'删除',
		        	iconCls:'icon-remove',
		        	handler:function(){
		        		deleteUserRole();
			        }
		        }/* ,{
		        	text:'修改',
		        	iconCls:'icon-edit',
		        	handler:function(){
		        		updateUserRole();
			        }
		        } ] */
		
	});
	
});

/*
 * 添加用户角色
 */
function insertUserRole(){
	window.location.href = "${pageContext.request.contextPath}/userRole/insertUserRole.do";
	/* $('#insertDiv').dialog({  
		closed:false,
	    modal:true,
	    buttons:[{
	    	text:'保存',
        	iconCls:'icon-save',
        	handler:function(){
        		$('#insertForm').submit();
	        }
	    },{
	    	text:'取消',
        	iconCls:'icon-cancel',
        	handler:function(){
        		$('#insertDiv').dialog('close');
	        }
	    }]
	});  */
}

/**
 * 编辑用户角色
 */
/*  
function updateUserRole(){
	var userRole = $('#listTable').datagrid('getSelected');//获取到选择的数据
	$('#updateForm input[name=userRoleid]').val(userRole.userRoleid);
	$('#updateForm input[name=userRolename]').val(userRole.userRolename);
	$('#updateForm input[name=password]').val(userRole.password);
	
	$('#updateDiv').dialog({
		closed:false,
	    modal:true,
	    buttons:[{
	    	text:'保存',
        	iconCls:'icon-save',
        	handler:function(){
        		$('#updateForm').submit();
	        }
	    },{
	    	text:'取消',
        	iconCls:'icon-cancel',
        	handler:function(){
        		$('#updateDiv').dialog('close');
	        }
	    }]
	});
}
 */
/**
 *删除用户角色
 */
 function deleteUserRole(){
	
	var rows = $('#listTable').datagrid('getSelections');
	//console.info(getSelections);
	if(rows.length>0){
		$.messager.confirm('确认提示','您确定要删除选择的记录？',function(r){  
		    if (r){  
		        var userRoleids = [];
		        for(var i = 0; i < rows.length; i++){
		        	userRoleids.push(rows[i].userRoleId);
		        }
		        console.info(userRoleids);
		        $.ajax({
		        	   url: "${pageContext.request.contextPath}/userRole/deleteUserRolesById.do",
		        	   data: "userRoleids="+userRoleids,
		        	   type:"POST",
		        	   success: function(msg){
		        		   $('#listTable').datagrid('reload');//刷新当前页
		        	   }
		        	 });
		    }  
		});  
	}else{
		
	}
}
</script>