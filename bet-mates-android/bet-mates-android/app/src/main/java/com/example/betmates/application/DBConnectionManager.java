package com.example.betmates.application;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    private Connection thisConnection;
    private static String dbName = "UsersAndBets";

    public DBConnectionManager()
    {
        thisConnection = null;
    }

    public static void setDBPathName(final String name) {
        try{
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (InstantiationException e){
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }
        dbName = name;
    }

    public void getConnectToDB() throws SQLException
    {
        thisConnection = DriverManager.getConnection("jdbc:hsqldb:file:" + dbName + ";shutdown=true", "SA", "");
    }


    public Connection getConnection() throws SQLException
    {
        if(thisConnection!=null)
        {
            return thisConnection;
        }
        else
        {
            getConnectToDB();
            return thisConnection;
        }
    }

    public static String getDBPathName(){ return dbName; }
}
