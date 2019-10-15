package com.example.betmates.persistence;

import java.util.ArrayList;
import com.example.betmates.objects.content.User;

public interface UserPersistence {

    ArrayList<User> getAllUsers();

    User getFirstUser();

    User getRandomUser();

    ArrayList<String> getUserFriends(final User currUser);

    User addUser(final User newUser);

    void deleteUser(final User oldUser);

    void setUserLoggedIn(final User user);

}
