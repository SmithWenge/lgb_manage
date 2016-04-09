<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

    <c:if test="${not empty editFailureMessage}">
    <div class="col-md-12" id="message">
        <p class="bg-danger">${editFailureMessage}</p>
    </div>
    </c:if>

    <style type="text/css">
        form label {
            margin-top: 5px;
        }
        #courseTime label {
            margin-top: 0px;
        }
    </style>

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">编辑课程</div>
    <div class="panel-body">
        <form id="courseEditForm" action="${contextPath}/admin/course/edit.action" method="post">
            <input type="hidden" value="${course.courseId}" name="courseId">
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="departmentId" class="col-md-3 control-label">所属系</label>
                    <div class="col-md-9">
                        <select class="form-control" id="departmentId" name="departmentId">

                            <c:forEach items="${departments}" var="department">
                                <c:if test="${course.departmentId == department.departmentId}">
                                    <option value="${department.departmentId}" selected>${department.departmentName}</option>
                                </c:if>
                                <c:if test="${course.departmentId != department.departmentId}">
                                    <option value="${department.departmentId}">${department.departmentName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="majorId" class="col-md-3 control-label">专业</label>
                    <div class="col-md-9">
                        <select class="form-control" id="majorId" name="majorId">
                            <option value="${course.majorId}">${course.majorName}</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="courseNum" class="col-md-3 control-label">课程编码</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="courseNum" value="${course.courseNum}" name="courseNum">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="courseName" class="col-md-3 control-label">课程名</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="courseName" value="${course.courseName}" name="courseName">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="courseEnrollmentNum" class="col-md-4 control-label">计划招生人数</label>
                    <div class="col-md-8">
                        <input type="number" class="form-control" id="courseEnrollmentNum" value="${course.courseEnrollmentNum}" name="courseEnrollmentNum">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="courseTeacherOne" class="col-md-4 control-label">任课教师1</label>
                    <div class="col-md-8">
                        <select class="form-control" id="courseTeacherOne" name="courseTeacherOne">
                            <option value="${course.courseTeacherOne}">${course.teacherOneName}</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="courseTeacherTwo" class="col-md-4 control-label">任课教师2</label>
                    <div class="col-md-8">
                        <select class="form-control" id="courseTeacherTwo" name="courseTeacherTwo">
                            <option value="${course.courseTeacherTwo}">${course.teacherTwoName}</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="courseRoom" class="col-md-4 control-label">教室</label>
                    <div class="col-md-8">
                        <tags:dicselect name="courseRoom" key="courseRoom" value="${course.courseRoom}" id="courseRoom" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="courseTuition" class="col-md-3 control-label">学费</label>
                    <div class="col-md-9">
                        <input type="number" class="form-control" id="courseTuition" value="${course.courseTuition}" name="courseTuition">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="courseYears" class="col-md-3 control-label">学制</label>
                    <div class="col-md-9">
                        <tags:dicselect name="courseYears" key="courseYears" value="${course.courseYears}" id="courseYears" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="courseGraLimitNum" class="col-md-4 control-label">毕业报名限制</label>
                    <div class="col-md-8">
                        <input type="number" class="form-control" id="courseGraLimitNum" value="${course.courseGraLimitNum}" name="courseGraLimitNum">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="courseLimitNum" class="col-md-3 control-label">报名限制</label>
                    <div class="col-md-9">
                        <input type="number" class="form-control" id="courseLimitNum" value="${course.courseLimitNum}" name="courseLimitNum">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="courseSumFlag" class="col-md-3 control-label">统计标示</label>
                    <div class="col-md-9">
                        <tags:dicselect name="courseSumFlag" key="courseSumFlag" value="${course.courseSumFlag}" id="courseSumFlag" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 form-group">
                    <label for="courseRemark" class="col-md-1 control-label">备注</label>
                    <div class="col-md-11">
                        <textarea class="form-control" rows="3" id="courseRemark" name="courseRemark">${course.courseRemark}</textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 form-group">
                    <label class="col-md-1 control-label">时间</label>
                    <div class="col-md-11" id="courseTime">
                        <div class="col-md-12">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="1-a" name="courseTime"> 周一 8:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="1-b" name="courseTime"> 周一 9:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="1-c" name="courseTime"> 周一 10:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="1-d" name="courseTime"> 周一 13:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="1-e" name="courseTime"> 周一 15:00
                            </label>
                        </div>
                        <div class="col-md-12">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="2-a" name="courseTime"> 周二 8:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="2-b" name="courseTime"> 周二 9:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="2-c" name="courseTime"> 周二 10:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="2-d" name="courseTime"> 周二 13:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="2-e" name="courseTime"> 周二 15:00
                            </label>
                        </div>
                        <div class="col-md-12">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="3-a" name="courseTime"> 周三 8:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="3-b" name="courseTime"> 周三 9:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="3-c" name="courseTime"> 周三 10:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="3-d" name="courseTime"> 周三 13:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="3-e" name="courseTime"> 周三 15:00
                            </label>
                        </div>
                        <div class="col-md-12">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="4-a" name="courseTime"> 周四 8:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="4-b" name="courseTime"> 周四 9:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="4-c" name="courseTime"> 周四 10:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="4-d" name="courseTime"> 周四 13:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="4-e" name="courseTime"> 周四 15:00
                            </label>
                        </div>
                        <div class="col-md-12">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="5-a" name="courseTime"> 周五 8:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="5-b" name="courseTime"> 周五 9:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="5-c" name="courseTime"> 周五 10:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="5-d" name="courseTime"> 周五 13:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="5-e" name="courseTime"> 周五 15:00
                            </label>
                        </div>
                        <div class="col-md-12">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="6-a" name="courseTime"> 周六 8:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="6-b" name="courseTime"> 周六 9:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="6-c" name="courseTime"> 周六 10:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="6-d" name="courseTime"> 周六 13:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="6-e" name="courseTime"> 周六 15:00
                            </label>
                        </div>
                        <div class="col-md-12">
                            <label class="checkbox-inline">
                                <input type="checkbox" value="7-a" name="courseTime"> 周日 8:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="7-b" name="courseTime"> 周日 9:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="7-c" name="courseTime"> 周日 10:20
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="7-d" name="courseTime"> 周日 13:00
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" value="7-e" name="courseTime"> 周日 15:00
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <c:forEach items="${courseTimes}" var="time">
                <input type="hidden" name="oldTime" value="${time.timeWeek}-${time.timeSpecific}">
            </c:forEach>
            <div class="form-group">
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-default">编辑课程</button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        $('#courseEditForm').validate({
            rules: {
                courseNum: {
                    required: true,
                    minlength: 2,
                    maxlength: 20
                },
                courseName: {
                    required: true,
                    minlength: 2,
                    maxlength: 30
                },
                courseEnrollmentNum: {
                    required: true,
                    number: true
                },
                courseTuition: {
                    required: true,
                    number: true
                },
                courseLimitNum: {
                    required: true,
                    number: true
                },
                courseGraLimitNum: {
                    required: true,
                    number: true
                },
                courseTime: {
                    required: true
                },
                majorId: {
                    required: true
                }
            },
            messages: {
                courseNum: {
                    required: "请填写课程号.",
                    minlength: "课程号的长度为2到20.",
                    maxlength: "课程号的长度为2到20."
                },
                courseName: {
                    required: "请填写课程名.",
                    minlength: "课程名的长度为2到30.",
                    maxlength: "课程名的长度为2到30."
                },
                courseEnrollmentNum: {
                    required: "请填写计划招生人数.",
                    number: "请填写数字."
                },
                courseTuition: {
                    required: "请填写学费.",
                    number: "请填写数字."
                },
                courseLimitNum: {
                    required: "请填写报名限制.",
                    number: "请填写数字."
                },
                courseGraLimitNum: {
                    required: "请填写毕业报名限制.",
                    number: "请填写数字."
                },
                courseTime: {
                    required: "请选择上课时间."
                },
                majorId: {
                    required: "请选择专业."
                }
            }
        });

        $('#departmentId').on('change', function () {
            var $departmentId = $('#departmentId').val();
            var major = document.getElementById("majorId");
            var teacherOne = document.getElementById("courseTeacherOne");
            var teacherTwo = document.getElementById("courseTeacherTwo");
            $.ajax({
                type: 'post',
                contentType: 'application/json',
                dataType: 'json',
                url: '${contextPath}/admin/course/majors/' + $departmentId + '.action',
                success: function (result) {
                    major.options.length = 0;
                    $.each(result.majors, function (i, item) {
                        major.options.add(new Option(item.majorName, item.majorId));
                    });
                }
            });
            $.ajax({
                type: 'post',
                contentType: 'application/json',
                dataType: 'json',
                url: '${contextPath}/admin/course/teachers/' + $departmentId + '.action',
                success: function (result) {
                    teacherOne.options.length = 0;
                    $.each(result.teachers, function (i, item) {
                        teacherOne.options.add(new Option(item.teacherName, item.teacherId));
                    });
                }
            });
            $.ajax({
                type: 'post',
                contentType: 'application/json',
                dataType: 'json',
                url: '${contextPath}/admin/course/teachers/' + $departmentId + '.action',
                success: function (result) {
                    teacherTwo.options.length = 0;
                    teacherTwo.options.add(new Option("请选择", "-1"));
                    $.each(result.teachers, function (i, item) {
                        teacherTwo.options.add(new Option(item.teacherName, item.teacherId));
                    });
                }
            });
        });

        $('#courseEditForm input[name = courseTime]').each(function () {
            var $time = $(this);

            $('#courseEditForm input[name = oldTime]').each( function () {
                if ($time.val() == $(this).val()) {
                    $time.attr('checked', true);
                }
            });
        })

        setTimeout(function() {
            $("#message").hide();
        }, 2000);
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>