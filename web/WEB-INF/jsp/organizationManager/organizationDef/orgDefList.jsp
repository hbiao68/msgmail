<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.adtec.com.cn" prefix="adtec"%>

<c:set var="viewKey"  value="${resid}1001"></c:set>
<c:set var="viewPrivlg" value="${oneUserAllPrivlgMap[viewKey].actionValue}"></c:set>
<c:set var="addKey"  value="${resid}1002"></c:set>
<c:set var="addPrivlg" value="${oneUserAllPrivlgMap[addKey].actionValue}"></c:set>
<c:set var="updateKey"  value="${resid}1003"></c:set>
<c:set var="updatePrivlg" value="${oneUserAllPrivlgMap[updateKey].actionValue}"></c:set>
<c:set var="delKey"  value="${resid}1004"></c:set>
<c:set var="delPrivlg" value="${oneUserAllPrivlgMap[delKey].actionValue}"></c:set>


<c:if test="${viewPrivlg || wholePrivlg}">
										
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 显示当前页面内容 -->
<adtec:localFileName isShow="true"></adtec:localFileName>

<title>Insert title here</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/jquery-1.4.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>
<link href="${pageContext.request.contextPath}/ui/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/skin.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/add.css" rel="stylesheet" type="text/css">

<style type="text/css">
table {
	table-layout: fixed;
}

table tr td,table tr td {
	width: 200px;
	/* text-align: center; */
}

th{
	font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
	color: #4f6b72;
	border-right: 1px solid #C1DAD7;
	border-bottom: 1px solid #C1DAD7;
	border-top: 1px solid #C1DAD7;
	letter-spacing: 2px;
	text-transform: uppercase;
	text-align: center;
	padding: 6px 6px 6px 12px;
	background: #CAE8EA no-repeat;
	
}

.msg {
	color: #FFFFFF;
	background: #68af02;
	font-size: 12px;
	padding: 3px 24px 3px;
	z-index: 1;
	position:absolute;
}

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
<body onload="onload()">


<div style="_width:99%" class="txt_title">
			
			机构等级&nbsp;
			 <span class="f_size normal black" id="_ut"> (共 <span id="_ut_c" class="red"><b>${fn:length(organizationDefs)}</b></span> 级)</span>
		</div>



<table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
	<tbody>
		<tr>
			<td colspan="3" nowrap="" align="left" class="barspace toolbgline">
				<%-- <a id="submitbtn" class="button_gray_s" name="submitbtn1" href="<%=request.getContextPath()%>/relation/list.do;" style="float:left; margin-right:5px;width:80px">更改开通级别</a> --%>
				<a id="submitbtn" class="button_gray_s" name="submitbtn1" href="#" onclick="orgRelationList()" style="float:left; margin-right:5px;width:80px" title="更改开通级别">更改开通级别</a>
			</td>
		</tr>
	</tbody>
</table>



		<table cellspacing="0" cellpadding="0" class="O2" style="table-layout:fixed;width:100%;*width:auto;">
			<tbody>
				<tr style="height:20px;">
					<td width="40%" class="o_title2">层级</td>
					<td  width="40%" class="o_title2">名称</td>
					<td width="20%" class="o_title2">操作</td>
				</tr>
			</tbody>
		</table>


		<c:if test="${organizationDefs == null || fn:length(organizationDefs) == 0}">
			<div id="tips_bar">
				<div class="nomail"><b>当前没有数据</b></div>
			</div>
			
		</c:if>
	


	<c:forEach items="${organizationDefs}" var="OrganizationDef">
			<div style="overflow:hidden;" class="M">
				<div class="M_l">
					<table cellspacing="0" cellpadding="0" style="table-layout:fixed;width:100%;*width:auto;">
						<tr style="height:20px;">
							<td width="50%" >第 ${OrganizationDef.level_id } 层级</td>
							<td name="orgDefExistName" style="text-align: left;" width="50%" >${OrganizationDef.name }</td>
							<td>
								<!-- 是最后一个等级 删除-->
								<c:if test="${OrganizationDef.level_id == maxId}">
										<c:if test="${updatePrivlg || wholePrivlg}">
											<a href="${pageContext.request.contextPath}/organizationDefManager/updateOrgDefPage.do?level_id=${OrganizationDef.level_id }">修改</a>
										</c:if>
										<!-- 正确的 -->
										<%-- <a href="${pageContext.request.contextPath}/organizationDefManager/deleteOrgDef.do?level_id=${OrganizationDef.level_id }" onclick="return confirm('您确定要删除该等级信息吗?')">删除</a> --%> 
										
										<!-- 行不通 -->
										<%-- <a href="${pageContext.request.contextPath}/organizationDefManager/deleteOrgDef.do?level_id=${OrganizationDef.level_id }" onclick="returnConfirm('您确定要删除该等级信息吗?');return false;">删除</a> --%>
										<c:if test="${delPrivlg || wholePrivlg}">
											<a href="#" onclick="deleteOrgDef(this,${OrganizationDef.level_id})">删除</a>
										</c:if>
									
								</c:if>
								<c:if test="${OrganizationDef.level_id != maxId}">
										<c:if test="${updatePrivlg || wholePrivlg}">
											<a href="${pageContext.request.contextPath}/organizationDefManager/updateOrgDefPage.do?level_id=${OrganizationDef.level_id }">修改</a>
										</c:if>
										<!-- <a href="#" onclick="return confirm('还未删除最小级别')" >删除</a>&nbsp; -->
										
										<c:if test="${delPrivlg || wholePrivlg}">
											<a href="#" onclick="returnMsg('还未删除最小级别')" >删除</a>&nbsp;
										</c:if>
								</c:if>	
							</td>
						</tr>
					</table>
				</div>
			</div>
	</c:forEach>


