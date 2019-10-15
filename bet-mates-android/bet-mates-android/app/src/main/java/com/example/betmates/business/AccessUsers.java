package com.example.betmates.business;

import java.util.ArrayList;

import com.example.betmates.application.Services;
import com.example.betmates.objects.content.User;
import com.example.betmates.persistence.UserPersistence;

public class AccessUsers {
    private UserPersistence userPersistence;
    private ArrayList<User> users;
    private User user;
    private int currUser;

    public AccessUsers() {
        userPersistence = Services.getUserPersistence();
        users = userPersistence.getAllUsers();
        user = userPersistence.getFirstUser();
        currUser = 0;
    }

    public AccessUsers(UserPersistence userPersistence, ArrayList<User> users, User user) {
//        this();
        this.userPersistence = userPersistence;
        this.users = users;
        this.user = user;
    }


    public ArrayList<User> getUsers() {
        if (userPersistence != null) {
            users = userPersistence.getAllUsers();
        }

        return users;
    }

    public User getRandomUser() {
        if (userPersistence != null) {
            user = userPersistence.getRandomUser();
        }

        return user;
    }

    public boolean userExists(String userName){
        boolean userFound = false;
        User user;
        for (int i = 0; i < users.size(); i++) {
                user = users.get(i);
                if (user.getName().equals(userName)) {
                    userFound = true;
                }
        }
        return userFound;
    }

    public User getFirstUser() {
        if (userPersistence !=null) {
            user = userPersistence.getFirstUser();
        }
        return user;
    }

    public User getUser(String userName) {
        User user = null;
        if (userName != null) {
            boolean userFound = false;
            for (int i = 0; i < users.size(); i++) {
                if (!userFound) {
                    user = users.get(i);
                    userFound = user.getName().equals(userName);
                }
             }
            if (!userFound) {
                user = null;
            }
        }
        return user;
    }

    public ArrayList<User> getUserFriends() {
        if (userPersistence != null) {
            user = userPersistence.getFirstUser();
        }

        if (user != null) {
            ArrayList<User> friends = new ArrayList<User>();
            ArrayList<String> friendsName = userPersistence.getUserFriends(user);
            for(int i=0;i<users.size();i++)
            {
                if(friendsName.contains(users.get(i).getName()))
                {
                    friends.add(users.get(i));
                }
            }
            return friends;
        }
        else {
            return null;
        }
    }

    public User addUser(User newUser) {
        return userPersistence.addUser(newUser);
    }

    public void deleteUser(User oldUser) {
        userPersistence.deleteUser(oldUser);
    }

    public void setUserLoggedIn(User user){
        userPersistence.setUserLoggedIn(user);
    }
}
