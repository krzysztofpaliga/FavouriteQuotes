package pl.psydell.favouritequotes.random;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import pl.psydell.favouritequotes.MainActivity;
import pl.psydell.favouritequotes.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class RandomQuoteScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickRandomQuoteNavButton_showsRandomQuoteScreen() {

        String navigateRandom = mMainActivityTestRule.getActivity()
                .getString(R.string.title_random);

        onView(allOf(withText(navigateRandom),
                isDescendantOfA(withId(R.id.navigation)),
                isDisplayed()))
                .perform(click());

        onView(withId(R.id.fragment_random)).check(matches(isDisplayed()));
    }
}
