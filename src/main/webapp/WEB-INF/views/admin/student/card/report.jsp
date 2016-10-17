<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>老干部大学学籍管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <link href="${contextPath}/static/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link rel="shortcut icon" href="${contextPath}/static/images/lgb.ico" />
</head>
<body>
<div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="col-md-12">
        <ul class="nav nav-pills">
            <li role="presentation"><a href="${contextPath}/admin/home/index.action">首页</a></li>
            <li role="presentation" class="active"><a href="${contextPath}/admin/student/count/index.action">考勤</a></li>
            <li class="dropdown" style="float: right;">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${adminLogin.adminLoginName} <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="${contextPath}/admin/routePass.action">修改密码</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="${contextPath}/admin/logout.action">退出登陆</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<script src="${contextPath}/static/plugins/echarts/echarts-2.min.js"></script>
<div class="row" style="margin-top: 5px; margin-right: 2%; margin-left: 2%;" id="contentDetail">
    <div class="panel panel-default col-md-6">
        <div class="panel-heading">按月统计</div>
        <div class="panel-body" id="studentCardReport" style="height: 400px;">
        </div>
    </div>
</div>

<script type="text/javascript" src="${contextPath}/static/plugins/jquery/jquery-1.9.1.min.js" ></script>

<script type="text/javascript">
    // 考勤报表
    var myChart = echarts.init(document.getElementById('studentCardReport'));

    var option = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : [],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'考勤',
                type:'bar',
                barWidth: '30%',
                data:[]
            }
        ]
    };

    myChart.setOption(option);

    $(function () {
        $.ajax({
            type: 'post',
            contentType: 'application/json',
            dataType: 'json',
            url: '${contextPath}/admin/student/count/data.action',
            success: function (result) {
                $.each(result.studentCardReport, function (i, item) {
                    option.xAxis[0].data.push(item.name);
                    option.series[0].data.push(item);
                });

                function eConsole1(param) {
                    if (typeof param.seriesIndex != 'undefined') {
                        $.ajax({
                            type: 'post',
                            contentType: 'application/json',
                            dataType: 'json',
                            url: '${contextPath}/admin/count/detail/studentCardReport.action',
                            data: JSON.stringify({
                                'key': param.name
                            }),
                            success: function (result) {
                                var tableContent = '<table class="table"><tr><td>学生名</td><td>性别</td><td>出生日期</td><td>卡号</td><td>电话1</td><td>电话2</td></tr>';
                                $.each(result.students, function (i, item) {
                                    tableContent += "<tr><td>" + item.stuName + "</td><td>" + item.stuGenderValue + "</td><td>" + item.stuBirthday + "</td><td>" + item.stuCardNum + "</td><td>" + item.stuTelOne + "</td><td>" + item.stuTelTwo + "</td></tr>";
                                });

                                tableContent += "</table>";

                                $('#contentDetail').html(tableContent);
                                $('#removeOne').html('');
                                $('#removeTwo').html('');
                                $('#removeThree').html('');
                            }
                        })
                    }
                }

                myChart.setOption(option);
//                myChart.on('click', eConsole1);
            }
        });
    });
</script>
</body>
</html>