package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;

/**
 * La classe MensilitaStipendio rappresenta una specifica implementazione della classe astratta Movimento,
 * utilizzata per gestire una mensilità di stipendio, con informazioni sulla data del pagamento e
 * lo stato di pagamento.
 */
public class MensilitaStipendio extends Movimento {

    private LocalDate dataStipendio;
    private boolean statoPagato;
    private final Stipendio stipendioDiAppartenenza;

    public MensilitaStipendio(Importo importo, Stipendio stipendioDiAppartenenza) {
        this(importo, LocalDate.now(), stipendioDiAppartenenza);
    }

    public MensilitaStipendio(Importo importo, LocalDate dataStipendio, Stipendio stipendioDiAppartenenza) {
        super(importo, dataStipendio);
        this.dataStipendio = dataStipendio;
        this.stipendioDiAppartenenza = stipendioDiAppartenenza;
        statoPagato = false;
        this.aggiungiTag(new Tags("Stipendio", 1));
    }

    public boolean statoPagato() {
        return statoPagato;
    }
    public LocalDate getDataStipendio() {
        return dataStipendio;
    }
    public Stipendio getStipendioDiAppartenenza() { return stipendioDiAppartenenza; }

}
