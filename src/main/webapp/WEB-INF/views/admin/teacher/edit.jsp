<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

    <c:if test="${not empty addFailureMessage}">
    <div class="col-md-12" id="message">
        <p class="bg-danger">${addFailureMessage}</p>
    </div>
    </c:if>

    <style type="text/css">
        form label {
            margin-top: 5px;
        }
    </style>

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
    <div class="panel-heading" style="height: 45px;padding-top: 10px;"><a href="${contextPath}/admin/teacher/page.action"><span class="glyphicon glyphicon-map-marker"></span>教师管理</a> > 教师编辑</div>
    <div class="panel-body">
        <form id="teacherEditForm" action="${contextPath}/admin/teacher/edit.action" method="post">
            <input type="hidden" name="teacherId" value="${edit.teacherId}">
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="teacherCardNum" class="col-md-3 control-label">卡号</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherCardNum" name="teacherCardNum" value="${edit.teacherCardNum}" readonly>
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="departmentId" class="col-md-3 control-label">所属系</label>
                    <div class="col-md-9">
                        <select class="form-control" id="departmentId" name="departmentId">
                            <c:forEach items="${departments}" var="department">
                                <c:if test="${edit.departmentId == department.departmentId}">
                                    <option value="${department.departmentId}" selected>${department.departmentName}</option>
                                </c:if>
                                <c:if test="${edit.departmentId != department.departmentId}">
                                    <option value="${department.departmentId}">${department.departmentName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherState" class="col-md-3 control-label">教师状态</label>
                    <div class="col-md-9">
                        <tags:dicselect name="teacherState" key="teacherState" value="${edit.teacherState}" id="teacherState" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="teacherGender" class="col-md-3 control-label">性别</label>
                    <div class="col-md-9">
                        <tags:dicselect name="teacherGender" key="gender" value="${edit.teacherGender}" id="teacherGender" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherBirthday" class="col-md-3 control-label">出生日期</label>
                    <div class="col-md-9">
                        <input type="date" class="form-control" id="teacherBirthday" value="${edit.teacherBirthday}" name="teacherBirthday">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherIdentifiedCardNum" class="col-md-3 control-label">身份证号</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherIdentifiedCardNum" value="${edit.teacherIdentifiedCardNum}" name="teacherIdentifiedCardNum">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="teacherWorkDate" class="col-md-3 control-label">聘用时间</label>
                    <div class="col-md-9">
                        <input type="date" class="form-control" id="teacherWorkDate" value="${edit.teacherWorkDate}" name="teacherWorkDate">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherOldWorkplace" class="col-md-4 control-label">原工作单位</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="teacherOldWorkplace" value="${edit.teacherOldWorkplace}" name="teacherOldWorkplace">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherTitle" class="col-md-3 control-label">职称</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherTitle" value="${edit.teacherTitle}" name="teacherTitle">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="teacherSchool" class="col-md-3 control-label">毕业院校</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherSchool" value="${edit.teacherSchool}" name="teacherSchool">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherEducational" class="col-md-4 control-label">学历</label>
                    <div class="col-md-8">
                        <tags:dicselect name="teacherEducational" key="educational" value="${edit.teacherEducational}" id="teacherEducational" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherMajor" class="col-md-3 control-label">专业</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherMajor" value="${edit.teacherMajor}" name="teacherMajor">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="teacherName" class="col-md-3 control-label">姓名</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherName" value="${edit.teacherName}" name="teacherName">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherFamilyName" class="col-md-3 control-label">家属姓名</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherFamilyName" value="${edit.teacherFamilyName}" name="teacherFamilyName">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherFamilyTel" class="col-md-3 control-label">家属电话</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherFamilyTel" value="${edit.teacherFamilyTel}" name="teacherFamilyTel">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="teacherSubject" class="col-md-3 control-label">拟任教学科</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherSubject" value="${edit.teacherSubject}" name="teacherSubject">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherTel" class="col-md-3 control-label">电话号</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherTel" value="${edit.teacherTel}" name="teacherTel">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherHealth" class="col-md-3 control-label">身体状况</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherHealth" value="${edit.teacherHealth}" name="teacherHealth">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 form-group">
                    <label for="teacherResume" class="col-md-1 control-label">学习简历</label>
                    <div class="col-md-11">
                        <textarea class="form-control" rows="3" id="teacherResume" name="teacherResume">${edit.teacherResume}</textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 form-group">
                    <label for="teacherWorkDesc" class="col-md-1 control-label">工作经验</label>
                    <div class="col-md-11">
                        <textarea class="form-control" rows="3" id="teacherWorkDesc" name="teacherWorkDesc">${edit.teacherWorkDesc}</textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 form-group">
                    <label for="teacherCheck" class="col-md-1 control-label">学习考核</label>
                    <div class="col-md-11">
                        <textarea class="form-control" rows="3" id="teacherCheck" name="teacherCheck">${edit.teacherCheck}</textarea>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-default">编辑教师</button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        jQuery.validator.addMethod("identifiedNum", function(value, element, param) {
            return this.optional(element) || value.length == param
        }, $.validator.format("请保证身份证号的长度为{0}"));
        jQuery.validator.addMethod("phoneLength", function(value, element, param) {
            return this.optional(element) || value.length == param
        }, $.validator.format("请保证身份证号长度为{0}"));

        $('#teacherEditForm').validate({
            rules: {
                teacherName: {
                    required: true,
                    minlength: 2,
                    maxlength: 10
                },
                teacherBirthday: {
                    required: true
                },
                teacherIdentifiedCardNum: {
                    required: true,
                    identifiedNum: 18
                },
                teacherTel: {
                    required: true,
                    phoneLength: 11
                },
                teacherWorkDate: {
                    required: true
                },
                teacherMajor: {
                    required: true
                }
            },
            messages: {
                teacherName: {
                    required: "请填写教师名.",
                    minlength: "教师名的长度为2到10.",
                    maxlength: "教师名的长度为2到10."
                },
                teacherBirthday: {
                    required: "请填写出生日期."
                },
                teacherIdentifiedCardNum: {
                    required: "请填写身份证号",
                    identifiedNum: "请保证身份证号的长度为18."
                },
                teacherTel: {
                    required: "请填写电话号.",
                    phoneLength: "请保证电话号长度为11."
                },
                teacherWorkDate: {
                    required: "请填写聘用时间."
                },
                teacherMajor: {
                    required: "请填写专业."
                }
            }
        });

        setTimeout(function() {
            $("#message").hide();
        }, 2000);
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>