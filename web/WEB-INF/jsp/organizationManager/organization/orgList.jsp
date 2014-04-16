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


<link href="${pageContext.request.contextPath}/ui/css/skin.css" rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath}/ui/css/common.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/ui/css/getcss.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/add.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/ui/css/mail_group_add.css" type="text/css" rel="stylesheet">
<link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree.css">
	
<script src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxcommon.js"></script>
<script src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree.js"></script>
<script src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree_start.js"></script>
<script src="${pageContext.request.contextPath}/dhtmlxtree/dhtmlxtree_json.js"></script>
<script src="${pageContext.request.contextPath}/js/util/jquery-1.4.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>


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

#contactTable td{
	border-bottom: 1px solid #e3e6eb;
	height: 25px;
	padding: 0 0 0 12px;
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



    <div style="display:block;width:1100px;height:300px;">
        <div class="list_body">
            <div style="_width: 99%" class="txt_title">
                <!-- <div class="search_subject nowrap" style="float:right;margin-top:-5px;_margin-top:0px;font:normal
                12px Verdana;top:5px;_top:-3px;">
                <div>
                <div class="smartsearch" id="smartSearch"><input type="text" style="color: rgb(160, 160, 160);" value="" autocomplete="off" name="search" id="search" newattachfolder="1" fullsearch="1" class="searchinput"><span id="searchIcon" class="ss_icon ss_fronticon ss_icon_search"></span><span onclick="searchMember();return false;" id="subjectsearchLogo" class="ss_icon ss_endicon ss_icon_arrowdown" style="cursor: pointer;"></span></div>
                </div> -->
                机构导入&nbsp;
                <!-- 标题 -->
            </div>
            <!-- <span class="f_size normal black" id="_ut"> (共 <span id="_ut_c" class="red"><b>2</b></span> 个联系人)</span> -->
        </div>
        
        
        <div style="display:block;width:900px;height:100px; float:left">
            <div style="line-height: 24px; zoom: 1;" class="toolbg toolbgline">
                <div class="right">
                    <!-- 1/1 页&nbsp; -->
                </div>
                <!-- <input type="button" onclick="orgUpload()" value="机构导入" />
                -->
                <a title="机构导入" id="submitbtn" class="button_gray_s" name="submitbtn1" onclick="javascript:orgUploadPage()" href="javascript:void(0);" style="margin-right:5px;width:80px">机构导入</a>
                <!-- <input class="button_gray_s" name="submitbtn1" style="width:80px;background-position:0-128px;margin-right:5px;" type="button" onclick="javascript:orgUploadPage()" value="机构导入"/> -->
            </div>
            <div>
                <table cellspacing="0" cellpadding="0" class="O2" style="table-layout: fixed; width: 100%; *width: auto;">
                    <tbody>
                        <tr style="height:20px;">
                            <!-- <td><input type="checkbox" id="chkfirstbox" onclick="chkfirstbox()" /> </td> -->
                            <td class="o_title2">
                                机构号码
                            </td>
                            <td class="o_title2">
                                机构名称
                            </td>
                            <td style="padding-left: 25px;" class="o_title2">
                                操作
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div>
            
            <div style="display:none;" id="tips_bar">
					<div class="nomail"><b>当前没有数据</b></div>
				</div>
            
            	
             <!-- 要table的高度随着数据的大小变化，下面有min-height的属性去掉即可  25px*pageSize +5  -->
           	<!--  <div style="min-height:130px;"> -->
                <!-- 数据显示 -->
                <table id="contactTable" cellspacing="0" cellpadding="0" style="table-layout: fixed; width: 100%; *width: auto; background-color: white;">
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="list_btline">
                <div class="f_size selbar_bt barspace2" style="height: 24px; padding-top: 3px">
                    <!-- <span class="addrtitle">选择：</span>
                    <a href="javascript:selectAll();">全部</a> - <a href="javascript:unselectAll();">无</a>
                    &nbsp;&nbsp; <a href="javascript:deleteSelected();">删除</a>
                    -->
                    <DIV class=right>
                        <font id="pageNow">
                        </font>
                        /
                        <font id="pageCount">
                        </font>
                        页&nbsp;
                        <A id="uppage" href="javascript:pageUp()">
                            上一页
                        </A>
                        &nbsp;
                        <A id="nextpage" href="javascript:pageNext()">
                            下一页
                        </A>
                        &nbsp;
                        <input type="text" style="width:20px;" id="page_jump">
                        <A title="跳转到指定一页" href="#" onclick="page_jump()" name="page_jump">
                            跳转
                        </A>
                    </DIV>
                </div>
            </div>
            <div style="" class="list_btline">
                <div class="f_size selbar_bt barspace2" style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px">
                    <!-- <span class="addrtitle">选择：</span>
                    <a href="javascript:SetChecked(1);">全部</a> - <a href="javascript:SetChecked(2);">无</a>
                    -->
                    机构名称：
                    <input maxlength="15" id="orgName" name="orgName" type="text" value=""
                    onblur="nameMsg()" />
                    <font color="red">
                        *
                    </font>
                    <span id="span1">
                    </span>
                    <c:if test="${addPrivlg || wholePrivlg}">
	                    <input title="添加" class="button" type="button" value="添加" onclick="addOrg();">
					</c:if>
                </div>
            </div>
        </div>
        
        
        <div style="display:block;height:100px;float:right">
        <div class="attbg mysidebar">
            <div class="bd b_size bold title_left">
                <div style="margin: 2px 1px 0 0" class="right f_size normal">
                    <!-- <a href="javascript:void(0);" onclick="return addDep();">添加机构</a> -->
                </div>
                所有机构
            </div>
            <div class="bd b_size bold title_left">
                <div id="treeboxbox_tree" style="width: 100%; height: 300px; background-color: #f5f5f5; border: 1px solid Silver;">
                </div>
            </div>
       </div>
        </div>
    </div>
    
    
    
    
    <!-- span做alert使用，用户操作成功之后，会在浏览器的正中间提示.span的值会根据用户上网不同操作 变化   $("#msg").html("删除成功"); 中做处理。 -->
    <span style="display:none;" class="msg" id="msg">&nbsp;</span>
    
    <!-- 从后台获取操作成功信息的判断。来判断做的什么操作 -->
	<input id="sucMsg" type="hidden" value="${sucMsg}">	
	<input id="updatePrivlg" type="hidden" value="${updatePrivlg}">	
	<input id="delPrivlg" type="hidden" value="${delPrivlg}">	
	<input id="wholePrivlg" type="hidden" value="${wholePrivlg}">	
	
	
	
	
	<!-- 这个form做页面刷新所用。这种刷新方式可以清除el表达式在input中的值 -->
	<form action="<%=request.getContextPath()%>/organizationManager/orgList.do" method="post">
	</form>
	
