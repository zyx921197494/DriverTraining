<%--
  Created by IntelliJ IDEA.
  User: dongkaiyang
  Date: 2020/7/21
  Time: 下午9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>登陆</title>
    <%--引入jquery--%>
    <script src="js/jquery-3.3.1.min.js"></script>
    <%--引入bootstrap--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <%--引入login.js--%>
    <script src="js/Oldlogin.js"></script>
</head>
<body>
<div class="jumbotron">
<div class="container">
<form action="menuPageTest" method="post" id="login_form">
    <div class="form-group">
        <label for="Username">用户名</label>
        <input type="Username" class="form-control" id="username" placeholder="Username" name="name">
    </div>
    <div class="form-group">
        <label for="Password">密码</label>
        <input type="password" class="form-control" id="password" placeholder="Password" name="password">
    </div>

    <button type="button" id="loginBtn" class="btn btn-default">登陆</button><font color="#dc143c">${requestScope.msg}</font>
</form>
</div>
</div>
</body>
</html>
