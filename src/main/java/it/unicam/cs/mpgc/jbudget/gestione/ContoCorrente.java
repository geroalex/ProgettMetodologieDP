package it.unicam.cs.mpgc.jbudget.gestione;

import it.unicam.cs.mpgc.jbudget.movimenti.Movimento;
import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.util.ArrayList;

public class ContoCorrente {

    private ArrayList<Movimento> movimenti;
    private Importo saldo;
    private final String iban;

    public ContoCorrente(String iban) {
        this.iban = iban;
        movimenti = new ArrayList<>();
        saldo = new Importo(0.0);
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

    public boolean aggiungiMovimento(Movimento movimento){
        if(movimento == null) throw new NullPointerException();
        return movimenti.add(movimento);
    }

    public boolean rimuoviMovimento(Movimento movimento){
        if(movimento == null) throw new NullPointerException();
        return movimenti.remove(movimento);
    }



}
