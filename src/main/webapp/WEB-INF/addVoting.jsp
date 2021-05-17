<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Створення голосування</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <h1><center>Створення голосування</center></h1>
        <form class="login-form" action="ControllerServlet?Destination=AddVoteAction" method="POST">
            <h2>Будь-ласка, уважно заповніть усі поля! </h2>
            <table>
                <tr><td>Назва нового голосування:<input type="text" name="name" size="10"></td></tr>
                <td><input type="submit" value="підтвердити"></td>
            </table>
        </form>
        <h4>Повернутися на головну <a href="ControllerServlet?Destination=mainPage">сторінку</a>.</h4>
        </center>
    </body>
</html>