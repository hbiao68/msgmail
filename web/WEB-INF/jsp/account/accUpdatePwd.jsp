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
.msg {
	color: #FFFFFF;
	background: #68af02;
	font-size: 12px;
	padding: 3px 24px 3px;
	z-index: 1;
	position: absolute;
}
</style>

</head>
<body onload="init()">
	<form action="<%=request.getContextPath()%>/account/updatePwdAction.do"
		method="post" id="myform">
		<table width="100%" cellspacing="0" cellpadding="2" border="0"
			class="toolbg">
			<tbody>
				<tr>
					<td nowrap="" align="left" class="barspace toolbgline"><br>
					<br></td>
				</tr>
			</tbody>
		</table>

		<table style="padding-bottom: 10px" width="100%" cellspacing="0"
			cellpadding="4" border="0" class="settingtable">
			<tbody>
				<tr>
					<td colspan="100%">
						<div
							style="font-size: 14px; padding: 8px 0 4px 2px; margin: 4px 15px"
							class="addr_line">修改密码</div>
					</td>
				</tr>

				<tr class="normal black">
					<td align="right"><div
							style="width: 120px; white-space: nowrap; text-align: right;">
							现在的密码：</div></td>
					<td width="96%" align="left"><input id="CurrentEncryptedPassword" type="password"
					    size="28" class="txt noime" 
						name="CurrentEncryptedPassword" maxlength="30" required onchange="checkCurrentEncryptedPassword()">&nbsp;<font
						color="#FF0000" >*</font><font class="Prompt"
						style="color: red; font-size: 12px"></font></td>
				</tr>
				
				<tr class="normal black">
					<td align="right">设置新的密码：</td>
					<td align="left">
						<input type="password" name="NewEncryptedPassword" id="mypassword" maxlength="32" size="28"  class="txt noime" required autocomplete="off">&nbsp;<font color="red">*</font>
					</td>
				</tr>

				<tr class="normal black">
					<td align="right">请再次新输入密码：</td>
					<td align="left">
						<input type="password" name="NewReuserEncryptedPassword" id="reuserEncryptedPassword" equalTo="#mypassword" maxlength="32" size="28"  class="txt noime" required autocomplete="off">&nbsp;<font color="red">*</font>
					</td>
				</tr>
			</tbody>
		</table>

		<table width="100%" cellspacing="0" cellpadding="2" border="0"
			class="toolbg">
			<tbody>
				<div style="" class="list_btline">
					<div class="f_size selbar_bt barspace2"
						style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px"
						align="center">
						<input type="submit" name="next" title="修改" value="修改" class="add button">&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" name="next" title="返回" value="返回" class="button" onclick="return onReturn('${username }')">
					</div>
				</div>
			</tbody>
		</table>
		<input type="hidden" name="username" value="${username }" id="username">
		<input type="hidden" value="${PromptPwdMsg}" class="PromptPWdMsg">
	</form>
	<span style="display: none;" class="msg" id="msg">删除成功&nbsp;</span>
</body>

<script type="text/javascript">
	//初始化数据
	function init() {
		//maojd update date:11:29 2013/12/30	页面一旦刷新，外部adtecUtil.js中定时 的隐藏 操作成功提示信息的方法失效。所以onload方法中，执行隐藏方法
		$(window.parent.document).find("#msgBoxDIV").fadeOut("slow");
		
		//添加或修改的提示信息
		var PromptPWdMsg = $('.PromptPWdMsg').val();
		if(PromptPWdMsg == "updatePwdMsg"){
			//$("#msg").html("请输入正确的当前密码！");
			//showMsg();
			adtecUtil.showMsg("请输入正确的当前密码");
		}else{
		}
	}
	//表单校验
	 $(function(){
			//指明校验什么表单
			$("#myform").validate();
		});

	//返回到登陆页面
	function onReturn(obj){
		with(document.forms[0])
		   {
				action="<%=request.getContextPath()%>/account/accUpdate.do?username="+ obj;
				submit();
		   }
	}
	//校验原有密码是否正确
	function checkCurrentEncryptedPassword(){
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/account/checkCurrentEncryptedPassword.do",
			dataType : "json",
			data:{"CurrentEncryptedPassword":$("#CurrentEncryptedPassword").val(),"username":$("#username").val()},
			success : function(msg) {
				if(msg == 1){
					$('.Prompt').text('当前密码输入正确');
					$('.add').attr("disabled","");
				}
				else{
					$('.Prompt').text('请输入正确的当前密码!');
					$('.add').attr("disabled","disabled");
				}
			}
		});
	}

</script>
</html>