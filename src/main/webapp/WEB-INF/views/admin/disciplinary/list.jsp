<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<style type="text/css">
  form label {
    margin-top: 5px;
  }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="padding-bottom: 0px; padding-top: 0px; padding-left: 0px;">
    <ul class="nav nav-pills">
      <li role="presentation" ><a href="${contextPath}/admin/student/page.action"><span class="glyphicon glyphicon-map-marker"></span> &nbsp;学生管理 &nbsp;/&nbsp; </a></li>
      <li role="presentation" ><a href="${contextPath}/admin/disciplinary/page.action" style="margin-left:-30px;color: black">违纪管理</a></li>
      <li role="presentation" style="float: right"><a href="${contextPath}/admin/disciplinary/routeAdd.action">添加违纪学员</a></li>
    </ul>
  </div>
  <div class="panel-body">
    <div class="row">
      <div class="col-md-6">
        <form style="margin-left: 2%; margin-right: 2%; margin-top: 1%;" action="${contextPath}/admin/disciplinary/pageSearch.action" method="post" id="queryDisciplinary">
          <div class="col-md-9 form-group">
            <label for="stuCardNum" class="col-md-4 control-label">学号</label>
            <div class="col-md-8">
              <input type="text" class="form-control" id="stuCardNum" placeholder="101010" name="stuCardNum">
            </div>
          </div>
          <div class="col-md-3 form-group">
            <div class="col-sm-offset-1 col-sm-4">
              <button type="submit" class="btn btn-success" style="float: right">检索</button>
            </div>
          </div>
        </form>
      </div>
      <div class="col-md-6">
        <form style="margin-left: 2%; margin-right: 2%; margin-top: 1%;" action="${contextPath}/admin/disciplinary/countSearch.action" method="post" id="stuAddForm">
          <div class="col-md-9 form-group">
            <label for="disciCount" class="col-md-4 control-label">违纪次数</label>
            <div class="col-md-8">
              <input type="text" class="form-control" id="disciCount" name="disciCount" value="1">
            </div>
          </div>
          <div class="col-md-3 form-group">
            <div class="col-sm-offset-1 col-sm-4">
              <button type="submit" class="btn btn-success" style="float: right">检索</button>
            </div>
          </div>
        </form>
      </div>
    </div>

    <div class="row" style="margin-top: 5px;">
      <div class="col-md-12">
        <table class="table" id="paginationTable">
          <tr style="background-color: #3767b1; color: #dbdbdb;">
            <th>卡号</th>
            <th>学员姓名</th>
            <th>电话</th>
            <th>违纪次数</th>
            <th>操作</th>
          </tr>
          <c:forEach items="${page.content}" var="user">
            <tr>
              <td>${user.stuCardNum}</td>
              <td>${user.stuName}</td>
              <td>${user.stuTelOne}</td>
              <td>${user.disciSum}</td>
              <td>
                <a href="${contextPath}/admin/disciplinary/moreInfo/${user.stuId}.action" style="text-decoration: none;">
                  <button type="button" class="btn btn-warning">浏览更多信息</button>
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
                <a href="${contextPath}/admin/disciplinary/page.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
              </li>
            </c:if>
            <c:if test="${page.number <= 0 }">
              <li class="previous disabled">
                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
              </li>
            </c:if>
            <c:if test="${page.number + 1 < page.totalPages }">
              <li class="next">
                <a href="${contextPath}/admin/disciplinary/page.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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

<%@include file="/WEB-INF/include/footer.jsp"%>