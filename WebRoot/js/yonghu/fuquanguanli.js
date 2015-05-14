// 打开新增用户2角色对话框
function open_addyonghu2juese_dlg() {
	$('#addyonghu2juese_dlg').dialog('open').dialog('setTitle', '为用户分配角色'); // 弹出窗口
	$('#yonghuselectbox').combobox({
	    url:'listYONGHUforYONGHU.action',  
	    valueField:'yhid',  
	    textField:'yhm'  
	});
	$.ajax({
		url : "listJUESEforYONGHU.action",
		context : document.body,
		success : function(data) {
			var jyobj = eval("(" + data + ")");
			var htmlstr="";
			for(var i=0;i<jyobj.length;i++){
				if(i>0){
					if(i%2==0){
						htmlstr=htmlstr+"";
					}
				}
				htmlstr=htmlstr+"<div class='fqx'><input type='checkbox' id='"+jyobj[i].jsid+"' name='jy_jsid' value='"+jyobj[i].jsid+"'/><label for='"+jyobj[i].jsid+"'>"+jyobj[i].jsmc+"</label></div>";
			}
			$("#jueseliebiao_yonghu").html(htmlstr);
		}
	});
	$.parser.parse('#addyonghu2juese_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}

// 新增用户2角色信息
function addyonghu2juese() {
	$('#addyonghu2juese_form').form('submit', {
		url : 'addJUESE2YONGHU.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增模块失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addyonghu2juese_form').form('clear');
				$('#addyonghu2juese_dlg').dialog('close');
			}
		}
	});
}


//打开新增用户组2角色对话框
function open_addyonghuzu2juese_dlg() {
	$('#addyonghuzu2juese_dlg').dialog('open').dialog('setTitle', '为用户组分配角色'); // 弹出窗口
	$('#yonghuzuselectbox').combobox({
	    url:'listYHZforYONGHU.action',  
	    valueField:'yhzid',  
	    textField:'yhzmc'  
	});
	$.ajax({
		url : "listJUESEforYONGHU.action",
		context : document.body,
		success : function(data) {
			var jyobj = eval("(" + data + ")");
			var htmlstr="";
			for(var i=0;i<jyobj.length;i++){
				if(i>0){
					if(i%2==0){
						htmlstr=htmlstr+"";
					}
				}
				htmlstr=htmlstr+"<div class='fqx'><input type='checkbox' id='"+jyobj[i].jsid+"' name='jyz_jsid' value='"+jyobj[i].jsid+"'/><label for='"+jyobj[i].jsid+"'>"+jyobj[i].jsmc+"</label></div>";
			}
			$("#jueseliebiao_yonghuzu").html(htmlstr);
		}
	});
	$.parser.parse('#addyonghuzu2juese_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}

// 新增用户组2角色信息
function addyonghuzu2juese() {
	$('#addyonghuzu2juese_form').form('submit', {
		url : 'addJUESE2YONGHUZU.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增模块失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addyonghuzu2juese_form').form('clear');
				$('#addyonghuzu2juese_dlg').dialog('close');
			}
		}
	});
}



//打开新增模块2角色对话框
function open_addmokuai2juese_dlg() {
	$('#addmokuai2juese_dlg').dialog('open').dialog('setTitle', '为角色分配模块'); // 弹出窗口
	$('#jueseselectbox').combobox({
	    url:'listJUESEforYONGHU.action',  
	    valueField:'jsid',  
	    textField:'jsmc'  
	});
	$.ajax({
		url : "listMOKUAIforYONGHU.action",
		context : document.body,
		success : function(data) {
			var jyobj = eval("(" + data + ")");
			var htmlstr="";
			for(var i=0;i<jyobj.length;i++){
				if(i>0){
					if(i%2==0){
						htmlstr=htmlstr+"";
					}
				}
				htmlstr=htmlstr+"<div class='fqx'><input type='checkbox' id='"+jyobj[i].mkid+"' name='jm_mkid' value='"+jyobj[i].mkid+"'/><label for='"+jyobj[i].mkid+"'>"+jyobj[i].mkmc+"</label></div>";
			}
			$("#mokuailiebiao_mokuai").html(htmlstr);
		}
	});
	$.parser.parse('#addmokuai2juese_dlg'); // 需要重新渲染对话框，否则easyui不起作用
}

//新增模块2角色信息
function addmokuai2juese() {
	$('#addmokuai2juese_form').form('submit', {
		url : 'addJUESE2MOKUAI.action',
		onSubmit : function() {
		},
		success : function(data) {
			if ("false" == data) {
				$.messager.alert('提示', '新增模块失败！');
			} else {
				var obj2 = eval("(" + data + ")");
				$.messager.alert('提示', obj2.message);
				$('#addmokuai2juese_form').form('clear');
				$('#addmokuai2juese_dlg').dialog('close');
			}
		}
	});
}



// 初始化方法
$(function() {
	
});