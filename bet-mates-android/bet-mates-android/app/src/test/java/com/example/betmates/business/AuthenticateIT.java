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
import java.util.ArrayList;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AuthenticateIT {

    private AccessUsers accessUsers;
    private Authenticate authenticate;
    private File tempDB;
    DBConnectionManager dbConnectionManager;
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
        this.authenticate = new Authenticate();
        this.authenticate = new Authenticate(accessUsers, user);
    }

    @Test
    public void testAuthenticator() {
        User user = new User("cdascds", 10, 10, "cdascdas");
        User returnUser = this.authenticate.authenticator(user.getName(), user.getPassword());

        assertNull(returnUser);
    }

    @Test
    public void testAuthenticatorWhenExist() {
        User returnUser = this.authenticate.authenticator("Bobby", "123");

        assertNotNull(returnUser);
    }

    @Test
    public void testAuthenticatorAll() {
        User returnUser = this.authenticate.authenticator("fdsaf", "fdsa", "20", "dasvd");

        assertNull("Should return null when user not exists",returnUser);
    }

    @Test
    public void testAuthenticatorAllWhenValid() {
        User returnUser = this.authenticate.authenticator("Ed", "1234", "20", "1234");
        assertNotNull(returnUser);
    }

    @Test
    public void testPass() {
        assertTrue("Age should be greater than 18", this.authenticate.verifyAge("20"));
        assertFalse("Age should be greater than 18", this.authenticate.verifyAge("10"));
    }

    @Test
    public void testExists() {
        assertTrue("Should be return true when user name exists already", this.authenticate.userExists("Bobby"));
        assertFalse("should be return false when user name not exists" ,this.authenticate.userExists("dsa"));
    }

}
