<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<div class="row" style="margin-left: 0.1%; float: left;width: 85%;">
    <div class="panel panel-default" style="float: left; width: 100%;">
        <div class="panel-heading" style="height: 45px;padding-top: 5px;">
            <a href="${contextPath}/admin/student/page.action"><span class="glyphicon glyphicon-map-marker"></span>基本配置</a>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-bordered">
                        <tr>
                            <td>课程限制数目</td>
                            <td>${config.studentCourseLimit}</td>
                        </tr>
                        <tr>
                            <td>缴费优惠信息</td>
                            <td>${config.financeMessage}</td>
                        </tr>
                        <tr>
                            <td>退款规则提示信息</td>
                            <td>${config.refundMessage}</td>
                        </tr>
                    </table>
                </div>
            </div>
            <form class="form-horizontal" id="basicConfigId" action="${contextPath}/admin/setting/basicConfig.action" method="post">
                <div class="form-group">
                    <label for="studentCourseLimit" class="col-sm-2 control-label">课程限制数目</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="studentCourseLimit" name="studentCourseLimit" value="${config.studentCourseLimit}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="financeMessage" class="col-sm-2 control-label">缴费优惠信息</label>
                    <div class="col-sm-10">
                        <textarea rows="3" class="form-control" id="financeMessage" name="financeMessage">${config.financeMessage}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="refundMessage" class="col-sm-2 control-label">退款规则提示信息</label>
                    <div class="col-sm-10">
                        <textarea rows="3" class="form-control" id="refundMessage" name="refundMessage">${config.refundMessage}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-warning">更换配置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>