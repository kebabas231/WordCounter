package lt.mipt.wordcounter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TextStatisticsCalculatorTest {

    @Test
    public void countWords_withNormalText_returnsCorrectCount() {
        assertEquals(4,
                TextStatisticsCalculator.countWords("Labas pasauli, kaip sekasi?"));
    }

    @Test
    public void countWords_withNull_returnsZero() {
        assertEquals(0, TextStatisticsCalculator.countWords(null));
    }

    @Test
    public void countWords_withEmptyText_returnsZero() {
        assertEquals(0, TextStatisticsCalculator.countWords(""));
    }

    @Test
    public void countWords_withWhitespace_returnsZero() {
        assertEquals(0, TextStatisticsCalculator.countWords("   \t\n "));
    }

    @Test
    public void countWords_withLithuanianLetters_countsWords() {
        assertEquals(5,
                TextStatisticsCalculator.countWords("Ąžuolas, ėglė, šuo ir žąsis."));
    }

    @Test
    public void countWords_withHyphenAndApostrophe_countsCombinedWords() {
        assertEquals(3,
                TextStatisticsCalculator.countWords("e-mail don't mother-in-law"));
    }

    @Test
    public void countWords_withNumbersAndSymbols_countsOnlyWords() {
        assertEquals(2,
                TextStatisticsCalculator.countWords("Java 123 !!! test"));
    }

    @Test
    public void countSentences_withNormalText_returnsCorrectCount() {
        assertEquals(3,
                TextStatisticsCalculator.countSentences(
                        "Labas. Kaip sekasi? Viskas gerai!"));
    }

    @Test
    public void countSentences_withNull_returnsZero() {
        assertEquals(0, TextStatisticsCalculator.countSentences(null));
    }

    @Test
    public void countSentences_withEmptyText_returnsZero() {
        assertEquals(0, TextStatisticsCalculator.countSentences(""));
    }

    @Test
    public void countSentences_withWhitespace_returnsZero() {
        assertEquals(0, TextStatisticsCalculator.countSentences("   \n\t"));
    }

    @Test
    public void countSentences_withMultipleSpaces_countsSentences() {
        assertEquals(3,
                TextStatisticsCalculator.countSentences(
                        "Pirmas sakinys.   Antras sakinys! Trečias?"));
    }

    @Test
    public void countSentences_withoutLetters_returnsZero() {
        assertEquals(0,
                TextStatisticsCalculator.countSentences("123. !!! 456?"));
    }

    @Test
    public void countSentences_withUnterminatedText_countsTextAsSentence() {
        assertEquals(1,
                TextStatisticsCalculator.countSentences("Tekstas be pabaigos ženklo"));
    }

    @Test
    public void countPunctuation_withNormalText_returnsCorrectCount() {
        assertEquals(3,
                TextStatisticsCalculator.countPunctuation("Labas, pasauli! Kaip?"));
    }

    @Test
    public void countPunctuation_withNull_returnsZero() {
        assertEquals(0, TextStatisticsCalculator.countPunctuation(null));
    }

    @Test
    public void countPunctuation_withEmptyText_returnsZero() {
        assertEquals(0, TextStatisticsCalculator.countPunctuation(""));
    }

    @Test
    public void countPunctuation_withoutPunctuation_returnsZero() {
        assertEquals(0,
                TextStatisticsCalculator.countPunctuation("Labas pasauli 123"));
    }

    @Test
    public void countPunctuation_withRepeatedPunctuation_countsEveryMark() {
        assertEquals(7,
                TextStatisticsCalculator.countPunctuation("?!...,,"));
    }

    @Test
    public void countNumbers_withNormalText_returnsCorrectCount() {
        assertEquals(3,
                TextStatisticsCalculator.countNumbers(
                        "Turiu 12 knygų, 3 rašiklius ir 25 sąsiuvinius."));
    }

    @Test
    public void countNumbers_withNull_returnsZero() {
        assertEquals(0, TextStatisticsCalculator.countNumbers(null));
    }

    @Test
    public void countNumbers_withEmptyText_returnsZero() {
        assertEquals(0, TextStatisticsCalculator.countNumbers(""));
    }

    @Test
    public void countNumbers_withWhitespace_returnsZero() {
        assertEquals(0, TextStatisticsCalculator.countNumbers("   \t\n"));
    }

    @Test
    public void countNumbers_withDecimalSeparators_countsDecimalsAsSingleNumbers() {
        assertEquals(2,
                TextStatisticsCalculator.countNumbers("Kaina 3.14 ir 0,75 euro."));
    }

    @Test
    public void countNumbers_insideWords_doesNotCountEmbeddedDigits() {
        assertEquals(1,
                TextStatisticsCalculator.countNumbers("A12 test123 45test 67"));
    }

    @Test
    public void countNumbers_withAdjacentNumbers_countsSeparateNumbers() {
        assertEquals(3,
                TextStatisticsCalculator.countNumbers("10 20 30"));
    }

    @Test
    public void countNumbers_withSignedLookingNumbers_countsDigitSequences() {
        assertEquals(2,
                TextStatisticsCalculator.countNumbers("-15 ir +20"));
    }
    @Test
    public void constructor_canBeCreated() {
        assertNotNull(new TextStatisticsCalculator());
    }
}
