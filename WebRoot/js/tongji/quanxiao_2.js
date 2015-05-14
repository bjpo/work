	//隐藏日期类型:开始时间
	function hiddendayType_day_begin(){
		$("#td_daytype_day_begin").hide();
		$("#db_daytype_day_begin").datebox({disabled:true,required:false}); 
	}
	//隐藏日期类型：结束时间
	function hiddendayType_day_end(){
		$("#td_daytype_day_end").hide();
		$("#db_daytype_day_end").datebox({disabled:true,required:false}); 
	}

	//隐藏学期类型:学期
	function hiddenTermType_term(){
		$("#td_termtype_term").hide();
		$("#db_termtype_term").combobox({disabled:true});
	}
	//隐藏学期类型:周
	function hiddenTermType_zhou(){
		$("#td_termtype_zhou").hide();
		$("#db_termtype_zhou").combobox({disabled:true});
	}
	
	//隐藏学期类型:天
	function hiddenTermType_tian(){
		$("#td_termtype_tian").hide();
		$("#db_termtype_tian").combobox({disabled:true});
	}
	//隐藏学期类型:课时
	function hiddenTermType_keshi(){
//		$("#td_termtype_keshi").hide();
//		$("#db_termtype_keshi").combobox({disabled:true});
	}
	//显示日期类型:开始时间
	function showdayType_day_begin(){
		$("#td_daytype_day_begin").show();
		$("#db_daytype_day_begin").datebox({disabled:false,required:true}); 
	}
	//显示日期类型：结束时间
	function showdayType_day_end(){
		$("#td_daytype_day_end").show();
		$("#db_daytype_day_end").datebox({disabled:false,required:true}); 
	}
	//显示学期类型:学期
	function showTermType_term(){
		$("#td_termtype_term").show();
		$("#db_termtype_term").combobox({disabled:false});
	}
	//显示学期类型:周
	function showTermType_zhou(){
		$("#td_termtype_zhou").show();
		$("#db_termtype_zhou").combobox({disabled:false});
	}
	
	//显示学期类型:天
	function showTermType_tian(){
		$("#td_termtype_tian").show();
		$("#db_termtype_tian").combobox({disabled:false});
	}
	//显示学期类型:课时
	function showTermType_keshi(){
//		$("#td_termtype_keshi").show();
//		$("#db_termtype_keshi").combobox({disabled:false});
	}
	
		
	//隐藏日期类型:课时
	function hiddenDayType_keshi(){
		$("#td_daytype_keshi").hide();
		$("#db_daytype_keshi").combobox({disabled:true});
	}
	//显示日期类型:课时
	function showdayType_keshi(){
		$("#td_daytype_keshi").show();
		$("#db_daytype_keshi").combobox({disabled:false});
	}
	
	//隐藏全部
	function hiddenAll(){
		hiddendayType_day_begin();
		hiddendayType_day_end();
		hiddenTermType_term();
		hiddenTermType_zhou();
		hiddenTermType_tian();
		hiddenTermType_keshi();
		hiddenDayType_keshi();
		showdayType_keshi();
	}

	function hrefsubmit(value){
	    var path = "tongji/xueyuan_tongji.jsp";
	    $('#XY_ID_HIDDEN').val(value);
	    $('#serform').attr("action", path).submit();
	}

	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////begin
	//模糊查询
	function doSearch() {
		$('#datagrid').datagrid({url : 'listQUANXIAO2.action'});
		$('#datagrid').datagrid('load', {
			XS_CD : $('#XS_CD_serchbar').val(),
			XS_ID : $('#XS_ID_serchbar').val(),
			XS_XM : $('#XS_XM_serchbar').val(),
			QXRS : $('#QXRS_serchbar').val(),
			NJ_ID : $('#NJ_ID_serchbar').val(),
			CDRSBL : $('#CDRSBL_serchbar').val(),
			XS_XH : $('#XS_XH_serchbar').val(),
			ZCCXRS : $('#ZCCXRS_serchbar').val(),
			JS_JSMC : $('#JS_JSMC_serchbar').val(),
			KSRQ : $('#KSRQ_serchbar').val(),//开始日期
			XS_QX : $('#XS_QX_serchbar').val(),
			ZTRS : $('#ZTRS_serchbar').val(),
			NJMC : $('#NJMC_serchbar').val(),
			YSKRS : $('#YSKRS_serchbar').val(),
			JG_JGGH : $('#JG_JGGH_serchbar').val(),
			XS_ZT : $('#XS_ZT_serchbar').val(),
			BJMC : $('#BJMC_serchbar').val(),
			BJ_ID : $('#BJ_ID_serchbar').val(),
			JG_JGMC : $('#JG_JGMC_serchbar').val(),
			KCXX_KCMC : $('#KCXX_KCMC_serchbar').val(),
			SKRQ : $('#SKRQ_serchbar').val(),
			XINGQI : $('#XINGQI_serchbar').combobox('getValue'), //星期
			KCXX_ID : $('#KCXX_ID_serchbar').val(),
			ZYMC : $('#ZYMC_serchbar').val(),
			ZCCXBL : $('#ZCCXBL_serchbar').val(),
			XY_ID : $('#XY_ID_serchbar').val(),
			CQQK : $('#CQQK_serchbar').val(),
			ZTRSBL : $('#ZTRSBL_serchbar').val(),
			ZHOU : $('#ZHOU_serchbar').combobox('getValue'), //周
			KS_ID :$('#KS_ID_serchbar').combobox('getValue'), //课时
			XYMC : $('#XYMC_serchbar').val(),
			CDRS : $('#CDRS_serchbar').val(),
			XUEQI_ID : $('#XUEQI_ID_serchbar').combobox('getValue'), //学期
			XS_ZCCX : $('#XS_ZCCX_serchbar').val(),
			JSRQ :$('#JSRQ_serchbar').val(),//结束日期
			QXRSBL : $('#QXRSBL_serchbar').val(),
			JS_ID : $('#JS_ID_serchbar').val(),
			JG_ID : $('#JG_ID_serchbar').val(),
			ZY_ID : $('#ZY_ID_serchbar').val(),
			optionflag : 'selbylike'
		});
	}
	
	
	function initserform(ksrq,jsrq,zhou,xingqi,ksid,xueqiid){
		$('#KSRQ_serchbar').val(ksrq);
		$('#JSRQ_serchbar').val(jsrq);
		$('#ZHOU_serchbar').combobox('setValue',zhou);
		$('#XINGQI_serchbar').combobox('setValue',xingqi);
		$('#KS_ID_serchbar').combobox('setValue',ksid);
		$('#XUEQI_ID_serchbar').combobox('setValue',xueqiid);
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////end
	$(function(){
	    hiddenAll();//隐藏全部
	    showdayType_day_begin();//默认为日期方式显示：显示日期相关
	    showdayType_day_end();
	    showdayType_keshi();
	    
	    
//		$('#KS_ID_serchbar').combobox('setValue','3');

	    
		//查询方式复选框变更联动
		$('#selectType').combobox({onChange:function(n,o){
		    	if("day"==n.toString()){ //按天统计
		    				hiddenAll();
							showdayType_day_begin();
						    showdayType_day_end();
						    showdayType_keshi();
		    	}else if("term"==n.toString()){
							hiddenAll();
							showTermType_term();//显示学期
							showTermType_zhou();//显示周
							showTermType_tian();
							showTermType_keshi();
		    	}
		}});


	});
