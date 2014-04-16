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
<div class="easyui-panel" fit=true border=false>
<table id="listTable">
	<!--  <thead>   
        <tr>   
            <th>用户id</th>   
            <th>用户名</th>   
            <th>用户密码</th>   
            <th data-options="field:'action'">操作</th>   
        </tr>   
    </thead>  -->

	<tbody>   
       <c:if test="${empty userList }">
			<tr><td>当前没有数据</td></tr>
		</c:if>
		<c:if test="${not empty userList }">
			<c:forEach items="${userList}" var="user">
				<tr>
					<td title="${user.value.userid }">
						${user.value.userid}
					</td>
					<td title="${user.value.username }">
						${user.value.username}
					</td>
					
					<td title="${user.value.password }">
						${user.value.password}
					</td>
					
					
					<td>
						<a href="${pageContext.request.contextPath}/user/updateUser.do?userid=${user.userid}">修改</a>
						<a href="${pageContext.request.contextPath}/user/updateUser.do?userid=${user.value.userid}">修改</a>
						&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/user/deleteUser.do?userid=${user.value.userid}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
    </tbody> 
 </table>
 </div> --%>

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
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:insertUser()">增加</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:deleteUser()">删除</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:updateUser()">修改</a></td>
   				<td></td>
   			</tr>
   		</table>
   		
   </form>
</div>

 
 
<!-- 显示用户的div -->
<table id="listTable">
</table>

<!-- <input type="button" value="添加" onclick="insertUser()">
 -->

<!-- 添加用户的div -->
<div class="easyui-dialog" closed=true id="insertDiv" title="添加用户" style="width:400px;height:200px;">  
     <form id="insertForm" action="${pageContext.request.contextPath}/user/insertUserAction.do" method="post">
		<table style="text-align: right;">
			<tr>
				<td>用户名：</td>
				<td><input id="insertUsername" type="text" name="username" onblur="queryCountByObj(this)" class="easyui-validatebox" data-options="missingMessage:'用户名不能为空',required:true">  </td>
			</tr>
			
			<tr>
				<td>密码：</td>
				<td><input id="insertPwd" type="password" name="password" class="easyui-validatebox"></td>
			</tr>
			<tr>
				<td>重复密码：</td>
				<td><input id="ReInsertPwd" type="password" class="easyui-validatebox" validType="equals['#insertPwd']"></td>
			</tr>
		</table>
	</form>
</div>

<!-- 修改用户信息div -->
<div class="easyui-dialog" closed=true  id="userUpdateDiv" title="编辑用户" style="width:400px;height:200px;">
	<form id="userUpdateForm" action="${pageContext.request.contextPath}/user/updateUserAction.do" method="post">
		<table id="userUpdateTable" style="text-align: right;">
					<tr style="height: 20px;">
						<td>用户id</td>
						<td title="${user.userid}">
							<input name="userid" type="text" value="${user.userid}" readonly="readonly">
						</td>				
					</tr>
					
					<tr style="height: 20px;">
						<td>用户名</td>
						<td title="${user.username}">
							<input onblur="queryCountByObj(this)" name="username" type="text" value="${user.username}" class="easyui-validatebox" data-options="missingMessage:'用户名不能为空',required:true">
						</td>
					</tr>
					
					<tr style="height: 20px;display: none;">
						<td>用户旧密码</td>
						<td title="${user.password }">
							<input name="password" type="password" value="${user.password}">
						</td>
					</tr>
					<%-- 
					<tr style="height: 20px;">
						<td>用户新密码</td>
						<td>
							<input id="NewPassword" name="NewPassword" type="password" >
						</td>
					</tr>
					
					<tr style="height: 20px;">
						<td>重复新密码</td>
						<td>
							<input id="ReNewPassword" name="ReNewPassword" type="password" class="easyui-validatebox" validType="equals['#NewPassword']">
						</td>
					</tr> --%>
		</table>
	</form>
</div>



</body>



</html>

<script type="text/javascript">

/**
 * 查询
 */
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
 * 显示用户
 */
$(function(){
	//$('#userUpdateDiv').dialog('destroy');
	$('#listTable').datagrid({
		url: '${pageContext.request.contextPath}/user/queryAllUserAction.do',
		fit : true,
		title:'用户管理',
		fitColumns:true,
		border:false,
		sortName:'username',//field
		pagination:true,
		pageSize:5,
		pageList:[5,10,20,50],
		columns:[[  
		          {field:'userid',title:'用户id',checkbox:true,width:100},  
		          {field:'username',title:'用户名',sortable:true,width:100}/* ,  
		          {field:'password',title:'用户密码',width:100} */
		      ]],
		toolbar:'#toolbar'
		/* toolbar:[{
		        	text:'增加',
		        	iconCls:'icon-add',
		        	handler:function(){
		        		insertUser();
			        }
		        },{
		        	text:'删除',
		        	iconCls:'icon-remove',
		        	handler:function(){
		        		deleteUser();
			        }
		        },{
		        	text:'修改',
		        	iconCls:'icon-edit',
		        	handler:function(){
		        		updateUser();
			        }
		        }] */
		
	});
});


//自定义验证的方法
$(function (){
	$.extend($.fn.validatebox.defaults.rules, {  
	    equals: {  
	        validator: function(value,param){
	            return value == $(param[0]).val();  
	        },  
	        message: '请再次输入相同的密码'  
	    }/* ,msg:{
	    	validator:function(value,param){
	    		$.fn.validatebox.defaults.rules.msg.message = param[0];
	    		return true;
	        },  
	        message: '该用户名已存在aaaaaa'
	    } */
	
	/* ,
	    ifExit:{
	    	validator: function(value,param){
	            //return value == $(param[0]).val();  
	    		var bl=true;//默认不存在
	    		$.ajax({
		    		type:'POST',
		    		async:false,
		    		dateType:'json',
		    		url:param[0],
		    		data:'username='+value,
		    		success:function(msg){
//		    			bl=result.valiEmp;
						if("isUsed" == msg){//数据库已经存在
							bl = false;
						}else{
							bl = true;
						}
		    		}
	    		});
	    		return bl;//返回。true表示验证通过（数据库不存在）
	        },  
	        message: '该用户名已存在'
	    } */
	}); 
	
});

