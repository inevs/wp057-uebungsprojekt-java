package de.nordakademie.wp057.shop.repository;

import de.nordakademie.wp057.shop.model.Kunde;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Lesender Zugriff auf die Tabelle {@code kunden}.
 */
public class KundenRepository {

    private final Datenbank datenbank;

    public KundenRepository(Datenbank datenbank) {
        this.datenbank = datenbank;
    }

    public Optional<Kunde> findeNachId(long id) throws SQLException {
        try (Connection conn = datenbank.verbinde();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT id, name, email FROM kunden WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? Optional.of(zuKunde(rs)) : Optional.empty();
            }
        }
    }

    public List<Kunde> findeAlle() throws SQLException {
        try (Connection conn = datenbank.verbinde();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT id, name, email FROM kunden ORDER BY id");
             ResultSet rs = stmt.executeQuery()) {
            List<Kunde> kunden = new ArrayList<>();
            while (rs.next()) {
                kunden.add(zuKunde(rs));
            }
            return kunden;
        }
    }

    private static Kunde zuKunde(ResultSet rs) throws SQLException {
        return new Kunde(rs.getLong("id"), rs.getString("name"), rs.getString("email"));
    }
}
