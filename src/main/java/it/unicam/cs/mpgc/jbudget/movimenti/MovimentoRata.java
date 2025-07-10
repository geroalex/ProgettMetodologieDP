package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;

public class MovimentoRata extends Movimento {

    private final Mutuo mutuoDiAppartenenza;
    private final float tassoInteresse;
    private final LocalDate data;

    public MovimentoRata(LocalDate data, Importo importo, Mutuo mutuoDiAppartenenza, float tassoInteresse) {
        super(importo);
        this.mutuoDiAppartenenza = mutuoDiAppartenenza;
        this.aggiungiTag(new Tags("Rata", 1));
        this.tassoInteresse = tassoInteresse;
        this.data = data;
    }

    public Mutuo getMutuoDiAppartenenza() {
        return mutuoDiAppartenenza;
    }
    public float getTassoInteresse() {
        return tassoInteresse;
    }
    public LocalDate getData() {
        return data;
    }

}
