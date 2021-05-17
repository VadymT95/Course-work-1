package com.example.courseWork.model;

import java.util.HashMap;

public class Voting {

    private String name;
    private boolean active;
    private HashMap<String, Participant> listOfParticipants = new HashMap();

    public Voting(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public HashMap<String,Participant> getListOfParticipants() {
        return listOfParticipants;
    }
    public void setListOfParticipants(HashMap<String,Participant> listOfParticipants) {
        this.listOfParticipants = listOfParticipants;
    }
    public void addParticipant(Participant p){
        listOfParticipants.put(p.getName(),p);
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

}
