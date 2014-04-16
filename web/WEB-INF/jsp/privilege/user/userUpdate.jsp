<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/user/updateUserAction.do" method="post">
<table>
			<tr style="height: 20px;">
				<td>用户id</td>
				<td title="${user.userid }">
					<input name="userid" type="text" value="${user.userid}" readonly="readonly">
				</td>				
			</tr>
			
			<tr style="height: 20px;">
				<td>用户名</td>
				<td title="${user.username}">
					<input name="username" type="text" value="${user.username}" >
				</td>
			</tr>
			
			<tr style="height: 20px;">
				<td>用户密码</td>
				<td title="${user.password }">
					<input name="password" type="text" value="${user.password}">
				</td>
			</tr>
</table>
<input type="submit" value="保存修改">
</form>
</body>
</html>