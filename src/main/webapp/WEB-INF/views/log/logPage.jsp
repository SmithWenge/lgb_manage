<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp"%>

<table class="table" id="paginationTable">
    <tr>
        <td></td>
        <td>日志编号</td>
        <td>操作</td>
        <td>级别</td>
        <td>内容</td>
        <td>操作者</td>
        <td>时间</td>
    </tr>
    <c:forEach items="${page.content}" var="log">
        <tr>
            <td></td>
            <td>${log.logId}</td>
            <td>${log.logActionContent}</td>
            <td>${log.logLevelContent}</td>
            <td>${log.logContent}</td>
            <td>${log.logUser}</td>
            <td>${log.logTime}</td>
        </tr>
    </c:forEach>
</table>
<nav>
    <ul class="pagination">
        <li>
            <a aria-label="Previous" onclick="previousPage();">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <li><a>总页数: ${page.totalPages}</a></li>
        <li><a>本页记录数: ${page.totalElements}</a></li>
        <li><a>当前页: ${page.number + 1}</a></li>
        <li><input id="pageNum" type="hidden" value="${page.number}"></li>
        <li>
            <a aria-label="Next" onclick="nextPage();">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>
<script type="text/javascript">
    var $num = parseInt($("#pageNum").val());

    function nextPage() {
        var $nextNum = $num + 1;
        $.ajax({
            url : "${reqBaseURL}/admin/log/page.action?page=" + $nextNum,
            type : "get",
            dataType : "text",
            contentType : "application/json; charset=utf-8",
            success : function(result) {
                var data = JSON.parse(result);
                var $paginationTable = $("#paginationTable");

                $("#pageNum").val(data.page.number);
                if ($paginationTable.size() > 0) {
                    $paginationTable.find("tr:not(:first)").remove();
                }

                var pageContent = "";
                console.log(data.page.content);
                $.each(data.page.content, function(i, item) {
                    pageContent += "<tr><td>" + item.name + "</td><td>"
                            + item.age + "</td><td>" + item.gender + "</td><td>"
                            + item.college + "</td><td>" + item.grade + "</td><td>"
                            + item.phone + "</td><td>" + item.email + "</td><td>"
                            + item.qq + "</td><td>" + item.dormitory + "</td></tr>";
                });

                $("#paginationTable").append(pageContent);
                console.log(data);
            }
        });
    }

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>