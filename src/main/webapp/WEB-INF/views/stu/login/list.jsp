<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>


<style type="text/css">
    form label {
        margin-top: 5px;
    }
</style>
<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">
        <ul class="nav nav-pills">
            <li role="presentation" ><a>报名系统</a></li>
            <li role="presentation" >
                <form class="form-inline" action="${contextPath}/stu/pageSearch.action" method="post">
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

            <li role="presentation"class="active" style="float: right"><a href="${contextPath}/stu/querySign.action">查看已报名课程</a></li>
        </ul>
    </div>

    <div class="panel-body">
        <c:if test="${not empty addMessage}">
            <div class="col-md-12" id="addMessage">
                <p class="bg-success">${addMessage}</p>
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
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable">
                    <tr style="background-color: #2aabd2;">
                        <th>课程号</th>
                        <th>课程名</th>
                        <th>所属专业</th>
                        <th>所属系别</th>
                        <th>限制人数</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${page.content}" var="course">
                        <tr>
                            <td>${course.courseNum}</td>
                            <td>${course.courseName}</td>
                            <td>${course.majorName}</td>
                            <td>${course.departmentName}</td>
                            <td>${course.courseLimitNum}</td>
                            <td>
                                <a href="${contextPath}/stu/courseInfo/${course.courseId}.action" style="text-decoration: none;">
                                    <button type="button" class="btn btn-warning" >查看课程信息</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
            <div class="col-md-12">
                <nav>
                    <ul class="pager">
                        <c:if test="${page.number > 0 }">
                            <li class="previous">
                                <a href="${contextPath}/stu/login.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number <= 0 }">
                            <li class="previous disabled">
                                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 < page.totalPages }">
                            <li class="next">
                                <a href="${contextPath}/stu/login.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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
                url: '${contextPath}/stu/majors/' + $departmentId + '.action',
                success: function (result) {
                    major.options.length = 0;
                    $.each(result.majors, function (i, item) {
                        major.options.add(new Option(item.majorName, item.majorId));
                    });
                }
            });
        });
    });
</script>
<%@include file="/WEB-INF/include/footer.jsp"%>