<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<link href="${contextPath}/static/plugins/bootstrap/css/docs.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/plugins/webui-popover/jquery.webui-popover.min.css">

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
    <div class="panel-heading" style="height: 45px;padding-top: 10px;">${room.roomName} ${room.roomFloor}层 ${room.roomCapacity}人</div>
    <div class="panel-body">
        <input type="hidden" id="roomId" value="${room.roomId}">
        <table class="table table-bordered table-striped responsive-utilities">
            <tr>
                <th></th>
                <th> 周一 </th>
                <th> 周二 </th>
                <th> 周三 </th>
                <th> 周四 </th>
                <th> 周五 </th>
                <th> 周六 </th>
                <th> 周日 </th>
            </tr>
            <tr>
                <th>上午 8:20</th>
                <td class="is-hidden" id="1a"></td>
                <td class="is-hidden" id="2a"></td>
                <td class="is-hidden" id="3a"></td>
                <td class="is-hidden" id="4a"></td>
                <td class="is-hidden" id="5a"></td>
                <td class="is-hidden" id="6a"></td>
                <td class="is-hidden" id="7a"></td>
            </tr>
            <tr>
                <th>上午 9:00</th>
                <td class="is-hidden" id="1b"></td>
                <td class="is-hidden" id="2b"></td>
                <td class="is-hidden" id="3b"></td>
                <td class="is-hidden" id="4b"></td>
                <td class="is-hidden" id="5b"></td>
                <td class="is-hidden" id="6b"></td>
                <td class="is-hidden" id="7b"></td>
            </tr>
            <tr>
                <th>上午 10:20</th>
                <td class="is-hidden" id="1c"></td>
                <td class="is-hidden" id="2c"></td>
                <td class="is-hidden" id="3c"></td>
                <td class="is-hidden" id="4c"></td>
                <td class="is-hidden" id="5c"></td>
                <td class="is-hidden" id="6c"></td>
                <td class="is-hidden" id="7c"></td>
            </tr>
            <tr>
                <th>下午 13:00</th>
                <td class="is-hidden" id="1d"></td>
                <td class="is-hidden" id="2d"></td>
                <td class="is-hidden" id="3d"></td>
                <td class="is-hidden" id="4d"></td>
                <td class="is-hidden" id="5d"></td>
                <td class="is-hidden" id="6d"></td>
                <td class="is-hidden" id="7d"></td>
            </tr>
            <tr>
                <th>下午 15:00</th>
                <td class="is-hidden" id="1e"></td>
                <td class="is-hidden" id="2e"></td>
                <td class="is-hidden" id="3e"></td>
                <td class="is-hidden" id="4e"></td>
                <td class="is-hidden" id="5e"></td>
                <td class="is-hidden" id="6e"></td>
                <td class="is-hidden" id="7e"></td>
            </tr>
        </table>
    </div>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function() {
        $.ajax({
            type: 'post',
            contentType: 'application/json',
            dataType: 'json',
            url: '${contextPath}/admin/room/info/courses/' + $('#roomId').val() + '.action',
            success: function (result) {
                $.each(result.courses, function (i, item) {
                    var idValue = item.timeWeek + item.timeSpecific;
                    $('#' + idValue).attr('class', 'is-visible');
                    $('#' + idValue).html(item.courseName);
                });
            }
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>