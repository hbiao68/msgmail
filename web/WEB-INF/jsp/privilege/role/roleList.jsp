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
		<td>角色id</td>
		<td>角色名称</td>
		<td>操作</td>
	</tr>
	
	<c:if test="${empty roleList }">
		<div id="tips_bar">
			<div class="nomail"><b>当前没有数据</b></div>
		</div>
	</c:if>
	<c:if test="${not empty roleList }">
		<c:forEach items="${roleList}" var="role">
			<tr style="height: 20px;">
				<td width="10%" title="${vs.index+1 }">${vs.index+1 }</td>
				<td title="${role.value.roleid }">
					${role.value.roleid}
				</td>
				
				<td title="${role.value.rolename }">
					${role.value.rolename}
				</td>
				
				<td>
					<a href="${pageContext.request.contextPath}/user/updaterole.do?roleid=${role.roleid}">修改</a>
					<a href="${pageContext.request.contextPath}/role/updateRole.do?roleid=${role.value.roleid}">修改</a>
					&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/role/deleteRole.do?roleid=${role.value.roleid}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</c:if>
</table>

<input type="button" value="添加" onclick="insertRole()">
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
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:insertRole()">增加</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:deleteRole()">删除</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:updateRole()">修改</a></td>
   				<td></td>
   			</tr>
   		</table>
   		
   </form>
</div>


<!-- 显示的div -->
<table id="listTable">
</table>


<!-- 添加的div -->
<div class="easyui-dialog" closed=true id="insertDiv" title="添加角色" style="width:400px;height:200px;">  
     <form id="insertForm" action="${pageContext.request.contextPath}/role/insertRoleAction.do" method="post">
		<table style="text-align: right;">
			<tr>
				<td>角色名：</td>
				<td><input type="text" name="rolename" onblur="queryCountByObj(this)"></td>
			</tr>
		</table>
	</form>
</div>


<!-- 修改信息div -->
<div class="easyui-dialog" closed=true  id="updateDiv" title="编辑角色" style="width:400px;height:200px;">
	<form id="updateForm" action="${pageContext.request.contextPath}/role/updateRoleAction.do" method="post">
		<table>
			<tr style="height: 20px;">
				<td>角色id</td>
				<td title="${role.roleid }">
					<input name="roleid" type="text" value="${role.roleid}" readonly="readonly">
				</td>
			</tr>
			
			<tr style="height: 20px;">
				<td>角色名称</td>
				<td title="${role.rolename}">
					<input name="rolename" type="text" value="${role.rolename}" >
				</td>
				
			</tr>
		</table>
	</form>
</div>

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
 * 初始化数据
 */
 $(function(){
		$('#listTable').datagrid({
			url: '${pageContext.request.contextPath}/role/queryAllRoleAction.do',
			fit : true,
			title:'角色管理',
			fitColumns:true,
			border:false,
			sortName:'rolename',//field
			pagination:true,
			pageSize:5,
			pageList:[5,10,20,50],
			columns:[[  
			          {field:'roleid',title:'角色id',checkbox:true,width:100},  
			          {field:'rolename',title:'角色名',sortable:true,width:100},
			      ]],
			      toolbar:'#toolbar'
			/* toolbar:[{
			        	text:'增加',
			        	iconCls:'icon-add',
			        	handler:function(){
			        		insertRole();
				        }
			        },{
			        	text:'删除',
			        	iconCls:'icon-remove',
			        	handler:function(){
			        		deleteRole();
				        }
			        },{
			        	text:'修改',
			        	iconCls:'icon-edit',
			        	handler:function(){
			        		updateRole();
				        }
			        }] */
			
		});
		
	});


/**
 * 添加角色
 */
