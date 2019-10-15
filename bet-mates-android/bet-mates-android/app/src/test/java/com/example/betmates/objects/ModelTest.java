package com.example.betmates.objects;

import android.view.View;

import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.Model;
import com.example.betmates.objects.content.User;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;
import static junit.framework.TestCase.assertEquals;

public class ModelTest {

    private Model model;
    private final int id = -1;
    private final int likes = 2;
    private final int propic = 2;
    private final int postpic = 2;
    private final String name = "a name";
    private final String time = "a time";
    private final String status = "a status";
    private Bet bet = new Bet();

    @Before
    public void setUp() {
        model = new Model(id, likes, propic, postpic, name, time, status, bet);
    }

    @Test
    public void testGetID() {
        System.out.println("\nStarting ModelTest: Get the id from model.");

        assertEquals("The id should be equal the id assigned", id, model.getId());

        System.out.println("\nFinished ModelTest: Get the id from model.");
    }

    @Test
    public void testSetID() {
        System.out.println("\nStarting ModelTest: Set the id from model.");

        final int newID = -10;
        model.setId(newID);
        assertEquals("The id should be equal the id assigned", newID, model.getId());

        System.out.println("\nFinished ModelTest: Set the id from model.");
    }

    @Test
    public void testGetLike() {
        System.out.println("\nStarting ModelTest: Get the like from model.");

        assertEquals("The likes should be equal the like assigned", likes, model.getLikes());

        System.out.println("\nFinished ModelTest: Get the like from model.");
    }

    @Test
    public void testSetLikes() {
        System.out.println("\nStarting ModelTest: Set the like from model.");

        int newLikes = likes + 2;
        model.setLikes(newLikes);
        assertEquals("The likes should be equal the id assigned", newLikes, model.getLikes());

        System.out.println("\nFinished ModelTest: Set the like from model.");
    }

    @Test
    public void testGetBet() {
        System.out.println("\nStarting ModelTest: get the bet from model.");

        assertSame("The bet should be equal the bet assigned", bet, model.getBet());

        System.out.println("\nFinished ModelTest: get the bet from model.");
    }

    @Test
    public void testGetPropic() {
        System.out.println("\nStarting ModelTest: get the propic from model.");

        assertSame("The propic should be equal the bet assigned", propic, model.getPropic());

        System.out.println("\nFinished ModelTest: get the propic from model.");
    }

    @Test
    public void testSetPropic() {
        System.out.println("\nStarting ModelTest: set the propic from model.");

        final int newPropic = propic + 1;
        model.setPropic(newPropic);
        assertSame("The propic should be equal the bet assigned", newPropic, model.getPropic());

        System.out.println("\nFinished ModelTest: set the propic from model.");
    }

    @Test
    public void testGetPostpic() {
        System.out.println("\nStarting ModelTest: get the postpic from model.");

        assertSame("The postpic should be equal the bet assigned", postpic, model.getPostpic());

        System.out.println("\nFinished ModelTest: get the postpic from model.");
    }

    @Test
    public void testSetPostpic() {
        System.out.println("\nStarting ModelTest: set the postpic from model.");

        final int newPostpic = postpic + 1;
        model.setPostpic(newPostpic);
        assertSame("The postPic should be equal the bet assigned", newPostpic, model.getPostpic());

        System.out.println("\nFinished ModelTest: set the postpic from model.");
    }

    @Test
    public void testGetName() {
        System.out.println("\nStarting ModelTest: get the name from model.");

        assertSame("The name should be equal the bet assigned", name, model.getName());

        System.out.println("\nFinished ModelTest: get the name from model.");
    }

    @Test
    public void testSetName() {
        System.out.println("\nStarting ModelTest: set the name from model.");

        final String newName = name + "1";
        model.setName(newName);
        assertSame("The name should be equal the bet assigned", newName, model.getName());

        System.out.println("\nFinished ModelTest: set the name from model.");
    }

    @Test
    public void testGetTime() {
        System.out.println("\nStarting ModelTest: get the time from model.");

        assertSame("The time should be equal the bet assigned", time, model.getTime());

        System.out.println("\nFinished ModelTest: get the time from model.");
    }

    @Test
    public void testSetTime() {
        System.out.println("\nStarting ModelTest: set the time from model.");

        final String newTime = time + "1";
        model.setTime(newTime);
        assertSame("The time should be equal the bet assigned", newTime, model.getTime());

        System.out.println("\nFinished ModelTest: set the time from model.");
    }

    @Test
    public void testGetStatus() {
        System.out.println("\nStarting ModelTest: get the status from model.");

        assertSame("The status should be equal the bet assigned", status, model.getStatus());

        System.out.println("\nFinished ModelTest: get the status from model.");
    }

    @Test
    public void testSetStatus() {
        System.out.println("\nStarting ModelTest: set the status from model.");

        final String newStatus = status + "1";
        model.setStatus(newStatus);
        assertSame("The status should be equal the bet assigned", newStatus, model.getStatus());

        System.out.println("\nFinished ModelTest: set the status from model.");
    }

}
