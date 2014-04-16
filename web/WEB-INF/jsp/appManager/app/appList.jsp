<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.adtec.com.cn" prefix="adtec"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 显示当前页面内容 -->
<adtec:localFileName isShow="true"></adtec:localFileName>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/util/jquery-1.4.4.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/util/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>
<title>List title here</title>
<link href="${pageContext.request.contextPath}/ui/css/common.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/skin.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/add.css"
	rel="stylesheet" type="text/css">

</head>

<style type="text/css">
.msg {
	color:#FFFFFF;
	background:#68af02;
	font-size:12px;
	padding:3px 24px 3px;
	z-index:1;
	position:absolute;
}
.button {
	font-family:"tahoma","宋体";
	font-size:9pt;
	color:#003399;
	border:1px #003399 solid;
	color:#006699;
	border-bottom:#93bee2 1px solid;
	border-left:#93bee2 1px solid;
	border-right:#93bee2 1px solid;
	border-top:#93bee2 1px solid;
	background-color:#e8f4ff;
	cursor:hand;
	font-style:normal;
	width:60px;
	height:22px;
}
</style>



<body onload="init()">

<div style="display:block;width:100%;min-width:1122px;height:300px;">

    <div style="_width: 99%" class="txt_title">
        业务管理&nbsp;
        <span class="f_size normal black" id="_ut">
        </span>
    </div>
    <form action="<%=request.getContextPath()%>/appManager/appInsert.do" name="form1"
    method="post">
        <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
            <tbody>
                <tr>
                    <td align="center">
                        业务名称：
                    </td>
                    <td>
                        <input type="text" name="appName" value="${appName }" size="21" maxlength="30">
                    </td>
                    <td align="center">
                        业务账号类型：
                    </td>
                    <td>
                        <select name="ta_id" class="ta_id" style="width: 150px">
                            <option value="">
                                &nbsp;
                            </option>
                            <c:forEach items="${rows}" var="rows">
                                <option title="${rows.cateDesc }" value="${rows.ta_id}">
                                    <c:if test="${fn:length(rows.cateDesc)>10}">
                                        ${fn:substring(rows.cateDesc,0,10)}...
                                    </c:if>
                                    <c:if test="${fn:length(rows.cateDesc)<=10 }">
                                        ${rows.cateDesc }
                                    </c:if>
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        业务开通的状态：
                    </td>
                    <td>
                        <select name="appState" class="appState" style="width: 150px">
                            <option value="">
                            </option>
                            <option value="1">
                                已有帐号开通该业务
                            </option>
                            <option value="2">
                                尚无帐号开通该业务
                            </option>
                        </select>
                </tr>
            </tbody>
        </table>
        <table cellspacing="0" cellpadding="0" class="O2" style="table-layout: fixed; width: 100%; *width: auto;"
        id="datalist">
            <tbody>
                <tr style="height: 20px;" class="egg">
                    <td width="5%" class="o_title2">
                        <input type="checkbox" id="chkfirstbox" onclick="javascript:checkAss()"
                        />
                    </td>
                    <td width="5%" class="o_title2">
                        序号
                    </td>
                    <td width="25%" class="o_title2">
                        业务名称
                    </td>
                    <td width="25%" class="o_title2">
                        业务服务器域名
                    </td>
                    <td width="20%" class="o_title2">
                        业务帐号类型
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
                    <c:if test="${empty appManager }">
                        <!-- <tr>
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
                    <c:if test="${not empty appManager }">
                        <c:forEach items="${appManager }" var="app" varStatus="vs">
                            <tr style="height: 20px;">
                                <td width="5%" class="o_title2">
                                    <input type='checkbox' name='chkbox' value="${app.appid }" title=""/>
                                </td>
                                <td width="5%" title="${vs.index+1 }" style="padding-left: 10px;">
                                    ${vs.index+1 }
                                </td>
                                <td width="25%" title="${app.appName }" style="padding-left: 10px;">
                                        <a href="#" onclick="return onFind('${app.appid}',this)" style="text-decoration: underline">
                                            ${app.appName }
                                        </a>
                                </td>
                                <td width="25%" title="${app.appDomain }" style="padding-left: 10px;">
                                        ${app.appDomain }
                                </td>
                                <td width="20%" title="${app.cateDesc}" style="padding-left: 10px;">
                                        ${app.cateDesc }
                                </td>
                                <td width="20%" style="padding-left: 10px;">
                                    <c:if test="${delPrivlg || wholePrivlg}">
	                                    <a href="#" onclick="return onDelete('${app.appid }')" title="删除" style="text-decoration: underline">
	                                        删除
	                                    </a>
									</c:if>
									
									<c:if test="${updatePrivlg || wholePrivlg}">
	                                    &nbsp;
	                                    <a href="#" onclick="return onUpdate('${app.appid }')" title="修改" style="text-decoration: underline">
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
        
 		<adtec:page url="appList.do" totalLines="${pageModel.count }" curpage="${pageModel.pageNow }" pagesize="${pageModel.pageSize }"></adtec:page>
                           
        <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
            <tbody>
                <div style="" class="list_btline">
                    <div class="f_size selbar_bt barspace2" style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px"
                    align="center">
                    <c:if test="${addPrivlg || wholePrivlg}">
                        <input type="submit" title="添加" name="next" value="添加" class="button">
					</c:if>
                        <input type="button" title="查询" name="next" value="查询" onclick="return onSelect()"
                        class="button">
                    <c:if test="${delPrivlg || wholePrivlg}">
                        <input id="deleteButton" title="删除" type="button" name="next" value="删除" class="button"
                        onclick="deleteSelected()">
					</c:if>
                    </div>
                </div>
            </tbody>
        </table>
        <input type="hidden" value="${appState}" class="appState_1">
        <input type="hidden" value="${ta_id}" class="ta_id_1">
        <input type="hidden" value="${pageModel.pageNow}" class="pageNowHidden">
        <input type="hidden" value="${pageModel.pageCount}" class="pageCountHidden">
        <input type="hidden" value="${PromptMsg}" class="PromptMsg">
        <input type="hidden" value="${dataMsg}" class="dataMsg">
        
       
    </form>
    <!-- 提示信息 -->
    <span style="display:none;" class="msg" id="msg">
        已将邮件成功删除&nbsp;
    </span>
    
