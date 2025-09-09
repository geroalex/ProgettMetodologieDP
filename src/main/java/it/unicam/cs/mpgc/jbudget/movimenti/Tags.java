package it.unicam.cs.mpgc.jbudget.movimenti;

public class Tags implements Comparable<Tags>{

    private final String tipoTag;
    private  String descrizioneTag;
    private  int pesoTag;

    public Tags(String tipoTag, int pesoTag){
        this(tipoTag, "Descrizione vuota", pesoTag);
    }

    public Tags(String tipoTag, String descrizioneTag, int pesoTag){
        this.tipoTag = tipoTag;
        this.pesoTag = pesoTag;
        this.descrizioneTag = descrizioneTag;
    }

    public void aumentaPeso(int diQuanto){
        pesoTag += diQuanto;
    }

    public void riduciPeso(int diQuanto){
        pesoTag -= diQuanto;
    }

    public void impostaPeso(int aQuanto){
        pesoTag = aQuanto;
    }

    public String getTipoTag() {
        return tipoTag;
    }

    public String getDescrizioneTag() {
        return descrizioneTag;
    }

    public int getPesoTag(){
        return pesoTag;
    }

    @Override
    public int compareTo(Tags o) {
        if(o == null) throw new NullPointerException("Tags non possono essere nulli");
        if(this.pesoTag > o.pesoTag) return 1;
        if(this.pesoTag == o.pesoTag) return 0;
        return -1;
    }
    public String toString(){
        return "Tipo: " + tipoTag + " Descrizione: " + descrizioneTag + " Peso: " + pesoTag;
    }

}
