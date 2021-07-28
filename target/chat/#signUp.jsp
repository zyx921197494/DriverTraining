<%--
  Created by IntelliJ IDEA.
  User: dongkaiyang
  Date: 2020/7/21
  Time: 下午9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <%--引入jquery--%>
    <script src="js/jquery-3.3.1.min.js"></script>
    <%--引入bootstrap--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <%--引入signUp.js--%>
    <script src="js/signUp.js"></script>
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <div class="form-group">
            <div id="userCue"><h4>欢迎注册</h4></div>
            <label for="user">用户名</label>
            <input type="Username" class="form-control" id="user" maxlength="16" placeholder="Username">
        </div>
        <div class="form-group">
            <label for="passwd">密码</label>
            <input type="password" class="form-control" id="passwd" maxlength="16" placeholder="Password">
        </div>
        <div class="form-group">
            <label for="passwd2">确认密码</label>
            <input type="password" class="form-control" id="passwd2" maxlength="16" placeholder="Password">
        </div>
        <button type="submit" id="reg" class="btn btn-default">提交</button>
    </div>
</div>
</body>
</html>
