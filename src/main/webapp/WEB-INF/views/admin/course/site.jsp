<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<%--课程管理--%>
<style>
    #course{
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 10px;"><a href="${contextPath}/admin/course/routePage.action"><span class="glyphicon glyphicon-map-marker"></span>课程管理</a> > 座位排号</div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>系</th>
                        <th>专业</th>
                        <th>课程名</th>
                        <th>教室</th>
                        <th>学员名</th>
                        <th>学员生日</th>
                        <th>座位号</th>
                    </tr>
                    <c:forEach items="${courseSites}" var="site">
                        <tr>
                            <td>${site.departmentName}</td>
                            <td>${site.majorName}</td>
                            <td>${site.courseName}</td>
                            <tags:dictd groupValue="courseRoom" itemKey="${site.courseRoom}" />
                            <td>${site.stuName}</td>
                            <td>${site.stuBirthday}</td>
                            <td>${site.siteNum}</td>
                        </tr>
                    </c:forEach>
                </table>
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