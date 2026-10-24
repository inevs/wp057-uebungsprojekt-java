# WP057 Übungsprojekt: Shop

Kleiner Bestellservice mit Kunden, Bestellungen und Versandkosten. Grundlage
für die Übungen in Woche 3.

## Voraussetzungen

- JDK 21 oder neuer (`java -version`)
- Maven muss nicht installiert sein, der Maven Wrapper (`mvnw`, unter
  Windows `mvnw.cmd`) lädt es beim ersten Aufruf herunter.

## Bauen und prüfen

```sh
./mvnw verify
```

Führt nacheinander aus und bricht beim ersten Fehler ab:

1. Checkstyle (Regeln in `checkstyle.xml`)
2. Compiler mit `-Xlint:all -Werror` (jede Warnung ist ein Fehler)
3. Tests mit JUnit

Zusätzlich mit Sicherheitsprüfung (SpotBugs mit FindSecBugs):

```sh
./mvnw verify -Psicherheit
```

## Starten

```sh
./mvnw -q compile exec:java                      # alle Kunden
./mvnw -q compile exec:java -Dexec.args="2"      # Kunde mit Nummer 2
```

Beim ersten Start wird `shop.db` mit Beispieldaten im aktuellen Verzeichnis
angelegt.

## Aufbau

```
src/main/java/de/nordakademie/wp057/shop/
├── App.java          Kommandozeile
├── controller/       Aufbereitung für die Ausgabe, ruft Services auf
├── service/          Anwendungslogik
├── repository/       Datenbankzugriffe (SQLite über JDBC)
└── model/            Kunde, Bestellung
```

Tests liegen unter `src/test/java/` und verwenden je Test eine eigene
Datenbank in einem temporären Verzeichnis (`TestDatenbank`).
