package com.example.betmates.persistence.stubs;

import java.util.ArrayList;
import java.util.Random;

import com.example.betmates.objects.content.User;
import com.example.betmates.persistence.UserPersistence;

public class UserPersistenceStub implements UserPersistence {

    private ArrayList<User> users;

    public UserPersistenceStub() {
        this.users = new ArrayList<>();

        User initUser = new User("Bobby", 20, 40,"password1");

        // Let's give this guy some friends
        User friend1 = new User("Tim", 123, 65);
        User friend2 = new User("Kevin", 54, 8877);
        User friend3 = new User("Robin", 16, 4);

        initUser.addFriend(friend1);
        initUser.addFriend(friend2);
        initUser.addFriend(friend3);

        // Add the users to the list
        users.add(initUser); // only initUser has friends :(
        users.add(friend1);
        users.add(friend2);
        users.add(friend3);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return this.users;
    }

    @Override
    public User getFirstUser() {
        if(users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User getRandomUser() {
        Random rand = new Random();
        return users.get(rand.nextInt(users.size()));
    }

    @Override
    public ArrayList<String> getUserFriends(User currUser) {
        ArrayList<String> result  = new ArrayList<String>();
        ArrayList<User> friends = currUser.getFriendsList();
        for(int i=0;i<friends.size();i++)
        {
            result.add(friends.get(i).getName());
        }
        return result;
    }

    @Override
    public User addUser(User newUser) {
        this.users.add(newUser);
        return newUser;
    }

    @Override
    public void deleteUser(User oldUser) {
        this.users.remove(oldUser);
    }

    @Override
    public void setUserLoggedIn(User user){
        User newUser;
        String userName = user.getName();
        for (int i = 0; i < users.size(); i++) {
            newUser = users.get(i);
            if (newUser.getName().equals(userName)) {
                users.remove(i);
                users.add(0,user);
                break;
            }
        }

    }


}