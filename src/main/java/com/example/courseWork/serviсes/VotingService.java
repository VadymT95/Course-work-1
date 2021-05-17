package com.example.courseWork.serviсes;

import com.example.courseWork.model.User;
import com.example.courseWork.model.Voting;

public interface VotingService {
    int getSumOfVotes(Voting v);
    String getLeader(Voting v);
    void addVoting(User user, String name);
}
