package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovimentoRataTest {

    @Test
    void testGetValoreConInteressi_WhenImportoIsZero() {
        LocalDate data = LocalDate.of(2025, 7, 10);
        Importo importo = new Importo(0L, (short) 0); // Importo is zero
        Mutuo mutuo = new Mutuo(importo, 0.0f, 12); // 12 rate mensili
        float tassoInteresse = 10.0f; // 10% interest rate

        MovimentoRata movimentoRata = new MovimentoRata(data, importo, mutuo, tassoInteresse);
        Importo expectedImporto = new Importo(0L, (short) 0); // Value remains zero

        Importo actualImporto = movimentoRata.getValoreConInteressi();

        assertEquals(expectedImporto.getValoreDouble(), actualImporto.getValoreDouble(), 0.01);
    }

    @Test
    void testGetValoreConInteressi_WhenTassoInteresseIsVeryHigh() {
        LocalDate data = LocalDate.of(2025, 7, 10);
        Importo importo = new Importo(1000L, (short) 0); // Base importo 1000.00
        Mutuo mutuo = new Mutuo(importo, 500.0f, 12); // 12 rate mensili
        float tassoInteresse = 500.0f; // 500% interest rate

        MovimentoRata movimentoRata = new MovimentoRata(data, importo, mutuo, tassoInteresse);
        Importo expectedImporto = new Importo(6000L, (short) 0); // 1000 + 500% = 6000.00

        Importo actualImporto = movimentoRata.getValoreConInteressi();

        assertEquals(expectedImporto.getValoreDouble(), actualImporto.getValoreDouble(), 0.01);
    }

    @Test
    void testGetValoreConInteressi_WhenTassoInteresseIsTiny() {
        LocalDate data = LocalDate.of(2025, 7, 10);
        Importo importo = new Importo(500L, (short) 50); // Base importo 500.50
        Mutuo mutuo = new Mutuo(importo, 0.001f, 12); // 12 rate mensili
        float tassoInteresse = 0.001f; // 0.1% interest rate

        MovimentoRata movimentoRata = new MovimentoRata(data, importo, mutuo, tassoInteresse);
        Importo expectedImporto = new Importo(500L, (short) 50); // Approx. no noticeable change

        Importo actualImporto = movimentoRata.getValoreConInteressi();

        assertEquals(expectedImporto.getValoreDouble(), actualImporto.getValoreDouble(), 0.01);
    }

    /**
     * Tests for the method {@code getValoreConInteressi} in the {@code MovimentoRata} class.
     * This method calculates the total value of the installment ({@code MovimentoRata}),
     * including the interest rate specified during the creation of the object.
     */

    @Test
    void testGetValoreConInteressi_WhenTassoInteresseIsPositive() {
        LocalDate data = LocalDate.of(2025, 7, 10);
        Importo importo = new Importo(1000L, (short) 0); // Base importo 1000.00
        Mutuo mutuo = new Mutuo(importo, 5.0f, 12); // 12 rate mensili
        float tassoInteresse = 5.0f; // 5% interest rate

        MovimentoRata movimentoRata = new MovimentoRata(data, importo, mutuo, tassoInteresse);
        Importo expectedImporto = new Importo(1050L, (short) 0); // 1000 + 5% = 1050.00

        Importo actualImporto = movimentoRata.getValoreConInteressi();

        assertEquals(expectedImporto.getValoreDouble(), actualImporto.getValoreDouble(), 0.01);
    }

    @Test
    void testGetValoreConInteressi_WhenTassoInteresseIsZero() {
        LocalDate data = LocalDate.of(2025, 7, 10);
        Importo importo = new Importo(1500L, (short) 0); // Base importo 1500.00
        Mutuo mutuo = new Mutuo(importo, 0.0f, 12); // 12 rate mensili
        float tassoInteresse = 0.0f; // 0% interest rate

        MovimentoRata movimentoRata = new MovimentoRata(data, importo, mutuo, tassoInteresse);
        Importo expectedImporto = new Importo(1500L, (short) 0); // No change in value

        Importo actualImporto = movimentoRata.getValoreConInteressi();

        assertEquals(expectedImporto.getValoreDouble(), actualImporto.getValoreDouble(), 0.01);
    }

    @Test
    void testGetValoreConInteressi_WhenTassoInteresseIsNegative() {
        LocalDate data = LocalDate.of(2025, 7, 10);
        Importo importo = new Importo(2000L, (short) 0); // Base importo 2000.00
        Mutuo mutuo = new Mutuo(importo, -10.0f, 12); // 12 rate mensili
        float tassoInteresse = -10.0f; // -10% "interest" rate

        MovimentoRata movimentoRata = new MovimentoRata(data, importo, mutuo, tassoInteresse);
        Importo expectedImporto = new Importo(1800L, (short) 0); // 2000 - 10% = 1800.00
        
        Importo actualImporto = movimentoRata.getValoreConInteressi();
        
        assertEquals(expectedImporto.getValoreDouble(), actualImporto.getValoreDouble(), 0.01);
    }

    @Test
    void testGetValoreConInteressi_WhenImportoHasNonZeroDecimals() {
        LocalDate data = LocalDate.of(2025, 7, 10);
        Importo importo = new Importo(999L, (short) 99); // Base importo 999.99
        Mutuo mutuo = new Mutuo(importo, 5.0f, 12); // 12 rate mensili
        float tassoInteresse = 5.0f; // 5% interest rate
        
        MovimentoRata movimentoRata = new MovimentoRata(data, importo, mutuo, tassoInteresse);
        Importo expectedImporto = new Importo(1049L, (short) 99); // Approx. 999.99 + 5% = 1049.99
        
        Importo actualImporto = movimentoRata.getValoreConInteressi();
        
        assertEquals(expectedImporto.getValoreDouble(), actualImporto.getValoreDouble(), 0.01);
    }
}