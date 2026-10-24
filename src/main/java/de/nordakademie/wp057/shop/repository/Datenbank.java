package de.nordakademie.wp057.shop.repository;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Zugang zur SQLite-Datenbank des Shops.
 */
public class Datenbank {

    private static final String TABELLE_KUNDEN = """
            CREATE TABLE IF NOT EXISTS kunden (
                id    INTEGER PRIMARY KEY,
                name  TEXT NOT NULL,
                email TEXT NOT NULL
            )""";

    private static final String TABELLE_BESTELLUNGEN = """
            CREATE TABLE IF NOT EXISTS bestellungen (
                id       INTEGER PRIMARY KEY,
                kunde_id INTEGER NOT NULL REFERENCES kunden(id),
                betrag   REAL NOT NULL
            )""";

    private final String url;

    public Datenbank(Path datei) {
        this.url = "jdbc:sqlite:" + datei;
    }

    public Connection verbinde() throws SQLException {
        return DriverManager.getConnection(url);
    }

    /**
     * Legt die Tabellen an, falls sie noch nicht existieren.
     */
    public void erzeugeTabellen() throws SQLException {
        try (Connection conn = verbinde(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(TABELLE_KUNDEN);
            stmt.executeUpdate(TABELLE_BESTELLUNGEN);
        }
    }

    /**
     * Füllt eine leere Datenbank mit Beispieldaten.
     */
    public void fuegeBeispieldatenEin() throws SQLException {
        try (Connection conn = verbinde()) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO kunden (id, name, email) VALUES (?, ?, ?)")) {
                fuegeKundeEin(stmt, 1, "Anna", "anna@example.org");
                fuegeKundeEin(stmt, 2, "Ben", "ben@example.org");
                fuegeKundeEin(stmt, 3, "Clara", "clara@example.org");
            }
            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO bestellungen (id, kunde_id, betrag) VALUES (?, ?, ?)")) {
                fuegeBestellungEin(stmt, 1, 1, 10.0);
                fuegeBestellungEin(stmt, 2, 1, 20.0);
                fuegeBestellungEin(stmt, 3, 2, 45.5);
                fuegeBestellungEin(stmt, 4, 2, 120.0);
                fuegeBestellungEin(stmt, 5, 2, 64.5);
            }
        }
    }

    private static void fuegeKundeEin(PreparedStatement stmt, long id, String name, String email)
            throws SQLException {
        stmt.setLong(1, id);
        stmt.setString(2, name);
        stmt.setString(3, email);
        stmt.executeUpdate();
    }

    private static void fuegeBestellungEin(PreparedStatement stmt, long id, long kundeId, double betrag)
            throws SQLException {
        stmt.setLong(1, id);
        stmt.setLong(2, kundeId);
        stmt.setDouble(3, betrag);
        stmt.executeUpdate();
    }
}
