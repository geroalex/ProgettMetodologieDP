package it.unicam.cs.mpgc.jbudget.grafica;

import it.unicam.cs.mpgc.jbudget.gestione.ContoCorrente;
import it.unicam.cs.mpgc.jbudget.gestione.MemoriaConti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class CreatoreConto extends JFrame implements Runnable, ActionListener {

    private JPanel creatoreConto;
    private JButton creaContoBtn;
    private JTextField codiceConto;

    @Override
    public void run() {
        creatoreConto = new JPanel();

        setTitle("Creatore Conto");
        creatoreConto.setLayout(new BorderLayout(10, 10));

        setSize(new Dimension(400, 150));
        setLocation(200, 200);
        setResizable(false);

        costruisciFinestra();

        add(creatoreConto);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);


    }


    private void costruisciFinestra(){
        //ContoCorrente cc = new ContoCorrente();
        JLabel label = new JLabel("Inserisci il codice IBAN del conto");

        codiceConto = new JTextField(27);


        creaContoBtn = new JButton("Crea Conto");
        creaContoBtn.addActionListener(this);


        creatoreConto.add(label, BorderLayout.NORTH);
        creatoreConto.add(codiceConto, BorderLayout.CENTER);
        creatoreConto.add(creaContoBtn, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == creaContoBtn) {
            MemoriaConti.aggiungiConto(new ContoCorrente(codiceConto.getText()));
            this.dispose();
        }

    }
}