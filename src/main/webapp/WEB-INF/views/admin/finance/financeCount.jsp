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
<br>
<p class="panel panel-default col-md-12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp累计总收入：${infoCount.sumActualTuition}元</p>
<p class="panel panel-default col-md-12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp今日累计总收入：${infoCount.daySumActualTuition}元</p>
<script src="${contextPath}/static/plugins/echarts/echarts.min.js"></script>
<div class="row" style="margin-top: 1%; margin-right: 2%; margin-left: 2%;">
    <div class="panel panel-default col-md-6">
        <div class="panel-body" id="monthFinance" style="height: 500px;">
        </div>
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
                    data : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
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
                    stack: '搜索引擎',
                    data:[]
                }
            ]
        };

    $(function () {
            $.each(${list},function (i, item) {
                optionMonthFinance.series[0].data.push(item.value);
            });
            myChart.setOption(optionMonthFinance);
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

