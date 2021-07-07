package edu.gatech.seclass.jobcompare6300;

import androidx.test.rule.ActivityTestRule;
//import androidx.test.espresso.contrib.RecyclerViewActions;
import org.junit.Rule;
import org.junit.Test;

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
import static edu.gatech.seclass.jobcompare6300.TestUtils.withRecyclerView;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class test03_JobOfferTest {

    @Rule
    public ActivityTestRule<JobOffer> JobOfferActivityTestRule = new ActivityTestRule<JobOffer>(JobOffer.class);
    private JobOffer joActivity = null;


    // launch the activity
    @Test
    public void test_JobOfferLaunch(){
        onView(withId(R.id.job_offer_tv)).check(matches(withText("Job Offer")));
    }

    @Test
    public void test_JobOffer_CheckInvalidInput_1(){
        onView(withId(R.id.jo_save_btn)).perform(click());

        onView(withId(R.id.jo_title_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_company_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_city_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_state_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_living_cost_index_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_commute_time_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_yearly_salary_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_yearly_bonus_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_retirement_benefits_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_leave_time_text)).check(matches(hasErrorText("input a valid one")));
    }

    @Test
    public void test_JobOffer_CheckInvalidInput_2(){

        onView(withId(R.id.jo_title_text)).perform(clearText(), replaceText("123"));
        onView(withId(R.id.jo_company_text)).perform(clearText(), replaceText("123"));
        onView(withId(R.id.jo_city_text)).perform(clearText(), replaceText("123"));
        onView(withId(R.id.jo_state_text)).perform(clearText(), replaceText("123"));
        onView(withId(R.id.jo_living_cost_index_text)).perform(clearText(), replaceText("abc"));
        onView(withId(R.id.jo_commute_time_text)).perform(clearText(), replaceText("abc"));
        onView(withId(R.id.jo_yearly_salary_text)).perform(clearText(), replaceText("abc"));
        onView(withId(R.id.jo_yearly_bonus_text)).perform(clearText(), replaceText("abc"));
        onView(withId(R.id.jo_retirement_benefits_text)).perform(clearText(), replaceText("abc"));
        onView(withId(R.id.jo_leave_time_text)).perform(clearText(), replaceText("abc"));

        onView(withId(R.id.jo_save_btn)).perform(click());

        onView(withId(R.id.jo_title_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_company_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_city_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_state_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_living_cost_index_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_commute_time_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_yearly_salary_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_yearly_bonus_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_retirement_benefits_text)).check(matches(hasErrorText("input a valid one")));
        onView(withId(R.id.jo_leave_time_text)).check(matches(hasErrorText("input a valid one")));
    }

    @Test
    public void test_JobOffer_AddJobOffer(){
        joActivity = JobOfferActivityTestRule.getActivity();

        onView(withId(R.id.jo_title_text)).perform(clearText(), replaceText("Test Engineer"));
        onView(withId(R.id.jo_company_text)).perform(clearText(), replaceText("Zillow"));
        onView(withId(R.id.jo_city_text)).perform(clearText(), replaceText("Atlanta"));
        onView(withId(R.id.jo_state_text)).perform(clearText(), replaceText("GA"));
        onView(withId(R.id.jo_commute_time_text)).perform(clearText(), replaceText("2.5"));
        onView(withId(R.id.jo_living_cost_index_text)).perform(clearText(), replaceText("95"));
        onView(withId(R.id.jo_yearly_salary_text)).perform(clearText(), replaceText("50000"));
        onView(withId(R.id.jo_yearly_bonus_text)).perform(clearText(), replaceText("1000.0"));
        onView(withId(R.id.jo_retirement_benefits_text)).perform(clearText(), replaceText("20"));
        onView(withId(R.id.jo_leave_time_text)).perform(clearText(), replaceText("20"));

        onView(withId(R.id.jo_save_btn)).perform(click());
        onView(withText("Added Successfully")).inRoot(withDecorView(not(is(joActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void test_JobOffer_AddAnotherJobOffer(){
        joActivity = JobOfferActivityTestRule.getActivity();

        onView(withId(R.id.jo_add_another_btn)).perform(click());

        onView(withId(R.id.jo_title_text)).perform(clearText(), replaceText("SDE"));
        onView(withId(R.id.jo_company_text)).perform(clearText(), replaceText("Amazon"));
        onView(withId(R.id.jo_city_text)).perform(clearText(), replaceText("Seattle"));
        onView(withId(R.id.jo_state_text)).perform(clearText(), replaceText("WA"));
        onView(withId(R.id.jo_commute_time_text)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.jo_living_cost_index_text)).perform(clearText(), replaceText("122"));
        onView(withId(R.id.jo_yearly_salary_text)).perform(clearText(), replaceText("82070.7"));
        onView(withId(R.id.jo_yearly_bonus_text)).perform(clearText(), replaceText("2000"));
        onView(withId(R.id.jo_retirement_benefits_text)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.jo_leave_time_text)).perform(clearText(), replaceText("5"));

        onView(withId(R.id.jo_save_btn)).perform(click());
        onView(withText("Added Successfully")).inRoot(withDecorView(not(is(joActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

//    @Test
//    public void test_JobOffer_VerifyAddedJobOffer(){
//        joActivity = JobOfferActivityTestRule.getActivity();
//
//        // go to job list
//        onView(withId(R.id.jo2mmbutton)).perform(click());
//        onView(withId(R.id.btnJobComparison)).perform(click());
//
//        // check the added job offers
//        onView(withId(R.id.jobListRecyclerView)).perform(RecyclerViewActions.scrollToPosition(0));
//        onView(withRecyclerView(R.id.jobListRecyclerView).atPositionOnView(0, R.id.job_title_txt)).check(matches(withText("SDE")));
//        onView(withRecyclerView(R.id.jobListRecyclerView).atPositionOnView(0, R.id.job_company_txt)).check(matches(withText("Amazon")));
//        onView(withRecyclerView(R.id.jobListRecyclerView).atPositionOnView(0, R.id.job_is_current_txt)).check(matches(withText("0")));
//
//        // check the added job offers
//        onView(withId(R.id.jobListRecyclerView)).perform(RecyclerViewActions.scrollToPosition(2));
//        onView(withRecyclerView(R.id.jobListRecyclerView).atPositionOnView(1, R.id.job_title_txt)).check(matches(withText("Test Engineer")));
//        onView(withRecyclerView(R.id.jobListRecyclerView).atPositionOnView(1, R.id.job_company_txt)).check(matches(withText("Zillow")));
//        onView(withRecyclerView(R.id.jobListRecyclerView).atPositionOnView(1, R.id.job_is_current_txt)).check(matches(withText("0")));
//    }

//    @Test
//    public void test_JobOffer_CompareWithCurrentJob(){
//        joActivity = JobOfferActivityTestRule.getActivity();
//
//        onView(withId(R.id.jo_title_text)).perform(clearText(), replaceText("Integration Engineer"));
//        onView(withId(R.id.jo_company_text)).perform(clearText(), replaceText("Black Rock"));
//        onView(withId(R.id.jo_city_text)).perform(clearText(), replaceText("Seattle"));
//        onView(withId(R.id.jo_state_text)).perform(clearText(), replaceText("WA"));
//        onView(withId(R.id.jo_commute_time_text)).perform(clearText(), replaceText("4"));
//        onView(withId(R.id.jo_living_cost_index_text)).perform(clearText(), replaceText("122"));
//        onView(withId(R.id.jo_yearly_salary_text)).perform(clearText(), replaceText("120000"));
//        onView(withId(R.id.jo_yearly_bonus_text)).perform(clearText(), replaceText("30000"));
//        onView(withId(R.id.jo_retirement_benefits_text)).perform(clearText(), replaceText("15"));
//        onView(withId(R.id.jo_leave_time_text)).perform(clearText(), replaceText("10"));
//
//        onView(withId(R.id.jo_save_btn)).perform(click());
//        onView(withText("Added Successfully")).inRoot(withDecorView(not(is(joActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
//
//
//        // compared with current job
//        onView(withId(R.id.jo_compare_cj_btn)).perform(click());
//
//        onView(withId(R.id.occ_id_1_view)).check(matches(hasErrorText("1")));
//        onView(withId(R.id.occ_title_1_view)).check(matches(hasErrorText("Junior SDE")));
//        onView(withId(R.id.occ_company_1_view)).check(matches(hasErrorText("Microsoft")));
//
//        onView(withId(R.id.occ_id_2_view)).check(matches(hasErrorText("4")));
//        onView(withId(R.id.occ_title_2_view)).check(matches(hasErrorText("Test Engineer")));
//        onView(withId(R.id.occ_company_2_view)).check(matches(hasErrorText("Zillow")));
//
//        // back to job offer
//        onView(withId(R.id.back2jo_btn)).perform(click());
//        onView(withId(R.id.job_offer_tv)).check(matches(withText("Job Offer")));
//
//    }




}