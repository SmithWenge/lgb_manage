<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>

<div class="row" style="margin-left: 2%; margin-right: 2%; margin-top: 5px;">
  <c:if test="${not empty addFailureMessage}">
    <div class="col-md-12" id="message">
      <p class="bg-danger">${addFailureMessage}</p>
    </div>
  </c:if>
  <style type="text/css">
    form label {
      margin-top: 5px;
    }
  </style>
  <div class="panel panel-default">
    <div class="panel-body">
      <form style="margin-left: 2%; margin-right: 2%; margin-top: 1%;" action="${contextPath}/admin/disciplinary/add.action" method="post" id="stuAddForm">
        <div class="row">
          <div class="col-md-4 form-group">
            <label for="stuCardNum" class="col-md-4 control-label">请刷卡</label>
            <div class="col-md-8">
              <input type="text" class="form-control" id="stuCardNum" name="stuCardNum">
            </div>
          </div>
          <div class="col-md-4 form-group">
            <label for="stuCardNumTwo" class="col-md-4 control-label">卡号</label>
            <div class="col-md-8">
              <input type="text" class="form-control" id="stuCardNumTwo" name="stuCardNumTwo">
            </div>
          </div>
          <div class="col-md-4 form-group">
            <label for="stuName" class="col-md-4 control-label">姓名</label>
            <div class="col-md-8">
              <input type="text" class="form-control" id="stuName" name="stuName">
            </div>
          </div>
          <div class="col-md-4 form-group">
            <label for="disciTime" class="col-md-4 control-label">违纪时间</label>
            <div class="col-sm-8">
              <input type="date" class="form-control" id="disciTime" name="disciTime">
            </div>
          </div>
          <div class="col-md-4 form-group">
            <label for="disciCause" class="col-md-4 control-label">违纪原因</label>
            <div class="col-md-8">
              <textarea class="form-control" id="disciCause" name="disciCause"></textarea>
            </div>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-4">
            <button type="submit" class="btn btn-default" >添加违纪学员</button>
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

        stuCardNum: {
          required: true,
          minlength: 2,
          maxlength: 50,
          remote: {
            url : "${contextPath}/admin/disciplinary/cardNum.action",
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
        stuName: {
          required: true
        },
        stuTelOne: {
          required: true,
          minlength: 6,
          maxlength: 11
        },
        disciTime: {
          required: true
        }
      },
      messages: {
        stuCardNum: {
          required: "请填写卡号.",
          minlength: "卡号的长度为2到50.",
          maxlength: "卡号的长度为2到50.",
          remote: "请输入正确的卡号.",
          equalTo: "请保证输入的卡号是正确的."
        },
        stuName: {
          required: "请填写姓名"
        },
        stuTelOne: {
          required: "请填写座机号码或手机号码",
          minlength: "请填写正确格式的号码",
          maxlength: "请填写正确格式的号码"
        },
        disciTime: {
          required: "请填写违纪时间"
        }
      }
    });

    setTimeout(function() {
      $("#message").hide();
    }, 2000);
  });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>