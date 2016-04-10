<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">收费</div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" align="center">
                    <tr style="background-color: #2aabd2;">
                        <th>学员名</th>
                        <th>性别</th>
                        <th>优惠</th>
                        <th>生日</th>
                        <th>电话1</th>
                        <th>电话2</th>
                    </tr>
                    <tr>
                        <td>${edit.stuName}</td>
                        <tags:dictd groupValue="gender" itemKey="${edit.stuGender}" />
                        <tags:dictd groupValue="courseDiscount" itemKey="${edit.courseDiscount}" />
                        <td>${edit.stuBirthday}</td>
                        <td>${edit.telOne}</td>
                        <td>${edit.telTwo}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="row" >
            <c:if test="${not empty addFailureMessage}">
                <div class="col-md-12" id="message">
                    <p class="bg-danger">${addFailureMessage}</p>
                </div>
            </c:if>
            <div class="col-md-12">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox"> Remember me
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Sign in</button>
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
        $('#majorEditForm').validate({
            rules: {
                majorName: {
                    required: true,
                    minlength: 2,
                    maxlength: 10
                }
            },
            messages: {
                majorName: {
                    required: "请填写专业名.",
                    minlength: "专业名的长度为2到10.",
                    maxlength: "专业名的长度为2到10."
                }
            }
        });

        setTimeout(function() {
            $("#message").hide();
        }, 2000);
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>