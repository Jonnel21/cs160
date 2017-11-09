package com.example.jonnel.parkhere;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAssertion;
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
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
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
public class LoginTest {
    @Rule
    public ActivityTestRule<LoginActivity> createActivityTestRule = new ActivityTestRule<>(LoginActivity.class);


    @Test
    public void testEmailPasswordLogin(){
        onView(withId(R.id.password)).perform(typeText("123456789"));
        closeSoftKeyboard();
        onView(withId(R.id.email)).perform(typeText("rambo@yourmom.com"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).check(matches(withText("123456789")));
    }

    @Test
    public void testLoginButton(){
        onView(withId(R.id.email_sign_in_button)).perform(click());
    }

    @Test
    public void testLoginSoftKeyboards(){
        onView(withId(R.id.email)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(click());
        closeSoftKeyboard();

    }

}