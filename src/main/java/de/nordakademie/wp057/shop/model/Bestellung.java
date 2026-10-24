package de.nordakademie.wp057.shop.model;

import java.math.BigDecimal;

/**
 * Eine Bestellung eines Kunden.
 *
 * @param id      eindeutige Bestellnummer
 * @param kundeId Kundennummer des bestellenden Kunden
 * @param betrag  Warenwert der Bestellung in Euro
 */
public record Bestellung(long id, long kundeId, BigDecimal betrag) {
}
