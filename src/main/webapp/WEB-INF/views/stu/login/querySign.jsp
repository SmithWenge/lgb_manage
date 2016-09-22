<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<style type="text/css">
  form label {
    margin-top: 5px;
  }
</style>
<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
  <div class="panel-heading">
    <ul class="nav nav-pills">
      <li role="presentation" ><a>您已报名的课程</a></li>
      <li role="presentation"class="active" style="float: right"><a href="${contextPath}/stu/login.action">返回上一页</a></li>
    </ul>
  </div>

  <div class="panel-body">
    <div class="row" style="margin-top: 5px;">
      <div class="col-md-12">
        <table class="table" id="paginationTable">
          <tr style="background-color: #2aabd2;">
            <th>课程号</th>
            <th>课程名</th>
            <th>所属专业</th>
            <th>所属系别</th>
            <th>限制人数</th>
            <th>操作</th>
          </tr>
          <c:forEach items="${page.content}" var="course">
            <tr>
              <td>${course.courseNum}</td>
              <td>${course.courseName}</td>
              <td>${course.majorName}</td>
              <td>${course.departmentName}</td>
              <td>${course.courseLimitNum}</td>
              <td>
                <a href="${contextPath}/stu/courseInfoSign/${course.courseId}.action" style="text-decoration: none;">
                  <button type="button" class="btn btn-warning" >查看课程信息</button>
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
                <a href="${contextPath}/stu/querySign.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
              </li>
            </c:if>
            <c:if test="${page.number <= 0 }">
              <li class="previous disabled">
                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
              </li>
            </c:if>
            <c:if test="${page.number + 1 < page.totalPages }">
              <li class="next">
                <a href="${contextPath}/stu/querySign.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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