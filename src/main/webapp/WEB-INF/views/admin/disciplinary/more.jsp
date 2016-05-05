<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>


<style type="text/css">
  form label {
    margin-top: 5px;
  }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;">
    <ul class="nav nav-pills">
      <li role="presentation" ><a href="${contextPath}/admin/student/page.action"><span class="glyphicon glyphicon-map-marker"></span>学生管理 > </a></li>
      <li role="presentation" ><a href="${contextPath}/admin/disciplinary/page.action" style="margin-left:-30px;color: black">违纪管理</a></li>
      <li role="presentation" style="float: right"><a href="${contextPath}/admin/disciplinary/page.action">返回上一页</a></li>
    </ul>
  </div>

  <div class="panel-body">
    <div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
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
    </div>
    <div class="row" style="margin-top: 5px;">
      <div class="col-md-12">
        <table class="table" id="paginationTable">
          <tr style="background-color: #3767b1; color: #dbdbdb;">
            <th>卡号</th>
            <th>学员姓名</th>
            <th>时间</th>
            <th>原因</th>
          </tr>
          <c:forEach items="${disciplinary}" var="disciplinary">
          <tr>
              <td>${disciplinary.stuCardNum}</td>
              <td>${disciplinary.stuName}</td>
              <td>${disciplinary.disciTime}</td>
              <td>${disciplinary.disciCause}</td>
          </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>