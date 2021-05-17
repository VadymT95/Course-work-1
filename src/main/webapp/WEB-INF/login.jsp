<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Вхід</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <h1><center>Вхід</center></h1>
        <form class="login-form" action="ControllerServlet?Destination=login" method="POST">
            <h2>Будь-ласка, уважно заповніть усі поля! </h2>
            <table>
                <tr><td>Ім'я користувача:<input type="text" name="name" size="10"></td></tr>
                <tr><td>Пароль:<input type="password" name="password" size="10"></td></tr>
                <td><input type="submit" value="Підтвердити"></td>
            </table>
        </form>
        <h4>Повернутися на головну <a href="ControllerServlet?Destination=mainPage">сторінку</a>.</h4>
        <h4>Ви ще не  <a href="ControllerServlet?Destination=redirect&address=register">зареєстровані</a>?</h4>
        </center>
    </body>
</html>