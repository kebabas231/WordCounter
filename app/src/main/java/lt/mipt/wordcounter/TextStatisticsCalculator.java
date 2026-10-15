
package lt.mipt.wordcounter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextStatisticsCalculator {

    private static final Pattern WORD_PATTERN =
            Pattern.compile("[\\p{L}\\p{M}]+(?:['-][\\p{L}\\p{M}]+)*");

    private static final Pattern LETTER_PATTERN =
            Pattern.compile("[\\p{L}\\p{M}]");

    private static final Pattern NUMBER_PATTERN =
            Pattern.compile("(?<![\\p{L}\\p{M}\\d])\\d+(?:[.,]\\d+)?(?![\\p{L}\\p{M}\\d])");

    private static final Pattern PUNCTUATION_PATTERN =
            Pattern.compile("[\\p{P}]");

    public static int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        Matcher matcher = WORD_PATTERN.matcher(text);
        int count = 0;

        while (matcher.find()) {
            count++;
        }

        return count;
    }

    public static int countSentences(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        String[] parts = text.trim().split("(?<=[.!?])\\s+");
        int count = 0;

        for (String part : parts) {
            if (LETTER_PATTERN.matcher(part).find()) {
                count++;
            }
        }

        return count;
    }

    public static int countPunctuation(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        Matcher matcher = PUNCTUATION_PATTERN.matcher(text);
        int count = 0;

        while (matcher.find()) {
            count++;
        }

        return count;
    }

    public static int countNumbers(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        Matcher matcher = NUMBER_PATTERN.matcher(text);
        int count = 0;

        while (matcher.find()) {
            count++;
        }

        return count;
    }
}
