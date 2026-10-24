package de.nordakademie.wp057.shop.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Auswertungen über die Bestellungen.
 */
public class StatistikRepository {

    private final Datenbank datenbank;

    public StatistikRepository(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    /**
     * Liefert den durchschnittlichen Bestellwert eines Kunden; hat der Kunde
     * keine Bestellungen, ist das Ergebnis 0.
     */
    public double durchschnittlicherBestellwert(String kunde)
            throws SQLException {
        Connection conn = datenbank.verbinde();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT b.betrag FROM bestellungen b"
                + " JOIN kunden k ON k.id = b.kunde_id"
                + " WHERE k.name = '" + kunde + "'");
        List<Double> betraege = new ArrayList<>();
        while (rs.next()) {
            betraege.add(rs.getDouble("betrag"));
        }
        double summe = 0;
        for (int i = 1; i < betraege.size(); i++) {
            summe += betraege.get(i);
        }
        return summe / betraege.size();
    }
}
