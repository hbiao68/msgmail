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
		<!-- <td>用户、权限id		&nbsp;&nbsp;</td>
		<td>用户id 			&nbsp;&nbsp;</td> -->
		<td>用户名 			&nbsp;&nbsp;</td>
		
		<!-- <td>权限id			&nbsp;&nbsp;</td>
		<td>资源id			&nbsp;&nbsp;</td> -->
		
		<td>资源名			&nbsp;&nbsp;</td>
		<td>操作类型		&nbsp;&nbsp;</td>
		<td>action值			&nbsp;&nbsp;</td>
	</tr>

	<c:if test="${empty userPrivilegeList }">
		<div id="tips_bar">
			<div class="nomail"><b>当前没有数据</b></div>
		</div>
	</c:if>
	<c:if test="${not empty userPrivilegeList }">
		<c:forEach items="${userPrivilegeList}" var="userPlg">
			<tr style="height: 20px;">
				<td width="10%" title="${vs.index+1 }">${vs.index+1 }</td>
				<td title="${userPlg.value.userPlgId }">
					${userPlg.value.userPlgId}		&nbsp;&nbsp;
				</td>
				<!-- 用户信息 -->
				<td title="${userPlg.value.userid }">
					${userPlg.value.userid}	&nbsp;&nbsp;
				</td>
				
				<td title="${userPlg.value.user.username}">
					${userPlg.value.user.username}	&nbsp;&nbsp;
				</td>
				
				
				<!-- 权限信息 -->
				<td title="${userPlg.value.privilegeid }">
					${userPlg.value.privilegeid}	&nbsp;&nbsp;
				</td>
				<td title="${userPlg.value.privilege.resid }">
					${userPlg.value.privilege.resid}	&nbsp;&nbsp;
				</td>
				
				<!-- 通过权限获取资源信息 -->
				<td title="${userPlg.value.privilege.resource.resname }">
					${userPlg.value.privilege.resource.resname}	&nbsp;&nbsp;
				</td>
				<!-- 权限信息 -->
				<td title="${userPlg.value.privilege.type.typename }">
					${userPlg.value.privilege.type.typename}	&nbsp;&nbsp;
				</td>
				<td title="${userPlg.value.privilege.actionValue }">
					${userPlg.value.privilege.actionValue}	&nbsp;&nbsp;
				</td>
				
				<td>
					<a href="${pageContext.request.contextPath}/user/updateUserPrivilege.do?userid=${user.userid}">修改</a>
					 &nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/userPrivilege/deleteUserPrivilege.do?userPlgId=${userPlg.value.userPlgId}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</c:if>
</table>
<input type="button" value="添加" onclick="insertUserPrivilege()">
 --%>

<!-- 查询的工具栏 -->
<div id="toolbar"  class="datagrid-toolbar" data-options="">
   <form id="searchForm">
   		<table>
   			<tr>
   				<td colspan="2">用户名：<input name="username" type="text"></td>
   				<td colspan="2">
   					<a onclick="javascript:searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
   				</td>
   				<td>
   					<a onclick="javascript:cleanSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
   				</td>
   			</tr>
   			<tr>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:insertUserPrivilege()">增加</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:deleteUserPrivilege()">删除</a></td>
   				<td></td>
   			</tr>
   		</table>
   		
   </form>
</div>

<div fit="true" class="easyui-tabs" border=false >
	<div title="用户权限管理">
       	<!-- 显示数据的div -->
		<table id="listTable">
			
		</table>
    </div>  
	
	<div title="用户所有权限" closable="true">
     	<!--   显示数据的div -->
		<table id="listAllTable">
		
		</table>
    </div>

</div>
</body>
</html>

<script>

function searchList(){
	 $('#listTable').datagrid('load',{
		 username:$('#searchForm [name=username]').val()
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
		url: '${pageContext.request.contextPath}/userPrivilege/queryAllUserPrivilegeAction.do',
		fit : true,
		fitColumns:true,
		border:false,
		//sortName:'username',//field
		pagination:true,
		pageSize:5,
		pageList:[5,10,20,50],
		columns:[[  
		          {field:'userPlgId',title:'用户权限id',checkbox:true,width:100},  
		          {field:'userid.user.username',title:'用户名',width:100,
		        	  formatter: function (value,row,index) {
		        		  return row.user.username;
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
		        		insertUserPrivilege();
			        }
		        },{
		        	text:'删除',
		        	iconCls:'icon-remove',
		        	handler:function(){
		        		deleteUserPrivilege();
			        }
		        }/* ,{
		        	text:'修改',
		        	iconCls:'icon-edit',
		        	handler:function(){
		        		updateUserPrivilege();
			        }
		        }] */
		
	});
	
	
	
	/**
	 *用户所有权限
	 */
	$('#listAllTable').datagrid({
		url: '${pageContext.request.contextPath}/userPrivilege/queryAllUserRolePrivlgAction.do',
		fit : true,
		fitColumns:true,
		border:false,
		//sortName:'username',//field
		pagination:true,
		pageSize:5,
		pageList:[5,10,20,50],
		columns:[[  
		          {field:'userPlgId',title:'用户权限id',checkbox:true,width:100},  
		          {field:'userid.user.username',title:'用户名',width:100,
		        	  formatter: function (value,row,index) {
		        		  return row.user.username;
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
		      ]]
		
	});
	
	
});

/**
 * 添加用户权限
 */
function insertUserPrivilege(){
	window.location.href = "${pageContext.request.contextPath}/userPrivilege/insertUserPrivilege.do";
}

/**
 * 删除用户 权限数据
 */
 function deleteUserPrivilege(){
	
	 var rows = $('#listTable').datagrid('getSelections');
		//console.info(rows);
		if(rows.length>0){
			$.messager.confirm('确认提示','您确定要删除选择的记录？',function(r){  
			    if (r){  
			        var ids = [];
			        for(var i = 0; i < rows.length; i++){
			        	ids.push(rows[i].userPlgId);
			        }
			        //console.info(userids);
			        $.ajax({
			        	   url: "${pageContext.request.contextPath}/userPrivilege/deleteUserPrivlgsById.do",
			        	   data: "ids="+ids,
			        	   type:"POST",
			        	   success: function(msg){
			        		   $('#listTable').datagrid('reload');//刷新当前页
			        		   $('#listAllTable').datagrid('reload');//刷新当前页
			        	   }
			        	 });
			    }  
			});  
		}else{
			
		}
	
}

</script>