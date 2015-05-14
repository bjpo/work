//菜单类别中生成树形菜单
$(function() {
	// 异步请求菜单类别
	$.ajax({
		type : "POST",
		url : "getAllMenuCate.action",
		success : function(data) {
			var obj = eval("(" + data + ")");
			$.each(obj, function(i, n) {
				$('#aa').accordion(
						'add',
						{
							title : n.menuName,
							content : "<div style='padding:10px'><ul name='"
									+ n.menuName + "'></ul><ul id='"
									+ n.menuCateId + "'></ul></div>",
							selected : false
						});
			});
		}
	});

	// 点击菜单类别时，触发请求菜单树
	$('#aa').accordion({
		onSelect : function(title, index) {
			// 从页面获取菜单类别ID
			var menuId = $("ul[name='" + title + "'] ~ ul").attr("id");
			$("ul[name='" + title + "']").tree({
				url : 'getMKWithMenuId.action?menuCateId=' + menuId,
				onClick : function(node) {
					addTab(node);
				},
				onDblClick : function(node) {
					if (node.state == 'closed') {
						$(this).tree('expand', node.target);
					} else {
						$(this).tree('collapse', node.target);
					}
				},
				// 加载数据时，进行提示
				onBeforeLoad : function() {
					$.messager.progress({
						text : '数据加载中....'
					});
				},
				// 数据加载完成后，关闭提示
				onLoadSuccess : function(data) {
					$.messager.progress('close');
				}
			});
		}
	});
});
//
// // 生成菜单树
// $('#treemenu').tree({
// url : 'getMenuTree.action',
// lines : true,
// onClick : function(node) {
// addTab(node);
// },
// onDblClick : function(node) {
// if (node.state == 'closed') {
// $(this).tree('expand', node.target);
// } else {
// $(this).tree('collapse', node.target);
// }
// }
// });
