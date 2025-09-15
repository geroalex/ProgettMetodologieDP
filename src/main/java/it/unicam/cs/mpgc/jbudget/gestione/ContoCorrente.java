package it.unicam.cs.mpgc.jbudget.gestione;

import it.unicam.cs.mpgc.jbudget.movimenti.*;
import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContoCorrente {

    
    private ArrayList<Movimento> movimenti;
    private ArrayList<MovimentiProgrammati> mProgrammati;
    private Importo saldo;
    private final String iban;

    public ContoCorrente() {
        this("IT600000000000000000");
    }   

    public ContoCorrente(String iban) {
        this.iban = iban;
        movimenti = new ArrayList<>();
        mProgrammati = new ArrayList<>();
        saldo = new Importo(0.0);
    }

    public void creaMovimentoProgrammato(MovimentiProgrammati mProg){
        mProgrammati.add(mProg);
        if(mProg instanceof Mutuo){
            Mutuo mutuo = (Mutuo) mProg;
            movimenti.addAll(mutuo.getRate());
        }

        if(mProg instanceof Stipendio){
            Stipendio stipendio = (Stipendio) mProg;
            movimenti.addAll(stipendio.getMensilitaAnnoCorrente());
            movimenti.add(stipendio.getTredicesima());
        }

    }

    public boolean contabilizza(int numeroMovimento){
        Movimento m = movimenti.get(numeroMovimento);
        if(m.isContabilizzato()) return false;
        Importo importo = m.getImporto();
        if(!m.isUscita()) {
            this.versa(importo);
            m.contabilizza();
            return true;
        }

        if(!this.preleva(importo.valoreAssoluto())) return false;
        m.contabilizza();
        return true;

    }

    /**
     * Adds the specified amount to the account balance.
     *
     * @param quanto the amount to be added to the account balance
     */
    public void versa(Importo quanto){
        saldo.aggiungi(quanto);
    }

    /**
     * Attempts to withdraw the specified amount from the account's balance.
     * If the amount is null, a NullPointerException will be thrown.
     *
     * @param quanto the amount to be withdrawn from the account balance
     * @return true if the withdrawal operation is successful, false otherwise
     * @throws NullPointerException if the specified amount is null
     */
    public boolean preleva(Importo quanto) throws NullPointerException{
        if(quanto == null) throw new NullPointerException();
        return saldo.sottrai(quanto);
    }

    public void aggiungiMovimento(Movimento movimento){
        if(movimento == null) throw new NullPointerException();
        if(movimenti.isEmpty()) {
            movimenti.add(movimento);
            return;
        }
        if(movimenti.getLast().getDataMovimento().isBefore(movimento.getDataMovimento())){
            movimenti.add(movimenti.indexOf(movimenti.getLast()) + 1, movimento);
            return;
        }
        for(Movimento m : movimenti){
            if(m.getDataMovimento().isBefore(movimento.getDataMovimento())) continue;
            movimenti.add(movimenti.indexOf(m), movimento);
            break;
        }
    }

    public boolean rimuoviMovimento(Movimento movimento){
        if(movimento == null) throw new NullPointerException();
        return movimenti.remove(movimento);
    }

    public boolean sposta(ContoCorrente destinazione, Importo quanto) throws NullPointerException{
        if(destinazione == null) throw new NullPointerException();
        if(quanto == null) throw new NullPointerException();
        if(!preleva(quanto)) return false;
        destinazione.versa(quanto);
        return true;
    }

    public ArrayList<Movimento> getMovimentiContabilizzati(){
        ArrayList<Movimento> mContabilizzati = new ArrayList<>();
        for(Movimento m : movimenti)
            if(m.isContabilizzato()) mContabilizzati.add(m);
        return mContabilizzati;
    }
    public ArrayList<MovimentiProgrammati> getMovimentiProgrammati(){ return mProgrammati;}
    public ArrayList<Movimento> getMovimenti(){
        return movimenti;
    }
    public ArrayList<Movimento> getMovimentiOrdinati(){
        ArrayList<Movimento> ritorno = new ArrayList<>(movimenti);
        ritorno.sort(null);
        return ritorno;
    }
    public ArrayList<Movimento> getMovimentiPrimaDi(LocalDate data){
        ArrayList<Movimento> ritorno = new ArrayList<>();
        for(Movimento m: movimenti)
            if(m.getDataMovimento().isBefore(data)) ritorno.add(m);
        return ritorno;
    }
    public ArrayList<Movimento> getMovimentiPosterioreAl(LocalDate data){
        ArrayList<Movimento> ritorno = new ArrayList<>();
        for(Movimento m: movimenti)
            if(m.getDataMovimento().isAfter(data)) ritorno.add(m);
        return ritorno;
    }
    public ArrayList<Movimento> getMovimentiAl(LocalDate data){
        ArrayList<Movimento> ritorno = new ArrayList<>();
        for(Movimento m: movimenti)
            if(m.getDataMovimento().isEqual(data)) ritorno.add(m);
        return ritorno;
    }
    public Importo getSaldo(){ return saldo;}
    public String getIban(){ return iban;}

    private void ordinaPerData(){

    }

}
