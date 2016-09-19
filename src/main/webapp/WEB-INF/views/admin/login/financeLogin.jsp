<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <meta charset="UTF-8">
  <title>老干部大学学籍管理</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
  <%--<meta http-equiv="X-UA-Compatible" content="IE=8" />--%>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="${contextPath}/static/plugins/bootstrap/css/bootstrap.css" />
  <link rel="stylesheet" href="${contextPath}/static/plugins/bootstrap/css/bootstrap-theme.css" />
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
      height: 285px;
      opacity: 0.8;
      padding:10px ;
    }

    .right_div{
      width: 400px;
      padding: 0px 40px;
      margin: 0 auto;
    }
    #inputAdminLoginName,#inputAdminLoginPass{
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
<form action="${contextPath}/admin/login.action" method="post" id="adminLoginForm">
  <div class="content">
    <div><h3 style="text-align: center">财务登录</h3></div>
    <div class="right_div">
      <label for="inputAdminLoginName"><span class="glyphicon glyphicon-user"></span><span>&nbsp;</span><span>&nbsp;</span></label>
      <input type="text" id="inputAdminLoginName" name="adminLoginName" placeholder="admin" /><br />
      <label for="inputAdminLoginPass"><span class="glyphicon glyphicon-lock"></span><span>&nbsp;</span><span>&nbsp;</span></label>
      <input type="password" id="inputAdminLoginPass" name="adminLoginPass" placeholder="密码" /><br />
      <span>&nbsp;</span><button class="btn btn-info" type="submit" id="sub">登录</button>
      <span>&nbsp;</span><button class="btn btn-default" type="button" id="forget" data-toggle="modal" data-target="#resetPass">忘记密码</button>
    </div>
  </div>
</form>

<!-- 重置密码Form -->
<div class="modal fade" id="resetPass" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">重置密码</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" action="${contextPath}/admin/resetPass.action" method="post">
          <div class="form-group">
            <label for="adminLoginName" class="col-sm-3 control-label">管理员登录名</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" id="adminLoginName" placeholder="admin" name="adminLoginName">
            </div>
          </div>
          <div class="form-group">
            <label for="adminEmail" class="col-sm-3 control-label">管理员邮箱</label>
            <div class="col-sm-9">
              <input type="email" class="form-control" id="adminEmail" placeholder="admin@example.com" name="adminEmail">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
              <button type="submit" class="btn btn-default">重置密码</button>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <%--<button type="button" class="btn btn-primary">发送邮件</button>--%>
      </div>
    </div>
  </div>
</div>
<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>