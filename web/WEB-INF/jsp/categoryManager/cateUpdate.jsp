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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util/adtec_util.js"></script>
<link href="${pageContext.request.contextPath}/ui/css/common.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/skin.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/ui/css/add.css"
	rel="stylesheet" type="text/css">

<style type="text/css">
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
    <div style="display:block;width:100%;min-width:1122px;height:300px;">
        <form action="<%=request.getContextPath()%>/category/insertAction.do"
        method="post" id="myform">
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
            <table style="padding-bottom: 10px" width="100%" cellspacing="0" cellpadding="4"
            border="0" class="settingtable">
                <tbody>
                    <tr>
                        <td colspan="100%">
                            <div style="font-size: 14px; padding: 8px 0 4px 2px; margin: 4px 15px"
                            class="addr_line">
                                终端修改
                            </div>
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            <div style="width: 120px; white-space: nowrap; text-align: right;">
                                终端ID：
                            </div>
                        </td>
                        <td width="96%" align="left">
                            <input style="color: gray" type="text" name="cateName" value="${rows.cateName }"
                            size="28" class="txt noime cateName" onchange="cate()" maxlength="30" required
                            readonly="readonly">
                            &nbsp;
                            <font color="#FF0000">
                                *终端ID不许修改
                            </font>
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            终端分类：
                        </td>
                        <td align="left">
                            <input type="text" id="zh" name="cateDesc" value="${rows.cateDesc }" maxlength="64"
                            size="28" class="txt noime" required autocomplete="off">
                            &nbsp;
                            <font color="red">
                                *
                            </font>
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            导入类：
                        </td>
                        <td align="left">
                            <input type="text" id="zh" name="importClass" value="${rows.importClass }"
                            maxlength="100" size="28" class="txt noime" required autocomplete="off">
                            &nbsp;
                            <font color="red">
                                *
                            </font>
                        </td>
                    </tr>
                    <tr class="normal black">
                        <td align="right">
                            认证类：
                        </td>
                        <td align="left">
                            <input type="text" id="zh" name="authClass" value="${rows.authClass }"
                            maxlength="100" size="28" class="txt noime" required autocomplete="off">
                            &nbsp;
                            <font color="red">
                                *
                            </font>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <tr>
                        <td colspan="100%">
                            <div style="font-size: 14px; margin: 4px 15px">
                                扩展属性定义：
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table cellspacing="0" cellpadding="0" class="O2" style="table-layout: fixed; width: 100%; *width: auto;"
            id="targetTable">
                <tbody>
                    <tr style="height: 20px;">
                        <td width="25%" class="o_title2">
                            序号
                        </td>
                        <td width="25%" class="o_title2">
                            名称
                        </td>
                        <td width="25%" class="o_title2">
                            描述
                        </td>
                        <td width="25%" class="o_title2">
                            操作
                        </td>
                    </tr>
                    <tr id="table" style="display: none">
                        <td width="25%" class="o_title2">
                            <input type="hidden" name="prop_index1" maxlength="5" readonly="readonly">
                            <label name="_index">
                            </label>
                        </td>
                        <td width="25%" class="o_title2">
                            <input type="hidden" name="propName" maxlength="100" readonly="readonly">
                            <label>
                            </label>
                        </td>
                        <td width="25%" class="o_title2">
                            <input type="hidden" name="propDesc" maxlength="100" readonly="readonly">
                            <label>
                            </label>
                        </td>
                        <td width="25%" class="o_title2">
                            <a onclick="del(this)" style="text-decoration: underline">
                                删除
                            </a>
                        </td>
                    </tr>
                    <c:if test="${rows1!=null }">
                    
                        <c:forEach items="${rows1 }" var="dd">
                        	<c:if test="not empty ${dd.propName }">
                        	
                            <tr>
                            <%-- <input type="text" value="${ dd.prop_index}"> --%>
                                <td width="25%" class="o_title2">
                                    <input type="hidden" name="prop_index1" value="${dd.prop_index }" maxlength="5"
                                    readonly="readonly">
                                    <label name="_index">
                                        ${dd.prop_index }
                                    </label>
                                </td>
                                <td width="25%" class="o_title2">
                                <input type="hidden" name="propName" value="${dd.propName }" maxlength="100"
                                    readonly="readonly">
                                    <label>
                                    ${dd.propName }
                                    </label>
                                </td>
                                <td width="25%" class="o_title2">
                                    <input type="hidden" name="propDesc" value="${dd.propDesc }" maxlength="100"
                                    readonly="readonly">
                                    <label>
                                    ${dd.propDesc }
                                    </label>
                                </td>
                                <td width="25%" class="o_title2">
                                    <a onclick="del(this)" style="text-decoration: underline">
                                        删除
                                    </a>
                                </td>
                            </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
            <table cellspacing="0" cellpadding="0" class="O2" style="table-layout: fixed; width: 100%; *width: auto;">
                <!-- <tbody> -->
                <tr>
                    <td width="25%" class="o_title2">
                        序号：
                        <input type="text" name="t_prop_index" maxlength="5" readonly="readonly">
                    </td>
                    <td width="25%" class="o_title2">
                        名称：
                        <input type="text" name="t_propName" id="t_propName" maxlength="100" onchange="onT_propName()">
                        <font class="tishiProp" style="color: red; font-size: 12px">
                        </font>
                    </td>
                    <td width="25%" class="o_title2">
                        描述：
                        <input type="text" name="t_propDesc" maxlength="100">
                    </td>
                    <td width="25%" class="o_title2">
                        <input type="button" onclick="add()" style="font-size: 12px" value="添加属性" id="insertProp"
                        class="update button">
                    </td>
                </tr>
                <!-- </tbody> -->
            </table>
            <table width="100%" cellspacing="0" cellpadding="2" border="0" class="toolbg">
                <tbody>
                    <div style="" class="list_btline">
                        <div class="f_size selbar_bt barspace2" style="height: 24px; padding-top: 4px; padding-bottom: 4px; background-color: #c1d9f3; height: 24px; padding-top: 3px"
                        align="center">
                            <input type="submit" title="修改" name="next" value="修改" onclick="return onUpdate()"
                            class="button">
                            <input type="button" title="返回" name="next" value="返回" onclick="return onReturn()"
                            class="button">
                        </div>
                    </div>
                </tbody>
            </table>
            <input type="hidden" name="ta_id" value="${rows.ta_id }">
        </form>
    </div>
    <!-- span做alert使用，用户操作成功之后，会在浏览器的正中间提示.span的会变化 在$("#msg").html("xx");中做处理。
    -->
    <span style="display:none;" class="msg" id="msg">
        &nbsp;
    </span>
  
