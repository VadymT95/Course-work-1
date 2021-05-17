<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <link rel="stylesheet" type="text/css" href=mainMenu">
    <head>
        <title>Власні голосування</title>
        <style type="text/css">
            .menu{
                list-style-type: none;
                padding-left: 0;
                margin-left: 0;
            }
            .menu a {
                text-decoration: none;
                font-family: sans-serif;
                color: #5757a0;
                display: inline-block;
                padding: 10px 20px;
                background-color: lavender;
                border-bottom: 5px solid #5757a0;
            }
            .menu li{
                display: inline;
            }
        </style>
    </head>
    <ul class="menu">
        <li><a href="ControllerServlet?Destination=mainPage">Головна</a></li>

        <c:if test="${empty user}">
            <li><a href="ControllerServlet?Destination=redirect&address=login">Власні голосування</a></li>
            <li><a href="ControllerServlet?Destination=redirect&address=login">увійти</a></li>
        </c:if>
        <c:if test="${!empty user}">
            <li><a href="ControllerServlet?Destination=redirect&address=userVoting">Власні голосування</a></li>
            <li><a href="ControllerServlet?Destination=exit" onclick="return confirm('Ви впевнені?')"> ${sessionScope.user.name}${" (Вихід)"}</a></li>
        </c:if>
    </ul>
    <body>
        <h4>Це сторінка з переліком власних голосуваннь. Для детальнішої інформації про кожне голосування натисніть на відповідне голосування в таблиці. Також ви можете створити нове голосування.</h4>
        <h4><a href="ControllerServlet?Destination=redirect&address=addVoting">Додати голосування.</a></h4>
        <table border="1" width="100%" cellpadding="5">
            <tr>
                <th>№ голосования</th>
                <th>название</th>
                <th>статус</th>
                <th>ссылка на страницу</th>
            </tr>
            <c:forEach items="${sessionScope.user.listOfVotings.values()}" var="currentVoting" varStatus="theCount">
            <tr>
                <td> ${theCount.index+1} </td>
                <td>${currentVoting.name}</td>
                <c:if test="${currentVoting.active}">
                <td>Голосування активне</td>
                </c:if>
                <c:if test="${!(currentVoting.active)}">
                    <td>Голосування пасивне</td>
                </c:if>
                <td><a href="ControllerServlet?Destination=adminVotingPage&voting=${currentVoting.name}">Редагувати</a></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
