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

<!-- orgAdd移到orgList页面直接添加，此页面抛弃 -->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/jquery.validate.min.js"></script>



</head>
<body>

	<form action="addOrg.do?orgId=${org.orgId}" method="post" onsubmit="return userCheck();">
		<table id="addtableId">
			<tr>
			<%-- <td>${org.orgId}</td> --%>
				<td><div style="display: none;">机构号码 </div> </td>
				<td colspan="2">
				
				<div style="display: none;">
					<!-- 判断是第几个等级 -->
					<c:if test="${empty org.level1}">
						<select id="level1" name="level1" >
							<c:if test="${listLevel == null || fn:length(listLevel) == 0}">
								<option value="1">01</option>
							</c:if>
							
							<c:if test="${listLevel != null && fn:length(listLevel) != 0}">
								<option value="${fn:length(listLevel)+1 }">
										${fn:length(listLevel)+1 } 
								</option>
							</c:if>
						</select>
					
					</c:if>
					<!-- 只能选择level2 -->
					<c:if test="${!empty org.level1 and empty org.level2}">
						<select id="level2"  name="level2" >
							<c:if test="${listLevel == null || fn:length(listLevel) == 0}">
								<option value="1">01</option>
							</c:if>
							
							<c:if test="${listLevel != null && fn:length(listLevel) != 0}">
								<option value="${fn:length(listLevel)+1 }">
										${fn:length(listLevel)+1 } 
								</option>
							</c:if>
						</select>
					
					</c:if>
					
					<c:if test="${!empty org.level1 and !empty org.level2 and empty org.level3}">
						<select id="level3"  name="level3">
							<c:if test="${listLevel == null || fn:length(listLevel) == 0}">
								<option value="1">01</option>
							</c:if>
							
							<c:if test="${listLevel != null && fn:length(listLevel) != 0}">
								<option value="${fn:length(listLevel)+1 }">
										${fn:length(listLevel)+1 } 
								</option>
							</c:if>
						</select>
					
					</c:if>
					<c:if test="${!empty org.level1 and !empty org.level2 and  !empty org.level3 and empty org.level4}">
						<select id="level4"  name="level4">
							<c:if test="${listLevel == null || fn:length(listLevel) == 0}">
								<option value="1">01</option>
							</c:if>
							
							<c:if test="${listLevel != null && fn:length(listLevel) != 0}">
								<option value="${fn:length(listLevel)+1 }">
										${fn:length(listLevel)+1 } 
								</option>
							</c:if>
						</select>
					
					</c:if>
					<!-- 只能选择level5 -->
					<c:if test="${!empty org.level1 and !empty org.level2 and  !empty org.level3 and  !empty org.level4 and empty org.level5}">
						<select id="level5"  name="level5" >
							<c:if test="${listLevel == null || fn:length(listLevel) == 0}">
								<option value="1">01</option>
							</c:if>
							
							<c:if test="${listLevel != null && fn:length(listLevel) != 0}">
								<option value="${fn:length(listLevel)+1 }">
										${fn:length(listLevel)+1 } 
								</option>
							</c:if>
						</select>
					
					</c:if>
					<c:if test="${!empty org.level1 and !empty org.level2 and  !empty org.level3 and  !empty org.level4 and !empty org.level5 and empty org.level6}">
						<select id="level6"  name="level6" >
							<c:if test="${listLevel == null || fn:length(listLevel) == 0}">
								<option value="1">01</option>
							</c:if>
							
							<c:if test="${listLevel != null && fn:length(listLevel) != 0}">
								<option value="${fn:length(listLevel)+1 }">
										${fn:length(listLevel)+1 } 
								</option>
							</c:if>
						</select>
					
					</c:if>
					
					<c:if test="${!empty org.level1 and !empty org.level2 and  !empty org.level3 and  !empty org.level4 and !empty org.level5 and !empty org.level6 and empty org.level7}">
						<select id="level7"  name="level7">
							<c:if test="${listLevel == null || fn:length(listLevel) == 0}">
								<option value="1">01</option>
							</c:if>
							
							<c:if test="${listLevel != null && fn:length(listLevel) != 0}">
								<option value="${fn:length(listLevel)+1 }">
										${fn:length(listLevel)+1 } 
								</option>
							</c:if>
						</select>
					
					</c:if>
					</div>
				</td>
				<td></td>
				
			</tr>
			<tr>
				<td>机构名称</td>
				<td><input maxlength="15" id="orgName" name="orgName" type="text" value="" onblur="nameMsg()" /> <font color="red">*</font>	</td>
				<td><span id="span1"></span></td>
			</tr>
			<tr>
				<td><input type="submit" value="确定" onsubmit="userCheck();"></td>
				<td><input type="button" onclick="clearRowsSMS()" value="返回"></td>
				<td></td>	
			</tr>
		</table>
	</form>

</body>
</html>

<script type="text/javascript">

function nameMsg(){
	var span = document.getElementById("span1");
	var name = document.getElementById("orgName").value;
	name = name.replace(/^\s+|\s+$/g, '');
	span.innerHTML = "";
		if(name == ""){
			msg = '名称不能为空';
			span.innerHTML = msg;
			span.style.color='#ff0000';
		    span.style.fontSize='12px';
		  
		}
		if(name.length > 15){
			msg = '长度限制为15';
			span.innerHTML = msg;
			span.style.color='#ff0000';
		    span.style.fontSize='12px';
		}
} 

function userCheck() {
	var textuser = document.getElementById("orgName").value;
	textuser = textuser.replace(/^\s+|\s+$/g, '');//去掉两边空格
	
	if(textuser == ""){
		//alert("名称不能为空");		把提升的弹窗去掉。用户体验好些。return false
		return false;
	}else if(textuser.length > 15){
		alert("长度限制为15");
	    return false;
	}else{
		
		
		return true;	
	}
}
//清除Table
function clearRowsSMS(){
	var tableLen = document.getElementById('addtableId').rows.length;
	if(tableLen > 1){
		for(var i=0; i<tableLen; i++){
			document.getElementById('addtableId').deleteRow(tableLen-i-1);
		}
	}
}

</script>