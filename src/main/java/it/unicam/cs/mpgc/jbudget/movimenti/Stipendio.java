package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Stipendio  {

    private final LocalDate dataAssunzione;
    private Importo importo;
    private ArrayList<MensilitaStipendio> mensilita;

    public Stipendio(LocalDate dataAssunzione, Importo importo){
        this.dataAssunzione = dataAssunzione;
        this.importo = importo;
        mensilita = new ArrayList<>();
    }

    public LocalDate getDataAssunzione() {
        return dataAssunzione;
    }

    public Importo getImporto() {
        return importo;
    }

    public void setImporto(Importo importo){
        this.importo = importo;
    }

    public void aumenta(Importo importo){
        this.importo.aggiungi(importo);
    }

    public void diminuisci(Importo importo){
        this.importo.sottrai(importo);
    }

}
