package com.dsc.latihanconstraint;

import android.os.IBinder;
import android.view.WindowManager;

import androidx.test.espresso.Root;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {


    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void loginBerhasilTest() {
        onView(withId(R.id.edtUsername))
                .perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.btnSignIn))
                .perform(click());
        onView(withText("YEAYYYY")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void loginPasswordSalahTest() {
        onView(withId(R.id.edtUsername))
                .perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("salah"), closeSoftKeyboard());
        onView(withId(R.id.btnSignIn))
                .perform(click());
        onView(withText("Salah Password!")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void loginUsernamePasswordSalah() {
        onView(withId(R.id.edtUsername))
                .perform(typeText("asd"), closeSoftKeyboard());
        onView(withId(R.id.edtPassword))
                .perform(typeText("salah"), closeSoftKeyboard());
        onView(withId(R.id.btnSignIn))
                .perform(click());
        onView(withText("Salah keduanya cuy!")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public class ToastMatcher extends TypeSafeMatcher<Root> {

        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                return windowToken == appToken;
            }
            return false;
        }

    }
}