function insertRole(){
	//window.location.href = "${pageContext.request.contextPath}/role/insertRole.do";
	$('#insertForm input').val("");
	$('#insertDiv').dialog({  
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
	}); 
	
	
	//添加的表单验证
	$('#insertForm').form({
		onSubmit: function(){  
	        //进行表单验证  
	        //如果返回false阻止提交 
	        var rolename = $('#insertForm input[name=rolename]');
	        //console.info(rolename);
	        if(queryCountByObj(rolename)>0){
	        	//('#insertUsername').validatebox().
	        	$.messager.show({
					title:'提示信息',
					msg:'角色名已存在',
					timeout:2000,
					showType:'slide'
				});
	        	return false;
	        }
	        
	    },  
	    success:function(msg){
	    	//console.info(msg);
	    	if("addSuc" == msg){
	    		$('#insertDiv').dialog('close');
		        $('#listTable').datagrid('reload');//刷新当前页
		        $.messager.show({
					title:'提示信息',
					msg:'添加角色成功',
					timeout:2000,
					showType:'slide'
				});
	    	}else{//("addError" == msg)
	    		$('#insertDiv').dialog('close');
	    		$.messager.show({
					title:'提示信息',
					msg:'添加角色失败',
					timeout:2000,
					showType:'slide'
				});
	    	}
	        
	    }
	});
}



/**
 * 编辑角色
 */
function updateRole(){
	var role = $('#listTable').datagrid('getSelected');//获取到选择的数据
	$('#updateForm input[name=roleid]').val(role.roleid);
	$('#updateForm input[name=rolename]').val(role.rolename);
	
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
	
	
	//编辑验证
	$('#updateForm').form({
	    onSubmit: function(){  
	        //进行表单验证  
	        //如果返回false阻止提交 
	        var rolename = $('#updateForm input[name=rolename]');
	        if(queryCountByObj(rolename)>0){
	        	//('#insertUsername').validatebox().
	        	$.messager.show({
					title:'提示信息',
					msg:'用角色已存在',
					timeout:2000,
					showType:'slide'
				});
	        	return false;
	        }
	        
	    },  
	    success:function(msg){
	    	//console.info(msg);
	    	if("updateSuc" == msg){
	    		$('#updateDiv').dialog('close');
		        $('#listTable').datagrid('reload');//刷新当前页
		        $.messager.show({
					title:'提示信息',
					msg:'修改角色成功',
					timeout:2000,
					showType:'slide'
				});
	    	}else{// if("updateError" == msg)
	    		$('#updateDiv').dialog('close');
	    		$.messager.show({
					title:'提示信息',
					msg:'修改角色失败',
					timeout:2000,
					showType:'slide'
				});
	    	}
	        
	    }  
	});
	
	
}



/**
 *删除角色
 */
 function deleteRole(){
	
	var rows = $('#listTable').datagrid('getSelections');
	//console.info(rows);
	if(rows.length>0){
		$.messager.confirm('确认提示','您确定要删除选择的记录？',function(r){  
		    if (r){  
		        var roleids = [];
		        for(var i = 0; i < rows.length; i++){
		        	roleids.push(rows[i].roleid);
		        }
		        //console.info(userids);
		        $.ajax({
		        	   url: "${pageContext.request.contextPath}/role/deleteRolesById.do",
		        	   data: "roleids="+roleids,
		        	   type:"POST",
		        	   success: function(msg){
		        		   //$('#listTable').datagrid('reload');//刷新当前页
		        		   if("isUsed" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'选择的角色已开通权限或者被用户使用，禁止删除',
		        					timeout:2000,
		        					showType:'slide'
		        				});
		        		   }else if("delError" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'删除角色失败',
		        					timeout:2000,
		        					showType:'slide'
		        				});
		        		   }else if("delSuc" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'删除角色成功',
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
 * 通过对象查询数量，用于修改和添加的时候验证
 */
 function queryCountByObj(obj){
	 var rolename = $(obj).val();
		var count=0;//默认不存在
   		$.ajax({
    		type:'POST',
    		async:false,
    		dateType:'json',
    		url:"${pageContext.request.contextPath}/role/queryCountByObj.do",
    		data:'rolename='+rolename,
    		success:function(msg){
				//console.info(msg);
				//console.info(msg>0);
    			count = msg;
    		}
   		});
	  return count;//返回。true表示验证通过（数据库不存在）
	
}
</script>
