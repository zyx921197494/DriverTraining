// $(function () {

    /**
     * 登陆按钮
     */
    // $("#submit").click(function () {
    //     var card = $("#card").val().trim();
    //     var password = $("#password").val().trim();
    //     var sendData = JSON.stringify({'password':password,'card':card});
    //     if (card.length === 0 || password.length === 0){
    //         alert("您未输入 身份证或密码");
    //         return false;
    //     }else {
    //         $.ajax({
    //             type:"POST",
    //             url:"/verify/login",
    //             data:sendData,
    //             dataType:"json",
    //             contentType:"application/json",
    //             success:function (data) {
    //                 alert(data.page)
    //                 $(location).attr('href', data.page);
    //             }
    //         })
            // $("#login_form").submit();
            // return true;
//         }
//     })
// })