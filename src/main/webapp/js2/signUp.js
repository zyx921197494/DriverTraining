// $(function () {
//     /**
//      * ajax判断身份证是否存在
//      */
//     $("#card").blur(function () {
//         var card = $("#card").val().trim();
//         var sendData = JSON.stringify({'card': card});
//         if(card.length!=0){
//             $.ajax({
//                 type:"POST",
//                 url:"/verify/checkName",
//                 data:sendData,
//                 dataType:"json",
//                 contentType:"application/json",
//                 success:function (data) {
//                     if (data){
//                         $('#userCue').html("<h4><font color='red'>该用户名已经被注册!</font></h4>");
//                     }else {
//                         $('#userCue').html("<h4><font color='#6495ed'>该身份证可用</font></h4>");
//                     }
//                 }
//             })
//         }
//     });
//
//     /**
//      * 提交注册按钮
//      */
//     $("#submit").click(function () {
//         var userName = $("#user").val().trim();
//         var pwd = $("#password").val().trim();
//         var card = $("#card").val().trim();
//         var email = $("#email").val().trim();
//         var identity = $("#identity").val().trim();
//         if(userName.length == 0 || pwd.length == 0|| card.length == 0|| email.length == 0|| identity.length == 0){
//             alert("您有一些内容没有填写。")
//             return;
//         }else {
//             var sendData = JSON.stringify({'name':userName,'password':pwd,'card':card,'email':email,'identity':identity});
//             $.ajax({
//                 type: "POST",
//                 url: "verify/signUp",
//                 data: sendData,
//                 dataType: "json",
//                 contentType: "application/json",
//                 success : function (data) {
//                     if (data){
//                         alert("注册成功，请登陆");
//                         window.history.back(-1);
//                     }else {
//                         alert("唉，出错了，请再试吧");
//                     }
//                 }
//             })
//         }
//     });
// });