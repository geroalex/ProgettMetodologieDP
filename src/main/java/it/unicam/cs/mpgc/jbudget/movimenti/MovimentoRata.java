package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;

public class MovimentoRata extends Movimento {

    private final Mutuo mutuoDiAppartenenza;
    private final float tassoInteresse;
    private int numeroRata;
    private static int conteggioRate;


    public MovimentoRata(LocalDate data, Importo importo, Mutuo mutuoDiAppartenenza, float tassoInteresse) {
        super(importo, data, "Rata n." + conteggioRate);
        numeroRata = conteggioRate;
        this.mutuoDiAppartenenza = mutuoDiAppartenenza;
        this.aggiungiTag(new Tags("Rata", 1));
        this.tassoInteresse = tassoInteresse;
        calcValoreConInteressi();
        conteggioRate++;
    }

    public int getNumeroRata() { return numeroRata; }

    private void calcValoreConInteressi(){
        Importo interessi = getImporto().calcolaPercentuale(tassoInteresse);
        interessi.aggiungi(getImporto());
        this.impostaImporto(interessi.getOpposto());
        setUscita(true);
    }

    public static void resetConteggioRate() {
        conteggioRate = 0;
    }
    public Mutuo getMutuoDiAppartenenza() {
        return mutuoDiAppartenenza;
    }
    public float getTassoInteresse() {
        return tassoInteresse;
    }


}
