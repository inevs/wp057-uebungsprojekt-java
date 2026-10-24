package de.nordakademie.wp057.shop.service;

import de.nordakademie.wp057.shop.model.Kunde;
import de.nordakademie.wp057.shop.repository.KundenRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Anwendungslogik rund um Kunden.
 */
public class KundenService {

    private final KundenRepository kundenRepository;

    public KundenService(KundenRepository kundenRepository) {
        this.kundenRepository = kundenRepository;
    }

    public Optional<Kunde> findeKunde(long id) throws SQLException {
        return kundenRepository.findeNachId(id);
    }

    public List<Kunde> alleKunden() throws SQLException {
        return kundenRepository.findeAlle();
    }
}
