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
public class CalendarActivityTest {
    @Rule
    public ActivityTestRule<CalendarActivity> mCalendarActivityTestRule= new ActivityTestRule<CalendarActivity>(CalendarActivity.class);

    @Test
    public void clickStartDateButton_opensEndDateUi() throws Exception{
        onView(withId(R.id.toTime2Button)).perform(click());

        onView(withId(R.id.datePicker1)).check(matches(isDisplayed()));
    }

}
