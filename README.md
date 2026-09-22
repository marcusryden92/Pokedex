# Pokédex

En konsolapplikation i Java där du kan hantera en samling Pokémons - skapa, visa,
redigera och ta bort (CRUD) - samt spara och ladda datan från fil i JSON-format.

Projektet är inlämningsuppgift 1 i kursen _Programmering med Java, grund_.

## Krav

- **Java 21** (fungerar även på Java 17/25)
- **Maven** (beroenden hämtas automatiskt)

Enda externa beroendet är [Jackson](https://github.com/FasterXML/jackson) för JSON,
vilket anges i `pom.xml`.

## Så här kör du

### I IntelliJ (rekommenderas)

1. Öppna projektmappen i IntelliJ (`File > Open`).
2. Låt IntelliJ ladda Maven-projektet så att Jackson hämtas hem
   (_Maven-fönstret > Reload All Maven Projects_ vid behov).
3. Öppna `src/main/java/se/marcus/pokedex/Main.java` och tryck på den gröna
   ▶-pilen bredvid `main`.

### Från terminalen

Projektet är ett vanligt Maven-projekt, så det bygger med:

```
mvn compile
```

Kör sedan `main`-klassen `se.marcus.pokedex.Main` (t.ex. via IntelliJ, eller med
`java` och Jackson-jar:erna på classpath).

## Datalagring

- Datan sparas i filen **`pokedex.json`** i arbetskatalogen (projektroten när du
  kör via IntelliJ).
- **Första körningen** (ingen sparad fil) fyller Pokédexen automatiskt med 6
  fördefinierade Pokémons med attacker (_seeding_).
- Vid start läses `pokedex.json` in om den finns. Saknas den, eller är den trasig,
  seedas datan om istället för att programmet kraschar.
- Val **8 (Avsluta)** sparar automatiskt innan programmet stängs.

## Funktioner (menyn)

```
=== Meny ===
1. Visa alla Pokémons
2. Lägg till ny Pokemon
3. Redigera Pokemon
4. Ta bort Pokemon
5. Sök Pokémon

6. Spara till fil
7. Ladda från fil

8. Återställ till seedad data
9. Avsluta
```

1. **Visa alla** – listar alla Pokémons med typ, HP och attacker.
2. **Lägg till** – guidad inmatning med validering; varje Pokémon får 1–4 attacker.
3. **Redigera** – ändra namn, typ, max-HP samt lägg till/ta bort attacker.
4. **Ta bort** – välj en Pokémon ur listan och radera den.
5. **Sök Pokémon** – sök på namn (skiftlägesokänsligt) och visa dess detaljer.
6. **Spara till fil** – skriver samlingen till `pokedex.json`.
7. **Ladda från fil** – läser in samlingen från `pokedex.json`.
8. **Återställ till seedad data** – ersätter samlingen med de 6 fördefinierade.
9. **Avsluta** – sparar automatiskt och avslutar.

## Exempel på användning

```
=== Meny ===
1. Visa alla Pokémons
...
Val: 2

=== Ny Pokémon ===
Pokemonens namn: Eevee
Typ:
1. Eld
2. Vatten
3. Gräs
4. El
5. Normal
Val: 5
Max HP: 55

=== Ny attack ===
Attackens namn: Tackle
Baseskada: 40
Träffsäkerhet (0-100): 100
Attackens typ:
...
Eevee tillagd.
```

All inmatning valideras: bokstäver där siffror förväntas, tomma rader, alldeles
för långa namn, värden utanför tillåtet intervall osv. hanteras med ett begripligt
felmeddelande – programmet kraschar inte.

## Projektstruktur

```
src/main/java/se/marcus/pokedex/
├── Main.java                 – startpunkt (ladda eller seeda, starta menyn)
├── model/
│   ├── Pokemon.java          – en Pokémon med 1–4 attacker
│   ├── Attack.java           – en attack
│   ├── ElementType.java      – typ-enum (Fire, Water, Grass, Electric, Normal)
│   └── PokemonCollection.java – samlingen samt seed/save/load
├── ui/
│   ├── Menu.java             – menyn och de olika flödena
│   └── ConsoleInput.java     – inläsning och validering av användarinput
├── persistence/
│   └── JsonStorage.java      – spara/ladda JSON med Jackson
└── exception/
    ├── InvalidPokemonException.java
    └── PokemonNotFoundException.java
```

## VG-krav

- **JSON som persistensformat** via Jackson (se `persistence/JsonStorage.java`).
- **Egna exceptions för domänfel**: `InvalidPokemonException` (kastas vid
  ogiltigt attack-antal, t.ex. fler än 4 eller färre än 1) och
  `PokemonNotFoundException` (kastas vid sökning på ett namn som inte finns).
