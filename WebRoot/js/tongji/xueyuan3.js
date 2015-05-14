	//隐藏日期类型:开始时间
	function hiddendayType_day_begin(){
		$("#td_daytype_day_begin").hide();
		$("#td_daytype_day_begin2").hide();
		$("#db_daytype_day_begin").datebox({disabled:true,required:false}); 
	}
	//隐藏日期类型：结束时间
	function hiddendayType_day_end(){
		$("#td_daytype_day_end").hide();
		$("#td_daytype_day_end2").hide();
		$("#db_daytype_day_end").datebox({disabled:true,required:false}); 
	}
	//隐藏学期类型:学期
	function hiddenTermType_term(){
		$("#td_termtype_term").hide();
		$("#td_termtype_term2").hide();
		$("#db_termtype_term").combobox({disabled:true});
	}
	//隐藏学期类型:周
	function hiddenTermType_zhou(){
		$("#td_termtype_zhou").hide();
		$("#td_termtype_zhou2").hide();
		$("#db_termtype_zhou").combobox({disabled:true});
	}
	
	//隐藏学期类型:天
	function hiddenTermType_tian(){
		$("#td_termtype_tian").hide();
		$("#td_termtype_tian2").hide();
		$("#db_termtype_tian").combobox({disabled:true});
	}
	//隐藏学期类型:课时
	function hiddenTermType_keshi(){
	/*	$("#td_termtype_keshi").hide();
		$("#td_termtype_keshi2").hide();
		$("#db_termtype_keshi").combobox({disabled:true});
	*/}
	//显示日期类型:开始时间
	function showdayType_day_begin(){
		$("#td_daytype_day_begin").show();
		$("#td_daytype_day_begin2").show();
		$("#db_daytype_day_begin").datebox({disabled:false,required:true}); 
	}
	//显示日期类型：结束时间
	function showdayType_day_end(){
		$("#td_daytype_day_end").show();
		$("#td_daytype_day_end2").show();
		$("#db_daytype_day_end").datebox({disabled:false,required:true}); 
	}
	//显示学期类型:学期
	function showTermType_term(){
		$("#td_termtype_term").show();
		$("#td_termtype_term2").show();
		$("#db_termtype_term").combobox({disabled:false});
	}
	//显示学期类型:周
	function showTermType_zhou(){
		$("#td_termtype_zhou").show();
		$("#td_termtype_zhou2").show();
		$("#db_termtype_zhou").combobox({disabled:false});
	}
	
	//显示学期类型:天
	function showTermType_tian(){
		$("#td_termtype_tian").show();
		$("#td_termtype_tian2").show();
		$("#db_termtype_tian").combobox({disabled:false});
	}
	//显示学期类型:课时
	function showTermType_keshi(){
		$("#td_termtype_keshi").show();
		$("#td_termtype_keshi2").show();
		$("#db_termtype_keshi").combobox({disabled:false});
	}
	
	
	
	
	//隐藏全部
	function hiddenAll(){
		hiddendayType_day_begin();
		hiddendayType_day_end();
		hiddenTermType_term();
		hiddenTermType_zhou();
		hiddenTermType_tian();
		hiddenTermType_keshi();
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////begin

