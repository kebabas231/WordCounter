# WordCounter – teksto analizės programėlė

## 1. Projekto aprašymas

**WordCounter** – tai „Android“ mobilioji programėlė, skirta tekstui analizuoti ir pagrindiniams teksto elementams skaičiuoti. Programėlė sukurta naudojant „Android Studio“ kūrimo aplinką ir „Java“ programavimo kalbą.

Programėlės paskirtis – suteikti vartotojui galimybę įvesti tekstą, atlikti jo analizę, peržiūrėti gautus rezultatus ir išsaugoti ankstesnių skaičiavimų istoriją.

Projektas parengtas kaip mobiliojo programavimo technologijų (MIPT) praktinis darbas Nr. 2.

## 2. Pagrindinės funkcijos

Programėlėje įgyvendintos šios funkcijos:

- **Teksto įvedimas** – vartotojas gali įvesti tekstą, kurį nori analizuoti.
- **Sakinių skaičiavimas** – apskaičiuojamas įvestame tekste esančių sakinių kiekis.
- **Žodžių skaičiavimas** – nustatomas tekste esančių žodžių kiekis.
- **Skyrybos ženklų skaičiavimas** – apskaičiuojamas tekste esančių skyrybos ženklų kiekis.
- **Skaičių skaičiavimas** – nustatomas tekste esančių skaičių kiekis.
- **Atsitiktinio testinio teksto generavimas** – programėlė gali parinkti atsitiktinį iš anksto paruoštą tekstą, skirtą skaičiavimo funkcijoms išbandyti.
- **Skaičiavimų istorija** – programėlėje rodomi ankstesnių skaičiavimų įrašai.
- **Istorijos išsaugojimas** – skaičiavimų istorija išsaugoma naudojant „Android“ `SharedPreferences` mechanizmą, todėl įrašai išlieka programėlę uždarius ir vėl paleidus.
- **Tuščio teksto patikrinimas** – jei vartotojas bando atlikti skaičiavimą neįvedęs teksto, programėlė pateikia pranešimą vartotojui.

## 3. Kaip naudotis programėle

### 3.1. Teksto įvedimas

1. Paleiskite „WordCounter“ programėlę.
2. Suraskite teksto įvedimo lauką.
3. Įveskite arba įklijuokite norimą analizuoti tekstą.
4. Pasirinkite programėlėje esantį skaičiavimo mygtuką.
5. Peržiūrėkite pateiktus analizės rezultatus.

Jeigu teksto laukas tuščias, programėlė pateikia pranešimą ir neleidžia atlikti įprasto skaičiavimo su tuščiu tekstu.

### 3.2. Teksto elementų skaičiavimas

Atlikus teksto analizę, apskaičiuojami šie elementai:

- **Sakiniai** – tekste esančių sakinių skaičius.
- **Žodžiai** – tekste esančių žodžių skaičius.
- **Skyrybos ženklai** – tekste esančių skyrybos ženklų skaičius.
- **Skaičiai** – tekste esančių skaičių skaičius.

Skaičiavimo rezultatai pateikiami programėlės sąsajoje.

Tikslus elementų atpažinimas priklauso nuo programėlėje įgyvendintos skaičiavimo logikos.

### 3.3. Testinio teksto generavimas

Programėlėje įgyvendinta galimybė pasirinkti atsitiktinį testinį tekstą. Ši funkcija skirta teksto analizės ir skaičiavimo galimybėms išbandyti.

Testinio teksto parinkimas atliekamas naudojant `SampleTextProvider` klasės metodą:

`SampleTextProvider.getRandomSample()`

Metodas parenka atsitiktinį tekstą iš programėlėje paruoštų pavyzdžių.

Naudojimo eiga:

1. Pasirinkite programėlėje esantį testinio teksto generavimo mygtuką.
2. Įsitikinkite, kad į teksto lauką įkeltas parinktas pavyzdys.
3. Atlikite teksto analizę.
4. Peržiūrėkite skaičiavimo rezultatus.

Ši funkcija leidžia išbandyti programėlę naudojant skirtingus tekstus, nereikalaujant kiekvieną kartą jų įvesti rankiniu būdu.

### 3.4. Skaičiavimų istorija

Programėlėje pateikiama skaičiavimų istorija, kurioje rodomi ankstesnių skaičiavimų įrašai.

Istorijos duomenys saugomi naudojant `SharedPreferences` – „Android“ mechanizmą, skirtą nedideliam programėlės duomenų kiekiui išsaugoti.

Pagrindinės istorijos savybės:

- Ankstesni skaičiavimų įrašai rodomi programėlėje.
- Istorijos duomenys išsaugomi uždarius programėlę.
- Pakartotinai paleidus programėlę, anksčiau išsaugoti įrašai išlieka.

Istorijos įrašams atvaizduoti naudojama `HistoryAdapter` klasė.

**Pastaba:** istorijos išvalymo galimybė dar nepatikrinta, todėl šiame dokumente ji neaprašoma kaip įgyvendinta funkcija.

## 4. Programos struktūra

Projektą sudaro „Java“ klasės ir XML išdėstymo failai.

| Klasė arba komponentas | Paskirtis |
|---|---|
| `MainActivity` | Pagrindinė programėlės veiklos klasė, atsakinga už pagrindinį programėlės veikimą ir vartotojo sąsajos sąveiką. |
| `TextAnalysisResult` | Skirta teksto analizės rezultatams pateikti ir saugoti. |
| `TextStatisticsCalculator` | Atlieka teksto statistikos skaičiavimus. |
| `SampleTextProvider` | Parenka atsitiktinį testinį tekstą. |
| `HistoryAdapter` | Atvaizduoja skaičiavimų istorijos įrašus. |
| XML išdėstymo failai | Apibrėžia programėlės vartotojo sąsajos elementų išdėstymą. |
| `SharedPreferences` | Naudojama skaičiavimų istorijos duomenims išsaugoti. |