</body>

<script type="text/javascript">

	
	var index=1;
 
	//表单校验
	 $(function(){
			//指明校验什么表单
			$("#myform").validate();
			//alert(1);
		});


	//跳转到修改页面
	    function onUpdate()
	    {
	       with(document.forms[0])
	       {
	          action="<%=request.getContextPath()%>/category/updateAction.do";
	       }
	    }
	//返回到list页面
	    function onReturn()
	    {
	       with(document.forms[0])
	       {
	        	  window.location.href="<%=request.getContextPath()%>/category/cateList.do";
		}
	}

	//将对扩展属性定义在页面端删除
	function del(obj) {
		//alert($(obj).closest("tr").attr("outerHTML"));
		//$(obj).closest("tr").attr("outerHTML","")
		$(obj).closest("tr").remove();
		resetSequenceNum();
		var _index = $("input[name='prop_index1']:last").val();
		if ((_index * 1 + 1) > 10) {
			$("#insertProp").attr("disabled", "disabled");
		} else {
			$("#insertProp").attr("disabled", false);
		}
	}

	//将对扩展属性定义在页面端添加
	function add() {
		var array = checkProp();
		/** maojd update data:10:30 2013/12/19  **/
		/* 修改一下判断逻辑，先去空格判断是否为空，然后判断是否重复。 */
    	
		//var prop_index = $("input[name='t_prop_index']").val();
    	//var propName = $("input[name='t_propName']").val();
    	//var propDesc = $("input[name='t_propDesc']").val();
		var propName = $.trim($("input[name='t_propName']").val());//获取终端的属性 input值，并去掉两边空格
    	var propDesc = $.trim($("input[name='t_propDesc']").val());//获取终端的属性描述 input值，并去掉两边空格
    	if(propName =="" || propName==undefined){
			adtecUtil.showMsg("名字不能为空");
    		$("input[name='t_propName']").focus();
    		return false;
    	}
    	if(!onT_propName()){
    		return false;//验证不是英文，就提示，并return false
    	}
    	if(array.length>0){
	    	for(var i=0;i<array.length;i++)
			{
    			if(propName == array[i].replace(/^\s+|\s+$/g, '') )
				{   
					adtecUtil.showMsg("名字不允许重复");
					return false;
				}
				//alert(propName+array.length);
			}
    	}
    	//alert(u_username);
    	
    	if(propDesc =="" || propDesc == undefined){

			adtecUtil.showMsg("描述不能为空");
    		$("input[name='t_propDesc']").focus();
    		return false;
    	}

		var trstr = $("#table").attr("outerHTML");
		//alert(trstr);

		$("#targetTable tr").last().after(trstr);
		//$("#targetTable").find("tr").last().after(trstr);

		$("#targetTable tr").last().css("display", "");
		//赋值
		var target = $("#targetTable tr").last().find("td");
		target.find("input[name='prop_index1']").val(index++);
		target.find("input[name='propName']").val(propName);
		target.find("input[name='propDesc']").val(propDesc);

		if(propName.length>=10){
    		propName=propName.substring(0,20)+"...";
    	}
    	if(propDesc.length>=10){
    		propDesc=propDesc.substring(0,15)+"...";
    	}
		
		target.find("input[name='prop_index1']").after();
		target.find("input[name='propName']").after(propName);
		target.find("input[name='propDesc']").after(propDesc);

		$("input[name='t_prop_index']").val(index);
		resetValue();
		resetSequenceNum();
		var _index = $("input[name='prop_index1']:last").val();
		$("input[name='t_prop_index']").val(_index * 1 + 1);
		if ((_index * 1 + 1) > 10) {
			$("#insertProp").attr("disabled", "disabled");
		} else {
			$("#insertProp").attr("disabled", false);
		}

	}
	//重新设置序号
	function resetSequenceNum() {
		var num = 1;
		$("input[name='prop_index1']").each(function(index, dom) {
			if (index != 0) {
				$(dom).val(num);
				num++;
			}
		});

		var num2 = 1;
		$("label[name='_index']").each(function(index, dom) {
			if (index != 0) {
				$(dom).html(num2);
				num2++;
			}
		});

		$("input[name='t_prop_index']").val(num2);

	}
	$(function() {
		var _index = $("input[name='prop_index1']:last").val();
		$("input[name='t_prop_index']").val(_index * 1 + 1);
		//调整查询出来数据显示的长度
		formactTable("targetTable",[0,1,2,3],[10,10,10,10],10);  
	});
	//清空表单的值
	function resetValue() {
		$("input[name='t_propName']").val("");
		$("input[name='t_propDesc']").val("");
	}
	
    //验证propName是否是英文
    function onT_propName(){
   //  var strName=${"#strName"}.val();
	   var CheckPropName=$("#t_propName").val();
	     if(isValidTruePropName(CheckPropName)){
			   return true;
	     }
	     else{
			   adtecUtil.showMsg("名称不允许为中文");
			   return false;
	     }
 	}

    //校验propName是否为英文
	function isValidTruePropName(CheckPropName){
		    var CheckPropNameTrim = CheckPropName.replace(/^\s+|\s+$/g, '');
	        var reg = /^[a-z A-Z 0-9 _]+$/;
	        if(reg.test(CheckPropNameTrim)){
	         return true;
	        }
	        return false;
	}
    
	//校验扩张属性定义名称是否重复
	function checkProp() {
		var array = new Array();
		$("input[name='propName']").each(function(index, dom) {
			array.push($(dom).val());
		});
		return array;
	}
	
	//页面初始化就执行 onload
	function init(){
		//检查扩展属性值
		var _index = $("input[name='prop_index1']:last").val();
		$("input[name='t_prop_index']").val(_index * 1 + 1);
		if ((_index * 1 + 1) > 10) {
			$("#insertProp").attr("disabled", "disabled");
		} else {
			$("#insertProp").attr("disabled", false);
		}
	}

</script>

</html>


