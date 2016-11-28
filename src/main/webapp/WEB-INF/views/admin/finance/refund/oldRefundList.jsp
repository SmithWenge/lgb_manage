<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<%-- 已退款查看 --%>
<!-- style="margin-left: 16%-->
<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%;">
    <div class="panel-heading" style="float: left;width: 90%;">
        <a href="${contextPath}/admin/finance/refund/route.action"><span class="glyphicon glyphicon-map-marker"></span>退课</a> &nbsp; <b>></b> &nbsp; 查看所有退课信息
    </div>
    <div class="panel-body" style="float: right;width: 90%;">

        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #2aabd2;">
                        <th>序号</th>
                        <th>学生名</th>
                        <th>课程名</th>
                        <th>退款费用</th>
                        <th>退款时间</th>
                        <th>操作人员</th>
                        <th>是/否退款</th>
                    </tr>
                    <c:forEach items="${courses}" var="course" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${course.studentName}</td>
                            <td>${course.courseName}</td>
                            <td>${course.refundMoney}</td>
                            <td>${course.refundTime}</td>
                            <td>${course.refundUser}</td>
                            <tags:dictd groupValue="refundTuitionFlag" itemKey="${course.refundTuitionFlag}" />
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>