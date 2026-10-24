package de.nordakademie.wp057.shop.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.nordakademie.wp057.shop.repository.KundenRepository;
import de.nordakademie.wp057.shop.repository.TestDatenbank;
import de.nordakademie.wp057.shop.service.KundenService;

import java.nio.file.Path;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class KundenControllerTest {

    @TempDir
    Path verzeichnis;

    private KundenController controller;

    @BeforeEach
    void setUp() throws SQLException {
        controller = new KundenController(
                new KundenService(new KundenRepository(TestDatenbank.mitBeispieldaten(verzeichnis))));
    }

    @Test
    void zeigtBekanntenKunden() throws SQLException {
        assertEquals("2: Ben <ben@example.org>", controller.zeigeKunde(2));
    }

    @Test
    void meldetUnbekanntenKunden() throws SQLException {
        assertEquals("Kein Kunde mit Nummer 99", controller.zeigeKunde(99));
    }
}
