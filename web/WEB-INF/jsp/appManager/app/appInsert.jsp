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
        method="post" name="form1" id="myform">
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
                                添加业务
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
                            <input type="text" size="28" class="txt noime appName" name="appName"
                            onchange="app()" required maxlength="30">
                            &nbsp;
                            <font color="#FF0000">
                                *
                            </font>
                            <font class="tishi" style="color: red; font-size: 12px">
                            </font>
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            业务服务器域名：
                        </td>
                        <td align="left">
                            <input type="text" name="appDomain" size="28" class="txt noime" maxlength="100"
                            required autocomplete="off">
                            &nbsp;
                            <font color="red">
                                *
                            </font>
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            业务帐号类型：
                        </td>
                        <td align="left">
                            <select name="ta_id" style="width:195px" id="selectId" onchange="getSelectTitle('selectId')">
                                <c:forEach items="${rows}" var="rows">
                                    <option title=" ${rows.cateDesc }" value="${rows.ta_id}">
                                        <c:if test="${fn:length(rows.cateDesc)>10}">
                                            ${fn:substring(rows.cateDesc,0,10)}...
                                        </c:if>
                                        <c:if test="${fn:length(rows.cateDesc)<=10 }">
                                            ${rows.cateDesc }
                                        </c:if>
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <div style="" class="list_btline">
                        <div class="f_size selbar_bt barspace2" style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px"
                        align="center">
                            <input type="submit" title="添加" name="next" value="添加" class="add button">
                            <input type="button" title="返回" name="next" value="返回" class="button" onclick="return onList()">
                        </div>
                    </div>
                </tbody>
            </table>
        </form>
    </div>
</body>



<script type="text/javascript">

 	//表单校验
 	$(function(){
			//指明校验什么表单
			$("#myform").validate();
			//给默认选中的下拉列表选项加title
 			 var option = $("option").attr("title"); 
			document.getElementById('selectId').setAttribute("title",option);
		}
 	);
 	
 	//当下拉列表选中选项时，给选中的选项加title
 	function getSelectTitle(name){
 		var obj=document.getElementById(name);
 		for(var i=0;i<obj.length;i++){
 		   if(obj[i].selected==true){
 		   // return obj[i].innerText;      //关键是通过option对象的innerText属性获取到选项文本
 		   //alert(obj[i].innerText);
 		   document.getElementById('selectId').setAttribute("title",obj[i].innerText);
 		   }
 		}
 		app();
		
 	}
 	//跳转到查询页面
    function onList()
    {
       with(document.forms[0])
       {
			window.location.href="<%=request.getContextPath()%>/appManager/appList.do";	   
       }
    }
	    
	 //校验业务是否存在
	 function app(){
	    	var appName=$('.appName').val();
	    	var ta_id = $('#selectId').val();

	   // 	alert(selectId);
	    	appName=$.trim(appName);
	    	 $.ajax({
	    		 type: "POST",
	    		 dataType:"json",
	    		 data: {"appName":appName,"ta_id":ta_id},
	    		 url:  "<%=request.getContextPath()%>/appManager/check.do",
			success : function(msg) {
				if (msg == '0') {
					$('.tishi').text('此业务名称可使用');
					$('.add').attr("disabled", "");

				} else {
					$('.tishi').text('此业务名称已存在');
					$('.add').attr("disabled", "disabled");
				}
			}
		});
	}
</script>

</html>

