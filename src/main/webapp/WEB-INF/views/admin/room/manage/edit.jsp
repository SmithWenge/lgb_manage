<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<%--教室--%>
<style>
    #room{
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="panel panel-default" style="float: left;width: 85%;">
    <div class="panel-heading" style="height: 45px;"><span class="glyphicon glyphicon-map-marker"></span>教室 > 编辑教室</div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/room/manage/edit.action" method="post" id="roomEditForm">
                    <input type="hidden" name="roomId" value="${edit.roomId}">
                    <div class="form-group">
                        <label for="roomName" class="col-sm-2 control-label">教室名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="roomName" name="roomName" value="${edit.roomName}" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="roomCapacity" class="col-sm-2 control-label">人数</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="roomCapacity" name="roomCapacity" value="${edit.roomCapacity}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="roomFloor" class="col-sm-2 control-label">楼层</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="roomFloor" name="roomFloor" value="${edit.roomFloor}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">编辑教室</button>
                            <button type="button" id="backMark" class="btn btn-default" style="margin-left: 100px">返回</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        $('#roomEditForm').validate({
            rules: {
                roomCapacity: {
                    required: true,
                    number: true,
                    range: [10, 100]
                },
                roomFloor: {
                    required: true,
                    number: true,
                    range: [1, 30]
                }
            },
            messages: {
                roomCapacity: {
                    required: "请填写教室人数.",
                    number: "请填写数字.",
                    range: "人数范围为[10, 100]"
                },
                roomFloor: {
                    required: "请填写楼层数目.",
                    number: "请填写数字.",
                    range: "楼层范围为[1, 30]"
                }
            }
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>