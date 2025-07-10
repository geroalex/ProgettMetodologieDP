package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Mutuo extends MovimentiProgrammati{

    private String naturaMutuo;
    private final float tassoInteresse;
    private ArrayList<MovimentoRata> rate;

    public Mutuo(Importo importo, float tassoInteresse){
        this("Mutuo generico", importo, tassoInteresse, LocalDate.now());
    }

    public Mutuo(String naturaMutuo, Importo importoMutuo, float tassoInteresse, LocalDate dataCreazione){
        super(importoMutuo, dataCreazione);
        this.naturaMutuo = naturaMutuo;
        this.tassoInteresse = tassoInteresse;
        rate = new ArrayList<>();
        creaRateAnnue();
    }

    private void creaRateAnnue(){
        
    }

}
