package com.example.courseWork.serviсes;

import com.example.courseWork.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public interface UserService {

    User getByName(String name);
    void addUser(User user);
    boolean checkPassword(String userName, String password) throws NoSuchAlgorithmException;
    String getPasswordHash(String password) throws NoSuchAlgorithmException;
    HashMap<String,User> getAllUsers();
    void addInfoAboutVotes(User user,String votingName);
    boolean findInfoAboutVotes(User user,String votingName);
}