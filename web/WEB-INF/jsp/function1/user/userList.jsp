<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/util/jquery-1.4.4.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(function() {

		//alert(1);
	});
	
	var myname="hb";
	
	function updateObj(obj){
		alert("updateObj");
	}
	
	function delObj(obj){
		//alert(obj.myname);
		//alert(obj.innerHTML);
		
		var parentTR = $(obj).closest("tr");
		//alert(parentTR.attr("outerHTML"));
		//alert(parentTR.find("td").size());
		//alert(parentTR.find("td").get(0).innerHTML);
		
		
		var userJson = {
			id:parentTR.find("td").get(0).innerHTML,
			name:parentTR.find("td").get(1).innerHTML,
			birthday:parentTR.find("td").get(2).innerHTML,
			hobbies:parentTR.find("td").get(3).innerHTML,
			date:new Date().getTime()
		};
		
		$.ajax({
		   url: "<%=request.getContextPath()%>/function1/user/delUserAction.do",
		   async: false,
		   type:"get",
		   //type:get,//将处理拦截使用post和get两种方式
		   //data: userJson,//直接传递json有浏览器兼容的问题，能够在ie中正常运行，但是在chrome中运行异常
		   data:"id="+parentTR.find("td").get(0).innerHTML+"&name="+parentTR.find("td").get(1).innerHTML+"&birthday="+parentTR.find("td").get(2).innerHTML+"&hobbies="+parentTR.find("td").get(3).innerHTML+"&date="+new Date().getTime(),
		   success: function(html){
		       //window.location.reload();
		   },
		   error:function(msg){
			   alert("error");
			   //window.location.reload();
		   }

		});
	}
</script>
<body>

	<table style="border:1px solid #333;">
		<tr style="border:1px solid #333;">
			<td>主键</td>
			<td>用户名</td>
			<td>生日</td>
			<td>爱好</td>
			<td>修改</td>
			<td>删除</td>
		</tr>
		<c:if test="${empty users }">
			<tr>
				<td style="font-color: red">没有数据</td>
			</tr>
		</c:if>
		<c:if test="${not empty users }">
			<c:forEach items="${users }" var="user" varStatus="vs">
				<tr>
					<td>${user.id }</td>
					<td>${user.name }</td>
					<td>
					<fmt:formatDate value="${user.birthday }" pattern="yyyy-MM-dd"/>
					</td>
					<td>${user.hobbies }</td>
					<td onclick="updateObj(this)"><a href="javascript:void">修改</a></td>
					<td onclick="delObj(this)"><a href="javascript:void">删除</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>

</body>
</html>