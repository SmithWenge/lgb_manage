<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp"%>
<ul class="list-unstyled">
    <li>发票号：${finance.billNumber}</li>
    <br/>
    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大连市老干部大学</li>
    <li>-------------------------</li>
    <li>卡号：${finance.stuCardNum}</li>
    <li>学员名：${finance.stuName}</li>
    <li>${finance.departmentName}>${finance.majorName}>${finance.courseName}</li>
    <li>金额：￥${finance.actualTuition}</li>
    <li>收款时间：${finance.financeTime}</li>
    <li>-------------------------</li>
    <br/>
    <li>本收据为大连老干部大学收费证</li>
    <li>明，仅用于本校调班、退学、更</li>
    <li>换发票的依据，不作为报销凭</li>
    <li>证，请妥善保管，丢失不补。最</li>
    <li>终解释权归老干部大学所有。</li>
</ul>

<%@include file="/WEB-INF/include/footer.jsp"%>



