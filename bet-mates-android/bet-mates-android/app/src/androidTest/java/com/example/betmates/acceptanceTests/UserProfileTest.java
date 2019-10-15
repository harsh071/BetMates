package com.example.betmates.acceptanceTests;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.betmates.R;
import com.example.betmates.presentation.EditProfile;
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
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserProfileTest {
    @Rule
    public ActivityTestRule<Login> activityRule = new ActivityTestRule<>(Login.class);



    @Test
    public void changeNameTest() {
        onView(ViewMatchers.withId(R.id.userNameText)).perform(typeText("Bobby"));
        onView(withId(R.id.passwordText)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());


        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Profile")).perform(click());

        onView(withId(R.id.editProfileText)).perform(click());
        onView(withId(R.id.changeName)).perform(typeText("Example"));
        onView(withText("Finish")).perform(click());

        onView(withId(R.id.UserName)).check(matches(withText("Example")));


    }

    @Test
    public void blankName() {
        onView(withId(R.id.userNameText)).perform(typeText("Bobby"));
        onView(withId(R.id.passwordText)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());


        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Profile")).perform(click());

        onView(withId(R.id.editProfileText)).perform(click());
        onView(withId(R.id.changeName)).perform(typeText(""));
        onView(withText("Finish")).perform(click());

    }
}
