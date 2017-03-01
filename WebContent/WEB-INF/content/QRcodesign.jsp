<%--
  Created by IntelliJ IDEA.
  User: Morn Wu
  Date: 2017/2/28
  Time: 上午 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>扫码签到</title>
    <script type="text/JavaScript" src="js/QRcodeGenerate.js"></script>
</head>
<body onbeforeunload="return changeTokenWhileClosing()">
<div id="signurl"></div>
<div id="qrcode"></div>
</body>
<script>
    refreshQRcode();
    var doRefresh=window.setInterval(refreshQRcode,10000);
    function changeTokenWhileClosing() {
        $.post("qrcodeSign", function(json) {
            $.each(json.actionMessages,function(index,data){

            });
        });
        return "确认关闭？";
    }
</script>
</html>
