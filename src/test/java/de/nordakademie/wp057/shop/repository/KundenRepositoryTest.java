package de.nordakademie.wp057.shop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.nordakademie.wp057.shop.model.Kunde;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class KundenRepositoryTest {

    @TempDir
    Path verzeichnis;

    private KundenRepository repository;

    @BeforeEach
    void setUp() throws SQLException {
        repository = new KundenRepository(TestDatenbank.mitBeispieldaten(verzeichnis));
    }

    @Test
    void findetKundenNachId() throws SQLException {
        assertEquals(Optional.of(new Kunde(1, "Anna", "anna@example.org")), repository.findeNachId(1));
    }

    @Test
    void unbekannteIdLiefertLeeresErgebnis() throws SQLException {
        assertTrue(repository.findeNachId(99).isEmpty());
    }

    @Test
    void findetAlleKundenSortiertNachId() throws SQLException {
        List<Long> ids = repository.findeAlle().stream().map(Kunde::id).toList();
        assertEquals(List.of(1L, 2L, 3L), ids);
    }
}
