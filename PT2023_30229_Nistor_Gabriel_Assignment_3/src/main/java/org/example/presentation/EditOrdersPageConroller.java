package org.example.presentation;

import org.example.businessLogic.ClientBLL;
import org.example.businessLogic.OrderBLL;
import org.example.businessLogic.ProductBLL;
import org.example.model.Client;
import org.example.model.Orders;
import org.example.presentation.pages.EditClientsPage;
import org.example.presentation.pages.EditOrdersPage;
import org.example.presentation.pages.HomePage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditOrdersPageConroller  extends Controller implements ActionListener {
    EditOrdersPage view;
    ClientBLL clientBLL = new ClientBLL();
    ProductBLL productBLL = new ProductBLL();
    OrderBLL orderBLL = new OrderBLL();
    public EditOrdersPageConroller(EditOrdersPage view) {
        super();
        this.view = view;
    }

    @Override
    /**
     * Set the listeners for the buttons
     *
     * @param  e is the event
     */
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == view.getInsertButton()) {
            String q = view.getTextField1().getText();

            Integer quantity = Integer.parseInt(q);
            JTable table1 = view.getTable1();
            JTable table2 = view.getTable2();
            DefaultTableModel model1 = (DefaultTableModel) table1.getModel();

            // Obțineți rândul selectat din tabelul 1
            int selectedRow1 = table1.getSelectedRow();
            int id_client = 0;
            int id_product = 0;
            // Verificați dacă a fost selectat un rând valid în tabelul 1
            if (selectedRow1 >= 0) {
                Object value1 = model1.getValueAt(selectedRow1, 0);
                id_client = Integer.parseInt(value1.toString());
                Object value2 = model1.getValueAt(selectedRow1, 1);
                Object value3 = model1.getValueAt(selectedRow1, 2);
                Object value4 = model1.getValueAt(selectedRow1, 3);
                Object value5 = model1.getValueAt(selectedRow1, 4);
            }

            // Obțineți modelul de tabel pentru tabelul 2
            DefaultTableModel model2 = (DefaultTableModel) table2.getModel();

            // Obțineți rândul selectat din tabelul 2
            int selectedRow2 = table2.getSelectedRow();

            // Verificați dacă a fost selectat un rând valid în tabelul 2
            if (selectedRow2 >= 0) {
                Object value6 = model2.getValueAt(selectedRow2, 0);
                id_product = Integer.parseInt(value6.toString());
                Object value7 = model2.getValueAt(selectedRow2, 1);
                Object value8 = model2.getValueAt(selectedRow2, 2);
                Object value9 = model2.getValueAt(selectedRow2, 3);
                Object value10 = model2.getValueAt(selectedRow2, 4);
                orderBLL.insert(new Orders(id_client,id_product, quantity));
            }
        } else if(e.getSource() == view.getButton()){
            view.dispose();

            setCalculatorListeners( new HomePage());
            return;
        }
        view.dispose();
        view = new EditOrdersPage(clientBLL.findAll(), productBLL.findAll(), orderBLL.findAll());
        setCalculatorListeners(view);
    }

}
