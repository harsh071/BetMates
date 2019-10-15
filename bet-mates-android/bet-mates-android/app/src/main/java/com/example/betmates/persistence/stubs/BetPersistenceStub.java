package com.example.betmates.persistence.stubs;

import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.User;
import com.example.betmates.persistence.BetPersistence;

import java.util.ArrayList;

public class BetPersistenceStub implements BetPersistence {
    private ArrayList<Bet> bets;
    private Bet currentBet;

    public BetPersistenceStub() {
        this.bets =  new ArrayList<>();
        Bet bet1 = new Bet(1000, "Can you drink a can of pop at once.","Pop Bet",new User("Harsh",19,2000));
        Bet bet2 = new Bet(1000, "Will Jets Win the game tonight? ","GameWinner",new User("Ayush",19,2000));
        Bet bet3 = new Bet(1000, "Can you use the terminal better than me?","Terminal Wizzard",new User("Justin",19,2000));
        Bet bet4 = new Bet(1000, "Make 100 kills in fortnite by tomorrow.","Fortnite",new User("Ray",19,2000));
        Bet bet5 = new Bet(1000, "Can you solve this equation? ","Solve the Equation",new User("Eddy",19,2000));
        Bet bet6 = new Bet(1000, "Can you beat me in a game in FIFA? ","FIFA",new User("Kaitlyn",19,2000));
        Bet bet7 = new Bet(1000, "Can you run 100m in 14 seconds? ","Sprinting",new User("Rob",19,2000));
        Bet bet8 = new Bet(1000, "Can you make a sandwich in under 5 minutes? ","How fast can you eat?",new User("John",19,2000));
        bets.add(bet1);
        bets.add(bet2);
        bets.add(bet3);
        bets.add(bet4);
        bets.add(bet5);
        bets.add(bet6);
        bets.add(bet7);
        bets.add(bet8);

        //set of closed bets.

        Bet bet1closed = new Bet(10, "I bet you can't fit 10 marshmallows in your mouth at once!", "Matt");
        Bet bet2closed = new Bet(20, "I bet you can't make 10 free throws in a row!", "Morgan");
        Bet bet3closed = new Bet(30, "I bet you won't get a better mark than me in stats", "Gabby");
        Bet bet4closed = new Bet(40, "I bet I have a harder slap shot than you", "Kris");
        Bet bet5closed = new Bet(50, "I bet I have more pairs of shoes than you", "Salina");
        Bet bet6closed = new Bet(60, "I bet the Raptors are going to win the NBA finals", "Nory");

        bet1closed.setClosed();
        bet2closed.setClosed();
        bet3closed.setClosed();
        bet4closed.setClosed();
        bet5closed.setClosed();
        bet6closed.setClosed();

        bets.add(bet1closed);
        bets.add(bet2closed);
        bets.add(bet3closed);
        bets.add(bet4closed);
        bets.add(bet5closed);
        bets.add(bet6closed);


    }

    public ArrayList<Bet> getBetsSequential() {
        return bets;
    }


    public Bet insertBet(Bet currentBet) {
        bets.add(currentBet);
        return currentBet;
    }


    public Bet updateBet(Bet currentBet) {
        int index;

        index = bets.indexOf(currentBet);
        if(index>=0) {
            bets.set(index,currentBet);
        }
        return currentBet;
    }

    public boolean deleteBet(Bet currentBet) {
        int index;
        index = bets.indexOf(currentBet);
        if(index>=0) {
            bets.remove(index);
            return true;
        }
        return false;
    }

    public void setCurrentBet(Bet newBet)
    {
        currentBet = newBet;
    }

    public Bet getCurrentBet()
    {
        if(currentBet!=null)
        {
            return currentBet;
        }
        else
        {
            return null;
        }
    }
    public void deleteCurrentBet()
    {
        currentBet = null;
    }
}
