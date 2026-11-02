# Warum gedaudit ohne Sprachmodell prüft

Dossier zum Vortest vom 21.09.2026, eingestellt am 13.10.2026. Es hält fest, was gemessen wurde und warum
die Prüfung mit einem Sprachmodell nicht weiterverfolgt wurde. Rohdaten und Modellantworten sind nicht
enthalten, weil sie Personendaten aus einem Ortsfamilienbuch tragen.

## Ausgangsfrage

Die erste Planung sah für gedaudit vier Prüfstufen vor:

| Stufe | Klasse | Beispiel | Werkzeug |
|--:|---|---|---|
| 1 | Form | kein gültiges GEDCOM, Verweis ins Leere | Parser |
| 2 | unmöglich | Tod vor Geburt, Kind in zwei Familien | feste Regeln |
| 3 | unbestätigt | Vorname passt nicht zum Geschlecht | Namensliste oder Sprachmodell |
| 4 | unwahrscheinlich | Telegraphist 1720, Mutter als Grenadier, Kind in der falschen von zwei gleichnamigen Ehen | nur ein Sprachmodell |

Die Stufen 1 und 2 haben andere Programme auch. Neu wären 3 und 4 gewesen. Bedingung: Das Modell läuft
lokal (PC, NAS, Vereinsrechner), weil Genealogen ihre Daten nicht an KI-Anbieter geben. Das Modell
sollte nur melden, nie korrigieren.

## Aufbau

Datengrundlage: ein Ortsfamilienbuch (Schorndorf) als SQLite-Datenbank, nur lesend.
Der Maßstab war vor jeder Messung festgelegt und wurde danach nicht geändert. Sollwerte entstanden durch
Konstruktion: In saubere Familien (ohne Treffer der Regelprüfung) wurde je genau ein Fehler eingebaut,
die andere Hälfte blieb als Kontrolle unverändert.

| Arm | Frage | Karten |
|---|---|---|
| A | Erklärt das Modell, warum eine Regel anschlägt (Zweitehe, Lesefehler, echt)? | 40 Treffer der Regelprüfung mit Artikeltext |
| C | Findet das Modell Unwahrscheinliches mit Weltwissen? | 30 Familien, 15 mit eingebautem Fehler (Beruf vor seiner Zeit, Vorname gegen Geschlecht, Mutter als Soldat, fernes Geburtsland, Zwillingsnotiz mit anderem Jahr, katholischer Rufname) |
| E | Erkennt das Modell ein Kind in der falschen von zwei gleichnamigen Ehen (Paten, Beruf, Ort, Namen)? | 30 Paare, 15 mit einem verschobenen Kind |

Gemessen: **Entdeckung** (eingebauter Fehler gemeldet), **Fehlalarm** (Kontrollkarte mit Meldung),
**Präzision** (richtige Meldungen geteilt durch alle Meldungen). Ollama, Temperatur 0, erzwungenes
JSON-Schema, ein Prompt für alle Modelle. Läufe auf dem eigenen PC (12 Kerne, keine Grafikkarte, 3B bis
8B) und auf einer gemieteten A100 mit 80 GB (4B bis 70B).

## Ergebnisse

**Arm C, Weltwissen, PC-Lauf:**

| Modell | Entdeckung | Fehlalarm-Karten | Meldungen | Präzision | s/Karte |
|---|--:|--:|--:|--:|--:|
| qwen2.5:3b | 2/15 | 15/15 | 36 | 6 % | 16 |
| qwen3:4b | 0/15 | 1/15 | 3 | 0 % | 15 |
| gemma3:4b | 6/15 | 15/15 | 36 | 17 % | 28 |
| qwen3:8b | 11/15 | 14/15 | 74 | 15 % | 101 |

