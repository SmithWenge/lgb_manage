<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="col-md-12" id="addMessage">
        <p class="bg-success">
            <c:if test="${not empty addMessage}">
                ${addMessage}
            </c:if>
        </p>
    </div>
    <div class="col-md-12" id="editMessage">
        <p class="bg-success">
            <c:if test="${not empty editMessage}">
                ${editMessage}
            </c:if>
        </p>
    </div>
    <div class="col-md-12" id="deleteMessage">
        <p class="bg-success">
            <c:if test="${not empty deleteMessage}">
                ${deleteMessage}
            </c:if>
        </p>
    </div>
    <div class="col-md-12" id="deleteFailureMessage">
        <p class="bg-danger">
            <c:if test="${not empty deleteFailureMessage}">
                ${deleteFailureMessage}
            </c:if>
        </p>
    </div>
    <div class="col-md-12">
        <a href="${contextPath}/admin/user/routeAdd.action" style="text-decoration: none;">
            <button type="button" class="btn btn-info" style="float: right;">添加</button>
        </a>
    </div>
</div>

<div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 5px;">
    <div class="col-md-12">
        <table class="table" id="paginationTable" align="center">
            <tr style="background-color: #2aabd2;">
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
                        <a href="${contextPath}/admin/log/page.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
                    </li>
                </c:if>
                <c:if test="${page.number <= 0 }">
                    <li class="previous disabled">
                        <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
                    </li>
                </c:if>
                <c:if test="${page.number + 1 < page.totalPages }">
                    <li class="next">
                        <a href="${contextPath}/admin/log/page.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>