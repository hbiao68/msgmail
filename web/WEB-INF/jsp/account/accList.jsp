<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.adtec.com.cn" prefix="adtec" %>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>

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
    <div style="display:block;width:100%;min-width:1122px;height:300px;">
        <form action="<%=request.getContextPath()%>/account/accInsert.do" name="form1"
        method="post">
            <div style="_width: 99%" class="txt_title">
                帐号管理&nbsp;
                <span class="f_size normal black" id="_ut">
                </span>
            </div>
            <!-- 查询条件 -->
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <tr>
                        <td align="center">
                            帐号：
                        </td>
                        <td>
                            <input type="text" name="username" value="${account.username }" class="txt noime"
                            maxlength="32" size="20">
                        </td>
                        <td align="center">
                            名称：
                        </td>
                        <td>
                            <input type="text" name="name" value="${account.name }" class="txt noime"
                            maxlength="32" size="20">
                        </td>
                    </tr>
                    <tr>
                        <td align="center">
          电子邮件：
                        </td>
                        <td>
                            <input type="text" name="email" value="${account.email }" class="txt noime"
                            maxlength="32" size="20">
                        </td>
                    </tr>
                </tbody>
            </table>
            <table cellspacing="0" cellpadding="0" class="O2" style="table-layout: fixed; width: 100%; *width: auto;"
            id="datalist">
                <tbody>
                    <tr style="height: 20px;" class="egg">
                        <td width="5%" class="o_title2">
                            序号
                        </td>
                        <td width="25%" class="o_title2">
                            帐号：
                        </td>
                        <td width="25%" class="o_title2">
                            名称：
                        </td>
                        <td width="25%" class="o_title2">
           电子邮箱：
                        </td>
                        <td width="20%" class="o_title2">
                            操作
                        </td>
                    </tr>
                </tbody>
            </table>
            <!-- 数据显示 -->
            <div>
                <table id="contactTable" cellspacing="0" cellpadding="0" style="table-layout: fixed; width: 100%; *width: auto; background-color: white;">
                    <tbody>
                        <c:if test="${empty account.list }">
                            <!-- <tr style="height: 40px;">
                            <td colspan="100" align="center"><h4>没有数据</h4></td>
                            </tr> -->
                            <div id="tips_bar">
                                <div class="nomail">
                                    <b>
                                        当前没有数据
                                    </b>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${not empty account.list }">
                            <c:forEach items="${account.list }" var="accountList" varStatus="vs">
                                <tr style="height: 20px;">
                                    <td width="5%" title="${vs.index+1 }"  style="padding-left: 10px;">
                                        ${vs.index+1 }
                                    </td>
                                    <td width="25%" title="${accountList.username }"  style="padding-left: 10px;">
                                            <a href="#" onclick="return onFindById('${accountList.username}')" style="text-decoration: underline">
                                                ${accountList.username }
                                            </a>
                                    </td>
                                    <td width="25%" title="${accountList.name }" style="padding-left: 10px;">
                                            ${accountList.name }
                                    </td>
                                    <td width="25%" title="${accountList.email }" style="padding-left: 10px;">
                                            ${accountList.email }
                                    </td>
                                    <td width="20%" style="padding-left: 10px;">
                                        <c:if test="${delPrivlg || wholePrivlg}">
	                                        <a onclick="return onDelete('${accountList.username }')" title="删除" href="#" style="text-decoration: underline">
	                                            删除
	                                        </a>
	                                        &nbsp;
										</c:if>
										
										
										<c:if test="${updatePrivlg || wholePrivlg}">
	                                        <a href="#" title="修改" onclick="return onUpdate('${accountList.username }')" style="text-decoration: underline">
	                                            修改
	                                        </a>
										</c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </div>
           <adtec:page url="cateList.do" totalLines="${account.pageModel.count }" curpage="${account.pageModel.pageNow }" pagesize="${account.pageModel.pageSize }"></adtec:page>
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <div style="" class="list_btline">
                        <div class="f_size selbar_bt barspace2" style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px"
                        align="center">
                            <!-- <span class="addrtitle">选择：</span> 
                            <a href="javascript:SetChecked(1);">全部</a> - <a href="javascript:SetChecked(2);">无</a>
                            -->
                            <c:if test="${addPrivlg || wholePrivlg}">
	                            <input type="submit" title="添加账号" name="next" value="添加账号" class="button">
							</c:if>
                            
                            <input type="button" title="查询" name="next" value="查询" onclick="return onSelect()"
                            class="button">
                        </div>
                    </div>
                </tbody>
            </table>
            <!-- 隐藏域 -->
            <input type="hidden" value="${account.pageModel.pageNow}" class="pageNowHidden">
            <input type="hidden" value="${account.pageModel.pageCount}" class="pageCountHidden">
            <input type="hidden" value="${PromptMsg}" class="PromptMsg">
        </form>
    </div>
    <!-- 提示信息 -->
    <span style="display: none;" class="msg" id="msg">
        删除成功&nbsp;
    </span>
    
