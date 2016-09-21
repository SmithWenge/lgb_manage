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
            width: 700px;
            height: 250px;
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
            margin-top: 44px;
        }
        b{
            color: red;
        }
    </style>
</head>
<body>
<div id="errorInfo">
    <img src="${contextPath}/static/images/404.png" />
    <span>很抱歉！您访问的页面不存在。<br>可能原因：网络信号弱；<br>输入网址不正确。<br>您可以<b>检验网址</b>输入是否正确或者<b>稍后再试试.</b></span>
</div>
</body>
</html>
