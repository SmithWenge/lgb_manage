<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<%-- 已缴费学员查看 --%>
<!-- style="margin-left: 16%-->
<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%;">
    <div class="panel-heading" style="float: left;width: 90%;">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/finance/payment.action">缴费信息</a></li>
            <li role="presentation" >
                <form class="form-inline" action="${contextPath}/admin/finance/payment.action" method="post">
                    <div class="form-group">
                        <label for="courseId">课程</label>
                        <select class="form-control" id="courseId" name="courseId">
                            <option value="0">全部</option>
                            <c:forEach items="${courses}" var="course">
                                <option value="${course.courseId}">${course.courseName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>
                </form>
            </li>
        </ul>
    </div>
    <div class="panel-body" style="float: right;width: 90%;">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #2aabd2;">
                        <th>序号</th>
                        <th>学生名</th>
                        <th>学员卡号</th>
                        <th>联系电话</th>
                        <th>课程名</th>
                        <th>级别</th>
                        <th>缴费状态</th>
                    </tr>
                    <c:forEach items="${students}" var="student" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${student.stuName}</td>
                            <td>${student.stuCardNum}</td>
                            <td>${student.stuTelOne}</td>
                            <td>${student.courseName}</td>
                            <tags:dictd groupValue="memberLevel" itemKey="${student.stuLevel}" />
                            <tags:dictd groupValue="tuitionFlag" itemKey="${student.tuitionFlag}" />
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>