</body>
<script type="text/javascript">

     //跳转到单一实例查询页面
	function onFindById(obj)
	{
	   with(document.forms[0])
	   {
	      action="<%=request.getContextPath()%>/account/accFindById.do?username="+obj;
	      submit();
	   }
	}

	//条件查询以及上一页下一页的跳转
    function onSelect(obj)
    {
    	$("#pageJump").val("");
       with(document.forms[0])
       {
          action="<%=request.getContextPath()%>/account/accList.do?pageNow="+obj;
          submit();
       }
       return true;
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
				          action="<%=request.getContextPath()%>/account/accList.do?pageNow="+obj;
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
    
    
     

    //跳转到修改页面
    function onUpdate(obj)
    {
       with(document.forms[0])
       {
          action="<%=request.getContextPath()%>/account/accUpdate.do?username="+ obj;
			submit();
		}
	}


	$(function() {
		//维持终端描述下拉列表的状态
		$('.cateDesc').val($('.cateDescBack').val());
		//调整查询出来数据显示的长度
		formactTable("contactTable",[0,1,2,3],[10,10,10,10],10);  
	});

	//初始化数据
	function init() {
		//maojd update date:11:29 2013/12/30	页面一旦刷新，外部adtecUtil.js中定时 的隐藏 操作成功提示信息的方法失效。所以onload方法中，执行隐藏方法
		$(window.parent.document).find("#msgBoxDIV").fadeOut("slow");
		adtecUtil.pageTagFun();
		adtecUtil.padTable("contactTable",10);//table的数据不够10的话，添加空白
		//添加或修改的提示信息
		var PromptMsg = $('.PromptMsg').val();
		if(PromptMsg == "InsertMsg"){
			//$("#msg").html("数据添加成功！");
			//showMsg();
			adtecUtil.showMsg("数据添加成功");
		}
		else if(PromptMsg == "failMsg"){
			adtecUtil.showMsg("网络故障！");
		}
		else if(PromptMsg == "UpdateMsg"){
			//$("#msg").html("数据修改成功！");
			//showMsg();
			adtecUtil.showMsg("数据修改成功");
		}else if(PromptMsg == "jumpMsg"){
			adtecUtil.showMsg("请输入正确的分页跳转参数");
		}
		else{
			
		}
	}

	//删除方法
	function onDelete(username) {
		//var a = window.confirm('确定删除');//maojd update date 2014-1-7 10:38:更改confirm的使用。放到。把if(a)发送ajax放到回调函数里面
		window.parent.adtec_confirm.confirm("确认删除",function (a){
			if (a == 1) {
				$.ajax({
							type : "POST",
							async : false,
							url : "${pageContext.request.contextPath}/account/delaccAction.do",
							dataType : "json",
							data : "username=" + username,
							success : function(msg) {
								//alert(msg);
								if (msg == "true") {

									adtecUtil.showMsg("删除成功");
									setTimeout("onSelect()",1000);
								} else {
									adtecUtil.showMsg("该终端类型下面有数据，无法删除");
								}
							}
						});
			} else {
			}
		});
	}
</script>
</html>

</c:if>
