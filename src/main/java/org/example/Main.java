package org.example;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

/**
 * Diese Klasse erbt von der Units Klasse und erstellt die GUI.
 * */
public class Main extends Units{
    private final JFrame mainFrame;
    private final Font textFont = new Font("Calibri", Font.PLAIN, 18);

    private String[] unitList;
    private List<double[]> unitConvert;
    private JPanel controlPanel;
    private JPanel unitsPanel;

    /**
     * Initialisiert das Haupt-Frame und die Liste der Einheiten. Im Anschluss wird das Fenster
     * gestaltet und die Widgets erstellt
     * */
    public Main() {
        mainFrame = new JFrame("UnitConvert");
        unitList = lengthUnits;
        unitConvert = lengthConvert;

        // Erstellt und gestaltet das Hauptfenster
        mainFrame.setSize(300,800);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(0,1));

        createWidgets(0);

        mainFrame.setVisible(true);
    }

    /**
     * In dieser Methode werden die Widgets erstellt.
     *
     * @param index Der index gibt an welches Element in der ComboBox ausgewählt ist.
     * */
    private void createWidgets(int index) {
        // Überprüft das controlPanel, falls es nicht null ist, dann wird es gelöscht.
        // Dies ist notwendig damit, nach dem Auswählen eines anderen Elements in der ComboBox,
        // die Widgets erstellt werden können.
        if (controlPanel != null) {
            mainFrame.remove(controlPanel);
        }

        GridLayout layout = new GridLayout(0,1, 10,10);
        controlPanel = new JPanel(new FlowLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JPanel comboBoxPanel = new JPanel(new FlowLayout());
        unitsPanel = new JPanel(layout);
        JComboBox<String> comboBox = getComboBox(index);
        Font titleFont = new Font("Calibri", Font.PLAIN, 20);

        // Erstellt und gestaltet so viele Widgets wie es Einheiten gibt
        for (int i = 0; i < unitList.length; i++) {
            String unit = unitList[i];
            // Erstellt und gestaltet den Border
            TitledBorder border = new TitledBorder(unit);
            border.setTitleFont(titleFont);

            // Erstellt und gestaltet das Panel für den Text
            JPanel textPanel = new JPanel();
            textPanel.setBorder(border);
            textPanel.setPreferredSize(new Dimension(250,70));

            // Erstellt und gestaltet den Text
            JTextField textField = getTextField(i);

            textPanel.add(textField);
            unitsPanel.add(textPanel);
        }

        // Fügt alle Panel ineinander und lädt das Haupt-Frame neu
        comboBoxPanel.add(comboBox);
        panel.add(comboBoxPanel, BorderLayout.NORTH);
        panel.add(unitsPanel, BorderLayout.SOUTH);
        controlPanel.add(panel);
        mainFrame.add(controlPanel);
        mainFrame.revalidate();
    }

    /**
     * Erstellt und gestaltet eine ComboBox und gibt diese wieder.
     *
     * @param index Der index gibt an welches Element in der ComboBox ausgewählt ist.
     * @return Gibt die ComboBox aus
     * */
    private JComboBox<String> getComboBox(int index) {
        // Erstellt und gestaltet die ComboBox
        JComboBox<String> comboBox = new JComboBox<>(selectionList);
        comboBox.setSelectedIndex(index);
        comboBox.setFont(textFont);
        comboBox.addItemListener(e -> {
            String selectedItem = (String) comboBox.getSelectedItem();

            // Wenn das Element geändert wurde, wird die Liste mit den Einheiten angepasst
            // und die Widgets erneut erstellt
            if (e.getStateChange() == ItemEvent.SELECTED && selectedItem != null) {
                switch (selectedItem) {
                    case "Längeneinheiten":
                        unitList = lengthUnits;
                        unitConvert = lengthConvert;
                        createWidgets(0);
                        break;
                    case "Zeiteinheiten":
                        unitList = timeUnits;
                        unitConvert = timeConvert;
                        createWidgets(1);
                        break;
                    case "Energieeinheiten":
                        unitList = energyUnits;
                        unitConvert = energyConvert;
                        createWidgets(2);
                        break;
                }
            }
        });
        return comboBox;
    }

    /**
     * Erstellt das TextField für die Einheiten.
     *
     * @param index Der index gibt an Wert für die Berechnung benutzt werden soll.
     * @return Gibt das TextField aus.
     * */
    private JTextField getTextField(int index) {
        // Erstellt und gestaltet das TextField
        JTextField textField = new JTextField();
        textField.setFont(textFont);
        textField.setPreferredSize(new Dimension(230,20));
        textField.addKeyListener(new KeyAdapter() {
            // Pattern um sicherzustellen das nur Zahlen, Punkt und wenn gelöscht wird,
            // akzeptiert wird
            final Pattern pattern = Pattern.compile("^[0-9.\b]+$");

            @Override
            public void keyTyped(KeyEvent ke) {
                // Bestimmt das letzte eingegebene Zeichen und gleicht das mit dem Pattern ab
                char c = ke.getKeyChar();
                Matcher matcher = pattern.matcher(String.valueOf(c));

                // Wenn die Eingabe nicht im Pattern ist, dann wird nichts eingegeben
                if (!matcher.matches()) {
                    ke.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                // Bestimmt das letzte eingegebene Zeichen und gleicht das mit dem Pattern ab
                char c = ke.getKeyChar();
                Matcher matcher = pattern.matcher(String.valueOf(c));
                String inputText = textField.getText();

                // Wenn die Eingabe im gültigen Bereich liegt, dann werden die TextFields angepasst
                if (matcher.matches()) {
                    double value = 0;

                    // Wenn die komplette Eingabe gelöscht wurde, dann soll 0 angezeigt werden.
                    // Wenn der eingegebene Text nicht leer ist, dann wird dieser konvertiert
                    if (!inputText.isEmpty()) {
                        value = Double.parseDouble(inputText);
                    }

                    // Berechnet und aktualisiert für jede Einheit, außer für die eingegebene,
                    // den Wert
                    for (int i = 0; i < unitsPanel.getComponentCount(); i++) {
                        // Es werden nur in den Feldern Berechnungen durchgeführt, in denen keine
                        // Eingabe stattfindet
                        if (i != index) {
                            // Das Panel mit der Einheit werden bestimmt
                            JPanel textPanel = (JPanel) unitsPanel.getComponent(i);
                            JTextField unitField = (JTextField) textPanel.getComponent(0);
                            // Der neue Wert wird mit dem Wert aus den zu konvertierenden Werten
                            // berechnet und dann auf 8 Stellen nach dem Komma gerundet
                            double convertValue = unitConvert.get(index)[i];
                            double roundedValue = roundedValue(value, convertValue);

                            // Das Feld wird mit dem neuen Wert aktualisiert
                            unitField.setText(String.valueOf(roundedValue));
                        }
                    }
                }
            }
        });

        return textField;
    }

    /**
     * In dieser Methode wird der neue Wert der Einheit berechnet und auf 8 Stellen nach dem Komma
     * gerundet.
     *
     * @param value Der eingegebene Wert.
     * @param convertValue Der Wert mit dem gerechnet werden muss.
     *
     * @return Gibt den angepassten Wert der Einheit aus.
     * */
    private double roundedValue(double value, double convertValue) {
        double factor = 10e7;
        double newValue = value * convertValue;

        return Math.round(newValue * factor) / factor;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}