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
<p class="panel panel-default col-md-12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp学员总数：${infoCount.numOfStudent}人</p>
<p class="panel panel-default col-md-12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp教师总数：${infoCount.numOfTeacher}人</p>
<p class="panel panel-default col-md-12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp课程总数：${infoCount.numOfCourse}门</p>

<script src="${contextPath}/static/plugins/echarts/echarts.min.js"></script>
<div class="row" style="margin-top: 1%; margin-right: 2%; margin-left: 2%;">
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
<div class="row" style="margin-top: 1%; margin-right: 2%; margin-left: 2%;">
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
<div class="row" style="margin-top: 1%; margin-right: 2%; margin-left: 2%;">
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

                myChart.setOption(optionYearStuEduStart);
//                var ecConfig = require('echarts/config');
                function eConsole(param) {
                    var mes = '【' + param.type + '】';
                    if (typeof param.seriesIndex != 'undefined') {
                        mes += '  seriesIndex : ' + param.seriesIndex;
                        mes += '  dataIndex : ' + param.dataIndex;
                    }
                    if (param.type == 'click') {
//                        document.getElementById('hover-console').innerHTML = 'Event Console : ' + mes;
                    }
                    else {
//                        document.getElementById('console').innerHTML = mes;
                    }
                    console.log(param);
                }
                myChart.on('click', eConsole);
                myChart2.setOption(optionStuGender);
                myChart3.setOption(optionStuEducational);
                myChart4.setOption(optionStuOldWorkPlaceType);
                myChart5.setOption(optionStuOldWorkType);
                myChart6.setOption(optionStuPolitical);
                myChart7.setOption(optionStuPreferential);
                myChart8.setOption(optionStuType);
                myChart9.setOption(optionYearOfStuBirthday);
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