package cowsay.andriod.tutkowskim.androidcowsay;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void defaultDefaultValues() {
        final String defaultText = "Hello";
        final String expectedCowsayText = Cowsay.say(defaultText);

        // Verify default values
        onView(withId(R.id.inputText)).check(matches(withText(defaultText)));
        onView(withId(R.id.outputText)).check(matches(withText(expectedCowsayText)));
    }

    @Test
    public void changeCowsayText() {
        final String textToEnter = "Moo!";
        final String expectedCowsayText = Cowsay.say(textToEnter);

        // Close the soft-keyboard
        onView(withId(R.id.inputText)).perform(closeSoftKeyboard());

        // Change the text
        onView(withId(R.id.inputText))
                .perform(click())
                .perform(replaceText(textToEnter));

        // Verify the input and output fields are updated correctly
        onView(withId(R.id.inputText))
                .check(matches(withText(textToEnter)));
        onView(withId(R.id.outputText))
                .check(matches(withText(expectedCowsayText)));
    }
}
