<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/privilege/updatePrivilegeAction.do" method="post">
<table>
			<tr style="height: 20px;">
				<td>权限id</td>
				<td title="${privilege.privilegeid }">
					${privilege.privilegeid}
				</td>
				<td><input name="privilegeid" type="text" style="display: none;" value="${privilege.privilegeid}"> </td>				
			</tr>
			
			<tr style="height: 20px;">
				<td>资源id</td>
				<td title="${privilege.resid}">
					${privilege.resid}
				</td>
				<td><input name="resid" type="text" style="display: none;" value="${privilege.resid}"> </td>
			</tr>
			
			<tr style="height: 20px;">
				<td>资源名</td>
				<td title="${privilege.resource.resname}">
					${privilege.resource.resname}
				</td>
				<td><input name="resource.resname" type="text" style="display: none;" value="${privilege.resource.resname}"> </td>
			</tr>
			
			<tr style="height: 20px;">
				<td>操作类型</td>
				<td title="${privilege.type.typename}">
					${privilege.type.typename}
				</td>
				<td><input name="actionType" type="text" style="display: none;" value="${privilege.type.typename}"> </td>
			</tr>
			
			<tr style="height: 20px;">
				<td>开启/关闭权限</td>
				<%-- <td title="${privilege.actionValue}">
					${privilege.actionValue}
				</td> --%>
				<td>
					<select name="actionValue">
						<option value="true">开启</option>
						<option value="false">关闭</option>
					</select>
				</td>
			</tr>
			
			
</table>
<input type="submit" value="保存修改">
</form>

</body>
</html>