<%@ page import="com.example.courseWork.serviсes.UserService" %>
<%@ page import="com.example.courseWork.model.User" %>
<%@ page import="com.example.courseWork.model.Voting" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <link rel="stylesheet" type="text/css" href=mainMenu">

    <head>
        <title>Головна сторінка</title>
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
        <h1>Головна сторінка</h1>
        <p align="center" style="color: black; font-size:30px">
            Перелік всіх голосуваннь:</p>
        <br/>
        <table border="1" width="100%" cellpadding="5">
            <tr>
                <th>№ голосування</th>
                <th>Назва</th>
                <th>Статус</th>
                <th>Власник</th>
                <th>Сторінка голосування</th>
            </tr>
            <%! int numder=0; %>
            <% UserService userService = (UserService) request.getAttribute("userService");
             for(User currentUser : userService.getAllUsers().values()){
                 HashMap<String,Voting> currentListOfVotings = currentUser.getListOfVotings();
                 for(Voting currentVoting : currentListOfVotings.values()){
                    if(currentUser != session.getAttribute("user")){%>
                    <%numder++;%>
                    <tr>
                        <td> <%=numder%></td>
                        <td><%=currentVoting.getName()%></td>

                        <%if(currentVoting.isActive()){ %>
                            <td>Голосування активне</td>
                            <td><%=currentUser.getName() %> </td>
                        <td><a href="ControllerServlet?Destination=votingPage&User=<%=currentUser.getName()%>&voting=<%=currentVoting.getName()%>">детальніше</a></td>
                        <%}%>
                        <%if(!(currentVoting.isActive())){ %>
                            <td>Голосування пасивне</td>
                            <td><%=currentUser.getName() %></td>
                            <td>Голосування неактивне</td>
                        <%}%>
                    </tr>
            <%}}} %>
            <%numder =0;%>
        </table>
    </body>
</html>
