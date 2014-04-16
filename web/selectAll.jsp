<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util/jquery-1.4.4.js"></script>



<link href="${pageContext.request.contextPath}/ui/css/common.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/skin.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/add.css"
	rel="stylesheet" type="text/css">




<script type="text/javascript">
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

	function deleteUsers() {
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

		/* 	 $.ajax({
				   type: "POST",
				  
				   url: "deleteXXX.do", 					  
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
		 */

		alert(chkstr);
		//alert("chkstr is:"+chkstr+"   长度是:" +chkstr.toString().length);
		window.location.reload();

	}
</script>


</head>
<body>

	<table id="showTable">
		<tr>
			<th><input type="checkbox" id="chkfirstbox"
				onclick="chkfirstbox()" /></th>
			<th>姓名</th>
			<th>年龄</th>
		</tr>
		<tr>
			<td style="width:10px;margin-right:2px;" ><input style="width:10px;" type="checkbox" name="chkbox" value="1" /></td>
			<td>张三</td>
			<td>17</td>
		</tr>
		<tr>
			<td><input type="checkbox" name="chkbox" value="2" /></td>
			<td>李四</td>
			<td>22</td>
		</tr>

		<tr>
			<td><input type="checkbox" name="chkbox" value="3" /></td>
			<td>张三</td>
			<td>25</td>
		</tr>


	</table>

	<tabel>
	<tr>
		<td>选择:</td>
		<td><a style="text-decoration: none;" href="#"
			onclick="selectAll()">全部 </a> -</td>
		<td><a style="text-decoration: none;" href="#"
			onclick="unselectAll()">无 </a></td>
	</tr>
	<br>
	<tr>
		<td><input type="button" value="新增"></td>
		<td><input type="button" value="删除" onclick="deleteUsers()">
		</td>


	</tr>

	</tabel>






	<div class="list_body">

		<DIV class="attbg mysidebar">
			<DIV class="bd b_size bold title_left">
				<DIV class="right f_size normal" style="MARGIN: 2px 1px 0px 0px">
					<A onclick="return addDep();" href="javascript:void(0);">新增部门</A>
				</DIV>
				部门
			</DIV>

			<div id="tab-2"
				style="background-color: white; margin: 0; padding: 0;">
				<div class="lm_sbar">
					<input class="lm_sopen" type="button" />
					<div>
						<input id="user_search" class="txt" type="text" autocomplete="off"
							value="" style="height: 20px;" />
					</div>
				</div>
				<div id="loading-2" style="text-align: center; display: none;">
					<img src="../../data/ui/images/loading.gif" border="0" />
				</div>
				<ul id="tree_user" class="ztree"
					style="overflow-y: auto; overflow-x: hidden; width: 190px;"></ul>
			</div>


		<!-- 	<UL class="grouplist addrtitle">
				<LI class="toolbg bold"><A
					href="http://auc.adtec.com.cn/app/user/member.php?page=1&amp;group_id=0">所有联系人</A>&nbsp;
				</LI>
				<LI><A title=FSG金融软件事业部
					href="http://auc.adtec.com.cn/app/user/member.php?page=1&amp;group_id=24">FSG金融软件事业部</A>&nbsp;
				</LI>
				<LI><A title=DIG数据整合事业部
					href="http://auc.adtec.com.cn/app/user/member.php?page=1&amp;group_id=23">DIG数据整合事业部</A>&nbsp;
				</LI>
			</UL> -->


		</DIV>

		<div class="myleftbar">
			<div style="line-height: 24px; zoom: 1;" class="toolbg toolbgline">
				<div class="right">
					2/26 页&nbsp; <a
						href='/app/user/member.php?group_id=0&search=&page=1'
						id="prevpage">上一页</a>&nbsp; <a id="nextpage"
						href='/app/user/member.php?group_id=0&search=&page=3'>下一页</a>&nbsp;<a
						title="跳转到指定一页" name="page_jump" href="javascript:void(0)">跳转</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>