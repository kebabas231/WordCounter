
package lt.mipt.wordcounter;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TextAnalysisResultTest {

    @Test
    public void constructor_storesAllValuesCorrectly() {
        TextAnalysisResult result = new TextAnalysisResult(
                3, 12, 5, 2, 80, 65, 1_700_000_000_000L, "Testinis tekstas"
        );

        assertEquals(3, result.getSentenceCount());
        assertEquals(12, result.getWordCount());
        assertEquals(5, result.getPunctuationCount());
        assertEquals(2, result.getNumberCount());
        assertEquals(80, result.getCharacterCountWithSpaces());
        assertEquals(65, result.getCharacterCountWithoutSpaces());
        assertEquals(1_700_000_000_000L, result.getTimestamp());
        assertEquals("Testinis tekstas", result.getPreviewSnippet());
    }

    @Test
    public void constructor_acceptsZeroValues() {
        TextAnalysisResult result = new TextAnalysisResult(
                0, 0, 0, 0, 0, 0, 0L, ""
        );

        assertEquals(0, result.getSentenceCount());
        assertEquals(0, result.getWordCount());
        assertEquals(0, result.getPunctuationCount());
        assertEquals(0, result.getNumberCount());
        assertEquals(0, result.getCharacterCountWithSpaces());
        assertEquals(0, result.getCharacterCountWithoutSpaces());
        assertEquals(0L, result.getTimestamp());
        assertEquals("", result.getPreviewSnippet());
    }

    @Test
    public void constructor_acceptsNegativeNumericValues() {
        TextAnalysisResult result = new TextAnalysisResult(
                -1, -2, -3, -4, -5, -6, -7L, "Test"
        );

        assertEquals(-1, result.getSentenceCount());
        assertEquals(-2, result.getWordCount());
        assertEquals(-3, result.getPunctuationCount());
        assertEquals(-4, result.getNumberCount());
        assertEquals(-5, result.getCharacterCountWithSpaces());
        assertEquals(-6, result.getCharacterCountWithoutSpaces());
        assertEquals(-7L, result.getTimestamp());
    }

    @Test
    public void getPreviewSnippet_returnsNullWhenNullWasProvided() {
        TextAnalysisResult result = new TextAnalysisResult(
                1, 1, 0, 0, 1, 1, 0L, null
        );

        assertEquals(null, result.getPreviewSnippet());
    }

    @Test
    public void getFormattedTime_returnsExpectedTime() {
        long timestamp = 1_700_000_000_000L;

        TextAnalysisResult result = new TextAnalysisResult(
                1, 1, 1, 1, 10, 8, timestamp, "Test"
        );

        SimpleDateFormat formatter =
                new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        String expected = formatter.format(new Date(timestamp));

        assertEquals(expected, result.getFormattedTime());
    }

    @Test
    public void getFormattedTime_returnsTimeInHHmmssFormat() {
        TextAnalysisResult result = new TextAnalysisResult(
                1, 1, 0, 0, 10, 8, System.currentTimeMillis(), "Test"
        );

        String formattedTime = result.getFormattedTime();

        assertNotNull(formattedTime);
        assertTrue(formattedTime.matches("\\d{2}:\\d{2}:\\d{2}"));
    }
}
