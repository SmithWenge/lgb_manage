<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/header.jsp"%>
<style>
    .header ul{
        padding-left: 0;
        margin-bottom: 0;
        background: #444444;
        height: 55px;
    }
    .header ul li {
        float: left;
        height: 55px;
        line-height: 55px;
        color: whitesmoke;
        list-style: none;
    }
    .header ul li a{
        color: white;
    }

    .nav_main {
        width: 10%;
        background: #3767b1;
        padding: 0;
        float:left;;
    }
    .nav_main ul{
        padding-left: 0;
    }
    .nav_main li {
        list-style: none;
        height: 45px;
        line-height: 45px;
        text-align: center;
    }

    .nav_main ul li a {
        color: whitesmoke;
        height: 45px;
        line-height: 45px;
        display: block;


    }

    .nav_main ul li a:hover {
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="header">
    <ul>
        <li style="width: 2%;"></li>
        <li style="width: 10%;">老干部学籍管理</li>
        <li style="width: 6%;"> <span class="glyphicon glyphicon-user"> </span> ${adminLogin.adminLoginName}</li>
        <li style="width: 75%;"><a href="${contextPath}/admin/routePass.action">更改密码</a></li>
        <li style="width: 5%;"><a href="${contextPath}/admin/logout.action"><span class="glyphicon glyphicon-off"></span> 退出</a></li>
    </ul>
</div>

    <div class="nav_main">
        <ul>
            <c:if test="${sessionScope.adminLogin.adminRole != 4}">
                <li role="presentation">
                    <a id="index_info" href="${contextPath}/admin/home/index.action">
                        <i class="fa fa-home fa-fw" aria-hidden="true"></i>
                        首页
                    </a>
                </li>
                <li role="presentation">
                    <a id="department" href="${contextPath}/admin/department/page.action">
                        <i class="fa fa-cubes fa-fw" aria-hidden="true"></i>
                        系管理
                    </a>
                </li>
                <li role="presentation">
                    <a id="major" href="${contextPath}/admin/major/page.action">
                        <i class="fa fa-cube fa-fw" aria-hidden="true"></i>
                        专业管理
                    </a>
                </li>
                <li role="presentation">
                    <a id="teacher" href="${contextPath}/admin/teacher/routePage.action">
                        <i class="fa fa-user-o fa-fw" aria-hidden="true"></i>
                        教师管理
                    </a>
                </li>
                <li role="presentation">
                    <a id="course" href="${contextPath}/admin/course/routePage.action">
                        <i class="fa fa-book fa-fw" aria-hidden="true"></i>
                        课程管理
                    </a>
                </li>
                <li role="presentation">
                    <a id="student" href="${contextPath}/admin/student/routePage.action">
                        <i class="fa fa-graduation-cap fa-fw" aria-hidden="true"></i>
                        学生管理
                    </a>
                </li>
                <li role="presentation">
                    <a id="score" href="${contextPath}/admin/score/routerList.action">
                        <i class="fa fa-user fa-fw" aria-hidden="true"></i>
                        成绩管理
                    </a>
                </li>
                <li role="presentation">
                    <a id="leader" href="${contextPath}/admin/course/leader/page.action">
                        <i class="fa fa-user fa-fw" aria-hidden="true"></i>
                        班长
                    </a>
                </li>
                <li role="presentation">
                    <a id="courseChange" href="${contextPath}/admin/course/change/route.action">
                        <i class="fa fa-arrows-h fa-fw" aria-hidden="true"></i>
                        换课
                    </a>
                </li>
                <li role="presentation">
                    <a id="count" href="${contextPath}/admin/count/index.action">
                        <i class="fa fa-pie-chart fa-fw" aria-hidden="true"></i>
                        统计
                    </a>
                </li>
                <li role="presentation">
                    <a id="card" href="${contextPath}/admin/student/count/index.action">
                        <i class="fa fa-bar-chart fa-fw" aria-hidden="true"></i>
                        考勤
                    </a>
                </li>
                <li role="presentation">
                    <a id="room" href="${contextPath}/admin/room/routeRoom.action">
                        <i class="fa fa-address-card fa-fw" aria-hidden="true"></i>
                        教室
                    </a>
                </li>
                <li role="presentation">
                    <a id="signUp" href="${contextPath}/admin/offline/sign.action">
                        <i class="fa fa-inbox fa-fw" aria-hidden="true"></i>
                        线下报名
                    </a>
                </li>
                <li role="presentation">
                    <a id="setting" href="${contextPath}/admin/setting/route.action">
                        <i class="fa fa-cog fa-fw" aria-hidden="true"></i>
                        颜色配置
                    </a>
                </li>
                <li role="presentation">
                    <a id="basic" href="${contextPath}/admin/setting/routeBasic.action">
                        <i class="fa fa-cogs fa-fw" aria-hidden="true"></i>
                        基本配置
                    </a>
                </li>
                <li role="presentation">
                    <a id="log" href="${contextPath}/admin/log/routePage.action">
                        <i class="fa fa-bars fa-fw" aria-hidden="true"></i>
                        查看日志
                    </a>
                </li>
                <li role="presentation">
                    <a id="user_manage" href="${contextPath}/admin/user/page.action">
                        <i class="fa fa-users fa-fw" aria-hidden="true"></i>
                        用户管理
                    </a>
                </li>
                <li role="presentation">
                    <a href="${contextPath}/admin/user/helpRouter.action">
                        <i class="fa fa-question-circle fa-fw" aria-hidden="true"></i>
                        帮助
                    </a>
                </li>
            </c:if>

            <%--<c:if test="${sessionScope.adminLogin.adminRole != 4}">--%>
                <%--<li role="presentation"><a href="${contextPath}/admin/user/helpRouter.action">帮助</a></li>--%>
            <%--</c:if>--%>
            <c:if test="${sessionScope.adminLogin.adminRole == 4}">
                <li role="presentation"><a id="finance" href="${contextPath}/admin/finance/routePage.action">收费</a></li>
                <li role="presentation"><a href="${contextPath}/admin/finance/unpayment.action">未缴费查询</a></li>
                <li role="presentation"><a href="${contextPath}/admin/finance/payment.action">已缴费查询</a></li>
                <li role="presentation"><a href="${contextPath}/admin/finance/query/all.action">缴费信息</a></li>
                <li role="presentation"><a href="${contextPath}/admin/finance/refund/route.action">退款</a></li>
                <li role="presentation"><a href="${contextPath}/admin/finance/course/change/list.action">换课费用</a></li>
            </c:if>
            <input type="hidden" id="roleId" value="${sessionScope.adminLogin.adminRole}">
        </ul>
    </div>

<%@include file="/WEB-INF/include/javascript.jsp"%>
<script type="text/javascript">
    $(function () {
//        var $roleId = $('#roleId').val();
//        if($roleId != 4) {
//            $('#financePre').click(function(){
//                return confirm("是否确认退出当前登录，前往财务登录");
//            });
//        }
    });
</script>
