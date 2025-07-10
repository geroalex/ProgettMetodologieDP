package it.unicam.cs.mpgc.jbudget.valori;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImportoTest {

    /**
     * Test of the `aggiungi` method in the `Importo` class,
     * which is used to add another `Importo` instance to the current one.
     */

    @Test
    void testAggiungi_WithPositiveValues() {
        Importo importo1 = new Importo(5, (short) 50);
        Importo importo2 = new Importo(3, (short) 75);

        importo1.aggiungi(importo2);

        assertEquals(9, importo1.getValoreIntero());
        assertEquals(25, importo1.getValoreDecimale());
    }

    @Test
    void testAggiungi_WithCarryOverToNextInteger() {
        Importo importo1 = new Importo(5, (short) 95);
        Importo importo2 = new Importo(3, (short) 10);

        importo1.aggiungi(importo2);

        assertEquals(9, importo1.getValoreIntero());
        assertEquals(5, importo1.getValoreDecimale());
    }

    @Test
    void testAggiungi_WithNullImporto() {
        Importo importo1 = new Importo(5, (short) 50);

        importo1.aggiungi((Importo) null);

        assertEquals(5, importo1.getValoreIntero());
        assertEquals(50, importo1.getValoreDecimale());
    }

    @Test
    void testAggiungi_WithZeroValues() {
        Importo importo1 = new Importo(5, (short) 50);
        Importo importo2 = new Importo(0, (short) 0);

        importo1.aggiungi(importo2);

        assertEquals(5, importo1.getValoreIntero());
        assertEquals(50, importo1.getValoreDecimale());
    }

    @Test
    void testAggiungi_WithDoubleValue() {
        Importo importo1 = new Importo(5, (short) 50);
        double valore = 3.75;

        importo1.aggiungi(valore);

        assertEquals(9, importo1.getValoreIntero());
        assertEquals(25, importo1.getValoreDecimale());
    }

    @Test
    void testAggiungi_WithDoubleCarryOver() {
        Importo importo1 = new Importo(5, (short) 95);
        double valore = 0.10;

        importo1.aggiungi(valore);

        assertEquals(6, importo1.getValoreIntero());
        assertEquals(5, importo1.getValoreDecimale());
    }

    @Test
    void testAggiungi_WithNegativeDoubleThrowsException() {
        Importo importo1 = new Importo(5, (short) 50);

        assertThrows(IllegalArgumentException.class, () -> importo1.aggiungi(-3.75));
    }

    @Test
    void testAggiungi_WithZeroDoubleValue() {
        Importo importo1 = new Importo(5, (short) 50);

        importo1.aggiungi(0.0);

        assertEquals(5, importo1.getValoreIntero());
        assertEquals(50, importo1.getValoreDecimale());
    }

    @Test
    void testAggiungi_WithPositiveIntegers() {
        Importo importo1 = new Importo(5, (short) 50);

        importo1.aggiungi(3, (short) 75);

        assertEquals(9, importo1.getValoreIntero());
        assertEquals(25, importo1.getValoreDecimale());
    }

    @Test
    void testAggiungi_WithCarryOverFromIntegerAddition() {
        Importo importo1 = new Importo(5, (short) 95);

        importo1.aggiungi(3, (short) 10);

        assertEquals(9, importo1.getValoreIntero());
        assertEquals(5, importo1.getValoreDecimale());
    }

    @Test
    void testAggiungi_WithIllegalArgumentExceptionForNegativeValues() {
        Importo importo1 = new Importo(5, (short) 50);

        assertThrows(IllegalArgumentException.class, () -> importo1.aggiungi(-3, (short) 75));
        assertThrows(IllegalArgumentException.class, () -> importo1.aggiungi(3, (short) -75));
        assertThrows(IllegalArgumentException.class, () -> importo1.aggiungi(3, (short) 105));
    }

    @Test
    void testAggiungi_WithZeroIntegerAndDecimalValues() {
        Importo importo1 = new Importo(5, (short) 50);

        importo1.aggiungi(0, (short) 0);

        assertEquals(5, importo1.getValoreIntero());
        assertEquals(50, importo1.getValoreDecimale());
    }
}