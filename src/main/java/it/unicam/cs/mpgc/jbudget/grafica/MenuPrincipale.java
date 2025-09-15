package it.unicam.cs.mpgc.jbudget.grafica;

import it.unicam.cs.mpgc.jbudget.gestione.ContoCorrente;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipale extends JPanel {



    public MenuPrincipale(){

        setLayout(new BorderLayout());

        JTextArea txtArea = new JTextArea("Risultati");
        BarraDegliStrumenti barraDiMenu = new BarraDegliStrumenti(this);
        barraDiMenu.setFloatable(false);

        add(barraDiMenu, BorderLayout.PAGE_START);
        add(txtArea, BorderLayout.CENTER);

        setVisible(true);
    }

    public void scrivi(String s){
        JTextArea txtArea = (JTextArea) getComponent(1);
        txtArea.append(s + "\n");
    }

}
