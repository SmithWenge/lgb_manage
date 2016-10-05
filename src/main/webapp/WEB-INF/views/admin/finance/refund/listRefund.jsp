<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<%-- 查询退课学员 --%>
<!-- style="margin-left: 16%-->
<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%;">
    <div class="panel-heading" style="float: left;width: 85%;">
        <a href="${contextPath}/admin/finance/refund/route.action"><span class="glyphicon glyphicon-map-marker"></span>退课</a> &nbsp; <b>></b> &nbsp; 查询学员的所有课程
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
                        <th>缴费时间</th>
                        <th>操作</th>
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
                            <td>${finance.financeTime}</td>
                            <td>
                                <a href="${contextPath}/admin/finance/refund/routeRefund/${finance.studentCourseId}.action" style="text-decoration: none;">
                                    <button type="button" class="btn btn-warning">退款</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>