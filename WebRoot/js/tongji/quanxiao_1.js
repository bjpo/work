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

	function hrefsubmit(){
		    var path = "tongji/quqnxiao_2.jsp";
		    $('#serform').attr("action", path).submit();
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////begin
	//模糊查询
	function doSearch() {		
		$('#datagrid').datagrid('load', {
			KSRQ : $('#KSRQ_serchbar').val(),//开始日期
			JSRQ : $('#JSRQ_serchbar').val(),//结束日期
			XINGQI : $('#XINGQI_serchbar').combobox('getValue'), //星期
			ZHOU : $('#ZHOU_serchbar').combobox('getValue'), //周
			KS_ID :$('#KS_ID_serchbar').combobox('getValue'), //课时
			XUEQI_ID : $('#XUEQI_ID_serchbar').combobox('getValue'), //学期
			optionflag : 'selbylike'
		});
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////end
	$(function(){
	    hiddenAll();//隐藏全部
	    showdayType_day_begin();//默认为日期方式显示：显示日期相关
	    showdayType_day_end();
	    showdayType_keshi();
	    
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

		$('#datagrid').datagrid({
					url : 'listQUANXIAO1.action',
					title : '全校',
					iconCls : 'icon-cls',
					pageSize : 10,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					fitColumns : false,
					nowrap : false,
					border : false,
					idField : 'TONGJI_ID',
					columns : [ [
							{
								title : '<b>应上课人数</b>',
								field : 'YSKRS',
								sortable : true,
								width : 100
							},{
								title : '<b>正常出席人数/%</b>',
								field : 'ZCCXRS',
								sortable : true,
								width : 100
							},{
								title : '<b>缺席人数/%</b>',
								field : 'QXRS',
								sortable : true,
								width : 100
							},{
								title : '<b>迟到人数/%</b>',
								field : 'CDRS',
								sortable : true,
								width : 100
							},{
								title : '<b>早退人数</b>',
								field : 'ZTRS',
								sortable : true,
								width : 100
							},{
								title : '<b>进一步查看组成</b>',
								field : 'TONGJI_ID',
								width : 100,
								formatter : function(value, rowData,
										rowIndex) {
									 ret="<a style='cursor:hand' onclick='hrefsubmit();'>进一步查看组成</a>";
									return ret;
								}
							} ] ],
					singleSelect : false,// 是否单选
					pagination : true,// 分页控件
					rownumbers : true,// 行号,
					frozenColumns : [ [ {
						field : 'ck',
						checkbox : true
					} ] ]
				});
				$('#datagrid').datagrid('getPager').pagination({
					buttons: [{
						text:"导出",
						iconCls:'icon-add',
						handler:function(){alert('add')}
					}],
					pageSize : 10,// 每页显示的记录条数，默认为10
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],// 可以设置每页记录条数的列表
					beforePageText : '第',// 页数文本框前显示的汉字
					afterPageText : '页    共 {pages} 页',
					displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
				});
	});
