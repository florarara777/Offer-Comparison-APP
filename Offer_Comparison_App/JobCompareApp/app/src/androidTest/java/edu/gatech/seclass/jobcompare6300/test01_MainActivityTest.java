package edu.gatech.seclass.jobcompare6300;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class test01_MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MainActivity mActivity = null;

    public ActivityTestRule<CurrentJob> currentJobActivityTestRule = new ActivityTestRule<CurrentJob>(CurrentJob.class);
    private CurrentJob cjActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(CurrentJob.class.getName(),null, false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    //Testing visibility of buttons with text
    @Test
    public void test_isTitleTextDisplayed() {
        ActivityScenario activityscenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnCurrentJob))
                .check(matches(withText("GO TO CURRENT JOB")));

        onView(withId(R.id.btnJobOffer))
                .check(matches(withText("ADD JOB OFFER")));

        onView(withId(R.id.btnJobComparison))
                .check(matches(withText("JOB COMPARISON")));

        onView(withId(R.id.btnWeightAdjust))
                .check(matches(withText("WEIGHT ADJUST")));
    }

    // Testing visibility of buttons
    @Test
    public void test_visibility_buttons_titles() {
        ActivityScenario activityscenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnCurrentJob))
                .check(matches(isDisplayed()));

        onView(withId(R.id.btnJobOffer))
                .check(matches(isDisplayed()));

        onView(withId(R.id.btnJobComparison))
                .check(matches(isDisplayed()));

        onView(withId(R.id.btnWeightAdjust))
                .check(matches(isDisplayed()));
    }

    //Testing navigating to the current job details activity screen when "GO TO CURRENT JOB" button is clicked on the main menu
    @Test
    public void test_navigate_toCurrentJob_screen() {
        ActivityScenario activityscenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnCurrentJob)).perform(click());
        onView(withId(R.id.current_job_activity_id)).check(matches(isDisplayed()));
    }

    //Testing navigating to the job offer details activity screen when "ADD JOB OFFER" button is clicked on the main menu
    @Test
    public void test_navigate_toJobOffer_screen() {
        ActivityScenario activityscenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnJobOffer)).perform(click());
        onView(withId(R.id.job_offer_activity_id)).check(matches(isDisplayed()));
    }

    //Testing navigating to the job comparison details activity screen when "JOB COMPARISON" button is clicked on the main menu
//    @Test
//    public void test_navigate_toJobComparison_screen() {
//        ActivityScenario activityscenario = ActivityScenario.launch(MainActivity.class);
//        onView(withId(R.id.btnJobComparison)).perform(click());
//        onView(withId(R.id.job_comparison_activity_id)).check(matches(isDisplayed()));
//    }

    //Testing navigating to the weight adjust details activity screen when "WEIGHT ADJUST" button is clicked on the main menu
    @Test
    public void test_navigate_toWeightAdjust_screen() {
        ActivityScenario activityscenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnWeightAdjust)).perform(click());
        onView(withId(R.id.weight_adjust_activity_id)).check(matches(isDisplayed()));
    }

    //Testing navigating to the current job details activity screen when "GO TO CURRENT JOB" button is clicked on the main menu and
    // then going back to the main menu screen when "MAIN MENU" button is pressed on Current job details activity screen
    @Test
    public void test_backPress_fromCurrentJob_toMainMenu() {
        ActivityScenario activityscenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnCurrentJob)).perform(click());
        onView(withId(R.id.current_job_activity_id)).check(matches(isDisplayed()));
        onView(withId((R.id.cj2mmbutton))).perform(click()); // method 1: clicking on "MAIN MENU" button on current job activity screen
//        pressBack(); //method 2: clicking on back button the phone
        onView(withId(R.id.main_menu_activity_id)).check(matches(isDisplayed()));
    }

    //Testing navigating to the Job offer details activity screen when "ADD JOB OFFER" button is clicked on the main menu and
    // then going back to the main menu screen when "MAIN MENU" button is pressed on job offer details activity screen
    @Test
    public void test_backPress_fromJobOffer_toMainMenu() {
        ActivityScenario activityscenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnJobOffer)).perform(click());
        onView(withId(R.id.job_offer_activity_id)).check(matches(isDisplayed()));
        onView(withId((R.id.jo2mmbutton))).perform(click()); // method 1: clicking on "MAIN MENU" button on current job activity screen
//        pressBack(); //method 2: clicking on back button the phone
        onView(withId(R.id.main_menu_activity_id)).check(matches(isDisplayed()));
    }

    //Testing navigating to the Job comparison details activity screen when "JOB COMPARISON" button is clicked on the main menu and
    // then going back to the main menu screen when "MAIN MENU" button is pressed on job comparison details activity screen
//    @Test
//    public void test_backPress_fromJobComparison_toMainMenu() {
//        ActivityScenario activityscenario = ActivityScenario.launch(MainActivity.class);
//        onView(withId(R.id.btnJobComparison)).perform(click());
//        onView(withId(R.id.job_comparison_activity_id)).check(matches(isDisplayed()));
//        onView(withId((R.id.jc2mmbutton))).perform(click()); // method 1: clicking on "MAIN MENU" button on current job activity screen
////        pressBack(); //method 2: clicking on back button the phone
//        onView(withId(R.id.main_menu_activity_id)).check(matches(isDisplayed()));
//    }

    //Testing navigating to the Weight adjust details activity screen when "WEIGHT ADJUST" button is clicked on the main menu and
    // then going back to the main menu screen when "MAIN MENU" button is pressed on Weight adjust details activity screen
    @Test
    public void test_backPress_fromWeightAdjust_toMainMenu() {
        ActivityScenario activityscenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnWeightAdjust)).perform(click());
        onView(withId(R.id.weight_adjust_activity_id)).check(matches(isDisplayed()));
        onView(withId((R.id.wa2mmbutton))).perform(click()); // method 1: clicking on "MAIN MENU" button on current job activity screen
//        pressBack(); //method 2: clicking on back button the phone
        onView(withId(R.id.main_menu_activity_id)).check(matches(isDisplayed()));
    }

//    @Test
//    public void testLaunchOfCurrentJobOnButtonClick() {
//        assertNotNull(mActivity.findViewById(R.id.btnCurrentJob));
//
//        onView(withId(R.id.btnCurrentJob)).perform(click());
//        Activity current_job_activity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
//        assertNotNull(current_job_activity);
//        current_job_activity.finish();
//    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}