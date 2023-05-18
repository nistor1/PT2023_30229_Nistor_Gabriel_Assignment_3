package org.example.presentation;

import org.example.businessLogic.ClientBLL;
import org.example.businessLogic.OrderBLL;
import org.example.businessLogic.ProductBLL;
import org.example.presentation.pages.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class HomePageController extends Controller implements ActionListener {
    Page view;
    ClientBLL clientBLL = new ClientBLL();
    ProductBLL productBLL = new ProductBLL();
    OrderBLL orderBLL = new OrderBLL();
    public HomePageController(Page view) {
        super();
        this.view = view;
    }

    /**
     * Set the listeners for the buttons
     *
     * @param  view is the view to be set
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        HomePage view = (HomePage) this.view;
        if(e.getSource() == view.getButton1()) {
            view.dispose();
            this.view = new EditClientsPage(clientBLL.findAll());
        } else if(e.getSource() == view.getButton2()) {
            view.dispose();
            this.view = new EditProductsPage(productBLL.findAll());
        } else if(e.getSource() == view.getButton3()) {
            view.dispose();
            this.view = new EditOrdersPage(clientBLL.findAll(), productBLL.findAll(), orderBLL.findAll());
        } else if(e.getSource() == view.getExitButton()) {
            view.dispose();
            System.exit(0);
        }
        setCalculatorListeners(this.view);
    }

}