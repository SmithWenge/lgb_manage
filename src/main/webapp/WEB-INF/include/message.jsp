<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 操作消息 --%>
<c:if test="${not empty message}">
    <div class="col-md-10" id="message">
        <div class="alert alert-info" role="alert">${message}</div>
    </div>
</c:if>
