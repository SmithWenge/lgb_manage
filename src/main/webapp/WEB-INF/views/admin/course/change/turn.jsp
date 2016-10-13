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
                        <td>${changeCourse.courseTuition}</td>
                        <td>${changeCourse.actualTuition}</td>
                        <td>${changeCourse.courseId}</td>
                    </tr>
                </table>
                <table class="table" align="center">
                    <tr>
                        <th>报名限制人数</th>
                        <th>毕业人数限制</th>
                        <th>计划招生人数</th>
                        <th>实际招生人数</th>
                        <th>上课时间</th>
                    </tr>
                    <tr>
                        <td>${changeCourse.courseLimitNum}</td>
                        <td>${changeCourse.courseGraLimitNum}</td>
                        <td>${changeCourse.courseEnrollmentNum}</td>
                        <td>${changeCourse.courseStuNum}</td>
                        <td>
                            <c:forEach items="${changeCourse.courseTimes}" var="time">
                                <tags:diclabel groupValue="timeWeek" itemKey="${time.timeWeek}"/>
                                <tags:diclabel groupValue="timeSpecific" itemKey="${time.timeSpecificInt}"/>,
                            </c:forEach>
                        </td>
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
                    <input type="hidden" name="studentId" value="${student.studentId}">
                    <input type="hidden" name="oldCourseActualTuition" value="${changeCourse.actualTuition}">
                    <input type="hidden" name="studentCourseId" value="${studentCourseId}">
                    <input type="hidden" name="stuType" value="${student.stuType}">
                    <input type="hidden" name="stuCardNum" value="${student.stuCardNum}">
                    <div class="form-group">
                        <label for="courseId" class="col-sm-2 control-label">新的课程</label>
                        <div class="col-sm-8">
                            <select class="form-control" name="courseId" id="courseId">
                                <c:forEach items="${otherCourses}" var="course">
                                    <option value="${course.courseId}">${course.courseName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="financeRadio1" class="col-sm-2 control-label">是否缴费</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="financeFlag" id="financeRadio1" value="0" checked> 不需要费用
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="financeFlag" id="financeRadio2" value="1"> 需要费用
                            </label>
                        </div>
                        <div class="form-group" id="financeDiv" style="display: none;">
                            <label for="finance" class="col-sm-2 control-label">费用金额</label>
                            <div class="col-sm-6">
                                <input type="number" class="form-control" id="finance" value="0" name="finance">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-danger">更换课程</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <%-- 可以换课的课程 --%>
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" align="center">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>系</th>
                        <th>专业</th>
                        <th>课程名</th>
                        <th>课程年制</th>
                        <%--<th>教室</th>--%>
                        <th>教师1</th>
                        <th>教师2</th>
                        <th>课程学费</th>
                        <%--<th>实际缴费</th>--%>
                        <%--<th>课程ID</th>--%>
                        <th>报名限制人数</th>
                        <th>毕业人数限制</th>
                        <th>计划招生人数</th>
                        <th>实际招生人数</th>
                    </tr>
                    <tr>
                        <td id="newDepartmentName"></td>
                        <td id="newMajorName"></td>
                        <td id="newCourseName"></td>
                        <td id="newCourseYears"></td>
                        <%--<tags:dictd groupValue="courseRoom" itemKey="${changeCourse.courseRoom}" />--%>
                        <td id="newTeacherOneName"></td>
                        <td id="newTeacherTwoName"></td>
                        <td id="newCourseTuition"></td>
                        <%--<td>${changeCourse.actualTuition}</td>--%>
                        <%--<td>${changeCourse.courseId}</td>--%>
                        <td id="newCourseLimitNum"></td>
                        <td id="newCourseGraLimitNum"></td>
                        <td id="newCourseEnrollmentNum"></td>
                        <td id="newCourseStuNum"></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
       $("#courseId").on('change', function () {
           $.ajax({
               type: 'post',
               contentType: 'application/json',
               dataType: 'json',
               url: '${contextPath}/admin/course/change/new/' + $("#courseId").val() + '.action',
               success: function (result) {
                   console.log(result.newCourse);
                   var course = result.newCourse;
                   $("#newDepartmentName").html(course.departmentName);
                   $("#newMajorName").html(course.majorName);
                   $("#newCourseName").html(course.courseName);
                   $("#newCourseYears").html(course.courseYears);
                   $("#newTeacherOneName").html(course.teacherOneName);
                   $("#newTeacherTwoName").html(course.teacherTwoName);
                   $("#newCourseTuition").html(course.courseTuition);
                   $("#newCourseLimitNum").html(course.courseLimitNum);
                   $("#newCourseGraLimitNum").html(course.courseGraLimitNum);
                   $("#newCourseEnrollmentNum").html(course.courseEnrollmentNum);
                   $("#newCourseStuNum").html(course.courseStuNum);
               }
           });
       });

        $("#courseId").trigger("change");

        /**
         * 当选中缴费时显示缴费div
         */
        $("#financeRadio2").on("checked", function () {
           $("#financeDiv").css("display", "block");
        });

        /**
         * 当选中不缴费时隐藏不缴费div,把费用div的value更改为0
         */
        $("#financeRadio2").on("checked", function () {
            $("#financeDiv").css("display", "none");
            $("#finance").val(0);
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>