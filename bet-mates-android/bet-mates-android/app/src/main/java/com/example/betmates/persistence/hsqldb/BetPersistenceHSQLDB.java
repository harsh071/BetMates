package com.example.betmates.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.betmates.application.DBConnectionManager;
import com.example.betmates.business.AccessUsers;
import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.User;
import com.example.betmates.persistence.BetPersistence;

import javax.xml.transform.Result;

public class BetPersistenceHSQLDB implements BetPersistence {

    private DBConnectionManager connectionManager;

    public BetPersistenceHSQLDB(DBConnectionManager input){
        this.connectionManager = input;
    }


    public Bet fromResultSet(final ResultSet rs) throws SQLException {
        final double amount = rs.getDouble("amount");
        final String betDescription = rs.getString("desc");
        final String betName = rs.getString("betName");

        return new Bet(amount, betDescription, betName);
    }

    @Override
    public ArrayList<Bet> getBetsSequential() {
        final ArrayList<Bet> bets = new ArrayList<>();

        try { // Make a connection to the db
            final Connection c = connectionManager.getConnection();

            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM BETS"); // perform a query

            while(rs.next()){ // Keep 'er goin'
                final Bet bet = fromResultSet(rs); // grab the bet
                bets.add(bet);
            }

            rs.close();
            st.close();

            return bets;

        } catch(final SQLException e ){
            e.printStackTrace();
        }

        return bets;
    }

    @Override
    public Bet insertBet(Bet newBet) { //insert the bet to the database and return that bet
        try { //make another connection
            final Connection c = connectionManager.getConnection();
            final PreparedStatement st = c.prepareStatement("INSERT INTO bets (betName, amount, desc, creatorName) VALUES(?, ?, ?, ?)");

            st.setString(1, newBet.getBetName());
            st.setDouble(2, newBet.getAmount());
            st.setString(3, newBet.getBetDescription());
            st.setString(4, newBet.getCreator().getName());

            st.executeUpdate(); // Update the DB

            st.close();

            return newBet;

        } catch(final SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Bet updateBet(Bet newBet) {
        try { // Make a connection to the DB

            final Connection c = connectionManager.getConnection();

            // In the stub we use an index to update, searching for the Bet and then resetting the data at the position.
            // We need to just Update the bets table where the

            final PreparedStatement st = c.prepareStatement("UPDATE bets SET betName = ?," +
                    " amount = ?," +
                    " desc = ?," +
                    " creatorname = ?,"+
                    " WHERE betName = ?");

            st.setString(1, newBet.getBetName());
            st.setDouble(2, newBet.getAmount());
            st.setString(3, newBet.getBetDescription());
            st.setString(4, newBet.getCreator().getName());
            st.setString(5, newBet.getBetName());

            st.executeUpdate();

            st.close();

            return newBet;
        } catch(final SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteBet(Bet currentBet){ //if removed successful then will return true.
        try { // Make a connection to the DB

            final Connection c = connectionManager.getConnection();

            final PreparedStatement st = c.prepareStatement("DELETE FROM bets WHERE betName = ?"); // Since we don't have an id just use the name
            st.setString(1, currentBet.getBetName());

            st.executeUpdate();

            st.close();

            return true;

        } catch(final SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public void setCurrentBet(Bet newBet)
    {
        try {
            final Connection c = connectionManager.getConnection();

            final Statement check = c.createStatement();
            final ResultSet rs = check.executeQuery("SELECT * FROM currentbet"); // perform a query

            if(!rs.next())
            {
                final PreparedStatement st = c.prepareStatement("INSERT INTO currentbet (betName, amount, desc, creatorname) VALUES(?, ?, ?, ?)");
                if(newBet.getBetName()!=null){ st.setString(1, newBet.getBetName());}else{st.setString(1, "");}
                st.setDouble(2, newBet.getAmount());
                if(newBet.getBetDescription()!=null) {st.setString(3, newBet.getBetDescription());}else{st.setString(3, "");}
                st.setString(4, newBet.getCreator().getName());
                st.executeUpdate();
                st.close();
            }
            else
            {
                final PreparedStatement st = c.prepareStatement("UPDATE currentbet SET betName = ?," +
                        " amount = ?," +
                        " desc = ?," +
                        " creatorname = ?"
                );
                st.setString(1, newBet.getBetName());
                st.setDouble(2, newBet.getAmount());
                st.setString(3, newBet.getBetDescription());
                st.setString(4, newBet.getCreator().getName());
                st.executeUpdate();
                st.close();
            }

            if(newBet.getJoiners()!=null)
            {
                final PreparedStatement remove = c.prepareStatement("DELETE FROM currentbetfriendlist");
                remove.executeUpdate();
                remove.close();
                for(int i=0;i<newBet.getJoiners().size();i++)
                {
                    String joinerName = newBet.getJoiners().get(i).getName();
                    final PreparedStatement joiner =  c.prepareStatement("INSERT INTO currentbetfriendlist (joinerName) VALUES(?)");
                    joiner.setString(1,joinerName);
                    joiner.executeUpdate();
                    joiner.close();
                }
            }

        }
        catch(final SQLException e) {
            e.printStackTrace();
        }

    }

    public Bet getCurrentBet()
    {
        AccessUsers userAccessor = new AccessUsers();
        Bet bet = new Bet(userAccessor.getFirstUser());
        try { // Make a connection to the db
            final Connection c = connectionManager.getConnection();

            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM currentbet"); // perform a query

            if(rs.next())
            {
                bet.setAmount(rs.getDouble(1));
                bet.setBetDescription(rs.getString(2));
                bet.setName(rs.getString(3));
            }

            final ResultSet js = st.executeQuery("SELECT * FROM currentbetfriendlist");

            while(js.next())
            {
                User joiner = userAccessor.getUser(js.getString(1));
                bet.addJoiner(joiner);
            }

            rs.close();
            st.close();


        } catch(final SQLException e ){
            e.printStackTrace();
        }

        return bet;
    }

    public void deleteCurrentBet()
    {
        try { // Make a connection to the DB

            final Connection c = connectionManager.getConnection();

            final PreparedStatement st = c.prepareStatement("DELETE FROM currentbet");

            st.executeUpdate();

            st.close();


        } catch(final SQLException e){
            e.printStackTrace();
        }

    }

}
