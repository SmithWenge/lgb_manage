<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>
<%@ include file="/WEB-INF/include/message.jsp"%>

<div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
  <div class="col-md-12">
    <ul class="nav nav-pills">
    </ul>
  </div>
</div>


<style type="text/css">
  form label {
    margin-top: 5px;
  }
</style>
<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
  <div class="panel-heading">
    <ul class="nav nav-pills">
      <li role="presentation" ><a>课程详细信息</a></li>
      <li role="presentation"class="active" style="float: right"><a href="${contextPath}/admin/offline/downSign.action?stuCardNum=${stuCardNum}">返回上一页</a></li>
    </ul>
  </div>

  <div class="panel-body">
    <div class="row" style="margin-top: 5px;">
      <div class="col-md-12">
        <table class="table" id="paginationTable">
          <tr style="background-color: #2aabd2;">
            <th>课程名</th>
            <th>所属专业</th>
            <th>所属系别</th>
            <th>已报名人数</th>
            <th>限制人数</th>
            <th>任课教师</th>
            <th>教室</th>
            <th>上课时间</th>
            <th>学费</th>
            <th>操作</th>
          </tr>
          <tr>
            <td>${course.courseName}</td>
            <td>${course.majorName}</td>
            <td>${course.departmentName}</td>
            <td>${course.courseStuNum}</td>
            <td>${course.courseLimitNum}</td>
            <td>${course.teacherOneName}</td>
            <tags:dictd groupValue="courseRoom" itemKey="${course.courseRoom}" />
            <td style="font-size: 5px;">
              <c:forEach items="${course.times}" var="time">
                <tags:diclabel groupValue="timeWeek" itemKey="${time.timeWeek}"/>
                <tags:diclabel groupValue="timeSpecific" itemKey="${time.timeSpecificInt}"/>,

              </c:forEach>
            </td>
            <td>${course.courseTuition}</td>

            <td>
              <a href="${contextPath}/admin/offline/signUp/${course.courseId}.action" style="text-decoration: none;">
                <button type="button" class="btn btn-success">报名</button>
              </a>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>
<%@include file="/WEB-INF/include/footer.jsp"%>