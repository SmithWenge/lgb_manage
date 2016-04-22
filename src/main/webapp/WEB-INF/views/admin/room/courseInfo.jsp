<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<link href="${contextPath}/static/plugins/bootstrap/css/docs.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/plugins/webui-popover/jquery.webui-popover.min.css">

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">详细信息</div>
    <div class="panel-body">
        <table class="table table-bordered table-striped responsive-utilities">
            <tr>
                <th>课程名</th>
                <th>系名</th>
                <th>专业名</th>
                <th>课程号</th>
                <th>学费</th>
                <th>学制</th>
                <th>计划招生人数</th>
                <th>课程人数</th>
                <th>毕业限制人数</th>
                <th>教师1</th>
                <th>教师2</th>
                <th>系主任</th>
            </tr>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.courseName}</td>
                    <td>${course.departmentName}</td>
                    <td>${course.majorName}</td>
                    <td>${course.courseNum}</td>
                    <td>${course.courseTuition}</td>
                    <td>${course.courseYears}</td>
                    <td>${course.courseEnrollmentNum}</td>
                    <td>${course.courseStuNum}</td>
                    <td>${course.courseGraLimitNum}</td>
                    <td>${course.teacherOneName}</td>
                    <td>${course.teacherTwoName}</td>
                    <td>${course.adminName}</td>
                </tr>
                <tr>
                    <td>上课时间</td>
                    <td colspan="11">
                        <c:forEach items="${course.times}" var="time">
                            <tags:diclabel groupValue="courseRoom" itemKey="${time.courseRoom}" />
                            <tags:diclabel groupValue="timeWeek" itemKey="${time.timeWeek}"/>
                            <tags:diclabel groupValue="timeSpecific" itemKey="${time.timeSpecificInt}"/>,
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>