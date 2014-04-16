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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>



<link href="${pageContext.request.contextPath}/ui/css/skin.css"
	rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath}/ui/css/common.css"
	type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/ui/css/getcss.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/add.css"
	type="text/css" rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/ui/css/mail_group_add.css"
	type="text/css" rel="stylesheet">


<!-- 下面是树状结构所需 -->

<link rel="STYLESHEET" type="text/css"
	href="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree.css">
<script
	src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxcommon.js"></script>
<script
	src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree.js"></script>
<script
	src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree_start.js"></script>
<script
	src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree_json.js"></script>


<style type="text/css">
.list_body {
	min-width: 780px;
}

.myleftbar {
	min-width: 560px;
	margin-right: 186px;
	margin-top: 10px;
	_margin-top: 0px;
	position: relative;
	_float: left;
	_margin-right: 0px;
	zoom: 1;
}

.mysidebar {
	position: relative;
	right: 0px;
	float: right;
	top: 0px;
	_top: 0px;
	padding: 10px 8px 20px 8px;
	/*width: 164px;*/
	height: auto;
}

.M_l {
	overflow: hidden;
}

.O,.M {
	clear: left;
}

.html,.body {
	_position: relative;
	_overflow-x: hidden;
}

#contactTable tr {
	border-bottom: 1px solid #e3e6eb;
}

#contactTable a:hover,a:link,a:visited {
	color: black;
	text-decoration: none;
}

#submitbtn {
	display: inline;
}

#treeButton a {
	padding: 2px 10px 2px 10px;
	height: 22px;
	border: 1px solid #bebebe;
	line-height: 22px;
	background: #DDD
		url(${pageContext.request.contextPath}/ui/images/buttonbg.png)
		repeat-x 0 -96px;
	text-align: center;
	text-decoration: none;
	color: #494949 !important;
}

* {
	margin: 0;
	padding: 0;
}

body {
	font-size: 12px;
}

ul,li {
	list-style-type: none;
}

#tab {
	width: 1150px;
	height: 28px;
	overflow: hidden;
}

#tab .tab_title {
	width: 1150px;
	height: 32px;
	overflow: hidden;
	left: 0;
	top: 0;
	clear: both;
	overflow: hidden;
	position: relative;
}

#tab .tab_title .u {
	width: 1085px;
	overflow: hidden;
	position: relative;
}

#tab .tab_title ul {
	margin: 0 5px;
	position: absolute;
	float: left;
	width: 4392px;
}

#tab .tab_title div {
	float: left;
	width: 20px;
	height: 30px;
	line-height: 30px;
	cursor: pointer;
}

#tab .tab_title span.vright {
	top: 0;
	right: 5px;
	margin-left: 2px;
}

#tab .tab_title span.vleft {
	top: 0;
	left: 0px;
	padding-left: 5px;
}

#tab .tab_title li {
	float: left;
	width: 133px;
	height: 30px;
	line-height: 30px;
	text-align: center;
	background-color: #c1d9f3;
	margin-right: 0px;
	border: 1px #999999 solid;
	cursor: pointer;
}

#tab .tab_title li:hover {
	background-color: #f2f4f6;
}

#tab .tab_title li.selected {
	background-color: #f2f4f6;
}

#div1 {
	width: 120px;
	height: 15px;
	border: 1px #CCCCCC solid;
	position: absolute;
	display: none;
	background: #CCCCCC;
	font-size: 5px;
	padding: 2px;
	color: #999999;
}

#div2 {
	width: 120px;
	height: 15px;
	border: 1px #CCCCCC solid;
	position: absolute;
	display: none;
	background: #CCCCCC;
	font-size: 5px;
	padding: 2px;
	color: #999999;
}

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
width:60px; 
height:22px; 
}

