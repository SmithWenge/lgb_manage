<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp"%>


<style type="text/css">
    #adminPasswordForm {
        margin-top: 2%;
    }
</style>

<form class="form-horizontal col-sm-offset-3" action="${contextPath}/admin/password.action" method="post" id="adminPasswordForm">
    <div class="form-group">
        <label for="adminLoginName" class="col-sm-2 control-label">管理员用户</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="adminLoginName" name="adminLoginName" value="${adminLogin.adminLoginName}" readonly>
        </div>
    </div>
    <div class="form-group">
        <label for="adminLoginPass" class="col-sm-2 control-label">管理员原密码</label>
        <div class="col-sm-5">
            <input type="password" class="form-control" id="adminLoginPass" name="adminLoginPass" placeholder="密码">
        </div>
    </div>
    <div class="form-group">
        <label for="adminLoginPassNew" class="col-sm-2 control-label">管理员新密码</label>
        <div class="col-sm-5">
            <input type="password" class="form-control" id="adminLoginPassNew" name="adminLoginPassNew" placeholder="新密码">
        </div>
    </div>
    <div class="form-group">
        <label for="adminLoginPassNewRe" class="col-sm-2 control-label">管理员新密码</label>
        <div class="col-sm-5">
            <input type="password" class="form-control" id="adminLoginPassNewRe" name="adminLoginPassNewRe" placeholder="再次输入密码">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-5">
            <button type="submit" class="btn btn-primary">更改密码</button>
        </div>
    </div>
</form>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function() {
        $("#adminPasswordForm").validate({
            rules: {
                adminLoginPassNew: {
                    required: true,
                    minlength: 5,
                    maxlength: 20
                },
                adminLoginPassNewRe: {
                    required: true,
                    minlength: 5,
                    maxlength: 20,
                    equalTo: '#adminLoginPassNew'
                }
            },
            messages: {
                adminLoginPassNew: {
                    required: "请输入新密码.",
                    minlength: "请确定新密码的长度为5到20之间.",
                    maxlength: "请确定新密码的长度为5到20之间."
                },
                adminLoginPassNewRe: {
                    required: "请输入新密码.",
                    minlength: "请确定新密码的长度为5到20之间.",
                    maxlength: "请确定新密码的长度为5到20之间.",
                    equalTo: "请保证两次输入的新密码一样."
                }
            }
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>