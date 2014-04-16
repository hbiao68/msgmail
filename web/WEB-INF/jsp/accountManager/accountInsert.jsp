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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/util/jquery-1.4.4.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/util/jquery.validate.min.js"></script>
<link rel="STYLESHEET" type="text/css"
	href="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>



<!-- 下面是树状结构所需 -->
<script
	src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxcommon.js">    </script>
<script
	src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree.js">    </script>
<script
	src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree_start.js">    </script>
<script
	src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree_json.js">    </script>

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
<body onload="init()">
    <div style="display:block;width:100%;min-width:500px;height:500px;">
        <form action="<%=request.getContextPath()%>/accountManager/insertAction.do"
        method="post" id="myform" onsubmit="return false;">
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <tr>
                        <td nowrap="" align="left" class="barspace toolbgline">
                            <br>
                            <br>
                        </td>
                    </tr>
                </tbody>
            </table>
            <!-- 下面是添加账户信息的显示位置-->
            <table style="padding-bottom: 10px" width="100%" cellspacing="0" cellpadding="4"
            border="0" class="settingtable" id="contactTable">
                <tbody>
                    <tr>
                        <td colspan="100%">
                            <div style="font-size: 14px; padding: 8px 0 4px 2px; margin: 4px 15px"
                            class="addr_line">
                                添加账户
                            </div>
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            <div style="width: 120px; white-space: nowrap; text-align: right;">
                                帐号：
                            </div>
                        </td>
                        <td width="96%" align="left">
                            <input id="accountName" type="text" name="accountName" class="accountName txt noime"
                            maxlength="20" size="28" maxlength="30" required onchange="account()">
                            &nbsp;
                            <font color="#FF0000">
                                *
                            </font>
                            <font class="tishi" style="color: red; font-size: 12px">
                            </font>
                        </td>
                    </tr>
<!--                     <tr class="normal black">
                        <td align="right">
                            密码：
                        </td>
                        <td align="left">
                            <input type="password" name="accountPwd" id="mypassword" maxlength="32"
                            size="28" class="txt noime" autocomplete="off">
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            请再次输入密码：
                        </td>
                        <td align="left">
                            <input type="password" name="repassword" id="repassword" equalTo="#mypassword"
                            maxlength="32" size="28" class="txt noime" autocomplete="off">
                        </td>
                    </tr> -->
                    <tr class="normal black">
                        <td align="right">
                            邮箱：
                        </td>
                        <td align="left">
                            <input type="text" id="email" name="email" maxlength="100" size="28" class="txt noime"
                            autocomplete="off" onblur="emailCheck()">
                            &nbsp;
                            <font id="emailMsg" style="font-size: 12px;color: red;">
                            </font>
                            <font color="red">
                                
                            </font>
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            机构名称：
                        </td>
                        <td align="left">
                            <input id="orgName" type="text" readonly="true" value="" size="28"
                            class="txt noime" required onclick="showMenu();"
                            />
                            &nbsp;
                            <a id="submitbtn" title="清除机构" class="button" onclick="clearCitySel()" href="#">
                                            清除机构
                            </a>
                            &nbsp;
                            <font id="orgNameMsg">
                            </font>
                            <font color="red">
                                *
                            </font>
                            <!-- <a href="#" onclick="clearCitySel()">clear</a> -->
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
                    <c:if test="${not empty extendpropdef }">
                        <c:forEach items="${extendpropdef }" var="ex" varStatus="vs">
                            <tr class="normal black">
                                <td align="right">
                                    ${ex.propDesc }:
                                </td>
                                <td>
                                    <input size="28" class="txt noime" autocomplete="off" name="prop${vs.index+1 }"
                                    maxlength="50">
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <!-- <div style="display:block;height:0px;float:right">
                    <div class="attbg mysidebar">
                    <div class="bd b_size bold title_left">
                    <div style="margin: 2px 1px 0 0" class="right f_size normal">
                    <a href="javascript:void(0);" onclick="return addDep();">添加机构</a>
                    </div>
                    所有机构
                    </div>
                    <div class="bd b_size bold title_left">
                    </div>
                    </div>
                    </div> -->
            </table>
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <div style="" class="list_btline">
                        <div class="f_size selbar_bt barspace2" style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px"
                        align="center">
                            <input type="submit" title="添加" name="next" value="添加" class="add button">
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <!-- <input type="button" title="返回" name="next" value="返回" class="button" onclick="historyGo()"> -->
                            <input type="button" title="返回" name="next" value="返回" class="button" onclick="javascript:history.go(-1)">
                        </div>
                    </div>
                </tbody>
            </table>
            <input type="hidden" name="cateName" value="${cateName }" id="cateName">
            <input type="hidden" id="inputIdmao" name="input" class="input">
        </form>
    </div>
    <!-- span做alert使用，用户操作成功之后，会在浏览器的正中间提示.span的会变化 在$("#msg").html("xx");中做处理。
    -->
    <span style="display:none;" class="msg" id="msg">
        &nbsp;
    </span>