<form action="${pageContext.request.contextPath}/organizationDefManager/addOrgDef.do" method="post" name="myForm" onsubmit="return userCheck();">
<table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
	<tbody>
		<tr>
			<td style="width:100px;text-align:right;" colspan="3" nowrap="" align="right" class="barspace toolbgline">
					选择层级：
					<select name="level_id" id="level_id">
						<c:if test="${organizationDefs == null || fn:length(organizationDefs) == 0}">
							<option value="1">第1层</option>
						</c:if>
						
						<c:if test="${organizationDefs != null && fn:length(organizationDefs) != 0}">
							<option value="${fn:length(organizationDefs)+1 }">
								第	${fn:length(organizationDefs)+1 } 层
							</option>
						</c:if>
					</select > 
			</td>
			
			<td style="width:200px;text-align:center;" colspan="3" nowrap="" align="left" class="barspace toolbgline">
					名称：<input maxlength="15" id="name" type="text" name="name" onBlur="nameMsg()">	<font color="red">*</font>
					<span id="span1"></span>
					<input title="添加" class="button" type="submit" value="添加" onsubmit="userCheck();">
					
			</td>
		</tr>
	</tbody>
	
</table>
</form>



<!-- span做alert使用，用户操作成功之后，会在浏览器的正中间提示.span的会变化 在alertMsg()中做处理。 -->
<span style="display:none;" class="msg" id="msg">已将邮件成功删除&nbsp;</span>
<!-- 从后台获取操作成功信息的判断。来判断做的什么操作 -->
<input id="sucMsg" type="hidden" value="${sucMsg}">

<!-- 这个form做页面刷新所用。这种刷新方式可以清除el表达式在input中的值 -->
<form name="formReload" action="${pageContext.request.contextPath}/organizationDefManager/queryAllOrgDef.do" method="post">
</form>
</body>
</html>

<script type="text/javascript">

function onload(){
	//adtecUtil.formactTable("",[1,2],[1],1);
	//adtecUtil.showMsg("shishi");
	var sucMsg = document.getElementById("sucMsg").value;
	//alert(sucMsg);
	if(sucMsg == "" || sucMsg == undefined || sucMsg == null){
		$(window.parent.document).find("#msgBoxDIV").fadeOut("slow");
	}else if(sucMsg == "updateOrgDefSuc"){
		//$("#msg").html("修改成功");
		//showMsg();
		adtecUtil.showMsg("修改机构等级成功");
	}else if(sucMsg == "addOrgDefSuc"){
		//$("#msg").html("添加成功");
		//showMsg();
		adtecUtil.showMsg("添加机构等级成功");
	}else if(sucMsg == "deleteOrgDefSuc"){
		//$("#msg").html("删除成功");
		//showMsg();
		adtecUtil.showMsg("删除机构等级成功");
		
	}else if(sucMsg == "addOrgDefError"){
		adtecUtil.showMsg("添加机构等级失败");
	}else if(sucMsg == "deleteOrgDefError"){
		adtecUtil.showMsg("删除机构等级失败");
	}else if(sucMsg == "updateOrgDefError"){
		adtecUtil.showMsg("修改机构等级失败");
	};
}

/**
 *  span作 alert使用的显示方法
 */
 

function reload(){
	//alert(123);
	window.location.reload()
}

function formReload(){
	with(document.forms[0]){
		action="${pageContext.request.contextPath}/organizationDefManager/queryAllOrgDef.do";
		submit();
	} 
}

function returnMsg(str){
	//$("#msg").html("还未删除最小级别");
	//showMsg();
	adtecUtil.showMsg(str);
}

