package org.example.presentation.pages;

import org.example.model.Client;
import org.example.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

public class EditProductsPage extends Page {
    JButton button;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;

    List<Product> productList;
    public EditProductsPage( List<Product> productList) {
        this.productList = productList;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 650);
        this.setLayout(new BorderLayout());

        this.getContentPane().setBackground(Color.getColor("F5F5F5"));

        super.setPanelNorth(createPanelNorth());
        super.setPanelCenter(createPanelCenter());

        this.add(getPanelNorth(), BorderLayout.NORTH);
        this.add(getPanelCenter(), BorderLayout.CENTER);

        this.setVisible(true);
    }

    public JPanel createPanelNorth() {
        insertButton = newButton("Insert");
        updateButton = newButton("Update");
        deleteButton = newButton("Delete");

        JLabel labelTextField1 = new JLabel("Id product");
        labelTextField1.setHorizontalAlignment(JLabel.CENTER);
        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(100, 30));
        textField1.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel labelTextField2 = new JLabel("Brand");
        labelTextField2.setHorizontalAlignment(JLabel.CENTER);
        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(100, 30));
        textField2.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel labelTextField3 = new JLabel("Description");
        labelTextField3.setHorizontalAlignment(JLabel.CENTER);
        textField3 = new JTextField();
        textField3.setPreferredSize(new Dimension(100, 30));
        textField3.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel labelTextField4 = new JLabel("Stock");
        labelTextField4.setHorizontalAlignment(JLabel.CENTER);
        textField4 = new JTextField();
        textField4.setPreferredSize(new Dimension(100, 30));
        textField4.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel labelTextField5 = new JLabel("Price");
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
        JTable table = new JTable();
        RefelctionJTable reflectionJTable3 = new RefelctionJTable();
        table = reflectionJTable3.createJTableFromList(productList);
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
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

    public List<Product> getProductList() {
        return productList;
    }
}
