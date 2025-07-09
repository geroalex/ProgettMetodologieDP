package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.util.ArrayList;

public abstract class Movimento implements Comparable<Movimento>{

    private Importo importo;
    private boolean isUscita;
    private ArrayList<Tags> tagList;
    private int tagPiuImportante;
    
    public Movimento(Importo importo){
        this.importo = importo;
        isUscita = this.importo.getValoreIntero() < 0;
        tagList = new ArrayList<>();
        tagPiuImportante = 0;
    }
    
    public Importo getImporto() {
        return importo;
    }

    public boolean isUscita() {
        return isUscita;
    }

    public ArrayList<Tags> getTagList() {
        return tagList;
    }

    /**
     * Aggiungi un tag alla lista dei tags
     * @param tag
     */
    public void aggiungiTag(Tags tag) {
        if (tagList.contains(tag)) return;

        tagList.add(tag);
        if (tagList.size() == 1) {
            tagPiuImportante = 0;
            return;
        }

        if (tag.compareTo(tagList.get(tagPiuImportante)) > 0)
            tagPiuImportante = tagList.size() - 1;
    }

    /**
     * Rimuovi un tag dalla lista dei tags
     * @param tag
     */
    public void rimuoviTag(Tags tag) {
        if (!tagList.contains(tag)) return;
        if (tagList.indexOf(tag) == tagPiuImportante) {
            tagList.remove(tag);
            if (tagList.isEmpty()) {
                tagPiuImportante = 0;
                return;
            }
            int maxIndex = 0;
            for (int i = 1; i < tagList.size(); i++) {
                if (tagList.get(i).compareTo(tagList.get(maxIndex)) > 0)
                    maxIndex = i;
            }
            tagPiuImportante = maxIndex;
        } else {
            tagList.remove(tag);
        }
    }


    public int compareTo(Movimento o) {
        return o.getTagList().get(tagPiuImportante).compareTo(getTagList().get(tagPiuImportante));
    }
    
}
