<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="${contextPath}/static/plugins/echarts/echarts.min.js"></script>
    <link href="${contextPath}/static/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="col-md-12">
        <ul class="nav nav-pills">
            <li role="presentation" class="active"><a href="#">首页</a></li>
            <li role="presentation"><a href="${contextPath}/admin/user/page.action">用户管理</a></li>
            <li role="presentation"><a href="${contextPath}/admin/student/page.action">学员管理</a></li>
            <li role="presentation"><a href="${contextPath}/admin/department/page.action">系管理</a></li>
            <li role="presentation"><a href="${contextPath}/admin/major/page.action">专业管理</a></li>
            <li role="presentation"><a href="${contextPath}/admin/teacher/routePage.action">教师管理</a></li>
            <li role="presentation"><a href="${contextPath}/admin/course/routePage.action">课程管理</a></li>
            <li role="presentation"><a href="${contextPath}/admin/student/routePage.action">学生管理</a></li>
            <li role="presentation"><a href="${contextPath}/admin/course/leader/page.action">班长</a></li>
            <li role="presentation"><a href="${contextPath}/admin/log/routePage.action">查看日志</a></li>
            <li role="presentation"><a href="${contextPath}/admin/count/index.action">统计</a></li>
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
<div class="row" style="margin-top: 1%; margin-right: 2%; margin-left: 2%;">
    <div class="panel panel-default col-md-4">
        <div class="panel-body" id="yearStuEduStart" style="height: 400px;">
        </div>
    </div>
</div>

<%--<script type="text/javascript" src="${contextPath}/static/plugins/json2/json2.js" />--%>
<%@include file="/WEB-INF/include/javascript.jsp"%>

<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('yearStuEduStart'));

    var option = {
        title : {
            text: '学员入学时间',
            subtext: '对比',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: []
        },
        series : [
            {
                name: '人员比例',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    $(function () {
        $.ajax({
            type: 'post',
            contentType: 'application/json',
            dataType: 'json',
            url: '${contextPath}/admin/count/all.action',
            success: function (result) {
                $.each(result.yearStuEduStart, function (i, item) {
                    option.legend.data.push(item.name);
                    option.series[0].data.push(item);
                });

                myChart.setOption(option);
            }
        });
    })
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>