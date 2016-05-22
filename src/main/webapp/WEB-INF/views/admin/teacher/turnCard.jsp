<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

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
    <div class="panel-heading" style="height: 45px;padding-top: 10px;"><a href="${contextPath}/admin/teacher/page.action"><span class="glyphicon glyphicon-map-marker"></span>教师管理</a> > 教师换卡</div>
    <div class="panel-body">
        <div class="row">
            <c:if test="${not empty turnCardFailureMessage}">
                <div class="col-md-12" id="message">
                    <p class="bg-danger">${turnCardFailureMessage}</p>
                </div>
            </c:if>
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/teacher/turnCard.action" method="post" id="teacherTurnCard">
                    <input type="hidden" name="teacherId" value="${teacher.teacherId}">
                    <div class="form-group">
                        <label for="teacherName" class="col-sm-2 control-label">教师名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="teacherName" value="${teacher.teacherName}" name="teacherName" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="teacherCardNum" class="col-sm-2 control-label">原卡号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="teacherCardNum" name="teacherCardNum">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="teacherCardNumNew" class="col-sm-2 control-label">新卡号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="teacherCardNumNew" name="teacherCardNumNew">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="teacherCardNumNewRe" class="col-sm-2 control-label">轻刷新卡</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="teacherCardNumNewRe" name="teacherCardNumNewRe">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">换卡</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        $('#teacherTurnCard').validate({
            rules: {
                teacherCardNum: {
                    required: true
                },
                teacherCardNumNew: {
                    required: true
                },
                teacherCardNumNewRe: {
                    required: true,
                    equalTo: '#teacherCardNumNew'
                }
            },
            messages: {
                teacherCardNum: {
                    required: "请填写卡号."
                },
                teacherCardNumNew: {
                    required: "请填写新卡号."
                },
                teacherCardNumNewRe: {
                    required: "请填写新卡号",
                    equalTo: '确定新卡号一样.'
                }
            }
        });

        setTimeout(function() {
            $("#message").hide();
        }, 2000);
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>