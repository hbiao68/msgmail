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
		<td>资源id</td>
		<td>资源名称</td>
		<td>备注</td>
		<td>操作</td>
	</tr>
	
	<c:if test="${empty resList }">
		<div id="tips_bar">
			<div class="nomail"><b>当前没有数据</b></div>
		</div>
	</c:if>
	<c:if test="${not empty resList }">
		<c:forEach items="${resList}" var="res">
			<tr style="height: 20px;">
				<td width="10%" title="${vs.index+1 }">${vs.index+1 }</td>
				<td title="${res.value.resid }">
					${res.value.resid}
				</td>
				
				<td title="${res.value.resname }">
					${res.value.resname}
				</td>
				
				<td title="${res.value.commen}">
					${res.value.commen}
				</td>
				
				<td>
					<a href="${pageContext.request.contextPath}/res/updateres.do?resid=${res.resid}">修改</a>
					<a href="${pageContext.request.contextPath}/resource/updateRes.do?resid=${res.value.resid}">修改</a>
					&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/resource/deleteRes.do?resid=${res.value.resid}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</c:if>
</table>

<input type="button" value="添加" onclick="insertRes()">
 --%>

<div id="toolbar"  class="datagrid-toolbar" data-options="">
   <form id="searchForm">
   		<table>
   			<tr>
   				<td colspan="2">资源名：<input name="resname" type="text"></td>
   				<td colspan="2">
   					<a onclick="javascript:searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
   				</td>
   				<td>
   					<a onclick="javascript:cleanSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
   				</td>
   			</tr>
   			<tr>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:insertRes()">增加</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:deleteRes()">删除</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:updateRes()">修改</a></td>
   				<td></td>
   			</tr>
   		</table>
   		
   </form>
</div>



<!-- 显示数据的div -->
<table id="listTable">
</table>
 

<!-- 添加的div -->
<div class="easyui-dialog" closed=true id="insertDiv" title="添加资源" style="width:400px;height:200px;"> 
	<form id="insertForm" action="${pageContext.request.contextPath}/resource/resInsertAction.do" method="post">
		<table>
			<tr>
				<td>名称：</td>
				<td><input type="text" name="resname"></td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><input type="text" name="commen"></td>
			</tr>
		</table>
	</form>
</div>
 
 

<!-- 修改信息div -->
<div class="easyui-dialog" closed=true  id="updateDiv" title="编辑资源" style="width:400px;height:200px;">
	<form id="updateForm" action="${pageContext.request.contextPath}/resource/updateResAction.do" method="post">
		<table id="updateTable" style="text-align: right;">
			<tr style="height: 20px;">
				<td>资源id</td>
				<td title="${res.resid }">
					<input name="resid" type="text" value="${res.resid}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>资源名称</td>
				<td title="${res.resname}">
					<input name="resname" type="text" value="${res.resname}" >
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td title="${res.commen}">
					<input name="commen" type="text" value="${res.commen}" >
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
		 resname:$('#searchForm [name=resname]').val()
	 });
}

function cleanSearch(){
	$('#listTable').datagrid('load',{});
	//$('#searchForm').find('input').val('');
	$('#searchForm input').val('');
}


/**
 * 显示资源
 */
$(function(){
	$('#listTable').datagrid({
		url: '${pageContext.request.contextPath}/resource/queryAllResourceAction.do',
		fit : true,
		title:'资源管理',
		fitColumns:true,
		border:false,
		sortName:'resname',//field
		pagination:true,
		pageSize:5,
		pageList:[5,10,20,50],
		columns:[[  
		          {field:'resid',title:'资源id',checkbox:true,width:100},  
		          {field:'resname',title:'资源名',sortable:true,width:100},
		          {field:'commen',title:'备注',width:100},
		      ]],
		      toolbar:'#toolbar'
		/* toolbar:[{
		        	text:'增加',
		        	iconCls:'icon-add',
		        	handler:function(){
		        		insertRes();
			        }
		        },{
		        	text:'删除',
		        	iconCls:'icon-remove',
		        	handler:function(){
		        		deleteRes();
			        }
		        },{
		        	text:'修改',
		        	iconCls:'icon-edit',
		        	handler:function(){
		        		updateRes();
			        }
		        }] */
		
	});
	
});

/*
 * 添加资源
 */
function insertRes(){
	//window.location.href = "${pageContext.request.contextPath}/resource/insertRes.do";
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
	
	
	$('#insertForm').form({
		onSubmit: function(){  
	        //进行表单验证  
	        //如果返回false阻止提交 
	        var resname = $('#insertForm input[name=resname]');
	        //console.info(rolename);
	        if(queryCountByObj(resname)>0){
	        	//('#insertUsername').validatebox().
	        	$.messager.show({
					title:'提示信息',
					msg:'资源名已存在',
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
					msg:'添加资源成功',
					timeout:2000,
					showType:'slide'
				});
	    	}else{//("addError" == msg)
	    		$('#insertDiv').dialog('close');
	    		$.messager.show({
					title:'提示信息',
					msg:'添加资源失败',
					timeout:2000,
					showType:'slide'
				});
	    	}
	        
	    }
	});
}

/**
 * 编辑资源
 */
function updateRes(){
	var res = $('#listTable').datagrid('getSelected');//获取到选择的数据
	console.info(res);
	$('#updateForm input[name=resid]').val(res.resid);
	$('#updateForm input[name=resname]').val(res.resname);
	$('#updateForm input[name=commen]').val(res.commen);
	
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
	        var resname = $('#updateForm input[name=resname]');
	        if(queryCountByObj(resname)>0){
	        	//('#insertUsername').validatebox().
	        	$.messager.show({
					title:'提示信息',
					msg:'资源名已存在',
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
					msg:'修改资源成功',
					timeout:2000,
					showType:'slide'
				});
	    	}else{// if("updateError" == msg)
	    		$('#updateDiv').dialog('close');
	    		$.messager.show({
					title:'提示信息',
					msg:'修改资源失败',
					timeout:2000,
					showType:'slide'
				});
	    	}
	        
	    }  
	});
}

/**
 *删除资源
 */
 function deleteRes(){
	var rows = $('#listTable').datagrid('getSelections');
	//console.info(getSelections);
	if(rows.length>0){
		$.messager.confirm('确认提示','您确定要删除选择的记录？',function(r){  
		    if (r){  
		        var resids = [];
		        for(var i = 0; i < rows.length; i++){
		        	resids.push(rows[i].resid);
		        }
		        //console.info(resids);
		        $.ajax({
		        	   url: "${pageContext.request.contextPath}/resource/deleteRessById.do",
		        	   data: "resids="+resids,
		        	   type:"POST",
		        	   success: function(msg){
		        		   //$('#listTable').datagrid('reload');//刷新当前页
		        		   if("isUsed" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'选择的资源被用户使用，禁止删除',
		        					timeout:2000,
		        					showType:'slide'
		        				});
		        		   }else if("delError" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'删除资源失败',
		        					timeout:2000,
		        					showType:'slide'
		        				});
		        		   }else if("delSuc" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'删除资源成功',
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
 * 通过对象查询资源数量
 */
 function queryCountByObj(obj){
	 var resname = $(obj).val();
		var count=0;//默认不存在
		$.ajax({
 		type:'POST',
 		async:false,
 		dateType:'json',
 		url:"${pageContext.request.contextPath}/resource/queryCountByObj.do",
 		data:'resname='+resname,
 		success:function(msg){
				//console.info(msg);
				//console.info(msg>0);
 			count = msg;
 		}
		});
	  return count;//返回。true表示验证通过（数据库不存在）
}

</script>