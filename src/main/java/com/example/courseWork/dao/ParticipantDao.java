package com.example.courseWork.dao;

import com.example.courseWork.model.*;

public interface ParticipantDao {

    Participant findByName(String userName,String currentVoting, String participantName);

    void addParticipant(String userName, String currentVoting, Participant p);
}