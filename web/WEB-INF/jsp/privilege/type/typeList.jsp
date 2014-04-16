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
<%-- <table>
	<tr>
		<td>操作类型id</td>
		<td>类型名</td>
		<td>备注</td>
		<td>操作</td>
	</tr>

	<c:if test="${empty typeList }">
		<div id="tips_bar">
			<div class="nomail"><b>当前没有数据</b></div>
		</div>
	</c:if>
	<c:if test="${not empty typeList }">
		<c:forEach items="${typeList}" var="type">
			<tr style="height: 20px;">
				<td width="10%" title="${vs.index+1 }">${vs.index+1 }</td>
				<td title="${type.value.typeid }">
					${type.value.typeid}
				</td>
				
				<td title="${type.value.typename }">
					${type.value.typename}
				</td>
				
				<td title="${type.value.common }">
					${type.value.common}
				</td>
				
				
				<td>
					<a href="${pageContext.request.contextPath}/type/updateType.do?typeid=${type.value.typeid}">修改</a>
					<a href="${pageContext.request.contextPath}/type/updateType.do?typeid=${type.value.typeid}">修改</a>
					&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/type/deleteType.do?typeid=${type.value.typeid}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</c:if>
</table>

<input type="button" value="添加" onclick="insertType()"> --%>

<!-- 查询的工具栏 -->
<div id="toolbar"  class="datagrid-toolbar" data-options="">
   <form id="searchForm">
   		<table>
   			<tr>
   				<td colspan="2">操作类型名：<input name="typename" type="text"></td>
   				<td colspan="2">
   					<a onclick="javascript:searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
   				</td>
   				<td>
   					<a onclick="javascript:cleanSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
   				</td>
   			</tr>
   			<tr>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:insertType()">增加</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:deleteType()">删除</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:updateType()">修改</a></td>
   				<td></td>
   			</tr>
   		</table>
   		
   </form>
</div>



<!-- 显示数据的div -->
<table id="listTable">
</table>



<!-- 添加的div -->
<div class="easyui-dialog" closed=true id="insertDiv" title="添加操作类型" style="width:400px;height:200px;">  
	<form id="insertForm" action="${pageContext.request.contextPath}/type/insertTypeAction.do" method="post">
		<table>
			<tr>
				<td>操作类型id：</td>
				<td><input type="text" name="typeid">  </td>
			</tr>
			<tr>
				<td>操作类型名：</td>
				<td><input type="text" name="typename">  </td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><input type="text" name="common"></td>
			</tr>
		</table>
	</form>
</div>


<!-- 修改信息div -->
<div class="easyui-dialog" closed=true  id="updateDiv" title="编辑用户" style="width:400px;height:200px;">
	<form id="updateForm" action="${pageContext.request.contextPath}/type/updateTypeAction.do" method="post">
		<table>
			<tr style="height: 20px;">
				<td>操作类型id</td>
				<td title="${type.typeid }">
					<input name="typeid" type="text" value="${type.typeid}" readonly="readonly">
				</td>				
			</tr>
			
			<tr style="height: 20px;">
				<td>操作类型名</td>
				<td title="${type.typename}">
					<input name="typename" type="text" value="${type.typename}" >
				</td>
			</tr>
			
			<tr style="height: 20px;">
				<td>备注</td>
				<td title="${type.common }">
					<input name="common" type="text" value="${type.common}">
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
		 typename:$('#searchForm [name=typename]').val()
	 });
}

function cleanSearch(){
	$('#listTable').datagrid('load',{});
	//$('#searchForm').find('input').val('');
	$('#searchForm input').val('');
}

/**
 * 显示操作类型
 */
