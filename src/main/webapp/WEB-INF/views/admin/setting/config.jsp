<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<div class="row" style="margin-left: 0.1%; float: left;width: 85%;">
    <div class="panel panel-default" style="float: left; width: 100%;">
        <div class="panel-heading" style="height: 45px;padding-top: 5px;">
            <a href="${contextPath}/admin/student/page.action"><span class="glyphicon glyphicon-map-marker"></span>背景色配置</a>
        </div>
        <div class="panel-body">
            <dl class="dl-horizontal">
                <dt>当前配置的颜色</dt>
                <dd>
                    <div style="width: 85%; height: 102px; background-color: #${color}; border: 1px solid black"></div>
                </dd>
                <form action="${contextPath}/admin/setting/config.action" method="post" style="margin-top: 50px">
                    <dt>
                        第一种背景色
                        <input type="radio" name="configColor" value="FFFAE9" checked>
                    </dt>
                    <dd>
                        <div style="width: 85%; height: 102px; background-color: #FFFAE9; border: 1px solid black"></div>
                    </dd>
                    <dt>
                        第二种背景色
                        <input type="radio" name="configColor" value="FFFCF4">
                    </dt>
                    <dd  style="margin-top: 5px;">
                        <div style="width: 85%; height: 102px; background-color: #FFFCF4; border: 1px solid black"></div>
                    </dd>
                    <dt>
                        第三种背景色
                        <input type="radio" name="configColor" value="FFFECE">
                    </dt>
                    <dd  style="margin-top: 5px;">
                        <div style="width: 85%; height: 102px; background-color: #FFFECE; border: 1px solid black"></div>
                    </dd>
                    <dt>
                        <button type="submit" class="btn btn-success">修改背景色</button>
                    </dt>
                </form>
            </dl>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>