</style>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 显示当前页面内容 -->
<adtec:localFileName isShow="true"></adtec:localFileName>
<title>List title here</title>
</head>
<body onload="init()">
    <div style="display:block;width:100%;min-width:1122px;height:300px;">
        <form action="<%=request.getContextPath()%>/accorgrelation/accorgInsert.do" method="post">
            <div style="display: block; width: 100%; height: 300px;">
                <div class="list_body">
                    <div style="_width: 99%" class="txt_title">
                        机构帐号管理&nbsp;
                    </div>
                </div>
                <div style="display: block; width: 100%; height: 100px; float: left">
                    <div style="line-height: 14px; zoom: 1;" class="toolbg toolbgline">
                        <!-- 下面是查询条件-->
                        <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                            <tbody>
                                <tr>
                                    <td>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帐号：
                                        <input type="text" name="username" value="${accountorgrelation.username }" maxlength="30"
                                        size="20" id="ocaccountName" class="txt noime">
                                    </td>
                                    <td align="right">
                                        机构名称：
                                    </td>
                                    <td align="left">
                                        <input id="bcd" type="text" name="orgName" readonly="readonly"
                                        size="20" class="txt noime" maxlength="100" autocomplete="off"
                                        onclick="showMenu();" />
                                        &nbsp;
                                        <!-- <a href="#" onclick="clearCitySel()">clear</a> -->
                                        <a id="submitbtn" title="清除机构" class="button" name="submitbtn1" onclick="clearCitySel()"
                                        href="#">
                                            清除机构
                                        </a>
                                        <div id="hidenDiv" style="display: none; position: absolute; left: 0px; top: 0px; z-index: 1; background-color: #f5f5f5; border: 1px solid Silver;">
                                            <div align="left" id="treeboxbox_tree" style="background-color: #f5f5f5; border: 1px solid Silver; margin-top: 0; width: 180px; height: 300px;">
                                            </div>
                                            <div id="treeButton" style="text-align: center; margin-top: 5px; margin-bottom: 5px">
                                                <a href="#" onclick="putTreeName()">
                                                    确定
                                                </a>
                                                <a href="#" onclick="cacleMenu()">
                                                    取消
                                                </a>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <!-- 下面是显示查询的数据-->
                    <div>
                        <table cellspacing="0" cellpadding="0" class="O2" style="table-layout: fixed; width: 100%; *width: auto;"
                        id="datalist">
                            <tbody>
                                <tr style="height: 20px;" class="egg">
                                    <td width="5%" class="o_title2">
                                        <input type="checkbox" id="chkfirstbox" onclick="javascript:checkAll()"/>
                                    </td>
                                    <td class="o_title2" width="5%">
                                        序号
                                    </td>
                                    <td class="o_title2" width="35%">
                                        帐号
                                    </td>
                                    <td class="o_title2" width="35%">
                                        机构
                                    </td>
                                    <td class="o_title2" width="20%">
                                        操作
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <!-- //数据显示 -->
                    <div>
                        <table id="contactTable" cellspacing="0" cellpadding="0" style="table-layout: fixed; width: 100%; *width: auto; background-color: white;">
                            <tbody>
                                <c:if test="${empty accountorgrelation.accorgList }">
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
                                <c:if test="${not empty accountorgrelation.accorgList }">
                                    <c:forEach items="${accountorgrelation.accorgList }" var="accorgList" varStatus="vs">
                                        <tr style="height: 20px;">
                                            <td class="o_title2" width="5%">
                                                <input type="checkbox" name="chkbox" value="${accorgList.relationid }" title=""/>
                                            </td>
                                            <td width="5%" style="padding-left: 10px;">
                                                ${vs.index+1 }
                                            </td>
                                            <td width="35%"  style="padding-left: 10px;">
                                                <a href="#" onclick="return onFind('${accorgList.relationid }')" style="text-decoration: underline">
                                                    ${accorgList.username }
                                                </a>
                                            </td>
                                            <td width="35%"  style="padding-left: 10px;">
                                                ${accorgList.orgName }
                                            </td>
                                            <td width="20%" style="padding-left: 10px;">
                                            
										<c:if test="${delPrivlg || wholePrivlg}">
                                                <a onclick="return onDelete('${accorgList.relationid }')" title="删除" href="#" style="text-decoration: underline">
                                                    删除
                                                </a>
                                                &nbsp;
										</c:if>
										
										<c:if test="${updatePrivlg || wholePrivlg}">
                                                <a onclick="return onUpdate('${accorgList.relationid }')" title="修改" href="#" style="text-decoration: underline">
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
                    
     		<adtec:page url="relationList.do" totalLines="${accountorgrelation.pageModel.count }" curpage="${accountorgrelation.pageModel.pageNow }" pagesize="${accountorgrelation.pageModel.pageSize }"></adtec:page>
                            
                    
                    <div style="" class="list_btline">
                        <div class="f_size selbar_bt barspace2" align="center" style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px">
                            <!-- <span class="addrtitle">选择：</span>
                            <a href="javascript:SetChecked(1);">全部</a> - <a href="javascript:SetChecked(2);">无</a>
                            -->
                            <c:if test="${addPrivlg || wholePrivlg}">
	                            <input type="submit" class="button" name="next" title="添加账号" value="添加账号">
							</c:if>
							
                            <input type="button" class="button" name="next" title="查询" value="查询"
                            onclick="onSelect()">
                            
                            <c:if test="${delPrivlg || wholePrivlg}">
	                            <input id="deleteButton" type="button" name="next" title="删除" class="button" value="删除"
	                            onclick="deleteSelected()">
							</c:if>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" id="inputIdmao" name="input" class="input">
                <input type="hidden" name="ta_id" value="${ta_id }" id="ta_id">
                <input type="hidden" value="${accountorgrelation.pageModel.pageNow}" class="pageNowHidden">
                <input type="hidden" value="${accountorgrelation.pageModel.pageCount}" class="pageCountHidden">
                <input type="hidden" value="${PromptMsg}" class="PromptMsg">
                <input type="hidden" value="${accountorgrelation.orgName}" class="ocOrgName">
                <input type="hidden" name="orgId" value="${accountorgrelation.orgId }" id="orgId">
        </form>
        <!-- 提示信息 -->
        <span style="display: none;" class="msg" id="msg">
            删除成功&nbsp;
        </span>
    </div>
