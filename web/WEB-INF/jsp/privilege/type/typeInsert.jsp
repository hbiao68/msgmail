<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/type/insertTypeAction.do" method="post">
<table>
	<tr>
		<td>操作类型名：</td>
		<td><input type="text" name="typename">  </td>
	</tr>
	
	<tr>
		<td>备注：</td>
		<td><input type="text" name="common"></td>
	</tr>

</table>

<input type="submit" value="保存" >
</form>
</body>
</html>