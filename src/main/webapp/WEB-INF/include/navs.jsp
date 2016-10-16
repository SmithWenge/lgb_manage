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
        width: 15%;
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
        <li style="width: 16%;">老干部学籍管理</li>
        <li style="width: 6%;"> <span class="glyphicon glyphicon-user"> </span> ${adminLogin.adminLoginName}</li>
        <li style="width: 65%;"><a href="${contextPath}/admin/routePass.action">更改密码</a></li>
        <li style="width: 10%;"><a href="${contextPath}/admin/logout.action"><span class="glyphicon glyphicon-off"></span> 退出</a></li>
    </ul>
</div>

    <div class="nav_main">
        <ul>
            <c:if test="${sessionScope.adminLogin.adminRole != 4}">
                <li role="presentation" class="active"><a id="index_info" href="${contextPath}/admin/home/index.action">首页</a></li>
                <li role="presentation"><a id="user_manage" href="${contextPath}/admin/user/page.action">用户管理</a></li>
                <li role="presentation"><a id="department" href="${contextPath}/admin/department/page.action">系管理</a></li>
                <li role="presentation"><a id="major" href="${contextPath}/admin/major/page.action">专业管理</a></li>
                <li role="presentation"><a id="teacher" href="${contextPath}/admin/teacher/routePage.action">教师管理</a></li>
                <li role="presentation"><a id="course" href="${contextPath}/admin/course/routePage.action">课程管理</a></li>
                <li role="presentation"><a id="courseChange" href="${contextPath}/admin/course/change/route.action">换课</a></li>
                <li role="presentation"><a id="student" href="${contextPath}/admin/student/routePage.action">学生管理</a></li>
                <li role="presentation"><a id="score" href="${contextPath}/admin/score/routerList.action">成绩管理</a></li>
                <li role="presentation"><a id="leader" href="${contextPath}/admin/course/leader/page.action">班长</a></li>
                <li role="presentation"><a id="log" href="${contextPath}/admin/log/routePage.action">查看日志</a></li>
                <li role="presentation"><a id="count" href="${contextPath}/admin/count/index.action">统计</a></li>
                <li role="presentation"><a id="card" href="${contextPath}/admin/student/count/index.action">考勤</a></li>
                <li role="presentation"><a id="room" href="${contextPath}/admin/room/routeRoom.action">教室</a></li>
                <li role="presentation"><a id="signUp" href="${contextPath}/admin/offline/sign.action">线下报名</a></li>
                <li role="presentation"><a id="setting" href="${contextPath}/admin/setting/route.action">颜色配置</a></li>
                <li role="presentation"><a id="basic" href="${contextPath}/admin/setting/routeBasic.action">基本配置</a></li>
            </c:if>

            <c:if test="${sessionScope.adminLogin.adminRole != 4}">
                <li role="presentation"><a href="${contextPath}/admin/user/helpRouter.action">帮助</a></li>
            </c:if>
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
