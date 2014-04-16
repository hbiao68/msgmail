<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.adtec.com.cn" prefix="adtec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 显示当前页面内容 -->
<adtec:localFileName isShow="true"></adtec:localFileName>
<title>Insert title here</title>
<!-- 此页面抛弃   -->
<!-- 下面是树状结构 -->

 <link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree.css">
        <script src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxcommon.js"></script>
        <script src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree.js"></script>
        <script src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree_start.js"></script>
</head>

</head>
<body>




	<table border="1" align="center">
		<tr>

			<th width="200px">机构号码</th>
			<th width="200px">机构名称</th>
			<th width="200px">操作</th>
		</tr>

		<c:if
			test="${listLevel1 == null || fn:length(listLevel1) == 0}">
			<tr>
				<td colspan='3' align="center">当前没有数据</td>
			</tr>
		</c:if>

<!-- -------------遍历显示数据--------------------------	  -->
		<c:forEach items="${listLevel1}" var="org">
			<tr>
				<td>
				<%-- <c:if test="${org.level1 != null} && ${org.level1 != \'\' }">${org.level1}|</c:if>
				<c:if test="${org.level2 != null} && ${org.level2 != \'\' }">${org.level2}|</c:if>
				<c:if test="${org.level3 != null} && ${org.level3 != \'\' }">${org.level3}|</c:if>
				<c:if test="${org.level4 != null} && ${org.level4 != \'\' }">${org.level4}|</c:if>
				<c:if test="${org.level5 != null} && ${org.level5 != \'\'}">${org.level5}|</c:if>
				<c:if test="${org.level6 != null} && ${org.level6 != \'\' }">${org.level6}|</c:if>
				<c:if test="${org.level7 != null} && ${org.level7 != \'\' }">${org.level7}|</c:if>
				 --%>	
			<!-- 	 这里的empty包括null和 "" -->
				<c:if test="${!empty org.level1}">${org.level1}|</c:if>
				<c:if test="${!empty org.level2}">${org.level2}|</c:if>
				<c:if test="${!empty org.level3}">${org.level3}|</c:if>
				<c:if test="${!empty org.level4}">${org.level4}|</c:if>
				<c:if test="${!empty org.level5}">${org.level5}|</c:if>
				<c:if test="${!empty org.level6}">${org.level6}|</c:if>
				<c:if test="${!empty org.level7}">${org.level7}|</c:if>	
				
						      
				</td>
				
				<td>${org.orgName }</td>
				<%-- <td><a href="deleteOrg.do?orgId=${org.orgId }" --%>
				<td><a href="<%=request.getContextPath()%>/organizationManager/deleteOrg.do?orgId=${org.orgId }"
					onclick="return confirm('您确定要删除该等级信息吗?')">删除</a>&nbsp;
					<a href="<%=request.getContextPath()%>/organizationManager/updateOrgPage.do?orgId=${org.orgId}">修改</a>
				</td>
				
				
			</tr>
		</c:forEach>
	</table>

</body>
</html>