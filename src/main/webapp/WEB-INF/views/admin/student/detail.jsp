<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<div class="row" style="margin-left: 0.1%; float: left; width: 85%;">
    <div class="panel panel-default" style="float: left; width: 100%;">
        <div class="panel-heading" style="height: 45px;padding-top: 5px;">
            <a href="${contextPath}/admin/student/page.action"><span class="glyphicon glyphicon-map-marker"></span> 学生管理</a> > 学员信息
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <tr>
                    <td rowspan="5">
                        <img id="photo" src="${contextPath}/admin/photo/route/${student.stuPicture}.action">
                    </td>
                    <th>卡号</th>
                    <td>${student.stuCardNum}</td>
                    <th>姓名</th>
                    <td>${student.stuName}</td>
                    <th>性别</th>
                    <tags:dictd groupValue="gender" itemKey="${student.stuGender}" />
                </tr>
                <tr>
                    <th>联系电话1</th>
                    <td>${student.stuTelOne}</td>
                    <th>联系电话2</th>
                    <td>${student.stuTelTwo}</td>
                    <th>入学时间</th>
                    <td>${student.studentStartDate}</td>
                </tr>
                <tr>
                    <th>人员类型</th>
                    <tags:dictd groupValue="stuType" itemKey="${student.stuType}" />
                    <th>民族</th>
                    <td>${student.stuNationality}</td>
                    <th>级别</th>
                    <tags:dictd groupValue="memberLevel" itemKey="${student.stuLevel}" />
                </tr>
                <tr>
                    <th>证件</th>
                    <tags:dictd groupValue="stuIdentifiedType" itemKey="${student.stuIdentifiedType}" />
                    <th>出生日期</th>
                    <td>${student.stuBirthday}</td>
                    <th>特长</th>
                    <td>${student.stuSpeciality}</td>
                </tr>
                <tr>
                    <th>证件号码</th>
                    <td>${student.stuIdentifiedNum}</td>
                    <th>原职务</th>
                    <tags:dictd groupValue="stuOldWorkType" itemKey="${student.stuOldWorkType}" />
                    <th>优惠</th>
                    <tags:dictd groupValue="courseDiscount" itemKey="${student.stuPreferential}" />
                </tr>
                <tr>
                    <th>原单位类别</th>
                    <tags:dictd groupValue="stuOldWorkPlaceType" itemKey="${student.stuOldWorkPlaceType}" />
                    <th>文化程度</th>
                    <tags:dictd groupValue="educational" itemKey="${student.stuEducational}" />
                    <th>家属电话</th>
                    <td>${student.stuDependentsTel}</td>
                </tr>
                <tr>
                    <th>原工作单位</th>
                    <td>${student.stuOldWorkPlaceName}</td>
                    <th>身体状况</th>
                    <td>${student.stuHealth}</td>
                    <th>家属关系</th>
                    <td>${student.stuDependentsDesc}</td>
                </tr>
                <tr>
                    <th>政治面貌</th>
                    <td>${student.stuPolitical}</td>
                    <th>现住址</th>
                    <td>${student.stuLocation}</td>
                    <th>审验情况</th>
                    <tags:dictd groupValue="memberCheck" itemKey="${student.stuCheck}" />
                </tr>
                <tr>
                    <th>备注1</th>
                    <td colspan="5">${student.stuRemarkOne}</td>
                </tr>
                <tr>
                    <th>备注2</th>
                    <td colspan="5">${student.stuRemarkTwo}</td>
                </tr>
            </table>
            <div class="form-group">
                <div class="col-sm-4">
                    <button type="button" id="backMark" class="btn btn-default">返回</button>
                </div>
            </div>
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