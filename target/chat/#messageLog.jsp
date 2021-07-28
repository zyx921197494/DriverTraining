<%@ page import="tech.dongkaiyang.domain.Record" %><%--
  Created by IntelliJ IDEA.
  User: dongkaiyang
  Date: 2020/7/27
  Time: 下午10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>聊天记录</title>
    <%--引入jquery--%>
    <script src="js/jquery-3.3.1.min.js"></script>
    <%--引入bootstrap--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</head>
<body>
<div class="jumbotron">
    <div class="container">
        <p><a class="btn btn-primary btn-lg" href="#menuPage.jsp" role="button">返回主页</a></p>
        <h2>学习记录</h2>
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading">历史记录</div>
            <table class="table">
                <tr>
                    <th>id</th>
                    <th>时间</th>
                    <th>学员</th>
                    <th>教练</th>
                </tr>
                <c:forEach items="${records}" var="record" varStatus="s">
                    <tr>
                        <td>${record.userName}</td>
                        <td>${record.destination}</td>
                        <td>${record.sendDate}</td>
                        <td>${record.messageType}</td>
                        <td>${record.content}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>
