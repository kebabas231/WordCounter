
package lt.mipt.wordcounter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lt.mipt.wordcounter.ui.HistoryAdapter;

public class MainActivity extends AppCompatActivity {

    private EditText etInputText;
    private TextView tvResults;
    private HistoryAdapter historyAdapter;

    private final List<TextAnalysisResult> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInputText = findViewById(R.id.etInputText);
        tvResults = findViewById(R.id.tvResults);

        Button btnAnalyze = findViewById(R.id.btnAnalyze);
        Button btnSample = findViewById(R.id.btnSample);
        RecyclerView rvHistory = findViewById(R.id.rvHistory);

        historyAdapter = new HistoryAdapter();

        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(historyAdapter);

        loadHistory();

        btnAnalyze.setOnClickListener(v -> analyzeText());

        btnSample.setOnClickListener(v ->
                etInputText.setText(SampleTextProvider.getRandomSample())
        );
    }

    private void analyzeText() {
        String text = etInputText.getText().toString().trim();

        if (text.isEmpty()) {
            Toast.makeText(
                    this,
                    "Įvesk tekstą analizei.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        int words = TextStatisticsCalculator.countWords(text);
        int sentences = TextStatisticsCalculator.countSentences(text);
        int punctuation = TextStatisticsCalculator.countPunctuation(text);
        int numbers = TextStatisticsCalculator.countNumbers(text);

        tvResults.setText(
                "Analizės rezultatai\n\n"
                        + "Žodžių: " + words + "\n"
                        + "Sakinių: " + sentences + "\n"
                        + "Skyrybos ženklų: " + punctuation + "\n"
                        + "Skaičių: " + numbers + "\n"
                        + "Simbolių su tarpais: " + text.length() + "\n"
                        + "Simbolių be tarpų: "
                        + text.replaceAll("\\s", "").length()
        );

        String preview = text.length() > 80
                ? text.substring(0, 80) + "..."
                : text;

        TextAnalysisResult result = new TextAnalysisResult(
                sentences,
                words,
                punctuation,
                numbers,
                text.length(),
                text.replaceAll("\\s", "").length(),
                System.currentTimeMillis(),
                preview
        );

        history.add(0, result);
        historyAdapter.setHistory(history);
        saveHistory();
    }

    private void saveHistory() {
        SharedPreferences prefs = getSharedPreferences(
                "WordCounterPrefs",
                MODE_PRIVATE
        );

        JSONArray array = new JSONArray();

        try {
            for (TextAnalysisResult item : history) {
                JSONObject object = new JSONObject();

                object.put("sentences", item.getSentenceCount());
                object.put("words", item.getWordCount());
                object.put("punctuation", item.getPunctuationCount());
                object.put("numbers", item.getNumberCount());
                object.put("withSpaces",
                        item.getCharacterCountWithSpaces());
                object.put("withoutSpaces",
                        item.getCharacterCountWithoutSpaces());
                object.put("timestamp", item.getTimestamp());
                object.put("preview", item.getPreviewSnippet());

                array.put(object);
            }

            prefs.edit()
                    .putString("history", array.toString())
                    .apply();

        } catch (Exception e) {
            Toast.makeText(
                    this,
                    "Nepavyko išsaugoti istorijos.",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void loadHistory() {
        SharedPreferences prefs = getSharedPreferences(
                "WordCounterPrefs",
                MODE_PRIVATE
        );

        String saved = prefs.getString("history", "[]");

        try {
            JSONArray array = new JSONArray(saved);

            history.clear();

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);

                TextAnalysisResult item = new TextAnalysisResult(
                        object.getInt("sentences"),
                        object.getInt("words"),
                        object.getInt("punctuation"),
                        object.getInt("numbers"),
                        object.getInt("withSpaces"),
                        object.getInt("withoutSpaces"),
                        object.getLong("timestamp"),
                        object.getString("preview")
                );

                history.add(item);
            }

            historyAdapter.setHistory(history);

        } catch (Exception e) {
            history.clear();
            historyAdapter.setHistory(history);
        }
    }
}
