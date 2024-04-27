package com.example.quizingapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.quizingapp.LoginActivity;
import com.example.quizingapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule

            LoginActivityTestRule    = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testValidLogin() {
        // Type valid email and password
        Espresso.onView(ViewMatchers.withId(R.id.editTextEmail))
                .perform(ViewActions.typeText("example@example.com"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.editTextPassword))
                .perform(ViewActions.typeText("Password123"), ViewActions.closeSoftKeyboard());

        // Click login button
        Espresso.onView(ViewMatchers.withId(R.id.buttonLogin)).perform(ViewActions.click());

        // Check if MainActivity is launched
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testInvalidEmail() {
        // Type invalid email and valid password
        Espresso.onView(ViewMatchers.withId(R.id.editTextEmail))
                .perform(ViewActions.typeText("invalidemail"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.editTextPassword))
                .perform(ViewActions.typeText("Password123"), ViewActions.closeSoftKeyboard());

        // Click login button
        Espresso.onView(ViewMatchers.withId(R.id.buttonLogin)).perform(ViewActions.click());

        // Check if error message is displayed for email
        Espresso.onView(ViewMatchers.withText("Invalid email format"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testInvalidPassword() {
        // Type valid email and invalid password
        Espresso.onView(ViewMatchers.withId(R.id.editTextEmail))
                .perform(ViewActions.typeText("example@example.com"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.editTextPassword))
                .perform(ViewActions.typeText("pass"), ViewActions.closeSoftKeyboard());

        // Click login button
        Espresso.onView(ViewMatchers.withId(R.id.buttonLogin)).perform(ViewActions.click());

        // Check if error message is displayed for password
        Espresso.onView(ViewMatchers.withText("Invalid password format"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
