<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>老干部大学学籍管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" />
    <link href="${contextPath}/static/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="col-md-12">
        <ul class="nav nav-pills">
            <li role="presentation" class="active"><a href="#">首页</a></li>
            <li role="presentation"><a href="${contextPath}/admin/user/page.action">用户管理</a></li>
            <li role="presentation"><a href="${contextPath}/admin/department/page.action">系管理</a></li>
            <li role="presentation"><a href="${contextPath}/admin/major/page.action">专业管理</a></li>
            <li role="presentation"><a href="${contextPath}/admin/teacher/routePage.action">教师管理</a></li>
            <li role="presentation"><a href="${contextPath}/admin/course/routePage.action">课程管理</a></li>
            <li role="presentation"><a href="${contextPath}/admin/student/routePage.action">学生管理</a></li>
            <li role="presentation"><a href="${contextPath}/admin/course/leader/page.action">班长</a></li>
            <li role="presentation"><a href="${contextPath}/admin/log/routePage.action">查看日志</a></li>
            <li role="presentation"><a href="${contextPath}/admin/count/index.action">统计</a></li>
            <li role="presentation"><a href="${contextPath}/admin/room/routeRoom.action">教室</a></li>
            <li role="presentation"><a href="${contextPath}/admin/finance/routePage.action">财务</a></li>
            <li role="presentation"><a href="${contextPath}/admin/user/helpRouter.action">帮助</a></li>
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
<script src="${contextPath}/static/plugins/echarts/echarts.min.js"></script>

<div class="row" style="margin-top: 5px; margin-right: 2%; margin-left: 2%;">
    <div class="panel panel-info">
        <div class="panel-heading">
            <ul class="nav nav-pills">
                <li role="presentation" style="float: left;">
                    按月统计
                </li>
                <li role="presentation">
                    <select class="form-control" id="countYear">
                        <option>2016</option>
                        <option>2017</option>
                        <option>2015</option>
                        <option>2018</option>
                        <option>2019</option>
                    </select>
                </li>
            </ul>
        </div>
        <div class="panel-body" id="monthFinance" style="height: 500px;"></div>
    </div>
</div>
<%@include file="/WEB-INF/include/javascript.jsp"%>
<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('monthFinance'));


     var optionMonthFinance = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data:['月收入额']
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
                    data : []
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'月收入额',
                    type:'bar',
                    barWidth : 8,
                    stack: '课程费用',
                    data:[]
                }
            ]
        };

    $(function () {

        /*页面选择的年份的数据传值*/
        $('#countYear').on('change', function () {
            $.ajax({
                type: 'post',
                contentType: 'application/json',
                dataType: 'json',
                url: '${contextPath}/admin/finance/echarts.action',
                data: JSON.stringify({
                    'countFinanceYear': $('#countYear').val()
                }),
                success: function (result) {
                    $.each(result.financeCount, function (i, item) {
                        optionMonthFinance.xAxis[0].data.push(item.name);
                        optionMonthFinance.series[0].data.push(item.value);
                    });

                    console.log(optionMonthFinance);

                    myChart.setOption(optionMonthFinance);
                }
            });
        });

        /*页面默认当前年份的数据传值*/
        $.ajax({
            type: 'post',
            contentType: 'application/json',
            dataType: 'json',
            url: '${contextPath}/admin/finance/echarts.action',
            data: JSON.stringify({
            'countFinanceYear': new Date().getFullYear()
            }),
            success: function (result) {
                $.each(result.financeCount, function (i, item) {
                    optionMonthFinance.xAxis[0].data.push(item.name);
                    optionMonthFinance.series[0].data.push(item.value);
                });

                console.log(optionMonthFinance);

                myChart.setOption(optionMonthFinance);
            }
        });
    })
</script>

<script type="text/javascript" src="${contextPath}/static/plugins/bootstrap/js/bootstrap.js" ></script>
<script type="text/javascript" src="${contextPath}/static/support/jquery.placeholder.js" ></script>
<script type="text/javascript">
    $(function () {
        // Invoke the plugin
        $('input, textarea').placeholder();
    });
</script>
<script type="text/javascript" src="${contextPath}/static/support/html5shiv.min.js" />
<%--<script type="text/javascript" src="${contextPath}/static/support/respond.min.js" />--%>