</body>
</html>

<script>
		var dataAll="";
		var count = 0;
		var pageSize = 5;
		var pageNow =1;
		var pageCount = 1;
		
		//定义的权限变量。获取隐藏域是否有删除修改的权限。机构管理的修改和删除按钮是通过js控制的，所有
		var wholePrivlg = $('#wholePrivlg').val();
		var delPrivlg = $('#delPrivlg').val();
		var updatePrivlg = $('#updatePrivlg').val();// "" 空字符串认为是false
		
		if(wholePrivlg == "true" || wholePrivlg == true)wholePrivlg = true;
		else wholePrivlg = false;
		
		if(delPrivlg == "true" || delPrivlg == true) delPrivlg = true;
		else delPrivlg = false;
		
		if(updatePrivlg == "true" || updatePrivlg == true) updatePrivlg = true;
		else updatePrivlg = false;
		
		
		
		
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
			
			//根据id获取内容的方法是 tree.getItemText(id)
			//alert("dbclick "+id+" Item " + tree.getItemText(id) + " was selected");
			//var abc = tree._getLeafCount(itemNode);判断子节点的个数
			//alert("节点个数"+abc+"--id 是"+id); 

			/* var num = tree.getAllChildless();//获取所有 未打开过的child
			alert(num); */
			var state = tree.getOpenState(id); //state 三种状态，0 表示全新未打开，1表示打开状态。-1，表示关闭状态
			//alert(state);
			if (state == 0) { //避免多次点击tree的时候，多次出现子 节点
				//alert("ajax");

				$.ajax({
							type : "POST",
							/*  url: "http://localhost:8080/myspring/organizationManager/queryOrgLevel.do", */
							url : "${pageContext.request.contextPath}/organizationManager/queryOrgLevel.do",
							data : "id=" + id,
							success : function(msg) {
								//   alert( "Data Saved: " + msg );
								var dataObj = eval('(' + msg + ')');
								
								dataAll = dataObj;
								pageFunction();//加载page的所需数据  
								var selectOrgName = tree.getItemText(id);
								if(selectOrgName == "全国" && (dataObj == null || dataObj == "")){
									document.getElementById("tips_bar").style.display="block";
								 
								}else if(dataObj == null || dataObj == ""){
									//没有子机构的显示 	start		
									$.ajax({//发送Ajax请求，得到一个org
										type:"POST",
										url:"${pageContext.request.contextPath}/organizationManager/queryOrgById.do",
										data:"id="+id,
										success:function(msg){
											var dataObj = eval('(' + msg + ')');
											//alert(dataObj.length);
											dataAll = dataObj;
											pageFunction();//加载page的所需数据 
											
											pageNow = 0;	//没有子结构的话，直接展示第一页。pageNow =0;执行pageNext
											pageNext();
											
										}
									});
									//没有子机构的显示		end
								}else{
									 //else 有子机构		elsestart
									//清理table.		for循环	tree.insertNewChild添加子节点
									//clearRowsSMS();
									//var table = document.getElementById("contactTable");//在pageNext方法中会清理table
									
									dataAll = dataObj;
									pageFunction();//加载page的所需数据  在if之前已经加载过。此处可以省去重复加载
									pageNow = 0;
									pageNext();
									
									for ( var i = 0; i < dataObj.length; i++) {
										var obj = dataObj[i]; //获取一个对象
										var orgId = obj.orgId;
										var orgName = obj.orgName;
									
										
										tree.insertNewChild(id, orgId, orgName);

									}
								}//else 有子机构		elseend
								
							}
						});
			} else {//  tree	 不是全新的状态，只new table，不new tree
				$
						.ajax({
							type : "POST",
							/*  url: "http://localhost:8080/myspring/organizationManager/queryOrgLevel.do", */
							url : "${pageContext.request.contextPath}/organizationManager/queryOrgLevel.do",
							data : "id=" + id,
							success : function(msg) {
								//    alert( "Data Saved: " + msg );
								var dataObj = eval('(' + msg + ')');
								
								dataAll = dataObj;
								pageFunction();//加载page的所需数据 
								pageNow = 0;
								pageNext();
								
							}
						});
			}
			
		}

		//清除Table
		function clearRowsSMS() {
			//var tableLen = document.getElementById('contactTable').rows.length;
			
			 var opanel = document.getElementById("contactTable");
		
			 var pchildren = opanel.childNodes;
			 //清空表中的行和列
			 
				 for(var a=0; a<pchildren.length; a++){
					  opanel.removeChild(pchildren[a]);
				 }
			 
			 
			/* if (tableLen > 1) {
				  for ( var i = 0; i < tableLen - 1; i++) {
					document.getElementById('contactTable').deleteRow(
							tableLen-i-1);
				}  
			     for(var int = 2;int < tableLen;int++) {
				    table.deleteRow(2);
			    } 
				//document.getElementById('contactTable').outerHTML = "";
				
			   
				} */
		}

		//alert("dbclick "+id+" Item " + tree.getItemText(id) + " was selected");
		//tree.insertNewChild('root', "0", "全国");  //id全  tree.getItemText(id)	全国 （也就是要显示的标签内容）
		//tree.enableTreeLines(true);

		/* 这里是数据的操作 */

		//删除
		function deleteOrg(orgId) {
			//var table = document.getElementById("contactTable");

			if (orgId != null && orgId != undefined && orgId != "") {
				//alert("确定删除？");

				$.ajax({
					type : "POST",
					async : false,
					/*  url: "http://localhost:8080/myspring/organizationManager/queryOrgLevel.do", */
					url : "${pageContext.request.contextPath}/organizationManager/queryOrgLevel.do",
					data : "id=" + orgId,
					success : function(msg) {
						var dataObj = eval('(' + msg + ')');
						if (dataObj == "") {
						//子机构为空start
							//alert("ajax");查询，发现下一个等级，没有数据。查询机构下是否有账号，没有账号，提示是否确认删除，否则"该机构下有账号，禁止删除"
							
							//验证机构下是否有账号start
							$.ajax({
									//url:"${pageContext.request.contextPath}/organizationManager/queryAllAcountByOrgId.do",
									url:"${pageContext.request.contextPath}/organizationManager/queryAllAcountByOrgId.do",
									data:"id="+orgId,
									type:"POST",
									async : false,
									 success:function(msg){
										var accountNum = eval('('+msg+')');
										//alert(accountNum);
										if(accountNum>0){
											//$("#msg").html("该机构下有账号，禁止删除");
											//showMsg();
											adtecUtil.showMsg("该机构下有账号，禁止删除");
										}else{//该机构下没有账号，提示确认
											//var a = window.confirm('确定删除'); //maojd update date 2014-1-7 10:38:更改confirm的使用。放到。把if(a)发送ajax放到回调函数里面
											window.parent.adtec_confirm.confirm("确认删除",function (a){
												if (a == 1) {//a==1表示点击了window.confirm的确定
													$.ajax({
														type : "POST",
														url : "${pageContext.request.contextPath}/organizationManager/deleteOrg.do",
														data : "orgId=" + orgId,
														success : function(msg) {
															//考虑怎么删除节点，不刷新页面？？
															//$("#msg").html("删除成功");
															//showMsg();
															if(msg == "deleteOrgSuc"){
																adtecUtil.showMsg("删除机构成功");
																setTimeout("formReload()",1000); //刷新页面
																//暂时先用刷新。改成静态删除
																// window.location.reload(); 
															}else{
																adtecUtil.showMsg("删除机构失败");
															}
															
															
															
														}
													});
												} else {
													//刚才点击了 window.confirm的取消
												}
											});
											
											
										}
									 }
								});
								//验证机构下是否有账号end
						} else {
						//子机构为空end
							//alert("该机构下有子机构，禁止删除");
							//$("#msg").html("该机构下有子机构，禁止删除");
							//showMsg();
							adtecUtil.showMsg("该机构下有子机构，禁止删除");
						}
					}
				});

			}
			//alert(orgId+"删除"); 

		}

		//修改页面
		/*
		function updateOrgPage(orgId) {
			// alert(orgId+"修改");  
			
			window.location.href="updateOrgPage.do ?orgId=\'"+orgId+"\'}"; 
			/* $.ajax({
				type : "POST",
				url : "updateOrgPage.do",
				data : "orgId=" + orgId,
				success : function(msg) {
					$("#updateOrgPage").html(msg);
					//$().html +="<span><a href='#' onclick='updateOrgPage(\""+obj.orgId+"\")'>返回</a></span>"
				}
			});
		} */

		//添加	把下一个页面拿到当前页面。已经废弃不用
		/* function addFunction() {
			var treeid = tree.getSelectedItemId();
			if (treeid == "" || treeid == undefined) {
				//alert("您没有选中要添加的等级");  默认选中全国
				treeid = 0;
			}
			$.ajax({
				type : "POST",
				//url : "${pageContext.request.contextPath}/organizationManager/addOrgPage.do",
				url:"${pageContext.request.contextPath}/organizationManager/addOrg.do",
				data : "orgId=" + treeid,
				success : function(msg) {
					//$("#addDiv").html(msg);	
				}
			});

		} */
		
		
		/* 添加验证*/ 
		function nameMsg(){
		var span = document.getElementById("span1");
		var name = document.getElementById("orgName").value;
		name = name.replace(/^\s+|\s+$/g, '');
		span.innerHTML = "";
			if(name == ""){
				/* var msg = '名称不能为空';
				span.innerHTML = msg;
				span.style.color='#ff0000';
			    span.style.fontSize='12px'; */
			    adtecUtil.showMsg("名称不能为空");
			    //$("#orgName").focus();
			    return false;
			} else if(!isEnChNum(name)){//通过验证true 不通过false
				adtecUtil.showMsg("机构名称不能包含特殊字符");
				return false;
			}
			if(name.length > 15){
				/* var msg = '长度限制为15';
				span.innerHTML = msg;
				span.style.color='#ff0000';
			    span.style.fontSize='12px'; */
			    adtecUtil.showMsg("长度限制为15");
			    //$("#name").focus();
			    return false;
			}
			return true;
		} 
		//添加ajax带着数据
		function addOrg() {
			//alert(123);
			if(nameMsg()){
				var treeid = tree.getSelectedItemId();
				var orgName = $("#orgName").val().replace(/^\s+|\s+$/g, '');
				//alert(treeid+"---"+orgName );
				if (treeid == "" || treeid == undefined || treeid==null) {
					//alert("您没有选中要添加的等级");  默认选中全国
					treeid = "0";
				}
				//alert(treeid);
				//发送ajax请求，判断选中的是不是 第7和等级。如果不是，才去添加，否则，提示用户。已经添加到最大等级
				//判断是否第7级 	start
				$.ajax({//发送Ajax请求，得到一个org
					type:"POST",
					url:"${pageContext.request.contextPath}/organizationManager/queryOrgById.do",
					data:"id="+treeid,
					success:function(msg){
						
						var dataObj = eval('(' + msg + ')');
						var obj = dataObj[0];
						//alert(msg);
						if (obj.level7 == null || obj.level7.replace(/^\s+|\s+$/g, '') =="" || obj.level7==undefined) {
							//level7 null，不是第七个层级，可以添加
							$.ajax({
								type : "POST",
								url : "${pageContext.request.contextPath}/organizationManager/addOrg.do",
								data:{"orgId":treeid,"orgName":orgName},
								success : function(msg) {
									//$("#addDiv").html(msg);
									
									//$("#msg").html("添加成功");
									//showMsg();
									if(msg == "addOrgSuc"){
										adtecUtil.showMsg("添加机构成功");
										//window.location.reload(); //暂时用刷新，改成静态删除
										//setTimeout("reload()",1000);这种刷新方式清除不了 input sucMsg中的值。导致重复提示（添加成功，修改成功等）
										setTimeout("formReload()",1000);
									}else{
										adtecUtil.showMsg("添加机构失败");
									}
									
								}
							});
							
						}else{
							//alert("最多支持7层，已经添加到最大层级");
							//$("#msg").html("最多支持7层，已经添加到最大层级");
							//showMsg();
							adtecUtil.showMsg("最多支持7层，已经添加到最大层级");
						}

					}
				});//判断是否第7级 	start
				
				return true;	
			}else{
				return false;
			}
			/* var textuser = document.getElementById("orgName").value;
			textuser = textuser.replace(/^\s+|\s+$/g, '');
			if(textuser == ""){
				//alert("名称不能为空");//		把提示的弹窗去掉。用户体验好些。return false
				return false;
			}else if(textuser.length > 15){
				//alert("长度限制为15");
			    return false;
			}else{
				
			}*/
		}
		
	   /*
		*orgUploadPage机构导入跳转
		*/
		function orgUploadPage(){
			window.location.href="${pageContext.request.contextPath}/organizationManager/orgUploadPage.do";
			//tipsWindown("机构导入","url:get?${pageContext.request.contextPath}/organizationManager/orgUploadPage.do","600","280","true","","true","text");
	   }
		
	   /*
	    *页面加载完毕。执行tree.clickFunc()方法。查询全国数据
	    */
	   
		function onload(){
			//maojd update date:11:29 2013/12/30	页面一旦刷新，外部adtecUtil.js中定时 的隐藏 操作成功提示信息的方法失效。所以onload方法中，执行隐藏方法
			$(window.parent.document).find("#msgBoxDIV").fadeOut("slow");
			
			//var sucMsg = document.getElementById("sucMsg").value;
			var sucMsg = $("#sucMsg").val();
			//alert(sucMsg);
			//alert(sucMsg);
			
			if(sucMsg == "updateOrgSuc"){
				//$("#msg").html("修改成功");
				//showMsg();
				adtecUtil.showMsg("机构修改成功");
			}else if(sucMsg == "updateOrgError"){
				adtecUtil.showMsg("机构修改失败");
			}else if(sucMsg == "orgLeadingInSuc"){
				//$("#msg").html("机构导入成功");
				//showMsg();
				adtecUtil.showMsg("机构导入成功");
			}else if(sucMsg == "orgLeadingInError"){
				adtecUtil.showMsg("机构导入失败");
			}
			$.ajax({
				type : "POST",
				// url : "${pageContext.request.contextPath}/organizationManager/queryOrgRoot.do",
				url : "${pageContext.request.contextPath}/organizationManager/queryOrgRoot.do",
				success : function(msg) {
					var dataObj = eval('(' + msg + ')');
					var orgId = dataObj[0].orgId;
					//var orgName = dataObj[0].orgName;
					//alert(orgId+"---"+orgName);
					//alert("dbclick "+id+" Item " + tree.getItemText(id) + " was selected");
					tree.insertNewChild('root',orgId,"全国");  //id全  tree.getItemText(id)	全国 （也就是要显示的标签内容）
					tree.enableTreeLines(true);
					clickFunc(orgId);
				}
			});
		}
	   /*
		*  初始化数据
		*  pageNow =1	当前页
		*  pageCount	总页
		*/
		function pageFunction(){
			//alert(dataAll+"count is "+count);
			count = dataAll.length;
			pageCount = Math.ceil(count/pageSize);
			if(pageCount == 0){
				pageCount++;
			}
			$("#pageNow").html("1");
			$("#pageCount").html(pageCount);
			if(pageNow<=1){
				$("#uppage").html("");
				$("#nextpage").html("下一页");
			}
			if(pageCount==1 || pageCount ==0){
				$("#uppage").html("");			//总页为1或者0，就隐藏上一页下一页标签
				$("#nextpage").html("");
			}
		}
		
		/*
		* javaScript	上一页 			pageUp
		* pageNow		当前页
		* start 		当前页的第一条 first  organization（机构）
		* end			最后一条		  last	organization
		*/
		function pageUp(){
			if (pageNow <= 2) { //如果pageNow小于等于1，set pageNow = 1;上一页的<a>标签，隐藏。
			    pageNow = 1;
			    $("#uppage").html("");
			    $("#nextpage").html("下一页");
			} else {
			    pageNow = pageNow - 1;
			    $("#uppage").html("上一页");
			    $("#nextpage").html("下一页");
			}
			if (pageCount == 1 || pageCount == 0) {
			    $("#uppage").html(""); //总页为1或者0，就隐藏上一页下一页标签
			    $("#nextpage").html("");
			}
			$("#pageNow").html(pageNow);
			//pageNow 从3变成2的话，所需数据11 到20
			var start = (pageNow - 1) * pageSize; //dataAll[20] 即 dataAll[start]	第21条数据
			var end = pageNow * pageSize; // dataAll[29] for循环时候，i<end	第30条数据
			if (end >= count){end=count;}
			
			var table = document.getElementById("contactTable");
			clearRowsSMS();
			for (var i = start; i < end; i++) {
			    var obj = dataAll[i]; //获取一个对象
			    var orgId = obj.orgId;
			    var orgName = obj.orgName;

			    /*  alert("parent"+id);
							 alert("selef"+orgId); 
							 */
			    var tr = table.insertRow(table.rows.length);
			    var td1 = tr.insertCell(0);
			    var td2 = tr.insertCell(1);
			    var td3 = tr.insertCell(2);
			    /* 	var td4 = tr.insertCell(3); */

			    //   td1.innerHTML = obj.orgId;拼装一下需要显示的 机构号码
			    var levelText = "";
			    if (obj.level1 != null) {
			        //alert(obj.level1);
			        levelText = levelText + obj.level1;
			    }
			    if (obj.level2 != null && obj.level2 != undefined && obj.level2 != "") {
			        levelText = levelText + "|" + obj.level2;
			    }
			    if (obj.level3 != null && obj.level3 != undefined && obj.level3 != "") {
			        levelText = levelText + "|" + obj.level3;
			    }
			    if (obj.level4 != null && obj.level4 != undefined && obj.level4 != "") {
			        levelText = levelText + "|" + obj.level4;
			    }
			    if (obj.level5 != null && obj.level5 != undefined && obj.level5 != "") {
			        levelText = levelText + "|" + obj.level5;
			    }
			    if (obj.level6 != null && obj.level6 != undefined && obj.level6 != "") {
			        levelText = levelText + "|" + obj.level6;
			    }
			    if (obj.level7 != null && obj.level7 != undefined && obj.level7 != "") {
			        levelText = levelText + "|" + obj.level7;
			    }

			    //td1.innerHTML = obj.orgId;
			    //$(td1).attr("style","width:10px;margin-right:10px;");
			    /* td1.innerHTML +="<input type='checkbox' name='chkbox' value=\'"+obj.orgId+"\' />"; */

			    td1.innerHTML = levelText;
			    td2.innerHTML = orgName;
			    //td3.innerHTML = "删除";
			    //  td3.innerHTML = "NIHAO";
			  
				if(delPrivlg || wholePrivlg){
				    td3.innerHTML += "<span><a href='#' onclick='deleteOrg(\"" + orgId + "\")'>&nbsp;&nbsp;&nbsp;&nbsp;删除</a></span>";
				}
				if(updatePrivlg || wholePrivlg){
				    //td3.innerHTML += "<span><a href='#' onclick='updateOrgPage(\""+ obj.orgId + "\")'>&nbsp;&nbsp;&nbsp;&nbsp;修改</a></span>";
				    td3.innerHTML += "<span><a href='${pageContext.request.contextPath}/organizationManager/updateOrgPage.do?orgId=" + orgId + "' >&nbsp;&nbsp;&nbsp;&nbsp;修改</a></span>";
				}
			    //tree.insertNewNext(obj.parent,obj.id,obj.item);
			}
			
			//固定:假如当前页的数量小于 pageSize，补充空格，以固定上一页和下一页标签的位置不动。
			//上一页的情况，一般是数据的条数是 正好等于pageSize,为了避免低概率的发生。还是加上去
			adtecUtil.padTable("contactTable",pageSize);
			/* var decrease = end - start;
			var add = pageSize - decrease;
			for(var i=0;i<add;i++){
				var tr = table.insertRow(table.rows.length);
				var td1 = tr.insertCell(0);
				var td2 = tr.insertCell(1);
				var td3 = tr.insertCell(2);
			} */
		}
		
		/*
		 *  pageNext	下一页
		 */
		
		function pageNext(){
			//alert("start"+pageCount+"end");
			if(pageNow<1){
				pageNow = 1;	//set pageNow设为1，uppage上一页标签隐藏
				$("#uppage").html("");
				$("#nextpage").html("下一页");
			}else{//pageNow 为 >=1的任意数字
				if(pageNow<pageCount){
					pageNow = pageNow +1;//小于总页，就+1，标签不做任何操作
					if(pageNow == pageCount){//+1后，如果等于 pageCount了，隐藏下一页标签
						$("#nextpage").html("");
						$("#uppage").html("上一页");
					}else{					//+1后，依然小于总页。
						$("#uppage").html("上一页");
						$("#nextpage").html("下一页");
						
					}
				}else {//等于总页，或者大于总页
					pageNow = pageCount;
					$("#nextpage").html("");
					$("#uppage").html("上一页");
				}
			}
			
			if(pageCount==1 || pageCount ==0){
				$("#uppage").html("");			//总页为1或者0，就隐藏上一页下一页标签
				$("#nextpage").html("");
			}
			$("#pageNow").html(pageNow);
			
			//上面执行完毕，pageNow从1变成了2，所需数据从11 到20个
			var start = (pageNow-1)*pageSize;	//其实start是 +1，但是dataAll是json数据，下标从0算。dataAll[start+1-1].orgName
			var end = pageNow*pageSize;
			if(end>=count){
				end = count;
			}
			//alert("start is "+start +" end is "+end + dataAll[start].orgName+" 最后一个orgName是：" +dataAll[end-1].orgName);
			var table = document.getElementById("contactTable");
			clearRowsSMS();
			
			if(count>0){
				for ( var i = start; i < end; i++) {
					var obj = dataAll[i]; //获取一个对象
					var orgId = obj.orgId;
					var orgName = obj.orgName;

					/*  alert("parent"+id);
					 alert("selef"+orgId); 
					 */
					var tr = table.insertRow(table.rows.length);
					var td1 = tr.insertCell(0);
					var td2 = tr.insertCell(1);
					var td3 = tr.insertCell(2);
				/* 	var td4 = tr.insertCell(3); */

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

					//td1.innerHTML = obj.orgId;
					//$(td1).attr("style","width:10px;margin-right:10px;");
					/* td1.innerHTML +="<input type='checkbox' name='chkbox' value=\'"+obj.orgId+"\' />"; */
					
					td1.innerHTML = levelText;
					td2.innerHTML = orgName;
					//td3.innerHTML = "删除";
					//  td3.innerHTML = "NIHAO";
					
					if(delPrivlg || wholePrivlg){
						td3.innerHTML += "<span><a href='#' onclick='deleteOrg(\""
								+ orgId
								+ "\")'>&nbsp;&nbsp;&nbsp;&nbsp;删除</a></span>";
					}
					if(updatePrivlg || wholePrivlg){
						//td3.innerHTML += "<span><a href='#' onclick='updateOrgPage(\""+ obj.orgId + "\")'>&nbsp;&nbsp;&nbsp;&nbsp;修改</a></span>";
						td3.innerHTML += "<span><a href='${pageContext.request.contextPath}/organizationManager/updateOrgPage.do?orgId="+orgId+"' >&nbsp;&nbsp;&nbsp;&nbsp;修改</a></span>";
						//tree.insertNewNext(obj.parent,obj.id,obj.item);
					}
					
				}
				
				//固定:假如当前页的数量小于 pageSize，补充空格，以固定上一页和下一页标签的位置不动。
				/* var decrease = end - start;
				var add = pageSize - decrease;
				for(var i=0;i<add;i++){
					var tr = table.insertRow(table.rows.length);
					var td1 = tr.insertCell(0);
					var td2 = tr.insertCell(1);
					var td3 = tr.insertCell(2);
					//td1.innerHTML+="";
				} */
				adtecUtil.padTable("contactTable",pageSize);
				
				
			}
			
			
			
		}
		
		/*
		 *  page_jump()		分页跳转
		 */
		
		function page_jump(){
			//var page_jump = $("#page_jump");
			//pageVal = page_jump.val().trim();
			var page_jump = document.getElementById("page_jump");
			pageVal = page_jump.value.replace(/^\s+|\s+$/g, '');
			if(checkIsInteger(pageVal)){
				//如果是数字，判断是否超过最大页
				
				//alert(page_jump);
				if(pageVal>pageCount){
					//alert("输入值是"+pageVal+"超过最大页");
					//alert("您输入的值超过最大页");
					
					//$("#msg").html("您输入的值超过最大页");
					//showMsg();
					adtecUtil.showMsg("您输入的值超过最大页");
					
					$(page_jump).attr("value","");			//清除刚才输入的数据，并获取焦点
					$(page_jump).focus();
				}else if(pageVal == 0){
					//alert("请输入正整数");
					//$("#msg").html("请输入正整数");
					//showMsg();
					adtecUtil.showMsg("请输入正整数");
					
					$(page_jump).attr("value","");
					$(page_jump).focus();
				}else{
					//alert("输入值是"+pageVal);
					pageNow = pageVal - 1;
					pageNext();
				}
			}else{
				//alert("输入值是"+pageVal+"只能输入数字，且为正整数");
				//alert("只能输入数字，且为正整数");
				//$("#msg").html("只能输入数字，且为正整数");
				//showMsg();
				adtecUtil.showMsg("只能输入数字，且为正整数");
				
				//page_jump.attr("value","");
				page_jump.value = "";
				page_jump.focus();
			}
			
			
		}
		
		
		//机构导入移到另一个orgUpload.jsp页面，此方法暂时抛弃
		/* function fileCheck() {
			var fileName = document.getElementById("file");
			var filetext = fileName.value;

			var span = document.getElementById("spanFile");
			span.innerHTML = "";

			//alert(filetext);
			if (filetext == '') {

				msg = '请上传要导入的文件';
				span.innerHTML = msg;
				span.style.color = '#ff0000';
				span.style.fontSize = '12px';

				return false; //提示没有选择要导入的文件

			} else {
				var result = /\.[^\.]+/.exec(filetext); //获取文件后缀名
				if (result.toString().toLowerCase() == '.csv') { //toString不可省去
					return true;
				} else {
					//alert("请选择CSV格式文件导入");

					msg = '请选择CSV格式文件导入';
					span.innerHTML = msg;
					span.style.color = '#ff0000';
					span.style.fontSize = '12px';

					return false;
				}
			} 
		}
		*/

		function orgUpload() {
			//机构导入，弹出 导入窗口
			if(window.ActiveXObject){ //IE   
				//alert("ie");
			   window.showModalDialog("${pageContext.request.contextPath}/organizationManager/orgUploadPage.do", window, "dialogWidth:630px;status:no;dialogHeight:440px");  
			  
			}else{  //非IE   
			    //alert("qita");
			   var w = window.open("${pageContext.request.contextPath}/organizationManager/orgUploadPage.do",'newwindow','height=440,width=630,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');  
			   w.focus();
			} 
			
			
			//window.showModalDialog("orgUploadPage.do", "dialogWidth:800px;dialogHeight=400px;status:no");
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
	    
	 	//验证，只能是字母，数字，下划线，中文
		function isEnChNum(strName){
		    var str = $.trim(strName);
			var regu = /^[a-zA-Z0-9_ \u4e00-\u9fa5]+$/; //字母数字下划线 中文 空格
			if(regu.test(str)){
				return true;
			}
			return false;
		    
		}
	    
	   /**
	    *	操作成功后的提示的信息
	    *	显示在浏览器的正中间，两秒后消失		
	    */
	    /*
	     function showMsg(msgHtml){
	    	$(window.parent.document).find("#msg").html(msgHtml);
	    	$(window.parent.document).find("#msgBoxDIV").slideDown("fast");
	    	setTimeout("hidenMsg()",1000);
	    	
			//获取浏览器的宽度
			if (window.innerWidth)
			winWidth = window.innerWidth;
			else if ((document.body) && (document.body.clientWidth))
			winWidth = document.body.clientWidth;
			//获取浏览器的高度（其实高度不用，删掉即可）
			if (window.innerHeight)
			winHeight = window.innerHeight;
			else if ((document.body) && (document.body.clientHeight))
			winHeight = document.body.clientHeight;
			
			//获取msg标签的长度
			var msgWidth = $("#msg").innerWidth();
			
			//alert(msgWidth);
			
			//alert(winWidth+"---高--"+winHeight);
			//alert(winWidth/2);
			var leftPx = (winWidth-msgWidth)/2;
			var topPx = 0;
			$("#msg").css({left:leftPx + "px", top:topPx+"px"}).slideDown("fast");
			setTimeout("hidenMsg()",1000); 
		}
	  //隐藏msg
	    function hidenMsg(){
	    	//$("#msg").fadeOut("slow");
	    	$(window.parent.document).find("#msgBoxDIV").fadeOut("slow");
	    }
	    */
		function reload(){
			window.location.reload();
		}
		
		
		function formReload(){
			$(window.parent.document).find("#msgBoxDIV").fadeOut("slow");	//隐藏起来提示成功的信息
			with(document.forms[0]){
			  	action="${pageContext.request.contextPath}/organizationManager/orgList.do";
				submit();
			} 
			//alert(23);
			//$('formNull').submit()
		}
	    
		
		/* function selectAll() {
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
		} */
		
	    /*
		 *批量删除点击 firstCheckBox，选中所有	
		 */
		
		/* function chkfirstbox() {
			//第一个 checkbox执行的方法。
			var box = document.getElementById("chkfirstbox");
			if (box.checked == true) {
				selectAll();
			}
			if (box.checked == false) {
				unselectAll();
			}
		} */

		
		/* function deleteSelected(){
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
			//alert(chkstr);
			chkstr = chkstr.substring(0, chkstr.length - 1);

			//发送ajax请求，到control，把chkstr发过去

				 $.ajax({
					   type: "POST",
					  
					   url: "${pageContext.request.contextPath}/organizationManager/deleteXXX.do", 					  
					   data: "ids="+chkstr,
					   success: function(msg){
						   window.location.reload();
					   }
				}); 

			//control里面处理
			
			String ids = request.getParameter("ids");
			for (int i = 0; i<ids.split(",").length; i++){
				user.setId(Integer.parseInt(ids.split(",")[i]));
				userService.delete(user);
			
			}
			 

			alert(chkstr);
			//alert("chkstr is:"+chkstr+"   长度是:" +chkstr.toString().length);
			window.location.reload();

		} */
		
	</script>
	
	


<script type="text/javascript">
	//机构导入移到另一个orgUpload.jsp页面，此方法暂时抛弃
	/* function fileCheck() {
		var fileName = document.getElementById("file");
		var filetext = fileName.value;

		var span = document.getElementById("spanFile");
		span.innerHTML = "";

		//alert(filetext);
		if (filetext == '') {

			msg = '请上传要导入的文件';
			span.innerHTML = msg;
			span.style.color = '#ff0000';
			span.style.fontSize = '12px';

			return false; //提示没有选择要导入的文件

		} else {
			var result = /\.[^\.]+/.exec(filetext); //获取文件后缀名
			if (result.toString().toLowerCase() == '.csv') { //toString不可省去
				return true;
			} else {
				//alert("请选择CSV格式文件导入");

				msg = '请选择CSV格式文件导入';
				span.innerHTML = msg;
				span.style.color = '#ff0000';
				span.style.fontSize = '12px';

				return false;
			}
		} 
	}
	 */

	/* function orgUpload() {
		//机构导入，弹出 导入窗口
		if (window.ActiveXObject) { //IE   
			//alert("ie");
			window
					.showModalDialog(
							"${pageContext.request.contextPath}/organizationManager/orgUploadPage.do",
							window,
							"dialogWidth:630px;status:no;dialogHeight:440px");

		} else { //非IE   
			//alert("qita");
			var w = window
					.open(
							"${pageContext.request.contextPath}/organizationManager/orgUploadPage.do",
							'newwindow',
							'height=440,width=630,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
			w.focus();
		}

		//window.showModalDialog("orgUploadPage.do", "dialogWidth:800px;dialogHeight=400px;status:no");
	} */

	
	
	/*
	 *批量删除所需。暂时不实现
	 */
/* 
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

	function chkfirstbox() {
		//第一个 checkbox执行的方法。
		var box = document.getElementById("chkfirstbox");
		if (box.checked == true) {
			selectAll();
		}
		if (box.checked == false) {
			unselectAll();
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
		//alert(chkstr);
		chkstr = chkstr.substring(0, chkstr.length - 1);

		//发送ajax请求，到control，把chkstr发过去
 */
		/* 	 $.ajax({
				   type: "POST",
				  
				   url: "${pageContext.request.contextPath}/organizationManager/deleteXXX.do", 					  
				   data: "ids="+chkstr,
				   success: function(msg){
					   window.location.reload();
				   }
			}); */

		//control里面处理
		/*
		String ids = request.getParameter("ids");
		for (int i = 0; i<ids.split(",").length; i++){
			user.setId(Integer.parseInt(ids.split(",")[i]));
			userService.delete(user);
		
		}
		 

		/* alert(chkstr);
		//alert("chkstr is:"+chkstr+"   长度是:" +chkstr.toString().length);
		window.location.reload();

	} */

	
	
</script>

</c:if>