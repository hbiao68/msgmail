<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.adtec.com.cn" prefix="adtec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript" src="${pageContext.request.contextPath}/js/util/jquery-1.4.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>
<link href="${pageContext.request.contextPath}/ui/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/skin.css" rel="stylesheet" type="text/css">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 显示当前页面内容 -->
<adtec:localFileName isShow="true"></adtec:localFileName>
<title>Insert title here</title>
</head>
<body>

<table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
	<tbody>
		<tr>
			<td nowrap="" align="left" class="barspace toolbgline">
				<a id="submitbtn" class="button_gray_s" name="submitbtn1" onclick="doValSubmit();" href="javascript:void(0);" style="float:left; margin-right:5px;" title="保存更改">保存更改</a>
				<a id="cancel" class="button_gray_s" name="submitbtn" href="${pageContext.request.contextPath}/organizationDefManager/queryAllOrgDef.do" style="float:left;" title="取消">取消</a>
			</td>
		</tr>
	</tbody>
</table>

		
<form id="form1" action="<%=request.getContextPath()%>/organizationDefManager/updateOrgDef.do" method="post" onsubmit="return userCheck();">
<table style="padding-bottom:10px" width="100%" cellspacing="0" cellpadding="4" border="0" class="settingtable">
	<tbody>
		<tr>
			<td colspan="2">
				<div style="font-size:14px;padding:8px 0 4px 2px;margin:4px 15px" class="addr_line">
				修改机构分级				</div>
			</td>
		</tr>
		<tr class="normal black">
			<td align="right"><div style="width:120px;white-space:nowrap; text-align:right;"> 层级：</div></td>
			<td width="96%" align="left"><input readonly="readonly" type="text" value="${orgDef.level_id }" size="28" class="txt noime" name="level_id" id="level_id" autocomplete="off" style="background-color: #f2f4f6;">&nbsp;<font color="#FF0000">*层级不可修改</font></td>
		
		</tr>
		<tr class="normal black">
			<td align="right">名称：</td>
			<td align="left"><input type="text" value="${orgDef.name}" size="28" class="txt noime" maxlength="15" id="name" name="name" onblur="nameMsg()" autocomplete="off">&nbsp;<font color="#FF0000">*<span id="span1"></span>  </font></td>
			
		</tr>
	</tbody>
	
</table>
</form>

<table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
	<tbody>
		<tr>
			<td nowrap="" align="left" class="barspace toolbgline">
				<a id="submitbtn" class="button_gray_s" name="submitbtn1" onclick="doValSubmit();" href="javascript:void(0);" style="float:left; margin-right:5px;" title="保存更改">保存更改</a>
				<a id="cancel" class="button_gray_s" name="submitbtn" href="${pageContext.request.contextPath}/organizationDefManager/queryAllOrgDef.do" style="float:left;" title="取消">取消</a>
			</td>
		</tr>
	</tbody>
</table>


</body>
</html>
<script type="text/javascript">
function wndback(){
		window.history.back();
		return false;
}

function nameMsg(){
	var span = document.getElementById("span1");
	var name = document.getElementById("name").value;
	name = name.replace(/^\s+|\s+$/g, '');
	//span.innerHTML = "nihao";
		if(name == ""){
			var msg = '名称不能为空';
			span.innerHTML = msg;
			span.style.color='#ff0000';
		    span.style.fontSize='12px';
		}
		if(name.length > 15){
			var msg = '长度限制为15';
			span.innerHTML = msg;
			span.style.color='#ff0000';
		    span.style.fontSize='12px';
		}
		
} 

function userCheck() {
	var textuser = document.getElementById("name").value;
	textuser = textuser.replace(/^\s+|\s+$/g, '');
	
	if(textuser == ""){
		//alert("名称不能为空");
		return false;
	} else if(!isEnChNum(textuser)){//通过验证true 不通过false
		adtecUtil.showMsg("机构名称不能包含特殊字符");
		return false;
	} else if(textuser.length > 15){
		//alert("长度限制为15");
	    return false;
	}else{
		//验证是否重复
		var isRepead = false;//不重复
		$.ajax({
			   async:false,
			   type: "POST",
			   url: "${pageContext.request.contextPath}/organizationDefManager/isRepeatOrgDefName.do",
			   data: "orgDefName="+textuser,
			   success: function(msg){
				   //alert(msg);
				   if(msg == "true"){//重复了
					  //return false;
					   adtecUtil.showMsg("机构分级名称不可重复");
					   isRepead = true;
				   };
				   
			   }
		});
		if(isRepead){//重复了
			return false;
		}
		return true;
		
	};
}



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
</script>