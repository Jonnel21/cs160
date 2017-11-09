package com.example.jonnel.parkhere;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RegisterTest {
    @Rule
    public ActivityTestRule<RegisterActivity> createActivityTestRule = new ActivityTestRule<>(RegisterActivity.class);
    String name = "jonnel";
    String pass = "jonnel";
    String email = "jonnel@jonnel";
    String last = "jonnel";

    @Test
    public void testRegisterFields(){
        onView(withId(R.id.password)).perform(typeText("jonnel"));
        closeSoftKeyboard();
        onView(withId(R.id.email)).perform(typeText("jonnel@jonnel"));
        closeSoftKeyboard();
        onView(withId(R.id.firstName)).perform(typeText("jonnel"));
        closeSoftKeyboard();
        onView(withId(R.id.lastName)).perform(typeText("jonnel"));
        closeSoftKeyboard();

        onView(withId(R.id.password)).check(matches(withText(pass)));
        onView(withId(R.id.email)).check(matches(withText(email)));
        onView(withId(R.id.firstName)).check(matches(withText(name)));
        onView(withId(R.id.lastName)).check(matches(withText(last)));
    }

    @Test
    public void testRegisterButton(){
        onView(withId(R.id.email_sign_in_button)).perform(click());

    }

    @Test
    public void testSoftKeyboardsForRegister(){
        onView(withId(R.id.password)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.email)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.firstName)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.lastName)).perform(click());
        closeSoftKeyboard();
    }



}