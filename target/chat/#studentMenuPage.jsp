<%--
  Created by IntelliJ IDEA.
  User: dongkaiyang
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>个人主页</title>
    <%--引入jquery--%>
    <script src="js/jquery-3.3.1.min.js"></script>
    <%--引入bootstrap--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <%--引入menuPage.js--%>
    <script src="js/menuPage.js"></script>
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <h3>你好！<font color="#6495ed">${requestScope.user.name}</font></h3>
        <p>欢迎使用。</p>
        <button type="button" class="btn btn-default btn-lg" onclick="window.location.href='privateChatTest'">
            <span class="glyphicon glyphicon-user" aria-hidden="true"></span> 预约
        </button>
        <br>
        <button type="button" class="btn btn-default btn-lg" onclick="window.location.href='groupChatTest'">
            <span class="glyphicon glyphicon-th" aria-hidden="true"></span> 个人信息
        </button>
        <br>
        <button type="button" class="btn btn-default btn-lg" onclick="window.location.href='findMessageLog'">
            <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> 查看学习记录
        </button>
        <br>
        <button type="button" class="btn btn-default btn-lg" onclick="window.location.href='logout'">
            <span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span> 退出登陆
        </button>
    </div>
</div>
</body>
</html>

