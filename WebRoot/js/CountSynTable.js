function updateData() {
	$.ajax({
		url : "findMoni.action",
		context : document.body,
		cache : false,
		success : function(data) {
			var o = eval("(" + data + ")");
			var Div = document.getElementById("a");
			Div.innerHTML = "全校出勤情况<br>" + "正常出席：" + 
			o.cx + "%<br>" + "缺席：" + o.qx + "%<br>" + "迟到：" + o.cd + "%<br>" + "早退：" + o.zt + "%<br>";
		}
	});
}

$(document).ready(function(e) {
	var boarddiv = "<div id='a' style='z-index:999;position:absolute;right:200px;width:200px;height:100px;overflow:hidden;padding:10px;font-size:14px;border:1px groove #281;-moz-user-select:none;'>加载中...</div>"; 
	$(document.body).append(boarddiv);
	var int = self.setInterval("updateData()", 10000);
});
