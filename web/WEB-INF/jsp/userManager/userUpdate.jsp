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
	<form action="<%=request.getContextPath()%>/user/updateAction.do"
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
							class="addr_line">修改用户信息</div>
					</td>
				</tr>

				<tr class="normal black">
					<td align="right"><div
							style="width: 120px; white-space: nowrap; text-align: right;">
							用户名：</div></td>
					<td width="96%" align="left"><input id="userName" type="text"
					    size="28" class="txt noime" value="${user.username }"
						name="userName" maxlength="30" required onchange="checkUserName()">&nbsp;<font
						color="#FF0000" >*</font> <font class="tishi"
						style="color: red; font-size: 12px"></font></td>
				</tr>

				<tr class="normal black">
					<td align="right">密码：</td>
					<td align="left">
						<a href="#" title="修改密码" onclick="return onUpdatePwd()" style ="text-decoration: underline">修改密码</a>
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
						<input type="submit" title="修改" name="next" value="修改" class="add button">&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" title="返回" name="next" value="返回" class="button" onclick="return onSelect()">
					</div>
				</div>
			</tbody>
		</table>
		<input type="hidden" name="uid" value="${user.userid }">
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
			//$("#msg").html("密码修改成功！");
			//showMsg();
			adtecUtil.showMsg("密码修改成功");
		}else{
		}
	}


	//表单校验
	 $(function(){
			//指明校验什么表单
			$("#myform").validate();
		});

	//校验新添加的帐号是否重复
	function checkUserName(){
		if(onEnglith()){
			var userName=$("#userName").val();
	    	/* accountName=accountName.trim(); */
	    	userName=userName.replace(/^\s+|\s+$/g, '');
	    	$.ajax({
	    		 type: "POST",
	    		 dataType:"json",
	    		 data: "userName="+userName,
	    		 url:  "<%=request.getContextPath()%>/login/checkUserName.do", 
	    		  success: function(msg){
	    		      if(msg == false){
	    				   $('.tishi').text('此帐号可使用');
	    				   $('.add').attr("disabled","");
	    				   
	    			   }else{
	    				   $('.tishi').text('此帐号已存在');
	    				   $('.add').attr("disabled","disabled");
	    			   }
	    		   }	    		   
	    		});
		}else{}
	}
	
	    //验证accountName是否是英文
	    function onEnglith(){
	   //  var strName=${"#strName"}.val();
	   var strName=document.getElementById('userName').value;
	     if(isValidTrueName(strName)){
			   $('.tishi').text('该帐号可正常使用');
			   $('.add').attr("disabled","");
			   return true;
	     }
	     else{
			   $('.tishi').text('请输入英文或数字帐号');
			   $('.add').attr("disabled","disabled");
			   return false;
	     }
	
	}

	function isValidTrueName(strName){
		      //   var abc = strName.trim();
		       var strNameTrim = strName.replace(/^\s+|\s+$/g, '');
	  //    var str = Trim(strName);   //判断是否为全英文大写或全中文，可以包含空格
	        var reg = /^[a-z A-Z 0-9 _]+$/;
	        if(reg.test(strNameTrim)){
	         return true;
	        }
	        return false;
	}
	
	//返回到登陆页面
	function onSelect(){
		with(document.forms[0])
		   {
		    	window.location.href="<%=request.getContextPath()%>/user/list.do";
		   }
	}
	
	//跳转到修改密码页面
	function onUpdatePwd(){
		with(document.forms[0]){
			action="<%=request.getContextPath()%>/user/updatePwd.do";
			submit();
		}
	}
	
	
	/**
	 *  span作 alert使用的显示方法
	 */
	 /*  function showMsg(msgHtml){
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

		var leftPx = (winWidth - msgWidth) / 2;
		var topPx = 0;
		$("#msg").css({
			left : leftPx + "px",
			top : topPx + "px"
		}).slideDown("fast");
		setTimeout("hidenMsg()", 2000);

	}
	//隐藏msg
	 function hidenMsg(){
	 	//$("#msg").fadeOut("slow");
	 	$(window.parent.document).find("#msgBoxDIV").fadeOut("slow");
	 }
	 */
</script>
</html>