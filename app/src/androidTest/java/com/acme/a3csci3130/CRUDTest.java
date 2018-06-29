package com.acme.a3csci3130;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class CRUDTest {
    @Rule
    public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCreate() {
        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.businessNumber)).perform(typeText("123456789"), closeSoftKeyboard());
        onView(withId(R.id.name)).perform(typeText("Bob's Swordfish Emporium"), closeSoftKeyboard());
        onView(withId(R.id.primaryBusiness)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Distributor"))).perform(click());
        onView(withId(R.id.address)).perform(typeText("10 Ocean Lane"), closeSoftKeyboard());
        onView(withId(R.id.province)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("NL"))).perform(click());
        onView(withId(R.id.submitButton)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).
                check(matches(withText("Bob's Swordfish Emporium")));
    }

    @Test
    public void testUpdate(){
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.name)).perform(replaceText("Updated Business Name"), closeSoftKeyboard());
        onView(withId(R.id.updateButton)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).
                check(matches(withText("Updated Business Name")));
    }

    @Test
    public void testDelete() {

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).
                check(matches(withText("Bob's Swordfish Emporium")));


        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.businessNumber)).perform(typeText("000888777"), closeSoftKeyboard());
        onView(withId(R.id.name)).perform(typeText("Helen the Fish Lady"), closeSoftKeyboard());
        onView(withId(R.id.primaryBusiness)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Fisher"))).perform(click());
        onView(withId(R.id.submitButton)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).
                check(matches(withText("Helen the Fish Lady")));


    }
}
