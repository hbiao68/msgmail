<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.adtec.com.cn" prefix="adtec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 显示当前页面内容 -->
<adtec:localFileName isShow="true"></adtec:localFileName>
<title>Insert title here</title>
<link rel="STYLESHEET" type="text/css"
	href="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree.css">
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
<script
	src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxcommon.js"></script>
<script
	src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree.js"></script>
<script
	src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree_start.js"></script>
<script
	src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree_json.js"></script>


<style type="text/css">
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
<body onload="init()">
    <div style="display:block;width:100%;min-width:500px;height:300px;">
        <form action="<%=request.getContextPath()%>/accorgrelation/updateAction.do"
        method="post" id="myform">
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <tr>
                        <td align="left" class="barspace toolbgline">
                            <br>
                            <br>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table style="padding-bottom: 10px" width="100%" cellspacing="0" cellpadding="4"
            border="0" class="settingtable" id="contactTable">
                <tbody>
                    <tr>
                        <td colspan="100%">
                            <div style="font-size: 14px; padding: 8px 0 4px 2px; margin: 4px 15px"
                            class="addr_line">
                                帐号机构添加
                            </div>
                        </td>
                    </tr>
                    
					<tr class="normal black">
						<td align="right"><div
								style="width: 120px; white-space: nowrap; text-align: right;">
								帐号：</div></td>
						<td width="96%" align="left">${accountorgrelation.username}</td>
					</tr> 
                    
                    <!-- 下面是给添加业务信息的显示位置 -->
                    <tr class="normal black">
                        <td align="right">
                            机构名称：
                        </td>
                        <td align="left">
                            <input id="orgName" type="text" name="orgName" required readonly="readonly"
                            class="txt noime" size="28" maxlength="100" autocomplete="off" onclick="showMenu();" value="${accountorgrelation.orgName}"
                            />
                            &nbsp;
                            <a id="submitbtn" title="清除机构" class="button" onclick="clearCitySel()">
                                清除机构
                            </a>
                            <font color="red">
                                *
                            </font>
                            <div id="hidenDiv" style="display: none; position: absolute; left: 0px; top: 0px; z-index: 1; background-color: #f5f5f5; border: 1px solid Silver;">
                                <div id="treeboxbox_tree" style="background-color: #f5f5f5; border: 1px solid Silver; margin-top: 0; width: 180px; height: 300px;">
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
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <div class="list_btline">
                        <div class="f_size selbar_bt barspace2" style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px"
                        align="center">
                            <input type="submit" title="修改" class="button" value="修改">
                            <!-- <input type="button" title="返回" name="next" value="返回" class="button" onclick="historyGo()"> -->
                            <input type="button" title="返回" name="next" value="返回" class="button" onclick="javascript:history.go(-1)">
                            
                        </div>
                    </div>
                </tbody>
            </table>
            <input type="hidden" name="orgId" class="orgId">
            <input type="hidden" name="relationid" value="${accountorgrelation.relationid }">
        </form>
    </div>
</body>

</html>

<!-- 下面是树状结构 -->
<script>
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
		$("input[name='orgId']").val(id);

		var cityValue = tree.getSelectedItemText();
		//alert(cityValue);
		//alert(cityValue);
		/* var cityValue = tree.getSelectedItemText();
		if (cityValue == 0) {
			cityValue = "请选择机构";
		}
		var citySel = $("#orgName").attr("value", cityValue);
		 */
		if(cityValue == ""){
			
		}else{
			var citySel = $("#orgName").attr("value", cityValue);
		}
		
		
		var state = tree.getOpenState(id); //state 三种状态，0 表示全新未打开，1表示打开状态。-1，表示关闭状态
		//alert(state);

		if (state == 0) {
			$.ajax({
						type : "POST",
						url : "${pageContext.request.contextPath}/organizationManager/queryOrgLevel.do", 					  
						data : "id=" + id,
						success : function(msg) {
							var dataObj = eval('(' + msg + ')');

							var table = document.getElementById("contactTable");
							for ( var i = 0; i < dataObj.length; i++) {
								var obj = dataObj[i]; //获取一个对象
								var orgId = obj.orgId;
								var orgName = obj.orgName;

								//处罚上面的事件以后，添加 子节点 #重要
								tree.insertNewChild(id, orgId, orgName);
							}
						}
					});
		} else {//  tree	 不是全新的状态，只new table，不new tree
			$.ajax({
						type : "POST",
						url : "${pageContext.request.contextPath}/organizationManager/queryOrgLevel.do",
						data : "id=" + id,
						success : function(msg) {
							var dataObj = eval('(' + msg + ')');
							var table = document.getElementById("contactTable");
							for ( var i = 0; i < dataObj.length; i++) {
								var obj = dataObj[i]; //获取一个对象
								var orgId = obj.orgId;
								var orgName = obj.orgName;

							}

						}
					});

		}

	}
	
	 $(function(){
			//指明校验什么表单
			$("#myform").validate();
		});

	 function onList()
	 {
		 with(document.forms[0])
		 {
			action="<%=request.getContextPath()%>/relation/orgRelationList.do";
			submit();
		 }
	}

	/*
	隐藏tree
	 */

	function clearCitySel() {
		var cityObj = $("#orgName");
		cityObj.attr("value", ""); //清楚文本框
	}
	function showMenu() {
		//点击文本框，以后，把div的样式设置到input的下面
		var cityObj = $("#orgName");
		var cityOffset = $("#orgName").offset();
		$("#hidenDiv").css({
			left : cityOffset.left + "px",
			top : cityOffset.top + cityObj.outerHeight() + "px"
		}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown); //	为body元素的 鼠标点击  绑定onBodyDown处理函数。
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "orgName"
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

	function init() {
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
						tree.insertNewChild('root', orgId, "全国"); //id全  tree.getItemText(id)	全国 （也就是要显示的标签内容）
						tree.enableTreeLines(true);
						clickFunc(orgId);
					}
				});
	}

	//树状结构的添加和取消按钮	
	function putTreeName() {
		var cityValue = tree.getSelectedItemText();
		if (cityValue == 0) {
			cityValue = "请选择机构";
		}
		var citySel = $("#orgName").attr("value", cityValue);
		hideMenu();
	}
	
	function historyGo(){
		$(window.parent.document).find("#folder_24").click();
	}
         
</script>