/* function returnConfirm(str){//您确定要删除该等级信息吗?
	window.parent.adtec_confirm.confirm(str,function (a){
		 if(a){
			 return true;
		 }
	});
} */
/**
 * 添加时候 的选择层级，静态的添加和删除
 */
/* function addOption(id,txt,val){
	var obj = document.getElementById(id);
	txt ="第"+txt+"层级";
    var opt = new Option(txt,val);
    obj.add(opt);
}

function removeAllOption(id){
	//alert(id);
    var obj=document.getElementById(id);
	obj.options.length=0;

} */


/**
 * 机构等级删除。
 * 修改 改为 直接由<a>请求到control,删除成功 以后queryAllOrgDef.do带一个参数sucMsg = deleteOrgDefSuc。在onload方法判断用户做了什么操作
 */
function deleteOrgDef(obj,orgDefLevel_id){
	//alert(orgDefLevel_id);
	//alert(obj+orgDefLevel_id);
	//var a = window.confirm('确定删除');//maojd update date 2014-1-7 10:38:更改confirm的使用。放到。把if(a)发送ajax放到回调函数里面
	window.parent.adtec_confirm.confirm("确认删除",function (a){
		
		if (a == 1) {//a==1表示点击了window.confirm的确定
			$.ajax({
			   async:false,
			   type: "POST",
			   url: "${pageContext.request.contextPath}/organizationDefManager/deleteOrgDef.do",
			   data: "level_id="+orgDefLevel_id,
			   success: function(msg){
				   //$("#msg").html("删除成功");
				   //showMsg();
				   if(msg == "deleteOrgDefSuc"){
					   //alert(msg);
					   adtecUtil.showMsg("删除机构等级成功");
					   $(obj).closest("tr").remove();	//静态删除
					   //removeAllOption('level_id');			    //清除添加的等级选择
					  // addOption('level_id',orgDefLevel_id,orgDefLevel_id);
					   //setTimeout("reload()",1000);//这种方式reload清除不了 onload方法时候需要用的msg
					   setTimeout("formReload()",1000);
					   
					   //不刷新，静态删除
				   }else if(msg == "deleteOrgDefError"){
					   adtecUtil.showMsg("删除机构等级失败");
				   }
				   
			   }
			});
		}else {
			//alert(a+"点击了取消");
			//刚才点击了 window.confirm的取消
		};
		
		
	});
	
	
}

function nameMsg(){
	
	var span = document.getElementById("span1");
	var name = document.getElementById("name").value;
		span.innerHTML = "";
		var nameSpace = name.replace(/^\s+|\s+$/g, '');
		//alert("start"+nameSpace+"end");
		if(nameSpace == ''){
			/* var msg = '名称不能为空';
			span.innerHTML = msg;
			span.style.color='#ff0000';
		    span.style.fontSize='12px'; */
			adtecUtil.showMsg("名称不能为空");
		    //$("#name").focus();
		    return false;
		}else if(!isEnChNum(nameSpace)){
			adtecUtil.showMsg("机构名称不能包含特殊字符");
			//$("#name").focus();
			return false;
		}
		if(nameSpace.length > 15){
			/* var msg = '长度限制为15';
			span.innerHTML = msg;
			span.style.color='#ff0000';
		    span.style.fontSize='12px'; */
			adtecUtil.showMsg("长度限制为15");
		    //$("#name").focus();
		    return false;
		};
		return true;
		
} 

function userCheck() {
	$("#name").focus();
	$("#name").blur();
	var textuser = document.getElementById("name").value;
	var optval = document.getElementById("level_id").value;	//获取select选中的值
	textuser = textuser.replace(/^\s+|\s+$/g, '');
	/* if(textuser == ""){
		//alert("名称不能为空");
		return false;
	}else if(textuser.length > 15){
		//alert("长度限制为15");
	    return false;
	} */
	if(!nameMsg()){
		//验证输入是否  只有中文、字母、数字、下划线。或者 是空格
		return false;
	}else if(optval > 7){
		//alert("最多可添加7层，已经添加到最大层级");
		//$("#msg").html("最多可添加7层，已经添加到最大层级");
		//showMsg();
		adtecUtil.showMsg("最多可添加7层，已经添加到最大层级");
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
};

function orgRelationList(){
	//alert(123);
	//$(window.parent.document).find("#folder_17").click();
	$(window.parent.document).find("#OutFolder a").each(function (index, domEle) {
		var str = domEle.attributes.onclick.value;//利用js获取onclick属性值，搜索url
		var start = str.indexOf ('ta_org_relation/orgRelationList.do');
		if(start>0){
			$(domEle).click();
		}
	});
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

</c:if>