<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/header.jsp"%>

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