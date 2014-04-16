<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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


<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<%-- 
<table>
	<tr>
		<td>权限id</td>
		<td>资源id</td>
		<td>资源名</td>
		<td>操作类型</td>
		<td>value</td>
		<td>操作</td>
	</tr>

	<c:if test="${empty privilegeList }">
		<div id="tips_bar">
			<div class="nomail"><b>当前没有数据</b></div>
		</div>
	</c:if>
	<c:if test="${not empty privilegeList }">
		<c:forEach items="${privilegeList}" var="privilege">
			<tr style="height: 20px;">
				<td width="10%" title="${vs.index+1 }">${vs.index+1 }</td>
				<td title="${privilege.value.privilegeid }">
					${privilege.value.privilegeid}
				</td>
				
				<td title="${privilege.value.resid}">
					${privilege.value.resid}
				</td>
				
				
				<td title="${privilege.value.resource.resname}">
					${privilege.value.resource.resname}
				</td>
				
				<td title="${privilege.value.actionType}">
					${privilege.value.actionType}
				</td>
				<td title="${privilege.value.type.typename}">
					${privilege.value.type.typename}
				</td>
				
				<td title="${privilege.value.actionValue}">
					${privilege.value.actionValue}
				</td>
				
				
				<td>
					<a href="${pageContext.request.contextPath}/privilege/updatePrivilege.do?privilegeid=${privilege.privilegeid}">修改</a>
					<a href="${pageContext.request.contextPath}/privilege/updatePrivilege.do?privilegeid=${privilege.value.privilegeid}">修改</a>
					&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/privilege/deletePrivilege.do?privilegeid=${privilege.value.privilegeid}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</c:if>
</table>

<input type="button" value="添加" onclick="insertPrivilege()">
 --%>


<!-- 查询的工具栏 -->
<div id="toolbar"  class="datagrid-toolbar" data-options="">
   <form id="searchForm">
   		<table>
   			<tr>
   				<td colspan="2">资源名：<input name="resname" type="text"></td>
   				<td colspan="2">操作类型：<input name="typename" type="text"></td>
   				<td colspan="2">
   					<a onclick="javascript:searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
   				</td>
   				<td>
   					<a onclick="javascript:cleanSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
   				</td>
   			</tr>
   			<tr>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:insertPrivilege()">增加</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:deletePrivilege()">删除</a></td>
   				<td></td>
   			</tr>
   		</table>
   		
   </form>
</div>


<!-- 显示数据的div -->
<table id="listTable">

</table>

<!-- 用户获取用户的操作提示信息 -->
<input type="hidden" name="infoMsg" id="infoMsg" type="text" value="${infoMsg}">

</body>
</html>

<script>
function insertPrivilege(){
	window.location.href = "${pageContext.request.contextPath}/privilege/insertPrivilege.do";
}


function searchList(){
	 $('#listTable').datagrid('load',{
		 resname:$('#searchForm [name=resname]').val(),
		 typename:$('#searchForm [name=typename]').val()
	 });
}

function cleanSearch(){
	$('#listTable').datagrid('load',{});
	//$('#searchForm').find('input').val('');
	$('#searchForm input').val('');
}

/**
 * 显示权限信息
 */
$(function(){
	$('#listTable').datagrid({
		url: '${pageContext.request.contextPath}/privilege/queryAllPrivilegeAction.do',
		fit : true,
		title:'权限管理',
		fitColumns:true,
		border:false,
		//sortName:'resname',//field
		pagination:true,
		pageSize:5,
		pageList:[5,10,20,50],
		columns:[[  
		          {field:'privilegeid',title:'权限id',checkbox:true,width:100},  
		          {field:'resname',title:'资源名',width:100,// sortable:true,
		        	  formatter: function (value, row,index) {
		        		  return row.resource.resname;
	                  }
		          },  
		          {field:'actionType',title:'操作类型',width:100,
		        	  formatter: function (value, row,index) {
		        		  return row.type.typename;
	                  }  
		          },
		          {field:'actionValue',title:'操作值',width:100}
		      ]],
		      toolbar:'#toolbar'    
		/* toolbar:[{
		        	text:'增加',
		        	iconCls:'icon-add',
		        	handler:function(){
		        		insertPrivilege();
			        }
		        },{
		        	text:'删除',
		        	iconCls:'icon-remove',
		        	handler:function(){
		        		deletePrivilege();
			        }
		        }/* ,{
		        	text:'修改',
		        	iconCls:'icon-edit',
		        	handler:function(){
		        		updatePrivilege();
			        }
		        } ] */
		
	});
	
});

/**
 * 删除权限
 */
function deletePrivilege(){
	var rows = $('#listTable').datagrid('getSelections');
	//console.info(getSelections);
	if(rows.length>0){
		$.messager.confirm('确认提示','您确定要删除选择的记录？',function(r){  
		    if (r){  
		        var privilegeids = [];
		        for(var i = 0; i < rows.length; i++){
		        	privilegeids.push(rows[i].privilegeid);
		        }
		        //console.info(privilegeids);
		        $.ajax({
		        	   url: "${pageContext.request.contextPath}/privilege/deletePrivilegesById.do",
		        	   data: "privilegeids="+privilegeids,
		        	   type:"POST",
		        	   success: function(msg){
		        		   if("isUsed" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'选择的权限被用户使用，禁止删除',
		        					timeout:2000,
		        					showType:'slide'
		        				});
		        		   }else if("delError" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'删除权限失败',
		        					timeout:2000,
		        					showType:'slide'
		        				});
		        		   }else if("delSuc" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'删除权限成功',
		        					timeout:2000,
		        					showType:'slide'
		        				});
		        			   $('#listTable').datagrid('reload');//刷新当前页
		        		   }
		        		   
		        	   }
		        	 });
		    }  
		});  
	}else{
		
	}
}

/**
 * 批量修改权限
 */
 function updatePrivilege(){
	console.info("批量修改");
}
</script>