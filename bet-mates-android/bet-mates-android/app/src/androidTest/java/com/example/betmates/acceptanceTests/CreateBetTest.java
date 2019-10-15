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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class CreateBetTest {
        @Rule
        public ActivityTestRule<Login> activityRule = new ActivityTestRule<>(Login.class);
    @Test
    public void createBetTest() {
        onView(ViewMatchers.withId(R.id.userNameText)).perform(typeText("Bobby"));
        onView(withId(R.id.passwordText)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.createBetButton)).perform(click());
        onView(withId(R.id.createBet_name)).perform(typeText("Example"), closeSoftKeyboard());
        onView(withId(R.id.createBet_description)).perform(typeText("Example description"), closeSoftKeyboard());
        onView(withId(R.id.createBet_amount)).perform(typeText("10"), closeSoftKeyboard());
        onView(withId(R.id.buttonBetCreate)).perform(click());

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Current Bets")).perform(click());


    }

}
