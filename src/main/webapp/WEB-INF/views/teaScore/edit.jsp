<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navsOfTeaScore.jsp"%>

<style>
  #major{
    background: whitesmoke;
    border-left: 4px solid #fed350;
    border-right: 4px solid #fed350;
    color: #444;
  }
</style>
<div class="panel panel-default" style="float: left;width: 100%;">
  <div class="panel-heading" style="height: 45px;padding-top: 10px;"><a href="${contextPath}/admin/score/routerList.action"><span class="glyphicon glyphicon-map-marker"></span>成绩管理</a> > 成绩修改</div>
  <div class="panel-body">
    <div class="row" >
      <c:if test="${not empty addFailureMessage}">
        <div class="col-md-12" id="message">
          <p class="bg-danger">${addFailureMessage}</p>
        </div>
      </c:if>
      <div class="col-md-12">
        <form class="form-horizontal" action="${contextPath}/teaScore/edit.action" method="post" id="scoreEditForm">
          <input type="hidden" name="studentCourseId" value="${score.studentCourseId}">
          <div class="form-group">
            <label for="stuName" class="col-sm-2 control-label">学生姓名</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="stuName" name="stuName" value="${score.stuName}" readonly="true" >
            </div>
          </div>
          <div class="form-group">
            <label for="studentCardNum" class="col-sm-2 control-label">学生卡号</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="studentCardNum" name="studentCardNum" value="${score.studentCardNum}" readonly="true" >
            </div>
          </div>
          <div class="form-group">
            <label for="courseName" class="col-sm-2 control-label">课程名</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="courseName" name="courseName" value="${score.courseName}" readonly="true" >
            </div>
          </div>
          <div class="form-group">
            <label for="stuName" class="col-sm-2 control-label">课程成绩</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="stuScore" name="stuScore" value="${score.stuScore}" >
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-default">编辑成绩</button>
              <button type="button" id="backMark" class="btn btn-default" style="margin-left: 100px">返回</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
  $(function () {

    setTimeout(function() {
      $("#message").hide();
    }, 2000);
  });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>