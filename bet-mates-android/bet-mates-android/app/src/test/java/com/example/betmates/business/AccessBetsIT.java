package com.example.betmates.business;

import java.io.File;

import com.example.betmates.application.DBConnectionManager;
import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.User;
import com.example.betmates.persistence.BetPersistence;
import com.example.betmates.persistence.hsqldb.BetPersistenceHSQLDB;
import com.example.betmates.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccessBetsIT {
    private AccessBets accessBets;
    private File tempDB;
    private DBConnectionManager dbManager;
    private User user = new User("name", 20, 20, "pass");

    @Before
    public void setUp() throws IOException {
        dbManager = new DBConnectionManager();
        this.tempDB = TestUtils.copyDB();
        String filePath = this.tempDB.getAbsolutePath().replace(".script", "");
        DBConnectionManager.setDBPathName(filePath);
        final BetPersistence betPersistence = new BetPersistenceHSQLDB(dbManager);
        this.accessBets = new AccessBets(betPersistence, null);
        this.accessBets = new AccessBets(betPersistence);
    }

    @Test
    public void testListBet() {
        final ArrayList<Bet> bet;

        bet = accessBets.getBetsSequential();
        assertNotNull("First sequential bet should not be null", bet);
        assertTrue("EquationMaster".equals(bet.get(0).getBetName()));

        System.out.println("Finish test list bet");
    }

    @Test
    public void testGetFirst() {
        final ArrayList<Bet> bet;

        bet = accessBets.getFirstBets();
        assertNotNull("First sequential bet should not be null", bet);
        assertTrue("EquationMaster".equals(bet.get(0).getBetName()));

        System.out.println("Finish test get first");
    }

    @Test
    public void testGetFirstWhenBetIsNull() throws IOException {

        dbManager = new DBConnectionManager();
        File tempDB = TestUtils.copyDB();
        String filePath = tempDB.getAbsolutePath().replace(".script", "");
        DBConnectionManager.setDBPathName(filePath);
        final BetPersistence betPersistence = new BetPersistenceHSQLDB(dbManager);
        AccessBets accessBets = new AccessBets(betPersistence, null);
        assertNotNull(accessBets.getFirstBets());
    }

    @Test
    public void testGetBets() {
        final ArrayList<Bet> returnBets;

        returnBets = accessBets.getFirstBets();
        assertNotNull("List of Bets should not be null", returnBets);

        System.out.println("\nFinished Get list of Bets from betPersistence.");
    }

    @Test
    public void testGetClosed() {
        final ArrayList<Bet> returnBet;

        returnBet = accessBets.getClosedBetSequential();
        assertTrue("List of Bets should be empty", returnBet.isEmpty());

        System.out.println("\nFinished Get closed list of Bets from betPersistence.");
    }

    @Test
    public void testGetOpened() {
        final ArrayList<Bet> returnBet;

        returnBet = accessBets.getOpenedBetSequential();
        assertFalse("List of Bets should not be empty", returnBet.isEmpty());
        for (int i = 0; i < returnBet.size(); i++) {
            assertFalse("List of Bets should be not closed", returnBet.get(i).getIfClosed());
        }

        System.out.println("\nFinished Get opened list of Bets from betPersistence.");
    }

    @Test
    public void testGetUserSequential() {
        final ArrayList<Bet> returnBet;

        returnBet = accessBets.getOpenedBetSequential();
        assertFalse("List of Bets should not be empty", returnBet.isEmpty());


        System.out.println("\nFinished Get user list of Bets from betPersistence.");
    }

    @Test
    public void testGetUsersBetSequential() {
        User user = new User("Bobby", 20,12);

        assertNotNull(accessBets.getUsersBetSequential(user));
    }

    @Test
    public void testInsertBet() {
        final Bet bet = new Bet(10, "description", "bet121er24", user);
        System.out.println("\nStarting AccessBetsTest: Insert Bet.");

        final Bet returnBet = accessBets.insertBet(bet);
        assertNotNull("The returned bet object should be the same as the bet object passed", returnBet);

        System.out.println("\nFinished AccessBetsTest: Insert Bet.");
    }

    @Test
    public void testUpdateBet() {
        final Bet bet = new Bet(10, "description", "bet121er24", user);
        System.out.println("\nStarting AccessBetsTest: Update Bet.");

        final Bet returnBet = accessBets.updateBet(bet);
        assertNull("The returned bet object should be the same as the bet object passed", returnBet);

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
        Bet returnBet = accessBets.insertBet(bet);
        assertSame("The returned bet object should be the same as the bet object passed", bet, returnBet);
        accessBets.deleteBet(bet);
        assertNull("Should return Null since already delete that bet.", accessBets.getBet(bet.getBetName()));

        System.out.println("\nFinished AccessBetsTest: Delete Bet from betPersistence");
    }

    @Test
    public void testSetCurrentBet() {
        Bet bet = new Bet(20, "des", "fneuia", new User("name", 20, 20));

        accessBets.setCurrentBet(bet);

        assertNotNull(accessBets.getCurrentBet());
    }

    @Test
    public void testdeleteCurrentBet() {
        accessBets.deleteCurrentBet();

        assertNotNull(accessBets.getCurrentBet());
    }

    @After
    public void tearDown() {
        //reset DB
        this.tempDB.delete();
    }


}
