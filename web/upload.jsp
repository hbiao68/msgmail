<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/mao.js"></script>

<script language="JavaScript">

function fileCheck(){
	var fileName = document.getElementById("file");
	var filetext = fileName.value;
	var result = /\.[^\.]+/.exec(filetext);			//获取文件后缀名
	if(result.toString().toLowerCase() == '.csv' ){	//toString不可省去
		return true;
	}else{
		alert("请选择CSV格式文件导入");
		return false;
	}
}
	
</script>


</head>
<body>
<%--		 action="<%=request.getContextPath()%>/fileUpload/upload.do"  --%>
	
	<form name="form1" action="fileUpload/upload.do" method="post" enctype="multipart/form-data" onsubmit="return fileCheck();">
		选择上传文件：<input id="file" name="myfile" type="file"  >
		<input type="submit" name="submit" value="上传" onsubmit="fileCheck();">
		
		选择上传文件：<input id="file" name="myfile1" type="file"  >
		<input type="submit" name="submit" value="上传" onsubmit="fileCheck();">
	</form>
	
	

</body>
</html>