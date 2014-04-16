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
	<form action="<%=request.getContextPath()%>/account/insertAction.do"
		method="post" id="myform" onsubmit="return false;">
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
							class="addr_line">
								帐号添加
							</div>
					</td>
				</tr>		

				<tr class="normal black">
					<td align="right"><div
							style="width: 120px; white-space: nowrap; text-align: right;">
							帐号：</div></td>
					<td width="96%" align="left"><input id="username" type="text"
					    size="28" class="txt noime"
						name="username" maxlength="64" required onchange="checkusername()">&nbsp;<font
						color="#FF0000" >*</font> <font class="tishi"
						style="color: red; font-size: 12px"></font></td>
				</tr> 
		

                <tr class="normal black">
                    <td align="right">名称：</td>
                    <td align="left">
                        <input type="text" name="name" size="28" class="txt noime"
                        maxlength="100" autocomplete="off">
                    </td>
                </tr>
                
                <tr class="normal black">
                    <td align="right">电子邮件：</td>
                    <td align="left">
                        <input type="text" id="email" name="email" size="28" class="txt noime"
                        maxlength="100" autocomplete="off" onblur="emailCheck()">
                        
                        &nbsp;
                            <font id="emailMsg" style="font-size: 12px;color: red;">
                            </font>
                    </td>
                </tr>


				<tr class="normal black">
					<td align="right">密码：</td>
					<td align="left">
						<input type="password" name="encryptedpassword" id="mypassword" maxlength="32" size="28"  class="txt noime" required autocomplete="off">&nbsp;<font color="red">*</font>
					</td>
				</tr>

				<tr class="normal black">
					<td align="right">请再次输入密码：</td>
					<td align="left">
						<input type="password" name="reencryptedpassword" id="reuserPassword" equalTo="#mypassword" maxlength="32" size="28"  class="txt noime" required autocomplete="off">&nbsp;<font color="red">*</font>
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
			//$("#myform").validate();
		 $("#myform").validate({
			 onsubmit:true,// 是否在提交时验证,默认也是true 
			 
			 //validate外部js验证通过之后，点击提交表单回调 此函数 
			 submitHandler: function(form) {  
				// alert(form);
				//验证账号，在account自己控制添加按钮的disable属性
				
				checkusername();
				// 验证邮箱
				if(!emailCheck()){
					//alert("email");
					alert(1234);
					return false;
				};
				//alert("真的提交 ");
			    with(document.forms[0]){
						action="<%=request.getContextPath()%>/account/insertAction.do";
						submit();
				}
			    //form.submit();提交表单，如果不写，即便通过表单也不会自动提交 
			}, 
		      
			 //不通过，点击提交回调 
			invalidHandler: function(form, validator) { 
				//alert("not pass验证没有通过"); 
			    return false;
			},
		});
	 });

	//校验新添加的帐号是否重复
	function checkusername(){
		if(onEnglith()){
			var username=$("#username").val();
	    	username = $.trim(username);
	    	$.ajax({
	    		 type: "POST",
	    		 dataType:"json",
	    		 data: "username="+username,
	    		 url:  "<%=request.getContextPath()%>/account/checkusername.do", 
	    		  success: function(msg){
	    		      if(msg == "0"){
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
	   var strName=document.getElementById('username').value;
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
			window.location.href="<%=request.getContextPath()%>/account/accList.do";	   
		   }
	}
	
	
	/*  maojd update 邮箱验证中文 */ 
	function emailCheck(){
		var email = document.getElementById("email").value.replace(/^\s+|\s+$/g, '');;
		var emailMsg = document.getElementById("emailMsg");
		if(isEmail(email)){
			emailMsg.innerText = "";
			return true;
		}else if(email == ""){
			emailMsg.innerText = "";
			return true;
		}else{
			//console.info(emailMsg);
			emailMsg.innerText = "请输入正确格式的电子邮件";
			//$("#emailMsg").focus();
			return false;
		}
	}
	 
	function isEmail(temp){
		//对电子邮件的验证
		//var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{1,9}){1,9})$/;
		//var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])$/;
        return reg.test(temp);
	}

</script>
</html>