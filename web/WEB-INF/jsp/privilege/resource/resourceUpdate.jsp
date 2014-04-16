<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/resource/updateResAction.do" method="post">
<table>
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
<input type="submit" value="保存修改">
</form>

</body>
</html>