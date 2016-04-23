<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<ul class="list-unstyled">
    <li>${finance.billNumber}</li>
    <li>${finance.studentCourseId}</li>
    <li>${finance.stuName}</li>
    <li>${finance.departmentName}</li>
    <li>${finance.majorName}</li>
    <li>${finance.courseName}</li>
    <li>${finance.actualTuition}</li>
    <li>${finance.financeTime}</li>
</ul>

<%@include file="/WEB-INF/include/footer.jsp"%>



