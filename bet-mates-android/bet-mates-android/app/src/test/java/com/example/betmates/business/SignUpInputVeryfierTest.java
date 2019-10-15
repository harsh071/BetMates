package com.example.betmates.business;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SignUpInputVeryfierTest {

    private SignUpInputVeryfier signUpInputVeryfier;
    private String userName;
    private String passWord;
    private String age;
    private String confirmPassword;

    @Before
    public void setUp() {
        userName = "userName";
        passWord = "passWord";
        age = "age";
        confirmPassword = "confirm";
    }

    @Test
    public void testIsInputEmptyWhenNoEmpty() {
        signUpInputVeryfier = new SignUpInputVeryfier(userName, passWord, age, confirmPassword);

        System.out.println("\nStarting SignUpInputVeryfierTest: Check is input empty when pass no null value.");
        assertFalse("This should return True, when all of the pass non null parameters", signUpInputVeryfier.isInputEmpty());

        System.out.println("\nFinished SignUpInputVeryfierTest: Check is input empty when pass no null value.");
    }

    @Test
    public void testIsInputEmptyWhenNameIsEmpty() {
        signUpInputVeryfier = new SignUpInputVeryfier("", passWord, age, confirmPassword);

        System.out.println("\nStarting SignUpInputVeryfierTest: Check is input empty when name is empty.");
        assertTrue("This should return True, when all of the pass non null parameters", signUpInputVeryfier.isInputEmpty());

        System.out.println("\nFinished SignUpInputVeryfierTest: Check is input empty when name is Null.");
    }

    @Test
    public void testIsInputEmptyWhenPassWordIsEmpty() {
        signUpInputVeryfier = new SignUpInputVeryfier(userName, "", age, confirmPassword);

        System.out.println("\nStarting SignUpInputVeryfierTest: Check is input empty when password is empty.");
        assertTrue("This should return True, when all of the pass non null parameters", signUpInputVeryfier.isInputEmpty());

        System.out.println("\nFinished SignUpInputVeryfierTest: Check is input empty when password is Null.");
    }

    @Test
    public void testIsInputEmptyWhenAgeIsEmpty() {
        signUpInputVeryfier = new SignUpInputVeryfier(userName, passWord, "", confirmPassword);

        System.out.println("\nStarting SignUpInputVeryfierTest: Check is input empty when age is empty.");
        assertTrue("This should return True, when all of the pass non null parameters", signUpInputVeryfier.isInputEmpty());

        System.out.println("\nFinished SignUpInputVeryfierTest: Check is input empty when age is Null.");
    }

    @Test
    public void testIsInputEmptyWhenconfirmIsEmpty() {
        signUpInputVeryfier = new SignUpInputVeryfier(userName, passWord, age, "");

        System.out.println("\nStarting SignUpInputVeryfierTest: Check is input empty when confirm is empty.");
        assertTrue("This should return True, when all of the pass non null parameters", signUpInputVeryfier.isInputEmpty());

        System.out.println("\nFinished SignUpInputVeryfierTest: Check is input empty when confirm is Null.");
    }
}
