
package lt.mipt.wordcounter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TextAnalysisResult implements Serializable {

    private final int sentenceCount;
    private final int wordCount;
    private final int punctuationCount;
    private final int numberCount;
    private final int characterCountWithSpaces;
    private final int characterCountWithoutSpaces;
    private final long timestamp;
    private final String previewSnippet;

    public TextAnalysisResult(
            int sentenceCount,
            int wordCount,
            int punctuationCount,
            int numberCount,
            int characterCountWithSpaces,
            int characterCountWithoutSpaces,
            long timestamp,
            String previewSnippet) {

        this.sentenceCount = sentenceCount;
        this.wordCount = wordCount;
        this.punctuationCount = punctuationCount;
        this.numberCount = numberCount;
        this.characterCountWithSpaces = characterCountWithSpaces;
        this.characterCountWithoutSpaces = characterCountWithoutSpaces;
        this.timestamp = timestamp;
        this.previewSnippet = previewSnippet;
    }

    public int getSentenceCount() {
        return sentenceCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getPunctuationCount() {
        return punctuationCount;
    }

    public int getNumberCount() {
        return numberCount;
    }

    public int getCharacterCountWithSpaces() {
        return characterCountWithSpaces;
    }

    public int getCharacterCountWithoutSpaces() {
        return characterCountWithoutSpaces;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviewSnippet() {
        return previewSnippet;
    }

    public String getFormattedTime() {
        SimpleDateFormat sdf =
                new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}
