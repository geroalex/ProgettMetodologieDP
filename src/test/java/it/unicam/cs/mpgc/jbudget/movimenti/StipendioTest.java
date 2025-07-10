package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StipendioTest {

    @Test
    void testGeneraMensilitaAnnoCorrenteWithFullYear() {
        LocalDate dataAssunzione = LocalDate.of(2024, 1, 1);
        Importo importo = new Importo(24000.0);
        Stipendio stipendio = new Stipendio(dataAssunzione, importo);

        ArrayList<MensilitaStipendio> mensilita = stipendio.getMensilitaAnnoCorrente();
        assertEquals(12, mensilita.size());

        double expectedMensile = importo.getValoreDouble() / 12;
        for (MensilitaStipendio m : mensilita) {
            assertEquals(expectedMensile, m.getImporto().getValoreDouble());
        }

        MensilitaStipendio tredicesima = stipendio.getTredicesima();
        assertEquals(expectedMensile, tredicesima.getImporto().getValoreDouble());
        assertEquals(LocalDate.of(LocalDate.now().getYear(), 12, 31), tredicesima.getDataStipendio());
    }

    @Test
    void testGeneraMensilitaAnnoCorrenteWithPartialYear() {
        LocalDate dataAssunzione = LocalDate.of(2025, 7, 1);
        Importo importo = new Importo(24000.0);
        Stipendio stipendio = new Stipendio(dataAssunzione, importo);

        ArrayList<MensilitaStipendio> mensilita = stipendio.getMensilitaAnnoCorrente();
        assertEquals(12, mensilita.size());

        double expectedMensile = importo.getValoreDouble() / 12;
        for (MensilitaStipendio m : mensilita) {
            assertEquals(expectedMensile, m.getImporto().getValoreDouble());
        }

        MensilitaStipendio tredicesima = stipendio.getTredicesima();
        int mesiDaConsiderare = 12 - dataAssunzione.getMonthValue();
        double expectedTredicesima = (expectedMensile * 0.08) * mesiDaConsiderare;
        assertEquals(expectedTredicesima, tredicesima.getImporto().getValoreDouble());
        assertEquals(LocalDate.of(LocalDate.now().getYear(), 12, 31), tredicesima.getDataStipendio());
    }

    @Test
    void testMensilitaMarkedAsPaid() {
        LocalDate dataAssunzione = LocalDate.of(2023, 1, 1);
        Importo importo = new Importo(24000.0);
        Stipendio stipendio = new Stipendio(dataAssunzione, importo);

        ArrayList<MensilitaStipendio> mensilita = stipendio.getMensilitaAnnoCorrente();
        for (MensilitaStipendio m : mensilita) {
            if (m.getDataStipendio().isBefore(LocalDate.now())) {
                assertTrue(m.statoPagato());
            } else {
                assertFalse(m.statoPagato());
            }
        }
    }

    @Test
    void testGeneraMensilitaAnnoCorrenteForSameYearAssunzione() {
        LocalDate dataAssunzione = LocalDate.of(LocalDate.now().getYear(), 3, 1);
        Importo importo = new Importo(12000.0);
        Stipendio stipendio = new Stipendio(dataAssunzione, importo);

        ArrayList<MensilitaStipendio> mensilita = stipendio.getMensilitaAnnoCorrente();
        assertEquals(12, mensilita.size());

        double expectedMensile = importo.getValoreDouble() / 12;
        for (MensilitaStipendio m : mensilita) {
            assertEquals(expectedMensile, m.getImporto().getValoreDouble());
        }

        int mesiDaConsiderare = 12 - dataAssunzione.getMonthValue();
        double expectedTredicesima = (expectedMensile * 0.08) * mesiDaConsiderare;

        MensilitaStipendio tredicesima = stipendio.getTredicesima();
        assertEquals(expectedTredicesima, tredicesima.getImporto().getValoreDouble());
        assertEquals(LocalDate.of(LocalDate.now().getYear(), 12, 31), tredicesima.getDataStipendio());
    }
}