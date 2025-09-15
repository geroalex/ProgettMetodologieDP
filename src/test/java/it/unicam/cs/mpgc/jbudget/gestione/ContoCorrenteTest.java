package it.unicam.cs.mpgc.jbudget.gestione;

import it.unicam.cs.mpgc.jbudget.movimenti.Movimento;
import it.unicam.cs.mpgc.jbudget.valori.Importo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ContoCorrenteTest {

    /**
     * Test that verifies a Movimento is added correctly in the sorted order.
     */
    @Test
    void testAggiungiMovimentoSuccessfully() {
        ContoCorrente account = new ContoCorrente("IT601234567890123456");
        Movimento movimento1 = new Movimento(new Importo(50.0), LocalDate.of(2025, 9, 10), "Deposit") {
            @Override
            public void contabilizza() {
                contabilizzato = true;
            }

            @Override
            public boolean isContabilizzato() {
                return contabilizzato;
            }
        };
        Movimento movimento2 = new Movimento(new Importo(100.0), LocalDate.of(2025, 9, 15), "Deposit") {
            @Override
            public void contabilizza() {
                contabilizzato = true;
            }

            @Override
            public boolean isContabilizzato() {
                return contabilizzato;
            }
        };
        account.aggiungiMovimento(movimento2);
        account.aggiungiMovimento(movimento1);

        assertEquals(2, account.getMovimenti().size());
        assertEquals(movimento1.getDataMovimento(), account.getMovimenti().get(0).getDataMovimento());
    }

    /**
     * Test that verifies adding a null Movimento throws a NullPointerException.
     */
    @Test
    void testAggiungiMovimentoNull() {
        ContoCorrente account = new ContoCorrente("IT601234567890123456");

        assertThrows(NullPointerException.class, () -> account.aggiungiMovimento(null));
    }

    /**
     * Test that verifies behavior when adding a duplicate Movimento to the account.
     */
    @Test
    void testAggiungiMovimentoDuplicate() {
        ContoCorrente account = new ContoCorrente("IT601234567890123456");
        Movimento movimento = new Movimento(new Importo(50.0), LocalDate.of(2025, 9, 10), "Deposit") {
            @Override
            public void contabilizza() {
                contabilizzato = true;
            }

            @Override
            public boolean isContabilizzato() {
                return contabilizzato;
            }
        };
        account.aggiungiMovimento(movimento);
        account.aggiungiMovimento(movimento);

        assertEquals(2, account.getMovimenti().size());
        assertSame(movimento, account.getMovimenti().get(0));
    }

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

        //assertTrue(result);
        assertEquals(200.0, source.getSaldo().getValoreDouble());
        assertEquals(0.0, destination.getSaldo().getValoreDouble());
    }
}