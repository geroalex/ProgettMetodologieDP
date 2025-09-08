package it.unicam.cs.mpgc.jbudget.gestione;

import it.unicam.cs.mpgc.jbudget.valori.Importo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContoCorrenteTest {

    /**
     * Test that verifies successful transfer between two accounts when sufficient balance is present.
     */
    @Test
    void testSpostaSuccess() {
        ContoCorrente source = new ContoCorrente("IT601234567890123456");
        ContoCorrente destination = new ContoCorrente("IT609876543210987654");
        Importo amount = new Importo(100.0);

        source.versa(new Importo(200.0));
        boolean result = source.sposta(destination, amount);

        assertTrue(result);
        assertEquals(100.0, source.getSaldo().getValoreDouble());
        assertEquals(100.0, destination.getSaldo().getValoreDouble());
    }

    /**
     * Test that verifies transfer fails when the source account has insufficient balance.
     */
    @Test
    void testSpostaInsufficientBalance() {
        ContoCorrente source = new ContoCorrente("IT601234567890123456");
        ContoCorrente destination = new ContoCorrente("IT609876543210987654");
        Importo amount = new Importo(300.0);

        source.versa(new Importo(200.0));
        boolean result = source.sposta(destination, amount);

        assertFalse(result);
        assertEquals(200.0, source.getSaldo().getValoreDouble());
        assertEquals(0.0, destination.getSaldo().getValoreDouble());
    }

    /**
     * Test that verifies transfer fails when the destination account is null.
     */
    @Test
    void testSpostaNullDestination() {
        ContoCorrente source = new ContoCorrente("IT601234567890123456");
        Importo amount = new Importo(100.0);

        source.versa(new Importo(200.0));

        assertThrows(NullPointerException.class, () -> source.sposta(null, amount));
    }

    /**
     * Test that verifies transfer fails when the amount is null.
     */
    @Test
    void testSpostaNullAmount() {
        ContoCorrente source = new ContoCorrente("IT601234567890123456");
        ContoCorrente destination = new ContoCorrente("IT609876543210987654");

        source.versa(new Importo(200.0));

        assertThrows(NullPointerException.class, () -> source.sposta(destination, null));
    }

    /**
     * Test that verifies transfer does not affect balances when the amount is zero.
     */
    @Test
    void testSpostaZeroAmount() {
        ContoCorrente source = new ContoCorrente("IT601234567890123456");
        ContoCorrente destination = new ContoCorrente("IT609876543210987654");
        Importo amount = new Importo(0.0);

        source.versa(new Importo(200.0));
        boolean result = source.sposta(destination, amount);

        assertTrue(result);
        assertEquals(200.0, source.getSaldo().getValoreDouble());
        assertEquals(0.0, destination.getSaldo().getValoreDouble());
    }
}