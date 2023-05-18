package org.example.presentation.pages;

import org.example.presentation.View;

import javax.swing.*;
import java.awt.*;

public abstract class Page extends View {
    private JPanel panelNorth;
    private JPanel panelCenter;
    private JPanel panelSouth;
    private JButton homeButton;
    private JButton backButton;


    public abstract JPanel createPanelCenter();

    //fiecare frame se imparte in trei paneluri(North, Central, South).
    public JPanel createPanelNorth() {
        //Se creeaza panelul din Nord, cu un buton de Home.
        homeButton = newHomeButton();

        JPanel panel = new JPanel();
        panel.setBackground(Color.getColor("F5F5F5"));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setPreferredSize(new Dimension(10, 130));

        panel.add(homeButton);

        return panel;
    }
    public JPanel createPanelSouth() {
        //Se creaza panelul din Sud, cu un buton de Back, care va duce utilizatorul inapoi.
        JPanel back = new JPanel();
        back.setLayout(new FlowLayout(FlowLayout.CENTER));
        back.setPreferredSize(new Dimension(20, 53));
        backButton = newButton("Back");
        backButton.setPreferredSize(new Dimension(150, 35));
        back.add(backButton);
        return back;
    }

    public JButton newHomeButton() {
        //Acesta este butonul de Home, acesta contine o imagine si un text.

        JButton button = newButton("ATELIER REPARATII");
        button.setFont(new Font("Serif", Font.PLAIN, 20));
        button.setHorizontalTextPosition(JButton.RIGHT);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setContentAreaFilled(false);
        button.setBackground(Color.getColor("F5F5F5"));

        return button;
    }
    public JButton newButton(String text) {
        //Functia generica de creare a unui buton.
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setBackground(Color.lightGray);
        button.setBorderPainted(false);

        return button;
    }
    public JPanel getPanelNorth() {
        return panelNorth;
    }

    public void setPanelNorth(JPanel panelNorth) {
        this.panelNorth = panelNorth;
    }

    public JPanel getPanelCenter() {
        return panelCenter;
    }

    public void setPanelCenter(JPanel panelCenter) {
        this.panelCenter = panelCenter;
    }

    public JPanel getPanelSouth() { return panelSouth; }

    public void setPanelSouth(JPanel panelSouth) { this.panelSouth = panelSouth; }

}
