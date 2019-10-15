package com.example.betmates;

import com.example.betmates.business.AccessBetsIT;
import com.example.betmates.business.AccessModelIT;
import com.example.betmates.business.AccessUsers;
import com.example.betmates.business.AccessUsersIT;
import com.example.betmates.business.Authenticate;
import com.example.betmates.business.AuthenticateIT;
import com.example.betmates.business.SignUpInputVeryfierIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessBetsIT.class,
        AccessModelIT.class,
        AccessUsersIT.class,
        AuthenticateIT.class,
        SignUpInputVeryfierIT.class
})
public class AllIntegrationTests {
}
