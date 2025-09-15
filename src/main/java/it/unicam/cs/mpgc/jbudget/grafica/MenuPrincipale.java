package it.unicam.cs.mpgc.jbudget.grafica;

import it.unicam.cs.mpgc.jbudget.gestione.ContoCorrente;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipale extends JPanel {

    private JTextArea txtArea;

    public MenuPrincipale(){

        setLayout(new BorderLayout());

        txtArea = new JTextArea("Risultati");
        BarraDegliStrumenti barraDiMenu = new BarraDegliStrumenti(this);
        barraDiMenu.setFloatable(false);

        add(barraDiMenu, BorderLayout.PAGE_START);
        add(txtArea, BorderLayout.CENTER);

        setVisible(true);
    }

    public void scrivi(String s){
        txtArea.append(s + "\n");
    }

    public void cancellaEScrivi(String s){
        txtArea.setText(s);
    }

}
