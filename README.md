# gedaudit

**Deutsch** · [English](README.en.md)

<p align="center"><img src="gedaudit/icons/app.png" alt="gedaudit" width="96"></p>

gedaudit prüft eine GEDCOM-Datei auf Widersprüche und Auffälligkeiten: Chronologie, Altersgrenzen, Struktur, Namen, Quellen,
Orte und die Form der Datei. 77 Regeln, jede eine Frage mit Ja oder Nein als Antwort. Die Datei wird nur gelesen, nichts
verlässt den Rechner. Für Windows, Linux und macOS, Oberfläche auf Deutsch, Englisch, Französisch, Niederländisch und Spanisch.

Seite mit Handbuch: [gedaudit.bgg-home.de](https://gedaudit.bgg-home.de/de/) · Pakete: [Releases](https://github.com/thobgg/gedaudit/releases)

<p align="center"><img src="docs/screenshots/gedaudit-pruefung.jpg" alt="Prüfung des Demo-Baums Falkenrath" width="900"><br><b>Prüfung</b>: links die Regeln in Gruppen, rechts die Treffer, darunter die Person.</p>

## Was es kann

- Prüfen mit Voreinstellungen (ausgewogen, streng, großzügig, mit Quellen, Kirchenbuch), Grenzwerte und Schwere je Regel, Geburt und Tod aus Taufe und Begräbnis schätzen.
- Treffer abhaken; sie kommen erst wieder, wenn sich an der Stelle etwas ändert. Abhakliste sichern und laden.
- Person zum Treffer mit Ereignissen, Eltern, Partnern und Kindern; bei Paaren beide nebeneinander. Strg+C kopiert den Personentext.
- Zwei Dateien vergleichen: geändert, nur in der einen oder anderen, wahrscheinlich dieselbe Person.
- Aufbau der Datei: der Tag-Baum je Datensatzart, GEDCOM-L, Hersteller-Tags und Tags außerhalb des Standards markiert.
- GEDCOM-L-Addendum: Paten und Zeugen ohne Datensatz (`_GODP`, `_WITN`), GOV-Kennung, Postleitzahl und Maidenhead-Locator am Ort, Familienstand; Verweise auf Ortsdatensätze (`_LOC`), die ins Leere zeigen, werden gemeldet.
- Kopf der Datei (Programm, Einsender, Version, Dateiname, Datum) und die Regeln von GEDCOM 7; geprüft wird nach der Version im Kopf oder wahlweise als 5.5.1 oder 7.0.
- Drucken und PDF. GEDCOM 5.5, 5.5.1 und 7; Zeichensätze UTF-8, UTF-16, ANSEL, ANSI.

<p align="center"><img src="docs/screenshots/gedaudit-vergleich.jpg" alt="Zwei Stände des Demo-Baums im Vergleich" width="900"><br><b>Zwei Dateien vergleichen</b>: Falkenrath 1.3 gegen 1.6.</p>

## Verwandt

Die Regeln sind dieselben wie in der Plausibilitätsprüfung von [wtWin, wtTux und wtMac](https://wtwin.bgg-home.de/), dem Desktop-Programm
für webtrees. Der geteilte Kern liegt im Repository [app4webtrees](https://github.com/thobgg/app4webtrees) und ist hier als Git-Submodul
eingebunden; eine Regel, die dort besser wird, wird es auch hier.

## Selbst bauen

```bash
git clone --recursive https://github.com/thobgg/gedaudit.git
cd gedaudit
./gradlew :gedaudit:packageDeb        # Linux
./gradlew :gedaudit:packageInno       # Windows (Inno Setup 6)
./gradlew :gedaudit:packageDmg        # macOS
```

Der Build braucht ein JDK 21 und das Android-SDK (der geteilte Kern ist eine Multiplattform-Bibliothek). Die Pakete entstehen sonst
per GitHub Actions (`.github/workflows/pakete.yml`). Hilfetexte und Übersetzungen liegen im Submodul unter `shared/src/desktopMain/resources/hilfe/`
und `shared/src/commonMain/composeResources/`.

## Lizenz

[GPL-3.0](LICENSE). Der Demo-Stammbaum [Falkenrath](https://github.com/thobgg/falkenrath-demo-tree) auf den Bildern steht unter CC0.
