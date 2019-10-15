package com.example.betmates;

import com.example.betmates.business.AccessBetsTest;
import com.example.betmates.business.AccessModelTest;
import com.example.betmates.business.AccessUsersTest;
import com.example.betmates.business.AutheticateTest;
import com.example.betmates.business.SignUpInputVeryfierTest;
import com.example.betmates.objects.BetTest;
import com.example.betmates.objects.ModelTest;
import com.example.betmates.objects.UserTest;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessBetsTest.class,
        AccessUsersTest.class,
        AccessModelTest.class,
        AutheticateTest.class,
        BetTest.class,
        ModelTest.class,
        UserTest.class,
        SignUpInputVeryfierTest.class
})

public class AllUnitTest {
}
