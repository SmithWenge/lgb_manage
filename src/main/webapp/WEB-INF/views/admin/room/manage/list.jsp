<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">教室管理</div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <form class="form-inline" action="${contextPath}/admin/room/manage/pageSearch.action" method="post">
                    <div class="form-group">
                        <label for="roomFloor">楼层</label>
                        <select class="form-control" name="roomFloor" id="roomFloor">
                            <option value="0">全部</option>
                            <option value="1">1层</option>
                            <option value="2">2层</option>
                            <option value="3">3层</option>
                            <option value="4">4层</option>
                            <option value="5">5层</option>
                            <option value="6">6层</option>
                            <option value="7">7层</option>
                            <option value="8">8层</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="inputRoomCapacity">人员数</label>
                        <input type="number" class="form-control" name="roomCapacity" id="inputRoomCapacity" value="0">
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>
                </form>
            </div>
        </div>

        <div class="row" style="margin-top: 1%;">
            <div class="col-md-12">
                <table class="table" id="paginationTable">
                    <tr style="background-color: #2aabd2;">
                        <th>教室编码</th>
                        <th>教室名</th>
                        <th>楼层</th>
                        <th>人数</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${page.content}" var="room">
                        <tr>
                            <td>${room.roomId}</td>
                            <td>${room.roomName}</td>
                            <td>${room.roomFloor} 层</td>
                            <td>${room.roomCapacity} 人</td>
                            <td>
                                <a href="${contextPath}/admin/room/manage/routeEdit/${room.roomId}.action" style="text-decoration: none;" >
                                    <button type="button" class="btn btn-danger">编辑</button>
                                </a>
                                <a href="${contextPath}/admin/room/manage/delete/${room.roomId}.action" style="text-decoration: none;" >
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
                                <a href="${contextPath}/admin/room/manage/page.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number <= 0 }">
                            <li class="previous disabled">
                                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 < page.totalPages }">
                            <li class="next">
                                <a href="${contextPath}/admin/room/manage/page.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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