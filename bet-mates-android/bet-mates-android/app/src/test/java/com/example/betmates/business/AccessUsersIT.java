package com.example.betmates.business;

import com.example.betmates.application.DBConnectionManager;
import com.example.betmates.objects.content.User;
import com.example.betmates.persistence.UserPersistence;
import com.example.betmates.persistence.hsqldb.UserPersistenceHSQLDB;
import com.example.betmates.utils.TestUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AccessUsersIT {

    private AccessUsers accessUsers;
    private File tempDB;
    private DBConnectionManager dbConnectionManager;
    private ArrayList<User> users;
    private User user = new User("name", 20, 12,"123");

    @Before
    public void setUp() throws IOException {
        dbConnectionManager = new DBConnectionManager();
        this.tempDB = TestUtils.copyDB();
        String filePath = this.tempDB.getAbsolutePath().replace(".script", "");
        DBConnectionManager.setDBPathName(filePath);
        final UserPersistence userPersistence = new UserPersistenceHSQLDB(dbConnectionManager);
        users = userPersistence.getAllUsers();
        this.accessUsers = new AccessUsers(userPersistence, users, user);
    }

    @Test
    public void testGetRandomUser() {
        ArrayList<User> users;

        users = accessUsers.getUsers();
        assertNotNull("list of users should not be null", users);
    }

    @Test
    public void testGetRandom() {
        User user = accessUsers.getRandomUser();
        assertNull(user);
    }

    @Test
    public void testUserExist() {
        String name = "Bobby";
        assertTrue(accessUsers.userExists(name));
        assertFalse(accessUsers.userExists("randasveav"));
    }

    @Test
    public void getFist() {
        assertNotNull("The first user should not be null", accessUsers.getFirstUser());
    }

    @Test
    public void getUser() {
        String name = "Bobby";
        assertNotNull(accessUsers.getUser(name));
        assertNull(accessUsers.getUser("nfdjsngg"));
    }

    @Test
    public void testGetUserFriend() {
        ArrayList<User> users;

        users = accessUsers.getUserFriends();
        assertNotNull(users);
    }

    @Test
    public void testAddUser() {
        User returnUser = accessUsers.addUser(user);

        assertNotNull(returnUser);
    }

    @Test
    public void testDeleteUser() {
        User user = accessUsers.getFirstUser();
        assertNotNull(user);

        accessUsers.deleteUser(user);
        assertFalse(accessUsers.userExists(user.getName()));
    }

    @Test
    public void testSetUserLoggedIn() {
        assertNotNull(accessUsers.addUser(user));

        accessUsers.setUserLoggedIn(user);

        assertEquals(accessUsers.getFirstUser().getName(), user.getName());
    }

}
