$(function () {
    /**
     * 登陆按钮
     */
    $("#loginBtn").click(function () {
        var username = $("#username").val().trim();
        var password = $("#password").val().trim();
        if (username.length == 0 || password.length == 0){
            alert("您未输入用户名或密码");
            return false;
        }else {
            $("#login_form").submit();
            return true;
        }
    })
})