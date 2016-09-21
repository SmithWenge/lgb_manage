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
            <li role="presentation"><a href="${contextPath}/admin/home/index.action">首页</a></li>
            <%--<li role="presentation"><a href="${contextPath}/admin/user/page.action">用户管理</a></li>--%>
            <%--<li role="presentation"><a href="${contextPath}/admin/department/page.action">系管理</a></li>--%>
            <%--<li role="presentation"><a href="${contextPath}/admin/major/page.action">专业管理</a></li>--%>
            <%--<li role="presentation"><a href="${contextPath}/admin/teacher/routePage.action">教师管理</a></li>--%>
            <%--<li role="presentation"><a href="${contextPath}/admin/course/routePage.action">课程管理</a></li>--%>
            <%--<li role="presentation"><a href="${contextPath}/admin/student/routePage.action">学生管理</a></li>--%>
            <%--<li role="presentation"><a href="${contextPath}/admin/course/leader/page.action">班长</a></li>--%>
            <%--<li role="presentation"><a href="${contextPath}/admin/log/routePage.action">查看日志</a></li>--%>
            <li role="presentation" class="active"><a href="${contextPath}/admin/count/index.action">统计</a></li>
            <%--<li role="presentation"><a href="${contextPath}/admin/room/routeRoom.action">教室</a></li>--%>
            <%--<li role="presentation"><a href="${contextPath}/admin/finance/routePage.action">财务</a></li>--%>
            <%--<li role="presentation"><a href="${contextPath}/admin/user/helpRouter.action">帮助</a></li>--%>
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
<div class="row" style="margin-top: 1%; margin-right: 2%; margin-left: 2%;">
    <div class="col-md-4 alert alert-success" role="alert">学员总数：${infoCount.numOfStudent}人</div>
    <div class="col-md-4 alert alert-success" role="alert">教师总数：${infoCount.numOfTeacher}人</div>
    <div class="col-md-4 alert alert-success" role="alert">课程总数：${infoCount.numOfCourse}门</div>
</div>
<script src="${contextPath}/static/plugins/echarts/echarts.min.js"></script>
<div class="row" style="margin-top: 5px; margin-right: 2%; margin-left: 2%;" id="contentDetail">
    <div class="panel panel-default col-md-4">
        <div class="panel-body" id="yearStuEduStart" style="height: 400px;">
        </div>
    </div>
    <div class="panel panel-default col-md-4">
        <div class="panel-body" id="stuGender" style="height: 400px;">
        </div>
    </div>
    <div class="panel panel-default col-md-4">
        <div class="panel-body" id="stuEducational" style="height: 400px;">
        </div>
    </div>
</div>
<div class="row" style="margin-top: 1%; margin-right: 2%; margin-left: 2%;" id="removeOne">
    <div class="panel panel-default col-md-4">
        <div class="panel-body" id="stuOldWorkPlaceType" style="height: 400px;">
        </div>
    </div>
    <div class="panel panel-default col-md-4">
        <div class="panel-body" id="stuOldWorkType" style="height: 400px;">
        </div>
    </div>
    <div class="panel panel-default col-md-4">
        <div class="panel-body" id="stuPolitical" style="height: 400px;">
        </div>
    </div>
</div>
<div class="row" style="margin-top: 1%; margin-right: 2%; margin-left: 2%;" id="removeTwo">
    <div class="panel panel-default col-md-4">
        <div class="panel-body" id="stuPreferential" style="height: 400px;">
        </div>
    </div>
    <div class="panel panel-default col-md-4">
        <div class="panel-body" id="stuType" style="height: 400px;">
        </div>
    </div>
    <div class="panel panel-default col-md-4">
        <div class="panel-body" id="yearOfStuBirthday" style="height: 400px;">
        </div>
    </div>
</div>

<%--<script type="text/javascript" src="${contextPath}/static/plugins/json2/json2.js" />--%>
<%@include file="/WEB-INF/include/javascript.jsp"%>

