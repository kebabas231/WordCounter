
package lt.mipt.wordcounter.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lt.mipt.wordcounter.R;
import lt.mipt.wordcounter.TextAnalysisResult;

public class HistoryAdapter
        extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final List<TextAnalysisResult> history = new ArrayList<>();

    public void setHistory(List<TextAnalysisResult> items) {
        history.clear();
        history.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);

        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull HistoryViewHolder holder, int position) {

        TextAnalysisResult item = history.get(position);

        holder.tvTimestamp.setText(item.getFormattedTime());
        holder.tvPreview.setText(item.getPreviewSnippet());

        holder.tvStatsBadge.setText(
                "Žodžiai: " + item.getWordCount()
                        + " | Sak.: " + item.getSentenceCount()
        );
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvTimestamp;
        TextView tvPreview;
        TextView tvStatsBadge;

        HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTimestamp = itemView.findViewById(R.id.tvItemTimestamp);
            tvPreview = itemView.findViewById(R.id.tvItemPreview);
            tvStatsBadge = itemView.findViewById(R.id.tvItemStatsBadge);
        }
    }
}
