package de.nordakademie.wp057.shop.repository;

import de.nordakademie.wp057.shop.model.Bestellung;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lesender Zugriff auf die Tabelle {@code bestellungen}.
 */
public class BestellRepository {

    private final Datenbank datenbank;

    public BestellRepository(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    public List<Bestellung> findeNachKunde(long kundeId) throws SQLException {
        try (Connection conn = datenbank.verbinde();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT id, kunde_id, betrag FROM bestellungen WHERE kunde_id = ? ORDER BY id")) {
            stmt.setLong(1, kundeId);
            try (ResultSet rs = stmt.executeQuery()) {
                List<Bestellung> bestellungen = new ArrayList<>();
                while (rs.next()) {
                    bestellungen.add(new Bestellung(
                            rs.getLong("id"),
                            rs.getLong("kunde_id"),
                            BigDecimal.valueOf(rs.getDouble("betrag"))));
                }
                return bestellungen;
            }
        }
    }
}
