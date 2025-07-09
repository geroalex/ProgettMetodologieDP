package it.unicam.cs.mpgc.jbudget.valori;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImportoTest {

    @Test
    void testGetValoreDouble() {
        Importo importo = new Importo(10, (short) 50);
        assertEquals(10.50, importo.getValoreDouble(), 0.001);

        importo = new Importo(15, (short) 75);
        assertEquals(15.75, importo.getValoreDouble(), 0.001);
    }

    @Test
    void testGetValoreDoubleWithZeroValues() {
        Importo importo = new Importo(0, (short) 0);
        assertEquals(0.0, importo.getValoreDouble(), 0.001);
    }

    @Test
    void testGetValoreDoubleWithNegativeValues() {
        Importo importo = new Importo(-10, (short) -50);
        assertEquals(-10.50, importo.getValoreDouble(), 0.001);
    }

    @Test
    void testSottraiImporto() {
        Importo importo1 = new Importo(15, (short) 50);
        Importo importo2 = new Importo(5, (short) 20);

        importo1.sottrai(importo2);

        assertEquals(10, importo1.getValoreIntero());
        assertEquals(30, importo1.getValoreDecimale());
    }

    @Test
    void testSottraiImportoNull() {
        Importo importo = new Importo(15, (short) 50);

        importo.sottrai((Importo) null);

        assertEquals(15, importo.getValoreIntero());
        assertEquals(50, importo.getValoreDecimale());
    }

    @Test
    void testSottraiDoubleValidValues() {
        Importo importo = new Importo(20, (short) 75);

        importo.sottrai(5.50);

        assertEquals(15, importo.getValoreIntero());
        assertEquals(25, importo.getValoreDecimale());
    }

    @Test
    void testSottraiDoubleZeroValue() {
        Importo importo = new Importo(20, (short) 75);

        importo.sottrai(0.0);

        assertEquals(20, importo.getValoreIntero());
        assertEquals(75, importo.getValoreDecimale());
    }

    @Test
    void testSottraiDoubleNegativeValueThrowsException() {
        Importo importo = new Importo(20, (short) 75);

        assertThrows(IllegalArgumentException.class, () -> importo.sottrai(-5.50));
    }

    @Test
    void testAggiungiValidValuesWithoutDecimalOverflow() {
        Importo importo = new Importo(10, (short) 20);

        importo.aggiungi(5, (short) 30);

        assertEquals(15, importo.getValoreIntero());
        assertEquals(50, importo.getValoreDecimale());
    }

    @Test
    void testAggiungiValidValuesWithDecimalOverflow() {
        Importo importo = new Importo(10, (short) 80);

        importo.aggiungi(5, (short) 30);

        assertEquals(16, importo.getValoreIntero());
        assertEquals(10, importo.getValoreDecimale());
    }

    @Test
    void testAggiungiZeroValues() {
        Importo importo = new Importo(10, (short) 20);

        importo.aggiungi(0, (short) 0);

        assertEquals(10, importo.getValoreIntero());
        assertEquals(20, importo.getValoreDecimale());
    }

    @Test
    void testAggiungiNegativeIntegerValueThrowsException() {
        Importo importo = new Importo(10, (short) 20);

        assertThrows(IllegalArgumentException.class, () -> importo.aggiungi(-1, (short) 30));
    }

    @Test
    void testAggiungiNegativeDecimalValueThrowsException() {
        Importo importo = new Importo(10, (short) 20);

        assertThrows(IllegalArgumentException.class, () -> importo.aggiungi(10, (short) -5));
    }

    @Test
    void testAggiungiDecimalValueExceedingHundredThrowsException() {
        Importo importo = new Importo(10, (short) 20);

        assertThrows(IllegalArgumentException.class, () -> importo.aggiungi(10, (short) 105));
    }

    @Test
    void testSottraiValidValuesWithoutDecimalUnderflow() {
        Importo importo = new Importo(15, (short) 50);

        importo.sottrai(5, (short) 30);

        assertEquals(10, importo.getValoreIntero());
        assertEquals(20, importo.getValoreDecimale());
    }

    @Test
    void testSottraiValidValuesWithDecimalUnderflow() {
        Importo importo = new Importo(15, (short) 10);

        importo.sottrai(5, (short) 30);

        assertEquals(9, importo.getValoreIntero());
        assertEquals(80, importo.getValoreDecimale());
    }

    @Test
    void testSottraiZeroValues() {
        Importo importo = new Importo(15, (short) 50);

        importo.sottrai(0, (short) 0);

        assertEquals(15, importo.getValoreIntero());
        assertEquals(50, importo.getValoreDecimale());
    }

    @Test
    void testSottraiNegativeIntegerValueThrowsException() {
        Importo importo = new Importo(15, (short) 50);

        assertThrows(IllegalArgumentException.class, () -> importo.sottrai(-1, (short) 30));
    }

    @Test
    void testSottraiNegativeDecimalValueThrowsException() {
        Importo importo = new Importo(15, (short) 50);

        assertThrows(IllegalArgumentException.class, () -> importo.sottrai(10, (short) -5));
    }

    @Test
    void testSottraiDecimalValueExceedingHundredThrowsException() {
        Importo importo = new Importo(15, (short) 50);

        assertThrows(IllegalArgumentException.class, () -> importo.sottrai(10, (short) 105));
    }

    @Test
    void testAggiungiImporto() {
        Importo importo1 = new Importo(10, (short) 50);
        Importo importo2 = new Importo(5, (short) 30);

        importo1.aggiungi(importo2);

        assertEquals(15, importo1.getValoreIntero());
        assertEquals(80, importo1.getValoreDecimale());
    }

    @Test
    void testAggiungiImportoNull() {
        Importo importo = new Importo(10, (short) 50);

        importo.aggiungi((Importo) null);

        assertEquals(10, importo.getValoreIntero());
        assertEquals(50, importo.getValoreDecimale());
    }

    @Test
    void testAggiungiDoubleValidValues() {
        Importo importo = new Importo(10, (short) 50);

        importo.aggiungi(5.25);

        assertEquals(15, importo.getValoreIntero());
        assertEquals(75, importo.getValoreDecimale());
    }

    @Test
    void testAggiungiDoubleZeroValue() {
        Importo importo = new Importo(10, (short) 50);

        importo.aggiungi(0.0);

        assertEquals(10, importo.getValoreIntero());
        assertEquals(50, importo.getValoreDecimale());
    }

    @Test
    void testAggiungiDoubleNegativeValueThrowsException() {
        Importo importo = new Importo(10, (short) 50);

        assertThrows(IllegalArgumentException.class, () -> importo.aggiungi(-5.0));
    }

    @Test
    void testMoltiplicaWithPositiveValues() {
        Importo importo1 = new Importo(5, (short) 25);
        Importo importo2 = new Importo(2, (short) 10);

        importo1.moltiplica(importo2);

        assertEquals(11, importo1.getValoreIntero());
        assertEquals(3, importo1.getValoreDecimale());
    }

    @Test
    void testMoltiplicaWithZeroValue() {
        Importo importo1 = new Importo(5, (short) 50);
        Importo importo2 = new Importo(0, (short) 0);

        importo1.moltiplica(importo2);

        assertEquals(0, importo1.getValoreIntero());
        assertEquals(0, importo1.getValoreDecimale());
    }

    @Test
    void testMoltiplicaNullThrowsException() {
        Importo importo1 = new Importo(5, (short) 25);

        assertThrows(NullPointerException.class, () -> importo1.moltiplica(null));
    }
}