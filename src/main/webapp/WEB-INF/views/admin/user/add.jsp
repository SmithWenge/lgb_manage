<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<%--添加用户--%>
<style>
    #user_manage{
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 10px;"><a href="${contextPath}/admin/user/page.action"><span class="glyphicon glyphicon-map-marker"></span>用户管理</a> > 添加用户</div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <c:if test="${not empty addFailureMessage}">
                <div class="col-md-12" id="message">
                    <p class="bg-danger">${addFailureMessage}</p>
                </div>
            </c:if>
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/user/add.action" method="post" id="userAddForm">
                    <div class="form-group">
                        <label for="inputAdminName" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputAdminName" placeholder="姓名" name="adminName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputAdminLoginName" class="col-sm-2 control-label">登陆名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputAdminLoginName" placeholder="登录名" name="adminLoginName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputAdminLoginPass" class="col-sm-2 control-label">登陆密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputAdminLoginPass" placeholder="password" name="adminLoginPass">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputAdminLoginPassRe" class="col-sm-2 control-label">登录密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputAdminLoginPassRe" placeholder="password" name="adminLoginPassRe">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputAdminRole" class="col-sm-2 control-label">权限</label>
                        <div class="col-sm-10">
                            <tags:dicselect name="adminRole" key="adminRole" value="1" id="inputAdminRole" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputAdminIsChanged" class="col-sm-2 control-label">能否调班</label>
                        <div class="col-sm-10">
                            <tags:dicselect name="adminIsChanged" key="adminIsChanged" value="0" id="inputAdminIsChanged" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputAdminIsReturn" class="col-sm-2 control-label">能否退班</label>
                        <div class="col-sm-10">
                            <tags:dicselect name="adminIsReturn" key="adminIsReturn" value="0" id="inputAdminIsReturn" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputAdminEmail" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="inputAdminEmail" placeholder="admin@example.com" name="adminEmail">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">添加人员</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        $('#userAddForm').validate({
            rules: {
                adminName: {
                    required: true,
                    minlength: 2,
                    maxlength: 10
                },
                adminLoginName: {
                    required: true,
                    minlength: 2,
                    maxlength: 10
                },
                adminLoginPass: {
                    required: true,
                    minlength: 5,
                    maxlength: 20
                },
                adminLoginPassRe: {
                    required: true,
                    minlength: 5,
                    maxlength: 20,
                    equalTo: '#inputAdminLoginPass'
                },
                adminEmail: {
                    required: true
                }
            },
            messages: {
                adminName: {
                    required: "请填写姓名.",
                    minlength: "姓名的长度为2到10.",
                    maxlength: "姓名的长度为2到10."
                },
                adminLoginName: {
                    required: "请填写登录名.",
                    minlength: "姓名的长度为2到10.",
                    maxlength: "姓名的长度为2到10."
                },
                adminLoginPass: {
                    required: "请填写登录密码.",
                    minlength: "登录密码的长度为5到20.",
                    maxlength: "登录密码的长度为5到20."
                },
                adminLoginPassRe: {
                    required: "请再次填写登陆密码.",
                    minlength: "登录密码的长度为5到20.",
                    maxlength: "登录密码的长度为5到20.",
                    equalTo: "请保证两次密码相同."
                },
                adminEmail: {
                    required: "请填写邮箱."
                }
            }
        });

        setTimeout(function() {
            $("#message").hide();
        }, 2000);
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>