package com.example.quizingapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.quizingapp.FoodActivity;
import com.example.quizingapp.MainActivity;
import com.example.quizingapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Intent;

@RunWith(AndroidJUnit4.class)
public class FoodActivityTest {

    @Rule
    public IntentsTestRule
            <FoodActivity> foodActivityRule = new IntentsTestRule<>(FoodActivity.class);


    @Test
    public void testDisplayQuestion() {
        // Start the activity
        FoodActivity activity = foodActivityRule.getActivity();
        // Ensure that the initial question is displayed correctly
        String initialQuestion = "What is the national dish of Tunisia, typically made from steamed semolina served with a stew of meat and vegetables?";
        Espresso.onView(ViewMatchers.withId(R.id.textViewQuestion))
                .check(ViewAssertions.matches(ViewMatchers.withText(initialQuestion)));

        // Ensure that the options for the initial question are displayed correctly
        String[] initialOptions = new String[]{"Shakshuka", "Brik", "Couscous", "Harissa"};
        Espresso.onView(ViewMatchers.withId(R.id.radioButtonOption1))
                .check(ViewAssertions.matches(ViewMatchers.withText(initialOptions[0])));
        Espresso.onView(ViewMatchers.withId(R.id.radioButtonOption2))
                .check(ViewAssertions.matches(ViewMatchers.withText(initialOptions[1])));
        Espresso.onView(ViewMatchers.withId(R.id.radioButtonOption3))
                .check(ViewAssertions.matches(ViewMatchers.withText(initialOptions[2])));
        Espresso.onView(ViewMatchers.withId(R.id.radioButtonOption4))
                .check(ViewAssertions.matches(ViewMatchers.withText(initialOptions[3])));
    }

    @Test
    public void testNextAndPreviousButtons() {
        // Start the activity
        FoodActivity activity = foodActivityRule.getActivity();
        // Click next button and ensure it changes the question
        Espresso.onView(ViewMatchers.withId(R.id.buttonNext)).perform(ViewActions.click());
        String nextQuestion = "Which spicy chili paste, made from roasted red peppers, garlic, and various spices, is a staple condiment in Tunisian cuisine?";
        Espresso.onView(ViewMatchers.withId(R.id.textViewQuestion))
                .check(ViewAssertions.matches(ViewMatchers.withText(nextQuestion)));

        // Click previous button and ensure it changes back to the initial question
        Espresso.onView(ViewMatchers.withId(R.id.buttonPrevious)).perform(ViewActions.click());
        String initialQuestion = "What is the national dish of Tunisia, typically made from steamed semolina served with a stew of meat and vegetables?";
        Espresso.onView(ViewMatchers.withId(R.id.textViewQuestion))
                .check(ViewAssertions.matches(ViewMatchers.withText(initialQuestion)));
    }

    @Test
    public void testAnswerSelection() {
        // Start the activity
        FoodActivity activity = foodActivityRule.getActivity();
        // Select an answer for the first question
        Espresso.onView(ViewMatchers.withId(R.id.radioButtonOption1)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonNext)).perform(ViewActions.click());
    }

    @Test
    public void testResultDisplay() {
        // Start the activity asynchronously
        FoodActivity activity = foodActivityRule.getActivity();
        // Select answers for all questions
        for (int i = 0; i < 7; i++) {
            Espresso.onView(ViewMatchers.withId(R.id.radioButtonOption1)).perform(ViewActions.click());
            Espresso.onView(ViewMatchers.withId(R.id.buttonNext)).perform(ViewActions.click());
        }
        // Submit the answers
        Espresso.onView(ViewMatchers.withId(R.id.buttonSubmit)).perform(ViewActions.click());

        // Check if the result card is displayed asynchronously
        Espresso.onView(ViewMatchers.withId(R.id.resultCardView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
