<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.adtec.com.cn" prefix="adtec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 显示当前页面内容 -->
<adtec:localFileName isShow="false"></adtec:localFileName>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/jquery-1.4.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>
<link href="${pageContext.request.contextPath}/ui/css/skin.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/ui/css/common.css" type="text/css" rel="stylesheet">
<style type="text/css">

/* ===========联系人导入==========  */
div {
	text-align:left;
}
.bd {
	width:500px;
	padding:1px;
	margin-top:15px;
}
.biginfo {
	font:bold 16px Verdana;
	padding:12px 0 12px 16px;
}
.tpinfo {
	background:#fff;
	padding:15px 20px;
	color:#6a727a;
	border-left:none;
	border-right:none;
	border-bottom:none;
}
.tpinfo b {
	color:#000;
}
.tpinfo1 {
	background:#fff;
	padding:0px 20px 20px 0;
	text-align:right;
}
.biginfo_m {
	font:bold 16px Verdana;
	color:#4a8f00;
}
.tpinfo_m {
	margin:20px 0 0 50px;
}
.input_file {
	width:260px
}
/* ==================== 联系人导入============ */
.bd {
	border:1px solid #a7c5e2;
}
.bd_upload {
	border:1px solid #4e86c4;
}
.biginfo {
	background:#ebf3fb;
	color:#4a8f00;
}
.tpinfo {
	background:#fff;
	border-top:1px solid #d2e2f4;
	color:#6a727a;
}
</style>


</head>

<body>
    <form name="form1" action="accountLeadingIn.do" method="post" enctype="multipart/form-data"
    onsubmit="return fileCheck();">
        <div class="bd">
            <div class="biginfo">
                导入端点帐号
            </div>
            <div class="tpinfo">
                <div style="height:16px">
                    请导入从Excel保存为"Unicode 文本"格式的TXT文件。
                    <!-- &nbsp;<a href="/app/import/example.php?type=contact" target="_blank">»下载范例</a>-->
                </div>
                <div style="margin:18px 0 8px 61px">
                    <b>
                        选择导入文件:&nbsp;&nbsp;
                    </b>
                    <input class="input_file" id="file" name="file" type="file">
                </div>
                <div style="margin:10px 0 8px 61px">
                    <b>
                        选择导入格式:&nbsp;&nbsp;
                    </b>
                    <select name="importformat" id="importformat">
                        <option value="csv">
                            csv文件(*.csv)
                        </option>
                    </select>
                </div>
                <div style="margin:10px 0 8px 74px">
                    <!-- <b>联系人群组:&nbsp;&nbsp;</b>
                    <select style="width:187px;" class="sel1" name="selGroup" id="selGroup">
                    </select>
                    &nbsp;&nbsp;<a onclick="return addDep();">新增群组</a>
                    -->
                    <span style="text-align:right;" id="spanFile">
                    </span>
                </div>
            </div>
            <div class="tpinfo1">
                <input type="submit" value="立即导入" class="btn btn_true" id="but_submit">
                <input type="button" onclick="window.history.back();" id="but_cancel"
                class="btn btn_true" value="取消">
            </div>
        </div>
    </form>
</body>

</html>

<script type="text/javascript">
function fileCheck(){
	var fileName = document.getElementById("file");
	var filetext = fileName.value;
	
	var span = document.getElementById("spanFile");
	span.innerHTML = "";
		
	//alert(filetext);
	if(filetext==''){
		
		msg = '请上传要导入的文件';
		span.innerHTML = msg;
		span.style.color='#ff0000';
	    span.style.fontSize='12px';
		
		return false;	//提示没有选择要导入的文件
		
	}else{
		var result = /\.[^\.]+/.exec(filetext);			//获取文件后缀名
		if(result.toString().toLowerCase() == '.csv' ){	//toString不可省去
			return true;
		}else{
			//alert("请选择CSV格式文件导入");
			
			msg = '请选择CSV格式文件导入';
			span.innerHTML = msg;
			span.style.color='#ff0000';
		    span.style.fontSize='12px';
			
			return false;
		}
	}
	
}
</script>