<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('yearStuEduStart'));
    var myChart2 = echarts.init(document.getElementById('stuGender'));
    var myChart3 = echarts.init(document.getElementById('stuEducational'));
    var myChart4 = echarts.init(document.getElementById('stuOldWorkPlaceType'));
    var myChart5 = echarts.init(document.getElementById('stuOldWorkType'));
    var myChart6 = echarts.init(document.getElementById('stuPolitical'));
    var myChart7 = echarts.init(document.getElementById('stuPreferential'));
    var myChart8 = echarts.init(document.getElementById('stuType'));
    var myChart9 = echarts.init(document.getElementById('yearOfStuBirthday'));

    var optionYearStuEduStart = {
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

    var optionStuGender = {
        title : {
            text: '学员性别',
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

    var optionStuEducational = {
        title : {
            text: '学员文化程度',
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

    var optionStuOldWorkPlaceType = {
        title : {
            text: '学员原单位类型',
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

    var optionStuOldWorkType = {
        title : {
            text: '学员原职务',
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

    var optionStuPolitical = {
        title : {
            text: '学员政治面貌',
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

    var optionStuPreferential = {
        title : {
            text: '学员优惠折扣',
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

    var optionStuType = {
        title : {
            text: '学员类别',
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

    var optionYearOfStuBirthday = {
        title : {
            text: '学员出生日期',
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


    $(function () {
        $.ajax({
            type: 'post',
            contentType: 'application/json',
            dataType: 'json',
            url: '${contextPath}/admin/count/all.action',
            success: function (result) {
                $.each(result.yearStuEduStart, function (i, item) {
                    optionYearStuEduStart.legend.data.push(item.name);
                    optionYearStuEduStart.series[0].data.push(item);
                });

                $.each(result.stuGender, function (i, item) {
                    optionStuGender.legend.data.push(item.name);
                    optionStuGender.series[0].data.push(item);
                });

                $.each(result.stuEducational, function (i, item) {
                    optionStuEducational.legend.data.push(item.name);
                    optionStuEducational.series[0].data.push(item);
                });

                $.each(result.stuOldWorkPlaceType, function (i, item) {
                    optionStuOldWorkPlaceType.legend.data.push(item.name);
                    optionStuOldWorkPlaceType.series[0].data.push(item);
                });

                $.each(result.stuOldWorkType, function (i, item) {
                    optionStuOldWorkType.legend.data.push(item.name);
                    optionStuOldWorkType.series[0].data.push(item);
                });

                $.each(result.stuPolitical, function (i, item) {
                    optionStuPolitical.legend.data.push(item.name);
                    optionStuPolitical.series[0].data.push(item);
                });

                $.each(result.stuPreferential, function (i, item) {
                    optionStuPreferential.legend.data.push(item.name);
                    optionStuPreferential.series[0].data.push(item);
                });

                $.each(result.stuType, function (i, item) {
                    optionStuType.legend.data.push(item.name);
                    optionStuType.series[0].data.push(item);
                });

                $.each(result.yearOfStuBirthday, function (i, item) {
                    optionYearOfStuBirthday.legend.data.push(item.name);
                    optionYearOfStuBirthday.series[0].data.push(item);
                });

                function eConsole1(param) {
                    if (typeof param.seriesIndex != 'undefined') {
                        $.ajax({
                            type: 'post',
                            contentType: 'application/json',
                            dataType: 'json',
                            url: '${contextPath}/admin/count/detail/yearStuEduStart.action',
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
                                console.log(result);
                            }
                        })
                    }
                }

                function eConsole2(param) {
                    if (typeof param.seriesIndex != 'undefined') {
                        $.ajax({
                            type: 'post',
                            contentType: 'application/json',
                            dataType: 'json',
                            url: '${contextPath}/admin/count/detail/stuGender.action',
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
                                console.log(result);
                            }
                        })
                    }
                }

                function eConsole3(param) {
                    if (typeof param.seriesIndex != 'undefined') {
                        $.ajax({
                            type: 'post',
                            contentType: 'application/json',
                            dataType: 'json',
                            url: '${contextPath}/admin/count/detail/stuEducational.action',
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
                                console.log(result);
                            }
                        })
                    }
                }

                function eConsole4(param) {
                    if (typeof param.seriesIndex != 'undefined') {
                        $.ajax({
                            type: 'post',
                            contentType: 'application/json',
                            dataType: 'json',
                            url: '${contextPath}/admin/count/detail/stuOldWorkPlaceType.action',
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
                                console.log(result);
                            }
                        })
                    }
                }

                function eConsole5(param) {
                    if (typeof param.seriesIndex != 'undefined') {
                        $.ajax({
                            type: 'post',
                            contentType: 'application/json',
                            dataType: 'json',
                            url: '${contextPath}/admin/count/detail/stuOldWorkType.action',
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
                                console.log(result);
                            }
                        })
                    }
                }

                function eConsole6(param) {
                    if (typeof param.seriesIndex != 'undefined') {
                        $.ajax({
                            type: 'post',
                            contentType: 'application/json',
                            dataType: 'json',
                            url: '${contextPath}/admin/count/detail/stuPolitical.action',
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
                                console.log(result);
                            }
                        })
                    }
                }

                function eConsole7(param) {
                    if (typeof param.seriesIndex != 'undefined') {
                        $.ajax({
                            type: 'post',
                            contentType: 'application/json',
                            dataType: 'json',
                            url: '${contextPath}/admin/count/detail/stuPreferential.action',
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
                                console.log(result);
                            }
                        })
                    }
                }

                function eConsole8(param) {
                    if (typeof param.seriesIndex != 'undefined') {
                        $.ajax({
                            type: 'post',
                            contentType: 'application/json',
                            dataType: 'json',
                            url: '${contextPath}/admin/count/detail/stuType.action',
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
                                console.log(result);
                            }
                        })
                    }
                }

                function eConsole9(param) {
                    if (typeof param.seriesIndex != 'undefined') {
                        $.ajax({
                            type: 'post',
                            contentType: 'application/json',
                            dataType: 'json',
                            url: '${contextPath}/admin/count/detail/yearStuBirthday.action',
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
                                console.log(result);
                            }
                        })
                    }
                }

                myChart.setOption(optionYearStuEduStart);
                myChart.on('click', eConsole1);
                myChart2.setOption(optionStuGender);
                myChart2.on('click', eConsole2);
                myChart3.setOption(optionStuEducational);
                myChart3.on('click', eConsole3);
                myChart4.setOption(optionStuOldWorkPlaceType);
                myChart4.on('click', eConsole4);
                myChart5.setOption(optionStuOldWorkType);
                myChart5.on('click', eConsole5);
                myChart6.setOption(optionStuPolitical);
                myChart6.on('click', eConsole6);
                myChart7.setOption(optionStuPreferential);
                myChart7.on('click', eConsole7);
                myChart8.setOption(optionStuType);
                myChart8.on('click', eConsole8);
                myChart9.setOption(optionYearOfStuBirthday);
                myChart9.on('click', eConsole9);
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

<%@include file="/WEB-INF/include/footer.jsp"%>