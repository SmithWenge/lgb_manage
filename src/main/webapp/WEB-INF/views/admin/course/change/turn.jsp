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
                        <th>学员类型</th>
                        <th>学员卡号</th>
                    </tr>
                    <tr>
                        <td>${student.stuName}</td>
                        <tags:dictd groupValue="stuType" itemKey="${student.stuType}" />
                        <td>${student.stuCardNum}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/course/change/turn.action" method="post">
                    <input type="hidden" name="oldCourseId" value="${changeCourse.courseId}" />
                    <input type="hidden" name="studentId" value="${changeCourse.studentId}">
                    <input type="hidden" name="oldCourseTuition" value="${changeCourse.actualTuition}">
                    <input type="hidden" name="studentCourseId" value="${studentCourseId}">
                    <input type="hidden" name="stuType" value="${changeCourse.stuType}">
                    <input type="hidden" name="stuCardNum" value="${student.stuCardNum}">
                    <input >
                    <div class="form-group">
                        <label for="courseId" class="col-sm-2 control-label">新的课程</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="courseId" id="courseId">
                                <c:forEach items="${otherCourses}" var="course">
                                    <option value="${course.courseId}">${course.courseName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="financeFlag" class="col-sm-2 control-label">是否缴费</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="financeFlag" id="financeFlag" value="0"> 不需要费用
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="financeFlag" id="inlineRadio2" value="1"> 需要费用
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-danger">更改课程</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>