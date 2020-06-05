<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>公告列表</title>
<link rel="stylesheet" type="text/css"
	href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../easyui/js/validateExtends.js"></script>
<script type="text/javascript" src="../echarts/echarts.min.js"></script>
</head>
<body>
<div class="easyui-accordion" style="width:960px;height:600px;">
    <div title="统计分析" iconCls="icon-chart-curve" style="overflow:auto;padding:10px;">
         <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
        <div id="charts-div" style="width: 800px;height:500px;"></div>
    </div>
</div>
	<script type="text/javascript">
	$(document).ready(function(){
	    getdata();
	});
	function getdata() {
		$.ajax({
	        type : "post",
	        url : "get_data",
	        dataType : 'json',
	        success : function(data) {
	        	 // 基于准备好的dom，初始化echarts实例
	            var myChart = echarts.init(document.getElementById('charts-div'));

	            // 指定图表的配置项和数据
	            var option = {
	                title: {
	                    text: '赛事类型统计'
	                },
	                tooltip: {},
	                legend: {
	                    data:['赛事数目']
	                },
	                xAxis: {
	                    data: ["全程","半程","十公里"]
	                },
	                yAxis: {},
	                series: [{
	                    name: '赛事数目',
	                    type: 'bar',
	                    data: data.valueList
	                }]
	            };

	            // 使用刚指定的配置项和数据显示图表。
	            myChart.setOption(option);
	        }
	    });
	}
	
       
    </script>

</body>
</html>