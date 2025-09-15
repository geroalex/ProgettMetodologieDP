package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.gestione.ContoCorrente;
import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.nio.MappedByteBuffer;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Movimento implements Comparable<Movimento>{

    private String tipoMovimento;
    private Importo importo;
    private boolean isUscita;
    private ArrayList<Tags> tagList;
    private int tagPiuImportante;
    protected boolean contabilizzato;
    private LocalDate dataMovimento;


    public Movimento(Importo importo){
        this(importo, "Movimento senza titolo");
    }

    public Movimento(String tipoMovimento){
        this(new Importo(0), tipoMovimento);
    }

    public Movimento(Importo importo, String tipoMovimento){
        this(importo, LocalDate.now(), tipoMovimento);
    }

    public Movimento(Importo importo, LocalDate dataMovimento, String tipoMovimento){
        if(importo == null) this.importo = new Importo(0);
        else this.importo = importo;
        isUscita = this.importo.getValoreIntero() < 0;
        tagList = new ArrayList<>();
        tagPiuImportante = 0;
        contabilizzato = false;
        this.dataMovimento = dataMovimento;
        this.tipoMovimento = tipoMovimento;
    }

    public void contabilizza() {
        contabilizzato = true;

    }

    public boolean isContabilizzato(){
        return contabilizzato;
    }
    public Importo getImporto() {
        return importo;
    }
    public void aggiungiImporto(Importo importo) { this.importo.aggiungi(importo); }
    public void sottraiImporto(Importo importo) { this.importo.sottrai(importo); }
    public LocalDate getDataMovimento() {
        return dataMovimento;
    }
    public void setDataMovimento(LocalDate dataMovimento) {
        this.dataMovimento = dataMovimento;
    }
    public void impostaImporto(Importo importo) {
        this.importo = importo;
    }
    public boolean isUscita() {
        return isUscita;
    }
    public ArrayList<Tags> getTagList() {
        return tagList;
    }
    public Tags getTagPiuImportante() { return tagList.get(tagPiuImportante); }
    /**
     * Aggiungi un tag alla lista dei tags
     * @param tag : tag da aggiungere
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
     * @param tag : tag da rimuovere
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
            for (int i = 1; i < tagList.size(); i++)
                if (tagList.get(i).compareTo(tagList.get(maxIndex)) > 0) maxIndex = i;
            tagPiuImportante = maxIndex;
        } else {
            tagList.remove(tag);
        }
    }
    public int compareTo(Movimento o) {
        return o.getTagList().get(tagPiuImportante).compareTo(getTagList().get(tagPiuImportante));
    }



    public String getTipoMovimento() {
        return tipoMovimento;
    }
    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    protected void setUscita(boolean b){
        isUscita = b;
    }

    public String toString(){
        return tipoMovimento +
                " " +
                importo.toString() +
                " " +
                dataMovimento.toString() +
                " " +
                "Contabilizzato: " +
                contabilizzato + " " +
                "Tags: " + tagList.toString();
    }


}
