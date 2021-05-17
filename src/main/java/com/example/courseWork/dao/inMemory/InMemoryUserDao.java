package com.example.courseWork.dao.inMemory;

import com.example.courseWork.dao.UserDao;
import com.example.courseWork.model.User;

import java.util.HashMap;

public class InMemoryUserDao extends InMemoryAbstractDao implements UserDao {
    public InMemoryUserDao(InMemoryDatabase database) {
        super(database.AllUsers,database);
    }

    @Override
    public User getByName(String name) {
        return database.AllUsers.get(name);
    }

    @Override
    public HashMap<String,User> getAll() {
        return (HashMap<String, User>) database.AllUsers;
    }

    @Override
    public User addByName(String name, User u) {
        return database.AllUsers.put(name,u);
    }
}
