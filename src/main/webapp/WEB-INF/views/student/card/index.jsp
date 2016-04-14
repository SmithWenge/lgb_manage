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

<div id="informationTable" class="col-md-10 col-md-offset-1"></div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        $('#myModal').css('display', 'block');
        $('#studentCardNum').on('input', function () {
            var $studentCardNum = $('#studentCardNum').val();

            if ($studentCardNum.length == 0) {
                $('#informationTable').empty();
            }

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
                   $('#informationTable').empty();
                   if (result.student[0] == null) {
                       $('#studentCardNum').val('');
                       return;
                   }
                   var $studentName = result.student[0].studentName;
                   var $studentCardNum = result.student[0].studentCardNum;

                   var $table = $('<table class="table">');
                   $table.appendTo($('#informationTable'));
                   var $tableHeader = $('<tr><th>学员名</th><th>学员卡号</th><th>课程名</th><th>上课教室</th><th>上课时间</th><th>系名</th><th>专业名</th></tr>')
                   $.each(result.infos, function (i, item) {
                       var $courseName = item.courseName;
                       var $courseRoom = item.courseRoom;
                       var $courseTime = item.courseTime;
                       var $departmentName = item.departmentName;
                       var $majorName = item.majorName;

                       var $tr = $('<tr><td>' + $studentName + '</td><td>' + $studentCardNum + '</td><td>' + $courseName + '</td><td>' + $courseRoom + '</td><td> ' + $courseTime + '</td><td>' + $departmentName + '</td><td>' + $majorName + '</td></tr>');
                       $tr.appendTo($table);
                   });
                   $('#informationTable').append("</table");

                   $('#studentCardNum').val('');
               }
           });
        });
    })
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>