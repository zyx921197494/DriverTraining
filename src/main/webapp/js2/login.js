$(function () {
    /**
     * 登陆按钮
     */
    $("#submit").click(function () {
        var username = $("#card").val().trim();
        var password = $("#password").val().trim();
        if (username.length == 0 || password.length == 0){
            alert("您未输入 身份证或密码");
            return false;
        }else {
            $("#login_form").submit();
            return true;
        }
    })
})