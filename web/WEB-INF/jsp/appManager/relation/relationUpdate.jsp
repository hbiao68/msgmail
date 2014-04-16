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

.msg {
	color: #FFFFFF;
	background: #68af02;
	font-size: 12px;
	padding: 3px 24px 3px;
	z-index: 1;
	position:absolute;
}
</style>

</head>
<body>
    <div style="display:block;width:100%;min-width:500px;height:300px;">
        <form action="<%=request.getContextPath()%>/relation/updateAction.do"
        method="post" onsubmit="return (addSubmit());">
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
                                修改开通业务信息
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
                            <input type="text" name="accountName" style="color: gray" value="${map.accountName }"
                            size="28" class="txt noime" onchange="app()" maxlength="30" readonly="readonly">
                            &nbsp;
                            <font color="#FF0000">
                                *业务名称不许修改
                            </font>
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            业务名称：
                        </td>
                        
                        <td align="left" width="96%">
                            <input type="checkbox" id="select_All" onclick="selectAll()">
                            全选
                            <br>
                            <c:forEach items="${rows }" var="_rows" varStatus="vs">
                                <input type="checkbox" name="appid"  onclick="selectCheckbox();" value="${_rows.appid }" <c:if test="${_rows.ef=='1' }">
                                checked="checked"
                                </c:if>
                                >
                                ${vs.index+1}.&nbsp;${_rows.appName }
                                <br>
                            </c:forEach>
                        </td>
                        
                    </tr>
                </tbody>
            </table>
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <div style="" class="list_btline">
                        <div class="f_size selbar_bt barspace2" style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px"
                        align="center">
                            <input type="submit" title="修改" name="next" value="修改" class="button">
                            <!-- <input type="button" name="next" value="返回" onclick="return onlist()"> -->
                            <input type="button" name="next" value="返回" class="button" onclick="javascript:history.go(-1)">
                            <!-- <input type="button" title="返回" name="next" value="返回" class="button" onclick="historyGo()"> -->
                            
                        </div>
                    </div>
                </tbody>
            </table>
            <input type="hidden" name="ta_id" value="${ta_id }">
            
            <!-- span做alert使用，用户操作成功之后，会在浏览器的正中间提示.span的会变化 在$("#msg").html("xx");中做处理。 -->
			<span style="display:none;" class="msg" id="msg">&nbsp;</span>
            
            
        </form>
        
        
        
    </div>
</body>
</html>

<script type="text/javascript">
	//表单校验
	$(function() {
		//指明校验什么表单
		$("#myform").validate();
		//alert(1);
	});

	function submit() {
		//	alert("submit");
		return false;
	}

	//修改时校验是否有checkbox选中
	function addSubmit() {
		//	alert("addSubmit");
		var checkedFlag = false;//是否被选中的标记
		$("input[name='appid']").each(function(index, dom) {
			if ($(this).attr("checked") == true) {
				checkedFlag = true;
				//	alert(checkedFlag);
			}
		});
		//$("#msg").html("请选中业务");
		//showMsg();
		//为false则表示没有选中
		if(!checkedFlag){
			adtecUtil.showMsg("请选中业务");
		}
		
		
		return checkedFlag;
	}
	function historyGo(){
		$(window.parent.document).find("#folder_16").click();
	}
	
	//全选
	function selectAll(){
    	var flag = $("#select_All").attr("checked");
    	if(flag){
    		$("input[name='appid']").each(function(index,dom){
    			$(this).attr("checked",true);
    		});
    	}else{
			$("input[name='appid']").each(function(index,dom){
				$(this).attr("checked",false);
    		});
    	}
    }
	
	//检查checkbox是否全部选中，来控制全选按钮
	function isSelectAlla(){
		var length = $("input[name='appid']").length;
		var count=0;
		$("input[name='appid']").each(function(index,dom){
			if($(this).attr("checked")==true){
				count++;
			}
		});
		if(count==length){
			 $("#select_All").attr("checked",true);
		}else{
			 $("#select_All").attr("checked",false);
		}
	}
	
	//校验Checkbox选中几个
	function selectCheckbox(){
		isSelectAlla();
	}
	
	
	/**
	 *  span作 alert使用的显示方法
	 */
	 /*function showMsg(msgHtml){
			$(window.parent.document).find("#msg").html(msgHtml);
			$(window.parent.document).find("#msgBoxDIV").slideDown("fast");
			setTimeout("hidenMsg()",1000);
			 //获取浏览器的宽度
			if (window.innerWidth)
			winWidth = window.innerWidth;
			else if ((document.body) && (document.body.clientWidth))
			winWidth = document.body.clientWidth;
			//获取浏览器的高度（其实高度不用，删掉即可）
			if (window.innerHeight)
			winHeight = window.innerHeight;
			else if ((document.body) && (document.body.clientHeight))
			winHeight = document.body.clientHeight;
			
			//获取msg标签的长度
			var msgWidth = $("#msg").innerWidth();
			//alert(msgWidth);
			
			//alert(winWidth+"---高--"+winHeight);
			//alert(winWidth/2);
			var leftPx = (winWidth-msgWidth)/2;
			var topPx = 0;
			$("#msg").css({left:leftPx + "px", top:topPx+"px"}).slideDown("fast");
			setTimeout("hidenMsg()",2000);
			
			 
	}
	//隐藏msg
	 function hidenMsg(){
	 	//$("#msg").fadeOut("slow");
	 	$(window.parent.document).find("#msgBoxDIV").fadeOut("slow");
	 }*/
</script>
