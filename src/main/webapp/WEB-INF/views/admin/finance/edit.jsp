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
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;padding-top: 5px;"><span class="glyphicon glyphicon-map-marker"></span>收费</div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" align="center">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>学员名</th>
                        <th>系</th>
                        <th>专业</th>
                        <th>课程</th>
                        <th>学费</th>
                        <th>来源</th>
                        <%--<th>优惠</th>--%>
                        <th>性别</th>
                        <%--<th>优惠</th>--%>
                        <th>生日</th>
                        <th>电话1</th>
                        <th>电话2</th>
                    </tr>
                    <tr>
                        <td>${edit.stuName}</td>
                        <td>${edit.departmentName}</td>
                        <td>${edit.majorName}</td>
                        <td>${edit.courseName}</td>
                        <td>${edit.courseTuition}</td>
                        <tags:dictd groupValue="signUpComeFrom" itemKey="${edit.signUpComeFrom}" />
                        <%--<tags:dictd groupValue="courseDiscount" itemKey="${edit.courseDiscount}" />--%>
                        <tags:dictd groupValue="gender" itemKey="${edit.stuGender}" />
                        <%--<tags:dictd groupValue="courseDiscount" itemKey="${edit.courseDiscount}" />--%>
                        <td>${edit.stuBirthday}</td>
                        <td>${edit.telOne}</td>
                        <td>${edit.telTwo}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/finance/edit.action" method="post" id="financeEditForm">
                    <input type="hidden" name="studentCourseId" value="${edit.studentCourseId}">
                    <div class="form-group">
                        <label for="courseDiscount" class="col-sm-2 control-label">优惠</label>
                        <div class="col-sm-10">
                            <tags:dicselect name="courseDiscount" key="courseDiscount" value="${edit.courseDiscount}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="actualTuition" class="col-sm-2 control-label">收款</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="actualTuition" name="actualTuition" placeholder="300">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="tuitionFlag" class="col-sm-2 control-label">是否付款</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="tuitionFlag" id="tuitionFlag" value="1">已付
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="tuitionFlag" value="0" checked>未付
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
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #2aabd2;">
                        <th>说明</th>
                        <th>学员名</th>
                        <th>系</th>
                        <th>专业</th>
                        <th>课程</th>
                        <th>学费</th>
                        <th>优惠</th>
                        <th>实际收费</th>
                        <th>收款时间</th>
                    </tr>
                    <c:forEach items="${finances}" var="finance">
                        <tr>
                            <td>已交款</td>
                            <td>${finance.stuName}</td>
                            <td>${finance.departmentName}</td>
                            <td>${finance.majorName}</td>
                            <td>${finance.courseName}</td>
                            <td>${finance.courseTuition}</td>
                            <tags:dictd groupValue="courseDiscount" itemKey="${finance.courseDiscount}" />
                            <td>${finance.actualTuition}</td>
                            <td>${finance.financeTime}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        $('#financeEditForm').validate({
            rules: {
                actualTuition: {
                    required: true
                }
            },
            messages: {
                actualTuition   : {
                    required: "请填写费用."
                }
            }
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>