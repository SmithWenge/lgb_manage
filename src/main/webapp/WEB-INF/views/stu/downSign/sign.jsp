<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/navs.jsp"%>

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
      <li role="presentation" ><a>线下报名系统</a></li>
      <li role="presentation"class="active" style="float: right"><a href="${contextPath}/admin/offline/queryDownSign.action">查看已报名课程</a></li>
    </ul>
  </div>



  <form style="margin-left: 0.2%; margin-right: 2%; margin-top: 1%;" class="form-horizontal col-sm-offset-1" action="${contextPath}/admin/offline/downSign.action" method="post" id="downSignForm">
    <div class="row">
      <div class="col-md-4 form-group">
        <label for="stuCardNum" class="col-md-4 control-label">学生卡号</label>
        <div class="col-md-8">
          <input type="text" class="form-control" id="stuCardNum" name="stuCardNum" placeholder="101010">
        </div>
      </div>
      <div class="col-md-4 form-group">
        <button type="submit" class="btn btn-default">确定</button>
      </div>
    </div>


  </form>

  <div class="panel-body">
    <c:if test="${not empty addMessage}">
      <div class="col-md-12" id="addMessage">
        <p class="bg-success">${addMessage}</p>
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
                <a href="${contextPath}/admin/offline/downCourseInfo/${course.courseId}.action" style="text-decoration: none;">
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
                <a href="${contextPath}/admin/offline/downSign.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
              </li>
            </c:if>
            <c:if test="${page.number <= 0 }">
              <li class="previous disabled">
                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
              </li>
            </c:if>
            <c:if test="${page.number + 1 < page.totalPages }">
              <li class="next">
                <a href="${contextPath}/admin/offline/downSign.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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

<script type="text/javascript">
  $(function () {
    $('#downSignForm').validate({
      rules: {
        stuCardNum: {
          required: true,
          minlength: 6,
          maxlength: 11
        }
      },
      messages: {
        stuCardNum: {
          required: "请填写卡号.",
          minlength: "卡号的长度为6.",
          maxlength: "卡号的长度为6."
        }
      }
    });
  });
</script>
<%@include file="/WEB-INF/include/footer.jsp"%>