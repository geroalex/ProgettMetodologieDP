package it.unicam.cs.mpgc.jbudget.valori;

/**
 * La classe Importo serve a rappresentare valori monetari a virgola fissa
 * Questa classe permette quindi operazioni monetarie precise come:
 * - addizione
 * - sottrazione
 * - moltiplicazione
 * - divisione
 * senza avere possibili errori di precisione dei valori in virgola mobile
 * @author Alessandro Pisciarelli 119161
 */
public class Importo implements Comparable<Importo> {

    private long valoreIntero;
    private short valoreDecimale;

    /**
     * Costruisce un importo partendo da un double, spezzandolo in due parti
     * una parte che rappresenta l'"intero" della cifra e l'altro che rappresenta
     * i decimali (centesimi) della cifra.
     *
     * @param valoreDaConvertire il valore double da convertire
     */
    public Importo(double valoreDaConvertire){
        valoreIntero = (long) valoreDaConvertire;
        valoreDecimale = (short) Math.round((valoreDaConvertire - valoreIntero) * 100);
    }

    /**
     * Costruisce un oggetto Importo specificando separatamente la parte intera e quella decimale
     * del valore monetario rappresentato.
     *
     * @param valoreIntero la parte intera del valore monetario
     * @param valoreDecimale la parte decimale del valore monetario rappresentata in centesimi
     */
    public Importo(long valoreIntero, short valoreDecimale) {
        this.valoreIntero = valoreIntero;
        this.valoreDecimale = valoreDecimale;
    }

    /**
     * Aggiunge un importo specificato, separatamente in termini di parte intera e decimale,
     * al valore attualmente rappresentato dall'oggetto. Se la somma dei decimali supera 100,
     * il valore risultante viene correttamente convertito, incrementando di 1 la parte intera.
     *
     * @param valoreIntero la parte intera del valore da aggiungere; deve essere maggiore o uguale a 0
     * @param valoreDecimale la parte decimale (in centesimi) del valore da aggiungere; deve essere
     *                       un valore compreso tra 0 e 100 (esclusi i limiti negativi e superiori a 100)
     * @throws IllegalArgumentException se valoreIntero è negativo, valoreDecimale è negativo,
     *                                  o valoreDecimale supera 100
     */
    public void aggiungi(long valoreIntero, short valoreDecimale){
        if(valoreIntero < 0 || valoreDecimale < 0 || valoreDecimale > 100) throw new IllegalArgumentException("Valori non ammessi");
        if(valoreIntero == 0 && valoreDecimale == 0) return;
        this.valoreIntero += valoreIntero;
        this.valoreDecimale = (short) (this.valoreDecimale + valoreDecimale);
        if(this.valoreDecimale >= 100) {
            this.valoreDecimale -= 100;
            this.valoreIntero++;
        }
    }

    /**
     * Sottrae un importo specificato, suddiviso in parte intera e decimale, dal valore attualmente rappresentato
     * dall'oggetto. Se la sottrazione dei decimali genera un valore negativo, la parte decimale viene regolata
     * sommando 100 e decrementando di 1 la parte intera per mantenere la consistenza del risultato.
     *
     * @param valoreIntero la parte intera del valore da sottrarre; deve essere maggiore o uguale a 0
     * @param valoreDecimale la parte decimale (in centesimi) del valore da sottrarre; deve essere un valore
     *                       compreso tra 0 e 100 (esclusi i limiti negativi e superiori a 100)
     * @throws IllegalArgumentException se valoreIntero è negativo, valoreDecimale è negativo o
     *                                  valoreDecimale supera 100
     */
    public boolean sottrai(long valoreIntero, short valoreDecimale){
        if(valoreIntero < 0 || valoreDecimale < 0 || valoreDecimale > 100) throw new IllegalArgumentException("Valori non ammessi");
        if(valoreIntero == 0 && valoreDecimale == 0) return false;
        this.valoreIntero -= valoreIntero;
        this.valoreDecimale = (short) (this.valoreDecimale - valoreDecimale);
        if(this.valoreDecimale < 0) {
            this.valoreDecimale += 100;
            this.valoreIntero--;
        }
        return true;
    }

    /**
     * Aggiunge un oggetto Importo all'istanza corrente.
     * L'importo da sommare viene scomposto in valori interi e decimali,
     * che vengono quindi aggiunti utilizzando il metodo appropriato.
     * Se il parametro importo è null, il metodo non esegue alcuna operazione.
     *
     * @param importo l'oggetto Importo da aggiungere; se null, l'operazione viene ignorata
     */
    public void aggiungi(Importo importo){
        if(importo == null) return;
        aggiungi(importo.getValoreIntero(), importo.getValoreDecimale());
    }

