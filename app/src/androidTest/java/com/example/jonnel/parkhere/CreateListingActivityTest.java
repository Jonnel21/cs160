package com.example.jonnel.parkhere;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by Sinjin on 11/8/2017.
 */
@RunWith(JUnit4.class)
public class CreateListingActivityTest {
    @Rule
    public ActivityTestRule<createListing_Activity> mCreateListingActivityTestRule= new ActivityTestRule<createListing_Activity>(createListing_Activity.class);

    @Test
    public void clickToCreateListingButton_opensMainUserUI() throws Exception{
        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.searchButton)).check(matches(isDisplayed()));
    }

}
