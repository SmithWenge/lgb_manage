<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/teacher/page.action">教师管理</a></li>
            <li role="presentation"class="active" style="float: right"><a href="${contextPath}/admin/teacher/routeAdd.action">添加教师</a></li>
        </ul>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-12">
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
                <c:if test="${not empty turnCardMessage}">
                    <div class="col-md-12" id="turnCardMessage">
                        <p class="bg-success">${turnCardMessage}</p>
                    </div>
                </c:if>
                <form style="margin-left: 2%; margin-right: 2%; margin-top: 1%;" action="${contextPath}/admin/teacher/pageSearch.action" method="post">

                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="teacherName" class="col-md-4 control-label">教师姓名</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="teacherName" name="teacherName">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherCardNum" class="col-md-4 control-label">卡号</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="teacherCardNum" name="teacherCardNum">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherGender" class="col-md-4 control-label">性别</label>
                            <div class="col-md-8">
                                <tags:dicselect name="teacherGender" key="gender" value="-1" id="teacherGender" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherWorkDate" class="col-md-4 control-label">聘用时间</label>
                            <div class="col-md-8">
                                <input type="date" class="form-control" id="teacherWorkDate" name="teacherWorkDate" placeholder="2016-04-05">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherState" class="col-md-4 control-label">状态</label>
                            <div class="col-md-8">
                                <tags:dicselect name="teacherState" key="teacherState" value="1" id="teacherState" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherBirthday" class="col-md-4 control-label">出生日期</label>
                            <div class="col-md-8">
                                <input type="date" class="form-control" id="teacherBirthday" placeholder="2016-04-05" name="teacherBirthday">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="departmentId" class="col-md-4 control-label">所属系</label>
                            <div class="col-md-8">
                                <select class="form-control" id="departmentId" name="departmentId">
                                    <c:forEach items="${departments}" var="department">
                                        <option value="${department.departmentId}">${department.departmentName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherIdentifiedCardNum" class="col-md-4 control-label">身份证号</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="teacherIdentifiedCardNum" placeholder="11111111111111111111" name="teacherIdentifiedCardNum">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherOldWorkplace" class="col-md-4 control-label">原工作单位</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="teacherOldWorkplace" placeholder="大连市" name="teacherOldWorkplace">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherTitle" class="col-md-4 control-label">职称</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="teacherTitle" placeholder="教授" name="teacherTitle">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherSchool" class="col-md-4 control-label">毕业院校</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="teacherSchool" placeholder="大连理工大学" name="teacherSchool">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherEducational" class="col-md-4 control-label">学历</label>
                            <div class="col-md-8">
                                <tags:dicselect name="teacherEducational" key="educational" value="2" id="teacherEducational" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherMajor" class="col-md-4 control-label">专业</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="teacherMajor" placeholder="计算机" name="teacherMajor">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherFamilyName" class="col-md-4 control-label">家属姓名</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="teacherFamilyName" placeholder="家属姓名" name="teacherFamilyName">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherFamilyTel" class="col-md-4 control-label">家属电话</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="teacherFamilyTel" placeholder="家属电话" name="teacherFamilyTel">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherSubject" class="col-md-4 control-label">拟任教学科</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="teacherSubject" placeholder="声乐,钢琴" name="teacherSubject">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherTel" class="col-md-4 control-label">电话号</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="teacherTel" placeholder="12345678901" name="teacherTel">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="teacherHealth" class="col-md-4 control-label">身体状况</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="teacherHealth" placeholder="健康" name="teacherHealth">
                            </div>
                        </div>
                        <div class="col-md-offset-4 col-md-4 form-group">
                            <div class="col-sm-offset-1 col-sm-20">
                                <button type="reset" class="btn btn-danger" >重置</button>
                                <button type="submit" class="btn btn-success" style="float: right">检索</button>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable">
                    <tr style="background-color: #2aabd2;">
                        <th>状态</th>
                        <th>卡号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>生日</th>
                        <th>联系电话</th>
                        <th>拟任教科目</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${page.content}" var="teacher">
                        <tr>
                            <tags:dictd groupValue="teacherState" itemKey="${teacher.teacherState}" />
                            <td>${teacher.teacherCardNum}</td>
                            <td>${teacher.teacherName}</td>
                            <tags:dictd groupValue="gender" itemKey="${teacher.teacherGender}" />
                            <td>${teacher.teacherBirthday}</td>
                            <td>${teacher.teacherTel}</td>
                            <td>${teacher.teacherSubject}</td>
                            <td>
                                <a href="${contextPath}/admin/teacher/routeTurnCard/${teacher.teacherId}.action" style="text-decoration: none;">
                                    <button type="button" class="btn btn-success">换卡</button>
                                </a>
                                <a href="${contextPath}/admin/teacher/routeEdit/${teacher.teacherId}.action" style="text-decoration: none;">
                                    <button type="button" class="btn btn-warning">编辑</button>
                                </a>
                                <a href="${contextPath}/admin/teacher/routeDelete/${teacher.teacherId}.action" style="text-decoration: none;" >
                                    <button type="button" class="btn btn-danger">解聘</button>
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
                                <a href="${contextPath}/admin/teacher/page.action?page=${page.number - 1}"><span aria-hidden="true">&larr;</span> 上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number <= 0 }">
                            <li class="previous disabled">
                                <a href="#"><span aria-hidden="true">&larr;</span>上一页</a>
                            </li>
                        </c:if>
                        <c:if test="${page.number + 1 < page.totalPages }">
                            <li class="next">
                                <a href="${contextPath}/admin/teacher/page.action?page=${page.number + 1}">下一页 <span aria-hidden="true">&rarr;</span></a>
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

<script type="text/javascript">
    $(function() {
        // 设置table表格中的行高
        var $height = $('#paginationTable td').height() + 'px';
        $('#paginationTable td').css('line-height', $height);

        setTimeout(function() {
            $("#addMessage").hide();
        }, 2000);
        setTimeout(function() {
            $("#editMessage").hide();
        }, 2000);
        setTimeout(function() {
            $("#deleteMessage").hide();
        }, 2000);
        setTimeout(function() {
            $("#deleteFailureMessage").hide();
        }, 2000);
        setTimeout(function() {
            $("#turnCardMessage").hide();
        }, 2000);
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>