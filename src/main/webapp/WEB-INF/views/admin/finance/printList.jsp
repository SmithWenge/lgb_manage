<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
  <div class="panel-heading">
    <ul class="nav nav-pills">
      <li role="presentation" ><a href="${contextPath}/admin/finance/printPage.action">打印发票</a></li>
      <li role="presentation">
        <form class="form-inline" action="${contextPath}/admin/finance/routePrintBill.action" method="post">
          <div class="form-group">
            <label for="stuCardNum" class="control-label"></label>
            <input type="text" class="form-control" id="stuCardNum" name="stuCardNum">
          </div>
          <button type="submit" class="btn btn-default">输入卡号检索</button>
        </form>
      </li>
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

    <div class="row" style="margin-top: 5px;">
      <div class="col-md-12">
        <table class="table" id="paginationTable" align="center">
          <tr style="background-color: #2aabd2;">
            <th>序号</th>
            <th>卡号</th>
            <th>姓名</th>
            <th>来源</th>
            <th>系</th>
            <th>专业</th>
            <th>课程</th>
            <th>学费</th>
            <th>优惠</th>
            <th>实际缴费</th>
            <th>操作用户</th>
            <th>操作时间</th>
            <th>操作</th>
          </tr>
          <c:forEach items="${page.content}" var="finance" varStatus="status">
            <tr>
              <td>${status.index + 1}</td>
              <td>${finance.cardNum}</td>
              <td>${finance.stuName}</td>
              <tags:dictd groupValue="signUpComeFrom" itemKey="${finance.signUpComeFrom}" />
              <td>${finance.departmentName}</td>
              <td>${finance.majorName}</td>
              <td>${finance.courseName}</td>
              <td>${finance.courseTuition}</td>
              <tags:dictd groupValue="courseDiscount" itemKey="${finance.courseDiscount}" />
              <td>${finance.actualTuition}</td>
              <td>${finance.financeUser}</td>
              <td>${finance.financeTime}</td>
              <td>
                <a href="${contextPath}/admin/finance/routePrint/${finance.studentCourseId}.action" style="text-decoration: none;">
                  <button type="button" class="btn btn-warning">打印</button>
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
                <a href="${contextPath}/admin/finance/countPage.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
              </li>
            </c:if>
            <c:if test="${page.number <= 0 }">
              <li class="previous disabled">
                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
              </li>
            </c:if>
            <c:if test="${page.number + 1 < page.totalPages }">
              <li class="next">
                <a href="${contextPath}/admin/finance/countPage.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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