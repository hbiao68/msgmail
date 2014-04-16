<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.adtec.com.cn" prefix="adtec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 显示当前页面内容 -->
<adtec:localFileName isShow="true"></adtec:localFileName>
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/util/jquery-1.4.4.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/util/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>
<link href="${pageContext.request.contextPath}/ui/css/common.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/skin.css"
	rel="stylesheet" type="text/css">

<style type="text/css">
.button { 
font-family: "tahoma", "宋体"; 
font-size:9pt; color: #003399; 
border: 1px #003399 solid; 
color:#006699; 
border-bottom: #93bee2 1px solid; 
border-left: #93bee2 1px solid; 
border-right: #93bee2 1px solid; 
border-top: #93bee2 1px solid; 
background-color: #e8f4ff; 
cursor: hand; 
font-style: normal ; 
width:60px; 
height:22px; 
}
</style>

</head>
<body>
    <div style="display:block;width:100%;min-width:500px;height:300px;">
        <form action="<%=request.getContextPath()%>/accountManager/updateAction.do"
        method="post"  id="myform">
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <tr>
                        <td nowrap="" align="left" class="barspace toolbgline">
                            <br>
                            <br>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table style="padding-bottom: 10px" width="100%" cellspacing="0" cellpadding="4"
            border="0" class="settingtable">
                <tbody>
                    <tr>
                        <td colspan="100%">
                            <div style="font-size: 14px; padding: 8px 0 4px 2px; margin: 4px 15px"
                            class="addr_line">
                                修改用户信息
                            </div>
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            <div style="width: 120px; white-space: nowrap; text-align: right;">
                                帐号：
                            </div>
                        </td>
                        <td width="96%" align="left">
                            <input type="text" style="color: gray" name="accountName" value="${ins.accountName }"
                            maxlength="20" size="28" class="txt noime" onchange="app()" maxlength="30"
                            readonly="readonly">
                            &nbsp;
                            <font color="#FF0000">
                                *帐号不许修改
                            </font>
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            邮箱：
                        </td>
                        <td align="left">
                            <input type="text" id="email" name="email" value="${ins.email }" email="true"
                            maxlength="100" required size="28" class="txt noime" autocomplete="off">
                            &nbsp;
                            <font color="red">
                                *
                            </font>
                        </td>
                    </tr>
                    
                    
                    
                    <!-- 暂时不需要展示 -->
  <%--                   <tr class="normal black">
                        <td align="right">
                            密码：
                        </td>
                        <td align="left">
                            <input type="password" name="accountPwd" value="${ins.accountPwd}" id="mypassword"
                            maxlength="32" size="28" class="txt noime" autocomplete="off">
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            请再次输入密码：
                        </td>
                        <td align="left">
                            <input type="password" name="repassword" value="${ins.accountPwd}" id="repassword"
                            equalTo="#mypassword" maxlength="32" size="28" class="txt noime" autocomplete="off">
                        </td>
                    </tr> --%>
                    <c:if test="${not empty extendpropdef }">
                        <c:forEach items="${extendpropdef }" var="ex" varStatus="var">
                            <tr class="normal black">
                                <td align="right">
                                    ${ex.propDesc }:
                                </td>
                                <c:if test="${var.index == 0}">
                                    <td align="left">
                                        <input size="28" class="txt noime" autocomplete="off" name="prop${vs.index+1 }"
                                        value="${ins.prop1}" maxlength="50">
                                    </td>
                                </c:if>
                                <c:if test="${var.index == 1}">
                                    <td>
                                        <input size="28" class="txt noime" autocomplete="off" name="prop${vs.index+2 }"
                                        value="${ins.prop2}" maxlength="50">
                                    </td>
                                </c:if>
                                <c:if test="${var.index == 2}">
                                    <td>
                                        <input size="28" class="txt noime" autocomplete="off" name="prop${vs.index+3 }"
                                        value="${ins.prop3}" maxlength="50">
                                    </td>
                                </c:if>
                                <c:if test="${var.index == 3}">
                                    <td>
                                        <input size="28" class="txt noime" autocomplete="off" name="prop${vs.index+4 }"
                                        value="${ins.prop4}" maxlength="50">
                                    </td>
                                </c:if>
                                <c:if test="${var.index == 4}">
                                    <td>
                                        <input size="28" class="txt noime" autocomplete="off" name="prop${vs.index+5 }"
                                        value="${ins.prop5}" maxlength="50">
                                    </td>
                                </c:if>
                                <c:if test="${var.index == 5}">
                                    <td>
                                        <input size="28" class="txt noime" autocomplete="off" name="prop${vs.index+6 }"
                                        value="${ins.prop6}" maxlength="50">
                                    </td>
                                </c:if>
                                <c:if test="${var.index == 6}">
                                    <td>
                                        <input size="28" class="txt noime" autocomplete="off" name="prop${vs.index+7 }"
                                        value="${ins.prop7}" maxlength="50">
                                    </td>
                                </c:if>
                                <c:if test="${var.index == 7}">
                                    <td>
                                        <input size="28" class="txt noime" autocomplete="off" name="prop${vs.index+8 }"
                                        value="${ins.prop8}" maxlength="50">
                                    </td>
                                </c:if>
                                <c:if test="${var.index == 8}">
                                    <td>
                                        <input size="28" class="txt noime" autocomplete="off" name="prop${vs.index+9 }"
                                        value="${ins.prop9}" maxlength="50">
                                    </td>
                                </c:if>
                                <c:if test="${var.index == 9}">
                                    <td>
                                        <input size="28" class="txt noime" autocomplete="off" name="prop${vs.index+10 }"
                                        value="${ins.prop10}" maxlength="50">
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <div style="" class="list_btline">
                        <div class="f_size selbar_bt barspace2" style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px"
                        align="center">
                            <input type="submit" title="修改" name="next" value="修改" class="button">
                            &nbsp;
                            <!-- <input type="button" name="next" value="返回" onclick="return onNext2()">	 -->
                            <input type="button" name="next" value="返回" class="button" onclick="javascript:history.go(-1)">
                            <!-- <input type="button" title="返回" name="next" value="返回" class="button" onclick="historyGo()"> -->
                        </div>
                    </div>
                </tbody>
            </table>
            <input type="hidden" name="cateName" value="${cateName }">
            <input type="hidden" name="Id" value="${ins.id }">
            <input type="hidden" name="orgId" value="${orgId }">
        </form>
    </div>
</body>

<script type="text/javascript">
	//表单校验		
	$(function() {
		//指明校验什么表单
		$("#myform").validate();
	});
	function historyGo(){
		$(window.parent.document).find("#folder_14").click();
	}	
</script>

</html>



