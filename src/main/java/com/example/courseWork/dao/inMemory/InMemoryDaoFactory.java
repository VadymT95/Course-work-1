package com.example.courseWork.dao.inMemory;

import com.example.courseWork.dao.*;

class InMemoryDaoFactory implements DaoFactory {

    private InMemoryDatabase database;
    private VotingDao votingDao;
    private ParticipantDao participantDao;
    private UserDao userDao;

    InMemoryDaoFactory(InMemoryDatabase database) {
        this.database = database;
        votingDao = new InMemoryVotingDao(database);
        participantDao = new InMemoryParticipantDao(database);
        userDao = new InMemoryUserDao(database);
    }

    @Override
    public VotingDao getVotingDao() {
        return votingDao;
    }

    @Override
    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }

}