
package lt.mipt.wordcounter;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void analyzeButton_displaysResults() {
        onView(withId(R.id.etInputText))
                .perform(replaceText("Labas pasauli! Tai testas."), closeSoftKeyboard());

        onView(withId(R.id.btnAnalyze)).perform(click());

        onView(withId(R.id.tvResults))
                .check(matches(withText(
                        org.hamcrest.Matchers.containsString("Žodžių: 4")
                )));
    }

    @Test
    public void analyzeButton_emptyText_doesNotDisplayResults() {
        onView(withId(R.id.etInputText))
                .perform(replaceText(""), closeSoftKeyboard());

        onView(withId(R.id.btnAnalyze)).perform(click());

        onView(withId(R.id.tvResults))
                .check(matches(withText(
                        "Analizės rezultatai bus rodomi čia."
                )));
    }
    @Test
    public void sampleButton_fillsInput() {
        onView(withId(R.id.btnSample)).perform(click());

        onView(withId(R.id.etInputText))
                .check(matches(
                        org.hamcrest.Matchers.not(
                                withText("")
                        )
                ));
    }
}
