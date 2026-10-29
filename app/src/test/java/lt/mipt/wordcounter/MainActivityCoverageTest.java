
package lt.mipt.wordcounter;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 35, manifest = Config.NONE)
public class MainActivityCoverageTest {

    @Test
    public void saveHistory_handlesException() throws Exception {
        MainActivity activity = Robolectric
                .buildActivity(MainActivity.class)
                .setup()
                .get();

        Field historyField =
                MainActivity.class.getDeclaredField("history");
        historyField.setAccessible(true);

        List<TextAnalysisResult> history =
                (List<TextAnalysisResult>) historyField.get(activity);

        history.add(null);

        Method saveHistory =
                MainActivity.class.getDeclaredMethod("saveHistory");
        saveHistory.setAccessible(true);

        saveHistory.invoke(activity);

        assertNotNull(activity);
    }
}
