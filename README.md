# Pokemon Battler

Bygger vidare på Pokédex-projektet från inlämning 1. Utöver CRUD, sökning och
JSON-persistens finns nu ett turbaserat stridssystem mot en CPU-motståndare.

Inlämningsuppgift 2, _Programmering med Java, grund_.

## Krav

- Java 21
- Maven

Beroenden (hämtas via `pom.xml`): Jackson för JSON, JUnit 5 för tester.

## Köra programmet

Öppna i IntelliJ, låt Maven ladda beroenden, kör `Main.java`.

Alternativt: `mvn compile` och kör `se.marcus.pokedex.Main`.

Tester: `mvn test`

## Menyn

```
1. Visa alla Pokémons
2. Lägg till ny Pokemon
3. Redigera Pokemon
4. Ta bort Pokemon
5. Sök Pokémon

6. Starta strid
7. Visa statistik

8. Spara till fil
9. Ladda från fil
10. Återställ till seedad data

11. Avsluta
```

1–5 och 8–10 är kvar från Pokédexen. Nytt är **Starta strid** (välj Pokémon,
CPU väljer slumpmässigt, välj svårighetsgrad) och **Visa statistik** (vinster per
Pokémon, mest använda attack, antal strider).

## Stridssystemet

Turbaserat -spelaren och CPU:n turas om att välja attack.

- **Träffsäkerhet:** varje attack har accuracy 0–100 som avgör om den träffar.
- **Typeffektivitet:** Fire > Grass, Water > Fire, Grass > Water, Electric > Water
  ger 2x skada. Omvänt ger 0.5x. Allt annat ger 1x.
- **Kritiska träffar:** 1/16 chans, dubbel skada, syns i loggen.
- **Stridslogg:** allt som händer loggas och visas efter striden.
- **Svårighetsgrad:** Lätt (CPU väljer slumpmässigt) eller Svår (CPU väljer attacken
  med högst basskada).

## Filer

- `pokedex.json` -Pokémon-samlingen. Seedas automatiskt vid första körning.
- `battle_stats.json` -stridsstatistik. Skapas efter första striden.

Saknad eller trasig fil kraschar inte programmet -det startar med tom data istället.

## Krasch-test

Programmet kraschar inte. All input valideras i `ConsoleInput`, konstruktorer
kastar exception vid ogiltiga värden, saknad/korrupt JSON fångas med try/catch.

## OOP-arkitektur

### Abstrakt Attack

`Attack` är abstrakt med en abstrakt `execute`-metod. `DamageAttack` ärver den
och implementerar skadeberäkningen (accuracy-check, typeffektivitet, kritisk träff,
`defender.takeDamage`).

Poängen är att en ny attacktyp -t.ex. en StatusAttack som sänker accuracy -kan
läggas till som en ny subklass utan att röra BattleEngine eller befintlig kod.
Execute anropas polymorft, och engine vet inte vilken subklass som kör.

### Strategy pattern för CPU

`CpuStrategy` är ett interface. `RandomStrategy` slumpar, `AggressiveStrategy`
väljer högst skada. BattleEngine tar emot en CpuStrategy i konstruktorn och
anropar `strategy.chooseAttack(...)` -den vet inte och bryr sig inte om vilken
implementation som ligger bakom. Att byta svårighetsgrad är att skicka in ett
annat objekt, ingen annan kod ändras.

### Paketstruktur

- `model/` -domänklasser (Pokemon, Attack, DamageAttack, TypeChart, BattleResult osv)
- `battle/` -stridslogik (BattleEngine, BattleLog, CpuStrategy, strategier)
- `service/` -statistik (StatisticsService)
- `ui/` -meny och inmatning
- `persistence/` -JSON via Jackson
- `exception/` -InvalidPokemonException, PokemonNotFoundException

Menyn läser input och delegerar -den innehåller ingen affärslogik.

## Projektstruktur

Allting ligger under `src/main/java/se/marcus/pokedex/`. Startpunkt är `Main.java`.

- **model/** -Pokemon, Attack (abstrakt), DamageAttack, ElementType, TypeChart, SeedData, PokemonCollection, BattleResult
- **battle/** -BattleEngine, BattleLog, CpuStrategy (interface), RandomStrategy, AggressiveStrategy
- **service/** -StatisticsService
- **ui/** -Menu, ConsoleInput
- **persistence/** -JsonStorage
- **exception/** -InvalidPokemonException, PokemonNotFoundException

Tester ligger i `src/test/java/se/marcus/pokedex/model/` -PokemonTest och TypeChartTest.
