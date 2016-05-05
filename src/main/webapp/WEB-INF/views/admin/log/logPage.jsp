<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<%--日志管理--%>
<style>
    #log{
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;"><span class="glyphicon glyphicon-map-marker"></span>日志管理</div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <form class="form-inline" action="${contextPath}/admin/log/pageSearch.action" method="post">
                    <div class="form-group">
                        <label for="selectLogAciton">操作</label>
                        <tags:dicselect name="logAction" key="logAction" value="-1" id="selectLogAciton" />
                        <%--<select class="form-control" name="logAction" id="selectLogAciton">--%>
                        <%--<option value="-1">--请选择--</option>--%>
                        <%--<option value="1">1:检索</option>--%>
                        <%--<option value="2">2:添加</option>--%>
                        <%--<option value="3">3:删除</option>--%>
                        <%--<option value="4">4:修改</option>--%>
                        <%--<option value="5">5:其他</option>--%>
                        <%--</select>--%>
                    </div>
                    <div class="form-group">
                        <label for="selectLogLevel">日志级别</label>
                        <tags:dicselect name="logLevel" key="logLevel" value="-1" id="selectLogLevel" />
                        <%--<select class="form-control" name="logLevel" id="selectLogLevel">--%>
                        <%--<option value="-1">--请选择--</option>--%>
                        <%--<option value="1">1:提示</option>--%>
                        <%--</select>--%>
                    </div>
                    <div class="form-group">
                        <label for="inputStartTime">开始时间</label>
                        <input type="date" class="form-control" id="inputStartTime" name="startTime">
                    </div>
                    <div class="form-group">
                        <label for="inputEndTime">截止时间</label>
                        <input type="date" class="form-control" id="inputEndTime" name="endTime">
                    </div>
                    <div class="form-group">
                        <label for="inputLogUser">操作者</label>
                        <input type="text" class="form-control" id="inputLogUser" name="logUser" placeholder="admin">
                    </div>
                    <button type="submit" class="btn btn-default">检索</button>
                </form>
            </div>
        </div>

        <div class="row" style="margin-top: 1%;">
            <div class="col-md-12">
                <table class="table" id="paginationTable">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>日志编号</th>
                        <th>操作</th>
                        <th>级别</th>
                        <th>内容</th>
                        <th>操作者</th>
                        <th>时间</th>
                    </tr>
                    <c:forEach items="${page.content}" var="log">
                        <tr>
                            <td>${log.logId}</td>
                            <tags:dictd groupValue="logAction" itemKey="${log.logAction}" />
                            <tags:dictd groupValue="logLevel" itemKey="${log.logLevel}" />
                            <td>${log.logContent}</td>
                            <td>${log.logUser}</td>
                            <td>${log.logTime}</td>
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
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>