<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<%--财务管理--%>
<style>
    #finance{
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="panel panel-default" style="float: left;width: 90%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;">
        <a href="${contextPath}/admin/finance/refund/route.action"><span class="glyphicon glyphicon-map-marker"></span>退课</a> &nbsp; <b>></b> &nbsp; 退款
        <%--<span class="glyphicon glyphicon-map-marker"></span>收费--%>
    </div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #2aabd2;">
                        <th>学生名</th>
                        <th>课程名</th>
                        <th>课程费用</th>
                        <th>实际缴费</th>
                        <th>操作人员</th>
                        <th>是/否打印发票</th>
                        <th>发票号</th>
                        <th>是/否缴费</th>

                    </tr>
                    <tr>
                        <td>${finance.stuName}</td>
                        <td>${finance.courseName}</td>
                        <td>${finance.courseTuition}</td>
                        <td>${finance.actualTuition}</td>
                        <td>${finance.financeUser}</td>
                        <tags:dictd groupValue="billFlag" itemKey="${finance.billFlag}" />
                        <td>${finance.billNumber}</td>
                        <tags:dictd groupValue="tuitionFlag" itemKey="${finance.tuitionFlag}" />
                    </tr>
                </table>
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/finance/refund/newRefund.action" method="post" id="refundForm">
                    <input type="hidden" name="studentCourseId" value="${finance.studentCourseId}">
                    <input type="hidden" name="studentId" value="${finance.studentId}">
                    <input type="hidden" name="courseId" value="${finance.courseId}">
                    <div class="form-group">
                        <label for="refundMoney" class="col-sm-2 control-label">退款费</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="refundMoney" name="refundMoney" value="${finance.actualTuition}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="refundTuitionFlag" class="col-sm-2 control-label">是否退款</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="refundTuitionFlag" id="refundTuitionFlag" value="1">已退
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="tuitionFlag" value="0" checked>未退
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-success">收款</button>
                            <button type="button" id="backMark" class="btn btn-default" style="margin-left: 100px">返回</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="alert alert-warning" role="alert">${config.refundMessage}</div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        $('#refundForm').validate({
            rules: {
                refundMoney: {
                    required: true
                }
            },
            messages: {
                refundMoney   : {
                    required: "请填写退款费."
                }
            }
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>