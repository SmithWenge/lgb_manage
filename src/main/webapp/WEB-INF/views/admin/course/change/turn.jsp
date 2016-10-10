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
                        <th>系</th>
                        <th>专业</th>
                        <th>课程名</th>
                        <th>课程年制</th>
                        <th>教室</th>
                        <th>教师1</th>
                        <th>教师2</th>
                        <th>报名限制人数</th>
                        <th>毕业人数限制</th>
                        <th>计划招生人数</th>
                        <th>实际招生人数</th>
                        <th>课程学费</th>
                        <th>实际缴费</th>
                        <th>课程ID</th>
                    </tr>
                    <tr>
                        <td>${changeCourse.departmentName}</td>
                        <td>${changeCourse.majorName}</td>
                        <td>${changeCourse.courseName}</td>
                        <td>${changeCourse.courseYears}</td>
                        <tags:dictd groupValue="courseRoom" itemKey="${changeCourse.courseRoom}" />
                        <td>${changeCourse.teacherOneName}</td>
                        <td>${changeCourse.teacherTwoName}</td>
                        <td>${changeCourse.courseLimitNum}</td>
                        <td>${changeCourse.courseGraLimitNum}</td>
                        <td>${changeCourse.courseEnrollmentNum}</td>
                        <td>${changeCourse.courseStuNum}</td>
                        <td>${changeCourse.courseTuition}</td>
                        <td>${changeCourse.actualTuition}</td>
                        <td>${changeCourse.courseId}</td>
                    </tr>
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