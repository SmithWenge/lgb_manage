<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<style>
  #score{
    background: whitesmoke;
    border-left: 4px solid #fed350;
    border-right: 4px solid #fed350;
    color: #444;
  }
</style>
<div class="panel panel-default" style="float: left;width: 90%;">
  <div class="panel-heading" style="padding-bottom: 0px; padding-top: 0px; padding-left: 0px;">
    <ul class="nav nav-pills">
      <li role="presentation" ><a href="${contextPath}/admin/score/routerList.action"><span class="glyphicon glyphicon-map-marker"></span> &nbsp;成绩管理</a></li>
      <li role="presentation" >
        <form class="form-inline" action="${contextPath}/admin/score/pageSearch.action" method="post">
          <div class="form-group">
            <label class="sr-only" for="departmentId">系别</label>
            <select class="form-control" id="departmentId" name="departmentId">
              <option value="0">全部</option>
              <c:forEach items="${departments}" var="department">
                <option value="${department.departmentId}">${department.departmentName}</option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label class="sr-only" for="majorId">专业</label>
            <select class="form-control" id="majorId" name="majorId">
              <option value="0">全部</option>
            </select>
          </div>
          <div class="form-group">
            <label class="sr-only" for="courseId">课程</label>
            <select class="form-control" id="courseId" name="courseId">
              <option value="0">全部</option>
            </select>
          </div>
          <button type="submit" class="btn btn-default">查询</button>
        </form>
      </li>
      <li role="presentation" style="float: right">
        <form class="form-inline" action="${contextPath}/admin/score/pageSearch.action" method="post">
          <div class="form-group">
              <label for="studentCardNum" class="control-label">学生卡号</label>
              <input type="text" class="form-control" id="studentCardNum" name="studentCardNum" >
          </div>
          <button type="submit" class="btn btn-default">查询</button>
        </form>
      </li>
    </ul>
  </div>
  <div class="panel-body">
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
            <c:forEach items="${scores}" var="score" varStatus="status">
              <tr style="line-height: 38px;">
                <td>${status.index + 1}</td>
                <td>${score.stuName}</td>
                <td>${score.studentCardNum}</td>
                <td>${score.courseName}</td>
                <td>${score.stuScore}</td>
                <td style="height: 30px;line-height: 38px">
                  <a href="${contextPath}/admin/score/routeEdit/${score.studentCourseId}.action" style="text-decoration: none;">
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

    $('#departmentId').on('change', function () {
      var $departmentId = $('#departmentId').val();
      var major = document.getElementById("majorId");
      $.ajax({
        type: 'post',
        contentType: 'application/json',
        dataType: 'json',
        url: '${contextPath}/admin/score/majors/' + $departmentId + '.action',
        success: function (result) {
          major.options.length = 0;
          $.each(result.majors, function (i, item) {
            major.options.add(new Option(item.majorName, item.majorId));
          });
        }
      });
    });

    $('#majorId').on('change', function () {
      var $majorId = $('#majorId').val();
      var course = document.getElementById("courseId");
      $.ajax({
        type: 'post',
        contentType: 'application/json',
        dataType: 'json',
        url: '${contextPath}/admin/score/courses/' + $majorId + '.action',
        success: function (result) {
          course.options.length = 0;
          $.each(result.courses, function (i, item) {
            course.options.add(new Option(item.courseName, item.courseId));
          });
        }
      });
    });
  });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>