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

<!-- 查询的工具栏 -->
<div id="toolbar"  class="datagrid-toolbar" data-options="">
	<form id="searchForm">
   		<table>
   			<tr>
   				<td colspan="2">栏位名：<input name="columnName" type="text"></td>
   				<td colspan="2">栏位URL：<input name="columnURL" type="text"></td>
   				<td colspan="2">资源名：<input name="resname" type="text"></td>
   				<td colspan="2">
   					<a onclick="javascript:searchList()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
   				</td>
   				<td>
   					<a onclick="javascript:cleanSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
   				</td>
   			</tr>
   			<tr>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:insertColumn()">增加</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:deleteColumn()">删除</a></td>
   				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:updateColumn()">修改</a></td>
   				<td></td>
   			</tr>
   		</table>
   	</form>
</div>


<!-- 显示用户的div -->
<table id="listTable">
</table>

<!-- 添加栏位的div -->
<div class="easyui-dialog" closed=true id="insertDiv" title="添加栏位" style="width:400px;height:200px;">  
     <form id="insertForm" action="${pageContext.request.contextPath}/column/insertColumnAction.do" method="post">
		<table style="text-align: right;">
			<tr>
				<td>栏位名：</td>
				<td><input name="columnName" type="text" class="easyui-validatebox" data-options="missingMessage:'栏位名不能为空',required:true">  </td>
			</tr>
			
			<tr>
				<td>链接URL：</td>
				<td><input name="columnUrl" type="text" class="easyui-validatebox"></td>
			</tr>
			<tr>
				<td>隶属资源：</td>
				<td>
				<select name="resid">
					<%-- <c:if test="${not empty resMap }">
						<c:forEach items="${resMap}" var="res">
							<option value="${res.value.resid}">${res.value.resname}</option>
						</c:forEach>
					</c:if> --%>
				</select>
				</td>
			</tr>
			
		</table>
	</form>
</div>

<!-- 修改的div -->
<div class="easyui-dialog" closed=true id="updateDiv" title="添加栏位" style="width:400px;height:200px;">  
     <form id="updateForm" action="${pageContext.request.contextPath}/column/updateColumnAction.do" method="post">
		<table style="text-align: right;">
			
			<tr>
				<td>栏位id：</td>
				<td><input readonly="readonly" name="columnId" type="text" class="easyui-validatebox">  </td>
			</tr>
			<tr>
				<td>栏位名：</td>
				<td><input name="columnName" type="text" class="easyui-validatebox" data-options="missingMessage:'栏位名不能为空',required:true">  </td>
			</tr>
			
			<tr>
				<td>链接URL：</td>
				<td><input name="columnUrl" type="text" class="easyui-validatebox"></td>
			</tr>
			<tr>
				<td>隶属资源：</td>
				<td>
				<select name="resid">
					<%-- <c:if test="${not empty resMap }">
						<c:forEach items="${resMap}" var="res">
							<option value="${res.value.resid}">${res.value.resname}</option>
						</c:forEach>
					</c:if> --%>
				</select>
				</td>
			</tr>
			
		</table>
	</form>
</div>

</body>
</html>

<script>


function searchList(){
	 $('#listTable').datagrid('load',{
		 columnName:$('#searchForm [name=columnName]').val(),
		 columnUrl:$('#searchForm [name=columnURL]').val(),
		 resname:$('#searchForm [name=resname]').val()
	 });
}

function cleanSearch(){
	$('#listTable').datagrid('load',{});
	//$('#searchForm').find('input').val('');
	$('#searchForm input').val('');
}


/**
 * 显示栏位信息
 */
$(function(){
	//$('#columnUpdateDiv').dialog('destroy');
	$('#listTable').datagrid({
		url: '${pageContext.request.contextPath}/column/queryAllColumnAction.do',
		fit : true,
		title:'栏位管理',
		fitColumns:true,
		border:false,
		sortName:'columnName',//field
		pagination:true,
		pageSize:5,
		pageList:[5,10,20,50],
		columns:[[  
		          {field:'columnId',title:'栏位id',checkbox:true,width:100},  
		          {field:'columnName',title:'栏位名',width:100,sortable:true},  
		          {field:'columnUrl',title:'栏位URL',width:100},  
		          {field:'resname',title:'隶属资源',width:100,
		        	  formatter: function (value, row,index) {
		        		  return row.resource.resname;
	                  }
		          }/* ,  
		          {field:'password',title:'用户密码',width:100} */
		      ]],
		toolbar:'#toolbar'
		/* toolbar:[{
		        	text:'增加',
		        	iconCls:'icon-add',
		        	handler:function(){
		        		insertColumn();
			        }
		        },{
		        	text:'删除',
		        	iconCls:'icon-remove',
		        	handler:function(){
		        		deleteColumn();
			        }
		        },{
		        	text:'修改',
		        	iconCls:'icon-edit',
		        	handler:function(){
		        		updateColumn();
			        }
		        }] */
		
	});
	
	
	//动态的添加选择资源option
	$.ajax({
		type: "POST",
		url: "${pageContext.request.contextPath}/resource/queryAllResourceActionNoPage.do",
		success: function(msg){
			var obj = eval('('+msg+')');
			//console.info(msg);字符串 
			//console.info(obj.rows[0].resname);
			for(var i=0;i<obj.rows.length;i++){
				var text = obj.rows[i].resname;
				$("select[name='resid']").append("<option value='"+obj.rows[i].resid+"'>"+text+"</option>");
			}
		}

	});
});