</div>
</body>


</html>

<script type="text/javascript">


    //跳转到单一实例查询页面
	function onFind(appid)
	{
    	//alert(44444);
    	//alert(obj);
	   with(document.forms[0])
	   {
	      action="${pageContext.request.contextPath}/appManager/appFindById.do?appid="+appid;
	      submit();

	   }
	}
	
	//条件查询，分页查询跳转页面
	function onSelect(obj)
	{
		$("#pageJump").val("");
		   with(document.forms[0])
		   {
		      action="<%=request.getContextPath()%>/appManager/appList.do?pageNow="+obj;
		      submit();
		   }
	}



    //跳转到修改页面
    function onUpdate(obj)
    {
       with(document.forms[0])
       {
          action="<%=request.getContextPath()%>/appManager/appUpdate.do?appid="+obj;
          submit();
       }
    }
    
    
    
	/*
	 *批量删除
	 */

	function selectAll() {
		//选中所有
		var box = document.getElementsByName("chkbox");
		var box_length = box.length;
		for (var i = 0; i < box_length; i++) {
			box[i].checked = true;
		}

	}

	function unselectAll() {
		//取消选中所有
		var box = document.getElementsByName("chkbox");
		var box_length = box.length;
		for (var i = 0; i < box_length; i++) {
			box[i].checked = false;
		}
	}


	//批量删除
	function deleteSelected(){
		//删除选中
		var box = document.getElementsByName("chkbox");
		var box_length = box.length;
		//alert(box.length);
		var chkstr = "";
		for (var i = 0; i < box_length; i++) {

			if (box[i].checked == true) {
				chkstr += box[i].value + ",";
			}
		}
	//	alert(chkstr);
		chkstr = chkstr.substring(0, chkstr.length - 1);

		if(chkstr.length>0){
			//发送ajax请求，到control，把chkstr发过去
			//var a= window.confirm('确定删除'); //maojd update date 2014-1-7 10:38:更改confirm的使用。放到。把if(a)发送ajax放到回调函数里面
			window.parent.adtec_confirm.confirm("确认删除",function (a){
				if(a==1){
				 	 $.ajax({
						   type: "POST",			
						   async : false,
						   url: "${pageContext.request.contextPath}/appManager/batchdelete.do",
						   dataType:"json",
						   data: {"ids":chkstr,"appState":$(".appState").val()},
						   success: function(msg){
								adtecUtil.showMsg(msg);
								setTimeout("onSelect()",1000);
						   }
					}); 			
				}else{
					//点击了取消	
				}
			});


		}else{
			//$("#msg").html("请选中业务");
			//showMsg();
			adtecUtil.showMsg("请选中业务");
		}

		
	} 

	//校验checkbox是否全选
    function checkAss(){
    	//第一个 checkbox执行的方法。
		var box = document.getElementById("chkfirstbox");
		if (box.checked == true) {
			selectAll();
		}
		if (box.checked == false) {
			unselectAll();
		}
	}
    

    //删除方法
    function onDelete(appid){

    	//var a = window.confirm('确定删除'); //maojd update date 2014-1-7 10:38:更改confirm的使用。放到。把if(a)发送ajax放到回调函数里面
  		window.parent.adtec_confirm.confirm("确认删除",function (a){
  			if (a == 1) {//a==1表示点击了window.confirm的确定
  				$.ajax({
  					   type: "POST",	
  					   async : false,
  					   url: "${pageContext.request.contextPath}/appManager/delAction.do",
  					   dataType:"json",
  					   data: {"appid":appid,"appState":$(".appState").val()},
  					   success: function(msg){
  						   if(msg == "数据删除成功！"){
 								adtecUtil.showMsg(msg);
  								setTimeout("onSelect()",1000); 							   
  						   }else{
  							 adtecUtil.showMsg(msg);
  						   }

  						   	
  					   }
  				}); 
  	    	}else{
  	    		
  	    	};
		});
    	
    }

	    function init(){
	    	//maojd update date:11:29 2013/12/30	页面一旦刷新，外部adtecUtil.js中定时 的隐藏 操作成功提示信息的方法失效。所以onload方法中，执行隐藏方法
			$(window.parent.document).find("#msgBoxDIV").fadeOut("slow");
			adtecUtil.pageTagFun();
			adtecUtil.padTable("contactTable",10);//table的数据不够10的话，添加空白
	    	var dateObj=$(".dataMsg").val();
	    	 
	    	if(dateObj=="没有数据，请先添加终端信息！"){
	    		//alert(dateObj);
	    		//$("#msg").html("没有数据，请先添加终端信息");
				//showMsg();
				adtecUtil.showMsg("没有数据，请先添加终端信息");
				setTimeout("onCateNamePage()",1000);
	    	}else{ 
/* 				pageFunction();//加载page的所需数据 
				pageNow = 0;	//没有子结构的话，直接展示第一页。pageNow =0;执行pageNext
				pageNext(); */
			
				
				//添加或修改时的提示信息
				var PromptMsg = $('.PromptMsg').val();
				if(PromptMsg == "InsertMsg"){
					//$("#msg").html("数据添加成功！");
					//showMsg();
					adtecUtil.showMsg("数据添加成功");
				}else if(PromptMsg == "failMsg"){
					adtecUtil.showMsg("网络故障！");
				}else if(PromptMsg == "UpdateMsg"){
					//$("#msg").html("数据修改成功！");
					//showMsg();
					adtecUtil.showMsg("数据修改成功");
				}else{
					
				};
				
	    	}
	    	

	    	//maojd update date:2013-12-24 没有数据的时候，把删除按钮隐藏掉
	    	var trNum = $("#contactTable").find("tr").length;
			 //alert(trNum);//   0说明没有数据
			 if(trNum == 0){
				 $("#deleteButton").css("display","none");
			 };
	    	
	    }
	    
	    //维持条件查询中appState的下拉列表的状态
	    $(function(){
	        //	alert($('.cate1_').val());
	        	$('.appState').val($('.appState_1').val());
	        	//调整查询出来数据显示的长度
	    		formactTable("contactTable",[1,2,3],[10,10,10],10);  
	        	
	        });
	    
	  //维持条件查询中ta_id下拉列表的状态
	    $(function(){
	    	//alert(1111);
	    	$('.ta_id').val($('.ta_id_1').val());
	    	//alert($("option[value="+v+"]']").attr());  
	    });

		//如果没有数据，跳转到终端分类管理页面添加数据
		function onCateNamePage(){
			/* with(document.forms[0]){
			        	  action="${pageContext.request.contextPath}/category/list.do";
				submit();
			}/*
			/* maojd update  直接点击父窗体的终端分类管理*/
			//$(window.parent.document).find("#folder_12").click();
			$(window.parent.document).find("#OutFolder a").each(function (index, domEle) {
	    		var str = domEle.attributes.onclick.value;//利用js获取onclick属性值，搜索url
				var start = str.indexOf ('category/cateList.do');
				if(start>0){
					$(domEle).click();
				}
	    	});
		};
		
		
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
					          action="<%=request.getContextPath()%>/appManager/appList.do?pageNow="+obj;
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

</c:if>