/*
 * 添加用户
 */
function insertUser(){
	
	//window.location.href = "${pageContext.request.contextPath}/user/insertUser.do";
	$('#insertForm input').val("");//由于添加成功，没有跳转页面。所以需要手动清空上次添加的值
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
	
	//提交之前表单验证
	$('#insertForm').form({
	    onSubmit: function(){  
	        //进行表单验证  
	        //如果返回false阻止提交 
	        var username = $('#insertUsername');
	        if(queryCountByObj(username)>0){
	        	//('#insertUsername').validatebox().
	        	$.messager.show({
					title:'提示信息',
					msg:'用户名已存在',
					timeout:2000,
					showType:'slide'
				});
	        	return false;
	        }
	        var insertPwd = $('#insertPwd').val();
	        var ReInsertPwd = $('#ReInsertPwd').val();
	        
	        if((insertPwd == ReInsertPwd)){//并且验证用户名不重复
	        	//给提示
		       	return true;
	        }
	        return false;
	        
	    },  
	    success:function(msg){
	    	//console.info(msg);
	    	if("addSuc" == msg){
	    		$('#insertDiv').dialog('close');
		        $('#listTable').datagrid('reload');//刷新当前页
		        $.messager.show({
					title:'提示信息',
					msg:'添加用户成功',
					timeout:2000,
					showType:'slide'
				});
	    	}else{//"addError" == msg
	    		$('#insertDiv').dialog('close');
	    		$.messager.show({
					title:'提示信息',
					msg:'添加用户失败',
					timeout:2000,
					showType:'slide'
				});
	    	}
	        
	    }  
	});
	
}

/**
 * 编辑用户
 */
function updateUser(){
	var user = $('#listTable').datagrid('getSelected');//获取到选择的数据
	//console.info(user);
	$('#userUpdateForm input[name=userid]').val(user.userid);
	$('#userUpdateForm input[name=username]').val(user.username);
	$('#userUpdateForm input[name=password]').val(user.password);
	
	$('#userUpdateDiv').dialog({
		closed:false,
	    modal:true,
	    buttons:[{
	    	text:'保存',
        	iconCls:'icon-save',
        	handler:function(){
        		$('#userUpdateForm').submit();
	        }
	    },{
	    	text:'取消',
        	iconCls:'icon-cancel',
        	handler:function(){
        		$('#userUpdateDiv').dialog('close');
	        }
	    }]
	});
	
	$('#userUpdateForm').form({
		onSubmit: function(){  
	        //进行表单验证  
	        //如果返回false阻止提交 
	        var username = $('#userUpdateForm input[name=username]');
	        if(queryCountByObj(username)>0){
	        	//('#insertUsername').validatebox().
	        	$.messager.show({
					title:'提示信息',
					msg:'用户名已存在',
					timeout:2000,
					showType:'slide'
				});
	        	return false;
	        }
	        
	    },
	    success:function(msg){
	    	//console.info(msg);
	    	if("updateSuc" == msg){
	    		$('#userUpdateDiv').dialog('close');
		        $('#listTable').datagrid('reload');//刷新当前页
		        $.messager.show({
					title:'提示信息',
					msg:'修改用户信息成功',
					timeout:2000,
					showType:'slide'
				});
	    	}else{// if("updateError" == msg)
	    		$('#userUpdateDiv').dialog('close');
	    		$.messager.show({
					title:'提示信息',
					msg:'修改用户信息失败',
					timeout:2000,
					showType:'slide'
				});
	    	}
	        
	    }
	});
}

/**
 *删除用户
 */
 function deleteUser(){
	
	var rows = $('#listTable').datagrid('getSelections');
	//console.info(getSelections);
	if(rows.length>0){
		$.messager.confirm('确认提示','您确定要删除选择的记录？',function(r){  
		    if (r){  
		        var userids = [];
		        for(var i = 0; i < rows.length; i++){
		        	userids.push(rows[i].userid);
		        }
		        //console.info(userids);
		        $.ajax({
		        	   url: "${pageContext.request.contextPath}/user/deleteUsersById.do",
		        	   data: "userids="+userids,//userids会自动变成字符串。后台request.getParam可以获取到  "xxx","xxx",""
		        	   type:"POST",
		        	   success: function(msg){
		        		   //$('#listTable').datagrid('reload');//刷新当前页
		        		   if("isUsed" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'选择的用户已开通权限或角色，禁止删除',
		        					timeout:2000,
		        					showType:'slide'
		        				});
		        		   }else if("delError" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'删除用户失败',
		        					timeout:2000,
		        					showType:'slide'
		        				});
		        		   }else if("delSuc" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'删除用户成功',
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
 * 验证用户名是否存在
 */
 function queryCountByObj(obj){
	var username = $(obj).val();
	var count=0;//默认不存在
   		$.ajax({
    		type:'POST',
    		async:false,
    		dateType:'json',
    		url:"${pageContext.request.contextPath}/user/queryCountByObj.do",
    		data:'username='+username,
    		success:function(msg){
				//console.info(msg);
				//console.info(msg>0);
    			count = msg;
    		}
   		});
   		return count;//返回。true表示验证通过（数据库不存在）
}
</script>