package de.nordakademie.wp057.shop.repository;

import java.nio.file.Path;
import java.sql.SQLException;

/**
 * Erzeugt für einen Test eine frische Datenbank mit Beispieldaten.
 */
public final class TestDatenbank {

    private TestDatenbank() {
    }

    public static Datenbank mitBeispieldaten(Path verzeichnis) throws SQLException {
        Datenbank datenbank = new Datenbank(verzeichnis.resolve("test.db"));
        datenbank.erzeugeTabellen();
        datenbank.fuegeBeispieldatenEin();
        return datenbank;
    }
}
