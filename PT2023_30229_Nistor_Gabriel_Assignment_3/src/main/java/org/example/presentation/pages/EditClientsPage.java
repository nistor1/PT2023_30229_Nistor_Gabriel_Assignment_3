package org.example.presentation.pages;

import org.example.model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

public class EditClientsPage extends Page {
    private JButton button;

    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;

    List<Client> clientList;
    public EditClientsPage( List<Client> clientList) {
        this.clientList = clientList;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 650);
        this.setLayout(new BorderLayout());

        this.getContentPane().setBackground(Color.getColor("F5F5F5"));

        super.setPanelNorth(this.createPanelNorth());
        super.setPanelCenter(createPanelCenter());

        this.add(getPanelNorth(), BorderLayout.NORTH);
        this.add(getPanelCenter(), BorderLayout.CENTER);

        this.setVisible(true);
    }

    public JPanel createPanelNorth() {
        insertButton = newButton("Insert");
        updateButton = newButton("Update");
        deleteButton = newButton("Delete");

        JLabel labelTextField1 = new JLabel("Id client");
        labelTextField1.setHorizontalAlignment(JLabel.CENTER);
        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(100, 30));
        textField1.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel labelTextField2 = new JLabel("Name");
        labelTextField2.setHorizontalAlignment(JLabel.CENTER);
        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(100, 30));
        textField2.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel labelTextField3 = new JLabel("Address");
        labelTextField3.setHorizontalAlignment(JLabel.CENTER);
        textField3 = new JTextField();
        textField3.setPreferredSize(new Dimension(100, 30));
        textField3.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel labelTextField4 = new JLabel("Email");
        labelTextField4.setHorizontalAlignment(JLabel.CENTER);
        textField4 = new JTextField();
        textField4.setPreferredSize(new Dimension(100, 30));
        textField4.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel labelTextField5 = new JLabel("Age");
        labelTextField5.setHorizontalAlignment(JLabel.CENTER);
        textField5 = new JTextField();
        textField5.setPreferredSize(new Dimension(100, 30));
        textField5.setFont(new Font("Serif", Font.PLAIN, 20));

        JPanel panel = new JPanel();
        panel.setBackground(Color.getColor("F5F5F5"));
        panel.setPreferredSize(new Dimension(200, 230));
        panel.setLayout(new GridLayout(7, 2));

        panel.add(insertButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(new JLabel(" "));

        panel.add(labelTextField1);
        panel.add(textField1);
        panel.add(labelTextField2);
        panel.add(textField2);
        panel.add(labelTextField3);
        panel.add(textField3);
        panel.add(labelTextField4);
        panel.add(textField4);
        panel.add(labelTextField5);
        panel.add(textField5);

        return panel;
    }
    public JPanel createPanelCenter() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.getColor("F5F5F5"));
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            //ResultSetTableModel resultSetTableModel = new ResultSetTableModel(result);

            //Un panel pentru butonul Back.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        button = newButton("Back");button.setPreferredSize(new Dimension(150, 35));
        buttonPanel.add(button);

        //Instantierea unui obiect JTable si a unui model.
        JTable table;

            RefelctionJTable reflectionJTable = new RefelctionJTable();
            table = reflectionJTable.createJTableFromList(clientList);
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }


    public void addBackToHomeButtonListener(ActionListener e) {
        button.addActionListener(e);
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public void setInsertButton(JButton insertButton) {
        this.insertButton = insertButton;
    }

    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public void setTextField3(JTextField textField3) {
        this.textField3 = textField3;
    }

    public void setTextField4(JTextField textField4) {
        this.textField4 = textField4;
    }

    public void setTextField5(JTextField textField5) {
        this.textField5 = textField5;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public JButton getButton() {
        return button;
    }

    public JButton getInsertButton() {
        return insertButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public List<Client> getClientList() {
        return clientList;
    }
}
