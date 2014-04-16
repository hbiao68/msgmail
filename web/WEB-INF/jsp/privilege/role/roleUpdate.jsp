<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/role/updateRoleAction.do" method="post">
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
<input type="submit" value="保存修改">
</form>

</body>
</html>