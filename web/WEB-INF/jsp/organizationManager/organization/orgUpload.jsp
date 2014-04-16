<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.adtec.com.cn" prefix="adtec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 显示当前页面内容 -->
<adtec:localFileName isShow="true"></adtec:localFileName>
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/ui/css/skin.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/ui/css/common.css" type="text/css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/js/util/jquery-1.4.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>
<style type="text/css">



/* ===========机构导入==========  */
div {text-align:left;}
.bd {width:500px;padding:1px;margin-top:15px;}
.biginfo {font:bold 16px Verdana;padding:12px 0 12px 16px;}
.tpinfo {background:#fff;padding:15px 20px;color:#6a727a;border-left:none;border-right:none;border-bottom:none;}
.tpinfo b {color:#000;}
.tpinfo1 {background:#fff;padding:0px 20px 20px 0;text-align:right;}
.biginfo_m {font:bold 16px Verdana;color:#4a8f00;}
.tpinfo_m {margin:20px 0 0 50px;}
.input_file {width:260px}


/* ==================== 机构导入============ */
.bd {border:1px solid #a7c5e2;}
.bd_upload {border:1px solid #4e86c4;}
/* .biginfo {background:#ebf3fb;color:#4a8f00;} */
.biginfo {background:#c1d9f3;color:#4a8f00;}
.tpinfo {background:#fff;border-top:1px solid #d2e2f4;color:#6a727a;}


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

<center>

<form name="form1" action="${pageContext.request.contextPath}/organizationManager/orgLeadingIn.do" method="post" enctype="multipart/form-data" onsubmit="return fileCheckOnSubmit();">

		
		<div class="bd">
				<div class="biginfo">导入机构</div>
				<div class="tpinfo">
					<div style="height:16px">请导入从Excel保存为"Unicode 文本"格式的TXT文件。<!-- &nbsp;<a href="/app/import/example.php?type=contact" target="_blank">»下载范例</a>--></div> 
					<div style="margin:18px 0 8px 61px">
						<b>选择导入文件:&nbsp;&nbsp;</b>
						<input class="input_file"  id="file" name="file" type="file" onchange="fileCheck()">
					</div>
					<div style="margin:10px 0 8px 61px">
						<b>选择导入格式:&nbsp;&nbsp;</b>
						<select name="importformat" id="importformat">
							<option value="csv">csv文件(*.csv)</option>
							
						</select>
					</div>
					
					<div style="margin:10px 0 8px 74px">
					<!-- 	<b>联系人群组:&nbsp;&nbsp;</b>
						<select style="width:187px;" class="sel1" name="selGroup" id="selGroup">
						</select>
						&nbsp;&nbsp;<a onclick="return addDep();">新增群组</a>
					 -->
						 <span style="text-align:right;" id="spanFile"></span>
					 </div>
				</div>
				<div class="tpinfo1">
					<input type="submit" value="立即导入" class="btn btn_true" id="but_submit">
					<!-- <input type="button" onclick="window.history.back();" id="but_cancel" class="btn btn_true" value="取消"> -->
					<input type="button" onclick="orgUploadBack();" id="but_cancel" class="btn btn_true" value="取消">
				</div>
			</div>
		
		

			
</form>


<!-- span做alert使用，用户操作成功之后，会在浏览器的正中间提示.span的会变化 在alertMsg()中做处理。 -->
<span style="display:none;" class="msg" id="msg">已将邮件成功删除&nbsp;</span>

</center>

</body>
</html>


<script type="text/javascript">



function orgUploadBack(){
	//hideMenu();
	window.location.href="${pageContext.request.contextPath}/organizationManager/orgList.do";
}

function hideMenu(){
	 $("#windownbg").remove();
	 $("#windown-box").fadeOut("fast",function(){$(this).remove();});
}

/**
 *  span作 alert使用的显示方法
 */
 /* function showMsg(msgHtml){
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

function fileCheck(){
	var fileName = document.getElementById("file");
	var filetext = fileName.value;
	
	var span = document.getElementById("spanFile");
	span.innerHTML = "";
		
	//alert(filetext);
	if(filetext==''){
		
		var msg = '请上传要导入的文件';
		span.innerHTML = msg;
		span.style.color='#ff0000';
	    span.style.fontSize='12px';
		
		return false;	//提示没有选择要导入的文件
		
	}else{
		var result=/\.[^\.]+$/.exec(filetext);
		//var result = /\.[^\.]+/.exec(filetext);			//获取文件后缀名.文件有名有'.'的时候获取的不对   eg:  文件.1.0.csv 
		if(result.toString().toLowerCase() == '.csv' ){	//toString不可省去
			return true;
		}else{
			//alert("请选择CSV格式文件导入");
			var msg = '请选择CSVnnnnn格式文件导入';
			span.innerHTML = msg;
			span.style.color='#ff0000';
		    span.style.fontSize='12px';
			return false;
		}
	}
	
}

function fileCheckOnSubmit(){
	var fileName = document.getElementById("file");
	var filetext = fileName.value;
	
	var span = document.getElementById("spanFile");
	span.innerHTML = "";
		
	//alert(filetext);
	if(filetext==''){
		
		var msg = '请上传要导入的文件';
		span.innerHTML = msg;
		span.style.color='#ff0000';
	    span.style.fontSize='12px';
		//alert("请选择要导入的文件");
		return false;	//提示没有选择要导入的文件
		
	}else{
		var result=/\.[^\.]+$/.exec(filetext);
		//var result = /\.[^\.]+/.exec(filetext);			//获取文件后缀名.文件有名有'.'的时候获取的不对   eg:  文件.1.0.csv 
		if(result.toString().toLowerCase() == '.csv' ){	//toString不可省去
			return true;
		}else{
			//alert("请选择CSV格式文件导入");
			
			var msg = '请选择CSV格式文件导入';
			span.innerHTML = msg;
			span.style.color='#ff0000';
		    span.style.fontSize='12px';
			//alert("请选择CSV格式文件导入");
			return false;
		}
	}
	
}
</script>