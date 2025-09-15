package it.unicam.cs.mpgc.jbudget.gestione;

import java.util.ArrayList;

public class MemoriaConti {

    private static ArrayList<ContoCorrente> conti = new ArrayList<>();

    public static void aggiungiConto(ContoCorrente cc){
        conti.add(cc);
    }

    public static ArrayList<ContoCorrente> getConti(){
        return conti;
    }

}
