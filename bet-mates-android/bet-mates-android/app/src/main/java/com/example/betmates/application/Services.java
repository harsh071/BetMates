package com.example.betmates.application;

import com.example.betmates.persistence.BetPersistence;
import com.example.betmates.persistence.ModelPersistence;
import com.example.betmates.persistence.UserPersistence;

import com.example.betmates.persistence.hsqldb.UserPersistenceHSQLDB;
import com.example.betmates.persistence.hsqldb.BetPersistenceHSQLDB;
import com.example.betmates.persistence.hsqldb.ModelPersistenceHSQLDB;
import com.example.betmates.persistence.stubs.BetPersistenceStub;
import com.example.betmates.persistence.stubs.ModelPersistenceStub;

public class Services {

    private static DBConnectionManager connectionManager =  new DBConnectionManager();
    private static UserPersistence userPersistence = null ;
    private static BetPersistence betPersistence = null;
    private static ModelPersistence modelPersistence = null;

    public static synchronized UserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistenceHSQLDB(connectionManager); // Database call!
        }
        return userPersistence;
    }

    public static synchronized BetPersistence getBetPersistence() {
        if (betPersistence == null) {
           //betPersistence = new BetPersistenceStub();
            betPersistence = new BetPersistenceHSQLDB(connectionManager); // For some reason fails when accessing bets (can't acquire lock)
        }
        return betPersistence;
    }

    public static synchronized ModelPersistence getModelPersistence()
    {
        if (modelPersistence == null) {
            //modelPersistence = new ModelPersistenceStub();
            modelPersistence = new ModelPersistenceHSQLDB(connectionManager); // For some reason fails when accessing bets (can't acquire lock)
        }
        return modelPersistence;
    }

    public static synchronized void clear()
    {
        userPersistence = null;
        betPersistence = null;
        modelPersistence = null;
    }



}
