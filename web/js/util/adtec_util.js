

var adtecUtil={
	"formactTable":formactTable,
	"showMsg":showMsg,
	"pageTagFun":pageTagFun,
	"padTable":padTable
};


/*
排除第一行为tr的标签,索引数组和长度数组一致。
id 字符串，table的id
arrIndex 数组，列索引
arrLength 数组，对应列的长度
defaultLength 数字，默认长度

formactTable("hb",[1,2,3,4,5],[10,10,10,10,20]);
formactTable2("hb",[1,2,3,4],[10,10,10,10],16);
*/
function formactTable(id,arrIndex,arrLength){
	//最前面需要校验参数
	//alert($("#"+id).find("tr").length);

	//检查参数是否合理
	//检查是否为字符串
	if(typeof(id)!="string"){
		return false;
	}
	//检查是否为数组
	if(!(arrIndex instanceof Array)){
		return false;
	}
	if(!(arrLength instanceof Array)){
		return false;
	}
	
	//设置默认长度
	var defaultLength = arguments[3];
	if(defaultLength==undefined || defaultLength==null || defaultLength==""){
		defaultLength = 10;
	}else{
		//检查默认字符串长度
		if(typeof defaultLength != "number"){
			defaultLength = 10;
			return false;
		}
	}
	
	if(arrIndex.length != arrLength.length){
		alert("两个数组长度不一致");
		return false;
	}

	var objTable = $("#"+id);
	//检查该id控件是否存在
	if(objTable.length==0){
		return false;
	}
	
	var objTrs = $("#"+id).find("tr");
	//如果
	if(objTrs.length<1){
		return false;
	}
	//标示从第n列开始
	var startIndex = arrIndex[0];
	
	$("#"+id).find("tr").each(function(num,dom){
		//标题行不用考虑
		if(num>=0){
			var col = arrIndex[num];
			var tdsObj = $(dom).find("td");
			//alert($(dom).find("td").length);
			tdsObj.each(function(index,ele){
				var colLength = arrLength[index-startIndex];
				
				
				//从指定的列数开始设定
				if(index >= startIndex){
					//如果实际列数大于设定的列数，则显示默认长度
					if(colLength==undefined || colLength==null || colLength==""){
						colLength = defaultLength;
					}
					
					//如果有链接
					if($(ele).find("a").length>0){
						//var a_content = $(ele).find("a").html();
						var a_content = $.trim($(ele).find("a").html());
						$(ele).attr("title",a_content);
						var len = a_content.length;
						var temp="";
						if(len > colLength){
							temp = a_content.substring(0,colLength)+"...";
							$(ele).find("a").html(temp);
						}else{
							//temp = content;
						}
					}else if($(ele).find("label").length>0){
						//var a_content = $(ele).find("a").html();
						var a_content = $.trim($(ele).find("label").html());
						$(ele).attr("title",a_content);
						var len = a_content.length;
						var temp="";
						if(len > colLength){
							temp = a_content.substring(0,colLength)+"...";
							$(ele).find("label").html(temp);
						}else{
							//temp = content;
						}
					}else{
						var content = $.trim($(ele).html());
						
						//alert(content);
						var len = content.length;
						var temp="";
						if(len > colLength){
							temp = content.substring(0,colLength)+"...";
							$(ele).html(temp);
							$(ele).attr("title",content);
						}else{
							//temp = content;
						}
					}
				}else{
					//设定td的title属性
					//如果有链接
					if($(ele).find("a").length>0){
						var a_content = $(ele).find("a").html();
						$(ele).attr("title",a_content);
					}else{
						var content = $(ele).html();
						$(ele).attr("title",content);
					}
				}
			});
			
		}
	});
}


$(function (){//每次有页面跳转，或者重新引入adtec_util.js的时候，先隐藏msg，然后继续操作
	hidenMsg();
});
//显示msg页面 
function showMsg(msgHtml){
	$(window.parent.document).find("#msg").html(msgHtml);			//要提示的文字 赋值
	var msgBoxDiv = $(window.parent.document).find("#msgBoxDIV");
	var disp = $(msgBoxDiv).css("display");							//获取到msg的透明度
	if(disp == "none"){
		if($(msgBoxDiv).css("opacity") < 1){
			$(msgBoxDiv).css("opacity",1);
		}
		$(msgBoxDiv).fadeIn("fast");	//显示出来
		setTimeout("hidenMsg()",1000);
	}
	//$(window.parent.document).find("#msgBoxDIV").css("display","");	//显示出来
								

}
//隐藏msg
function hidenMsg(){
	var msgBoxDiv = $(window.parent.document).find("#msgBoxDIV");
	var disp = $(msgBoxDiv).css("display");
	if(disp == "block"){
		/*if($(msgBoxDiv).css("opacity") < 1){
			$(msgBoxDiv).css("opacity",1);
		}*/
		$(msgBoxDiv).fadeOut("slow");	//隐藏起来提示成功的信息
	}
	//$(window.parent.document).find("#msgBoxDIV").css("display","none");	//隐藏起来提示成功的信息
}


/**
 * 分页标签不带查询条件，所以改成onclick function
 */
function pageTagFun(){
	var pageTagList = document.getElementsByName("pageTag");
	for(var i = 0; i < pageTagList.length; i++){
		var pageTag = pageTagList[i];
		var href = pageTag.href;
		pageTag.href = "#";
		var start = href.indexOf("pageNow=");
		var pageNow = href.substring(start+8);
		pageTag.setAttribute("onclick","return onSelect(\'"+pageNow+"\')");
	}
}


/**
 *固定table的高度（显示数据的table数据量不够一页，插入空tr）
 *maojd update date:2014/02/18 16:14
 */
function padTable(tableId,pageSize){
	
	var tips_bar = document.getElementById("tips_bar");//获取页面为空的div.数据为空的话，补充tr。否则不做操作
	if((tips_bar == null) || (tips_bar == undefined) ){
		//alert("补充");
		var table = document.getElementById(tableId);
		var exitTrNum = table.rows.length;		 //存在tr数量
		var addTrNum = pageSize - exitTrNum;	 //需要添加tr的数量
		var tdSize = 0;							//td的个数
		var height = '10px';					//tr的高度
		if(table.rows.length>0){
			tdSize = table.rows[0].cells.length;
			height = table.rows[0].style.height;//20px
		}
		for(var i=0;i<addTrNum;i++){		  	 //循环插入tr
			var tr = table.insertRow(table.rows.length);
			tr.style.height = height;			//设定tr的高度
			for(var j=0;j<tdSize;j++){			//循环插入td
				var td = tr.insertCell(j);
				td.innerHTML+="&nbsp;";
			}
		}
	}
	
	
}