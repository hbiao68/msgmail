<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.adtec.com.cn" prefix="adtec"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="viewKey"  value="${resid}1001"></c:set>
<c:set var="viewPrivlg" value="${oneUserAllPrivlgMap[viewKey].actionValue}"></c:set>
<c:set var="addKey"  value="${resid}1002"></c:set>
<c:set var="addPrivlg" value="${oneUserAllPrivlgMap[addKey].actionValue}"></c:set>
<c:set var="updateKey"  value="${resid}1003"></c:set>
<c:set var="updatePrivlg" value="${oneUserAllPrivlgMap[updateKey].actionValue}"></c:set>
<c:set var="delKey"  value="${resid}1004"></c:set>
<c:set var="delPrivlg" value="${oneUserAllPrivlgMap[delKey].actionValue}"></c:set>

<c:if test="${viewPrivlg || wholePrivlg}">


<html>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/util/jquery-1.4.4.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/util/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/util/adtec_util.js"></script>

<style type="text/css">
.msg {
	color: #FFFFFF;
	background: #68af02;
	font-size: 12px;
	padding: 3px 24px 3px;
	z-index: 1;
	position: absolute;
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
width:80px; 
height:22px; 
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 显示当前页面内容 -->
<adtec:localFileName isShow="true"></adtec:localFileName>
<title>List title here</title>
<link href="${pageContext.request.contextPath}/ui/css/common.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/skin.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/add.css"
	rel="stylesheet" type="text/css">
</head>

<body onload="init()">
	<form action="<%=request.getContextPath()%>/user/insert.do"
		name="form1" method="post">
		<div style="_width: 99%" class="txt_title">
			用户管理&nbsp; <span class="f_size normal black" id="_ut"></span>
		</div>
		<!-- 查询条件 -->
		<table width="100%" cellspacing="0" cellpadding="2" border="0"
			class="toolbg">
			<tbody>
				<tr>
					<td align="center">用户名：</td>
					<td><input type="text" name="userName" value="${userName}" class="txt noime"
						maxlength="32" size="20"></td> 
				</tr>
			</tbody>
		</table>
		<table cellspacing="0" cellpadding="0" class="O2"
			style="table-layout: fixed; width: 100%; *width: auto;" id="datalist">
			<tbody>
				<tr style="height: 20px;" class="egg">
					<td width="10%" class="o_title2">序号</td>
					<td width="70%" class="o_title2">用户名</td>
					<td width="20%" class="o_title2">操作</td>
				</tr>
			</tbody>
		</table>

	 	<!-- 数据显示 -->
		<div>
			<table id="contactTable" cellspacing="0" cellpadding="0"
				style="table-layout: fixed; width: 100%; *width: auto; background-color: white;">
				<tbody>

					<c:if test="${empty user }">
						<!-- <tr style="height: 40px;">
							<td colspan="100" align="center"><h4>没有数据</h4></td>
						</tr> -->
						<div id="tips_bar">
							<div class="nomail"><b>当前没有用户</b></div>
						</div>
					</c:if>
					<c:if test="${not empty user }">

						<c:forEach items="${user }" var="us" varStatus="vs">
							<tr style="height: 20px;">
								<td width="10%" title="${vs.index+1 }">${vs.index+1 }</td>
								
								<td width="70%" title="${us.username }">
										<a href="#" onclick="return onFindById('${us.userid }')" style ="text-decoration: underline">
											${us.username } </a>
									</td>
								
								<td width="20%">
								<c:if test="${delPrivlg || wholePrivlg}">
									<a onclick="return onDelete('${us.userid }')" href="#"  style ="text-decoration: underline">删除</a> 
								</c:if>
								<c:if test="${updatePrivlg || wholePrivlg}">
									&nbsp; <a href="#" onclick="return onUpdate('${us.userid }')"  style ="text-decoration: underline">修改</a>
								</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
 
     		<adtec:page url="list.do" totalLines="${pageModel.count }" curpage="${pageModel.pageNow }" pagesize="${pageModel.pageSize }"></adtec:page>
            
		<table width="100%" cellspacing="0" cellpadding="2" border="0"
			class="toolbg">
			<tbody>
				<div style="" class="list_btline">
					<div class="f_size selbar_bt barspace2"
						style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px"
						align="center">
						<!-- <span class="addrtitle">选择：</span> 
                    <a href="javascript:SetChecked(1);">全部</a> - <a href="javascript:SetChecked(2);">无</a>
                    -->
                    <c:if test="${addPrivlg || wholePrivlg}">
						<input type="submit" name="next" title="添加新用户" value="添加新用户" class="button"> 
					</c:if>
						<input type="button" name="next" title="查询" value="查询" onclick="return onSelect()" class="button">
					</div>
				</div>

			</tbody>
		</table>
		<!-- 隐藏域 -->
		<input type="hidden" value="${pageModel.pageNow}" class="pageNowHidden">
		<input type="hidden" value="${pageModel.pageCount}" class="pageCountHidden">
		<input type="hidden" value="${PromptMsg}" class="PromptMsg">
	</form>
	<!-- 提示信息 -->
	<span style="display: none;" class="msg" id="msg">删除成功&nbsp;</span>
</body>


<script type="text/javascript">

     //跳转到单一实例查询页面
	function onFindById(obj)
	{
	   with(document.forms[0])
	   {
	      action="<%=request.getContextPath()%>/user/findById.do?uid="+obj;
	      submit();
	   }
	}

	//条件查询以及上一页下一页的跳转
    function onSelect(obj)
    {
    	$("#pageJump").val("");
       with(document.forms[0])
       {
          action="<%=request.getContextPath()%>/user/list.do?pageNow="+obj;
          submit();
       }
       return true;
    }

    //跳转到修改页面
    function onUpdate(obj)
    {
       with(document.forms[0])
       {
          action="<%=request.getContextPath()%>/user/update.do?uid="+ obj;
			submit();
		}
	}


	//初始化数据
	function init() {
		//maojd update date:11:29 2013/12/30	页面一旦刷新，外部adtecUtil.js中定时 的隐藏 操作成功提示信息的方法失效。所以onload方法中，执行隐藏方法
		$(window.parent.document).find("#msgBoxDIV").fadeOut("slow");
		adtecUtil.pageTagFun();
		//添加或修改的提示信息
		var PromptMsg = $('.PromptMsg').val();
		if(PromptMsg == "InsertMsg"){
			//$("#msg").html("数据添加成功！");
			//showMsg();
			adtecUtil.showMsg("数据添加成功");
		}
		else if(PromptMsg == "UpdateMsg"){
			//$("#msg").html("数据修改成功！");
			//showMsg();
			adtecUtil.showMsg("数据修改成功");
		}
		else{
			
		}
	}

	//删除方法
	function onDelete(uid) {
		//var a = window.confirm('确定删除');
		window.parent.adtec_confirm.confirm("确认删除",function (a){
			if (a == 1) {
			
				$.ajax({
							type : "POST",
							url : "${pageContext.request.contextPath}/user/deluserAction.do",
							dataType : "json",
							data : "uid=" + uid,
							success : function(msg) {
								if (msg == "数据删除成功！") {
									
									//$("#msg").html("删除成功！");
									//showMsg();
									adtecUtil.showMsg("删除成功");
									setTimeout("onSelect()",1000);
								} else {
									adtecUtil.showMsg("该用户不允许删除！");
								}
							}
						});
			}
				else {

			}
			
		});
	}
	
	//分页查询跳转到某一页面
	function onJumpSelect(obj)
	{
		   var pageJump=$("#pageJump").val();
			//var pageVal=pageJump.trim();
			var pageVal=pageJump.replace(/^\s+|\s+$/g, '');
			var pageCount=$('.pageCountHidden').val();
			if(checkIsInteger(pageVal)){
				pageVal=pageVal*1;
				pageCount=pageCount*1;
				if(pageVal > pageCount){
					//alert("输入值是"+pageVal+"超过最大页");
					
					//$("#msg").html("您输入的值超过最大页");
					//showMsg();
					adtecUtil.showMsg("您输入的值超过最大页");

				}else if(pageVal == 0){
					
					//$("#msg").html("请输入正整数");
					//showMsg();
					adtecUtil.showMsg("请输入正整数");

				}else{
				       with(document.forms[0])
				       {
				          action="<%=request.getContextPath()%>/category/cateList.do?pageNow="+obj;
				          submit();
				       }
				}
			}
			else{
				
				//$("#msg").html("只能输入数字，且为正整数");
				//showMsg();
				adtecUtil.showMsg("只能输入数字，且为正整数");
			}
	}
	
    /*function checkPage_jump
     *验证跳转的文本框输入值 格式
     */
 	// 检查是否为数字
 	function checkIsInteger(str){
 			if (str == ""){
 			 return false;
 			}
 			if (str.search(/^[0-9]+$/) < 0){
 			 return false;
 			}
 			else{
 			 return true;
 			}
 	}
	
</script>

</html>

</c:if>