<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Додання учасника</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <h1><center>Додання учасника</center></h1>
        <form class="login-form" action="ControllerServlet?Destination=AddParticipantAction&voting=${requestScope.voting}" method="POST">
            <h2>Будь-ласка, уважно заповніть усі поля!</h2>
            <table>
                <tr><td>Ім'я:<input type="text" name="name" size="10"></td></tr>
                <tr><td>Прізвище:<input type="text" name="surname" size="10"></td></tr>
                <td><input type="submit" value="підтвердити"></td>
            </table>
        </form>
        <h4>Повернутися на головну <a href="ControllerServlet?Destination=mainPage">сторінку</a>.</h4>
    </body>
</html>