Toks programos suskirstymas leidžia atskirti vartotojo sąsajos valdymą, skaičiavimo logiką, rezultatų pateikimą, testinių duomenų parinkimą ir istorijos atvaizdavimą.

## 5. Techniniai reikalavimai

Programėlės kūrimui ir paleidimui reikalinga:

- Kompiuteris su įdiegta „Android Studio“ kūrimo aplinka.
- „Java“ programavimo kalbos palaikymas.
- Projekte naudojama „Android SDK“ versija ir sukonfigūruota kūrimo aplinka.
- „Android“ emuliatorius arba suderinamas fizinis „Android“ įrenginys programėlei paleisti ir išbandyti.

**Minimali palaikoma Android versija:** dar reikia patikrinti projekto `minSdk` reikšmę.

**Kitos techninės versijos:** „Android Gradle Plugin“, „Gradle“ ir „JDK“ versijos turi atitikti projekto konfigūraciją. Tikslios jų reikšmės šiame dokumente nepateikiamos, nes jos dar nepatikrintos.

## 6. Kaip paleisti programėlę naudojant „Android Studio“

### 6.1. Projekto atidarymas

1. Paleiskite „Android Studio“.
2. Pasirinkite projekto atidarymo parinktį.
3. Nurodykite „WordCounter“ projekto aplanką arba pasirinkite iš „GitHub“ atsisiųstą projekto kopiją.
4. Palaukite, kol „Android Studio“ įkels projektą ir atliks „Gradle Sync“ procesą.
5. Jei pateikiami trūkstamų SDK komponentų pranešimai, įdiekite projektui reikalingus komponentus.

### 6.2. Programėlės paleidimas

1. Pasirinkite turimą „Android“ emuliatorių arba prijunkite suderinamą fizinį įrenginį.
2. Jei naudojate fizinį įrenginį, įsitikinkite, kad jis tinkamai paruoštas programėlėms derinti.
3. „Android Studio“ įrankių juostoje pasirinkite pagrindinę paleidimo konfigūraciją.
4. Paspauskite **Run** mygtuką.
5. Palaukite, kol projektas bus sukompiliuotas ir programėlė paleista pasirinktame įrenginyje.

Jeigu projektas nesikompiliuoja, patikrinkite „Gradle Sync“ pranešimus, SDK konfigūraciją ir kitas kūrimo klaidas.

## 7. Programėlės testavimas

Pagrindines programėlės funkcijas galima patikrinti atliekant šiuos veiksmus:

| Nr. | Testavimo veiksmas | Tikrinama funkcija |
|---|---|---|
| 1 | Įvesti įprastą tekstą ir atlikti analizę. | Teksto analizė ir skaičiavimo rezultatai. |
| 2 | Įvesti tekstą su keliais sakiniais. | Sakinių skaičiavimas. |
| 3 | Įvesti tekstą su skirtingais žodžiais. | Žodžių skaičiavimas. |
| 4 | Įvesti tekstą su skyrybos ženklais. | Skyrybos ženklų skaičiavimas. |
| 5 | Įvesti tekstą, kuriame yra skaičių. | Skaičių skaičiavimas. |
| 6 | Palikti teksto lauką tuščią ir bandyti atlikti skaičiavimą. | Tuščio teksto patikrinimas ir pranešimas vartotojui. |
| 7 | Kelis kartus generuoti testinį tekstą. | Atsitiktinio testinio teksto parinkimas. |
| 8 | Atlikti kelis skaičiavimus ir peržiūrėti istoriją. | Skaičiavimų istorijos atvaizdavimas. |
| 9 | Uždaryti ir iš naujo paleisti programėlę. | Istorijos duomenų išsaugojimas naudojant `SharedPreferences`. |

Testavimo metu rekomenduojama palyginti pateiktus rezultatus su rankiniu būdu patikrintais rezultatais.

Ši lentelė aprašo rekomenduojamus testavimo veiksmus, o ne patvirtintus testavimo rezultatus.

## 8. Naudojamos technologijos

Projekte naudojamos šios technologijos ir komponentai:

- **Java** – programėlės veikimo logikai įgyvendinti.
- **Android Studio** – programėlės kūrimo, kompiliavimo ir derinimo aplinka.
- **Android SDK** – „Android“ programėlei kurti ir paleisti reikalingi įrankiai.
- **XML** – vartotojo sąsajos išdėstymui aprašyti.
- **SharedPreferences** – skaičiavimų istorijos duomenims išsaugoti.

## 9. Projekto išvados

„WordCounter“ programėlė skirta pagrindiniams teksto elementams analizuoti ir jų kiekiui apskaičiuoti. Ji leidžia vartotojui įvesti tekstą, generuoti atsitiktinius testinius pavyzdžius, peržiūrėti analizės rezultatus ir naudotis išsaugoma skaičiavimų istorija.

Projekte naudojamos atskiros klasės teksto statistikai skaičiuoti, rezultatams pateikti, testiniams tekstams parinkti ir istorijos įrašams atvaizduoti. Duomenų išsaugojimui naudojamas `SharedPreferences` mechanizmas.

Programėlė suteikia galimybę praktiškai išbandyti „Android“ programėlių kūrimo principus, vartotojo sąsajos elementų valdymą, teksto apdorojimą ir lokalių duomenų išsaugojimą.

---

**Praktinis darbas:** MIPT praktinis darbas Nr. 2  
**Projekto pavadinimas:** WordCounter  
**Programavimo kalba:** Java  
**Kūrimo aplinka:** Android Studio