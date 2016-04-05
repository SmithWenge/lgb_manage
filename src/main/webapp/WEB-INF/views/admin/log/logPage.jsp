<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="row"  style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="col-md-12">
        <form class="form-inline" action="${contextPath}/admin/log/pageSearch.action" method="post">
            <div class="form-group">
                <label for="selectLogAciton">操作</label>
                <select class="form-control" name="logAction" id="selectLogAciton">
                    <option value="-1">--请选择--</option>
                    <option value="1">1:检索</option>
                    <option value="2">2:添加</option>
                    <option value="3">3:删除</option>
                    <option value="4">4:修改</option>
                    <option value="5">5:其他</option>
                </select>
            </div>
            <div class="form-group">
                <label for="selectLogLevel">日志级别</label>
                <select class="form-control" name="logLevel" id="selectLogLevel">
                    <option value="-1">--请选择--</option>
                    <option value="1">1:提示</option>
                </select>
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

<div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="col-md-12">
        <table class="table" id="paginationTable">
            <tr style="background-color: #2aabd2;">
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
                    <td>${log.logActionContent}</td>
                    <td>${log.logLevelContent}</td>
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
<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>