<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/resource/resInsertAction.do" method="post">
		<table>
			<!-- <tr>
				<td>编码</td>
				<td><input type="text" name="resid"></td>
			</tr> -->
			<tr>
				<td>名称</td>
				<td><input type="text" name="resname"></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="commen"></td>
			</tr>
			<tr>
				<td><input type="submit" value="提交"></td>
				<td><input type="reset" name="重置"></td>
			</tr>

		</table>
	</form>
</body>
</html>