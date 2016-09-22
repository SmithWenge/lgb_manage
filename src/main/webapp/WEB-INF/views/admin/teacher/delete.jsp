<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<%--教师管理--%>
<style>
    #teacher{
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 10px;"><a href="${contextPath}/admin/teacher/page.action"><span class="glyphicon glyphicon-map-marker"></span>教师管理</a> > 教师解聘</div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/teacher/delete.action" method="post" id="teacherDelete">
                    <input type="hidden" name="teacherId" value="${delete.teacherId}">
                    <div class="form-group">
                        <label for="teacherName" class="col-sm-2 control-label">教师名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="teacherName" value="${delete.teacherName}" name="teacherName" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="teacherOverDesc" class="col-md-2 control-label">解聘理由</label>
                        <div class="col-md-10">
                            <textarea class="form-control" rows="3" id="teacherOverDesc" name="teacherOverDesc" placeholder="解聘理由"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">解聘</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<%@include file="/WEB-INF/include/footer.jsp"%>