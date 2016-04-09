<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">班长选择</div>
    <div class="panel-body">
        <div class="row" >
            <c:if test="${not empty editFailureMessage}">
                <div class="col-md-12" id="message">
                    <p class="bg-danger">${editFailureMessage}</p>
                </div>
            </c:if>
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/course/make/leader.action" method="post">
                    <input type="hidden" name="courseId" value="${course.courseId}">
                    <div class="form-group">
                        <label for="inputCourseName" class="col-sm-2 control-label">课程名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputCourseName" value="${course.courseName}" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputCourseMaster" class="col-sm-2 control-label">选择班长</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="inputCourseMaster" name="courseMaster">
                                <c:forEach items="${studentUsers}" var="student">
                                    <option value="${student.stuId}" selected>${student.stuName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">确定班长</button>
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
        setTimeout(function() {
            $("#message").hide();
        }, 2000);
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>