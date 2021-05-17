package com.example.courseWork.controllers;

import com.example.courseWork.model.Participant;
import com.example.courseWork.model.User;
import com.example.courseWork.model.Voting;
import com.example.courseWork.serviсes.UserService;
import com.example.courseWork.serviсes.VotingService;
import com.example.courseWork.serviсes.VotingServiceImpl;
import com.example.courseWork.validators.StringValidator;
import com.example.courseWork.validators.Validator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.applet.AppletContext;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@WebServlet(name = "ControllerServlet", value = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {

    VotingService votingService;
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {

        ApplicationContextListener.contextInitialized(config);
        votingService = (VotingService) config.getServletContext().getAttribute("votingService");
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("Destination");
        if (path == null) path = "/";
        switch (path) {
            case "mainPage":
                mainPage(request, response);
            break;
            case "login":
                login(request, response);
                break;
            case "register":
                register(request, response);
                break;
            case "votingPage":
                votingPage(request, response);
                break;
            case "adminVotingPage":
                adminVotingPage(request, response);
                break;
            case "exit":
                exit(request, response);
                break;
            case "vote":
                vote(request, response);
                break;
            case "AddParticipant":
                AddParticipant(request, response);
                break;
            case "AddParticipantAction":
                AddParticipantAction(request, response);
                break;
            case "AddVoteAction":
                AddVoteAction(request, response);
                break;
            case "activation":
                activation(request, response);
                break;
            case "redirect":
                redirect(request, response);
                break;
        }
    }
    protected void mainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("userService",userService);
        request.getRequestDispatcher("WEB-INF/mainPage.jsp").forward(request,response);
    }
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validator validator = new StringValidator();
        try{
            String name = request.getParameter("name");
            validator.validate(name);
            String password = request.getParameter("password");
            validator.validate(password);

            if(userService.getByName( name )== null)  throw new IllegalArgumentException("Введені данні некоректні!");
            if(userService.checkPassword(name,password)){
                request.getSession().setAttribute("user", userService.getByName(name));
            }else{
                throw new IllegalArgumentException("Введені данні некоректні!");
            }
            }catch (Exception e){
                error(request,response,e.getMessage());
            }

            response.sendRedirect("ControllerServlet?Destination=mainPage");

    }
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validator validator = new StringValidator();
        try{
            String psw = request.getParameter("psw");
            String pswRepeat = request.getParameter("psw-repeat");
            String name = request.getParameter("name");
            String login = request.getParameter("login");
            validator.validate(psw);
            validator.validate(pswRepeat);
            validator.validate(name);
            validator.validate(login);

            if(userService.getPasswordHash(psw).equals(userService.getPasswordHash(pswRepeat))){
                User user = new User(name,login,userService.getPasswordHash(psw));
                userService.addUser(user);
                request.getSession().setAttribute("user", user);
            }else{ throw new IllegalArgumentException("Паролі не співпадають!"); }
        }catch (Exception e){
            error(request,response,e.getMessage());
        }
        response.sendRedirect("ControllerServlet?Destination=mainPage");
    }
    protected void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        request.getRequestDispatcher("ControllerServlet?Destination=mainPage").forward(request,response);
    }
    protected void votingPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String adminOfVoting = request.getParameter("User");
        String voteName = request.getParameter("voting");

        User currentUser = userService.getByName(adminOfVoting);
        Voting v1 = (Voting) currentUser.getListOfVotings().get(voteName);

        request.setAttribute("voting",v1);
        request.setAttribute("adminOfVoting",currentUser);
        request.getRequestDispatcher("WEB-INF/votingPage.jsp").forward(request,response);
    }
    protected void adminVotingPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String voteName = request.getParameter("voting");
        User currentUser = (User) request.getSession().getAttribute("user");
        Voting v1 = (Voting) currentUser.getListOfVotings().get(voteName);
        request.setAttribute("voting",v1);
        request.setAttribute("voting2",v1);
        request.getRequestDispatcher("WEB-INF/adminVotingPage.jsp").forward(request,response);
    }
    protected void vote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("user");

        String adminOfVoting = request.getParameter("adminUser");
        String voteName = request.getParameter("voting");
        String particippant = request.getParameter("participant");

        if(userService.findInfoAboutVotes(currentUser,voteName)){
            error(request,response,"Ви вже використали свій голос.");
        }else {;
            Voting currentVoting = (Voting) userService.getByName(adminOfVoting).getListOfVotings().get(voteName);
            currentVoting.getListOfParticipants().get(particippant).addOneVote();

            userService.addInfoAboutVotes(currentUser,voteName);

            request.getRequestDispatcher("ControllerServlet?Destination=mainPage").forward(request,response);
        }
    }
    protected void error(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
    }
    protected void AddParticipant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String voteName = request.getParameter("voting");
        request.setAttribute("voting",voteName);
        request.getRequestDispatcher("WEB-INF/AddParticipant.jsp").forward(request,response);
    }
    protected void AddParticipantAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validator validator = new StringValidator();
        try{
            String voting = request.getParameter("voting");
            User currentUser = (User) request.getSession().getAttribute("user");
            Voting currentVoting = (Voting) currentUser.getListOfVotings().get(voting);

            String name = request.getParameter("name");
            String surName = request.getParameter("surname");
            validator.validate(name);
            validator.validate(surName);
            currentVoting.addParticipant(new Participant(name,surName));
        }catch (Exception e){
            error(request,response,e.getMessage());
        }
        request.getRequestDispatcher("ControllerServlet?Destination=mainPage").forward(request,response);
    }
    protected void AddVoteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validator validator = new StringValidator();
        try{
            String name = request.getParameter("name");
            User currentUser = (User) request.getSession().getAttribute("user");
            validator.validate(name);
            votingService.addVoting(currentUser,name);
        }catch (Exception e){
            error(request,response,e.getMessage());
        }
        request.getRequestDispatcher("ControllerServlet?Destination=mainPage").forward(request,response);
    }
    protected void activation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String votingName = request.getParameter("voting");
        User currentUser = (User) request.getSession().getAttribute("user");
        Voting voting = (Voting) currentUser.getListOfVotings().get(votingName);
        voting.setActive(!(voting.isActive()));
        request.getRequestDispatcher("ControllerServlet?Destination=adminVotingPage&voting="+votingName).forward(request,response);
    }
    protected void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = request.getParameter("address");
        request.getRequestDispatcher("WEB-INF/"+address+".jsp").forward(request,response);
    }
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
