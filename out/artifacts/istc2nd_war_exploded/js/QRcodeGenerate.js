/**
 * Created by Morn Wu on 2017/2/28.
 */
document.write('<script type="text/JavaScript" src="js/jquery-3.1.1.js"></script>');
document.write('<script type="text/JavaScript" src="js/qrcode.js"></script>');


function refreshQRcode() {
    var url=location.host+"/Sign?tokenfetch=";
    $.post("qrcodeSign", function(json) {
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

function changeTokenWhileClosing() {
    $.post("qrcodeSign", function(json) {
        $.each(json.actionMessages,function(index,data){

        });
    });
}
