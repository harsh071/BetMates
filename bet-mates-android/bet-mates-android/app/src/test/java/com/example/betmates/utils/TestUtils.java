package com.example.betmates.utils;

import com.google.common.io.Files;
import com.example.betmates.application.DBConnectionManager;
import com.example.betmates.application.MainActivity;
import com.example.betmates.application.Services;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class TestUtils {


    private static final File DB_SRC = new File("src/main/assets/db/UsersAndBets.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC, target);
        DBConnectionManager.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }

//    private static File origDB;
//    private static File testDB;
//    private static final String DB_PATH = "src/main/assets/db/UsersAndBets.script";
//    public static DBConnectionManager dbConnectionManager = new DBConnectionManager();
//
//    public static DBConnectionManager setupDatabase() throws IOException
//    {
//        testDB = File.createTempFile("UsersAndBets", ".script");
//        origDB = new File(DB_PATH);
//        copy(origDB, testDB);
//        DBConnectionManager.setDBPathName(testDB.getAbsolutePath().replace(".script", ""));
//        return dbConnectionManager;
//    }
//
//    private static void copy(File source, File destination) throws IOException
//    {
//        try (InputStream in = new FileInputStream(source))
//        {
//            try (OutputStream outputStream = new FileOutputStream(destination))
//            {
//                byte[] buffer = new byte[1024];
//                int lengthRead;
//                // Write the DB to the destination
//                while ((lengthRead = in.read(buffer)) > 0)
//                {
//                    outputStream.write(buffer, 0, lengthRead);
//                }
//            }
//        }
//    }
//
//    public static void clearDatabase()
//    {
//        testDB.delete();
//        Services.clear();
//    }

}
