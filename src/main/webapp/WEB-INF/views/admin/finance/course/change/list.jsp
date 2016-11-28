<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<!-- style="margin-left: 16%-->
<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%;">
    <div class="panel-heading" style="float: left;width: 90%;">换课费用</div>
    <div class="panel-body" style="float: right;width: 90%;">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #2aabd2;">
                        <th>序号</th>
                        <th>卡号</th>
                        <th>姓名</th>
                        <th>课程</th>
                        <th>换课时间</th>
                        <th>是否有费用</th>
                        <th>缴费金额</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${finances}" var="finance" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${finance.stuCardNum}</td>
                            <td>${finance.stuName}</td>
                            <td>${finance.courseName}</td>
                            <td>${finance.changeTime}</td>
                            <tags:dictd groupValue="financeFlag" itemKey="${finance.financeFlag}" />
                            <td>${finance.finance}</td>
                            <td>
                                <a href="${contextPath}/admin/finance/course/change/${finance.changeCourseId}/tuition.action" style="text-decoration: none;">
                                    <button type="button" class="btn btn-danger">费用操作</button>
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