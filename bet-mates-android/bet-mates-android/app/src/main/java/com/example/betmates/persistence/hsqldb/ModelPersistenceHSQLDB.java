package com.example.betmates.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.betmates.application.DBConnectionManager;
import com.example.betmates.business.AccessBets;
import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.Model;
import com.example.betmates.persistence.BetPersistence;
import com.example.betmates.persistence.ModelPersistence;

public class ModelPersistenceHSQLDB implements ModelPersistence {

    private DBConnectionManager connectionManager;
    private AccessBets betAccessor;

    public ModelPersistenceHSQLDB(DBConnectionManager input){

        this.connectionManager = input;
        betAccessor = new AccessBets();
    }


    private Model fromResultSet(final ResultSet rs) throws SQLException {
        final int id  = rs.getInt("ID");
        final int likes = rs.getInt("LIKES");
        final int propic = rs.getInt("PROPIC");
        final int postpic = rs.getInt("POSTPIC");
        final String name = rs.getString("MODELUSER");
        final String time = rs.getString("MODELTIME");
        final String status = rs.getString("STATUS");
        final String betname = rs.getString("BETNAME");

        // We need to make another query to get the rest of the bet information!
        final Statement st = connectionManager.getConnection().createStatement();
       // final ResultSet newRs = st.executeQuery("SELECT * FROM BETS WHERE BETNAME = " + betname);
        //BetPersistenceHSQLDB newConn = new BetPersistenceHSQLDB(connectionManager);
        Bet connBet = betAccessor.getBet(betname);

        return new Model(id, likes, propic, postpic, name, time, status,connBet);
    }

    @Override
    public ArrayList<Model> getModelSequential(){
        final ArrayList<Model> models = new ArrayList<>();

        try { // Make a connection to the db
            final Connection c = connectionManager.getConnection();

            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM MODELS"); // perform a query

            while(rs.next()){ // Keep 'er goin'
                final Model model = fromResultSet(rs); // grab the bet
                models.add(model);
            }

            rs.close();
            st.close();

            return models;

        } catch(final SQLException e ){
            e.printStackTrace();
        }

        return models;
    }

    @Override
    public Model insertModel(Model newBet){
        try{
            final Connection c = connectionManager.getConnection();

            final PreparedStatement st = c.prepareStatement("INSERT INTO MODELS (ID, LIKES, PROPIC, POSTPIC, MODELUSER, MODELTIME, STATUS, BETNAME) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            st.setInt(1, newBet.getId());
            st.setInt(2, newBet.getLikes());
            st.setInt(3, newBet.getPropic());
            st.setInt(4, newBet.getPostpic());
            st.setString(5, newBet.getName());
            st.setString(6, newBet.getTime());
            st.setString(7, newBet.getStatus());
            st.setString(8, newBet.getBet().getBetName()); // Can we change this to use the object?

            st.executeUpdate(); // Update the DB
            st.close();

            return newBet;
        } catch(final SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Model updateModel(Model currentModel){
        try{
            final Connection c = connectionManager.getConnection();

            final PreparedStatement st = c.prepareStatement("UPDATE MODELS SET ID = ?, LIKES = ?, PROPIC = ?, POSTPIC = ?, MODELUSER = ?, MODELTIME = ?, STATUS = ?, BETNAME = ? WHERE ID = ?");
            st.setInt(1, currentModel.getId());
            st.setInt(2, currentModel.getLikes());
            st.setInt(3, currentModel.getPropic());
            st.setInt(4, currentModel.getPostpic());
            st.setString(5, currentModel.getName());
            st.setString(6, currentModel.getStatus());
            st.setString(7, currentModel.getBet().getBetName()); // Can we change this to use the object?

            st.setInt(8, currentModel.getId());

            st.executeUpdate();
            st.close();

            return currentModel;
        } catch(final SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteModel(Model currentBet){
        try {
            final Connection c = connectionManager.getConnection();

            final PreparedStatement st = c.prepareStatement("DELETE FROM MODELS WHERE ID = ?");
            st.setInt(1, currentBet.getId());

            return true;
        } catch(final SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public int getId()
    {
        final ArrayList<Model> models = new ArrayList<>();

        try { // Make a connection to the db
            final Connection c = connectionManager.getConnection();

            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM MODELS"); // perform a query

            while(rs.next()){ // Keep 'er goin'
                final Model model = fromResultSet(rs); // grab the bet
                models.add(model);
            }

            rs.close();
            st.close();

            return models.get(models.size()-1).getId()+1;

        } catch(final SQLException e ){
            e.printStackTrace();
        }

        return 0;
    }
}
