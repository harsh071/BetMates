package com.example.betmates.objects;


import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BetTest {

    private Bet bet;
    private final String betName = "betName";
    private final double amount = 12;
    private final String betDescription = "description";
    private final User creator = new User("creator", 20, 1000);

    @Before
    public void serUp() {
        bet = new Bet(amount, betDescription, betName, creator);
    }

    @Test
    public void testAddJoiner() {
        User user = new User();
        System.out.println("\nStarting BetTest: Add joiner test.");

        bet.addJoiner(user);
        assertTrue("Joiners should contain the user just assigned.", bet.getJoiners().contains(user));

        System.out.println("\nFinished BetTest: Add joiner test.");
    }

    @Test
    public void testAddJoiners() {
        ArrayList<User> joiners = new ArrayList<User>();
        User user1 = new User("ed1", 20, 10);
        User user2 = new User("ed2", 20, 10);
        User user3 = new User("ed3", 20, 10);
        joiners.add(user1);
        joiners.add(user2);
        joiners.add(user3);
        System.out.println("\nStarting BetTest: Add list of joiners test.");

        bet.addJoiner(joiners);
        assertTrue("Joiners should contain the user1 just assigned.", bet.getJoiners().contains(user1));
        assertTrue("Joiners should contain the user2 just assigned.", bet.getJoiners().contains(user2));
        assertTrue("Joiners should contain the user3 just assigned.", bet.getJoiners().contains(user3));

        System.out.println("\nFinished BetTest: Add list of joiner test.");
    }

    @Test
    public void testRemoveExistJoiner() {
        ArrayList<User> joiners = new ArrayList<User>();
        User user1 = new User("ed1", 20, 10);
        User user2 = new User("ed2", 20, 10);
        User user3 = new User("ed3", 20, 10);
        joiners.add(user1);
        joiners.add(user2);
        joiners.add(user3);
        System.out.println("\nStarting BetTest: Remove a user from joiners.");

        bet.addJoiner(joiners);
        assertTrue("Should return true when removing existing user.", bet.removeJoiner(user1));
        assertFalse("Joiners should not contain the user1 just assigned.", bet.getJoiners().contains(user1));
        assertTrue("Joiners should contain the user2 just assigned.", bet.getJoiners().contains(user2));
        assertTrue("Joiners should contain the user3 just assigned.", bet.getJoiners().contains(user3));

        System.out.println("\nFinished BetTest: Remove a user from joiners.");
    }

    @Test
    public void testRemoveNonExistJoiner() {
        ArrayList<User> joiners = new ArrayList<User>();
        User user1 = new User("ed1", 20, 10);
        User user2 = new User("ed2", 20, 10);
        User user3 = new User("ed3", 20, 10);
        joiners.add(user2);
        joiners.add(user3);
        System.out.println("\nStarting BetTest: Remove a user from joiners.");

        bet.addJoiner(joiners);
        assertFalse("Should return false when removing non existing user.", bet.removeJoiner(user1));
        assertFalse("Joiners should not contain the user1 just assigned.", bet.getJoiners().contains(user1));
        assertTrue("Joiners should contain the user2 just assigned.", bet.getJoiners().contains(user2));
        assertTrue("Joiners should contain the user3 just assigned.", bet.getJoiners().contains(user3));

        System.out.println("\nFinished BetTest: Remove a user from joiners.");
    }

    @Test
    public void testSetClosed() {
        System.out.println("\nStarting BetTest: Set bet to close.");

        bet.setClosed();
        assertTrue("The return should be True, since the bet is closed.", bet.getIfClosed());

        System.out.println("\nFinished BetTest: Get if the bet is closed.");
    }

    @Test
    public void testGetBetName() {
        System.out.println("\nStarting BetTest: Get the name of the bet.");

        assertSame("The bet name should be the same as defined", betName, bet.getBetName());

        System.out.println("\nFinished BetTest: Get the name of the bet.");
    }

    @Test
    public void testGetAmount() {
        System.out.println("\nStarting BetTest: Get the amount of the bet.");

        assertEquals("The amount should be the same as defined.", amount, bet.getAmount(), 0.0001);

        System.out.println("\nFinished BetTest: Get the amount of the bet.");
    }

    @Test
    public void testGetDescription() {
        System.out.println("\nStarting BetTest: Get the description of the bet.");

        assertSame("The description should be the same as defined.", betDescription, bet.getBetDescription());

        System.out.println("\nFinished BetTest: Get the description of the bet.");
    }

    @Test
    public void testGetCreator() {
        System.out.println("\nStarting BetTest: Get the Creator of the bet.");

        assertSame("The Creator should be the same as defined.", creator, bet.getCreator());

        System.out.println("\nFinished BetTest: Get the Creator of the bet.");
    }

    @Test
    public void testGetJoiners() {
        User user1 = new User("joiner1", 20, 1000);
        bet.addJoiner(user1);
        System.out.println("\nStarting BetTest: Get the list of joiner.");

        assertTrue("The return list should contain the user we just assed.", bet.getJoiners().contains(user1));

        System.out.println("\nFinished BetTest: Get the list of joiner.");
    }

    @Test
    public void testGetIfClosedWhenOpened() {
        System.out.println("\nStarting BetTest: Get if the bet is closed.");

        assertFalse("The return should be false, since the bet is not closed.", bet.getIfClosed());

        System.out.println("\nFinished BetTest: Get if the bet is closed.");
    }

    @Test
    public void testGetIfClosedWhenClosed() {
        System.out.println("\nStarting BetTest: Get if the bet is closed.");

        bet.setClosed();
        assertTrue("The return should be True, since the bet is closed.", bet.getIfClosed());

        System.out.println("\nFinished BetTest: Get if the bet is closed.");
    }

}
