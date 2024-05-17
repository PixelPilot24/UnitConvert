package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse mit den Einheiten die umgerechnet werden.
 * */
public abstract class Units {
    // Liste mit den Einheiten
    String[] selectionList = {"Längeneinheiten", "Zeiteinheiten", "Energieeinheiten"};

    // Listen mit den Namen der Einheiten
    protected final String[] lengthUnits = {
            "Nanometer (nm)", "Mikrometer (µm)", "Millimeter (mm)", "Zentimeter (cm)",
            "Dezimeter (dm)", "Meter (m)", "Kilometer (km)", "Fuß (ft)", "Meile (mi)"
    };
    protected final String[] timeUnits = {
            "Millisekunde (ms)", "Sekunde (s)", "Minute (min)", "Stunde (h)", "Tag (d)", "Woche (w)",
            "Jahr (a)"
    };
    protected final String[] energyUnits = {
            "Joule (J)", "Kilojoule (kJ)", "Kilokalorien (kcal)", "Wattstunden (Wh)",
            "Kilowattstunden (kWh)", "Megawattstunden (MWh)", "Gigawattstunden (GWh)", "Terawattstunden (TWh)"
    };

    // Listen zum Speichern der Umrechnungsfaktoren für jeden Einheiten Typ
    protected final List<double[]> lengthConvert = new ArrayList<>();
    protected final List<double[]> timeConvert = new ArrayList<>();
    protected final List<double[]> energyConvert = new ArrayList<>();

    /**
     * Initialisiert den Konvertierungsfaktor für die Listen.
     * */
    public Units() {
        addLength();
        addTime();
        addEnergy();
    }

    /**
     * Erstellt und fügt die Konvertierungsfaktoren der lengthConvert Liste hinzu.
     * */
    private void addLength() {
        double[] nm = {1, 0.001, 0.000001, 10e-7, 10e-8, 10e-9, 10e-13, 3.3 * 10e-9, 6.2 * 10e-13};
        double[] mym = {1000, 1, 0.001, 0.0001, 0.00001, 0.000001, 10e-9, 0.00000328, 6.2 * 10e-10};
        double[] mm = {10e6, 1000, 1, 0.1, 0.01, 0.001, 0.000001, 0.00328084, 6.2 * 10e-7};
        double[] cm = {10e7, 10000, 10, 1, 0.1, 0.01, 0.00001, 0.0328084, 0.00000621};
        double[] dm = {10e8, 100000, 100, 10, 1, 0.1, 0.0001, 0.328084, 0.000062137};
        double[] m = {10e9, 10e6, 1000, 100, 10, 1, 0.001, 3.28084, 0.00062137};
        double[] km = {10e12, 10e9, 10e6, 100000, 10000, 1000, 1, 3280.84, 0.621371};
        double[] ft = {3.048 * 10e8, 304800, 304,8, 30.48, 3.048, 0.3048, 0.0003048, 1, 0.00018939};
        double[] mi = {
                1.609 * 10e12, 1.609 * 10e9, 1.609 * 10e6, 160934.4, 16093.44, 1609.344, 1.609344,
                5280 , 1
        };
        lengthConvert.add(nm);
        lengthConvert.add(mym);
        lengthConvert.add(mm);
        lengthConvert.add(cm);
        lengthConvert.add(dm);
        lengthConvert.add(m);
        lengthConvert.add(km);
        lengthConvert.add(ft);
        lengthConvert.add(mi);
    }

    /**
     * Erstellt und fügt die Konvertierungsfaktoren der timeConvert Liste hinzu.
     * */
    private void addTime() {
        double[] ms = {1, 0.001, 0.00002, 2.8 * 10e-7, 1.1574 * 10e-8, 1.6534 * 10e-9, 3.171 * 10e-11};
        double[] s = {1000, 1, 0.01667, 0.00028, 0.00001, 1.6534 * 10e-6, 3.171 * 10e-8};
        double[] min = {60000, 60, 1, 0.01667, 0.00069, 0.0001, 1.9026 * 10e-6};
        double[] h = {3.6 * 10e6, 3600, 60, 1, 0.04167, 0.00595, 0.00011};
        double[] d = {8.64 * 10e7, 86400, 1440, 24, 1, 0.14286, 0.00274};
        double[] w = {6.048 * 10e8, 604800.00016, 10080, 168, 7, 1, 0.01918};
        double[] a = {3.1536 * 10e10, 3.1536 * 10e7, 525599, 99973, 8760, 365, 52.14286, 1};

        timeConvert.add(ms);
        timeConvert.add(s);
        timeConvert.add(min);
        timeConvert.add(h);
        timeConvert.add(d);
        timeConvert.add(w);
        timeConvert.add(a);
    }

    /**
     * Erstellt und fügt die Konvertierungsfaktoren der energyConvert Liste hinzu.
     * */
    private void addEnergy() {
        double[] joule = {
            1, 0.001, 2.3901 * 10e-4, 2.778 * 10e-4, 2.778 * 10e-7, 2.778 * 10e-10, 2.778 * 10e-13,
            2.778 * 10e-17
        };
        double[] kJ = {
                1000, 1, 0.2390057, 2.77777778, 2.778 * 10e-4, 2.778 * 10e-7, 2.778 * 10e-10,
                2.778 * 10e-13
        };
        double[] kcal = {
                4186.8, 4.1868, 1, 1.163, 1.163 * 10e-3, 1.163 * 10e-6, 1.163 * 10e-9, 1.163 * 10e-12
        };
        double[] wh = {3600, 3.6, 0.85984523, 1, 0.001, 10e-6, 10e-9, 10e-12};
        double[] kWh = {3.6 * 10e6, 3600, 859.84522785, 1000, 1, 0.001, 10e-6, 10e-9};
        double[] mWh = {3.6 * 10e9, 3.6 * 10e6, 8.5985 * 10e5, 10e6, 1000, 1, 0.001, 10e-6};
        double[] gWh = {3.6 * 10e12, 3.6 * 10e9, 8.5985 * 10e8, 10e9, 10e6, 1000, 1, 1000};
        double[] tWh = {3.6 * 10e15, 3.6 * 10e12, 8.5985 * 10e11, 10e12, 10e9, 10e6, 1000, 1};

        energyConvert.add(joule);
        energyConvert.add(kJ);
        energyConvert.add(kcal);
        energyConvert.add(wh);
        energyConvert.add(kWh);
        energyConvert.add(mWh);
        energyConvert.add(gWh);
        energyConvert.add(tWh);
    }
}
