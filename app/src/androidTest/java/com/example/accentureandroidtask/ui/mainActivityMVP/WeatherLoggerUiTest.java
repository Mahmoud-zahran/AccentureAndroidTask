package com.example.accentureandroidtask.ui.mainActivityMVP;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.example.accentureandroidtask.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WeatherLoggerUiTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");

    @Test
    public void weatherLoggerUiTest() {
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
try {
 Thread.sleep(3000);
 } catch (InterruptedException e) {
 e.printStackTrace();
 }

        ViewInteraction actionMenuItemView = onView(
allOf(withId(R.id.action_save), withContentDescription("Settings"),
childAtPosition(
childAtPosition(
withId(R.id.action_bar),
1),
1),
isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction actionMenuItemView2 = onView(
allOf(withId(R.id.action_save), withContentDescription("Settings"),
childAtPosition(
childAtPosition(
withId(R.id.action_bar),
1),
1),
isDisplayed()));
        actionMenuItemView2.perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction actionMenuItemView3 = onView(
allOf(withId(R.id.action_save), withContentDescription("Settings"),
childAtPosition(
childAtPosition(
withId(R.id.action_bar),
1),
1),
isDisplayed()));
        actionMenuItemView3.perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction actionMenuItemView4 = onView(
allOf(withId(R.id.action_save), withContentDescription("Settings"),
childAtPosition(
childAtPosition(
withId(R.id.action_bar),
1),
1),
isDisplayed()));
        actionMenuItemView4.perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction actionMenuItemView5 = onView(
allOf(withId(R.id.action_save), withContentDescription("Settings"),
childAtPosition(
childAtPosition(
withId(R.id.action_bar),
1),
1),
isDisplayed()));
        actionMenuItemView5.perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction cardView = onView(
allOf(withId(R.id.AR_CardView),
childAtPosition(
childAtPosition(
withId(R.id.recyclerView),
1),
0),
isDisplayed()));
        cardView.perform(click());

         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
try {
 Thread.sleep(6000);
 } catch (InterruptedException e) {
 e.printStackTrace();
 }

        pressBack();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html

        ViewInteraction cardView2 = onView(
allOf(withId(R.id.AR_CardView),
childAtPosition(
childAtPosition(
withId(R.id.recyclerView),
4),
0),
isDisplayed()));
        cardView2.perform(click());

         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
try {
 Thread.sleep(6000);
 } catch (InterruptedException e) {
 e.printStackTrace();
 }

        pressBack();

         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
try {
 Thread.sleep(1500);
 } catch (InterruptedException e) {
 e.printStackTrace();
 }

        ViewInteraction actionMenuItemView6 = onView(
allOf(withId(R.id.action_save), withContentDescription("Settings"),
childAtPosition(
childAtPosition(
withId(R.id.action_bar),
1),
1),
isDisplayed()));
        actionMenuItemView6.perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction actionMenuItemView7 = onView(
allOf(withId(R.id.action_save), withContentDescription("Settings"),
childAtPosition(
childAtPosition(
withId(R.id.action_bar),
1),
1),
isDisplayed()));
        actionMenuItemView7.perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction actionMenuItemView8 = onView(
allOf(withId(R.id.action_save), withContentDescription("Settings"),
childAtPosition(
childAtPosition(
withId(R.id.action_bar),
1),
1),
isDisplayed()));
        actionMenuItemView8.perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction cardView3 = onView(
allOf(withId(R.id.AR_CardView),
childAtPosition(
childAtPosition(
withId(R.id.recyclerView),
1),
0),
isDisplayed()));
        cardView3.perform(click());

         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
try {
 Thread.sleep(3000);
 } catch (InterruptedException e) {
 e.printStackTrace();
 }

        ViewInteraction relativeLayout = onView(
allOf(childAtPosition(
allOf(withId(R.id.AR_CardView),
childAtPosition(
IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
0)),
0),
isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction relativeLayout2 = onView(
allOf(childAtPosition(
allOf(withId(R.id.AR_CardView),
childAtPosition(
IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
0)),
0),
isDisplayed()));
        relativeLayout2.check(matches(isDisplayed()));
        }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
