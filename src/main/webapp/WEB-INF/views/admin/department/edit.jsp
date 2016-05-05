<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<link href="${contextPath}/static/plugins/jquery-datetimepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${contextPath}/static/plugins/jquery-datetimepicker/jquery.js" ></script>
<script type="text/javascript" src="${contextPath}/static/plugins/jquery-datetimepicker/jquery.datetimepicker.full.js" ></script>
<script type="text/javascript">
    $(function () {
        $('#inputDepartmentStartDate').datetimepicker({
            format:'Y-m-d H:i:s',
            lang:'zh'
        });
        $('#inputDepartmentStopDate').datetimepicker({
            format:'Y-m-d H:i:s',
            lang:'zh'
        });
    });
</script>

<%--系管理>系修改--%>
<style>
    #department{
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="panel panel-default"  style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 10px;"><a href="${contextPath}/admin/department/page.action"><span class="glyphicon glyphicon-map-marker"></span>系管理</a> > 系修改</div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <c:if test="${not empty addFailureMessage}">
                <div class="col-md-12" id="message">
                    <p class="bg-danger">${addFailureMessage}</p>
                </div>
            </c:if>
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/department/edit.action" method="post" id="departmentEditForm">
                    <input type="hidden" name="departmentId" value="${edit.departmentId}">
                    <div class="form-group">
                        <label for="inputDepartmentName" class="col-sm-2 control-label">系名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputDepartmentName" placeholder="艺术系" name="departmentName" value="${edit.departmentName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputDepartmentStartDate" class="col-sm-2 control-label">报名开始时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputDepartmentStartDate" name="departmentStartDate" value="${edit.departmentStartDate}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputDepartmentStopDate" class="col-sm-2 control-label">报名结束时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputDepartmentStopDate" name="departmentStopDate" value="${edit.departmentStopDate}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputAdminId" class="col-sm-2 control-label">系主任</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="inputAdminId" name="adminId">
                                <option value="0">--请选择--</option>
                                <c:forEach items="${departmentAdmins}" var="admin">
                                    <c:if test="${admin.adminId == edit.adminId}">
                                        <option value="${admin.adminId}" selected>${admin.adminName}</option>
                                    </c:if>
                                    <c:if test="${admin.adminId != edit.adminId}">
                                        <option value="${admin.adminId}">${admin.adminName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">编辑系</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%--<%@include file="/WEB-INF/include/javascript.jsp"%>--%>
<script type="text/javascript" src="${contextPath}/static/plugins/jquery-validate/jquery.validate.js" ></script>

<script type="text/javascript">
    $(function () {
        $('#departmentEditForm').validate({
            rules: {
                departmentName: {
                    required: true,
                    minlength: 2,
                    maxlength: 10
                },
                departmentStartDate: {
                    required: true
                },
                departmentStopDate: {
                    required: true
                }
            },
            messages: {
                departmentName: {
                    required: "请填写系名.",
                    minlength: "系名的长度为2到10.",
                    maxlength: "系名的长度为2到10."
                },
                departmentStartDate: {
                    required: "请填写报名开始时间."
                },
                departmentStopDate: {
                    required: "请填写报名截止时间."
                }
            }
        });

        setTimeout(function() {
            $("#message").hide();
        }, 2000);
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>