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

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">教师添加</div>
    <div class="panel-body">
        <form id="teacherAddForm" action="${contextPath}/admin/teacher/add.action" method="post">
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="teacherCardNumOne" class="col-md-2 control-label">卡号</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" id="teacherCardNumOne" placeholder="102020202" name="teacherCardNumOne">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherCardNum" class="col-md-3 control-label">请刷卡</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherCardNum" placeholder="102020202" name="teacherCardNum">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="departmentId" class="col-md-3 control-label">所属系</label>
                    <div class="col-md-9">
                        <select class="form-control" id="departmentId" name="departmentId">
                            <c:forEach items="${departments}" var="department">
                                <option value="${department.departmentId}">${department.departmentName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="teacherGender" class="col-md-3 control-label">性别</label>
                    <div class="col-md-9">
                        <tags:dicselect name="teacherGender" key="gender" value="1" id="teacherGender" />
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherBirthday" class="col-md-3 control-label">出生日期</label>
                    <div class="col-md-9">
                        <input type="date" class="form-control" id="teacherBirthday" placeholder="2016-04-05" name="teacherBirthday">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherIdentifiedCardNum" class="col-md-3 control-label">身份证号</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherIdentifiedCardNum" placeholder="11111111111111111111" name="teacherIdentifiedCardNum">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="teacherWorkDate" class="col-md-3 control-label">聘用时间</label>
                    <div class="col-md-9">
                        <input type="date" class="form-control" id="teacherWorkDate" placeholder="2016-04-05" name="teacherWorkDate">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherOldWorkplace" class="col-md-4 control-label">原工作单位</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="teacherOldWorkplace" placeholder="大连市" name="teacherOldWorkplace">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherTitle" class="col-md-3 control-label">职称</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherTitle" placeholder="教授" name="teacherTitle">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="teacherSchool" class="col-md-3 control-label">毕业院校</label>
                    <div class="col-md-9">
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
                    <label for="teacherMajor" class="col-md-3 control-label">专业</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherMajor" placeholder="计算机" name="teacherMajor">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="teacherName" class="col-md-3 control-label">姓名</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherName" placeholder="姓名" name="teacherName">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherFamilyName" class="col-md-3 control-label">家属姓名</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherFamilyName" placeholder="家属姓名" name="teacherFamilyName">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherFamilyTel" class="col-md-3 control-label">家属电话</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherFamilyTel" placeholder="家属电话" name="teacherFamilyTel">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="teacherSubject" class="col-md-3 control-label">拟任教学科</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherSubject" placeholder="声乐,钢琴" name="teacherSubject">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherTel" class="col-md-3 control-label">电话号</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherTel" placeholder="12345678901" name="teacherTel">
                    </div>
                </div>
                <div class="col-md-4 form-group">
                    <label for="teacherHealth" class="col-md-3 control-label">身体状况</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="teacherHealth" placeholder="健康" name="teacherHealth">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 form-group">
                    <label for="teacherState" class="col-md-3 control-label">状态</label>
                    <div class="col-md-9">
                        <tags:dicselect name="teacherState" key="teacherState" value="1" id="teacherState" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 form-group">
                    <label for="teacherResume" class="col-md-1 control-label">学习简历</label>
                    <div class="col-md-11">
                        <textarea class="form-control" rows="3" id="teacherResume" name="teacherResume" placeholder="学习简历"></textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 form-group">
                    <label for="teacherWorkDesc" class="col-md-1 control-label">工作经验</label>
                    <div class="col-md-11">
                        <textarea class="form-control" rows="3" id="teacherWorkDesc" name="teacherWorkDesc" placeholder="工作经验"></textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 form-group">
                    <label for="teacherCheck" class="col-md-1 control-label">学校考核</label>
                    <div class="col-md-11">
                        <textarea class="form-control" rows="3" id="teacherCheck" name="teacherCheck" placeholder="学校考核"></textarea>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-default">添加教师</button>
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

        $('#teacherAddForm').validate({
            rules: {
                teacherCardNum: {
                    required: true,
                    minlength: 2,
                    maxlength: 50,
                    remote: {
                        url : "${contextPath}/admin/teacher/cardNum.action",
                        type : "post",
                        dataType : "json",
                        data : {
                            name : function() {
                                return $("#teacherCardNum").val();
                            }
                        }
                    },
                    equalTo: '#teacherCardNumOne'
                },
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
                teacherCardNum: {
                    required: "请填写卡号.",
                    minlength: "卡号的长度为2到50.",
                    maxlength: "卡号的长度为2到50.",
                    remote: "请确定卡号或者该卡教师已经添加.",
                    equalTo: "请保证输入的卡号是正确的."
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