package com.example.courseWork.dao.inMemory;

import com.example.courseWork.dao.ParticipantDao;
import com.example.courseWork.model.Participant;
import com.example.courseWork.model.Voting;

public class InMemoryParticipantDao extends  InMemoryAbstractDao implements ParticipantDao {

    public InMemoryParticipantDao(InMemoryDatabase database) {
        super(database.AllParticipants,database);
    }
    @Override
    public Participant findByName(String userName,String currentVoting, String participantName) {
        Voting v = (Voting) database.AllUsers.get(userName).getListOfVotings().get(currentVoting);
        return v.getListOfParticipants().get(participantName);
    }

    @Override
    public void addParticipant(String userName, String currentVoting, Participant p) {
        ((Voting) database.AllUsers.get(userName).getListOfVotings().get(currentVoting)).addParticipant(p);
    }
}
