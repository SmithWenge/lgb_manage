<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 5px;">

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
            <div class="panel-body">
            <form action="${contextPath}/admin/student/edit.action" method="post" id="stuEditForm">
                <input type="hidden" name="stuId" value="${edit.stuId}">
                <div class="row">
                    <div class="col-md-4 form-group">
                        <label for="stuCardNum" class="col-md-4 control-label">卡号</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="stuCardNum" placeholder="111111" name="stuCardNum" value="${edit.stuCardNum}">
                        </div>
                    </div>
                    <div class="col-md-4 form-group">
                        <label for="stuName" class="col-md-4 control-label">姓名</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="stuName" name="stuName" value="${edit.stuName}">
                        </div>
                    </div>
                    <div class="col-md-4 form-group">
                        <label for="stuGender" class="col-md-4 control-label">性别</label>
                        <div class="col-md-8">
                            <tags:dicselect name="stuGender" key="gender" value="${edit.stuGender}" id="stuGender" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuTelOne" class="col-md-4 control-label">联系电话1</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuTelOne" name="stuTelOne" value="${edit.stuTelOne}">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuTelTwo" class="col-md-4 control-label">联系电话2</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuTelTwo" name="stuTelTwo" value="${edit.stuTelTwo}">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="studentStartDate" class="col-md-4 control-label">入学时间</label>
                            <div class="col-sm-8">
                                <input type="date" class="form-control" id="studentStartDate" name="studentStartDate" value="${edit.studentStartDate}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuType" class="col-md-4 control-label">人员类型</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuType" key="stuType" value="${edit.stuType}" id="stuType" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuNationality" class="col-md-4 control-label">民族</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuNationality" name="stuNationality" placeholder="汉族" value="${edit.stuNationality}">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuLevel" class="col-md-4 control-label">级别</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuLevel" name="stuLevel" value="${edit.stuLevel}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuIdentifiedType" class="col-md-4 control-label">证件</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuIdentifiedType" key="stuIdentifiedType" value="${edit.stuIdentifiedType}" id="stuIdentifiedType" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuBirthday" class="col-md-4 control-label">出生日期</label>
                            <div class="col-sm-8">
                                <input type="date" class="form-control" id="stuBirthday" name="stuBirthday" value="${edit.stuBirthday}"/>
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuSpeciality" class="col-md-4 control-label">特长</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuSpeciality" name="stuSpeciality" value="${edit.stuSpeciality}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuIdentifiedNum" class="col-md-4 control-label">证件号码</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuIdentifiedNum" name="stuIdentifiedNum" value="${edit.stuIdentifiedNum}">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuOldWorkType" class="col-md-4 control-label">原职务(或职业)</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuOldWorkType" key="stuOldWorkType" value="${edit.stuOldWorkType}" id="stuOldWorkType" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuPreferential" class="col-md-4 control-label">优惠</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuPreferential" key="courseDiscount" value="${edit.stuPreferential}" id="stuPreferential" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuOldWorkPlaceType" class="col-md-4 control-label">原单位类别</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuOldWorkPlaceType" key="stuOldWorkPlaceType" value="${edit.stuOldWorkPlaceType}" id="stuOldWorkPlaceType" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuEducational" class="col-md-4 control-label">文化程度</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuEducational" key="educational" value="${edit.stuEducational}" id="stuEducational" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuDependentsTel" class="col-md-4 control-label">家属电话</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuDependentsTel" name="stuDependentsTel" value="${edit.stuDependentsTel}"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuOldWorkPlaceName" class="col-md-4 control-label">原工作单位</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuOldWorkPlaceName" name="stuOldWorkPlaceName" value="${edit.stuOldWorkPlaceName}"/>
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuHealth" class="col-md-4 control-label">身体状况</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuHealth" name="stuHealth" value="${edit.stuHealth}"/>
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuDependentsDesc" class="col-md-4 control-label">家属关系</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuDependentsDesc" name="stuDependentsDesc" value="${edit.stuDependentsDesc}"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuPolitical" class="col-md-4 control-label">政治面貌</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuPolitical" key="stuPolitical" value="${edit.stuPolitical}" id="stuPolitical" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuLocation" class="col-md-4 control-label">现住址</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuLocation" name="stuLocation" value="${edit.stuLocation}">
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuCheck" class="col-md-4 control-label">审验情况</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="stuCheck" name="stuCheck">${edit.stuCheck}</textarea>
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuRemarkOne" class="col-md-4 control-label">备注</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="stuRemarkOne" name="stuRemarkOne">${edit.stuRemarkOne}</textarea>
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuRemarkTwo" class="col-md-4 control-label">备注2</label>
                            <div class="col-md-8">
                                <textarea class="form-control" id="stuRemarkTwo" name="stuRemarkTwo">${edit.stuRemarkTwo}</textarea>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="form-group">
                    <div class="col-sm-4">
                        <button type="submit" class="btn btn-default" >编辑人员</button>
                        <button type="button" id="backMark" class="btn btn-default" style="margin-left: 100px">返回</button>
                    </div>
                </div>
            </form>
            </div>
        </div>

</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        $('#stuAddForm').validate({
            rules: {
                stuTelOne: {
                    required: true,
                    minlength: 6,
                    maxlength: 20
                },
                stuNationality: {
                    required: true
                },
                stuBirthday: {
                    required: true
                },
                stuIdentifiedNum: {
                    required: true
                },
                stuLastEightNum: {
                    required: true,
                    minlength: 8,
                    maxlength: 8
                },
                stuPreferential: {
                    required: true
                },
                stuOldWorkPlaceType: {
                    required: true
                },
                stuPolitical: {
                    required: true
                },
                stuDependentsTel: {
                    required: true
                },
                stuDependentsDesc: {
                    required: true
                },
                stuLocation: {
                    required: true
                },
                stuEducational: {
                    required: true
                }
            },
            messages: {
                stuTelOne: {
                    required: "请填写座机号码或手机号码",
                    minlength: "请填写正确格式的号码",
                    maxlength: "请填写正确格式的号码"
                },
                stuNationality: {
                    required: "请填写民族"
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
                stuPreferential: {
                    required: "请填写优惠区间"
                },
                stuOldWorkPlaceType: {
                    required: "请填写原单位类别"
                },
                stuPolitical: {
                    required: "请填写政治面貌"
                },
                stuDependentsTel: {
                    required: "请填写家属电话"
                },
                stuDependentsDesc: {
                    required: "请填写家属关系"
                },
                stuLocation: {
                    required: "请填写家庭住址"
                },
                stuEducational: {
                    required: "请填写文化程度"
                }
            }
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>