package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Mutuo {

    private String naturaMutuo;
    private Importo importoMutuo;
    private final float tassoInteresse;
    private final LocalDate dataCreazione;
    private ArrayList<MovimentoRata> rate;

    public Mutuo(Importo importo, float tassoInteresse){
        this("Mutuo generico", importo, tassoInteresse, LocalDate.now());
    }

    public Mutuo(String naturaMutuo, Importo importoMutuo, float tassoInteresse, LocalDate dataCreazione){
        this.naturaMutuo = naturaMutuo;
        this.importoMutuo = importoMutuo;
        this.tassoInteresse = tassoInteresse;
        this.dataCreazione = dataCreazione;
    }



}
