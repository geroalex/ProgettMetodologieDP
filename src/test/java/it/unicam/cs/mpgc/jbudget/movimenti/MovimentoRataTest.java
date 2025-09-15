package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovimentoRataTest {






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

        Importo actualImporto = movimentoRata.getImporto();

        assertEquals(expectedImporto.getValoreDouble(), actualImporto.getValoreDouble(), 0.01);
    }



}