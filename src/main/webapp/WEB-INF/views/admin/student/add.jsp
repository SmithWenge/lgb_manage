<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<div class="row" style="margin-left: 0.1%; float: left;width: 90%;">
    <div class="panel panel-default" style="float: left; width: 100%;">
        <div class="panel-heading">
            <a href="${contextPath}/admin/student/page.action"><span class="glyphicon glyphicon-map-marker"></span> &nbsp;学生管理</a> / 添加学员</div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-12">
                    <video id="video" style="width: 320px; height: 240px;" autoplay></video>
                    <button id="snap" class="btn btn-success">照相</button>
                    <canvas id="canvas" width="320px" height=240px"></canvas>
                </div>
            </div>
            <form action="${contextPath}/admin/student/add.action" method="post" id="stuAddForm">
                <input type="hidden" name="stuPicture" id="stuPicture" value="default_student.jpg">
                <div class="row">
                    <div class="col-md-4 form-group">
                        <label for="stuCardNumTwo" class="col-md-4 control-label">卡号</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="stuCardNumTwo" name="stuCardNumTwo">
                        </div>
                    </div>
                    <div class="col-md-4 form-group">
                        <label for="stuCardNum" class="col-md-4 control-label">请刷卡</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="stuCardNum" name="stuCardNum">
                        </div>
                    </div>
                    <div class="col-md-4 form-group">
                        <label for="stuName" class="col-md-4 control-label">姓名</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="stuName" name="stuName">
                        </div>
                    </div>
                </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuGender" class="col-md-4 control-label">性别</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuGender" key="gender" value="1" id="stuGender" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuTelOne" class="col-md-4 control-label">联系电话1</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuTelOne" name="stuTelOne">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuTelTwo" class="col-md-4 control-label">联系电话2</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuTelTwo" name="stuTelTwo">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuType" class="col-md-4 control-label">人员类型</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuType" key="stuType" value="3" id="stuType" />
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
                                <tags:dicselect name="stuLevel" key="memberLevel" value="1" id="stuLevel" />
                                <%--<input type="text" class="form-control" id="stuLevel" name="stuLevel">--%>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuIdentifiedType" class="col-md-4 control-label">证件</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuIdentifiedType" key="stuIdentifiedType" value="1" id="stuIdentifiedType" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuBirthday" class="col-md-4 control-label">出生日期</label>
                            <div class="col-sm-8">
                                <input type="date" class="form-control" id="stuBirthday" name="stuBirthday">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuSpeciality" class="col-md-4 control-label">特长</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuSpeciality" name="stuSpeciality">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuIdentifiedNum" class="col-md-4 control-label">证件号码</label>
                            <div class="col-md-8">
                                <input type="password" class="form-control" id="stuIdentifiedNum" name="stuIdentifiedNum">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuEducational" class="col-md-4 control-label">文化程度</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuEducational" key="educational" value="2" id="stuEducational" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuPreferential" class="col-md-4 control-label">优惠</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuPreferential" key="courseDiscount" value="3" id="stuPreferential" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuOldWorkPlaceType" class="col-md-4 control-label">原单位类别</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuOldWorkPlaceType" key="stuOldWorkPlaceType" value="1" id="stuOldWorkPlaceType" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="studentStartDate" class="col-md-4 control-label">入学时间</label>
                            <div class="col-sm-8">
                                <input type="date" class="form-control" id="studentStartDate" name="studentStartDate">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuDependentsTel" class="col-md-4 control-label">家属电话</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuDependentsTel" name="stuDependentsTel">
                            </div>
                        </div>
                    </div>
                    <div class="row">
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
                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuPolitical" class="col-md-4 control-label">政治面貌</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuPolitical" key="stuPolitical" value="1" id="stuPolitical" />
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuLocation" class="col-md-4 control-label">现住址</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="stuLocation" name="stuLocation">
                            </div>
                        </div>
                        <div class="col-md-4 form-group">
                            <label for="stuOldWorkType" class="col-md-4 control-label">原职务</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuOldWorkType" key="stuOldWorkType" value="3" id="stuOldWorkType" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 form-group">
                            <label for="stuCheck" class="col-md-4 control-label">审验情况</label>
                            <div class="col-md-8">
                                <tags:dicselect name="stuCheck" key="memberCheck" value="1" id="stuCheck" />
                                <%--<textarea class="form-control" id="stuCheck" name="stuCheck"></textarea>--%>
                            </div>
                        </div>
                        <%--<div class="col-md-4 form-group">--%>
                            <%--<label for="stuRemarkOne" class="col-md-4 control-label">备注</label>--%>
                            <%--<div class="col-md-8">--%>
                                <%--<textarea class="form-control" id="stuRemarkOne" name="stuRemarkOne"></textarea>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-4 form-group">--%>
                            <%--<label for="stuRemarkTwo" class="col-md-4 control-label">备注2</label>--%>
                            <%--<div class="col-md-8">--%>
                                <%--<textarea class="form-control" id="stuRemarkTwo" name="stuRemarkTwo"></textarea>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <div class="row">
                        <div class="col-md-12 form-group">
                            <label for="stuRemarkOne" class="col-md-1 control-label">备注1</label>
                            <div class="col-md-11">
                                <textarea class="form-control" id="stuRemarkOne" name="stuRemarkOne"></textarea>
                            </div>
                        </div>
                    </div>
                <div class="row">
                    <div class="col-md-12 form-group">
                        <label for="stuRemarkTwo" class="col-md-1 control-label">备注2</label>
                        <div class="col-md-11">
                            <textarea class="form-control" id="stuRemarkTwo" name="stuRemarkTwo"></textarea>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4">
                        <button type="submit" class="btn btn-success" >添加学生</button>
                        <button type="button" id="backMark" class="btn btn-warning" style="margin-left: 100px">返回</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <%-- 拍照脚本 --%>
    <script type="text/javascript">
        // Put event listeners into place
        window.addEventListener("DOMContentLoaded", function() {
            // Grab elements, create settings, etc.
            var canvas = document.getElementById('canvas');
            var context = canvas.getContext('2d');
            var video = document.getElementById('video');
            var mediaConfig =  { video: true };

            var errBack = function(e) {
                console.log('An error has occurred!', e)
            };

            // Put video listeners into place
            if(navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
                navigator.mediaDevices.getUserMedia(mediaConfig).then(function(stream) {
                    video.src = window.URL.createObjectURL(stream);
                    video.play();
                });
            }

            /* Legacy code below! */
            else if(navigator.getUserMedia) { // Standard
                navigator.getUserMedia(mediaConfig, function(stream) {
                    video.src = stream;
                    video.play();
                }, errBack);
            } else if(navigator.webkitGetUserMedia) { // WebKit-prefixed
                navigator.webkitGetUserMedia(mediaConfig, function(stream){
                    video.src = window.webkitURL.createObjectURL(stream);
                    video.play();
                }, errBack);
            } else if(navigator.mozGetUserMedia) { // Mozilla-prefixed
                navigator.mozGetUserMedia(mediaConfig, function(stream){
                    video.src = window.URL.createObjectURL(stream);
                    video.play();
                }, errBack);
            }

            // Trigger photo take
            document.getElementById('snap').addEventListener('click', function() {
                context.drawImage(video, 0, 0, 320, 240);

                var imageData = canvas.toDataURL("image/jpg");
                // 将图像转换为64数据
                var base64data = imageData.split(",")[1];

                var xmlHttp;
                if (window.XMLHttpRequest) {
                    xmlHttp=new XMLHttpRequest();
                } else {
                    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlHttp.open("post", "${contextPath}/admin/webcam.svt", true);
                xmlHttp.setRequestHeader("X-Requested-Width", "XMLHttpRequest");
                var formData = new FormData();
                formData.append("photo",base64data);
                xmlHttp.onreadystatechange=function()
                {
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                    {
                        var responseText = xmlHttp.responseText;
                        var reg = new RegExp('"',"g");
                        responseText = responseText.replace(reg, "");
                        document.getElementById("stuPicture").value = responseText;
                        console.log(responseText);
                    }
                }
                xmlHttp.send(formData);
            });
        }, false);

    </script>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        $('#stuAddForm').validate({
            rules: {
                stuCardNum: {
                    required: true,
                    minlength: 2,
                    maxlength: 50,
                    remote: {
                        url : "${contextPath}/admin/student/cardNum.action",
                        type : "post",
                        dataType : "json",
                        data : {
                            name : function() {
                                return $("#stuCardNum").val();
                            }
                        }
                    },
                    equalTo: '#stuCardNumTwo'
                },
                studentStartDate: {
                    required: true
                },
                stuName: {
                    required: true
                },
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
                    required: true,
                    minlength: 6,
                    maxlength: 20
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
                stuCardNum: {
                    required: "请填写卡号.",
                    minlength: "卡号的长度为2到50.",
                    maxlength: "卡号的长度为2到50.",
                    remote: "请确定卡号或者该卡学生已经添加.",
                    equalTo: "请保证输入的卡号是正确的."
                },
                studentStartDate: {
                    required: "请填写入学日期"
                },
                stuName: {
                    required: "请填写姓名"
                },
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
                    required: "请填写家属电话",
                    minlength: "请填写正确格式的号码",
                    maxlength: "请填写正确格式的号码"
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