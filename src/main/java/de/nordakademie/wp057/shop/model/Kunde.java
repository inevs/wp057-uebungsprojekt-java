package de.nordakademie.wp057.shop.model;

/**
 * Ein Kunde des Shops.
 *
 * @param id    eindeutige Kundennummer
 * @param name  Name des Kunden
 * @param email E-Mail-Adresse
 */
public record Kunde(long id, String name, String email) {
}
