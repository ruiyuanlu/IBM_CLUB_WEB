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
    alert("请各位部长注意，签到时务必保证网络畅通！");
    refreshQRcode();
    window.setInterval(refreshQRcode,10000);
</script>
</html>
