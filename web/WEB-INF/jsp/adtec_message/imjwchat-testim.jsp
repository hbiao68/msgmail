<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.adtec.com.cn" prefix="adtec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="viewKey"  value="${resid}1001"></c:set>
<c:set var="viewPrivlg" value="${oneUserAllPrivlgMap[viewKey].actionValue}"></c:set>
<c:set var="addKey"  value="${resid}1002"></c:set>
<c:set var="addPrivlg" value="${oneUserAllPrivlgMap[addKey].actionValue}"></c:set>
<c:set var="updateKey"  value="${resid}1003"></c:set>
<c:set var="updatePrivlg" value="${oneUserAllPrivlgMap[updateKey].actionValue}"></c:set>
<c:set var="delKey"  value="${resid}1004"></c:set>
<c:set var="delPrivlg" value="${oneUserAllPrivlgMap[delKey].actionValue}"></c:set>
<c:if test="${viewPrivlg  || wholePrivlg}">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="pageID" content="imjwchat-testim">
<adtec:localFileName isShow="true"></adtec:localFileName>
<title><fmt:message key="plugin.testim.title"/></title>
</head>


<body>


<ul>

<li>
1.带from to content（发送者，接受者）<br>
	<form action="${pageContext.request.contextPath}/adtec_message/sendMessageFromToCont.do" method="post">
		sender:<input name="sender" type="text" value="mjd1">
		receiver: <input name="receiver" type="text" value="mjd2">
		content:<input name="content" type="text" value="消息体">
		<input type="submit" value="测试发送">
	</form>
	<br>
</li>

<li>
2.测试发送消息<br>
	<form action="${pageContext.request.contextPath}/adtec_message/sendMessageToCont.do" method="post">
		接收用户id（receiver）: <input name="receiver" type="text" value="mjd2">
		内容（content）:<input name="content" type="text" value="消息体">
		<input type="submit" value="测试发送消息">
	</form>
	<br>
</li>
<li>
3.测试广播消息<br>
	<form action="${pageContext.request.contextPath}/adtec_message/brdMessage.do" method="post">
		接受者：<select name="_brdrecv"><option value="online">在线用户</option><option value="all">所有</option></select>
		广播内容（content）:<input name="content" type="text" value="消息体">
		<input type="submit" value="测试发送广播">
	</form>
	<br>
</li>


<li>
4.xml字符串测试<br>
	<form action="${pageContext.request.contextPath}/adtec_message/xmlMessage.do" method="post">
		<textarea name="xmlTextArea" ></textarea>
		<input type="submit" value="测试发送xml">
	</form>
	<br>
</li>

</ul>

<hr>


<ul>
<li>
1.测试要获取的用户状态:<br>
	<form action="${pageContext.request.contextPath}/adtec_message/getUserStatus.do" method="post">
		用户id:<input name="_idUser" type="text" value="mjd1">
		<input type="submit" value="获取在线状态">
	</form>
	<br>
</li>

<li>
2.获取在线用户总数<br>
	<form action="${pageContext.request.contextPath}/adtec_message/getCountOnlineUsers.do" method="post">
		
		<input type="submit" value="获取在线用户数量">
	</form>
	<br>
</li>

</ul>


</body>


</html>
</c:if>