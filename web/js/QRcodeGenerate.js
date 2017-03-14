/**
 * Created by Morn Wu on 2017/2/28.
 */
document.write('<script type="text/JavaScript" src="/js/jquery-3.1.1.js"></script>');
document.write('<script type="text/JavaScript" src="/js/qrcode.js"></script>');
var deptID, times;

function refreshQRcode() {
    var url="http://" +  location.host + "/memberSign?token=";
    $.post("refreshQRCode", {
        deptID: deptID,
        times: times
    },function(json) {
        $.each(json.actionMessages,function(index,data){
            var qrcode;
            var token=null;
            token=data;
            if (token != null){
                url=url+token;
                //修改，给url中添加部门id属性和签到次数属性
                url=url+"&deptID="+deptID+"&amptimes="+times;//url中直接写 &times 会被解析为x，因此用&amp 作为 &
                document.getElementById("signurl").innerHTML=url;
                document.getElementById("qrcode").innerHTML="";
                new QRCode('qrcode', {
                    text: url,
                    width: 256,
                    height: 256,
                    colorDark : '#000000',
                    colorLight : '#ffffff',
                    correctLevel : QRCode.CorrectLevel.H
                });
            }
            else {
                document.getElementById("signurl").innerHTML="获取二维码失败!";
            }
        });
    });
}

function changeTokenWhileClosing() {
    $.post("refreshQRCode", function(json) {
        $.each(json.actionMessages,function(index,data){

        });
        return "确认关闭？";
    });
}
