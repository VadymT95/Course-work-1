package com.example.courseWork.model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private String name;
    private String login;
    private String passwordHash;
    private HashMap<String,Voting> listOfVotings = new HashMap<>();
    private HashMap<String, ArrayList<String>> activeVotesOfThisUser = new HashMap<>();

    public User(String name, String login, String passwordHash) {
        this.name = name;
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public HashMap getListOfVotings() {
        return listOfVotings;
    }

    public void setListOfVotings(HashMap listOfVotings) {
        this.listOfVotings = listOfVotings;
    }

    public HashMap<String, ArrayList<String>> getActiveVotesOfThisUser() {
        return activeVotesOfThisUser;
    }
    public void setActiveVotesOfThisUser(HashMap<String, ArrayList<String>> activeVotesOfThisUser) {
        this.activeVotesOfThisUser = activeVotesOfThisUser;
    }
    public void addVoting(String name, Voting voting){
        this.listOfVotings.put(name,voting);
    }
}

