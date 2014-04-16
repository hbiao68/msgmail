<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/type/updateTypeAction.do" method="post">
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
<input type="submit" value="保存修改">
</form>
</body>
</html>