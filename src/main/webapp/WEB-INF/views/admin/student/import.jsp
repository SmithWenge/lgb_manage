<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="panel panel-info" style="margin-left: 2%; margin-right: 2%; margin-top: 5px;">
    <div class="panel-heading">
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
            <button type="submit" class="btn btn-default">导入</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>