package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.gestione.ContoCorrente;
import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Movimento implements Comparable<Movimento>{

    private String tipoMovimento;
    private Importo importo;
    private boolean isUscita;
    private ArrayList<Tags> tagList;
    private int tagPiuImportante;
    private boolean contabilizzato;
    private LocalDate dataMovimento;

    @Deprecated
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

    public boolean contabilizza(ContoCorrente conto) {
        if(!isUscita) {
            contabilizzato = true;
            conto.versa(this.importo);
            return true;
        }
        if(conto.preleva(this.importo)) {
            contabilizzato = true;
            return true;
        }
        return false;
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

    /**
     * Si usa solo per movimenti il cui movimento finanziario è gia completato, e si vuole quindi
     * segnare che il denaro è stato ricevuto/inviato
     */
    public void contabilizzaFormalmente() {
        contabilizzato = true;
    }

    public String getTipoMovimento() {
        return tipoMovimento;
    }
    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

}
