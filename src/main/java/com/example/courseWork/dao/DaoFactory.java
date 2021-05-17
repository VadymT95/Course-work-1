package com.example.courseWork.dao;

public interface DaoFactory {

    ParticipantDao getParticipantDao();

    UserDao getUserDao();

    VotingDao getVotingDao();
}
