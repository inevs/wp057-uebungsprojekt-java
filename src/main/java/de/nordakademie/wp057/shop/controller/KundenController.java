package de.nordakademie.wp057.shop.controller;

import de.nordakademie.wp057.shop.model.Kunde;
import de.nordakademie.wp057.shop.service.KundenService;

import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * Bereitet Kundendaten für die Ausgabe auf.
 */
public class KundenController {

    private final KundenService kundenService;

    public KundenController(KundenService kundenService) {
        this.kundenService = kundenService;
    }

    public String zeigeKunde(long id) throws SQLException {
        return kundenService.findeKunde(id)
                .map(KundenController::formatiere)
                .orElse("Kein Kunde mit Nummer " + id);
    }

    public String zeigeAlleKunden() throws SQLException {
        return kundenService.alleKunden().stream()
                .map(KundenController::formatiere)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static String formatiere(Kunde kunde) {
        return kunde.id() + ": " + kunde.name() + " <" + kunde.email() + ">";
    }
}
