package com.example.claire.donationtrackerv1;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.claire.donationtrackerv1.controller.AddItemActivity;
import com.example.claire.donationtrackerv1.controller.AppHomeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;


@RunWith(AndroidJUnit4.class)

// NOTE: must delete items that were added to inventory every time you
// want to run these tests, otherwise will fail adding with duplicate IDs

public class AddItemTest {
    //created by chunter41
    /** this line is preferred way to hook up to activity */
    @Rule
    public ActivityTestRule<AddItemActivity> mAddItemActivityRule =
            new ActivityTestRule<>(AddItemActivity.class);


    @Test
    public void checkValidShortDesc() {
        // Test for a valid short description entered into short description box

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 1"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("March 5, 2018"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Add Item Successful"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
        Espresso.pressBack();
    }

    @Test
    public void checkInvalidShortDesc() {
        // Test for an invalid short description entered into short description box

        //type invalid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText(""),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("March 5, 2018"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Please Enter Valid Short Description"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
    }

    @Test
    public void checkValidValue() {
        // Test for a valid value entered into value box

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 3"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("7.52"),
                closeSoftKeyboard());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("March 5, 2018"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Add Item Successful"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
        Espresso.pressBack();
    }

    @Test
    public void checkInvalidValue() {
        // Test for an invalid value entered into value box

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 4"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("hi"),
                closeSoftKeyboard());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("March 5, 2018"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Please Enter Valid Value"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
    }

    @Test
    public void checkChangeCategory() {
        // Test for choosing a new category when adding an item

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 5"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //change the category
        onView(withId(R.id.categorySpinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("March 5, 2018"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Add Item Successful"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
        Espresso.pressBack();
    }

    @Test
    public void checkDefaultCategory() {
        // Test for leaving the default category when adding an item

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 6"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //don't change the category

        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("March 5, 2018"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Add Item Successful"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
        Espresso.pressBack();
    }

    @Test
    public void checkValidLongDesc() {
        // Test for a valid long description entered into full description box

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 7"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("This is a valid full description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("March 5, 2018"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Add Item Successful"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
        Espresso.pressBack();
    }

    @Test
    public void checkInvalidLongDesc() {
        // Test for an invalid long description entered into full description box

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 8"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText(""),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("March 5, 2018"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Please Enter Full Description"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
    }

    @Test
    public void checkChangeLocation() {
        // Test for choosing a new location when adding an item

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 9"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //change the location
        onView(withId(R.id.locationSpinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("March 5, 2018"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Add Item Successful"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
        Espresso.pressBack();
    }

    @Test
    public void checkDefaultLocation() {
        // Test for leaving the default location when adding an item

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 10"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //don't change the location

        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("March 5, 2018"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Add Item Successful"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
        Espresso.pressBack();
    }

    @Test
    public void checkValidTime() {
        // Test for a valid time entered into the time box

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 11"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("2:32pm"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("March 5, 2018"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Add Item Successful"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
        Espresso.pressBack();
    }

    @Test
    public void checkInvalidTime() {
        // Test for an invalid time entered into the time box

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 12"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("invalid time"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("March 5, 2018"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Please Enter Valid Time"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
    }

    @Test
    public void checkValidDate() {
        // Test for a valid date entered into the date box

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 13"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("October 13, 2018"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Add Item Successful"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
        Espresso.pressBack();
    }

    @Test
    public void checkInvalidDate() {
        // Test for an invalid date entered into the date box

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 14"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("Not valid"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Please Enter Valid Date"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
    }

    @Test
    public void checkEnterComment() {
        // Test for entering a comment into the comment box

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 15"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("October 13, 2018"),
                closeSoftKeyboard());
        //type comment into the comment box
        onView(withId(R.id.commentsEntry)).perform(click());
        onView(withId(R.id.commentsEntry)).perform(typeText("This is a comment"),
                closeSoftKeyboard());
        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Add Item Successful"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
        Espresso.pressBack();
    }

    @Test
    public void checkCommentBlank() {
        // Test for leaving the comment box blank

        //type valid short description into the short description box
        onView(withId(R.id.shortDescriptionEntry)).perform(typeText("Add Item Test 16"),
                closeSoftKeyboard());
        //type value into the value box
        onView(withId(R.id.valueEntry)).perform(click());
        onView(withId(R.id.valueEntry)).perform(typeText("1"),
                closeSoftKeyboard());
        //type long description into the long description box
        onView(withId(R.id.fullDescriptionEntry)).perform(click());
        onView(withId(R.id.fullDescriptionEntry)).perform(typeText("Full Description"),
                closeSoftKeyboard());
        //type time into the time box
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffTimeEntry)).perform(typeText("12:00am"),
                closeSoftKeyboard());
        //type date into the date box
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(click());
        onView(withId(R.id.timeOfDropoffDateEntry)).perform(typeText("October 13, 2018"),
                closeSoftKeyboard());
        //don't type comment into the comment box

        //now click on add item button
        onView(withId(R.id.addItemButton)).perform(click());
        //check result to make sure that we have successfully added an item
        AddItemActivity activity = mAddItemActivityRule.getActivity();
        onView(withText("Add Item Successful"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
        Espresso.pressBack();
    }
}
