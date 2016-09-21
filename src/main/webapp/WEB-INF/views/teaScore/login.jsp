<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>老干部大学学籍管理</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
  <%--<meta http-equiv="X-UA-Compatible" content="IE=8" />--%>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="${contextPath}/static/plugins/bootstrap/css/bootstrap.css" />
  <link rel="shortcut icon" href="${contextPath}/static/images/lgb.ico" />

  <title>老干部大学学籍管理</title>
  <style>
    body{
      background: url(${contextPath}/static/images/bg.jpg);
      background-position: center top;
      background-repeat: no-repeat;
    }
    .content{
      margin: 200px auto 0;
      width: 420px;
      background: #fff;
      height: 255px;
      opacity: 0.8;
      padding:10px ;
    }

    .right_div{
      width: 400px;
      padding: 16px 40px;
      margin: 0 auto;
    }
    #teacherName,#teacherCardNum{
      width: 270px;
      height: 40px;
      margin-top: 10px;
      opacity: 0.9;
    }
    #sub,#forget{
      width: 270px;
      height: 35px;
      margin: 15px 20px 0;
    }
    #forget{
      margin-top: 10px;
    }
  </style>
</head>


<body>
<form action="${contextPath}/teaScore/login.action" method="post">
  <div class="content">
    <div class="right_div">
      <label for="teacherName">
        <span class="glyphicon glyphicon-user"></span>
        <span>&nbsp;</span>
        <span>&nbsp;</span>
      </label>
      <input type="text" id="teacherName" name="teacherName" placeholder="教师姓名" /><br />
      <label for="teacherCardNum">
        <span class="glyphicon glyphicon-lock"></span>
        <span>&nbsp;</span>
        <span>&nbsp;</span>
      </label>
      <input type="password" id="teacherCardNum" name="teacherCardNum" placeholder="教师卡号" /><br />
      <span>&nbsp;</span>
      <button class="btn btn-info" type="submit" id="sub">登陆</button>
    </div>
  </div>
</form>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>