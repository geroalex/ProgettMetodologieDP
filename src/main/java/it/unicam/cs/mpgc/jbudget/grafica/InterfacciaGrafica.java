package it.unicam.cs.mpgc.jbudget.grafica;

import it.unicam.cs.mpgc.jbudget.gestione.ContoCorrente;

import javax.swing.*;
import java.awt.*;

public class InterfacciaGrafica extends JFrame implements Runnable {



    @Override
    public void run() {
        MenuPrincipale menu = new MenuPrincipale();


        setTitle("JBudget");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 400));

        setLocation(200, 200);

        setResizable(false);
        add(menu);

        setVisible(true);


    }



}
