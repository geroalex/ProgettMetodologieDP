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
        MovimentoSemplice acquistoCasa = new MovimentoSemplice(new Importo(-1.0), "Cazzo");
        acquistoCasa.aggiungiTag(new Tags("Casa", 1));
        cc.aggiungiMovimento(acquistoCasa);

        cc.versa(new Importo(1000.0));


        System.out.println(cc.getMovimenti().getFirst().toString());
        System.out.println(cc.getSaldo());
        System.out.println("----------------------------------------");
        //cc.contabilizza(0);
        System.out.println(cc.getMovimenti().getFirst().toString());
        System.out.println(cc.getSaldo());
        System.out.println("----------------------------------------");


        Mutuo mutuoCasa = new Mutuo(new Importo(10000), 4.0f, 48);
        //System.out.println(mutuoCasa.toString());

        cc.creaMovimentoProgrammato(mutuoCasa);


        cc.contabilizza(1);
        System.out.println(cc.getMovimenti().toString());

        System.out.println(cc.getSaldo());


        Stipendio stipendio = new Stipendio(LocalDate.now(), new Importo(1200));
        System.out.println(stipendio.toString());


    }

}
