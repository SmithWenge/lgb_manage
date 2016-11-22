<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<%--学生管理--%>
<style>
    #student{
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="panel panel-info" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/excel/routeImport.action">学员导入</a></li>
            <li role="presentation" style="float: right"><a href="${contextPath}/admin/excel/template.action">下载模版</a></li>
        </ul>
    </div>
    <div class="panel-body">
        <form action="${contextPath}/admin/excel/addExcel.action" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="studentFile">添加学员文件</label>
                <input type="file" id="studentFile" name="file">
                <p class="help-block">学员基本信息表</p>
            </div>
            <p><b>请先下载模版,根据模版规则添加对应的学员信息,在导入模版.</b></p>
            <button type="submit" class="btn btn-default">导入</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>