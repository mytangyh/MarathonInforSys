<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>赛事列表</title>
<link rel="stylesheet" type="text/css"
	href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../easyui/js/validateExtends.js"></script>
<script type="text/javascript">

	$(function() {
		var table;

		//datagrid初始化 
		$('#dataList').datagrid({
			title : '赛事列表',
			iconCls : 'icon-more',//图标 
			border : true,
			collapsible : false,//是否可折叠的 
			fit : true,//自动大小 
			method : "post",
			url : "get_list?t=" + new Date().getTime(),
			idField : 'game_id',
			singleSelect : false,//是否单选 
			pagination : true,//分页控件 
			rownumbers : true,//行号 
			sortName : 'game_id',
			//sortOrder : 'DESC',
			sortOrder : 'asc',
			remoteSort : false,
			columns : [ [ {
				field : 'chk',
				checkbox : true,
				width : 50
			}, {
				field : 'game_id',
				title : 'ID',
				width : 50,
				sortable : true
			}, {
				field : 'game_name',
				title : '赛事名称',
				width : 150,
				sortable : true
			}, {
				field : 'game_type',
				title : '赛事类型',
				width : 100
			}, {
                field : 'game_begintime',
                title : '起跑时间',
                width : 150
            },{
                field : 'game_endtime',
                title : '关门时间',
                width : 150
            },{
                field : 'game_num',
                title : '赛事规模(人)',
                width : 100
            },
            {
                field : 'game_unit',
                title : '主办单位',
                width : 100
            },{
                field : 'game_location',
                title : '举办地点',
                width : 100
            },
            {
                field : 'game_remakes',
                title : '备注',
                width : 100
            },
			
			] ],
			toolbar : "#toolbar"
		});
		//设置分页控件 
		var p = $('#dataList').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10 
			pageList : [ 10, 20, 30, 50, 100 ],//可以设置每页记录条数的列表 
			beforePageText : '第',//页数文本框前显示的汉字 
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		});
		//设置工具类按钮
		$("#add").click(function() {
			table = $("#addTable");
			$("#addDialog").dialog("open");
		});
		//修改
		$("#edit").click(function() {
			table = $("#editTable");
			var selectRows = $("#dataList").datagrid("getSelections");
			if (selectRows.length != 1) {
				$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
			} else {
				$("#editDialog").dialog("open");
			}
		});
		//删除
		$("#delete").click(
				function() {
					var selectRows = $("#dataList").datagrid("getSelections");
					var selectLength = selectRows.length;
					if (selectLength == 0) {
						$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
					} else {
						var ids = [];
						$(selectRows).each(function(i, row) {
							ids[i] = row.game_id;
						});
						$.messager.confirm("消息提醒", "将删除与赛事相关的所有数据，确认继续？",
								function(r) {
									if (r) {
										$.ajax({
											type : "post",
											url : "delete",
											data : {
												ids : ids
											},
											dataType : 'json',
											success : function(data) {
												if (data.type == "success") {
													$.messager.alert("消息提醒",
															"删除成功!", "info");
													//刷新表格
													$("#dataList").datagrid(
															"reload");
													$("#dataList").datagrid(
															"uncheckAll");
												} else {
													$.messager
															.alert("消息提醒",
																	data.msg,
																	"warning");
													return;
												}
											}
										});
									}
								});
					}
				});

		//设置添加窗口
		$("#addDialog").dialog(
				{
					title : "添加赛事",
					width : 350,
					height : 500,
					iconCls : "icon-add",
					modal : true,
					collapsible : false,
					minimizable : false,
					maximizable : false,
					draggable : true,
					closed : true,
					buttons : [
							{
								text : '添加',
								plain : true,
								iconCls : 'icon-user_add',
								handler : function() {
									var validate = $("#addForm").form(
											"validate");
									if (!validate) {
										$.messager.alert("消息提醒", "请检查你输入的数据!",
												"warning");
										return;
									} else {
										var data = $("#addForm").serialize();
										$.ajax({
											type : "post",
											url : "addgame",
											data : data,
											dataType : 'json',
											success : function(data) {
												if (data.type == "success") {
													$.messager.alert("消息提醒",
															"添加成功!", "info");
													//关闭窗口
													$("#addDialog").dialog(
															"close");
													//清空原表格数据
													$("#add_gamename").textbox(
															'setValue', "");
													$("#add_gamenum").textbox(
															'setValue', "");
													$("#add_gameunit").textbox(
                                                            'setValue', "");
													$("#add_gamelocation").textbox(
                                                            'setValue', "");
													$("#add_gameremakes").textbox(
                                                            'setValue', "");
													//重新刷新页面数据
													$('#dataList').datagrid(
															"reload");

												} else {
													$.messager
															.alert("消息提醒",
																	data.msg,
																	"warning");
													return;
												}
											}
										});
									}
								}
							}, ],
					onClose : function() {
						$("#add_gamename").textbox(
                                'setValue', "");
                        $("#add_gamenum").textbox(
                                'setValue', "");
                        $("#add_gameunit").textbox(
                                'setValue', "");
                        $("#add_gamelocation").textbox(
                                'setValue', "");
                        $("#add_gameremakes").textbox(
                                'setValue', "");
					}
				});

		//编辑用户信息
		
		
		$("#editDialog").dialog(
				{
					title : "修改用户信息",
					width : 350,
					height : 500,
					iconCls : "icon-edit",
					modal : true,
					collapsible : false,
					minimizable : false,
					maximizable : false,
					draggable : true,
					closed : true,
					buttons : [
							{
								text : '提交',
								plain : true,
								iconCls : 'icon-edit',
								handler : function() {
									var validate = $("#editForm").form(
											"validate");
									if (!validate) {
										$.messager.alert("消息提醒", "请检查你输入的数据!",
												"warning");
										return;
									} else {

										var data = $("#editForm").serialize();

										$.ajax({
											type : "post",
											url : "edit",
											data : data,
											dataType : 'json',
											success : function(data) {
												if (data.type == "success") {
													$.messager.alert("消息提醒",
															"修改成功!", "info");
													//关闭窗口
													$("#editDialog").dialog(
															"close");

													//重新刷新页面数据
													$('#dataList').datagrid(
															"reload");
													$('#dataList').datagrid(
															"uncheckAll");

												} else {
													$.messager
															.alert("消息提醒",
																	data.msg,
																	"warning");
													return;
												}
											}
										});
									}
								}
							}, ],
					onBeforeOpen : function() {
						function tfmt(str){//格式化为 月/日/年 时:分
						    var str1=str.split(" ");
						    var dat=str1[0].split("-");
						    var timefm=dat[1].concat("/"+dat[2]+"/"+dat[0]+" "+str1[1]);
						    return timefm;
						}
						var selectRow = $("#dataList").datagrid("getSelected");
						//设置值
						$("#edit_id").val(selectRow.game_id);
						$("#edit_gamename").textbox('setValue',
								selectRow.game_name);
						$("#edit_gametype").combobox('setValue',
								selectRow.game_type);
						$("#edit_begintime").datetimebox('setValue',
                               tfmt(selectRow.game_begintime));
						$("#edit_endtime").datetimebox('setValue',
                                tfmt(selectRow.game_endtime));
						$("#edit_gamenum").numberbox('setValue',
                                selectRow.game_num);
						$("#edit_gameunit").textbox('setValue',
                                selectRow.game_unit);
						$("#edit_gamelocation").textbox('setValue',
                                selectRow.game_location);
						$("#edit_gameremakes").textbox('setValue',
                                selectRow.game_remakes);
					}
				});

		//搜索按钮
		$("#search-btn").click(function() {
			$('#dataList').datagrid('reload', {
				game_name:$("#search-gamename").textbox('getValue'),
				game_type:$("#search-gametype").combobox('getValue'),
				game_num:$("#search-gamenum").numberbox('getValue'),
				game_unit:$("#search-gameunit").textbox('getValue'),
				game_location:$("#search-gamelocation").textbox('getValue')
			});
		});
	});
