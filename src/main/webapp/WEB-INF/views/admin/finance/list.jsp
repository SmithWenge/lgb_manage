<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/finance/routePage.action">财务</a></li>
            <li role="presentation" >
                <form class="form-inline" action="${contextPath}/admin/finance/pageSearch.action" method="post">
                    <div class="form-group">
                        <label for="courseDiscount">优惠类型</label>
                        <tags:dicselect name="courseDiscount" key="courseDiscount" value="11" />
                    </div>
                    <div class="form-group">
                        <label for="signUpComeFrom">来源</label>
                        <tags:dicselect name="signUpComeFrom" key="signUpComeFrom" value="-1" />
                    </div>
                    <div class="form-group">
                        <label for="departmentId">系别</label>
                        <select class="form-control" id="departmentId" name="departmentId">
                            <option value="0">全部</option>
                            <c:forEach items="${departments}" var="department">
                                <option value="${department.departmentId}">${department.departmentName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="majorId">专业</label>
                        <select class="form-control" id="majorId" name="majorId">
                            <option value="0">全部</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="courseId">课程</label>
                        <select class="form-control" id="courseId" name="courseId">
                            <option value="0">全部</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>
                </form>
            </li>
            <li role="presentation" style="float: right"><a href="${contextPath}/admin/finance/routeCount.action">财务分析</a></li>
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
                        <th>卡号</th>
                        <th>姓名</th>
                        <th>来源</th>
                        <th>系</th>
                        <th>专业</th>
                        <th>课程</th>
                        <th>学费</th>
                        <th>优惠</th>
                        <th>操作用户</th>
                        <th>操作时间</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${page.content}" var="finance" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${finance.cardNum}</td>
                            <td>${finance.stuName}</td>
                            <tags:dictd groupValue="signUpComeFrom" itemKey="${finance.signUpComeFrom}" />
                            <td>${finance.departmentName}</td>
                            <td>${finance.majorName}</td>
                            <td>${finance.courseName}</td>
                            <td>${finance.courseTuition}</td>
                            <tags:dictd groupValue="courseDiscount" itemKey="${finance.courseDiscount}" />
                            <td>${finance.signUpUser}</td>
                            <td>${finance.signUpDate}</td>
                            <td>
                                <a href="${contextPath}/admin/finance/routeEdit/${finance.studentCourseId}.action" style="text-decoration: none;">
                                    <button type="button" class="btn btn-warning">收款</button>
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
                                <a href="${contextPath}/admin/finance/page.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number <= 0 }">
                            <li class="previous disabled">
                                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 < page.totalPages }">
                            <li class="next">
                                <a href="${contextPath}/admin/finance/page.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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
                url: '${contextPath}/admin/finance/majors/' + $departmentId + '.action',
                success: function (result) {
                    major.options.length = 0;
                    $.each(result.majors, function (i, item) {
                        major.options.add(new Option(item.majorName, item.majorId));
                    });
                }
            });
        });

        $('#majorId').on('change', function () {
            var $majorId = $('#majorId').val();
            var course = document.getElementById("courseId");
            $.ajax({
                type: 'post',
                contentType: 'application/json',
                dataType: 'json',
                url: '${contextPath}/admin/finance/courses/' + $majorId + '.action',
                success: function (result) {
                    course.options.length = 0;
                    $.each(result.courses, function (i, item) {
                        course.options.add(new Option(item.courseName, item.courseId));
                    });
                }
            });
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>