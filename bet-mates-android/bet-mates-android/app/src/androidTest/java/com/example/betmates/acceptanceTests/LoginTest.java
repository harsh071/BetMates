package com.example.betmates.acceptanceTests;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.betmates.R;
import com.example.betmates.presentation.Login;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    @Rule
    public ActivityTestRule<Login> activityRule = new ActivityTestRule<>(Login.class);

    @Test
    public void checkPassword(){
        onView(ViewMatchers.withId(R.id.userNameText)).perform(typeText("Bobby"));
        onView(withId(R.id.passwordText)).perform(typeText("12"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());

//        onView(withText("Invalid username or password")).
//                inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void blankUserName(){
        onView(withId(R.id.userNameText)).perform(typeText(""));
        onView(withId(R.id.passwordText)).perform(typeText("12"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());

//        onView(withText("One Of the above field is empty. ")).
//                inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void loginTest() {
        onView(withId(R.id.userNameText)).perform(typeText("Bobby"));
        onView(withId(R.id.passwordText)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());


        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Profile")).perform(click());

        onView(withId(R.id.UserName)).check(matches(withText("Bobby")));

    }
    @Test
    public void signUpTest() {
        onView(withId(R.id.signUpButton)).perform(click());

        onView(withId(R.id.enterName)).perform(typeText("Example"), closeSoftKeyboard());
        onView(withId(R.id.enterAge)).perform(typeText("19"), closeSoftKeyboard());
        onView(withId(R.id.enterPassword)).perform(typeText("10"), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText("10"), closeSoftKeyboard());

        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.userNameText)).perform(typeText("Example"));
        onView(withId(R.id.passwordText)).perform(typeText("10"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Profile")).perform(click());

        onView(withId(R.id.UserName)).check(matches(withText("Example")));

    }






}
