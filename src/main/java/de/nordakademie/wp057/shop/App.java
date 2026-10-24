package de.nordakademie.wp057.shop;

import de.nordakademie.wp057.shop.controller.KundenController;
import de.nordakademie.wp057.shop.repository.Datenbank;
import de.nordakademie.wp057.shop.repository.KundenRepository;
import de.nordakademie.wp057.shop.service.KundenService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

/**
 * Kommandozeile des Shops.
 *
 * <p>Ohne Argument werden alle Kunden ausgegeben, mit einer Kundennummer als
 * Argument nur dieser Kunde. Die Datenbank {@code shop.db} wird beim ersten
 * Start im aktuellen Verzeichnis angelegt.
 */
public final class App {

    private App() {
    }

    public static void main(String[] args) throws SQLException {
        Path datei = Path.of("shop.db");
        boolean neu = !Files.exists(datei);
        Datenbank datenbank = new Datenbank(datei);
        if (neu) {
            datenbank.erzeugeTabellen();
            datenbank.fuegeBeispieldatenEin();
        }

        KundenController controller = new KundenController(
                new KundenService(new KundenRepository(datenbank)));
        if (args.length == 0) {
            System.out.println(controller.zeigeAlleKunden());
        } else {
            System.out.println(controller.zeigeKunde(Long.parseLong(args[0])));
        }
    }
}
