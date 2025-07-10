package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;

public abstract class MovimentiProgrammati {

    private Importo importo;
    private LocalDate quando;
    protected LocalDate annoCorrente;

    public MovimentiProgrammati(Importo importo){
        this(importo, LocalDate.now());
    }

    public MovimentiProgrammati(Importo importo, LocalDate quando){
        this.importo = importo;
        this.quando = quando;
    }

    public void aumenta(Importo importo) {
        if(importo == null) throw new NullPointerException();
        this.importo.aggiungi(importo);
    }

    public void diminuisci(Importo importo){
        this.importo.sottrai(importo);
    }

    public LocalDate getQuando() { return quando; }
    public void setQuando(LocalDate quando) { this.quando = quando;}
    public Importo getImporto() { return importo; }
    public void setImporto(Importo importo) { this.importo = importo;}

}
