package org.example;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * Diese Klasse erbt von der Units Klasse und erstellt die GUI.
 * */
public class Main extends Units{
    private final JFrame mainFrame;
    private final Font textFont = new Font("Calibri", Font.PLAIN, 18);

    private String[] unitList;
    private JPanel controlPanel;

    /**
     * Initialisiert das Haupt-Frame und die Liste der Einheiten. Im Anschluss wird das Fenster
     * gestaltet und die Widgets erstellt
     * */
    public Main() {
        mainFrame = new JFrame("UnitConvert");
        unitList = lengthUnits;

        // Erstellt und gestaltet das Hauptfenster
        mainFrame.setSize(300,500);
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
        JPanel unitsPanel = new JPanel(layout);
        JComboBox<String> comboBox = getComboBox(index);
        Font titleFont = new Font("Calibri", Font.PLAIN, 20);

        // Erstellt und gestaltet so viele Widgets wie es Einheiten gibt
        for (String unit : unitList) {
            // Erstellt und gestaltet den Border
            TitledBorder border = new TitledBorder(unit);
            border.setTitleFont(titleFont);

            // Erstellt und gestaltet das Panel für den Text
            JPanel textPanel = new JPanel();
            textPanel.setBorder(border);
            textPanel.setPreferredSize(new Dimension(250,70));

            // Erstellt und gestaltet den Text
            JTextArea textArea = new JTextArea("0");
            textArea.setFont(textFont);
            textArea.setPreferredSize(new Dimension(230,20));

            textPanel.add(textArea);
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
        // Liste mit den Einheiten
        String[] selectionList = {"Längeneinheiten", "Flächeneinheiten"};
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
                        createWidgets(0);
                        break;
                    case "Flächeneinheiten":
                        unitList = areaUnits;
                        createWidgets(1);
                        break;
                }
            }
        });
        return comboBox;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}