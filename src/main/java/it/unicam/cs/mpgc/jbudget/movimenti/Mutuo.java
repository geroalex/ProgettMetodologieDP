package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Mutuo extends MovimentiProgrammati{

    private String naturaMutuo;
    private final float tassoInteresse;
    private ArrayList<MovimentoRata> rate;
    private final int numeroRate;
    private final Importo importoMensile;

    /**
     * Crea un mutuo con i seguenti parametri:
     *
     * (Verra assegnato a naturaMutuo "Mutuo generico" e alla data di creazione del mutuo, la data odierna al momento
     * della creazione del mutuo)
     *
     * @param importo: importo da restituire
     * @param tassoInteresse: tasso di interesse da pagare oltre l'importo da restituire
     * @param numeroRate: numero delle rate mensili in cui suddividere il mutuo
     */
    public Mutuo(Importo importo, float tassoInteresse, int numeroRate){
        this("Mutuo generico", importo, tassoInteresse, LocalDate.now(), numeroRate);
    }

    /**
     *
     * Crea un mutuo con i seguenti parametri:
     *
     * @param naturaMutuo:A cosa si riferisce questo mutuo
     * @param importoMutuo: Quanto è l'importo da restituire
     * @param tassoInteresse: Il tasso di interesse da pagare oltre l'importo da restituire
     * @param dataCreazione: data in cui è stato creato il mutuo
     * @param numeroRate: numero delle rate mensili in cui suddividere il mutuo
     */
    public Mutuo(String naturaMutuo, Importo importoMutuo, float tassoInteresse, LocalDate dataCreazione, int numeroRate){
        super(importoMutuo, dataCreazione);
        this.naturaMutuo = naturaMutuo;
        this.tassoInteresse = tassoInteresse;
        this.numeroRate = numeroRate;
        this.importoMensile = importoMutuo.diviso(numeroRate);
        rate = new ArrayList<>();
        creaRate();
    }

    private void creaRate(){

        for(int i = 0; i < numeroRate; i++)
            rate.add(new MovimentoRata(getQuando().plusMonths(i), importoMensile, this, tassoInteresse));

    }

    public ArrayList<MovimentoRata> getRate(){
        return rate;
    }

}
