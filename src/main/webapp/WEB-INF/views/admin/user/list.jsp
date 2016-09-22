<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<%--用户管理--%>
<style>
    #user_manage{
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/user/page.action"><span class="glyphicon glyphicon-map-marker"></span>用户管理</a></li>
            <li role="presentation" class="active" style="float: right"><a href="${contextPath}/admin/user/routeAdd.action">添加用户</a></li>
        </ul>
    </div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>用户名</th>
                        <th>登录名</th>
                        <th>权限</th>
                        <th>能否调班</th>
                        <th>能否退班</th>
                        <th>是否锁定</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${page.content}" var="user">
                        <tr>
                            <td>${user.adminName}</td>
                            <td>${user.adminLoginName}</td>
                            <tags:dictd groupValue="adminRole" itemKey="${user.adminRole}" />
                            <tags:dictd groupValue="adminIsChanged" itemKey="${user.adminIsChanged}" />
                            <tags:dictd groupValue="adminIsReturn" itemKey="${user.adminIsReturn}" />
                            <tags:dictd groupValue="adminIsLock" itemKey="${user.adminIsLock}" />
                            <td>
                                <a href="${contextPath}/admin/user/routeEdit/${user.adminId}.action" style="text-decoration: none;">
                                    <button type="button" class="btn btn-warning">编辑</button>
                                </a>
                                <a href="${contextPath}/admin/user/delete/${user.adminId}.action" style="text-decoration: none;" >
                                    <button type="button" class="btn btn-danger">删除</button>
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
                                <a href="${contextPath}/admin/user/page.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number <= 0 }">
                            <li class="previous disabled">
                                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 < page.totalPages }">
                            <li class="next">
                                <a href="${contextPath}/admin/user/page.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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