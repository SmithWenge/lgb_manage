<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<%--学生管理--%>
<style>
    #student{
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;">学生换卡</div>
    <div class="panel-body">
        <div class="row">
            <c:if test="${not empty turnCardFailureMessage}">
                <div class="col-md-12" id="message">
                    <p class="bg-danger">${turnCardFailureMessage}</p>
                </div>
            </c:if>
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/student/turnCard.action" method="post" id="studentTurnCard">
                    <input type="hidden" name="stuId" value="${studentUser.stuId}">
                    <div class="form-group">
                        <label for="stuName" class="col-sm-2 control-label">教师名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="stuName" value="${studentUser.stuName}" name="stuName" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="stuCardNum" class="col-sm-2 control-label">原卡号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="stuCardNum" name="stuCardNum">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="stuCardNumNew" class="col-sm-2 control-label">新卡号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="stuCardNumNew" name="stuCardNumNew">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="stuCardNumNewRe" class="col-sm-2 control-label">轻刷新卡</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="stuCardNumNewRe" name="stuCardNumNewRe">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">换卡</button>
                            <button type="button" id="backMark" class="btn btn-default" style="margin-left: 100px">返回</button>
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
        $('#studentTurnCard').validate({
            rules: {
                stuCardNum: {
                    required: true
                },
                stuCardNumNew: {
                    required: true
                },
                stuCardNumNewRe: {
                    required: true,
                    equalTo: '#stuCardNumNew'
                }
            },
            messages: {
                stuCardNum: {
                    required: "请填写卡号."
                },
                stuCardNumNew: {
                    required: "请填写新卡号."
                },
                stuCardNumNewRe: {
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