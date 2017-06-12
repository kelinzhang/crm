<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">

<%@include file="common.jsp" %>
<script type="text/javascript" src="../../js/views/index.js"></script>
<script type="text/javascript" src="../../js/views/highcharts.js"></script>
<link media="all" href="../../css/index.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
$(function () {
    $('#container').highcharts({
        title: {
            text: '销售报表',
            x: -20 //center
        },
        subtitle: {
            text: '按销售人员分组',
            x: -20
        },
        xAxis: {
            categories: ['Jan', 'Feb']
        },
        yAxis: {
            title: {
                text: 'Temperature (°C)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '°C'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'Tokyo',
            data: [7.0, 6.9]
        }, {
            name: 'New York',
            data: [-0.2, 0.8]
        }, {
            name: 'Berlin',
            data: [-0.9, 0.6]
        }, {
            name: 'London',
            data: [3.9, 4.2]
        }]
    });
});
		</script>
</head>
<body>
 <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
    
   
</body>
</html>
