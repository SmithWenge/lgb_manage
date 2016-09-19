<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>


<style type="text/css">
    form label {
        margin-top: 5px;
    }
</style>

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
    <div class="panel-heading" style="height: 45px;padding-top: 5px;">
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="${contextPath}/admin/student/page.action"><span class="glyphicon glyphicon-map-marker"></span> 学生管理</a></li>
            <li role="presentation" style="float: right"><a href="${contextPath}/admin/student/routeAdd.action">添加学员</a></li>
            <li role="presentation" style="float: right"><a href="${contextPath}/admin/disciplinary/page.action">违纪学员管理</a></li>
            <li role="presentation" style="float: right"><a href="${contextPath}/admin/student/export.action">学员导出Excel</a></li>
            <li role="presentation" style="float: right"><a href="${contextPath}/admin/excel/routeImport.action">学员导入</a></li>
        </ul>
    </div>

    <div class="panel-body">

        <form action="${contextPath}/admin/student/pageSearch.action" method="post" id="stuAddForm">
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="stuCardNum" class="col-md-4 control-label">卡号</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="stuCardNum" placeholder="请刷卡" name="stuCardNum">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuName" class="col-md-4 control-label">学员名称</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="stuName" name="stuName">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuGender" class="col-md-4 control-label">性别</label>
                    <div class="col-md-8">
                        <tags:dicselect name="stuGender" key="gender" value="-1" id="stuGender" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuType" class="col-md-4 control-label">人员类别</label>
                    <div class="col-md-8">
                        <tags:dicselect name="stuType" key="stuType" value="-1" id="stuType" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuBirthday" class="col-md-4 control-label">出生日期</label>
                    <div class="col-md-8">
                        <input type="date" class="form-control" id="stuBirthday" name="stuBirthday">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="studentStartDate" class="col-md-4 control-label">入学时间</label>
                    <div class="col-md-8">
                        <input type="date" class="form-control" id="studentStartDate" name="studentStartDate">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuTelOne" class="col-md-4 control-label">联系电话1</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="stuTelOne" name="stuTelOne">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuNationality" class="col-md-4 control-label">民族</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="stuNationality" name="stuNationality" placeholder="汉族">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuLevel" class="col-md-4 control-label">级别</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="stuLevel" name="stuLevel">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuOldWorkType" class="col-md-4 control-label">原职务</label>
                    <div class="col-md-8">
                        <tags:dicselect name="stuOldWorkType" key="stuOldWorkType" value="-1" id="stuOldWorkType" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuEducational" class="col-md-4 control-label">文化程度</label>
                    <div class="col-md-8">
                        <tags:dicselect name="stuEducational" key="educational" value="-1" id="stuEducational" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuIdentifiedNum" class="col-md-4 control-label">证件号码</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="stuIdentifiedNum" name="stuIdentifiedNum">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuPolitical" class="col-md-4 control-label">政治面貌</label>
                    <div class="col-md-8">
                        <tags:dicselect name="stuPolitical" key="stuPolitical" value="-1" id="stuPolitical" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuOldWorkPlaceType" class="col-md-4 control-label">原单位类别</label>
                    <div class="col-md-8">
                        <tags:dicselect name="stuOldWorkPlaceType" key="stuOldWorkPlaceType" value="-1" id="stuOldWorkPlaceType" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuPreferential" class="col-md-4 control-label">优惠</label>
                    <div class="col-md-8">
                        <tags:dicselect name="stuPreferential" key="courseDiscount" value="10" id="stuPreferential" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuDependentsTel" class="col-md-4 control-label">家属电话</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="stuDependentsTel" name="stuDependentsTel">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuOldWorkPlaceName" class="col-md-4 control-label">原工作单位</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="stuOldWorkPlaceName" name="stuOldWorkPlaceName">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuHealth" class="col-md-4 control-label">身体状况</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="stuHealth" name="stuHealth">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuDependentsDesc" class="col-md-4 control-label">家属关系</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="stuDependentsDesc" name="stuDependentsDesc">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="stuLocation" class="col-md-4 control-label">现住址</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="stuLocation" name="stuLocation">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <div class="col-sm-offset-1 col-sm-8">
                        <button type="reset" class="btn btn-danger" style="float: left">重置</button>
                        <button type="submit" class="btn btn-success" style="float: right">检索</button>

                    </div>
                </div>
            </div>
        </form>
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
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
                                <%--<a href="${contextPath}/admin/student/baoming/${user.stuId}.action" style="text-decoration: none;" >--%>
                                    <%--<button type="button" class="btn btn-success">报名</button>--%>
                                <%--</a>--%>
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

<%@include file="/WEB-INF/include/footer.jsp"%>