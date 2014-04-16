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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util/jquery-1.4.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util/jquery.validate.min.js"></script>

<link href="${pageContext.request.contextPath}/ui/css/common.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/skin.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/add.css"
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
    <div style="display:block;width:100%;min-width:1122px;height:300px;">
        <form action="<%=request.getContextPath()%>/category/cateList.do" method="post">
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <tr>
                        <td nowrap="nowrap" align="left" class="barspace toolbgline">
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
                                终端详细信息
                            </div>
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            <div style="width: 120px; white-space: nowrap; text-align: right;">
                                终端ID：
                            </div>
                        </td>
                        <td width="96%" align="left">
                            ${rows.cateName }&nbsp;
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            终端分类：
                        </td>
                        <td align="left">
                            ${rows.cateDesc }
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            导入类：
                        </td>
                        <td align="left">
                            ${rows.importClass }
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            认证类：
                        </td>
                        <td align="left">
                            ${rows.authClass }
                        </td>
                    </tr>
                </tbody>
            </table>
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <tr>
                        <td colspan="100%">
                            <div style="font-size: 14px; margin: 4px 15px">
                                扩展属性定义：
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table cellspacing="0" cellpadding="0" class="O2" style="table-layout: fixed; width: 100%; *width: auto;">
                <tbody>
                <tr></tr>
                    <tr style="height: 20px;">
                        <td width="20%" class="o_title2">
                            序号
                        </td>
                        <td width="40%" class="o_title2">
                            名称
                        </td>
                        <td width="40%" class="o_title2">
                            描述
                        </td>
                    </tr>
                </tbody>
            </table>
             <table id="contactTable" cellspacing="0" cellpadding="0" style="table-layout: fixed; width: 100%; *width: auto; background-color: white;">
	              <tbody>
		            <c:if test="${empty rows1 }">
		                <div id="tips_bar">
		                    <div class="nomail">
		                        <b>
		                            当前没有数据
		                        </b>
		                    </div>
		                </div>
		            </c:if>
		            <c:if test="${not empty rows1 }">
		                <c:forEach items="${rows1 }" var="dd">
		                	<tr></tr>
		                                <tr style="height: 20px;">
		                                    <td width="20%">
		                                        ${dd.prop_index }
		                                    </td>
		                                    <td width="40%">
		                                        ${dd.propName }
		                                    </td>
		                                    <td width="40%" title="${dd.propDesc }">
		                                        ${dd.propDesc }
		                                    </td>
		                                </tr>
		                </c:forEach>
		            </c:if>
	            </tbody>
            </table>
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <div class="list_btline">
                        <div class="f_size selbar_bt barspace2" style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px"
                        align="center">
                            <!-- <input type="button" name="next" value="返回" onclick="javascript:history.go(-1)"
                            class="button"> -->
                            <input type="button" title="返回" name="next" value="返回" class="button" onclick="return onList()">
                        </div>
                    </div>
                </tbody>
            </table>
        </form>
    </div>        
</body>

<script type="text/javascript">

		//跳转到查询页面
	    function onList()
	    {
	       with(document.forms[0])
	       {
			action="<%=request.getContextPath()%>/category/cateList.do";
			submit();
		   }
		}
		
	
		$(function (){
			//调整查询出来数据显示的长度
			formactTable("contactTable",[1,2,3],[30,30,30],30);  
		});
</script>

</html>
