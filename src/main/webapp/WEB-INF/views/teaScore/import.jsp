<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navsOfTeaScore.jsp"%>

<div class="panel panel-default" style="float: left;width: 100%;">
  <div class="panel-heading" style="height: 45px;padding-top: 5px;">
    <ul class="nav nav-pills">
      <li role="presentation" ><a href="${contextPath}/teacher/score/routerImport.action"><span class="glyphicon glyphicon-map-marker"></span>导入成绩</a></li>
      <li role="presentation" style="float: right;"><a href="${contextPath}/teacher/score/download.action">获取模板</a></li>
    </ul>
  </div>
  <div class="panel-body">
    <ul>
      <li>1. 首先下载录入数据的模板</li>
      <li>2. 按着模板中的需要的字段(添加相关数据)</li>
      <li>3. 确定文件中数据的正确性,<b>是否有不合法数据,例如空值</b>,确定数据的<b>对应关系</b>正确.</li>
      <li>4. 确定文件文件大小<b>不能大于10M</b></li>
      <li>5. 确保每次录入的条目<b>不能大于500</b>条</li>
      <li>6. 请确保<b>学号</b>,<b>课程号</b>,<b>学生成绩</b>输入准确</li>
      <li>7. 请确保填写数据的边界正确,<b>即不要存在没有数据的列</b></li>
      <li>8. 如果对已经有成绩的同学再次录入成绩,该同学成绩会修改为<b>第二次录入的分值</b></li>
    </ul>
  </div>
  <div class="panel-footer">
    <form class="form-inline" action="${contextPath}/teacher/score/import.action" method="post"
          enctype="multipart/form-data">
      <div class="form-group">
        <label for="inputFile">添加数据文件</label>
        <input type="file" id="inputFile" name="file">
        <p class="help-block">请确保添加正确文件</p>
      </div>
      <button type="submit" class="btn btn-default">导入数据</button>
    </form>
  </div>

</div>



<%@include file="/WEB-INF/include/javascript.jsp"%>


<%@include file="/WEB-INF/include/footer.jsp"%>