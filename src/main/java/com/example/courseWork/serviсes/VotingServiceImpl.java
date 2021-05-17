package com.example.courseWork.serviсes;

import com.example.courseWork.dao.DaoFactory;
import com.example.courseWork.model.Participant;
import com.example.courseWork.model.User;
import com.example.courseWork.model.Voting;

import java.util.HashMap;

public class VotingServiceImpl implements VotingService{
    DaoFactory daoFactory;
    public VotingServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public int getSumOfVotes(Voting v) {

        int sum = 0;
        for(Participant p : (v.getListOfParticipants().values())){
            sum += p.getNumberOfvotes();};
        return sum;
    }

    @Override
    public String getLeader(Voting v) {
        HashMap<String,Participant> listOfParticipants = v.getListOfParticipants();
        if(listOfParticipants.size()==0) return "--";
        int max = 0;
        String name = null;
        for(Participant p : (v.getListOfParticipants().values())){

            if(max <= p.getNumberOfvotes()){
                max = p.getNumberOfvotes();
                name = p.getName();
            }
        }
            if(max != 0) {
               Participant p = listOfParticipants.get(name);
               return p.getName() + " " + p.getSurname()+ " (" +(p.getNumberOfvotes()) +").";
            }
        return "--";
    }
    public void addVoting (User user,String name){
        Voting v = new Voting(name);
        user.addVoting(name,v);
    }
}