</body>
</html>
<!-- 下面是树状结构 -->


	<script>
		
	    function init(){
	    	//maojd update date:11:29 2013/12/30	页面一旦刷新，外部adtecUtil.js中定时 的隐藏 操作成功提示信息的方法失效。所以onload方法中，执行隐藏方法
			$(window.parent.document).find("#msgBoxDIV").fadeOut("slow");
			adtecUtil.pageTagFun();
			adtecUtil.padTable("contactTable",10);//table的数据不够10的话，添加空白
			$.ajax({
				type : "POST",
				/* url : "${pageContext.request.contextPath}/organizationManager/queryOrgRoot.do", */
				url : "${pageContext.request.contextPath}/organizationManager/queryOrgRoot.do",
				success : function(msg) {
					var dataObj = eval('(' + msg + ')');
					var orgId = dataObj[0].orgId;
					var orgName = dataObj[0].orgName;
					//alert(orgId+"---"+orgName);
					//alert("dbclick "+id+" Item " + tree.getItemText(id) + " was selected");
					tree.insertNewChild('root',orgId,"全国");  //id全  tree.getItemText(id)	全国 （也就是要显示的标签内容）
					tree.enableTreeLines(true);
					clickFunc(orgId);
				}
			});
	    	
 	    	
 	    	//截取字符串
			 adtecUtil.formactTable("contactTable",[1,2,3],[20,20,20],20); 
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
				else if(PromptMsg == "NoInsertMsg"){
					//$("#msg").html("该银行下暂无帐号！");
					//showMsg();
					adtecUtil.showMsg("该银行下暂无帐号");
				}else if(PromptMsg == "failMsg"){
					adtecUtil.showMsg("网络故障！");
				}
				else{
					
				}
	    	
	    	//maojd update date:2013-12-24 没有数据的时候，把删除按钮隐藏掉
	    	var trNum = $("#contactTable").find("tr").length;
			 //alert(trNum);//   0说明没有div,即：有数据
			 if(trNum == 0){
				 $("#deleteButton").css("display","none");
			 };
	    }
		
		
		
			tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%","root");

			tree.setSkin('dhx_skyblue');
			tree.setImagePath("${pageContext.request.contextPath}/dhtmlxtree/common/images/");
			tree.enableDragAndDrop(0);
			tree.enableTreeLines(false);
			tree.setImageArrays("plus.gif","","","","plus.gif");
			tree.setImageArrays("minus.gif","","","","minus.gif");
			tree.setStdImages("book.gif","books_open.gif","books_close.gif");	
			
			tree.setOnClickHandler(clickFunc);
			function clickFunc(id){
				var cityValue;
				//条件查询中的机构名称点击查询后仍然在文本框中显示
				var ocOrgName = $(".ocOrgName").val();
				if(ocOrgName !="" && tree.getSelectedItemText() == ""){
					cityValue = ocOrgName;
				}else{
					cityValue = tree.getSelectedItemText();
				}
				var citySel = $("#bcd").attr("value", cityValue);
				
				
				$("input[name='input']").val(id);
	
				var state = tree.getOpenState(id);	//state 三种状态，0 表示全新未打开，1表示打开状态。-1，表示关闭状态

				if(state==0){
					//alert("ajax");

					$.ajax({
						   type: "POST",
						   url: "${pageContext.request.contextPath}/organizationManager/queryOrgLevel.do", 
						//   url: "queryOrgLevel.do", 					  
						   data: "id="+id,
						   success: function(msg){
						   //  alert( "Data Saved: " + msg );
						     var dataObj = eval('('+msg+')');
						     
						   //清理table
							//  clearRowsSMS();
						     var table = document.getElementById("contactTable");
						     for(var i=0;i<dataObj.length;i++){
						    	   var obj = dataObj[i];	//获取一个对象
				            	   var orgId=obj.orgId;
				            	   var orgName=obj.orgName; 
				            	 
								     //处罚上面的事件以后，添加 子节点 #重要
								     tree.insertNewChild(id,orgId,orgName);
				               }
						   }
						
						});
					
					
				}
				else{//  tree	 不是全新的状态，只new table，不new tree
					$.ajax({
						   type: "POST",
						    url: "${pageContext.request.contextPath}/organizationManager/queryOrgLevel.do", 
						    /*   url: "queryOrgLevel.do", 	*/				  
						   data: "id="+id,
						   success: function(msg){
						 //    alert( "Data Saved: " + msg );
						     var dataObj = eval('('+msg+')');
						     
						   //清理table
					//		  clearRowsSMS();
						     var table = document.getElementById("contactTable");
						    
						     for(var i=0;i<dataObj.length;i++){
						    	   var obj = dataObj[i];	//获取一个对象
				            	   var orgId=obj.orgId;
				            	   var orgName=obj.orgName; 

				               }

						   }
						});
					
				};
	
			} 

			//单一实例查询
		function onFind(obj)
		{
		   with(document.forms[0])
		   {
		      action="<%=request.getContextPath()%>/accorgrelation/accorgFindById.do?relationid="+obj
		      submit();
		   }
		}
		
		
		//条件查询以及上一页下一页的跳转
	    function onSelect(obj)
	    {
	    	$("#pageJump").val("");
	       with(document.forms[0])
	       {
	    	   action="<%=request.getContextPath()%>/accorgrelation/accorgList.do?pageNow="+obj;
	          submit();
	       }
	    }
	    

	    //跳转到修改页面
	    function onUpdate(obj)
	    {
	       with(document.forms[0])
	       {
	          action="<%=request.getContextPath()%>/accorgrelation/accorgUpdate.do?relationid="+obj
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
				for (i = 0; i < box_length; i++) {
					box[i].checked = true;
				}

			}

			function unselectAll() {
				//取消选中所有
				var box = document.getElementsByName("chkbox");
				var box_length = box.length;
				for (i = 0; i < box_length; i++) {
					box[i].checked = false;
				}
			}

			function deleteSelected() {
				//删除选中
				var box = document.getElementsByName("chkbox");
				var box_length = box.length;
				//alert(box.length);
				var chkstr = "";
				for (i = 0; i < box_length; i++) {

					if (box[i].checked == true) {
						chkstr += box[i].value + ",";
					}
				}
			//	alert(chkstr);
			
				if(chkstr.length>0){
					chkstr = chkstr.substring(0, chkstr.length - 1);
//					alert($("#ta_id").val());


					//发送ajax请求，到control，把chkstr发过去
					//var a= window.confirm('确定删除');//maojd update date 2014-1-7 10:38:更改confirm的使用。放到。把if(a)发送ajax放到回调函数里面
					window.parent.adtec_confirm.confirm("确认删除",function (a){
						if(a==1){
						 	 $.ajax({
								   type:"POST",
								   async : false,
								   url:"${pageContext.request.contextPath}/accorgrelation/batchdeleteAction.do",
								   data : "ids=" + chkstr,
								   success: function(msg){
									if(msg){
										adtecUtil.showMsg("数据删除成功！");
										setTimeout("onSelect()",1000);
									}else{
										adtecUtil.showMsg("网络故障！");
									}
								   }
							}); 

						}else{
							//点击了取消
						};
					});

					
				}else{
					//$("#msg").html("请选中业务");
					//showMsg();
					adtecUtil.showMsg("请选中业务");
				};
				
			} 

			//全选
		    function checkAll(){
		    	//第一个 checkbox执行的方法。
				var box = document.getElementById("chkfirstbox");
				if (box.checked == true) {
					selectAll();
				}
				if (box.checked == false) {
					unselectAll();
				}
			}

			/*
			隐藏tree
		      */

			function clearCitySel(){
				var cityObj = $("#bcd");	
				cityObj.attr("value","");	//清楚文本框
			}
			function showMenu(){
				//点击文本框，以后，把div的样式设置到input的下面
				var cityObj = $("#bcd");
				var cityOffset = $("#bcd").offset();
				$("#hidenDiv").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
			
				$("body").bind("mousedown", onBodyDown);	//	为body元素的 鼠标点击  绑定onBodyDown处理函数。
			}
			function onBodyDown(event) {
				if (!(event.target.id == "menuBtn" || event.target.id == "bcd" || event.target.id == "hidenDiv" || $(event.target).parents("#hidenDiv").length>0)) {
					hideMenu();
				}//onBodyDown让div 那个tree隐藏
			}
			function hideMenu() {
				$("#hidenDiv").fadeOut("fast");//fadeOut([speed],[easing],[fn])通过不透明度的变化来实现所有匹配元素的淡出效果
												//speed:三种预定速度之一的字符串("slow","normal", or "fast")或表示动画时长的毫秒数值
				$("body").unbind("mousedown", onBodyDown);	//unbind 从每一个匹配的元素中删除绑定的事件
			}  
		  /**
			*取消按钮
			*/
			function cacleMenu() {
				clearCitySel();
				hideMenu();
			}
			

		//删除方法
		function onDelete(relationid) {
			//var a = window.confirm('确定删除');//maojd update date 2014-1-7 10:38:更改confirm的使用。放到。把if(a)发送ajax放到回调函数里面
			window.parent.adtec_confirm.confirm("确认删除",function (a){
				if (a == 1){
					$.ajax({
								type : "POST",
								async : false,
								url : "${pageContext.request.contextPath}/accorgrelation/delAction.do",
								dataType : "json",
								data : "relationid=" + relationid,
								success : function(msg) {
									if(msg == "true"){
										adtecUtil.showMsg("数据删除成功！");
										setTimeout("onSelect()",1000);
									}else{
										adtecUtil.showMsg("网络故障！");
									}
									
								}
							});
				}else{

				}
			});
			
		}

		//树状结构的添加和取消按钮
		/* 	function hideMenuClearName(){
				hideMenu();
				clearCitySel();
			} */
			
		function putTreeName() {
			var cityValue = tree.getSelectedItemText();
			if (cityValue == 0) {
				cityValue = "请选择机构";
			}
			var citySel = $("#bcd").attr("value", cityValue);
			hideMenu();
		}


		//在页面段将删除的记录删除
		function delShow(obj) {

			$(obj).closest("tr").remove();

		}
		
		//分页查询跳转到某一页面
		function onJumpSelect(obj)
		{
			   var pageJump=$("#pageJump").val();
//				var pageVal=pageJump.replace(/^\s+|\s+$/g, '');
				var pageVal = $.trim(pageJump);
				var pageCount=$('.pageCountHidden').val();
				if(checkIsInteger(pageVal)){
					pageVal=pageVal*1;
					pageCount=pageCount*1;
					if(pageVal > pageCount){

						adtecUtil.showMsg("您输入的值超过最大页");

					}else if(pageVal == 0){
						
						//$("#msg").html("请输入正整数");
						//showMsg();
						adtecUtil.showMsg("请输入正整数");

					}else{
					       with(document.forms[0])
					       {
					          action="<%=request.getContextPath()%>/accorgrelation/accorgList.do?pageNow="+obj;
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