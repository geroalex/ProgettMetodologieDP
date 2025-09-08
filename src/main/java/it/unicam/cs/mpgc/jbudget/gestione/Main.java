package it.unicam.cs.mpgc.jbudget.gestione;

import it.unicam.cs.mpgc.jbudget.movimenti.MovimentoSemplice;
import it.unicam.cs.mpgc.jbudget.valori.Importo;

public class Main {

    public static void main(String[] args){
        ContoCorrente cc = new ContoCorrente();
        MovimentoSemplice acquistoCasa = new MovimentoSemplice(new Importo(1.0), "Cazzo");
        cc.aggiungiMovimento(acquistoCasa);

        System.out.println(cc.getMovimenti().get(0).toString());


    }

}
