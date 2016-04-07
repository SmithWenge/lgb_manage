<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">专业修改</div>
    <div class="panel-body">
        <div class="row" >
            <c:if test="${not empty addFailureMessage}">
                <div class="col-md-12" id="message">
                    <p class="bg-danger">${addFailureMessage}</p>
                </div>
            </c:if>
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/major/edit.action" method="post" id="majorEditForm">
                    <input type="hidden" name="majorId" value="${edit.majorId}">
                    <div class="form-group">
                        <label for="inputDepartmentId" class="col-sm-2 control-label">选择系</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="inputDepartmentId" name="departmentId">
                                <c:forEach items="${departments}" var="department">
                                    <c:if test="${edit.departmentId == department.departmentId}">
                                        <option value="${department.departmentId}" selected>${department.departmentName}</option>
                                    </c:if>
                                    <c:if test="${edit.departmentId != department.departmentId}">
                                        <option value="${department.departmentId}">${department.departmentName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputMajorName" class="col-sm-2 control-label">专业名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputMajorName" placeholder="新专业" name="majorName" value="${edit.majorName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">编辑专业</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        $('#majorEditForm').validate({
            rules: {
                majorName: {
                    required: true,
                    minlength: 2,
                    maxlength: 10
                }
            },
            messages: {
                majorName: {
                    required: "请填写专业名.",
                    minlength: "专业名的长度为2到10.",
                    maxlength: "专业名的长度为2到10."
                }
            }
        });

        setTimeout(function() {
            $("#message").hide();
        }, 2000);
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>