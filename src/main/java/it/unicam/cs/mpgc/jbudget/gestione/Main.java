package it.unicam.cs.mpgc.jbudget.gestione;

import it.unicam.cs.mpgc.jbudget.movimenti.MovimentoSemplice;
import it.unicam.cs.mpgc.jbudget.movimenti.Mutuo;
import it.unicam.cs.mpgc.jbudget.movimenti.Stipendio;
import it.unicam.cs.mpgc.jbudget.movimenti.Tags;
import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args){
        ContoCorrente cc = new ContoCorrente();

        MovimentoSemplice acquistoCasa = new MovimentoSemplice(new Importo(-1.0), "Casa");
        acquistoCasa.aggiungiTag(new Tags("Casa", 1));

        MovimentoSemplice acquistoSport = new MovimentoSemplice(new Importo(-1.0), "Sport");
        acquistoSport.aggiungiTag(new Tags("Sport", 2));

        MovimentoSemplice ricevutaOccasionale = new MovimentoSemplice(new Importo(100.0), "Lavoro");
        ricevutaOccasionale.aggiungiTag(new Tags("Lavoro", 3));

        cc.aggiungiMovimento(acquistoCasa);
        cc.aggiungiMovimento(acquistoSport);
        cc.aggiungiMovimento(ricevutaOccasionale);

        cc.versa(new Importo(1000.0));


        System.out.println(cc.getMovimenti().getFirst().toString());
        System.out.println(cc.getSaldo());
        System.out.println("----------------------------------------");
        //cc.contabilizza(0);
        System.out.println(cc.getMovimenti().getFirst().toString());
        System.out.println(cc.getSaldo());
        System.out.println("----------------------------------------");

        System.out.println(cc.getMovimentiOrdinati().toString());

        //System.out.println(cc.getMovimenti().toString());

    }

}
