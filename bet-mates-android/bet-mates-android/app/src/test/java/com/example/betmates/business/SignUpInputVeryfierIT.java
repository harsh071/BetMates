package com.example.betmates.business;

import com.example.betmates.objects.content.User;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SignUpInputVeryfierIT {

    private String userName;
    private String password;
    private String age;
    private String confirmPassword;
    private SignUpInputVeryfier signUpInputVeryfier;

    @Before
    public void setUp() {
        this.userName = "userName";
        this.password = "password";
        this.age = "Age";
        this.confirmPassword = password;
    }

    @Test
    public void testIsInoutEmpty() {
        this.signUpInputVeryfier = new SignUpInputVeryfier(userName, password, age, confirmPassword);
        assertFalse(signUpInputVeryfier.isInputEmpty());
        this.signUpInputVeryfier = new SignUpInputVeryfier("", password, age, confirmPassword);
        assertTrue(signUpInputVeryfier.isInputEmpty());
    }
}
