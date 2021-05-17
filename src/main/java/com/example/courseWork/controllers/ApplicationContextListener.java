package com.example.courseWork.controllers;

import com.example.courseWork.dao.DaoFactory;
import com.example.courseWork.dao.inMemory.InMemoryDatabase;
import com.example.courseWork.serviсes.*;

import javax.servlet.*;
import java.util.function.UnaryOperator;

public class ApplicationContextListener{

    public static void contextInitialized(ServletConfig config) {
        InMemoryDatabase database = new InMemoryDatabase();
        DaoFactory daoFactory = database.getDaoFactory();

        VotingService votingService = new VotingServiceImpl(daoFactory);
        config.getServletContext().setAttribute("votingService", votingService);

        UserService userService = new UserServiceImpl(daoFactory, UnaryOperator.identity());
        config.getServletContext().setAttribute("userService", userService);
    }
}