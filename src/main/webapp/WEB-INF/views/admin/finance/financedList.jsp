<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<%--财务管理--%>
<style>
  #finance{
    background: whitesmoke;
    border-left: 4px solid #fed350;
    border-right: 4px solid #fed350;
    color: #444;
  }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;">
    <ul class="nav nav-pills">
      <li role="presentation" ><a href="${contextPath}/admin/finance/routeCount.action"><span class="glyphicon glyphicon-map-marker"></span>财务分析</a></li>
      <li role="presentation">
        <form class="form-inline" action="${contextPath}/admin/finance/routeDayCount.action" method="post">
          <div class="form-group">
            <label for="queryFinanceDate" class="control-label">操作时间</label>
            <input type="date" class="form-control" id="queryFinanceDate" name="queryFinanceDate" style="height:33px;width:140px">
          </div>
          <button type="submit" class="btn btn-default">检索</button>
        </form>
      </li>
      <li role="presentation">
        <form class="form-inline" action="${contextPath}/admin/finance/routeTwoDayCount.action" method="post">
          <div class="form-group">
            <label for="queryFinanceDateOne" class="control-label">&nbsp;&nbsp;&nbsp;操作时间</label>
            <input type="date" class="form-control" id="queryFinanceDateOne" name="queryFinanceDateOne" style="height:33px;width:140px">
          </div>
          <div class="form-group">
            <label for="queryFinanceDateTwo" class="control-label">至</label>
            <input type="date" class="form-control" id="queryFinanceDateTwo" name="queryFinanceDateTwo" style="height:33px;width:140px">
          </div>
          <button type="submit" class="btn btn-default">检索</button>
        </form>
      </li>
      <li role="presentation" >
        <a>总收费: ${infoCount.sumActualTuition}</a>
      </li>
      <li role="presentation" >
        <a>今天收费: ${infoCount.daySumActualTuition}</a>
      </li>
      <li role="presentation" >
        <a href="${contextPath}/admin/finance/routeEcharts.action">查看财务统计</a>
      </li>

      <li role="presentation" >
        <a href="${contextPath}/admin/finance/routeExcel.action">导出</a>
      </li>
    </ul>
  </div>
  <div class="panel-body">

    <div class="row" style="margin-top: 5px;">
      <div class="col-md-12">
        <table class="table" id="paginationTable" align="center">
          <tr style="background-color: #3767b1; color: #dbdbdb;">
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
  });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>