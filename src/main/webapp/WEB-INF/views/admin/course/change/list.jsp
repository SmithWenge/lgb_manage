<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>收费</div>
    <div class="panel-body">
        <%-- 可以换课的课程 --%>
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" align="center">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>课程名</th>
                        <th>学员卡号</th>
                        <th>学员名</th>
                        <th>选课时间</th>
                        <th>是否缴费</th>
                        <th>真实费用</th>
                        <th>是否开发票</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${couldCourses}" var="course">
                        <tr>
                            <td>${course.courseName}</td>
                            <td>${course.stuCardNum}</td>
                            <td>${course.stuName}</td>
                            <td>${course.signUpTime}</td>
                            <tags:dictd groupValue="tuitionFlag" itemKey="${course.tuitionFlag}" />
                            <td>${course.actualTuition}</td>
                            <tags:dictd groupValue="billFlag" itemKey="${course.billFlag}" />
                            <td>
                                <a href="${contextPath}/admin/course/change/route/turn/${course.studentCourseId}.action" style="text-decoration: none;" >
                                    <button type="button" class="btn btn-info">换课</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <%-- 已换课课程 --%>
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #2aabd2;">
                        <th>学员名</th>
                        <th>学员卡号</th>
                        <th>新课程名</th>
                        <th>原课程名</th>
                        <th>换课时间</th>
                    </tr>
                    <c:forEach items="${hasCourses}" var="course">
                        <tr>
                            <td>${course.stuName}</td>
                            <td>${course.stuCardNum}</td>
                            <td>${course.courseName}</td>
                            <td>${course.oldCourseName}</td>
                            <td>${course.changeTime}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>