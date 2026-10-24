package de.nordakademie.wp057.shop.service;

import java.math.BigDecimal;

/**
 * Versandkosten einer Bestellung.
 */
public final class Versandkosten {

    private static final BigDecimal GRENZE_KOSTENLOS = new BigDecimal("50.00");
    private static final BigDecimal STANDARD = new BigDecimal("4.99");

    private Versandkosten() {
    }

    /**
     * Ab 50,00 € Warenwert (einschließlich) ist der Versand kostenlos,
     * darunter kostet er 4,99 €. Ein fehlender oder nicht positiver
     * Warenwert ist ein Fehler.
     */
    public static BigDecimal berechne(BigDecimal warenwert) {
        if (warenwert == null || warenwert.signum() <= 0) {
            throw new IllegalArgumentException("Warenwert muss größer als 0 sein");
        }
        return warenwert.compareTo(GRENZE_KOSTENLOS) >= 0 ? BigDecimal.ZERO : STANDARD;
    }
}
