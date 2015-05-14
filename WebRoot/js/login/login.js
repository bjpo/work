
function login(){
	$(".topdown").show();
}
// 初始化
$(function() {
	// 弹出窗口方法 2014-08-19
	$(".button").click(function() {
		var $this = $(this);
		var index = $this.index();

		$(".topdown").fadeToggle(500);
		window.setTimeout(function() {
			$(".topbox").slideToggle(500);
		}, 100);
	});
	
	$(".close").click(function() {
		var $this = $(this);
		var index = $this.index();
		$(".topdown").fadeToggle(500);
		window.setTimeout(function() {
			$(".topbox").slideToggle(500);
		}, 100); 
	});

	// 图片滚动方法
	var len = $(".num > li").length;
	var index = 0;
	var adTimer;
	$(".num li").mouseover(function() {
		index = $(".num li").index(this);
		showImg(index);
	}).eq(0).mouseover();

	$('#scrollPics').hover(function() {
		clearInterval(adTimer);
	}, function() {
		adTimer = setInterval(function() {
			showImg(index)
			index++;
			if (index == len) {
				index = 0;
			}
		}, 3000);
	}).trigger("mouseleave");
	function showImg(index) {
		var adHeight = $("#scrollPics>ul>li:first").height();
		$(".slider").stop(true, false).animate({
			"marginTop" : -adHeight * index + "px"
		}, 1000);
		$(".num li").removeClass("on").eq(index).addClass("on");
	}


});

// 登录前进行验证
function loginCheck() {
//	$('#loginForm').form('submit',{
//		url : 'login.action',
//		onSubmit : function() {
//		}
//	});
	$("#loginForm").attr("action", "login.action");
	$("#loginForm").submit(function(){});
}
//添加收藏
function AddFavorite(sURL, sTitle)
{
    try
    {
    	
    	//ie浏览器
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e)
    {
        try
        {//非ie浏览器
            window.sidebar.addPanel(sTitle, sURL, "");
        }
        catch (e)
        {
        	$.messager.alert('提示','　　你的浏览器不支持此操作，请使用ctrl+d进行添加收藏');
          
        }
    }
}

