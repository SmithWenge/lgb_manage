<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<%--教师管理--%>
<style>
    #leader{
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/course/leader/page.action"><span class="glyphicon glyphicon-map-marker"></span>班长</a></li>
            <li role="presentation"class="active" style="float: right"><a href="${contextPath}/admin/course/leader/export.action">导出班长</a></li>
        </ul>
    </div>
    <div class="panel-body">
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

        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>卡号</th>
                        <th>学员名</th>
                        <th>教室</th>
                        <th>系</th>
                        <th>专业</th>
                        <th>课程</th>
                        <th>性别</th>
                        <th>生日</th>
                        <th>电话1</th>
                        <th>电话2</th>
                        <th>座号</th>
                    </tr>
                    <c:forEach items="${page.content}" var="leader">
                        <tr>
                            <td>${leader.cardNum}</td>
                            <td>${leader.leaderName}</td>
                            <td>${leader.classRoom}</td>
                            <td>${leader.departmentName}</td>
                            <td>${leader.majorName}</td>
                            <td>${leader.courseName}</td>
                            <tags:dictd groupValue="gender" itemKey="${leader.gender}" />
                            <td>${leader.birthday}</td>
                            <td>${leader.telOne}</td>
                            <td>${leader.telTwo}</td>
                            <td>${leader.siteNum}</td>
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
                                <a href="${contextPath}/admin/course/leader/page.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number <= 0 }">
                            <li class="previous disabled">
                                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 < page.totalPages }">
                            <li class="next">
                                <a href="${contextPath}/admin/course/leader/page.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>