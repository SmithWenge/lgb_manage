<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navsOfTeaScore.jsp"%>

<div class="panel panel-default" style="float: left;width: 100%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;">
    <ul class="nav nav-pills">
      <li role="presentation" ><a href="${contextPath}/teaScore/routerHelp.action"><span class="glyphicon glyphicon-map-marker"></span>成绩管理</a></li>
      <li role="presentation" >
        <form class="form-inline" action="${contextPath}/teaScore/Search.action" method="post">
          <div class="form-group">
            <label class="sr-only" for="courseId">系别</label>
            <select class="form-control" id="courseId" name="courseId">
              <option value="0">班级</option>
              <c:forEach items="${courses}" var="course">
                <option value="${course.courseId}">${course.courseName}</option>
              </c:forEach>
            </select>
          </div>
          <button type="submit" class="btn btn-default">查询</button>
        </form>
      </li>
      <li role="presentation" style="float: right"><a href="${contextPath}/teaScore/routerImport.action">导入成绩</a></li>
    </ul>
  </div>
  <div class="panel-body">
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
    <c:if test="${not empty editFailureMessage}">
      <div class="col-md-12" id="editFailureMessage">
        <p class="bg-danger">${editFailureMessage}</p>
      </div>
    </c:if>
    <style>
      table th{
        min-width: 60px;;
        line-height: 40px;
        /*max-width: 80px;*/

      }
    </style>
    <div class="row" style="margin-top: 5px;">
      <div class="col-md-12">
        <table class="table" id="paginationTable" align="center">
          <tr style="background-color: #3767b1; color: #dbdbdb;">
            <th>序号</th>
            <th>姓名</th>
            <th>学号</th>
            <th>课程名</th>
            <th>成绩</th>
            <th>操作</th>
          </tr>
          <form method="post" id="batchForm" border="1px black solid;">
            <c:forEach items="${scoreModels}" var="scoreModel" varStatus="status">
              <tr style="line-height: 38px;">
                <td>${status.index + 1}</td>
                <td>${scoreModel.stuName}</td>
                <td>${scoreModel.studentCardNum}</td>
                <td>${scoreModel.courseName}</td>
                <td>${scoreModel.stuScore}</td>
                <td style="height: 30px;line-height: 38px">
                  <a href="${contextPath}/teaScore/routeEdit/${scoreModel.studentCourseId}.action" style="text-decoration: none;">
                    <button type="button" class="btn btn-warning">修改成绩</button>
                  </a>
                </td>
              </tr>
            </c:forEach>
          </form>
        </table>
      </div>
    </div>
  </div>

  <%@include file="/WEB-INF/include/javascript.jsp"%>

  <script type="text/javascript">
    $(function() {
      // 设置table表格中的行高
      var $height = $('#paginationTable td').height() + 'px';
      $('#paginationTable td').css('line-height', $height);

      setTimeout(function() {
        $("#addMessage").hide();
      }, 2000);
      setTimeout(function() {
        $("#editMessage").hide();
      }, 2000);
      setTimeout(function() {
        $("#deleteMessage").hide();
      }, 2000);
      setTimeout(function() {
        $("#deleteFailureMessage").hide();
      }, 2000);
      setTimeout(function() {
        $("#editFailureMessage").hide();
      }, 2000);
    });
  </script>

  <%@include file="/WEB-INF/include/footer.jsp"%>
