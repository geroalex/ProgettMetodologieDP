package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;

import java.time.LocalDate;

public class MovimentoSemplice extends Movimento{

    public MovimentoSemplice(Importo importo) {
        super(importo);
    }

    public MovimentoSemplice(Importo importo, String tipoMovimento) {
        super(importo, tipoMovimento);
    }

    public MovimentoSemplice(Importo importo, String tipoMovimento, LocalDate data) {
        super(importo, data, tipoMovimento);
    }

}
