# Changelog
Alle nennenswerten Änderungen an diesem Projekt werden in dieser Datei dokumentiert.


## [1.0.0] 2024-05-17
### Neue Funktionen
- Auswählen von verschiedenen Einheiten
- Automatische Umrechnung in andere Einheiten

### Added
- Grundlegende Funktionen der Anwendung implementiert
- Benutzeroberfläche und Design fertiggestellt

### Fixed
- Diverse Bugs und Fehler behoben


## Änderungen vor der Basisversion
## Inhaltsverzeichnis
- [2024-05-17](#2024-05-17)
    - [Refaktorierung](#refaktorierung)
    - [Main.java](#mainjava)
    - [Units.java](#unitsjava)
- [2024-05-17](#2024-05-17-1)
    - [Refaktorierung](#refaktorierung-1)
    - [Main.java](#mainjava-1)
    - [Units.java](#unitsjava-1)

### 2024-05-17
#### Refaktorierung
- Methoden überarbeitet in: Main.java, Units.java
- Neue Einheit hinzugefügt: Energieeinheiten
- Neue Datei hinzugefügt: README.md

#### Main.java
- Größe des hauptfenster angepasst
- createWidgets Methode verändert
  - TextArea zu TextField geändert
  - TextField zu Methode exportiert
- getComboBox Methode verändert
  - Liste mit den Namen der Einheiten nach Units.java verschoben
- Methode zur Erstellung vom TextField
- Methode zur Berechnung und Aufrundung

#### Units.java
- Liste mit den Namen der Einheiten hinzugefügt
- Flächeneinheiten durch Zeiteinheiten ersetzt
- "Energieeinheiten" hinzugefügt
- Konstruktor erstellt
- Methode zur Erstellung der Konvertierungsfaktoren für die Längeneinheiten erstellt
- Methode zur Erstellung der Konvertierungsfaktoren für die Zeiteinheiten erstellt
- Methode zur Erstellung der Konvertierungsfaktoren für die Energieeinheiten erstellt

### 2024-05-17
#### Refaktorierung
- GUI erstellung
- Neue Dateien hinzugefügt: Main.java, Units.java

#### Main.java
- Methode zur Initialisierung und Erstellung des Hauptfensters
- Methode zur Erstellung der Widgets im Hauptfenster
- Methode für die Erstellung der ComboBox

#### Units.java
- Listen für Längen- und Flächeneinheiten erstellt