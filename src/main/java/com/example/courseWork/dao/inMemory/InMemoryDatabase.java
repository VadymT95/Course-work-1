package com.example.courseWork.dao.inMemory;

import com.example.courseWork.dao.DaoFactory;
import com.example.courseWork.model.*;
import java.util.*;

public class InMemoryDatabase {

    Map<String, Participant> AllParticipants;
    Map<String, Voting> AllVotings;
    Map<String, User> AllUsers;

    public InMemoryDatabase() {
        AllParticipants = new HashMap<>();
        AllVotings = new HashMap<>();
        AllUsers = new HashMap<>();
    }

    public DaoFactory getDaoFactory() {
        return new InMemoryDaoFactory(this);
    }

}
