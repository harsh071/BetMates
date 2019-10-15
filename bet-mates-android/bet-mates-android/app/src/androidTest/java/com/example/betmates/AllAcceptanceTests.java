package com.example.betmates;

import com.example.betmates.acceptanceTests.CreateBetTest;
import com.example.betmates.acceptanceTests.LoginTest;
import com.example.betmates.acceptanceTests.SignUpTest;
import com.example.betmates.acceptanceTests.UserProfileTest;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateBetTest.class,
        LoginTest.class,
        SignUpTest.class,
        UserProfileTest.class
})

public class AllAcceptanceTests {

}



