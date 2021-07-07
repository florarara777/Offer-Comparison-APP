package edu.gatech.seclass.jobcompare6300;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class test02_CurrentJobTest {
    @Rule
    public ActivityTestRule<CurrentJob> currentJobActivityTestRule = new ActivityTestRule<CurrentJob>(CurrentJob.class);
    private CurrentJob cjActivity = null;

    // launch the activity
    @Test
    public void test_CurrentJobLaunch(){
        onView(withId(R.id.current_job_tv)).check(matches(withText("Current Job")));
    }

    @Test
    public void test_CurrentJob_CheckInvalidInput_2(){

        ActivityScenario activityscenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnCurrentJob)).perform(click());
        onView(withId(R.id.current_job_activity_id)).check(matches(isDisplayed()));

        onView(withId(R.id.cj_title_text)).perform(clearText(), replaceText("123"));
        onView(withId(R.id.cj_company_text)).perform(clearText(), replaceText("123"));
        onView(withId(R.id.cj_city_text)).perform(clearText(), replaceText("123"));
        onView(withId(R.id.cj_state_text)).perform(clearText(), replaceText("123"));
        onView(withId(R.id.cj_living_cost_index_text)).perform(clearText(), replaceText("abc"));
        onView(withId(R.id.cj_commute_time_text)).perform(clearText(), replaceText("abc"));
        onView(withId(R.id.cj_yearly_salary_text)).perform(clearText(), replaceText("abc"));
        onView(withId(R.id.cj_yearly_bonus_text)).perform(clearText(), replaceText("abc"));
        onView(withId(R.id.cj_retirement_benefits_text)).perform(clearText(), replaceText("abc"));
        onView(withId(R.id.cj_leave_time_text)).perform(clearText(), replaceText("abc"));

        onView(withId(R.id.cj_save_btn)).perform(click());

        onView(withId(R.id.cj_title_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.cj_company_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.cj_city_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.cj_state_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.cj_living_cost_index_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.cj_commute_time_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.cj_yearly_salary_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.cj_yearly_bonus_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.cj_retirement_benefits_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.cj_leave_time_text)).check(matches(hasErrorText("input a valid one")));
    }

//    @Test
//    public void test_CurrentJob_UpdateCurrentJob(){
//        cjActivity = currentJobActivityTestRule.getActivity();
//
//        onView(withId(R.id.cj_title_text)).perform(clearText(), replaceText("Junior SDE"));
//        onView(withId(R.id.cj_company_text)).perform(clearText(), replaceText("Microsoft"));
//        onView(withId(R.id.cj_city_text)).perform(clearText(), replaceText("Mountain View"));
//        onView(withId(R.id.cj_state_text)).perform(clearText(), replaceText("CA"));
//        onView(withId(R.id.cj_commute_time_text)).perform(clearText(), replaceText("2"));
//        onView(withId(R.id.cj_living_cost_index_text)).perform(clearText(), replaceText("140"));
//        onView(withId(R.id.cj_yearly_salary_text)).perform(clearText(), replaceText("81200.5"));
//        onView(withId(R.id.cj_yearly_bonus_text)).perform(clearText(), replaceText("13452"));
//        onView(withId(R.id.cj_retirement_benefits_text)).perform(clearText(), replaceText("25"));
//        onView(withId(R.id.cj_leave_time_text)).perform(clearText(), replaceText("7"));
//
//        onView(withId(R.id.cj_save_btn)).perform(click());
//        onView(withText("Successfully Updated!")).inRoot(withDecorView(not(is(cjActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
//
//        // go to main menu and go back the current job
//        onView(withId(R.id.cj2mmbutton)).perform(click());
//        onView(withId(R.id.btnCurrentJob)).perform(click());
//
//        onView(withId(R.id.cj_title_text)).check(matches(withText("Junior SDE")));
//        onView(withId(R.id.cj_company_text)).check(matches(withText("Microsoft")));
//        onView(withId(R.id.cj_city_text)).check(matches(withText("Mountain View")));
//        onView(withId(R.id.cj_state_text)).check(matches(withText("CA")));
//        onView(withId(R.id.cj_commute_time_text)).check(matches(withText("2")));
//        onView(withId(R.id.cj_living_cost_index_text)).check(matches(withText("140")));
//        onView(withId(R.id.cj_yearly_salary_text)).check(matches(withText("81200.5")));
//        onView(withId(R.id.cj_yearly_bonus_text)).check(matches(withText("13452")));
//        onView(withId(R.id.cj_retirement_benefits_text)).check(matches(withText("25")));
//        onView(withId(R.id.cj_leave_time_text)).check(matches(withText("7")));
//    }

}