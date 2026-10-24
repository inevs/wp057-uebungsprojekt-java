package de.nordakademie.wp057.shop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.nordakademie.wp057.shop.model.Bestellung;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class BestellRepositoryTest {

    @TempDir
    Path verzeichnis;

    private BestellRepository repository;

    @BeforeEach
    void setUp() throws SQLException {
        repository = new BestellRepository(TestDatenbank.mitBeispieldaten(verzeichnis));
    }

    @Test
    void findetBestellungenEinesKunden() throws SQLException {
        List<Long> ids = repository.findeNachKunde(2).stream().map(Bestellung::id).toList();
        assertEquals(List.of(3L, 4L, 5L), ids);
    }

    @Test
    void kundeOhneBestellungenLiefertLeereListe() throws SQLException {
        assertTrue(repository.findeNachKunde(3).isEmpty());
    }
}
