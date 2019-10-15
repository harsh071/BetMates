package com.example.betmates.acceptanceTests;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.example.betmates.R;
import com.example.betmates.presentation.SignUp;
import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.Espresso.onView;
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

public class SignUpTest {
    @Rule
    public ActivityTestRule<SignUp> mActivtiyRule = new ActivityTestRule<>(SignUp.class);




    @Test
    public void blankNameTest(){


        onView(ViewMatchers.withId(R.id.enterName)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.enterAge)).perform(typeText("19"), closeSoftKeyboard());
        onView(withId(R.id.enterPassword)).perform(typeText("10"), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText("10"), closeSoftKeyboard());

        onView(withId(R.id.button)).perform(click());

        onView(withText("One of the fields is empty.")).
                inRoot(withDecorView(not(is(mActivtiyRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }
    @Test
    public void blankAgeTest(){


        onView(withId(R.id.enterName)).perform(typeText("Example"), closeSoftKeyboard());
        onView(withId(R.id.enterAge)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.enterPassword)).perform(typeText("10"), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText("10"), closeSoftKeyboard());

        onView(withId(R.id.button)).perform(click());


    }
    @Test
    public void blankPasswordTest(){

        onView(withId(R.id.enterName)).perform(typeText("Example"), closeSoftKeyboard());
        onView(withId(R.id.enterAge)).perform(typeText("19"), closeSoftKeyboard());
        onView(withId(R.id.enterPassword)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText("10"), closeSoftKeyboard());

        onView(withId(R.id.button)).perform(click());

        onView(withText("One of the fields is empty.")).
                inRoot(withDecorView(not(is(mActivtiyRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }

    @Test
    public void blankConfirmPasswordTest(){


        onView(withId(R.id.enterName)).perform(typeText("Example"), closeSoftKeyboard());
        onView(withId(R.id.enterAge)).perform(typeText("19"), closeSoftKeyboard());
        onView(withId(R.id.enterPassword)).perform(typeText("10"), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText(""), closeSoftKeyboard());

        onView(withId(R.id.button)).perform(click());

        onView(withText("One of the fields is empty.")).
                inRoot(withDecorView(not(is(mActivtiyRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }




}
