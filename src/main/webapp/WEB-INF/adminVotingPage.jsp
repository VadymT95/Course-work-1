<%@ page import="com.example.courseWork.model.Voting" %>
<%@ page import="com.example.courseWork.model.Participant" %>
<%@ page import="com.example.courseWork.serviсes.VotingService" %>
<%@ page import="com.example.courseWork.serviсes.VotingServiceImpl" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" type="text/css" href=mainMenu">
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
    <ul class="menu">
        <li><a href="ControllerServlet?Destination=mainPage">Головна</a></li>
        <c:if test="${empty user}">
            <li><a href="ControllerServlet?Destination=redirect&address=login">Власні голосування</a></li>
            <li><a href="ControllerServlet?Destination=redirect&address=login">Увійти</a></li>
        </c:if>
        <c:if test="${!empty user}">
            <li><a href="ControllerServlet?Destination=redirect&address=userVoting">Власні голосування</a></li>
            <li><a href="ControllerServlet?Destination=exit" onclick="return confirm('Ви впевнені?')"> ${sessionScope.user.name}${" (Вихід)"}</a></li>
        </c:if>
    </ul>
    <% Voting currentVoting = (Voting) request.getAttribute("voting");
    VotingService votingService = (VotingService) getServletConfig().getServletContext().getAttribute("votingService");%>
    <head>
        <title>Сторінка голосування№</title>
    </head>
    <body>
        <h2>Налаштування голосування:</h2>
        <h4><a href="ControllerServlet?Destination=AddParticipant&voting=${requestScope.voting.name}">додати учасника</a></h4>
        <%if(currentVoting.isActive()){ %>
            <h4><a href="ControllerServlet?Destination=activation&voting=${requestScope.voting2.name}">вимкнути голосування</a></h4>
        <%}%>
        <%if(!(currentVoting.isActive())){ %>
            <h4><a href="ControllerServlet?Destination=activation&voting=${requestScope.voting2.name}">увімкнути голосування</a></h4>
        <%}%>
        <h2>Загальна інформація:</h2>
        <h4>Всього голосів: <%=votingService.getSumOfVotes(currentVoting)%></h4>
        <h4>Кількість учасників: <%= currentVoting.getListOfParticipants().size()%></h4>
        <h4>Лідер голосування: <%=votingService.getLeader(currentVoting)%></h4>
        <p align="center" style="color: black; font-size:30px">
            Перелік учасників:
        </p>
        <table border="1" width="100%" cellpadding="5">
            <c:if test="${!empty user}">
                <tr>
                    <th>№ учасника</th>
                    <th>Особисті дані</th>
                    <th>кількість голосів</th>
                </tr>
                <c:forEach items="${requestScope.voting2.listOfParticipants.values()}" var="currentParticipants" varStatus="theCount">
                <tr>
                    <td> ${theCount.index+1} </td>
                    <td>${currentParticipants.name}${" "}${currentParticipants.surname}</td>
                    <td>${currentParticipants.numberOfvotes}</td>
                </tr>
                </c:forEach>
            </c:if>
        </table>
    </body>
</html>
