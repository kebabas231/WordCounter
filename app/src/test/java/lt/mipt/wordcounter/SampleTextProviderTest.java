
package lt.mipt.wordcounter;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.lang.reflect.Field;

public class SampleTextProviderTest {

    @Test
    public void getRandomSample_returnsNonNullText() {
        assertNotNull(SampleTextProvider.getRandomSample());
    }

    @Test
    public void getRandomSample_returnsNonEmptyText() {
        assertFalse(SampleTextProvider.getRandomSample().isEmpty());
    }

    @Test
    public void getRandomSample_doesNotRepeatPreviousSample() {
        String first = SampleTextProvider.getRandomSample();
        String second = SampleTextProvider.getRandomSample();

        assertNotNull(first);
        assertNotNull(second);
        assertNotEquals(first, second);
    }

    @Test
    public void getRandomSample_returnsTextRepeatedly() {
        for (int i = 0; i < 50; i++) {
            String sample = SampleTextProvider.getRandomSample();

            assertNotNull(sample);
            assertFalse(sample.isEmpty());
        }
    }

    @Test
    public void getRandomSample_returnsOneOfAvailableSamples() {
        boolean found = false;

        for (int i = 0; i < 20; i++) {
            String sample = SampleTextProvider.getRandomSample();

            if (sample.startsWith("Vilniaus universitetas")
                    || sample.startsWith("Programavimas Java")
                    || sample.startsWith("Greitas ruduo")
                    || sample.startsWith("Skaičiavimas:")
                    || sample.startsWith("Šiandien puiki diena")) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    public void getRandomSample_retriesWhenIndexRepeats() throws Exception {
        Field lastIndexField =
                SampleTextProvider.class.getDeclaredField("lastIndex");
        lastIndexField.setAccessible(true);

        int originalLastIndex = lastIndexField.getInt(null);

        try {
            lastIndexField.setInt(null, 0);

            final int[] calls = {0};

            Random controlledRandom = new Random() {
                @Override
                public int nextInt(int bound) {
                    calls[0]++;
                    return calls[0] == 1 ? 0 : 1;
                }
            };

            String sample =
                    SampleTextProvider.getRandomSample(controlledRandom);

            assertNotNull(sample);
            assertEquals(2, calls[0]);
            assertTrue(sample.startsWith("Programavimas Java"));
        } finally {
            lastIndexField.setInt(null, originalLastIndex);
        }
    }
    @Test
    public void getRandomSample_returnsDifferentSamplesOverTime() {
        String previous = SampleTextProvider.getRandomSample();
        boolean differentFound = false;

        for (int i = 0; i < 10; i++) {
            String current = SampleTextProvider.getRandomSample();

            if (!previous.equals(current)) {
                differentFound = true;
                break;
            }
        }

        assertTrue(differentFound);
    }

    @Test
    public void constructor_canBeCreated() throws Exception {
        java.lang.reflect.Constructor<SampleTextProvider> constructor =
                SampleTextProvider.class.getDeclaredConstructor();

        constructor.setAccessible(true);

        assertNotNull(constructor.newInstance());
    }
}
