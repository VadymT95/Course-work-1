<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 29.04.2021
  Time: 09:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Реєстрація</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <form class="login-form" action="ControllerServlet?Destination=register" method="POST">
            <h1><center>Реєстрація</center></h1>
            <h2>Будь-ласка, уважно заповніть усі поля! </h2>
            <table>
                <tr><td>Ім'я користувача:<input type="text"  name="name" required></td></tr>
                <tr><td>Логін користувача:<input type="text"  name="login" required></td></tr>
                <tr><td>Пароль:<input type="password" name="psw" required></td></tr>
                <tr><td>Повторення паролю:<input type="password" name="psw-repeat" required></td></tr>
            </table>
            <button type="submit" class="registerbtn">Зареєструватися</button>
            <div class="container signin">
                <h4>Повернутися на головну <a href="ControllerServlet?Destination=mainPage">сторінку</a>.</h4>
                <p>Уже маєте аккаунт? <a href="ControllerServlet?Destination=redirect&address=login">Вхід</a>.</p>
            </div>
        </form>
    </body>
</html>
