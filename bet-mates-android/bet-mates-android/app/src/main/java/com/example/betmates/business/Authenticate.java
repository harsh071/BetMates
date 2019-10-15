package com.example.betmates.business;

import com.example.betmates.objects.content.User;


public class Authenticate {
    private final int MIN_AGE = 18;
    private AccessUsers accessUsers;

    public Authenticate() {
        accessUsers = new AccessUsers();
    }

    public Authenticate(AccessUsers accessUsers, User user) {
        this.accessUsers = accessUsers;
        this.accessUsers.addUser(user);
    }

    public User authenticator(String userName, String password){
        User user = accessUsers.getUser(userName);

        if (!checkPassword(user, password)) {
            user = null;
        }

        return user;
    }

    public User authenticator(String userName,String password, String age, String confirmPassword){
        boolean verifyPass = verifyPasswords(password,confirmPassword);
        boolean verifyAge = verifyAge(age);
        boolean verifyUserName = !userExists(userName);

        User newUser = null;
        if(verifyAge && verifyPass && verifyUserName) {
            newUser = new User(userName, Integer.parseInt(age), (float) 10.0, password);
            accessUsers.addUser(newUser);
        }
        return newUser;
    }

    private boolean verifyPasswords(String password, String confirmPassword){
        return password.equals(confirmPassword);
    }

    public boolean verifyAge(String age) {
        return Integer.parseInt(age) >= MIN_AGE;
    }

    public boolean userExists(String userName){
        return accessUsers.userExists(userName);
    }

    private boolean checkPassword(User user, String password)
    {
        if (user != null && user.getPassword().equals(password)) {
            accessUsers.setUserLoggedIn(user);
            return true;
        }
        return false;
    }

}
