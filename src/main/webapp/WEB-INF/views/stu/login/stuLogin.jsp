<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp"%>


<style type="text/css">
    #stuLoginForm {
        margin-top: 2%;
    }
</style>

<form class="form-horizontal col-sm-offset-3" action="${contextPath}/stu/login.action" method="POST" id="stuLoginForm">
    <div class="form-group">
        <label for="inputStuLoginName" class="col-sm-2 control-label">学生卡号</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="inputStuLoginName" name="stuCardNum" placeholder="111111">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-5">
            <button type="submit" class="btn btn-primary">登陆</button>
        </div>
    </div>
</form>



<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>