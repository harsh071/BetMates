package com.example.betmates.objects;

import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;


public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void testAddBet() {
        final Bet bet = new Bet();

        System.out.println("\nStarting UserTest: Add bet into user");

        user.addBet(bet);
        assertTrue("the current bet must contain bet.", user.getCurrentBets().contains(bet));

        System.out.println("\nFinished UserTest: Add bet into user");
    }

    @Test
    public void testRemoveBet() {
        final Bet bet = new Bet();

        System.out.println("\nStarting UserTest: remove bet from user");

        user.addBet(bet);
        user.removeBet(bet);
        assertFalse("the current bet should not contain bet.", user.getCurrentBets().contains(bet));

        System.out.println("\nFinished UserTest: remove bet from user");
    }

    @Test
    public void testAddFriend() {
        final User user = new User();

        System.out.println("\nStarting UserTest: add friend into user");

        user.addFriend(user);
        assertTrue("the friend list should contain user.", user.friendExists(user));

        System.out.println("\nFinished UserTest: add friend into user");
    }

    @Test
    public void testRemoveFriend() {
        final User user = new User();

        System.out.println("\nStarting UserTest: remove friend from user");

        System.out.println("the list of user's friends are: " + user.getFriendsList());
        user.addFriend(user);
        System.out.println("the list of user's friends are: " + user.getFriendsList());
        System.out.println("the friend we added is: " + user);
        user.removeFriend(user);
        System.out.println("The frined list when i remove this friend: " + user.getFriendsList());
        assertFalse("the friend should not contain in list of friends.", user.friendExists(user));

        System.out.println("\nFinished UserTest: remove friend from user");
    }

    @Test
    public void testFriendExists() {
        System.out.println("\nstarting UserTest: find Friend exists in the friend list.");

        assertFalse("The should be false. since we did not add the user to the list yet", user.friendExists(new User()));
        System.out.println("\nFinished UserTest: find Friend exists in the friend list.");
    }

    @Test
    public void testModifyBalanceWhenAdd() {
        System.out.println("\nStarting UserTest: Modify balance");

        final float newBalance = user.getBalance() + 10;
        assertEquals("The new balance should be equals to modifies balance", newBalance, user.modifyBalance(10, true), 0.001);

        System.out.println("\nFinished UserTest: Modify balance");
    }

    @Test
    public void testModifyBalanceWhenSub() {
        System.out.println("\nStarting UserTest: Modify balance");

        final float newBalance = user.getBalance() - 10;
        assertEquals("The new balance should be equals to modifies balance", newBalance, user.modifyBalance(10, false), 0.001);

        System.out.println("\nFinished UserTest: Modify balance");
    }

    @Test
    public void testSetName() {
        System.out.println("\nStarting UserTest: set name");

        final String newName = "newName";
        user.setName(newName);
        assertSame("The new name should be the same as newName", newName, user.getName());

        System.out.println("\nFinished UserTest: set name");
    }


    @Test
    public void testSetAge() {
        System.out.println("\nStarting UserTest: set age");

        final int newAge = 20;
        user.setAge(newAge);
        assertSame("The new age should be the same as newAge", newAge, user.getAge());

        System.out.println("\nFinished UserTest: set age");
    }


    @Test
    public void testSetBalance() {
        System.out.println("\nStarting UserTest: set balance");

        final float newBalance = 20;
        user.setBalance(newBalance);
        assertEquals("The new balance should be the same as newBalance", newBalance, user.getBalance(), 0.001);

        System.out.println("\nFinished UserTest: set balance");
    }

    @Test
    public void testGetCurrentBets() {
        Bet bet = new Bet(10, "des", "name");
        ArrayList<Bet> bets;
        System.out.println("\nStarting UserTest: get current bets");

        user.addBet(bet);
        bets = user.getCurrentBets();
        assertTrue("The new bet should be in the list of bets", bets.contains(bet));

        System.out.println("\nFinished UserTest: get current bets");
    }


    @Test
    public void testGetCurrentFriends() {
        User user = new User("name", 20, 20);
        ArrayList<User> users;
        System.out.println("\nStarting UserTest: get current users");

        user.addFriend(user);
        users = user.getFriendsList();
        assertTrue("The new user should be in the list of users", users.contains(user));

        System.out.println("\nFinished UserTest: get current users");
    }

    @Test
    public void testGetName() {
        System.out.println("\nStarting UserTest: get name");

        final String newName = "newName";
        user.setName(newName);
        assertSame("The name should be the same as returned name", newName, user.getName());

        System.out.println("\nFinished UserTest: get name");
    }

    @Test
    public void testGetAge() {
        System.out.println("\nStarting UserTest: get age");

        final int newAge = 20;
        user.setAge(newAge);
        assertSame("The new age should be the same as newAge", newAge, user.getAge());

        System.out.println("\nFinished UserTest: get age");
    }

    @Test
    public void testGetBalance() {
        System.out.println("\nStarting UserTest: get balance");

        final float newBalance = 20;
        user.setBalance(newBalance);
        assertEquals("The new balance should be the same as newBalance", newBalance, user.getBalance(), 0.001);

        System.out.println("\nFinished UserTest: get balance");
    }

}
