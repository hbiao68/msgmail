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
</style>

</head>
<body>
	<form action="<%=request.getContextPath()%>/user/insertAction.do"
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
							class="addr_line">用户添加</div>
					</td>
				</tr>

				<tr class="normal black">
					<td align="right"><div
							style="width: 120px; white-space: nowrap; text-align: right;">
							用户名：</div></td>
					<td width="96%" align="left"><input id="userName" type="text"
					    size="28" class="txt noime"
						name="userName" maxlength="30" required onchange="checkUserName()">&nbsp;<font
						color="#FF0000" >*</font> <font class="tishi"
						style="color: red; font-size: 12px"></font></td>
				</tr>

				<tr class="normal black">
					<td align="right">密码：</td>
					<td align="left">
						<input type="password" name="userPassword" id="mypassword" maxlength="32" size="28"  class="txt noime" required autocomplete="off">&nbsp;<font color="red">*</font>
					</td>
				</tr>

				<tr class="normal black">
					<td align="right">请再次输入密码：</td>
					<td align="left">
						<input type="password" name="reuserPassword" id="reuserPassword" equalTo="#mypassword" maxlength="32" size="28"  class="txt noime" required autocomplete="off">&nbsp;<font color="red">*</font>
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
						<input type="submit" name="next" title="添加" value="添加" class="add button">&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" name="next" title="返回" value="返回" class="button" onclick="return onSelect()">
					</div>
				</div>
			</tbody>
		</table>
	</form>

</body>

<script type="text/javascript">

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
	    			  //alert(typeof msg);//boolean
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
		    	action="<%=request.getContextPath()%>/user/list.do";
		    	submit();
		   }
	}

</script>
</html>