Entweder schweigt das Modell (qwen3:4b fand nicht einmal den Telegraphisten von 1780), oder es meldet auf
fast jeder sauberen Familie etwas, meist mit erfundenem Geschichtswissen („Maurer in Schorndorf im
18. Jh. unwahrscheinlich", „Geburtsaufzeichnungen erst ab dem frühen 18. Jh."). Wer die Liste von
qwen3:8b sichtet, prüft 74 Meldungen, um 11 richtige zu finden. Die Antworten der größeren Modelle aus
diesem Arm gingen beim Abbruch der Mietmaschine verloren.

**Arm E, Fehlzuordnung, Mietmaschine:**

| Modell | Entdeckung | Fehlalarm-Karten | Meldungen | Präzision | Speicher |
|---|--:|--:|--:|--:|--:|
| qwen3:4b | 1/15 | 15/15 | 52 | 2 % | 3 GB |
| qwen3:8b | 5/15 | 15/15 | 57 | 9 % | 5 GB |
| gemma3:12b | 6/15 | 15/15 | 57 | 11 % | 8 GB |
| qwen3:14b | 7/15 | 15/15 | 56 | 12 % | 9 GB |
| mistral-small3.2 (24B) | 7/15 | 15/15 | 35 | 20 % | 15 GB |
| gemma3:27b | 4/15 | 15/15 | 47 | 9 % | 17 GB |
| qwen3:30b-a3b | 2/15 | 10/15 | 29 | 7 % | 19 GB |
| qwen3:32b | 10/15 | 11/15 | 27 | 37 % | 20 GB |
| gpt-oss:20b (mit Denkschritt) | 8/15 | 2/15 | 13 | 62 % | 13 GB |
| llama3.3:70b | 14/15 | 5/15 | 23 | 61 % | 43 GB |

Bis 27B melden alle Modelle auf jeder Kontrollkarte und fast immer in derselben der beiden Familien,
unabhängig vom Inhalt. Die vorab notierte Schwelle (Präzision über 50 %, Entdeckung mindestens 8 von 15)
erreichen nur llama3.3:70b, das auf keinem Vereinsrechner läuft, und gpt-oss:20b, das eine Grafikkarte
mit 16 GB oder mehr braucht und auch dann jede zweite Fehlzuordnung übersieht.

**Arm A:** Die kleinen Modelle (3B, 4B) erfanden rund zwei Drittel ihrer Belegzitate; qwen3:4b und 8B
zitierten überwiegend wörtlich. Eine Bewertung der Kategorien gegen einen Sollwert fand nicht statt.

## Entscheidung (13.10.2026)

Nicht weiterverfolgt, aus diesen Gründen:

1. Auf der Hardware, die Vereine und Familienforscher haben (PC ohne Grafikkarte, NAS), bringt kein
   Modell einen Nutzen: zu viele Fehlalarme, erfundene Begründungen, oder gar keine Meldungen.
2. Brauchbar wurde es erst ab 70B oder ab 20B mit Denkschritt auf eigener Grafikkarte. Selbst dann sind
   fast vier von zehn Meldungen falsch, und die Prüfung dauert je Familie Sekunden statt
   Millisekunden.
3. Die Stufen 1 und 2 leisten feste Regeln vollständig und ohne Modell. Sie sind nachvollziehbar,
   schnell und liefern bei gleicher Datei immer dasselbe Ergebnis.

gedaudit prüft deshalb nur mit Regeln, deren Antwort Ja oder Nein ist.

## Offen geblieben

- Antworten der Modelle ab 14B in den Armen A und C (verloren).
- Gegenprobe qwen3:14b mit Denkschritt in Arm E.
- Arm D: geschlossene Fragen je Kind („passt Kind 4 zu Familie 1? ja, nein, unsicher") mit
  Vorsortierung durch Regeln. Das hätte kleinere Modelle eventuell tragfähig gemacht, wurde aber nicht
  gebaut.

Wer den Ansatz wieder aufnimmt, sollte bei Arm D beginnen und die Messung auf aktuellen Modellen
wiederholen; die Zahlen oben gelten für den Stand September 2026.
