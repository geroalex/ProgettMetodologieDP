package it.unicam.cs.mpgc.jbudget.grafica;

import it.unicam.cs.mpgc.jbudget.gestione.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class BarraDegliStrumenti extends JToolBar implements ActionListener {

    private JButton pulsanteCerca;
    private JTextField giorno;
    private JTextField mese;
    private JTextField anno;
    private MenuPrincipale appartenenza;

    public BarraDegliStrumenti(MenuPrincipale appartenenza){
        this.appartenenza = appartenenza;
        pulsanteCerca = new JButton("Cerca");
        giorno = new JTextField("Giorno");
        mese = new JTextField("Mese");
        anno = new JTextField("Anno");

        pulsanteCerca.setPreferredSize(new Dimension(150, 30));



        pulsanteCerca.addActionListener(this);

        add(pulsanteCerca);
        addSeparator();
        add(giorno);
        add(mese);
        add(anno);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == pulsanteCerca){
            System.out.println("Cerca");
            int anno = Integer.parseInt(this.anno.getText());
            int mese = Integer.parseInt(this.mese.getText());
            int giorno = Integer.parseInt(this.giorno.getText());
            //System.out.println("Cerca: " + anno + "/" + mese + "/" + giorno);
            if(!(0 < anno && anno < 9999)) throw new IllegalArgumentException("Anno non valido");
            if(!(0 < mese && mese < 13)) throw new IllegalArgumentException("Mese non valido");
            LocalDate data = LocalDate.of(anno, mese, 1);
            if(!(0 < giorno && giorno < data.lengthOfMonth())) throw new IllegalArgumentException("Giorno non valido");

            appartenenza.scrivi(Main.cc.getMovimentiAl(LocalDate.of(anno, mese, giorno)).toString());


        }
    }


}
