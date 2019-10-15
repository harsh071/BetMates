package com.example.betmates.business;

import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.User;
import com.example.betmates.persistence.BetPersistence;
import com.example.betmates.persistence.stubs.BetPersistenceStub;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class AccessBetsTest {

    private AccessBets accessBets;
    private BetPersistence betPersistence = new BetPersistenceStub();
    private ArrayList<Bet> bets = betPersistence.getBetsSequential();

    @Before
    public void setUp() {
        accessBets = new AccessBets(betPersistence, bets);
    }

    @Test
    public void testGetBets() {
        final ArrayList<Bet> returnBets;
        System.out.println("\nStarting AccessBetsTest: Get list of Bets from betPersistence.");

        returnBets = accessBets.getFirstBets();
        assertNotNull("List of Bets should not be null", returnBets);
        assertSame("List of Bets should be the same ArrayList<Bet> object as the object passed through constructor.", bets, returnBets);

        System.out.println("\nFinished AccessBetsTest: Get list of Bets from betPersistence.");
    }


    @Test
    public void testGetClosedBetSequential() {
        final Bet closedBet = new Bet();
        closedBet.setClosed();
        betPersistence.insertBet(closedBet);
        final ArrayList<Bet> returnClosedBet;
        System.out.println("\nStarting AccessBetsTest: Get closed bets from list of bets.");

        returnClosedBet = accessBets.getClosedBetSequential();
        assertNotNull("The returned linked list should not be null", returnClosedBet);
        for (int i = 0; i < returnClosedBet.size(); i++) {
            assertTrue("List of closedBetSequential should only contain closed bet.", returnClosedBet.get(i).getIfClosed());
        }

        System.out.println("\nFinished AccessBetsTest: Get closed bets from list of bets.");

    }

    @Test
    public void testGetOpenedBetSequential() {
        final Bet openedBet = new Bet();
        betPersistence.insertBet(openedBet);
        final ArrayList<Bet> returnOpenedBet;
        System.out.println("\nStarting AccessBetsTest: Get opened bets from list of bets.");

        returnOpenedBet = accessBets.getOpenedBetSequential();
        assertNotNull("the returned LinkedList should not be null", returnOpenedBet);
        for (int i = 0; i < returnOpenedBet.size(); i++) {
            assertFalse("List of openedBetSequential should only contain opened bet.", returnOpenedBet.get(i).getIfClosed());
        }

        System.out.println("\nFinished AccessBetsTest: Get opened bets from list of bets.");
    }

    @Test
    public void testGetUsersBetSequential() {
        final User user = new User("ed", 20, 20);
        final Bet bet = new Bet(10, "descriptiono", "name", user);
        accessBets.insertBet(bet);
        final ArrayList<Bet> returnedSequentialBets;
        System.out.println("\nStarting AccessBetsTest: Get sequential bets from list of bets.");

        returnedSequentialBets = accessBets.getUsersBetSequential(user);
        assertNotNull("The returned LinkedList should not be null", returnedSequentialBets);
        for (int i = 0; i < returnedSequentialBets.size(); i++) {
            assertSame("The user for this bet should be the same as the user I create", user, returnedSequentialBets.get(i).getCreator());
        }

    }

    @Test
    public void testInsertBet() {
        final Bet myBet = new Bet();
        System.out.println("\nStarting AccessBetsTest: Insert Bet.");

        final Bet returnBet = accessBets.insertBet(myBet);
        assertSame("The returned bet object should be the same as the bet object passed", myBet, returnBet);

        System.out.println("\nFinished AccessBetsTest: Insert Bet.");
    }

    @Test
    public void testInsertBetWhenNull() {
        final Bet myBet = null;
        System.out.println("\nStarting AccessBetsTest: Insert Bet when pass null.");

        assertNull("The returned bet should be null, when pass a null object", accessBets.insertBet(myBet));

        System.out.println("\nFinished AccessBetsTest: Insert Bet when pass null.");
    }

    @Test
    public void testUpdateBet() {
        final Bet myBet = new Bet();
        System.out.println("\nStarting AccessBetsTest: Update Bet.");

        assertNull("The returned bet object should be the same as the bet object passed", accessBets.updateBet(myBet));

        System.out.println("\nFinished AccessBetsTest: Update Bet.");
    }

    @Test
    public void testUpdateBetWhenNull() {
        final Bet myBet = null;
        System.out.println("\nStarting AccessBetsTest: Update Bet when pass null.");

        assertNull("The returned bet should be null, when pass a null object", accessBets.updateBet(myBet));

        System.out.println("\nFinished AccessBetsTest: Update Bet when pass null.");
    }

    @Test
    public void testDeleteBet() {
        final Bet bet = new Bet(10, "description", "bet");
        betPersistence.insertBet(bet);
        System.out.println("\nStarting AccessBetsTest: Delete Bet from betPersistence");

        accessBets.deleteBet(bet);
        assertNull("Should return Null since already delete that bet.", accessBets.getBet(bet.getBetName()));

        System.out.println("\nFinished AccessBetsTest: Delete Bet from betPersistence");

    }

}
