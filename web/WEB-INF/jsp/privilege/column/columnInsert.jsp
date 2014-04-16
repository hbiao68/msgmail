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



<!-- 添加栏位的div -->
<div class="easyui-dialog" id="insertDiv" title="添加栏位" style="width:400px;height:200px;" 
	data-options="buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){alert('edit')}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){alert('help')}
			}]"
>  
     <form id="insertForm" action="${pageContext.request.contextPath}/column/insertColumnAction.do" method="post">
		<table style="text-align: right;">
			<tr>
				<td>栏位名：</td>
				<td><input type="text" class="easyui-validatebox" data-options="missingMessage:'栏位名不能为空',required:true">  </td>
			</tr>
			
			<tr>
				<td>链接地址：</td>
				<td><input type="text" class="easyui-validatebox"></td>
			</tr>
			<tr>
				<td>隶属资源：</td>
				<td>
				<select name="resid">
					<c:if test="${not empty resMap }">
						<c:forEach items="${resMap}" var="res">
							<option value="${res.value.resid}">${res.value.resname}</option>
						</c:forEach>
					</c:if>
				</select>
				</td>
			</tr>
			
		</table>
	</form>
</div>

</body>
</html>