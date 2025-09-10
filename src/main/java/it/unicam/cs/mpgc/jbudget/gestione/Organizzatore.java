package it.unicam.cs.mpgc.jbudget.gestione;

import it.unicam.cs.mpgc.jbudget.movimenti.Movimento;
import it.unicam.cs.mpgc.jbudget.movimenti.Tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Organizzatore {

    /**
     * Ordina in-place i movimenti in base al tag "più importante"
     * (il massimo nella lista dei tag del movimento secondo Tags.compareTo).
     * I movimenti senza tag vengono posizionati per ultimi.
     *
     * @param movimenti lista di movimenti da ordinare (può essere null)
     * @return la stessa lista, ordinata; mai null
     */
    public static ArrayList<Movimento> ordinaMovimenti(ArrayList<Movimento> movimenti) {
        if (movimenti == null)
            return new ArrayList<>();

        // Usa l'ordinamento naturale (Comparable) di Movimento
        movimenti.sort(null);
        return movimenti;
    }


}