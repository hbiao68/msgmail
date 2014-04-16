<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree_json.js"></script>


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


<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 显示当前页面内容 -->
<adtec:localFileName isShow="true"></adtec:localFileName>
<title>List title here</title>
</head>
<body onload="init()">
    <div style="display:block;width:100%;min-width:1122px;height:300px;">
        <form action="<%=request.getContextPath()%>/ta_org_relation/org_relationInsert.do"
        method="post" onsubmit="return (addSubmit());">
            <!-- <div style="display: block; width: 100%; height: 300px;"> -->
            <div class="list_body">
                <div style="_width: 99%" class="txt_title">
                    业务开通管理&nbsp;
                </div>
            </div>
            <!-- 下面是做条件查询-->
            <div style="display: block; width: 100%; height: 100px; float: left">
                <div style="line-height: 14px; zoom: 1;" class="toolbg toolbgline">
                    <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                        <tbody>
                            <tr>
                                <td align="right">
                                    终端类型：
                                </td>
                                <td align="left">
                                    <select name="ta_id" class="ta_idOption" style="width: 150px">
                                        <c:forEach items="${listCate}" var="rows">
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
                                <td align="right">
                                    机构名称：
                                </td>
                                <td align="left">
                                    <input id="bcd" type="text" name="orgName" readonly="readonly" value="${orgName}"
                                    size="20" class="txt noime" maxlength="100" onclick="showMenu();" />
                                    &nbsp;
                                    <!-- <a href="#" onclick="clearCitySel()">clear</a> -->
                                    <a id="submitbtn" class="button" title="清除机构" onclick="clearCitySel()" href="#">
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
                <!-- 下面是选择通过哪个业务来进行查询-->
                <div style="background-color: #c1d9f3;">
                    <table>
                        <c:if test="${empty listApp }">
                        </c:if>
                        <c:if test="${not empty listApp }">
                            <div id="tab">
                                <div class="tab_title">
                                    <div class="vleft">
                                        <strong>
                                            <<
                                        </strong>
                                    </div>
                                    <div class="u">
                                        <ul class="scrol">
                                            <c:forEach items="${listApp }" var="li">
                                                <li title="${li.appName }" targetid="${li.appid }">
                                                    <a style="height: 32px; width: 135px; display: block;" href="#" onclick="return onAppid('${li.appid }')">
                                                        ${li.appName }
                                                    </a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="vright">
                                        <strong>
                                            >>
                                        </strong>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </table>
                </div>
                <!-- 下面是显示查询的数据-->
                <div>
                    <table cellspacing="0" cellpadding="0" class="O2" style="table-layout: fixed; width: 100%; *width: auto;"
                    id="datalist">
                        <tbody>
                            <tr style="height: 20px;" class="egg">
                                <td class="o_title2" width="10%">
                                    序号
                                </td>
                                <td class="o_title2" width="70%">
                                    机构名称
                                </td>
                                <td class="o_title2" width="20%">
                                    操作
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div>
                    <!-- //数据显示 -->
                    <table id="contactTable" cellspacing="0" cellpadding="0" style="table-layout: fixed; width: 100%; *width: auto; background-color: white;">
                        <tbody>
                            <c:if test="${empty orgRelation }">
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
                            <c:if test="${not empty orgRelation }">
                                <c:forEach items="${orgRelation }" var="orgRel" varStatus="vs">
                                    <tr style="height: 20px;">
                                        <td width="10%" style="padding-left: 10px;" title="${vs.index+1 }">
                                            ${vs.index+1 }
                                        </td>
                                        <td width="70%" style="padding-left: 10px;" title="${orgRel.orgName }">
                                            ${orgRel.orgName }
                                        </td>
                                        <td width="20%" style="padding-left: 10px;">
                                            <c:if test="${delPrivlg || wholePrivlg}">
	                                            <a onclick="return onDelete('${orgRel.orgId }')" title="删除" href="#" style="text-decoration: underline">
	                                                删除
	                                            </a>
											</c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
                
      		<adtec:page url="orgRelationList.do" totalLines="${pageModel.count }" curpage="${pageModel.pageNow }" pagesize="${pageModel.pageSize }"></adtec:page>
                
                <div style="" class="list_btline">
                    <div class="f_size selbar_bt barspace2" align="center" style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px">
                        <!-- <span class="addrtitle">选择：</span>
                        <a href="javascript:SetChecked(1);">全部</a> - <a href="javascript:SetChecked(2);">无</a>
                        -->
                        <c:if test="${addPrivlg || wholePrivlg}">
	                        <input type="submit" class="button" name="next" title="开通业务" value="开通业务">
	                        <span>
	                        </span>
						</c:if>
						
                        <input type="button" class="button" name="next" title="查询" value="查询"
                        onclick="onSelect()">
                    </div>
                </div>
            </div>
            <input type="hidden" name="oid" class="oid">
            <%-- <input type="hidden" value="${pageNow}" class="pageNowHidden">
                <input type="hidden" value="${pageCount}" class="pageCountHidden">
                --%>
                <input type="hidden" name="ta_id" value="${ta_id}" id="ta_id">
                <input type="hidden" name="appName" value="${appName}">
                <input type="hidden" name="appid" value="${appid }" id="appid">
                <input type="hidden" value="${listApp }" id="listAppCheck">
                <input type="hidden" value="${pageModel.pageNow}" class="pageNowHidden">
                <input type="hidden" value="${pageModel.pageCount}" class="pageCountHidden">
                <input type="hidden" value="${InsertMsg }" class="insertMsg">
                <input type="hidden" value="${dataMsg}" class="dataMsg">
                <input type="hidden" value="${orgName}" class="ocOrgName">
                
        </form>
        <input type="hidden" name="orgId" value="${orgId }" id="orgId">
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
			var li = $.trim($('.selected').html());
			if(li == null || li == undefined || li == ""){
				$('li:first a').click();
				//$('li:first').dblclick();
			}
	    	$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/organizationManager/queryOrgRoot.do",
				success : function(msg) {
					var dataObj = eval('(' + msg + ')');
					var orgId = dataObj[0].orgId;
					var orgName = dataObj[0].orgName;
					tree.insertNewChild('root',orgId,"全国");  //id全  tree.getItemText(id)	全国 （也就是要显示的标签内容）
					tree.enableTreeLines(true);
					clickFunc(orgId);
				}
			});
	    	
 	    	var dateObj = $(".dataMsg").val();
 	    	if(dateObj=="没有数据，请先添加终端信息！"){
	    		//alert(dateObj);
	    		//$("#msg").html("没有数据，请先添加终端信息！");
				//showMsg();
				adtecUtil.showMsg("没有数据，请先添加终端信息");
				setTimeout("onCateNamePage()",1000);
	    	}else{  			
			
	    	 } 

	    	//从添加页面跳转到查询页面的提示
	    	var InsertMsg=$(".insertMsg").val();

	    	if(InsertMsg=="insertMsg"){
				//$("#msg").html("数据添加成功！");
				//showMsg();
	    		adtecUtil.showMsg("数据添加成功");
	    	}else if(InsertMsg == "failMsg"){
				adtecUtil.showMsg("网络故障！");
			}else{
	    		
	    	};
	    }
	    
		tree = new dhtmlXTreeObject("treeboxbox_tree", "100%", "100%", "root");

		tree.setSkin('dhx_skyblue');
		tree.setImagePath("${pageContext.request.contextPath}/dhtmlxtree/common/images/");
		tree.enableDragAndDrop(0);
		tree.enableTreeLines(false);
		tree.setImageArrays("plus.gif", "", "", "", "plus.gif");
		tree.setImageArrays("minus.gif", "", "", "", "minus.gif");
		tree.setStdImages("book.gif", "books_open.gif", "books_close.gif");

		//tree.setOnDblClickHandler(dbclickFunc);//双击事件
		tree.setOnClickHandler(clickFunc);

		function clickFunc(id) {
			//alert(id);
			$("input[name='oid']").val(id);
			var cityValue;
			//条件查询中的机构名称点击查询后仍然在文本框中显示
			var ocOrgName = $(".ocOrgName").val();
			if(ocOrgName !="" && tree.getSelectedItemText() == ""){
				cityValue = ocOrgName;
			}else{
				cityValue = tree.getSelectedItemText();
			}
			//var citySel = $("#orgName").attr("value", cityValue);
			var citySel = $("#bcd").attr("value", cityValue);
			
			var state = tree.getOpenState(id); //state 三种状态，0 表示全新未打开，1表示打开状态。-1，表示关闭状态

			if (state == 0) {

				$.ajax({
							type : "POST",
							url : "${pageContext.request.contextPath}/organizationManager/queryOrgLevel.do",
							//   url: "queryOrgLevel.do", 					  
							data : "id=" + id,
							success : function(msg) {
								//    alert( "Data Saved: " + msg );
								var dataObj = eval('(' + msg + ')');

								//清理table
								//	  clearRowsSMS();
								var table = document
										.getElementById("contactTable");

								for ( var i = 0; i < dataObj.length; i++) {
									var obj = dataObj[i]; //获取一个对象
									var orgId = obj.orgId;
									var orgName = obj.orgName;

									var tr = table.insertRow(table.rows.length);
									var td1 = tr.insertCell(0);
									var td2 = tr.insertCell(1);
									var td3 = tr.insertCell(2);

									//   td1.innerHTML = obj.orgId;拼装一下需要显示的 机构号码
									var levelText = "";
									if (obj.level1 != null) {
										//alert(obj.level1);
										levelText = levelText + obj.level1;
									}
									if (obj.level2 != null
											&& obj.level2 != undefined
											&& obj.level2 != "") {
										levelText = levelText + "|"
												+ obj.level2;
									}
									if (obj.level3 != null
											&& obj.level3 != undefined
											&& obj.level3 != "") {
										levelText = levelText + "|"
												+ obj.level3;
									}
									if (obj.level4 != null
											&& obj.level4 != undefined
											&& obj.level4 != "") {
										levelText = levelText + "|"
												+ obj.level4;
									}
									if (obj.level5 != null
											&& obj.level5 != undefined
											&& obj.level5 != "") {
										levelText = levelText + "|"
												+ obj.level5;
									}
									if (obj.level6 != null
											&& obj.level6 != undefined
											&& obj.level6 != "") {
										levelText = levelText + "|"
												+ obj.level6;
									}
									if (obj.level7 != null
											&& obj.level7 != undefined
											&& obj.level7 != "") {
										levelText = levelText + "|"
												+ obj.level7;
									}

									//处罚上面的事件以后，添加 子节点 #重要
									tree.insertNewChild(id, orgId, orgName);
								}

							}

						});

			} else {//  tree	 不是全新的状态，只new table，不new tree
				$.ajax({
							type : "POST",
							url : "${pageContext.request.contextPath}/organizationManager/queryOrgLevel.do",
							/*   url: "queryOrgLevel.do", 	*/
							data : "id=" + id,
							success : function(msg) {
								//    alert( "Data Saved: " + msg );
								var dataObj = eval('(' + msg + ')');

								//清理table
								//  clearRowsSMS();
								var table = document
										.getElementById("contactTable");

								for ( var i = 0; i < dataObj.length; i++) {
									var obj = dataObj[i]; //获取一个对象
									var orgId = obj.orgId;
									var orgName = obj.orgName;

									var tr = table.insertRow(table.rows.length);
									var td1 = tr.insertCell(0);
									var td2 = tr.insertCell(1);
									var td3 = tr.insertCell(2);

									//   td1.innerHTML = obj.orgId;拼装一下需要显示的 机构号码
									var levelText = "";
									if (obj.level1 != null) {
										//alert(obj.level1);
										levelText = levelText + obj.level1;
									}
									if (obj.level2 != null
											&& obj.level2 != undefined
											&& obj.level2 != "") {
										levelText = levelText + "|"
												+ obj.level2;
									}
									if (obj.level3 != null
											&& obj.level3 != undefined
											&& obj.level3 != "") {
										levelText = levelText + "|"
												+ obj.level3;
									}
									if (obj.level4 != null
											&& obj.level4 != undefined
											&& obj.level4 != "") {
										levelText = levelText + "|"
												+ obj.level4;
									}
									if (obj.level5 != null
											&& obj.level5 != undefined
											&& obj.level5 != "") {
										levelText = levelText + "|"
												+ obj.level5;
									}
									if (obj.level6 != null
											&& obj.level6 != undefined
											&& obj.level6 != "") {
										levelText = levelText + "|"
												+ obj.level6;
									}
									if (obj.level7 != null
											&& obj.level7 != undefined
											&& obj.level7 != "") {
										levelText = levelText + "|"
												+ obj.level7;
									}
								}
							}
						});
			}
		}
		
		/*
		隐藏tree
		 */

		function clearCitySel() {
			var cityObj = $("#bcd");
			cityObj.attr("value", ""); //清楚文本框
		}
		function showMenu() {
			//点击文本框，以后，把div的样式设置到input的下面
			var cityObj = $("#bcd");
			var cityOffset = $("#bcd").offset();
			$("#hidenDiv").css({
				left : cityOffset.left + "px",
				top : cityOffset.top + cityObj.outerHeight() + "px"
			}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown); //	为body元素的 鼠标点击  绑定onBodyDown处理函数。
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "bcd"
					|| event.target.id == "hidenDiv" || $(event.target).parents(
					"#hidenDiv").length > 0)) {
				hideMenu();
			}//onBodyDown让div 那个tree隐藏
		}
		function hideMenu() {
			$("#hidenDiv").fadeOut("fast");//fadeOut([speed],[easing],[fn])通过不透明度的变化来实现所有匹配元素的淡出效果
			//speed:三种预定速度之一的字符串("slow","normal", or "fast")或表示动画时长的毫秒数值
			$("body").unbind("mousedown", onBodyDown); //unbind 从每一个匹配的元素中删除绑定的事件
		}
		
		//取消按钮
		function cacleMenu() {
			clearCitySel();
			hideMenu();
		}

		
		//树状结构的添加和取消按钮

		function putTreeName() {
			var cityValue = tree.getSelectedItemText();
			if (cityValue == 0) {
				cityValue = "请选择机构";
			}
			var citySel = $("#bcd").attr("value", cityValue);
			hideMenu();
		}
	    
    
			//如果是第一页，将上一页按钮隐藏
			function hiddenPageUp(){
				var pageNowHidden=$('.pageNowHidden').val();
				if(pageNowHidden <= 1){
					//$("#pageUp").css("visibility", "hidden");
					/*maojd  update  date:17:54 2013/12/19 		只是标签隐藏，还可以看到下划线，点击就回报错，所以修改为disable:none*/
					$("#pageUp").css('display','none');
				} else {
					//$("#pageUp").css("visibility", "visible");
					$("#pageUp").css('display','block');
				};

			}
			//如果是最后一页，将下一页下一页隐藏
			function hiddenPageDown(){
				var pageNow=$('.pageNowHidden').val();
				var pageCount=$('.pageCountHidden').val();

				if (pageNow >= pageCount) {

					//$("#pageDown").css("visibility", "hidden");
					$("#pageDown").css('display','none');
				} else {
					//$("#pageDown").css("visibility", "visible");
					$("#pageDown").css('display','block');
				};

			}		
			
			
			//条件查询以及上一页下一页的跳转
		    function onSelect(obj)
		    {
		    	$("#pageJump").val("");
		       with(document.forms[0])
		       {
		    	   action="<%=request.getContextPath()%>/ta_org_relation/orgRelationList.do?pageNow="+obj;
		           submit();
		       }
		    }
		    

		    //单一实例查询
		    function onAppid(obj){
		        with(document.forms[0])
		        {
		    //	document.getElementById("cateName").value=obj;
		    	//alert(1);
		    	action="<%=request.getContextPath()%>/ta_org_relation/orgRelationList.do?appid="+obj;
		    			submit();
		    	}
		    }

			//当没有数据时，跳转到端点分类管理页面进行添加数据
			function onCateNamePage(){
				/* with(document.forms[0]){
					action="${pageContext.request.contextPath}/category/list.do";
					submit();
				} */
				/* maojd update  直接点击父窗体的终端分类管理*/
				//$(window.parent.document).find("#folder_12").click();
				$(window.parent.document).find("#OutFolder a").each(function (index, domEle) {
		    		var str = domEle.attributes.onclick.value;//利用js获取onclick属性值，搜索url
					var start = str.indexOf ('category/cateList.do');
					if(start>0){
						$(domEle).click();
					}
		    	});
			}


	//删除方法
	function onDelete(orgId) {
		//var a = window.confirm('确定删除');//maojd update date 2014-1-7 10:38:更改confirm的使用。放到。把if(a)发送ajax放到回调函数里面
		window.parent.adtec_confirm.confirm("确认删除",function (a){
			if (a == 1) {
				$.ajax({
							type : "POST",
							async : false,
							url : "${pageContext.request.contextPath}/ta_org_relation/delAction.do",
							dataType : "json",
							data : {
								"orgId" : orgId,
								"appid" : $("#appid").val(),
								"ta_id" : $("#ta_id").val()
							},
							success : function(msg) {
								//$("#msg").html("删除成功");
								//showMsg();
								adtecUtil.showMsg(msg);
								setTimeout("onSelect()",1000);
							}
						});
			} else {

			}
		});
		
	}

	//tab选项卡以及左右滑动按钮
	$(function() {
		//维持条件查询中ta_id的下拉列表的状态
		$('.ta_idOption').val($('#ta_id').val());

		var appidAfter = $('#appid').val();

		var index = 0;
		$(".tab_title ul li").click(function() {
			index = $(".tab_title ul li").index(this);

			$(this).addClass("selected").siblings().removeClass("selected");
			$(".tab_content div").eq(index).show().siblings().hide();
		});

		var i = 8; //定义每个面板显示8个菜单

		var len = $(".u .scrol li").length; //获得LI元素的个数
		var page = 1;
		var maxpage = Math.ceil(len / i);
		var scrollWidth = $(".u").width();
		var divbox = "<div id='div1' >已经到最后一个面板了</div>";
		$("body").append(divbox);
		$(".vright").click(function(e) {
			if (!$(".u .scrol").is(":animated")) {
				if (page == maxpage) {
					$(".u .scrol").stop();
					$("#div1").css({
						"top" : (e.pageY + 20) + "px",
						"left" : (e.pageX + 20) + "px",
						"opacity" : "0.9"

					}).stop(true, false).fadeIn(800).fadeOut(800);

				} else {
					$(".u .scrol").animate({
						left : "-=" + scrollWidth + "px"
					}, 2000);
					page++;
				}
			}
		});
		
		$(".vleft").click(function() {
			if (!$(".u .scrol").is(":animated")) {
				if (page == 1) {
					$(".u .scrol").stop();
				} else {
					$(".u .scrol").animate({
						left : "+=" + scrollWidth + "px"
					}, 2000);
					page--;
				}
			}
		});

		//计算显示位置
		$(".tab_title ul li[targetid='" + appidAfter + "']").addClass(
				"selected");
		var obj = $(".tab_title ul li[targetid='" + appidAfter + "']");
		var indexNum = $(".tab_title ul li").index(obj) + 1;

		var currentPage_top = Math.ceil(indexNum / i) - 1;
		for ( var m = 0; m < currentPage_top; m++) {
			$(".vright").click();
		}
	});

	//校验添加时，是否有业务
	function addSubmit() {
		var appidFlag = true;//是否被选中的标记
		var appidCheck = $('#listAppCheck').val();

		if(appidCheck == null || appidCheck == "" || appidCheck == undefined){
			appidFlag = false;

			adtecUtil.showMsg("该终端下尚无业务");
		}
		return appidFlag;
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
				          action="<%=request.getContextPath()%>/ta_org_relation/orgRelationList.do?pageNow="+obj;
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