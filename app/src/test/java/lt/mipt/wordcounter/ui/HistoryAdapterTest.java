
package lt.mipt.wordcounter.ui;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import lt.mipt.wordcounter.R;
import lt.mipt.wordcounter.TextAnalysisResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 35, manifest = Config.NONE)
public class HistoryAdapterTest {

    private TextAnalysisResult createResult(
            int sentences, int words, String preview) {
        return new TextAnalysisResult(
                sentences,
                words,
                2,
                1,
                20,
                17,
                System.currentTimeMillis(),
                preview
        );
    }

    @Test
    public void newAdapter_hasZeroItems() {
        HistoryAdapter adapter = new HistoryAdapter();

        assertEquals(0, adapter.getItemCount());
    }

    @Test
    public void setHistory_updatesItemCount() {
        HistoryAdapter adapter = new HistoryAdapter();
        List<TextAnalysisResult> items = new ArrayList<>();

        items.add(createResult(1, 5, "Pirmas tekstas"));
        items.add(createResult(2, 10, "Antras tekstas"));

        adapter.setHistory(items);

        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void setHistory_replacesPreviousItems() {
        HistoryAdapter adapter = new HistoryAdapter();
        List<TextAnalysisResult> first = new ArrayList<>();
        first.add(createResult(1, 5, "Pirmas"));

        adapter.setHistory(first);

        List<TextAnalysisResult> second = new ArrayList<>();
        second.add(createResult(2, 10, "Antras"));
        second.add(createResult(3, 15, "Trečias"));

        adapter.setHistory(second);

        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void onCreateViewHolder_createsHolderWithTextViews() {
        android.content.Context context =
                ApplicationProvider.getApplicationContext();

        ViewGroup parent = new android.widget.FrameLayout(context);
        HistoryAdapter adapter = new HistoryAdapter();

        HistoryAdapter.HistoryViewHolder holder =
                adapter.onCreateViewHolder(parent, 0);

        assertNotNull(holder);
        assertNotNull(holder.itemView.findViewById(R.id.tvItemTimestamp));
        assertNotNull(holder.itemView.findViewById(R.id.tvItemPreview));
        assertNotNull(holder.itemView.findViewById(R.id.tvItemStatsBadge));
    }

    @Test
    public void onBindViewHolder_displaysHistoryValues() {
        android.content.Context context =
                ApplicationProvider.getApplicationContext();

        ViewGroup parent = new android.widget.FrameLayout(context);
        HistoryAdapter adapter = new HistoryAdapter();

        List<TextAnalysisResult> items = new ArrayList<>();
        items.add(createResult(3, 12, "Testinis peržiūros tekstas"));
        adapter.setHistory(items);

        HistoryAdapter.HistoryViewHolder holder =
                adapter.onCreateViewHolder(parent, 0);

        adapter.onBindViewHolder(holder, 0);

        TextView preview = holder.itemView.findViewById(
                R.id.tvItemPreview);
        TextView stats = holder.itemView.findViewById(
                R.id.tvItemStatsBadge);
        TextView timestamp = holder.itemView.findViewById(
                R.id.tvItemTimestamp);

        assertEquals("Testinis peržiūros tekstas",
                preview.getText().toString());
        assertEquals("Žodžiai: 12 | Sak.: 3",
                stats.getText().toString());
        assertNotNull(timestamp.getText().toString());
    }
}
