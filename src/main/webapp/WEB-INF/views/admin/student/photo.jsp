<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/navs.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<style>
    video {
        border: 1px solid #ccc;
        display: block;
        margin: 0 0 20px 0;
    }
    #canvas {
        margin-top: 20px;
        border: 1px solid #ccc;
        display: block;
    }
</style>

<video id="video" width="320" height="240" autoplay></video>
<button id="snap" class="sexyButton">照相</button>
<canvas id="canvas" width="320" height="240"></canvas>

<%@include file="/WEB-INF/include/javascript.jsp"%>

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
                    console.log(responseText);
                }
            }
            xmlHttp.send(formData);
        });
    }, false);

</script>

<%@include file="/WEB-INF/include/footer.jsp"%>