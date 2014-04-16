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
	src="<%=request.getContextPath()%>js/util/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>	
<link href="${pageContext.request.contextPath}/ui/css/common.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/skin.css"
	rel="stylesheet" type="text/css">

<style type="text/css">
.button {
	font-family:"tahoma","宋体";
	font-size:9pt;
	color:#003399;
	border:1px #003399 solid;
	color:#006699;
	border-bottom:#93bee2 1px solid;
	border-left:#93bee2 1px solid;
	border-right:#93bee2 1px solid;
	border-top:#93bee2 1px solid;
	background-color:#e8f4ff;
	cursor:hand;
	font-style:normal;
	width:60px;
	height:22px;
}
</style>

</head>
<body>
    <div style="display:block;width:100%;min-width:500px;height:300px;">
        <form action="<%=request.getContextPath()%>/appManager/insertAction.do"
        method="post">
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
                                业务详细信息
                            </div>
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            <div style="width: 120px; white-space: nowrap; text-align: right;">
                                业务名称：
                            </div>
                        </td>
                        <td width="96%" align="left">
                            ${ins.appName }&nbsp;
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            业务服务器域名：
                        </td>
                        <td align="left">
                            ${ins.appDomain }
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            业务帐号类型：
                        </td>
                        <td align="left">
                            <%-- <select name="ta_id" disabled="disabled">
                                <c:forEach items="${rows}" var="rows">
                                    <option value="${rows.ta_id}">
                                        ${rows.cateDesc }
                                    </option>
                                </c:forEach>
                                
                                 <option value="${ins.ta_id}">
                                        ${cateDesc}
                                    </option>
                            </select> --%>
                            ${cateDesc}
                        </td>
                    </tr>
                </tbody>
            </table>
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <div style="" class="list_btline">
                        <div class="f_size selbar_bt barspace2" style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px"
                        align="center">
                            <input type="button" title="返回" name="next" value="返回" class="button" onclick="return onList()">
                        </div>
                    </div>
                </tbody>
            </table>
        </form>
    </div>
</body>


</html>

<script type="text/javascript">
     //跳转到查询页面
	 function onList()
	 {
	    with(document.forms[0])
	    {
				action="<%=request.getContextPath()%>/appManager/appList.do";
				submit();
		}
	}
</script>

