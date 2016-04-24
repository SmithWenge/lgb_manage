<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<ul class="list-unstyled">
    <li>发票号：${finance.billNumber}</li>
    <li>大连市老干部大学</li>
    <li>卡号：${finance.stuCardNum}</li>
    <li>学员名：${finance.stuName}</li>
    <li>${finance.departmentName}>${finance.majorName}>${finance.courseName}</li>
    <li>金额：${finance.actualTuition}</li>
    <li>收款时间：${finance.financeTime}</li>
</ul>

<%@include file="/WEB-INF/include/footer.jsp"%>



