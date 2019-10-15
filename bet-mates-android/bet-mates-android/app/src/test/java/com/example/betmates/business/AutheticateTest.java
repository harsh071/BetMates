package com.example.betmates.business;

import com.example.betmates.objects.content.User;
import com.example.betmates.persistence.UserPersistence;
import com.example.betmates.persistence.stubs.UserPersistenceStub;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AutheticateTest {

    private Authenticate authenticate;

    private UserPersistence userPersistence = new UserPersistenceStub();
    private ArrayList<User> users = userPersistence.getAllUsers();
//    private User user = userPersistence.getFirstUser();
    private final String mypassword = "myPass";
    private final User user = new User("Eddy", 20, 10, mypassword);
    private AccessUsers accessUsers = new AccessUsers(userPersistence, users, user);

    @Before
    public void setUp() {
        authenticate = new Authenticate(accessUsers, user);
    }

    @Test
    public void testAuthenticatorWhenInvalidInput() {
        final User returnedUser;
        System.out.println("\nStarting AutheticateTest: Authenticate user by incorrect userName and password.");

        returnedUser = authenticate.authenticator(user.getName(), "notMypass");
        assertNull("ReturnedUser should not be null", returnedUser);

        System.out.println("\nFinished AutheticateTest: Authenticate user by incorrect userName and password.");
    }

    @Test
    public void testAuthenticatorForCreateCorrectUser() {
        final User returnedUser;
        final String myName = "123";
        final String myAge = "20";
        System.out.println("\nStarting AutheticateTest: Authenticate user by create correct new userName and password.");

        returnedUser = authenticate.authenticator(myName, mypassword, myAge, mypassword);
        assertNotNull("ReturnedUser should not be null", returnedUser);
        assertEquals("The returned user name should be the same as input,", myName, returnedUser.getName());
        assertEquals("The returned user password should be the same as input", mypassword, returnedUser.getPassword());
        assertEquals("THe returned user age should be the same as input", Integer.parseInt(myAge), returnedUser.getAge());

        System.out.println("\nFinished AutheticateTest: Authenticate user by creating correct new userName and password.");
    }

    @Test
    public void testAuthenticatorForCreateIncorrectUser() {
        final User returnedUser;
        System.out.println("\nStarting AutheticateTest: Authenticate user by create incorrect new userName and password.");

        returnedUser = authenticate.authenticator(user.getName(), mypassword, user.getAge()+"", "MyotherPass");
        assertNull("ReturnedUser should not be null", returnedUser);

        System.out.println("\nFinished AutheticateTest: Authenticate user by creating incorrect new userName and password.");
    }

    @Test
    public void testPrivateVerifyCorrectPasswords() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        final String myPass = "Mypass";
        System.out.println("\nStarting AutheticateTest: Verify passwords when passing correct password.");

        Method method = Authenticate.class.getDeclaredMethod("verifyPasswords", String.class, String.class);
        method.setAccessible(true);
        boolean output = (boolean)method.invoke(authenticate, myPass, myPass);

        assertTrue("The output should be true when pass same password", output);

        System.out.println("\nFinished AutheticateTest: Verify passwords when passing correct password.");
    }

    @Test
    public void testPrivateVerifyIncorrectPasswords() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        final String myPass = "Mypass";
        final String myOtherPass = "jello";
        System.out.println("\nStarting AutheticateTest: Verify passwords when passing incorrect password.");

        Method method = Authenticate.class.getDeclaredMethod("verifyPasswords", String.class, String.class);
        method.setAccessible(true);
        boolean output = (boolean)method.invoke(authenticate, myPass, myOtherPass);

        assertFalse("The output should be false when pass different password", output);

        System.out.println("\nFinished AutheticateTest: Verify passwords when passing incorrect password.");
    }

    @Test
    public void testPrivateVerifyCorrectAge() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        final String myPass = "20";
        System.out.println("\nStarting AutheticateTest: Verify age when passing correct age.");

        Method method = Authenticate.class.getDeclaredMethod("verifyAge", String.class);
        method.setAccessible(true);
        boolean output = (boolean)method.invoke(authenticate, myPass);

        assertTrue("The output should be true when pass valid age", output);

        System.out.println("\nFinished AutheticateTest: Verify age when passing correct age.");
    }

    @Test
    public void testPrivateVerifyIncorrectAge() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        final String myPass = "1";
        System.out.println("\nStarting AutheticateTest: Verify age when passing incorrect age.");

        Method method = Authenticate.class.getDeclaredMethod("verifyAge", String.class);
        method.setAccessible(true);
        boolean output = (boolean)method.invoke(authenticate, myPass);

        assertFalse("The output should be false when pass invalid age", output);

        System.out.println("\nFinished AutheticateTest: Verify age when passing incorrect age.");
    }

    @Test
    public void testPrivateVerifyUserExistsWhenNotExists() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        System.out.println("\nStarting AutheticateTest: Verify user existence when passing not existing user.");

        Method method = Authenticate.class.getDeclaredMethod("userExists", String.class);
        method.setAccessible(true);
        boolean output = (boolean)method.invoke(authenticate, user.getName()+"123");

        assertFalse("The output should be False when passing not existing user name", output);

        System.out.println("\nFinished AutheticateTest: Verify user existence when passing not existing user.");
    }

    @Test
    public void testPrivateCheckPasswordWhenPassCorrectPassword() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        System.out.println("\nStarting AutheticateTest: Check existing user password with correct input password.");

        Method method = Authenticate.class.getDeclaredMethod("checkPassword", User.class, String.class);
        method.setAccessible(true);
        boolean output = (boolean)method.invoke(authenticate, user, user.getPassword());

        assertTrue("The output should be true when passing correct password", output);

        System.out.println("\nFinished AutheticateTest: Check existing user password with correct input password.");
    }

    @Test
    public void testPrivateCheckPasswordWhenPassIncorrectPassword() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        System.out.println("\nStarting AutheticateTest: Check existing user password with incorrect input password.");

        Method method = Authenticate.class.getDeclaredMethod("checkPassword", User.class, String.class);
        method.setAccessible(true);
        boolean output = (boolean)method.invoke(authenticate, user, user.getPassword()+"12");

        assertFalse("The output should be False when passing incorrect password", output);

        System.out.println("\nFinished AutheticateTest: Check existing user inpassword with correct input password.");
    }

}
