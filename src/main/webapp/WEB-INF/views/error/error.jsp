<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>老干部大学学籍管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <%--<meta http-equiv="X-UA-Compatible" content="IE=8" />--%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${contextPath}/static/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <style>
        body{
            background: whitesmoke;
        }
        #errorInfo{
            width: 670px;
            height: 200px;
            margin: 160px auto;
            /*float: left;*/
        }
        img{
            display: inline-block;
            margin: 0px auto;
            margin-bottom: 0px;
            float: left;
            /*margin-left: auto;*/
        }
        span{
            color: #444444;
            font-family: "微软雅黑";
            /*text-align: center;*/
            display: inline-block;
            font-size: 20px;
            line-height: 40px;
            /*vertical-align: bottom;*/
            float: left;
            margin-top: 60px;
        }
        a{
            color: red;
        }
    </style>
</head>
<body>
<div id="errorInfo">
    <img src="${contextPath}/static/images/500.png" />
    <span>很抱歉！服务器内部错误，<br>我们正在调试，请稍后访问.<br>您可以<a href="#">返回上一级</a>或者<a href="#">重新登录</a></span>
</div>
</body>
</html>
