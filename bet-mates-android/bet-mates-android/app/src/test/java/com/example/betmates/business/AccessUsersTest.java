package com.example.betmates.business;

import com.example.betmates.objects.content.User;
import com.example.betmates.persistence.UserPersistence;
import com.example.betmates.persistence.stubs.UserPersistenceStub;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class AccessUsersTest {

    private AccessUsers accessUsers;
    private UserPersistence userPersistence;
    private ArrayList<User> users;
    private User user;

    @Before
    public void setUp() {
        userPersistence = new UserPersistenceStub();
        users = userPersistence.getAllUsers();
        user = userPersistence.getFirstUser();
        accessUsers = new AccessUsers(userPersistence, users, user);
    }

    @Test
    public void testGetUsers() {
        final ArrayList<User> returnUsers;
        System.out.println("\nStarting AccessUsersTest: Get list of Users from userPersistence.");

        returnUsers = accessUsers.getUsers();
        assertNotNull("List of Users should not be null", returnUsers);
        assertSame("List of Bets should be the same ArrayList<User> object as the object passed through constructor.", users, returnUsers);

        System.out.println("\nFinished AccessUsersTest: Get list of Users from userPersistence.");
    }

    @Test
    public void testGetRandonUser() {
        final User returnedUser;
        System.out.println("\nStarting AccessUsersTest: Get random user from userPersistence.");

        returnedUser = accessUsers.getRandomUser();
        assertNotNull("Return a random user should not be null.", returnedUser);

        System.out.println("\nFinished AccessUserTest: Get random user form userPersistence.");
    }

    @Test
    public void testUserExistsWhenInputValidUserName() {
        final User user = new User("Ed", 20, 10);
        final String userName = user.getName();
        System.out.println("\nStarting AccessUsersTest: Check user existence when pass valid username.");

        accessUsers.addUser(user);
        assertTrue("Should return True when pass a valid user name", accessUsers.userExists(userName));

        System.out.println("\nFinished AccessUsersTest: Check User existence when pass valid username.");
    }

    @Test
    public void testUserExistsWhenInputInvalidUserName() {
        final String userName = "not valid";
        System.out.println("\nStarting AccessUsersTest: Check user existence when pass invalid username.");

        assertFalse("Should return False when pass a invalid user name.", accessUsers.userExists(userName));

        System.out.println("\nFinished AccessUsersTest: Check user existence when pass invalid username.");
    }

    @Test
    public void testGetFirstUser() {
        final User returnedUser;
        System.out.println("\nStarting AccessUsersTest: Get first user from userPersistence.");

        returnedUser = accessUsers.getFirstUser();
        assertNotNull("Return first user in userPersistence should not be null.", returnedUser);
        assertSame("Returned user should be the same user object passed to the constructor", user, returnedUser);

        System.out.println("\nFinished AccessUserTest: Get first user form userPersistence.");
    }

    @Test
    public void testGetUserWhenPassValidName() {
        final User user = new User("ed", 20, 20);
        System.out.println("\nStarting AccessUsersTest: Get User from userPersistence by user name.");

        accessUsers.addUser(user);
        assertSame("The returned user object should be the same as the user we mocked.", user, accessUsers.getUser(user.getName()));;

        System.out.println("\nFinished AccessUserTest: Get User from userPersistence by user name.");
    }

    @Test
    public void testGetUserWhenPassInvalidName() {
        System.out.println("\nStarting AccessUsersTest: Get Uer from userPersistence by user name.");

        assertNull("This function should return Null when passing invalid user name.", accessUsers.getUser(""));

        System.out.println("\nFinished AccessUserTest: Get User from userPersistence by user name.");
    }

    @Test
    public void testGetUserFriends() {
        final ArrayList<User> returnedFriends;
        System.out.println("\nStarting AccessUsersTest: Get user friends from userPersistence.");

        returnedFriends = accessUsers.getUserFriends();
        for (int i = 0; i < returnedFriends.size(); i++) {
            assertSame("Returned user should be the same user object passed to the constructor", userPersistence.getUserFriends(user).get(i), returnedFriends.get(i).getName());
        }


        System.out.println("\nFinished AccessUserTest: Get user friends form userPersistence.");
    }

    @Test
    public void testGetUserFriendsWhenNull() {
        System.out.println("\nStarting AccessUsersTest: Get user friends from userPersistence.");

        accessUsers = new AccessUsers(null, null, null);
        ArrayList<User> users = accessUsers.getUserFriends();
        assertNull(users);
        System.out.println("\nFinished AccessUserTest: Get user friends form userPersistence.");
    }

    @Test
    public void testAddUser() {
        final User myUser = new User();
        final User returnedUser;
        System.out.println("\nStarting AccessUsersTest: Add user to userPersistence.");

        returnedUser = accessUsers.addUser(myUser);
        assertNotNull("Returned user should not be null when pass non null User", returnedUser);
        assertSame("The returned user should be the same as rhe user passed in.", myUser, returnedUser);

        System.out.println("\nFinished AccessUserTest: Add user to userPersistence.");
    }

    @Test
    public void testAddUserWhenNull() {
        final User myUser = null;
        final User returnedUser;
        System.out.println("\nStarting AccessUsersTest: Add user to userPersistence.");

        returnedUser = accessUsers.addUser(myUser);
        assertNull("Returned user should be null when pass null", returnedUser);

        System.out.println("\nFinished AccessUserTest: Add user to userPersistence.");
    }

    @Test
    public void testDeleteUser() {
        final User user = new User("Ed", 20, 20);
        userPersistence.addUser(user);
        System.out.println("\nStarting AccessUsersTest: Delete user from userPersistence.");

        accessUsers.deleteUser(user);
        assertNull("Should return Null, since Already delete that user", accessUsers.getUser(user.getName()));

        System.out.println("\nFinished AccessUsersTest: Delete user from userPersistence.");
    }
}
