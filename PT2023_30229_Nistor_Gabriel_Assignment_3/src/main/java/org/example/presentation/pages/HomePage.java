package org.example.presentation.pages;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomePage extends Page {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton exitButton;
    public HomePage() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLocationRelativeTo(null); //set frame location in the center of the screen
        this.setSize(500, 650);
        this.setLayout(new BorderLayout());

        this.getContentPane().setBackground(Color.getColor("F5F5F5"));

        super.setPanelNorth(createPanelNorth());
        super.setPanelCenter(createPanelCenter());
        super.setPanelSouth(createPanelSouth());

        this.add(getPanelNorth(), BorderLayout.NORTH);
        this.add(getPanelCenter(), BorderLayout.CENTER);
        this.add(getPanelSouth(), BorderLayout.SOUTH);

        this.setVisible(true);
    }
    /**
     * Return the panelSouth
     *
     * @return  panelSouth to be returned
     */
    @Override
    public JPanel createPanelSouth() {
        //Aceasta metoda suprascrie metoda din clasa parinte, si implementeaza butonul exit
        JPanel exit = new JPanel();
        exit.setLayout(new FlowLayout(FlowLayout.CENTER));
        exitButton = newButton("EXIT");
        exit.add(exitButton);
        return exit;
    }
    /**
     * Return the panelNorth
     *
     * @return  panelNorth to be returned
     */
    @Override
    public JPanel createPanelNorth() {

        JLabel logoLabel = new JLabel();
        logoLabel.setText("BAZA DE DATE");
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setVerticalAlignment(JLabel.NORTH);

        logoLabel.setHorizontalTextPosition(JLabel.CENTER);
        logoLabel.setVerticalTextPosition(JLabel.BOTTOM);
        logoLabel.setFont(new Font("Serif", Font.PLAIN, 30));

        JPanel panel = new JPanel();
        panel.setBackground(Color.getColor("F5F5F5"));
        panel.setPreferredSize(new Dimension(100, 230));
        panel.add(logoLabel);

        return panel;
    }
    /**
     * Return the panelCenter
     *
     * @return  panelCenter to be returned
     */
    public JPanel createPanelCenter() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.getColor("F5F5F5"));
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));

        button1 = newButton("                          Clients                         ");
        button2 = newButton("                         Products                       ");
        button3 = newButton("                          Orders                          ");




        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        return panel;
    }
    public JButton getButton1() {
        return button1;
    }
    public JButton getButton2() {
        return button2;
    }
    public JButton getButton3() {
        return button3;
    }
    public JButton getExitButton() {
        return exitButton;
    }
}

