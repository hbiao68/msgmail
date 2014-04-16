<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.adtec.com.cn" prefix="adtec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 显示当前页面内容 -->
<adtec:localFileName isShow="true"></adtec:localFileName>
<title>Insert title here</title>

<script src="${pageContext.request.contextPath}/js/util/jquery-1.4.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/util/jquery.validate.min.js"></script> --%>

<link href="${pageContext.request.contextPath}/ui/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/skin.css" rel="stylesheet" type="text/css">


		
<style type="text/css">
/* a{ display:block; width:36px;
height:16px;
border:2px outset #eee;
background:#eee; text-align:center;
font-size:12px; color:#000; text-decoration:none; padding-top:2px; }
a:hover{ border:2px inset #eee; background:#CCCCCC; text-decoration:none; } */

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
	
	<table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
	<tbody>
		<tr>
			<td nowrap="" align="left" class="barspace toolbgline">
				<a id="submitbtn" class="button_gray_s" name="submitbtn1" onclick="doValSubmit();" href="javascript:void(0);" style="float:left; margin-right:5px;" title="保存更改">保存更改</a>
				<a id="cancel" class="button_gray_s" name="submitbtn" href="${pageContext.request.contextPath}/organizationManager/orgList.do" style="float:left;" title="取消">取消</a>
			</td>
		</tr>
	</tbody>
</table>

		

<form id="form1" action="${pageContext.request.contextPath}/organizationManager/updateOrg.do?orgId=${org.orgId} " method="post" onsubmit="return userCheck();">
<table id="updatetableId" style="padding-bottom:10px" width="100%" cellspacing="0" cellpadding="4" border="0" class="settingtable">
	<tbody>
		<tr>
			<td colspan="2">
				<div style="font-size:14px;padding:8px 0 4px 2px;margin:4px 15px" class="addr_line">
				修改机构信息				</div>
			</td>
		</tr>
		<tr class="normal black">
			<td align="right"><div style="width:120px;white-space:nowrap; text-align:right;"> 机构名称：</div></td>
			<td width="96%" align="left"><input maxlength="15" id="orgName" name="orgName" type="text" value="${org.orgName }" onblur="nameMsg()" size="28" class="txt noime" autocomplete="off">&nbsp;<font color="#FF0000">*</font><span id="span1"></span></td>
			
		</tr>
		
	</tbody>
	
</table>
</form>

<table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
	<tbody>
		<tr>
			<td nowrap="" align="left" class="barspace toolbgline">
				<a id="submitbtn" class="button_gray_s" name="submitbtn1" onclick="doValSubmit();" href="javascript:void(0);" style="float:left; margin-right:5px;" title="保存更改">保存更改</a>
				<a id="cancel" class="button_gray_s" name="submitbtn" href="${pageContext.request.contextPath}/organizationManager/orgList.do" style="float:left;" title="取消">取消</a>
			</td>
		</tr>
	</tbody>
</table>

<!-- span做alert使用，用户操作成功之后，会在浏览器的正中间提示.span的会变化 在alertMsg()中做处理。 -->
<span style="display:none;" class="msg" id="msg">已将邮件成功删除&nbsp;</span>

</body>
</html>


<script type="text/javascript">
function wndback(){
		window.history.back();
		return false;
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
 }

 */
function nameMsg(){
	var span = document.getElementById("span1");
	var name = document.getElementById("orgName").value;
	name = name.replace(/^\s+|\s+$/g, '');
	span.innerHTML = "";
		if(name == ""){
			var msg = '名称不能为空';
			span.innerHTML = msg;
			span.style.color='#ff0000';
		    span.style.fontSize='12px';
		  
		}
		if(name.length >15){
			var msg = '长度限制为15';
			span.innerHTML = msg;
			span.style.color='#ff0000';
		    span.style.fontSize='12px';
		}
} 

function userCheck() {
	var textuser = document.getElementById("orgName").value;
	textuser = textuser.replace(/^\s+|\s+$/g, '');
	if(textuser == ""){
		//alert("名称不能为空");
		return false;
	}else if(!isEnChNum(textuser)){//通过验证true 不通过false
		adtecUtil.showMsg("机构名称不能包含特殊字符");
		return false;
	}else if(textuser.length > 15){
		//alert("长度限制为15");
		//$("#msg").html("长度限制为15");
		//showMsg();
		adtecUtil.showMsg("长度限制为15");
	    return false;
	}else{
		
		
		return true;	
	}
}

//清除Table
function clearRowsSMS(){
	var tableLen = document.getElementById('updatetableId').rows.length;
	if(tableLen > 1){
		for(var i=0; i<tableLen; i++){
			document.getElementById('updatetableId').deleteRow(tableLen-i-1);
		}
	}
}


//提交form表单
function doValSubmit(){
	$('#form1').submit();
}

//验证，只能是字母，数字，下划线，中文
function isEnChNum(strName){
    var str = $.trim(strName);
	var regu = /^[a-zA-Z0-9_ \u4e00-\u9fa5]+$/; //字母数字下划线 中文 空格
	if(regu.test(str)){
		return true;
	}
	return false;
    
}
/* function submit(){
	var orgName = document.getElementById("orgName");
	alert(orgName);
	
} */

</script>