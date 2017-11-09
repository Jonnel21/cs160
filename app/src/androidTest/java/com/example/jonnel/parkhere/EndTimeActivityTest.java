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
public class EndTimeActivityTest {
    @Rule
    public ActivityTestRule<EndTime> mEndTimeActivityTestRule= new ActivityTestRule<EndTime>(EndTime.class);

    @Test
    public void clickEndTimeButton_opensStartDateUi() throws Exception{
        onView(withId(R.id.myButton)).perform(click());

        onView(withId(R.id.datePicker1)).check(matches(isDisplayed()));
    }

}
