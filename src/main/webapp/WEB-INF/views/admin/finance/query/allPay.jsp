<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<%-- 已缴费学员查看 --%>
<!-- style="margin-left: 16%-->
<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%;">
    <div class="panel-heading" style="float: left;width: 85%;">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/finance/query/all.action">收费查询</a></li>
            <li role="presentation" >
                <form class="form-inline" action="${contextPath}/admin/finance/query/all.action" method="post">
                    <div class="form-group">
                        <label class="sr-only" for="courseId">课程</label>
                        <select class="form-control" id="courseId" name="courseId">
                            <option value="0">全部</option>
                            <c:forEach items="${courses}" var="course">
                                <option value="${course.courseId}">${course.courseName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <div class="form-group">
                            <label class="sr-only" for="financeYear">年</label>
                            <input type="number" class="form-control" id="financeYear" name="financeYear">
                        </div>
                    </div>
                    <script type="text/javascript">
                        var financeYear = new Date().getFullYear();
                        document.getElementById("financeYear").value = financeYear;
                    </script>
                    <div class="form-group">
                        <label class="sr-only" for="billFlag">发票</label>
                        <tags:dicselect name="billFlag" key="billFlag" value="0" />
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>
                </form>
            </li>
        </ul>
    </div>
    <div class="panel-body" style="float: right;width: 85%;">

        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #2aabd2;">
                        <th>序号</th>
                        <th>学生名</th>
                        <th>课程名</th>
                        <th>课程费用</th>
                        <th>实际缴费</th>
                        <th>操作人员</th>
                        <th>是/否打印发票</th>
                        <th>发票号</th>
                        <th>是/否缴费</th>

                    </tr>
                    <c:forEach items="${finances}" var="finance" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${finance.stuName}</td>
                            <td>${finance.courseName}</td>
                            <td>${finance.courseTuition}</td>
                            <td>${finance.actualTuition}</td>
                            <td>${finance.financeUser}</td>
                            <tags:dictd groupValue="billFlag" itemKey="${finance.billFlag}" />
                            <td>${finance.billNumber}</td>
                            <tags:dictd groupValue="tuitionFlag" itemKey="${finance.tuitionFlag}" />
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>