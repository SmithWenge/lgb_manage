<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<br>
<div id="content" style="float: left;width: 85%;">
    <div class="main_left" style="padding-left: 50px;">
        <h2>帮助</h2>
        <br>
        <h3>关于报名</h3>
        <p class="lead">只有系统设置中开放了报名才可以报名</p>
        <p class="lead">只有当前日期在系别的报名开始时间和报名结束时间之间时，该系才可以报名</p>
        <p class="lead">报名开始时间和“报名结束时间”在系别管理中设置</p>
        <p class="lead">每个学员只可以报三个班</p>
        <p class="lead">相同专业，报名flag相同的班只能报一个</p>
        <h3>关于排座</h3>
        <p class="lead">在报名开始时间到报名结束时间的范围内，按U型二元生日排座</p>
        <p class="lead">在报名时间以外，自动按当前可用的最小座位号分配座位</p>
        <p class="lead">报名时间设置在系别管理里面可以设置</p>
        <h3>关于调班</h3>
        <p class="lead">只有调班权限的用户可以操作调班</p>
        <p class="lead">只有学期内可以调班</p>
        <p class="lead">每个学员在学期内可以调2次班</p>
    </div>
</div>


<%@include file="/WEB-INF/include/footer.jsp"%>