<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/home/index.action">课程信息</a></li>
            <li role="presentation" >
                <form class="form-inline" action="${contextPath}/admin/home/indexSearch.action" method="post">
                    <div class="form-group">
                        <label class="sr-only" for="departmentId">系别</label>
                        <select class="form-control" id="departmentId" name="departmentId">
                            <option value="0">全部</option>
                            <c:forEach items="${departments}" var="department">
                                <option value="${department.departmentId}">${department.departmentName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="majorId">专业</label>
                        <select class="form-control" id="majorId" name="majorId">
                            <option value="0">全部</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>
                </form>
            </li>
        </ul>
    </div>
    <div class="panel-body">
            <c:if test="${not empty addMessage}">
                <div class="col-md-12" id="addMessage">
                    <p class="bg-success">${addMessage}</p>
                </div>
            </c:if>
            <c:if test="${not empty editMessage}">
                <div class="col-md-12" id="editMessage">
                    <p class="bg-success">${editMessage}</p>
                </div>
            </c:if>
            <c:if test="${not empty deleteMessage}">
                <div class="col-md-12" id="deleteMessage">
                    <p class="bg-success">${deleteMessage}</p>
                </div>
            </c:if>
            <c:if test="${not empty deleteFailureMessage}">
                <div class="col-md-12" id="deleteFailureMessage">
                    <p class="bg-danger">${deleteFailureMessage}</p>
                </div>
            </c:if>
            <c:if test="${not empty editFailureMessage}">
                <div class="col-md-12" id="editFailureMessage">
                    <p class="bg-danger">${editFailureMessage}</p>
                </div>
            </c:if>

        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #2aabd2;">
                        <th>序号</th>
                        <th>专业</th>
                        <th>课程号</th>
                        <th>课程名</th>
                        <th>任课教师</th>
                        <th>教室</th>
                        <th>上课时间</th>
                        <th>计划人数</th>
                        <th>实际人数</th>
                        <th>学费</th>
                        <th>报名限制</th>
                    </tr>
                    <form method="post" id="batchForm">
                    <c:forEach items="${page.content}" var="course" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${course.majorName}</td>
                            <td>${course.courseNum}</td>
                            <td>${course.courseName}</td>
                            <td>${course.teacherOneName} ${course.teacherTwoName}</td>
                            <tags:dictd groupValue="courseRoom" itemKey="${course.courseRoom}" />
                            <td>
                            <c:forEach items="${course.times}" var="time">
                                <tags:diclabel groupValue="timeWeek" itemKey="${time.timeWeek}"/>
                                 <tags:diclabel groupValue="timeSpecific" itemKey="${time.timeSpecificInt}"/>,
                            </c:forEach>
                            </td>
                            <td>${course.courseEnrollmentNum}</td>
                            <td>${course.courseStuNum}</td>
                            <td>${course.courseTuition}</td>
                            <td>${course.courseLimitNum}</td>
                        </tr>
                    </c:forEach>
                    </form>
                </table>
            </div>
        </div>

        <div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
            <div class="col-md-12">
                <nav>
                    <ul class="pager">
                        <c:if test="${page.number > 0 }">
                            <li class="previous">
                                <a href="${contextPath}/admin/home/page.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number <= 0 }">
                            <li class="previous disabled">
                                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 < page.totalPages }">
                            <li class="next">
                                <a href="${contextPath}/admin/home/page.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 >= page.totalPages }">
                            <li class="next disabled">
                                <a href="#">下一页 <span aria-hidden="true">&rarr;</span></a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function() {
        // 设置table表格中的行高
        var $height = $('#paginationTable td').height() + 'px';
        $('#paginationTable td').css('line-height', $height);

        setTimeout(function() {
            $("#addMessage").hide();
        }, 2000);
        setTimeout(function() {
            $("#editMessage").hide();
        }, 2000);
        setTimeout(function() {
            $("#deleteMessage").hide();
        }, 2000);
        setTimeout(function() {
            $("#deleteFailureMessage").hide();
        }, 2000);
        setTimeout(function() {
            $("#editFailureMessage").hide();
        }, 2000);

        $('#departmentId').on('change', function () {
            var $departmentId = $('#departmentId').val();
            var major = document.getElementById("majorId");
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
        });

        $('#batchUpgrade').on('click', function () {
            var actionPath = "${contextPath}/admin/course/batch/upgrade.action";
            $('#batchForm').attr('action', actionPath).submit();
        });

        $('#batchGraduate').on('click', function () {
            var actionPath = "${contextPath}/admin/course/batch/graduate.action";
            $('#batchForm').attr('action', actionPath).submit();
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>