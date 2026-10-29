
package lt.mipt.wordcounter;

import static org.junit.Assert.assertNotNull;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 35, manifest = Config.NONE)
public class MainActivityRobolectricTest {

    @Test
    public void activity_isCreatedWithRequiredViews() {
        MainActivity activity = Robolectric
                .buildActivity(MainActivity.class)
                .setup()
                .get();

        assertNotNull(activity.findViewById(R.id.etInputText));
        assertNotNull(activity.findViewById(R.id.tvResults));
        assertNotNull(activity.findViewById(R.id.btnAnalyze));
        assertNotNull(activity.findViewById(R.id.btnSample));
        assertNotNull(activity.findViewById(R.id.rvHistory));
    }

    @Test
    public void sampleButton_fillsInput() {
        MainActivity activity = Robolectric
                .buildActivity(MainActivity.class)
                .setup()
                .get();

        Button sampleButton = activity.findViewById(R.id.btnSample);
        EditText input = activity.findViewById(R.id.etInputText);

        sampleButton.performClick();

        assertNotNull(input.getText().toString());
        org.junit.Assert.assertFalse(input.getText().toString().isEmpty());
    }

    @Test
    public void analyzeButton_displaysAnalysisResults() {
        MainActivity activity = Robolectric
                .buildActivity(MainActivity.class)
                .setup()
                .get();

        EditText input = activity.findViewById(R.id.etInputText);
        TextView results = activity.findViewById(R.id.tvResults);
        Button analyzeButton = activity.findViewById(R.id.btnAnalyze);

        input.setText("Labas pasauli! Tai testas.");
        analyzeButton.performClick();

        org.junit.Assert.assertTrue(
                results.getText().toString().contains("Analizės rezultatai")
        );
        org.junit.Assert.assertTrue(
                results.getText().toString().contains("Žodžių:")
        );
    }

    @Test
    public void analyzeButton_emptyText_keepsPlaceholder() {
        MainActivity activity = Robolectric
                .buildActivity(MainActivity.class)
                .setup()
                .get();

        EditText input = activity.findViewById(R.id.etInputText);
        Button analyze = activity.findViewById(R.id.btnAnalyze);
        TextView results = activity.findViewById(R.id.tvResults);

        String original = results.getText().toString();

        input.setText("   ");
        analyze.performClick();

        org.junit.Assert.assertEquals(
                original, results.getText().toString()
        );
    }

    @Test
    public void analyzeButton_longText_handlesPreviewLimit() {
        MainActivity activity = Robolectric
                .buildActivity(MainActivity.class)
                .setup()
                .get();

        EditText input = activity.findViewById(R.id.etInputText);
        Button analyze = activity.findViewById(R.id.btnAnalyze);
        TextView results = activity.findViewById(R.id.tvResults);

        String longText = "Labas ".repeat(20);
        input.setText(longText);
        analyze.performClick();

        org.junit.Assert.assertTrue(
                results.getText().toString().contains("Žodžių:")
        );

        RecyclerView recyclerView =
                activity.findViewById(R.id.rvHistory);

        org.junit.Assert.assertEquals(
                1, recyclerView.getAdapter().getItemCount()
        );
    }

    @Test
    public void loadHistory_restoresSavedEntry() {
        Context context = ApplicationProvider.getApplicationContext();

        SharedPreferences prefs = context.getSharedPreferences(
                "WordCounterPrefs", Context.MODE_PRIVATE
        );

        String savedHistory = "[{\"sentences\":2,\"words\":5,"
                + "\"punctuation\":3,\"numbers\":1,"
                + "\"withSpaces\":25,\"withoutSpaces\":20,"
                + "\"timestamp\":1000,\"preview\":\"Išsaugotas tekstas\"}]";

        prefs.edit().putString("history", savedHistory).commit();

        MainActivity activity = Robolectric
                .buildActivity(MainActivity.class)
                .setup()
                .get();

        RecyclerView recyclerView =
                activity.findViewById(R.id.rvHistory);

        org.junit.Assert.assertEquals(
                1, recyclerView.getAdapter().getItemCount()
        );

        prefs.edit().clear().commit();
    }

    @Test
    public void loadHistory_invalidJson_clearsHistory() {
        Context context = ApplicationProvider.getApplicationContext();

        SharedPreferences prefs = context.getSharedPreferences(
                "WordCounterPrefs", Context.MODE_PRIVATE
        );

        prefs.edit().putString("history", "invalid-json").commit();

        MainActivity activity = Robolectric
                .buildActivity(MainActivity.class)
                .setup()
                .get();

        RecyclerView recyclerView =
                activity.findViewById(R.id.rvHistory);

        org.junit.Assert.assertEquals(
                0, recyclerView.getAdapter().getItemCount()
        );

        prefs.edit().clear().commit();
    }

}
