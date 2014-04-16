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
<%-- 
<table>
	<tr>
		<td>角色、权限主键		&nbsp;&nbsp;</td>
		<td>角色id 			&nbsp;&nbsp;</td>
		<td>角色名称 			&nbsp;&nbsp;</td>
		
		<td>权限id 			&nbsp;&nbsp;</td>
		<td>资源名 			&nbsp;&nbsp;</td>
		<td>操作类型 			&nbsp;&nbsp;</td>
		<td>actionValue 			&nbsp;&nbsp;</td>
	</tr>

	<c:if test="${empty rolePlgList }">
		<div id="tips_bar">
			<div class="nomail"><b>当前没有数据</b></div>
		</div>
	</c:if>
	<c:if test="${not empty rolePlgList }">
		<c:forEach items="${rolePlgList}" var="rolePlg">
			<tr style="height: 20px;">
				
				<td title="${rolePlg.value.rolePlgId}">
					${rolePlg.value.rolePlgId}	&nbsp;&nbsp;
				</td>
				<td title="${rolePlg.value.roleid }">
					${rolePlg.value.roleid}	&nbsp;&nbsp;
				</td>
				<td title="${rolePlg.value.role.rolename}">
					${rolePlg.value.role.rolename}	&nbsp;&nbsp;
				</td>
				
				<!-- 权限信息 -->
				<td title="${rolePlg.value.privilegeid}">
					${rolePlg.value.privilegeid}	&nbsp;&nbsp;
				</td>
				<td title="${rolePlg.value.privilege.resource.resname}">
					${rolePlg.value.privilege.resource.resname}	&nbsp;&nbsp;
				</td>
				<td title="${rolePlg.value.privilege.type.typename}">
					${rolePlg.value.privilege.type.typename}	&nbsp;&nbsp;
				</td>
				<td title="${rolePlg.value.privilege.actionValue}">
					${rolePlg.value.privilege.actionValue}	&nbsp;&nbsp;
				</td>
				
				<td>
					<a href="${pageContext.request.contextPath}/user/updateUser.do?userid=${user.userid}">修改</a>
					 &nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/rolePrivilege/deleteRolePrivilege.do?rolePlgId=${rolePlg.value.rolePlgId}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</c:if>
</table>

<input type="button" value="添加" onclick="insertRolePrivilege()"> 
--%>
<!-- 查询的工具栏 -->
<div id="toolbar"  class="datagrid-toolbar" data-options="">
   <form id="searchForm">
   		<table>
   			<tr>
   				<td colspan="2">角色名：<input name="rolename" type="text"></td>
   				<td colspan="2">
   					<a onclick="javascript:searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
   				</td>
   				<td>
   					<a onclick="javascript:cleanSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
   				</td>
   			</tr>
   			<tr>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:insertRolePrivilege()">增加</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:deleteRolePrivilege()">删除</a></td>
   				
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

/**
 * 搜索
 */
function searchList(){
	 $('#listTable').datagrid('load',{
		 rolename:$('#searchForm [name=rolename]').val()
	 });
}

function cleanSearch(){
	$('#listTable').datagrid('load',{});
	//$('#searchForm').find('input').val('');
	$('#searchForm input').val('');
}


/**
 * 显示用户权限数据
 */
$(function(){
	$('#listTable').datagrid({
		url: '${pageContext.request.contextPath}/rolePrivilege/queryAllRolePrivilegeAction.do',
		fit : true,
		fitColumns:true,
		border:false,
		title:"角色权限管理",
		//sortName:'username',//field
		pagination:true,
		pageSize:5,
		pageList:[5,10,20,50],
		columns:[[  
		          {field:'rolePlgId',title:'角色权限id',checkbox:true,width:100},  
		          {field:'role.rolename',title:'角色名',width:100,
		        	  formatter: function (value,row,index) {
		        		  return row.role.rolename;
	                  }
		          },  
		          {field:'privilege.resource.resname',title:'资源名',width:100,
		        	  formatter: function (value,row,index) {
		        		  return row.privilege.resource.resname;
	                  }  
		          },  
		          {field:'privilege.type.typename',title:'操作类型',width:100,
		        	  formatter: function (value,row,index) {
		        		  return row.privilege.type.typename;
	                  }  
		          },  
		          {field:'privilege.actionValue',title:'是否允许',iconCls:'icon-ok',width:100,
		        	  formatter: function (value,row,index) {
		        		  //return row.privilege.actionValue;
	                  },
	                  styler: function(value,row,index){
                	    if(row.privilege.actionValue == true){
                	    	return 'background-image:url(${pageContext.request.contextPath}/easyui/themes/icons/ok.png);background-repeat:no-repeat;';
                	    }else{
                	    	return 'background-image:url(${pageContext.request.contextPath}/easyui/themes/icons/no.png);background-repeat:no-repeat;';
                	    }
		      		  }
		          }
		      ]],
		      toolbar:'#toolbar'
		/* toolbar:[{
		        	text:'增加',
		        	iconCls:'icon-add',
		        	handler:function(){
		        		insertRolePrivilege();
			        }
		        },{
		        	text:'删除',
		        	iconCls:'icon-remove',
		        	handler:function(){
		        		deleteRolePrivilege();
			        }
		        }/* ,{
		        	text:'修改',
		        	iconCls:'icon-edit',
		        	handler:function(){
		        		updateRolePrivilege();
			        }
		        } ] */
		
	});
	
});

/**
 * 添加用户权限
 */
function insertRolePrivilege(){
	window.location.href = "${pageContext.request.contextPath}/rolePrivilege/insertRolePrivilege.do";
}

/**
 * 批量删除
 */
function deleteRolePrivilege(){
	var rows = $('#listTable').datagrid('getSelections');
	console.info(rows);
	if(rows.length>0){
		$.messager.confirm('确认提示','您确定要删除选择的记录？',function(r){  
		    if (r){  
		        var ids = [];
		        for(var i = 0; i < rows.length; i++){
		        	ids.push(rows[i].rolePlgId);
		        }
		        //console.info(userids);
		        $.ajax({
		        	   url: "${pageContext.request.contextPath}/rolePrivilege/deleteRolePrivlgsById.do",
		        	   data: "ids="+ids,
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