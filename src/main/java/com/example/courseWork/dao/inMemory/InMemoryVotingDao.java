package com.example.courseWork.dao.inMemory;

import com.example.courseWork.dao.VotingDao;
import com.example.courseWork.model.Voting;

public class InMemoryVotingDao extends  InMemoryAbstractDao implements VotingDao {
    public InMemoryVotingDao(InMemoryDatabase database) {
        super(database.AllVotings,database);
    }

    @Override
    public Voting getByName(String UserName,String VotingName) {
        return (Voting) database.AllUsers.get(UserName).getListOfVotings().get(VotingName);
    }
}
