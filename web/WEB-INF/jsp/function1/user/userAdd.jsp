<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/jquery-1.4.4.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

$(function(){
	
	//alert(1);
});

</script>
<body>
<form action="<%=request.getContextPath() %>/function1/user/addAction.do" method="post">

	用户id : <input name="id" value="">
	<br>
	用户姓名:<input name="name" value="">
	<br>
	生日:<input name="birthday" value=""><font style="color:red">(yyyy-mm-dd)</font>
	<br>
	
	
	篮球<input type="checkbox" name="hobbies" value="basketball">
	足球<input type="checkbox" name="hobbies" value="football">
	网球<input type="checkbox" name="hobbies" value="tennis">
	
	
	
	<input type="submit" value="提交">

</form>

<br>
初始化的数据 ： 	${hb }
<br>

	<c:forEach items="${hb}" var="hobby" varStatus="vs">
		<c:choose>
			<c:when test="${hobby == 'basketball'}">
				篮球<input type="checkbox" name="hobbies" value="basketball">
			</c:when>
			<c:when test="${hobby == 'football'}">
				足球<input type="checkbox" name="hobbies" value="football">
			</c:when>
			<c:when test="${hobby == 'tennis'}">
				网球<input type="checkbox" name="hobbies" value="tennis">
			</c:when>
		</c:choose>
	</c:forEach>
	
	<br>
	<br>
	
	<fmt:setLocale value="zh_CN"/>
	<fmt:setBundle basename="message" var="myBundle" />
	
	<fmt:message key="hello" bundle="${myBundle }">
		<fmt:param>niii</fmt:param>
　　</fmt:message>

<br>
------------<spring:message code="hello" arguments="111,222" argumentSeparator=",">
	
</spring:message><br>
</body>
</html>