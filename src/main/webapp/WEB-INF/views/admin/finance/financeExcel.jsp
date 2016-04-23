<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
  <div class="panel-heading">
    <ul class="nav nav-pills">
      <li role="presentation" ><a href="${contextPath}/admin/finance/routeCount.action">财务分析</a></li>
      <li role="presentation">
        <form class="form-inline" action="${contextPath}/admin/finance/export.action" method="post">
          <div class="form-group">
              <select class="form-control" id="excelYear" name="excelYear">
                <option>2016</option>
                <option>2017</option>
                <option>2018</option>
                <option>2019</option>
                <option>2020</option>
                <option>2021</option>
              </select>
          </div>
          <div class="form-group">
            <select class="form-control" id="excelMonth" name="excelMonth">
              <option value="00">请选择</option>
              <option value="01">01</option>
              <option value="02">02</option>
              <option value="03">03</option>
              <option value="04">04</option>
              <option value="05">05</option>
              <option value="06">06</option>
              <option value="07">07</option>
              <option value="08">08</option>
              <option value="09">09</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
            </select>
          </div>
          <button type="submit" class="btn btn-default">生成Excel表格</button>
        </form>
      </li>
    </ul>
  </div>
<%@include file="/WEB-INF/include/footer.jsp"%>
