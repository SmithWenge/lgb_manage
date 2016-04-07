<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/teacher/page.action">教师管理</a></li>
            <li role="presentation"class="active" style="float: right"><a href="${contextPath}/admin/teacher/routeAdd.action">添加教师</a></li>
        </ul>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-12">
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
                <c:if test="${not empty turnCardMessage}">
                    <div class="col-md-12" id="turnCardMessage">
                        <p class="bg-success">${turnCardMessage}</p>
                    </div>
                </c:if>
                <form class="form-inline" action="${contextPath}/admin/teacher/pageSearch.action" method="post">
                    <div class="form-group">
                        <label for="teacherName">教师姓名</label>
                        <input type="text" class="form-control" id="teacherName" name="teacherName">
                    </div>
                    <div class="form-group">
                        <label for="teacherCardNum">卡号</label>
                        <input type="text" class="form-control" id="teacherCardNum" name="teacherCardNum">
                    </div>
                    <div class="form-group">
                        <label for="teacherGender">性别</label>
                        <tags:dicselect name="teacherGender" key="gender" value="-1" id="teacherGender" />
                    </div>
                    <div class="form-group">
                        <label for="teacherWorkDate">聘用时间</label>
                        <input type="date" class="form-control" id="teacherWorkDate" name="teacherWorkDate" placeholder="2016-04-05">
                    </div>
                    <button type="submit" class="btn btn-default">检索</button>
                </form>
            </div>
        </div>

        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable">
                    <tr style="background-color: #2aabd2;">
                        <th>状态</th>
                        <th>卡号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>生日</th>
                        <th>联系电话</th>
                        <th>拟任教科目</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${page.content}" var="teacher">
                        <tr>
                            <tags:dictd groupValue="teacherState" itemKey="${teacher.teacherState}" />
                            <td>${teacher.teacherCardNum}</td>
                            <td>${teacher.teacherName}</td>
                            <tags:dictd groupValue="gender" itemKey="${teacher.teacherGender}" />
                            <td>${teacher.teacherBirthday}</td>
                            <td>${teacher.teacherTel}</td>
                            <td>${teacher.teacherSubject}</td>
                            <td>
                                <a href="${contextPath}/admin/teacher/routeTurnCard/${teacher.teacherId}.action" style="text-decoration: none;">
                                    <button type="button" class="btn btn-success">换卡</button>
                                </a>
                                <a href="${contextPath}/admin/teacher/routeEdit/${teacher.teacherId}.action" style="text-decoration: none;">
                                    <button type="button" class="btn btn-warning">编辑</button>
                                </a>
                                <a href="${contextPath}/admin/teacher/routeDelete/${teacher.teacherId}.action" style="text-decoration: none;" >
                                    <button type="button" class="btn btn-danger">解聘</button>
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
                                <a href="${contextPath}/admin/teacher/page.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number <= 0 }">
                            <li class="previous disabled">
                                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 < page.totalPages }">
                            <li class="next">
                                <a href="${contextPath}/admin/teacher/page.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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
            $("#turnCardMessage").hide();
        }, 2000);
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>