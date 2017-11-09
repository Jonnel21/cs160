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
public class UserActivityTest {
    @Rule
    public ActivityTestRule<UserActivity> mTimeActivityTestRule= new ActivityTestRule<UserActivity>(UserActivity.class);

    @Test
    public void clickSearchButton_opensSearchUi() throws Exception{
        onView(withId(R.id.searchButton)).perform(click());

        onView(withId(R.id.Listview)).check(matches(isDisplayed()));
    }

    @Test
    public void clickDeleteButton_opensDeleteUi() throws Exception{
        onView(withId(R.id.deleteButton)).perform(click());

        onView(withId(R.id.deleteList)).check(matches(isDisplayed()));
    }

    @Test
    public void clickCreateButton_opensCreateUi() throws Exception{
        onView(withId(R.id.createButton)).perform(click());

        onView(withId(R.id.timePicker1)).check(matches(isDisplayed()));
    }
    /*
    @Test
    public void signOutButton_opensLoginUI() throws Exception{
        onView(withId(R.id.signoutButton)).perform(click());

        onView(withId(R.id.login_progress)).check(matches(isDisplayed()));
    }
*/


}