</body>


<!-- 下面是树状结构 -->


<script  type="text/javascript" language="javascript">
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
		$("input[name='input']").val(id);

		
		var cityValue = tree.getSelectedItemText();
		var citySel = $("#orgName").attr("value", cityValue);
		
		
		if(cityValue == "全国"){
			//$("#msg").html("请选择具体机构");
			//showMsg();
			//$('.add').attr("disabled", "disabled");
			$("#orgNameMsg").text('请选择具体机构');
			//$('.add').attr("disabled", "disabled");
			//alert("群过");
			return false;
		}else{
			$("#orgNameMsg").text('');
			//$('.add').attr("disabled", "");
			
		}
		
		//	var abc=tree.getItemText(id);

		var state = tree.getOpenState(id); //state 三种状态，0 表示全新未打开，1表示打开状态。-1，表示关闭状态
		//alert(state);

		if (state == 0) {
			//alert("ajax");

			$.ajax({
						type : "POST",
						url : "${pageContext.request.contextPath}/organizationManager/queryOrgLevel.do",
						//   url: "queryOrgLevel.do", 					  
						data : "id=" + id,
						success : function(msg) {
							//  alert( "Data Saved: " + msg );
							var dataObj = eval('(' + msg + ')');

							var table = document.getElementById("contactTable");
							//  alert(table);
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
						/*   url: "queryOrgLevel.do", 	*/
						data : "id=" + id,
						success : function(msg) {
							//    alert( "Data Saved: " + msg );
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


	

	//表单校验
	 $(function(){
			//指明校验什么表单
			//$("#myform").validate();
			
			
			//maojd update date:2014/2/11 10:33   onsubmit一起使用在ie8下有问题。所以改成这种方式。在validate通过之后从新验证自己需要验证的值
			
			$("#myform").validate({
						 onsubmit:true,// 是否在提交时验证,默认也是true 
						 
						 //validate外部js验证通过之后，点击提交表单回调 此函数 
						 submitHandler: function(form) {  
							// alert(form);
							//验证账号，在account自己控制添加按钮的disable属性
							account();
							//验证密码两次是否一致
							if(!pasEqual()){
								return false;
							};
							// 验证邮箱
							if(!emailCheck()){
								//alert("email");
								return false;
							};
							//验证机构名称.没有选择，或者选择了全国
							if(!orgNameCheck()){
								//alert("我验证了并且 return false了 ");
								return false;
							};
							//alert("真的提交 ");
						    with(document.forms[0]){
									action="<%=request.getContextPath()%>/accountManager/insertAction.do";
									submit();
							}
						    //form.submit();提交表单，如果不写，即便通过表单也不会自动提交 
						}, 
					      
						 //不通过，点击提交回调 
						invalidHandler: function(form, validator) { 
							//alert("not pass验证没有通过"); 
						    return false;
						},
					});
	});	
		
   

	//校验帐号是否已存在
   function account(){
   	if(onEnglisth()){ 
   	var accountName=$.trim($('.accountName').val());

   	 $.ajax({
   		 type: "POST",
   		 dataType:"json",
   		 data: {"accountName":accountName,"cateName":$("#cateName").val()},
   		 url:  "${pageContext.request.contextPath}/accountManager/check.do",
				success : function(msg) {
						//$('.tishi').text(obj.acc=='0'?'此帐号可使用':'此帐号已存在');
						if (msg == '0') {
							$('.tishi').text('此帐号可使用');
							$('.add').attr("disabled", "");

						} else {
							$('.tishi').text('此帐号已存在');
							$('.add').attr("disabled", "disabled");
						}
					
				}
			});
		} else {

		}

	}


	//验证accountName是否是英文
	function onEnglisth() {
		//  var strName=${"#strName"}.val();
		var strName = document.getElementById('accountName').value;
		//alert(strName+onEnglisth(strName));
		if (isEnTrueName(strName)) {
			//$('.tishi').text('此ID可正常使用');
			$('.tishi').text('');
			$('.add').attr("disabled", "");
			return true;
		} else {
			$('.tishi').text('请输入英文或数字ID');
			$('.add').attr("disabled", "disabled");
			return false;
		}

	}
	/*maojd update:修改方法名，验证是否英文的方法，和验证邮箱的方法重名*/
	//校验是否为中文
	function isEnTrueName(strName) {
		//   var abc = strName.trim();
		var abc = strName.replace(/(^\s*)(\s*$)/g, "");
		//	 var abc=strName;
		//alert("==="+abc+"====");
		//    var str = Trim(strName);   //判断是否为全英文大写或全中文，可以包含空格
		var reg = /^[a-z A-Z 0-9 _]+$/;
		if (reg.test(abc)) {
			return true;
		}else{
			return false;
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
		
		/*maojd update:添加账号到某个机构下。如果只有全国虚拟节点，则不展示机构tree*/
		$.ajax({
			type : "POST",
			// url : "${pageContext.request.contextPath}/organizationManager/queryOrgRoot.do",
			url : "${pageContext.request.contextPath}/organizationManager/queryOrgLevel.do",
			data : "id=" + 0,
			success : function(msg) {
				var dataObj = eval('(' + msg + ')');
				
				if(dataObj == null || dataObj == ""){
					//$("#msg").html("请先导入机构");
					//showMsg();
					adtecUtil.showMsg("请先导入机构");
				}else{
					//点击文本框，以后，把div的样式设置到input的下面
					var cityObj = $("#orgName");
					var cityOffset = $("#orgName").offset();
					$("#hidenDiv").css({
						left : cityOffset.left + "px",
						top : cityOffset.top + cityObj.outerHeight() + "px"
					}).slideDown("fast");

					$("body").bind("mousedown", onBodyDown); //	为body元素的 鼠标点击  绑定onBodyDown处理函数。
				}
			}
		});
		
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

	//树状结构的添加和取消按钮	

	function putTreeName() {
		var cityValue = tree.getSelectedItemText();
		if (cityValue == 0) {
			cityValue = "请选择机构";
		}
		var citySel = $("#orgName").attr("value", cityValue);
		hideMenu();
	}

	 //初始化
	function init() {
		//maojd update date:11:29 2013/12/30	页面一旦刷新，外部adtecUtil.js中定时 的隐藏 操作成功提示信息的方法失效。所以onload方法中，执行隐藏方法
		$(window.parent.document).find("#msgBoxDIV").fadeOut("slow");
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
						tree.insertNewChild('root', orgId, "全国"); //id全  tree.getItemText(id)	全国 （也就是要显示的标签内容）
						tree.enableTreeLines(true);
						clickFunc(orgId);
					}
				});

	}
	 
	/*  maojd update 邮箱验证中文 */ 
	function emailCheck(){
		var email = document.getElementById("email").value.replace(/^\s+|\s+$/g, '');;
		var emailMsg = document.getElementById("emailMsg");
		if(isValidTrueName(email)){
			emailMsg.innerText = "";
			return true;
		}else if(email == ""){
			emailMsg.innerText = "";
			return true;
		}else{
			//console.info(emailMsg);
			emailMsg.innerText = "请输入正确格式的电子邮件";
			//$("#emailMsg").focus();
			return false;
		}
	}
	 
	function isValidTrueName(temp){
		//对电子邮件的验证
		/* var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		// /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		if (!myreg.test(temp)) {
			//alert('提示\n\n请输入有效的E_mail！');
			//myreg.focus();
			return false;
		}else{
			return true;
		} */
		
		//var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{1,9}){1,9})$/;
		//var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])$/;
        return reg.test(temp);
	}
	
	//moajd update 添加手动验证 密码是否一致
	//验证密码是否一致,一致返回true.否则返回false
	 function pasEqual(){
		var mypassword = $("#mypassword").val();
		var repassword = $("#repassword").val();
		//alert(mypassword + "." + repassword+"是否一样" + (mypassword==repassword));
		if(mypassword==repassword){
			return true;
		}else{
			return false;
		}
	}
	
	//验证机构名称
	function orgNameCheck(){
		var orgName = $("#orgName").val();
		var cityValue = tree.getSelectedItemText();
		if(($.trim(orgName) == "") || (cityValue == "全国")){
			$("#orgNameMsg").text('请选择具体机构');
			return false;
		}else{
			return true;
		}
		//return false;
	}
	
	function beforeSubmit(){
		return false;
	}
	
	
	function historyGo(){
		$(window.parent.document).find("#folder_14").click();
	}
	
	
	
	function onInsert(){
		/* var ifsub = true;
		//验证账号，在account自己控制添加按钮的disable属性
		account();
		//验证密码两次是否一致
		if(!pasEqual()){
			ifsub = false;
			return false;
		};
		// 验证邮箱
		if(!emailCheck()){
			alert("email");
			ifsub = false;
			return false;
		};
		//验证机构名称.没有选择，或者选择了全国
		//alert(orgNameCheck());
	//	var cityValue = tree.getSelectedItemText();
		if(!orgNameCheck()){
	//		$("#orgName").val("");
			alert("我验证了并且 return false了 ");
			ifsub = false;
			return false;
			//alert("我照样提交");
		};
		alert("真的提交 ");
		alert(ifsub);
		if(ifsub){
			
		} */
       
		with(document.forms[0])
	       {
			action="<%=request.getContextPath()%>/accountManager/insertAction.do";
			submit();
		   }

	}

</script>
</html>