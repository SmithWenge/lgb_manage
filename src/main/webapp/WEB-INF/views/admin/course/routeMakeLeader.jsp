<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<%--课程管理--%>
<style>
    #course{
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 10px;"><a href="${contextPath}/admin/course/routePage.action"><span class="glyphicon glyphicon-map-marker"></span>课程管理</a> > 专业修改</div>
    <div class="panel-body">
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
                        <label for="inputDepartmentId" class="col-sm-2 control-label">选择班长</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="inputDepartmentId" name="departmentId">
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

<%@include file="/WEB-INF/include/footer.jsp"%>