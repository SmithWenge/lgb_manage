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
        <li style="width: 72%;"> <span class="glyphicon glyphicon-user"> </span> ${teacher.teacherName}</li>
        <li style="width: 10%;"><a href="${contextPath}/teacher/score/logout.action"><span class="glyphicon glyphicon-off"></span> 退出</a></li>
    </ul>
</div>