$(function(){
	$('#listTable').datagrid({
		url: '${pageContext.request.contextPath}/type/queryAllTypeAction.do',
		fit : true,
		title:'操作类型管理',
		fitColumns:true,
		border:false,
		//sortName:'typename',//field
		pagination:true,
		pageSize:5,
		pageList:[5,10,20,50],
		columns:[[  
		          {field:'typeid',title:'操作类型id',checkbox:true,width:100},
		          {field:'typeid2',title:'操作类型id',width:100,
		        	  formatter: function (value,row,index) {
	        		  return row.typeid;}
		          },
		          {field:'typename',title:'操作类型名',sortable:true,width:100},  
		          {field:'common',title:'备注',width:100}
		      ]],
		toolbar:'#toolbar'
		/* toolbar:[{
		        	text:'增加',
		        	iconCls:'icon-add',
		        	handler:function(){
		        		insertType();
			        }
		        },{
		        	text:'删除',
		        	iconCls:'icon-remove',
		        	handler:function(){
		        		deleteType();
			        }
		        },{
		        	text:'修改',
		        	iconCls:'icon-edit',
		        	handler:function(){
		        		updateType();
			        }
		        }] */
		
	});
	
});

/*
 * 添加操作类型
 */
function insertType(){
	//window.location.href = "${pageContext.request.contextPath}/type/insertType.do";
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
	        var typename = $('#insertForm input[name=typename]');
	        //console.info(rolename);
	        if(queryCountByObj(typename)>0){
	        	//('#insertUsername').validatebox().
	        	$.messager.show({
					title:'提示信息',
					msg:'操作类型已存在',
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
					msg:'添加操作类型成功',
					timeout:2000,
					showType:'slide'
				});
	    	}else{//("addError" == msg)
	    		$('#insertDiv').dialog('close');
	    		$.messager.show({
					title:'提示信息',
					msg:'添加操作类型失败',
					timeout:2000,
					showType:'slide'
				});
	    	}
	        
	    }
	});
}

/**
 * 编辑操作类型
 */
function updateType(){
	var type = $('#listTable').datagrid('getSelected');//获取到选择的数据
	$('#updateForm input[name=typeid]').val(type.typeid);
	$('#updateForm input[name=typename]').val(type.typename);
	$('#updateForm input[name=common]').val(type.common);
	
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
	        var typename = $('#updateForm input[name=typename]');
	        if(queryCountByObj(typename)>0){
	        	//('#insertUsername').validatebox().
	        	$.messager.show({
					title:'提示信息',
					msg:'操作类型已存在',
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
					msg:'修改操作类型成功',
					timeout:2000,
					showType:'slide'
				});
	    	}else{// if("updateError" == msg)
	    		$('#updateDiv').dialog('close');
	    		$.messager.show({
					title:'提示信息',
					msg:'修改操作类型失败',
					timeout:2000,
					showType:'slide'
				});
	    	}
	        
	    }  
	});
	
	
}

/**
 *删除操作类型
 */
 function deleteType(){
	
	var rows = $('#listTable').datagrid('getSelections');
	//console.info(getSelections);
	if(rows.length>0){
		$.messager.confirm('确认提示','您确定要删除选择的记录？',function(r){  
		    if (r){  
		        var typeids = [];
		        for(var i = 0; i < rows.length; i++){
		        	typeids.push(rows[i].typeid);
		        }
		        //console.info(typeids);
		        $.ajax({
		        	   url: "${pageContext.request.contextPath}/type/deleteTypesById.do",
		        	   data: "typeids="+typeids,
		        	   type:"POST",
		        	   success: function(msg){
		        		   //$('#listTable').datagrid('reload');//刷新当前页
		        		   if("isUsed" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'选择的操作类型被权限使用，禁止删除',
		        					timeout:2000,
		        					showType:'slide'
		        				});
		        		   }else if("delError" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'删除操作类型失败',
		        					timeout:2000,
		        					showType:'slide'
		        				});
		        		   }else if("delSuc" == msg){
		        			   $.messager.show({
		        					title:'提示信息',
		        					msg:'删除操作类型成功',
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
 * 通过对象查询
 */
 function queryCountByObj(obj){
	 var typename = $(obj).val();
		var count=0;//默认不存在
		$.ajax({
 		type:'POST',
 		async:false,
 		dateType:'json',
 		url:"${pageContext.request.contextPath}/type/queryCountByObj.do",
 		data:'typename='+typename,
 		success:function(msg){
				//console.info(msg);
				//console.info(msg>0);
 			count = msg;
 		}
		});
	  return count;//返回。数量
}
</script>

</body>
</html>