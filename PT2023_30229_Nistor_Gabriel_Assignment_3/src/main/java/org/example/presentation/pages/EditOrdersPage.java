package org.example.presentation.pages;

import org.example.model.Bill;
import org.example.model.Client;
import org.example.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class EditOrdersPage extends Page {
    JButton button;
    private JButton insertButton;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JTextField textField1;
    List<Product> productList;
    List<Client> clientList;
    List<Bill> billList;
    public EditOrdersPage(  List<Client> clientList, List<Product> productList, List<Bill> billList) {
        this.productList = productList;
        this.clientList = clientList;
        this.billList = billList;

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
        insertButton = newButton("                                                      Insert                                                    ");
        JPanel panel = new JPanel();

        panel.add(insertButton);

        panel.setBackground(Color.getColor("F5F5F5"));
        panel.setPreferredSize(new Dimension(100, 400));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelTextField1 = new JLabel("Quantity");
        labelTextField1.setHorizontalAlignment(JLabel.CENTER);
        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(100, 30));
        textField1.setFont(new Font("Serif", Font.PLAIN, 20));

        panel.add(labelTextField1);
        panel.add(textField1);
        panel.add(new JLabel(" "));
        panel.add(new JLabel(" "));
        //ResultSetTableModel resultSetTableModel = new ResultSetTableModel(result);

        //Instantierea unui obiect JTable si a unui model.
        RefelctionJTable reflectionJTable = new RefelctionJTable();
        table1 = reflectionJTable.createJTableFromList(clientList);

        JScrollPane scrollPane = new JScrollPane(table1);

        panel.add(scrollPane);

        //ResultSetTableModel resultSetTableModel = new ResultSetTableModel(result);

        //Instantierea unui obiect JTable si a unui model.
        RefelctionJTable reflectionJTable1 = new RefelctionJTable();
        table2 = reflectionJTable.createJTableFromList(productList);

        JScrollPane scrollPane2 = new JScrollPane(table2);

        panel.add(scrollPane2);
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
        RefelctionJTable reflectionJTable3 = new RefelctionJTable();
        table3 = reflectionJTable3.createJTableFromList(billList);

        JScrollPane scrollPane = new JScrollPane(table3);

        panel.add(scrollPane);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }
    public void addBackToHomeButtonListener(ActionListener e) {
        button.addActionListener(e);
    }

    public JButton getButton() {
        return button;
    }

    public JButton getInsertButton() {
        return insertButton;
    }

    public JTable getTable1() {
        return table1;
    }

    public JTable getTable2() {
        return table2;
    }

    public JTable getTable3() {
        return table3;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public List<Bill> getBillList() {
        return billList;
    }
}
