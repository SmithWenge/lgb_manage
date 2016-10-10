<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<%-- 查询该学生的课程,用于提供更换课程服务 --%>
<!-- style="margin-left: 16%-->
<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%;">
    <div class="panel-heading" style="float: left;width: 85%;">
        <a href="${contextPath}/admin/finance/refund/route.action"><span class="glyphicon glyphicon-map-marker"></span>换课</a> &nbsp; <b>></b> &nbsp; 查询所有课程
        <a href="${contextPath}/admin/finance/refund/oldRefund.action" style="float: right;">查看换课</a>
    </div>
    <div class="panel-body" style="float: right;width: 85%;">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/course/change/query.action" method="post" id="refundQueryForm">
                    <div class="form-group">
                        <label for="stuCardNum" class="col-sm-2 control-label">学员号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="stuCardNum" placeholder="请输入学员卡号" name="stuCardNum">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">查询</button>
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
        $('#refundQueryForm').validate({
            rules: {
                stuCardNum: {
                    required: true
                }
            },
            messages: {
                stuCardNum   : {
                    required: "请填写学号."
                }
            }
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>