<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>


<style type="text/css">
    form label {
        margin-top: 5px;
    }
</style>
<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/student/page.action">学生管理</a></li>
            <li role="presentation"class="active" style="float: right"><a href="${contextPath}/admin/student/routeAdd.action">添加学员</a></li>
            <li role="presentation"class="active" style="float: right"><a href="${contextPath}/admin/student/export.action">导出Excel</a></li>
        </ul>
    </div>
    <div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
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
    </div>
    <div class="panel-body">
        <form style="margin-left: 2%; margin-right: 2%; margin-top: 1%;" action="${contextPath}/admin/student/pageSearch.action" method="get" id="stuAddForm">
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="stuCardNum" class="col-md-3 control-label">卡号</label>
                    <div class="col-md-7">
                        <input type="text" class="form-control" id="stuCardNum" placeholder="111111" name="stuCardNum">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuName" class="col-md-3 control-label">学员名称</label>
                    <div class="col-md-7">
                        <input type="text" class="form-control" id="stuName" name="stuName">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuGender" class="col-md-3 control-label">性别</label>
                    <div class="col-md-7">
                        <tags:dicselect name="stuGender" key="gender" value="1" id="stuGender" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuType" class="col-md-3 control-label">人员类别</label>
                    <div class="col-md-7">
                        <tags:dicselect name="stuType" key="stuType" value="3" id="stuType" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuBirthday" class="col-md-3 control-label">出生日期</label>
                    <div class="col-md-7">
                        <input type="date" class="form-control" id="stuBirthday" name="stuBirthday">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="studentStartDate" class="col-md-3 control-label">入学时间</label>
                    <div class="col-md-7">
                        <input type="date" class="form-control" id="studentStartDate" name="studentStartDate">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuOldWorkType" class="col-md-3 control-label">原职务</label>
                    <div class="col-md-7">
                        <tags:dicselect name="stuOldWorkType" key="stuOldWorkType" value="3" id="stuOldWorkType" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuEducational" class="col-md-3 control-label">文化程度</label>
                    <div class="col-md-7">
                        <tags:dicselect name="stuEducational" key="educational" value="2" id="stuEducational" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuIdentifiedNum" class="col-md-3 control-label">证件号码</label>
                    <div class="col-md-7">
                        <input type="text" class="form-control" id="stuIdentifiedNum" name="stuIdentifiedNum">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuPolitical" class="col-md-3 control-label">政治面貌</label>
                    <div class="col-md-7">
                        <tags:dicselect name="stuPolitical" key="stuPolitical" value="1" id="stuPolitical" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuOldWorkPlaceType" class="col-md-3 control-label">原单位类别</label>
                    <div class="col-md-7">
                        <tags:dicselect name="stuOldWorkPlaceType" key="stuOldWorkPlaceType" value="1" id="stuOldWorkPlaceType" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <div class="col-sm-offset-1 col-sm-8">
                        <button type="reset" class="btn btn-success" style="float: left">重置</button>
                        <button type="submit" class="btn btn-danger" style="float: right">检索</button>

                    </div>
                </div>
            </div>

        </form>
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable">
                    <tr style="background-color: #2aabd2;">
                        <th>卡号</th>
                        <th>学员名</th>
                        <th>性别</th>
                        <th>生日</th>
                        <th>电话1</th>
                        <th>电话2</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${page.content}" var="user">
                        <tr>
                            <td>${user.stuCardNum}</td>
                            <td>${user.stuName}</td>
                            <tags:dictd groupValue="gender" itemKey="${user.stuGender}" />
                            <td>${user.stuBirthday}</td>
                            <td>${user.stuTelOne}</td>
                            <td>${user.stuTelTwo}</td>
                            <td>
                                <a href="${contextPath}/admin/student/routeEdit/${user.stuId}.action" style="text-decoration: none;">
                                    <button type="button" class="btn btn-warning">编辑</button>
                                </a>
                                <a href="${contextPath}/admin/student/delete/${user.stuId}.action" style="text-decoration: none;" >
                                    <button type="button" class="btn btn-danger">删除</button>
                                </a>
                                <a href="${contextPath}/admin/student/baoming/${user.stuId}.action" style="text-decoration: none;" >
                                    <button type="button" class="btn btn-success">报名</button>
                                </a>
                                <a href="${contextPath}/admin/student/routeTurnCard/${user.stuId}.action" style="text-decoration: none;" >
                                    <button type="button" class="btn btn-success">换卡</button>
                                </a>
                            </td>
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
                                <a href="${contextPath}/admin/student/page.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number <= 0 }">
                            <li class="previous disabled">
                                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 < page.totalPages }">
                            <li class="next">
                                <a href="${contextPath}/admin/student/page.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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

<!--<script type="text/javascript">
    $(function () {
        $('#stuAddForm').validate({
            rules: {
                stuID: {
                    required: true
                },
                stuBirthday: {
                    required: true
                },
                stuIdentifiedNum: {
                    required: true
                },
                stuOldWorkPlaceType: {
                    required: true
                },
                stuPolitical: {
                    required: true
                },
                stuEducational: {
                    required: true
                }
            },
            messages: {
                stuID: {
                    required: "请输入卡号"
                },
                stuBirthday: {
                    required: "请填写出生日期"
                },
                stuIdentifiedNum: {
                    required: "请填写证件号码"
                },
                stuLastEightNum: {
                    required: "请填写身份证号最后8位",
                    minlength: "请填写正确的格式",
                    maxlength: "请填写正确的格式"
                },
                stuOldWorkPlaceType: {
                    required: "请填写原单位类别"
                },
                stuPolitical: {
                    required: "请填写政治面貌"
                },
                stuEducational: {
                    required: "请填写文化程度"
                }
            }
        });

        setTimeout(function() {
            $("#message").hide();
        }, 2000);
    });
</script>-->
<%@include file="/WEB-INF/include/footer.jsp"%>