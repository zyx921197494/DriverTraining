$(function () {
    /**
     * ajax判断用户名是否存在
     */
    $("#user").blur(function () {
        var userName = $("#user").val().trim();
        var sendData = JSON.stringify({'name': userName});
        if(userName.length!=0){
            $.ajax({
                type:"POST",
                url:"verify/checkName",
                data:sendData,
                dataType:"json",
                contentType:"application/json",
                success:function (data) {
                    if (data){
                        $('#userCue').html("<h4><font color='red'>该用户名已经被注册!</font></h4>");
                    }else {
                        $('#userCue').html("<h4><font color='#6495ed'>该用户名可用</font></h4>");
                    }
                }
            })
        }
    });

    /**
     * 提交注册按钮
     */
    $("#reg").click(function () {
        var userName = $("#user").val().trim();
        var pwd1 = $("#passwd").val().trim();
        var pwd2 = $("#passwd2").val().trim();
        if(userName.length == 0 || pwd1.length == 0 || pwd2.length == 0){
            alert("您有一些内容没有填写。")
            return;
        }else if(pwd1 != pwd2){
            alert("您的两次密码不一致。")
        }else {
            var sendData = JSON.stringify({'name':userName,'password':pwd1});
            $.ajax({
                type: "POST",
                url: "verify/signUp",
                data: sendData,
                dataType: "json",
                contentType: "application/json",
                success : function (data) {
                    if (data){
                        alert("注册成功，请登陆");
                        window.history.back(-1);
                    }else {
                        alert("唉，出错了，请再试吧");
                    }
                }
            })
        }
    });
});