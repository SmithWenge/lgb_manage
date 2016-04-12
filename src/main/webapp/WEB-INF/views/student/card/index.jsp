<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp"%>

<form class="form-horizontal" style="margin-top: 5%;">
    <div class="form-group">
        <div class="col-md-offset-3 col-md-6">
            <input type="text" class="form-control" id="studentCardNum" placeholder="101010" autofocus>
        </div>
    </div>
</form>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        $('#studentCardNum').on('input', function () {
            var $studentCardNum = $('#studentCardNum').val();
            if ($studentCardNum.length < 6) return;

            var data = {};
            data["studentCardNum"] = $studentCardNum;
           $.ajax({
               type: 'post',
               contentType: 'application/json',
               dataType: 'json',
               url: '${contextPath}/student/card/read.action',
               data: JSON.stringify(data),
               success: function (result) {
                   console.log(result.infos);
                   console.log(result.student);
               }
           });
        });
    })
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>