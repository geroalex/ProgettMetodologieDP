package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Stipendio  {

    private final LocalDate dataAssunzione;
    private LocalDate annoCorrente;
    private Importo importo;
    private ArrayList<MensilitaStipendio> mensilitaAnnoCorrente;
    private MensilitaStipendio tredicesima;


    public Stipendio(LocalDate dataAssunzione, Importo importo){
        if(importo == null) throw new NullPointerException();
        this.dataAssunzione = dataAssunzione;
        this.importo = importo;
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
        Importo stipendioMensile = new Importo(importo.getValoreDouble() / 12);
        annoCorrente = LocalDate.of(LocalDate.now().getYear(), 1, 28);
        for(int i = 0; i < 12; i++)
            mensilitaAnnoCorrente.add(new MensilitaStipendio(stipendioMensile, annoCorrente.plusMonths(i), this));

        for(MensilitaStipendio m : mensilitaAnnoCorrente)
            if(m.getDataStipendio().isBefore(LocalDate.now())) m.impostaPagato(true);

        if(!(dataAssunzione.getYear() == annoCorrente.getYear())){
            tredicesima = new MensilitaStipendio(stipendioMensile, LocalDate.of(annoCorrente.getYear(), 12, 31), this);
            return;
        }

        int mesiDaConsiderare = 12 - dataAssunzione.getMonthValue();
        tredicesima = new MensilitaStipendio(new Importo((stipendioMensile.getValoreDouble() * 0.08) * mesiDaConsiderare), LocalDate.of(annoCorrente.getYear(), 12, 31), this);

    }

    /**
     * Cancella tutte le informazioni relative allo stipendio per l'anno corrente.
     * Questo metodo svuota la lista delle mensilità dell'anno corrente e annulla
     * il valore associato all'importo dello stipendio.
     */
    @Deprecated
    public void cancellaStipendio(){
        mensilitaAnnoCorrente.clear();
        importo = null;
    }

    public LocalDate getDataAssunzione() {
        return dataAssunzione;
    }

    public Importo getImporto() {
        return importo;
    }

    /**
     * Sets the value of the importo field.
     *
     * @param importo the new value to assign to the importo field
     */
    public void setImporto(Importo importo){
        this.importo = importo;
    }

    /**
     * Increases the current 'importo' field by adding the specified 'importo' value.
     *
     * @param importo the amount to be added to the current 'importo'
     */
    public void aumenta(Importo importo){
        this.importo.aggiungi(importo);
    }

    /**
     * Decreases the current value of the 'importo' field by subtracting the specified 'importo' value.
     *
     * @param importo the amount to be subtracted from the current 'importo'
     */
    public void diminuisci(Importo importo){
        this.importo.sottrai(importo);
    }

    public LocalDate getAnnoCorrente(){ return annoCorrente; }
    public ArrayList<MensilitaStipendio> getMensilitaAnnoCorrente(){ return mensilitaAnnoCorrente; }
    public MensilitaStipendio getTredicesima(){ return tredicesima; }

}