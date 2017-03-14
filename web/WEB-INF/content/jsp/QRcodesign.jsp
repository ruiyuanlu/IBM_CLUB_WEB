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
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="/bootHTML/css/materialize.min.css"  media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>扫码签到</title>
    <script type="text/JavaScript" src="/js/QRcodeGenerate.js"></script>
</head>
<body onbeforeunload="changeTokenWhileClosing()">

<!-- Modal Trigger -->
<a class="modal-trigger waves-effect waves-light btn" href="#modal1">二维码</a>

<!-- Modal Structure -->
<div id="modal1" class="modal modal-fixed-footer">
    <div class="modal-content">
        <div class="row">
        <div class="col s4"></div>
        <div class="col s4">
            <h4>标题</h4>
            <div id="qrcode" ></div>
        </div>
        <div class="col s4"></div>
        </div>
    </div>
    <div class="modal-footer">
        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">关闭</a>
    </div>
</div>
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/bootHTML/js/materialize.min.js"></script>
<script type="text/javascript">
    $('#modal1').modal('open');

    $('.modal').modal({
            dismissible: true, // Modal can be dismissed by clicking outside of the modal
            opacity: .5, // Opacity of modal background
            inDuration: 300, // Transition in duration
            outDuration: 200, // Transition out duration
            startingTop: '4%', // Starting top style attribute
            endingTop: '10%', // Ending top style attribute
            ready: function(modal, trigger) { // Callback for Modal open. Modal and trigger parameters available.
                refreshQRcode();
                window.setInterval(refreshQRcode,10000);
            },
            //complete: function() {document.getElementById("imgs").src="./src/erweima2.png" ;} // Callback for Modal close

        }
    );
</script>


<div id="signurl"></div>

</body>
<script>
    deptID=1;times=1;
  //  refreshQRcode();
  //  window.setInterval(refreshQRcode,10000);
</script>
</html>
