package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;

public class MovimentoSemplice extends Movimento{

    public MovimentoSemplice(Importo importo) {
        super(importo);
    }

    public MovimentoSemplice(Importo importo, String tipoMovimento) {
        super(importo, tipoMovimento);
    }


}
