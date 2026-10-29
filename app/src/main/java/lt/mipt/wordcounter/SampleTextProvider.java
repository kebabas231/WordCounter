
package lt.mipt.wordcounter;

import java.util.Random;

public class SampleTextProvider {

    private static final String[] SAMPLES = {
            "Vilniaus universitetas įkurtas 1579 metais. Ar žinojote, kad jame studijuoja daugiau nei 20 000 studentų?! Tai nuostabu...",
            "Programavimas Java kalba yra įdomus, tačiau reikalauja atidumo! 2024 metais sukurtas MIPT praktikos darbas Nr. 2 veikia puikiai.",
            "Greitas ruduo lapinas peršoko per 15 miegančių šunų! Kiek žodžių čia yra? Tiksliai 12 žodžių ir 3 skyrybos ženklai.",
            "Skaičiavimas: 10 + 25 = 35. Dešimtainis skaičius yra 3.14, o kitas – 0,75! Ar viskas suskaičiuota teisingai?",
            "Šiandien puiki diena mokytis. Rytas ramus, saulėtas ir šiltas (apie 22 laipsnius). Ką veiksime rytoj?"
    };

    private static final Random RANDOM = new Random();
    private static int lastIndex = -1;

    public static String getRandomSample() {
        return getRandomSample(RANDOM);
    }

    // Šis metodas leidžia testuose kontroliuoti atsitiktinį indeksą.
    static String getRandomSample(Random random) {
        int index;

        do {
            index = random.nextInt(SAMPLES.length);
        } while (index == lastIndex);

        lastIndex = index;
        return SAMPLES[index];
    }
}
