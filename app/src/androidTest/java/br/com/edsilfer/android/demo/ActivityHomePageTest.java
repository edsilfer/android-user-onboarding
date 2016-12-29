package br.com.edsilfer.android.demo;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ActivityHomePageTest {

    @Rule
    public ActivityTestRule<ActivityHomePage> mActivityTestRule = new ActivityTestRule<>(ActivityHomePage.class);

    @Test
    public void activityHomePageTest() throws InterruptedException {
        onView(allOf(withId(R.id.pager))).perform(swipeLeft());
        Thread.sleep(800);
        onView(allOf(withId(R.id.pager))).perform(swipeLeft());
        Thread.sleep(800);
        onView(allOf(withId(R.id.pager))).perform(swipeLeft());
        Thread.sleep(800);
        onView(allOf(withId(R.id.pager))).perform(swipeRight());
        Thread.sleep(800);
        onView(allOf(withId(R.id.pager))).perform(swipeRight());
        Thread.sleep(800);
        onView(allOf(withId(R.id.pager))).perform(swipeRight());
        Thread.sleep(800);
    }
}
