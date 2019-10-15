package com.example.betmates.persistence.hsqldb;

import com.example.betmates.application.DBConnectionManager;
import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.User;
import com.example.betmates.persistence.UserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserPersistenceHSQLDB implements UserPersistence {

    private DBConnectionManager connectionManager;

    public UserPersistenceHSQLDB(final DBConnectionManager input){
        this.connectionManager = input;
    }


    private User fromResultSet(final ResultSet rs) throws SQLException {
        final String name = rs.getString("name");
        final int age = rs.getInt("age");
        final float balance = rs.getFloat("balance");
        final String pass = rs.getString("userpass"); // Can we grab the password?
        return new User(name, age, balance, pass); // Need to include the password!
    }

    @Override
    public ArrayList<User> getAllUsers(){
        final ArrayList<User> users = new ArrayList<>();
        try { // Make a connection to the db
            final Connection c = connectionManager.getConnection();
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM USERS"); // perform a query

            while(rs.next()){ // Keep 'er goin'
                final User user= fromResultSet(rs); // grab the bet
                users.add(user);
            }

            rs.close();
            st.close();

            return users;

        }
        catch(final SQLException e ){
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User getFirstUser(){
        try {
            final Connection c = connectionManager.getConnection();
            final ArrayList<User> users = new ArrayList<>();

            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM CURRENTUSER");

            while(rs.next()) { // Keep 'er goin'
                final User user = fromResultSet(rs); // grab the bet
                users.add(user);
            }

            rs.close();
            st.close();

            return users.get(0); // Just use the first one!
        } catch(final SQLException e ){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getRandomUser(){
        try {

            final Connection c = connectionManager.getConnection();

            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM users ORDER BY RAND() LIMIT 1");

            final User user = fromResultSet(rs);

            rs.close();
            st.close();

            return user;
        } catch(final SQLException e ){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<String> getUserFriends(final User currUser){
        final ArrayList<String> friendNames = new ArrayList<>();

        try { // Make a connection to the db
            final Connection c = connectionManager.getConnection();

            final Statement st = c.createStatement();

            final String sql = "SELECT * FROM USERFRIENDS WHERE username = '" + currUser.getName()+"'";
            final ResultSet rs = st.executeQuery(sql); // Need to access the users friend names

            while(rs.next()){ // Keep 'er goin'
                final String friendName = rs.getString("friendname");
                if(friendName!=null)
                {
                    friendNames.add(friendName); // Add the name to the name list
                }
            }
            rs.close();
            st.close();

            return friendNames;

        } catch(final SQLException e ){
            e.printStackTrace();
        }
        return friendNames;
    }

    @Override
    public User addUser(final User newUser){
        try { //make another connection
            final Connection c = connectionManager.getConnection();
            final PreparedStatement st = c.prepareStatement("INSERT INTO USERS (NAME, AGE, BALANCE, USERPASS) VALUES(?, ?, ?, ?)");

            st.setString(1, newUser.getName());
            st.setInt(2, newUser.getAge());
            st.setFloat(3, newUser.getBalance());
            st.setString(4, newUser.getPassword());

            st.executeUpdate(); // Update the DB
            st.close();

            return newUser;

        } catch(final SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteUser(final User oldUser){
        try {
            final Connection c = connectionManager.getConnection();

            final PreparedStatement st = c.prepareStatement("DELETE FROM users WHERE name = ?"); // Since we don't have an id just use the name
            st.setString(1, oldUser.getName());

            st.executeUpdate();
            st.close();

        } catch(final SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void setUserLoggedIn(User user){
        try{
            final Connection c = connectionManager.getConnection();

            // Rearrange the found User to the first row in the table
            final PreparedStatement st = c.prepareStatement("UPDATE CURRENTUSER SET NAME = ?, AGE = ?, BALANCE = ?, USERPASS = ?");
            st.setString(1, user.getName());
            st.setInt(2, user.getAge());
            st.setFloat(3, user.getBalance());
            st.setString(4, user.getPassword());

            st.executeUpdate();
            st.close();

        } catch(final SQLException e){
            e.printStackTrace();
        }
    }
}
