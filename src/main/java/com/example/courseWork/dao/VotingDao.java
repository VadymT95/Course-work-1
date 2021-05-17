package com.example.courseWork.dao;

import com.example.courseWork.model.Voting;

public interface VotingDao {
    Voting getByName(String UserName,String VotingName);
}
