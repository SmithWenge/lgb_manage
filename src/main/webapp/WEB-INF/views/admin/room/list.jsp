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
                <td class="is-hidden" id="1"><a href="${contextPath}/admin/room/info/detail/1.action">210室</a></td>
                <td class="is-hidden" id="2"><a href="${contextPath}/admin/room/info/detail/2.action">212室</a></td>
                <td class="is-hidden" id="3"><a href="${contextPath}/admin/room/info/detail/3.action">213室</a></td>
                <td class="is-hidden" id="4"><a href="${contextPath}/admin/room/info/detail/4.action">215室</a></td>
                <td class="is-hidden" id="5"><a href="${contextPath}/admin/room/info/detail/5.action">216室</a></td>
            </tr>
            <tr>
                <th scope=row><code>三楼</code></th>
                <td class="is-hidden" id="6"><a href="${contextPath}/admin/room/info/detail/6.action">307室</a></td>
                <td class="is-hidden" id="7"><a href="${contextPath}/admin/room/info/detail/7.action">308室</a></td>
                <td class="is-hidden" id="8"><a href="${contextPath}/admin/room/info/detail/8.action">309室</a></td>
                <td class="is-hidden" id="9"><a href="${contextPath}/admin/room/info/detail/9.action">310室</a></td>
            </tr>
            <tr>
                <th scope=row><code>四楼</code></th>
                <td class="is-hidden" id="10"><a href="${contextPath}/admin/room/info/detail/10.action">401室</a></td>
                <td class="is-hidden" id="11"><a href="${contextPath}/admin/room/info/detail/11.action">402室</a></td>
                <td class="is-hidden" id="12"><a href="${contextPath}/admin/room/info/detail/12.action">403室</a></td>
                <td class="is-hidden" id="13"><a href="${contextPath}/admin/room/info/detail/13.action">405室</a></td>
                <td class="is-hidden" id="14"><a href="${contextPath}/admin/room/info/detail/14.action">406室</a></td>
                <td class="is-hidden" id="15"><a href="${contextPath}/admin/room/info/detail/15.action">407室</a></td>
                <td class="is-hidden" id="16"><a href="${contextPath}/admin/room/info/detail/16.action">408室</a></td>
                <td class="is-hidden" id="17"><a href="${contextPath}/admin/room/info/detail/17.action">409室</a></td>
                <td class="is-hidden" id="18"><a href="${contextPath}/admin/room/info/detail/18.action">417室</a></td>
                <td class="is-hidden" id="19"><a href="${contextPath}/admin/room/info/detail/19.action">418室</a></td>
            </tr>
            <tr>
                <th scope=row><code>五楼</code></th>
                <td class="is-hidden" id="20"><a href="${contextPath}/admin/room/info/detail/20.action">501室</a></td>
                <td class="is-hidden" id="21"><a href="${contextPath}/admin/room/info/detail/21.action">502室</a></td>
                <td class="is-hidden" id="22"><a href="${contextPath}/admin/room/info/detail/22.action">503室</a></td>
                <td class="is-hidden" id="23"><a href="${contextPath}/admin/room/info/detail/23.action">505室</a></td>
                <td class="is-hidden" id="25"><a href="${contextPath}/admin/room/info/detail/25.action">507室</a></td>
                <td class="is-hidden" id="26"><a href="${contextPath}/admin/room/info/detail/26.action">509室</a></td>
                <td class="is-hidden" id="27"><a href="${contextPath}/admin/room/info/detail/27.action">510室</a></td>
                <td class="is-hidden" id="28"><a href="${contextPath}/admin/room/info/detail/28.action">511室</a></td>
                <td class="is-hidden" id="29"><a href="${contextPath}/admin/room/info/detail/29.action">513室</a></td>
                <td class="is-hidden" id="30"><a href="${contextPath}/admin/room/info/detail/30.action">515室</a></td>
                <td class="is-hidden" id="31"><a href="${contextPath}/admin/room/info/detail/31.action">516室</a></td>
                <%--<td class="is-hidden" id="31">517室</td>--%>
            </tr>
            <tr>
                <th scope=row><code>六楼</code></th>
                <td class="is-hidden" id="32"><a href="${contextPath}/admin/room/info/detail/32.action">601室</a></td>
                <td class="is-hidden" id="33"><a href="${contextPath}/admin/room/info/detail/33.action">602室</a></td>
                <td class="is-hidden" id="34"><a href="${contextPath}/admin/room/info/detail/34.action">606室</a></td>
                <td class="is-hidden" id="35"><a href="${contextPath}/admin/room/info/detail/35.action">608室</a></td>
            </tr>
            <tr>
                <th scope=row><code>七楼</code></th>
                <td class="is-hidden" id="36"><a href="${contextPath}/admin/room/info/detail/36.action">701室</a></td>
                <td class="is-hidden" id="37"><a href="${contextPath}/admin/room/info/detail/37.action">702室</a></td>
            </tr>
            <tr>
                <th scope=row><code>八楼</code></th>
                <td class="is-hidden" id="38"><a href="${contextPath}/admin/room/info/detail/38.action">802室</a></td>
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