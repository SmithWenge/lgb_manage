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
    <ul>
      <li><b>1. 可以选择班级查询学生，然后逐条单个修改成绩</b></li>
      <li><b>2. 可以通过excel打入成绩，需要注意操作流程，防止出错</b></li>
      <li><b>3. 班级下拉框中只会显示登录教师所教的班级，对别的教师的班级不会显示</b></li>
    </ul>
  </div>

  <%@include file="/WEB-INF/include/javascript.jsp"%>

  <%@include file="/WEB-INF/include/footer.jsp"%>
