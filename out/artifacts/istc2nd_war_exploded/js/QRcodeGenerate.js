/**
 * Created by Morn Wu on 2017/2/28.
 */
document.write('<script type="text/JavaScript" src="js/jquery-3.1.1.js"></script>');
document.write('<script type="text/JavaScript" src="js/qrcode.js"></script>');

function refreshQRcode() {
    var url=location.host+"/Sign?tokenfetch=";
    $.post("qrcodeSign", function(json) {
        if (json.jsonresult.allsigned == true){
            alert("所有部员已经签到完毕，点确认关闭页面。")
            window.close();
        }
        $.each(json.actionMessages,function(index,data){
            var qrcode;
            var token=null;
            token=data;
            if (token != null){
                url=url+token;
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
                document.getElementById("signurl").innerHTML="获取二维码失败！";
            }
        });
    });
}

//请各位部长注意，签到的时候务必保证网络畅通。如果关闭的时候网络阻塞，那么token将不会被更新！
function changeTokenWhileClosing() {
    var allsigned = false;
    $.post("qrcodeSign", function(json) {
        allsigned = json.jsonresult.allsigned;
    });
    if (allsigned == false) {
        return "确认关闭？";
    }
}
