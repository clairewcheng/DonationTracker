package com.example.claire.donationtrackerv1;

import android.content.ComponentName;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.claire.donationtrackerv1.controller.SignInActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)

public class SignInUnitTest {
    //created by bthornburgh3
    /** this line is preferred way to hook up to activity */
    @Rule
    public ActivityTestRule<SignInActivity> mSignInActivityRule =
            new ActivityTestRule<>(SignInActivity.class);



    @Test
    public void checkValidPasswordEmailSignIn() {
        //Test correct valid email+password combo entered into sign in screen

        //type email into the email box
        onView(withId(R.id.emailsignin)).perform(typeText("test@gmail.com"),
                closeSoftKeyboard());
        //type password into the password box
        onView(withId(R.id.passwordsignin)).perform(click());
        onView(withId(R.id.passwordsignin)).perform(typeText("testpassword"),
                closeSoftKeyboard());
        //now click on sign in button
        onView(withId(R.id.signinbutton)).perform(click());
        //check result to make sure that we are successfully signed in
        //assertNotNull(onView(withId(R.id.user_type_field)));
        SignInActivity activity = mSignInActivityRule.getActivity();
        onView(withText("Sign In Successful"))
                .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                .check(matches((isDisplayed())));
        Espresso.pressBack();
    }

    @Test
    public void checkInvalidPasswordSignIn() {

            // Test incorrect password being entered in with correct email

            //type email into the email box
            onView(withId(R.id.emailsignin)).perform(typeText("test@gmail.com"),
                    closeSoftKeyboard());
            //type password into the password box
            onView(withId(R.id.passwordsignin)).perform(click());
            onView(withId(R.id.passwordsignin)).perform(typeText("wrongpassword"),
                    closeSoftKeyboard());
            //now click on sign in button
            onView(withId(R.id.signinbutton)).perform(click());
            //check to make sure error was thrown
            SignInActivity activity = mSignInActivityRule.getActivity();
            onView(withText("Please Correct Email and or Password")).inRoot(withDecorView(not(activity.
                    getWindow().getDecorView()))).check(matches((isDisplayed())));

    }

    @Test (timeout = 200000)
    public void checkInvalidEmailSignIn() {
        // Test incorrect password being entered in with correct email

        //type email into the email box
        onView(withId(R.id.emailsignin)).perform(typeText("doesntexist@gmail.com"),
                closeSoftKeyboard());
        //type password into the password box
        onView(withId(R.id.passwordsignin)).perform(doubleClick());
        onView(withId(R.id.passwordsignin)).perform(typeText("testpassword"),
                closeSoftKeyboard());
        //now click on sign in button
        onView(withId(R.id.signinbutton)).perform(click());
        //check to make sure error was thrown
        SignInActivity activity = mSignInActivityRule.getActivity();
        onView(withText("Please Correct Email and or Password")).inRoot(withDecorView(not(activity.
                getWindow().getDecorView()))).check(matches((isDisplayed())));
    }

    @Test
    public void checkSignInWithOutAnEmail() {
        // check to make sure you cannot log in without an email entered

        //type email into the email box
        onView(withId(R.id.emailsignin)).perform(doubleClick());
        onView(withId(R.id.emailsignin)).perform(typeText(" "),
                closeSoftKeyboard());
        //type password into the password box
        onView(withId(R.id.passwordsignin)).perform(click());
        onView(withId(R.id.passwordsignin)).perform(typeText("testpassword"),
                closeSoftKeyboard());
        //now click on sign in button
        onView(withId(R.id.signinbutton)).perform(click());
        //check to make sure error was thrown
        SignInActivity activity = mSignInActivityRule.getActivity();
        onView(withText("Please enter valid email...")).inRoot(withDecorView(not(activity.
                getWindow().getDecorView()))).check(matches((isDisplayed())));
    }

    @Test
    public void checkSignInWithOutAPassword() {
        // check to make sure you cannot log in without an email entered

        //type email into the email box
        onView(withId(R.id.emailsignin)).perform(typeText("test@gmail.com"),
                closeSoftKeyboard());
        //type password into the password box
        onView(withId(R.id.passwordsignin)).perform(doubleClick());
        onView(withId(R.id.passwordsignin)).perform(typeText(" "),
                closeSoftKeyboard());
        //now click on sign in button
        onView(withId(R.id.signinbutton)).perform(click());
        //check to make sure error was thrown
        SignInActivity activity = mSignInActivityRule.getActivity();
        onView(withText("Please enter valid password...")).inRoot(withDecorView(not(activity.
                getWindow().getDecorView()))).check(matches((isDisplayed())));

    }

}
