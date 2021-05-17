package com.example.courseWork.serviсes;

import com.example.courseWork.dao.DaoFactory;
import com.example.courseWork.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.UnaryOperator;
import java.security.*;

public class UserServiceImpl implements UserService {

    DaoFactory daoFactory;
    UnaryOperator<String> passwordHasher;

    public UserServiceImpl(DaoFactory daoFactory, UnaryOperator<String> passwordHasher) {
        this.daoFactory = daoFactory;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public User getByName(String name) {
        return daoFactory.getUserDao().getByName(name);
    }

    @Override
    public void addUser(User user) {
        daoFactory.getUserDao().addByName(user.getName(),user);
    }

    @Override
    public boolean checkPassword(String userName, String password) throws NoSuchAlgorithmException {
        return daoFactory.getUserDao().getByName(userName).getPasswordHash().equals(getPasswordHash(password));
    }

    @Override
    public String getPasswordHash(String userPassword)  throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(userPassword.getBytes());
        byte[] bytes = md.digest();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    @Override
    public HashMap<String, User> getAllUsers() {
        return daoFactory.getUserDao().getAll();
    }
    @Override
    public void addInfoAboutVotes(User user,String votingName){
        if(user.getActiveVotesOfThisUser().get(user.getName()) != null){
            for(String cuurentName : user.getActiveVotesOfThisUser().get(user.getName())){
                if(cuurentName.equals(votingName)) return;
            }
            user.getActiveVotesOfThisUser().get(user.getName()).add(votingName);
        }else{
            ArrayList<String> temp = new ArrayList<>();
            temp.add(votingName);
            user.getActiveVotesOfThisUser().put(user.getName(),temp);
        }
    }
    public boolean findInfoAboutVotes(User user,String votingName) {
        if (user.getActiveVotesOfThisUser().get(user.getName()) == null) {
            return false;
        } else {
            for (String cuurentName : user.getActiveVotesOfThisUser().get(user.getName())) {
                if (cuurentName.equals(votingName)) return true;
            }
        }
        return false;
    }
}
