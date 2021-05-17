package com.example.courseWork.dao;

import com.example.courseWork.model.User;

import java.util.HashMap;

public interface UserDao {
    User getByName(String name);
    HashMap<String,User> getAll();
    User addByName(String name, User u);
}