    /**
     * Sottrae un importo specificato dall'oggetto corrente. L'importo da sottrarre
     * viene scomposto nelle sue componenti di parte intera e decimale e la sottrazione
     * viene eseguita utilizzando il metodo appropriato. Se il parametro {@code importo} è null,
     * il metodo non esegue alcuna operazione e termina senza modificare lo stato dell'oggetto.
     *
     * @param importo l'oggetto Importo da sottrarre; se null, l'operazione viene ignorata
     */
    public boolean sottrai(Importo importo){
        if(importo == null) return false;
        return sottrai(importo.getValoreIntero(), importo.getValoreDecimale());
    }

    /**
     * Aggiunge un valore espresso come numero in virgola mobile doppia precisione
     * all'importo attualmente rappresentato dall'oggetto. Se il valore da aggiungere
     * è negativo, viene sollevata un'eccezione. Se il valore da aggiungere è zero,
     * il metodo termina senza effettuare modifiche all'oggetto.
     *
     * @param valore il valore in formato*/
    public void aggiungi(double valore){
        if(valore < 0) throw new IllegalArgumentException("Valore negativo");
        if(valore == 0) return;
        aggiungi((long) valore, (short) Math.round((valore - (long) valore) * 100));
    }

    /**
     * Sottrae un valore espresso in formato a virgola mobile doppia precisione
     * dall'importo attualmente rappresentato dall'oggetto. Se il valore fornito
     * è negativo, viene lanciata un'eccezione. Se il valore è zero, il metodo
     * termina senza apportare alcuna modifica all'oggetto.
     *
     * @param valore il valore in formato double da sottrarre; deve essere maggiore o uguale a 0
     * @throws IllegalArgumentException se il parametro valore è negativo
     */
    public boolean sottrai(double valore){
        if(valore < 0) throw new IllegalArgumentException("Valore negativo");
        if(valore == 0) return false;
        return sottrai((long) valore, (short) Math.round((valore - (long) valore) * 100));
    }

    /**
     * Imposta il valore dell'oggetto con l'importo specificato.
     * Il valore dell'importo viene suddiviso nelle sue componenti
     * di parte intera e parte decimale, che vengono quindi salvate
     * nei campi corrispondenti.
     *
     * @param importo l'oggetto Importo utilizzato per impostare il nuovo valore.
     *                Deve essere non nullo, altrimenti viene sollevata una NullPointerException.
     * @throws NullPointerException se il parametro importo è null.
     */
    public void impostaImporto(Importo importo){
        if(importo == null) throw new NullPointerException("Importo non valido");
        this.valoreIntero = importo.getValoreIntero();
        this.valoreDecimale = importo.getValoreDecimale();
    }

    /**
     * Moltiplica il valore dell'oggetto corrente per il valore di un altro oggetto Importo
     * e aggiorna l'oggetto corrente con il risultato del prodotto.
     *
     * @param importo l'oggetto Importo da utilizzare come moltiplicatore.
     *                Deve essere non nullo, altrimenti viene sollevata una NullPointerException.
     * @throws NullPointerException se il parametro importo è null.
     */
    public void moltiplica(Importo importo){
        if(importo == null) throw new NullPointerException("Importo non valido");
        double altroValore = importo.getValoreDouble();
        double questoValore = getValoreDouble();
        double valoreMoltiplicato = altroValore * questoValore;
        impostaImporto(new Importo(valoreMoltiplicato));
    }

    /**
     * Ritorna la parte intera dell' importo
     * @return valoreIntero
     */
    public long getValoreIntero() {
        return valoreIntero;
    }

    /**
     * Ritorna la parte decimale (centesimi) dell'importo
     * @return valoreDecimale
     */
    public short getValoreDecimale() {
        return valoreDecimale;
    }

    public double getValoreDouble() {
        return valoreIntero + (valoreDecimale / 100.0);
    }

    /**
     * Compares this {@code Importo} object with the specified {@code Importo} object
     * for order. Returns a negative integer, zero, or a positive integer as
     * this object is less than, equal to, or greater than the specified object.
     * The comparison is based on the monetary value represented by the object.
     *
     * @param o the {@code Importo} object to be compared. Must not be null.
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified {@code Importo} is null.
     */
    @Override
    public int compareTo(Importo o) {
        if(o == null) throw new NullPointerException("Importo non valido");
        if(this.getValoreDouble() > o.getValoreDouble()) return 1;
        if(this.getValoreDouble() == o.getValoreDouble()) return 0;
        return -1;
    }
}
