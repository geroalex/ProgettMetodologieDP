package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Stipendio extends MovimentiProgrammati{

    private ArrayList<MensilitaStipendio> mensilitaAnnoCorrente;
    private MensilitaStipendio tredicesima;


    public Stipendio(LocalDate dataAssunzione, Importo importo){
        super(importo, dataAssunzione);
        if(importo == null) throw new NullPointerException();
        //this.dataAssunzione = dataAssunzione;
        mensilitaAnnoCorrente = new ArrayList<>();
        generaMensilitaAnnoCorrente();
    }

    /**
    * Genera le mensilità dello stipendio per l'anno corrente e calcola la tredicesima.
    *
    * Questo metodo esegue le seguenti operazioni:
    * 1. Calcola lo stipendio mensile dividendo l'importo annuale per 12
    * 2. Imposta l'anno corrente al 28 gennaio dell'anno in corso
    * 3. Genera 12 mensilità dello stipendio per l'anno corrente
    * 4. Marca come pagate tutte le mensilità antecedenti alla data odierna
    * 5. Calcola la tredicesima mensilità secondo le seguenti regole:
    *    - Se l'anno di assunzione è diverso dall'anno corrente, la tredicesima è pari a una mensilità intera
    *    - Se l'anno di assunzione è quello corrente, la tredicesima viene calcolata in proporzione ai mesi
    *      lavorati (8% dello stipendio mensile per ogni mese rimanente nell'anno dalla data di assunzione)
    */
    protected void generaMensilitaAnnoCorrente(){
        Importo stipendioMensile = new Importo(getImporto().getValoreDouble() / 12);
        annoCorrente = LocalDate.of(LocalDate.now().getYear(), 1, 28);
        for(int i = 0; i < 12; i++)
            mensilitaAnnoCorrente.add(new MensilitaStipendio(stipendioMensile, annoCorrente.plusMonths(i), this, false));

        for(MensilitaStipendio m : mensilitaAnnoCorrente)
            if(m.getDataStipendio().isBefore(LocalDate.now())) m.contabilizza();

        if(!(getQuando().getYear() == annoCorrente.getYear())){
            tredicesima = new MensilitaStipendio(stipendioMensile, LocalDate.of(annoCorrente.getYear(), 12, 31), this, true);
            return;
        }

        int mesiDaConsiderare = 12 - getQuando().getMonthValue();
        tredicesima = new MensilitaStipendio(new Importo((stipendioMensile.getValoreDouble() * 0.08) * mesiDaConsiderare), LocalDate.of(annoCorrente.getYear(), 12, 31), this, true);

    }

    /**
     * Cancella tutte le informazioni relative allo stipendio per l'anno corrente.
     * Questo metodo svuota la lista delle mensilità dell'anno corrente e annulla
     * il valore associato all'importo dello stipendio.
     */
    @Deprecated
    public void cancellaStipendio(){
        mensilitaAnnoCorrente.clear();
        setImporto(null);
    }

    public LocalDate getAnnoCorrente(){ return annoCorrente; }
    public ArrayList<MensilitaStipendio> getMensilitaAnnoCorrente(){ return mensilitaAnnoCorrente; }
    public MensilitaStipendio getTredicesima(){ return tredicesima; }

}