</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0">

	</table>
	<!-- 工具栏 -->
	<div id="toolbar">
        <div style="float: left;">
            <a id="add" href="javascript:;" class="easyui-linkbutton"
                data-options="iconCls:'icon-add',plain:true">添加</a>
        </div>
        <div style="float: left;" class="datagrid-btn-separator"></div>
        <div style="float: left;">
            <a id="edit" href="javascript:;" class="easyui-linkbutton"
                data-options="iconCls:'icon-edit',plain:true">修改</a>
        </div>
        <div style="float: left;" class="datagrid-btn-separator"></div>
        <div>
            <a id="delete" href="javascript:;" class="easyui-linkbutton"
                data-options="iconCls:'icon-some-delete',plain:true">删除</a> 
               
                名称： <input id="search-gamename" class="easyui-textbox" style="width: 100px" />
                类型：<select id="search-gametype"  class="easyui-combobox" style="width: 100px;">
                        <option value="">全部类型</option>
                            <option value="全程">全程</option>
                            <option value="半程">半程</option>
                            <option value="10公里">10公里</option>
                    </select>
                    规模： <input id="search-gamenum" class="easyui-numberbox" style="width: 100px" /> 
             单位： <input id="search-gameunit" class="easyui-textbox" style="width: 100px" /> 
             地点： <input id="search-gamelocation" class="easyui-textbox" style="width: 100px" />
            <a id="search-btn" href="javascript:;" class="easyui-linkbutton"
                data-options="iconCls:'icon-search',plain:true">搜索</a>
        </div>
    </div>
	

	<!-- 添加窗口 -->
	<div id="addDialog" style="padding: 10px;">
		<form id="addForm" method="post">
			<table id="addTable" cellpadding="8">
				<tr>
					<td>赛事名称:</td>
					<td><input id="add_gamename" class="easyui-textbox"
						style="width: 200px; height: 30px;" type="text" name="game_name"
						data-options="required:true, missingMessage:'请填写赛事名称'" /></td>
				</tr>

				<tr>
					<td>赛事类型:</td>
					<td><select class="easyui-combobox" name="game_type"
						id="add_gametype" data-options="editable:false,panelHeight:'auto'"
						style="width: 200px;">
							<option value="全程">全程</option>
							<option value="半程">半程</option>
							<option value="10公里">10公里</option>
					</select></td>
				</tr>
				<tr>
					<td>起跑时间：</td>
					<td><input class="easyui-datetimebox" name="game_begintime"
						id="add_begintime" data-options="required:true,showSeconds:false"
						value="${notices.release_time}" style="width: 200px"></td>
				</tr>
				<tr>
					<td>关门时间：</td>
					<td><input class="easyui-datetimebox" name="game_endtime"
						id="add_endtime" data-options="required:true,showSeconds:false"
						value="${notices.release_time}" style="width: 200px"></td>
				</tr>
				<tr>
					<td>赛事规模:</td>
					<td><input id="add_gamenum" type="text" name="game_num"
						class="easyui-numberbox" value="1000"
						style="width: 200px; height: 30px;"
						data-options="min:0,required:true, missingMessage:'请填写赛事规模(单位：人)'">
					</td>
				</tr>
				<tr>
				<tr>
					<td>主办单位:</td>
					<td><input id="add_gameunit"
						style="width: 200px; height: 30px;" class="easyui-textbox"
						type="text" name="game_unit"
						data-options="required:true, missingMessage:'请填写主办单位'" /></td>
				</tr>
				<tr>
					<td>比赛地点:</td>
					<td><input id="add_gamelocation"
						style="width: 200px; height: 30px;" class="easyui-textbox"
						type="text" name="game_location"
						data-options="required:true, missingMessage:'请填写比赛地点'" /></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input id="add_gameremakes" value="无"
						style="width: 200px; height: 30px;" class="easyui-textbox"
						type="text" name="game_remakes" data-options="required:false" /></td>
				</tr>
			</table>
		</form>
	</div>


	<!-- 修改窗口 -->
	<div id="editDialog" style="padding: 10px">
		<form id="editForm" method="post">
			<input type="hidden" name="game_id" id="edit_id">
			<table id="editTable" border=0 cellpadding="8">
				<tr>
                    <td>赛事名称:</td>
                    <td><input id="edit_gamename" class="easyui-textbox"
                        style="width: 200px; height: 30px;" type="text" name="game_name"
                        data-options="required:true, missingMessage:'请填写赛事名称'" /></td>
                </tr>

                <tr>
                    <td>赛事类型:</td>
                    <td><select class="easyui-combobox" name="game_type"
                        id="edit_gametype" data-options="editable:false,panelHeight:'auto'"
                        style="width: 200px;">
                            <option value="全程">全程</option>
                            <option value="半程">半程</option>
                            <option value="10公里">10公里</option>
                    </select></td>
                </tr>
                <tr>
                    <td>起跑时间：</td>
                    <td><input class="easyui-datetimebox" name="game_begintime"
                        id="edit_begintime" data-options="required:true,showSeconds:false"
                         style="width: 200px"></td>
                </tr>
                <tr>
                    <td>关门时间：</td>
                    <td><input class="easyui-datetimebox" name="game_endtime"
                        id="edit_endtime" data-options="required:true,showSeconds:false"
                        style="width: 200px"></td>
                </tr>
                <tr>
                    <td>赛事规模:</td>
                    <td><input id="edit_gamenum" type="text" name="game_num"
                        class="easyui-numberbox" value="1000"
                        style="width: 200px; height: 30px;"
                        data-options="min:0,required:true, missingMessage:'请填写赛事规模(单位：人)'">
                    </td>
                </tr>
                <tr>
                <tr>
                    <td>主办单位:</td>
                    <td><input id="edit_gameunit"
                        style="width: 200px; height: 30px;" class="easyui-textbox"
                        type="text" name="game_unit"
                        data-options="required:true, missingMessage:'请填写主办单位'" /></td>
                </tr>
                <tr>
                    <td>比赛地点:</td>
                    <td><input id="edit_gamelocation"
                        style="width: 200px; height: 30px;" class="easyui-textbox"
                        type="text" name="game_location"
                        data-options="required:true, missingMessage:'请填写比赛地点'" /></td>
                </tr>
                <tr>
                    <td>备注:</td>
                    <td><input id="edit_gameremakes"
                        style="width: 200px; height: 30px;" class="easyui-textbox"
                        type="text" name="game_remakes" data-options="required:false" /></td>
                </tr>
			</table>
		</form>
	</div>


</body>
</html>