/**
 * 添加
 */
function insertColumn(){
	//window.location.href = "${pageContext.request.contextPath}/column/insertColumn.do";
	$('#insertForm input').val("");//由于添加成功，没有跳转页面。所以需要手动清空上次添加的值
	$('#insertDiv').dialog({  
		closed:false,
	    modal:true,
	    buttons:[{
	    	text:'保存',
        	iconCls:'icon-save',
        	handler:function(){
        		$('#insertForm').submit();
        		//$('#insertDiv').dialog('close');
        		//setTimeout("$('#listTable').datagrid('reload')",1000);;//刷新当前页
	        }
	    },{
	    	text:'取消',
        	iconCls:'icon-cancel',
        	handler:function(){
        		$('#insertDiv').dialog('close');
	        }
	    }]
	});
	
	/*添加之前验证*/
	$('#insertForm').form({
		onSubmit: function(){
	        //进行表单验证  
	        //如果返回false阻止提交 
	        var columnName = $('#insertForm input[name=columnName]');
	        //console.info(columnName);
	        //console.info(queryCountByObj(columnName));
	        if(queryCountByObj(columnName)>0){
	        	//('#insertUsername').validatebox().
	        	$.messager.show({
					title:'提示信息',
					msg:'栏位名已存在',
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
					msg:'添加栏位信息成功',
					timeout:2000,
					showType:'slide'
				});
	    	}else{//("addError" == msg)
	    		$('#insertDiv').dialog('close');
	    		$.messager.show({
					title:'提示信息',
					msg:'添加栏位信息失败',
					timeout:2000,
					showType:'slide'
				});
	    	}
	        
	    }
	});
	
}


/**
 * 验证是否存在
 */
 function queryCountByObj(obj){
	var columnName = $(obj).val();
	var count=0;//默认不存在
	$.ajax({
	type:'POST',
	async:false,
	dateType:'json',
	url:"${pageContext.request.contextPath}/column/queryCountByObj.do",
	data:'columnName='+columnName,
	success:function(msg){
			//console.info(msg);
			//console.info(msg>0);
		count = msg;
	}
	});
	return count;//返回。true表示验证通过（数据库不存在）
	
}

/**
 * 修改
 */
function updateColumn(){
	var column = $('#listTable').datagrid('getSelected');//获取到选择的数据
	//console.info(column);
	$('#updateForm input[name=columnId]').val(column.columnId);
	$('#updateForm input[name=columnName]').val(column.columnName);
	$('#updateForm input[name=columnUrl]').val(column.columnUrl);
	
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
	
	
	$('#updateForm').form({
		onSubmit: function(){  
	        //进行表单验证  
	        //如果返回false阻止提交 
	        var columnName = $('#updateForm input[name=columnName]').val();
	        var columnId = $('#updateForm input[name=columnId]').val();
	        if(updateQueryCountByObj(columnName,columnId)>0){
	        	//('#insertUsername').validatebox().
	        	$.messager.show({
					title:'提示信息',
					msg:'栏位名已存在',
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
					msg:'修改栏位信息成功',
					timeout:2000,
					showType:'slide'
				});
	    	}else{// if("updateError" == msg)
	    		$('#updateDiv').dialog('close');
	    		$.messager.show({
					title:'提示信息',
					msg:'修改栏位名信息失败',
					timeout:2000,
					showType:'slide'
				});
	    	}
	        
	    }
	});
	
}

/**
 * 修改的时候，通过栏位名查询出来数量。有的话，则提示，栏位名已经存在（查询的时候，需要排除本身）
 */
function updateQueryCountByObj(columnName,columnId){
	var count=0;//默认不存在
	$.ajax({
		type:'POST',
		async:false,
		dateType:'json',
		url:"${pageContext.request.contextPath}/column/updateQueryCountByObj.do",
		data:{"columnName":columnName,"columnId":columnId},
		success:function(msg){
				//console.info(msg);
				//console.info(msg>0);
			count = msg;
		}
	});
	return count;//返回。true表示验证通过（数据库不存在）
}
 
 
/**
 * 删除
 */
 function deleteColumn(){
	 var rows = $('#listTable').datagrid('getSelections');
		//console.info(getSelections);
		if(rows.length>0){
			$.messager.confirm('确认提示','您确定要删除选择的记录？',function(r){  
			    if (r){  
			        var ids = [];
			        for(var i = 0; i < rows.length; i++){
			        	ids.push(rows[i].columnId);
			        }
			        //console.info(ids);
			        $.ajax({
			        	   url: "${pageContext.request.contextPath}/column/deleteColumnsById.do",
			        	   data: "ids="+ids,//ids会自动变成字符串。后台request.getParam可以获取到  "xxx","xxx",""
			        	   type:"POST",
			        	   success: function(msg){
			        		   //$('#listTable').datagrid('reload');//刷新当前页
			        		   if("delSuc" == msg){
			        			   $.messager.show({
			        					title:'提示信息',
			        					msg:'删除栏位成功',
			        					timeout:2000,
			        					showType:'slide'
			        				});
			        			   $('#listTable').datagrid('reload');//刷新当前页
			        		   }else{// if("delError" == msg)
			        			   $.messager.show({
			        					title:'提示信息',
			        					msg:'删除栏位失败',
			        					timeout:2000,
			        					showType:'slide'
			        				});
			        		   }
			        	   }
			        	 });
			    }  
			});  
		}
	
}


</script>