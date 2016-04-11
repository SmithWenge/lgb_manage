<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<link href="${contextPath}/static/plugins/bootstrap/css/docs.min.css" rel="stylesheet" type="text/css">

<div class="panel panel-default" style="margin-left: 2%; margin-right: 2%; margin-top: 1%;">
    <div class="panel-heading">
        教室 | <a href="${contextPath}/admin/room/routeTime.action">详细</a>
    </div>
    <div class="panel-body">
        <table class="table table-bordered table-striped responsive-utilities">
            <tbody>
            <tr>
                <th scope=row><code>二楼</code></th>
                <td class="is-hidden" id="1">210室</td>
                <td class="is-hidden" id="2">212室</td>
                <td class="is-hidden" id="3">213室</td>
                <td class="is-hidden" id="4">215室</td>
                <td class="is-hidden" id="5">216室</td>
            </tr>
            <tr>
                <th scope=row><code>三楼</code></th>
                <td class="is-hidden" id="6">307室</td>
                <td class="is-hidden" id="7">308室</td>
                <td class="is-hidden" id="8">309室</td>
                <td class="is-hidden" id="9">310室</td>
            </tr>
            <tr>
                <th scope=row><code>四楼</code></th>
                <td class="is-hidden" id="10">401室</td>
                <td class="is-hidden" id="11">402室</td>
                <td class="is-hidden" id="12">403室</td>
                <td class="is-hidden" id="13">405室</td>
                <td class="is-hidden" id="14">406室</td>
                <td class="is-hidden" id="15">407室</td>
                <td class="is-hidden" id="16">408室</td>
                <td class="is-hidden" id="17">409室</td>
                <td class="is-hidden" id="18">417室</td>
                <td class="is-hidden" id="19">418室</td>
            </tr>
            <tr>
                <th scope=row><code>五楼</code></th>
                <td class="is-hidden" id="20">501室</td>
                <td class="is-hidden" id="21">502室</td>
                <td class="is-hidden" id="22">503室</td>
                <td class="is-hidden" id="23">505室</td>
                <td class="is-hidden" id="24">507室</td>
                <td class="is-hidden" id="25">509室</td>
                <td class="is-hidden" id="26">510室</td>
                <td class="is-hidden" id="27">511室</td>
                <td class="is-hidden" id="28">513室</td>
                <td class="is-hidden" id="29">515室</td>
                <td class="is-hidden" id="30">516室</td>
                <td class="is-hidden" id="31">517室</td>
            </tr>
            <tr>
                <th scope=row><code>六楼</code></th>
                <td class="is-hidden" id="32">601室</td>
                <td class="is-hidden" id="33">602室</td>
                <td class="is-hidden" id="34">606室</td>
                <td class="is-hidden" id="35">608室</td>
            </tr>
            <tr>
                <th scope=row><code>七楼</code></th>
                <td class="is-hidden" id="36">701室</td>
                <td class="is-hidden" id="37">702室</td>
            </tr>
            <tr>
                <th scope=row><code>八楼</code></th>
                <td class="is-hidden" id="38">802室</td>
            </tr>
            </tbody>
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
            url: '${contextPath}/admin/room/index.action',
            success: function (result) {
                $.each(result.rooms, function (i, item) {
                    $('#' + item.roomId).attr('class', 'is-visible');
                    $('#' + item.roomId).html($('#' + item.roomId).html() + ' 占用');
                });
            }
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>