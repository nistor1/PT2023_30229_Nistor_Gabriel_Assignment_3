package org.example.presentation;

import org.example.businessLogic.ClientBLL;
import org.example.businessLogic.OrderBLL;
import org.example.businessLogic.ProductBLL;
import org.example.presentation.pages.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
public class Controller {
    protected View view;
    protected ClientBLL clientBLL;
    protected OrderBLL orderBLL;
    protected ProductBLL productBLL;
    private HomePageController homePageController;
    private EditOrdersPageConroller editOrdersPageController;
    private EditClientsPageController editClientsPageController;
    private EditProductsPageController editProductsPageController;

    public Controller() {

    }

    public Controller(ClientBLL clientBLL, ProductBLL productBLL, OrderBLL orderBLL) {
        this.clientBLL = clientBLL;
        this.orderBLL = orderBLL;
        this.productBLL = productBLL;

        view = new View(1);

        setCalculatorListeners(view.getPage());
    }
    /**
     * Set the listeners for the HomePage
     *
     * @param  view is the current view
     */
    public void addActionsHomePage(HomePage view) {
        homePageController = new HomePageController(view);
        view.getButton1().addActionListener(homePageController);
        view.getButton2().addActionListener(homePageController);
        view.getButton3().addActionListener(homePageController);
        view.getExitButton().addActionListener(homePageController);
    }
    /**
     * Set the listeners for the EditOrdersPage
     *
     * @param  view is the current view
     */
    public void addActionsEditOrdersPage(EditOrdersPage view) {
        editOrdersPageController = new EditOrdersPageConroller(view);
        view.getButton().addActionListener(editOrdersPageController);
        view.getInsertButton().addActionListener(editOrdersPageController);
    }
    /**
     * Set the listeners for the EditClientsPage
     *
     * @param  view is the current view
     */
    public void addActionsEditClientsPage(EditClientsPage view) {
        editClientsPageController = new EditClientsPageController(view);
        view.getButton().addActionListener(editClientsPageController);
        view.getInsertButton().addActionListener(editClientsPageController);
        view.getDeleteButton().addActionListener(editClientsPageController);
        view.getUpdateButton().addActionListener(editClientsPageController);
        view.getTextField1().addActionListener(editClientsPageController);
        view.getTextField2().addActionListener(editClientsPageController);
        view.getTextField3().addActionListener(editClientsPageController);
        view.getTextField4().addActionListener(editClientsPageController);
        view.getTextField5().addActionListener(editClientsPageController);
    }
    /**
     * Set the listeners for the EditProductsPage
     *
     * @param  view is the current view
     */
    public void addActionsEditProductsPage(EditProductsPage view) {
        editProductsPageController = new EditProductsPageController(view);
        view.getButton().addActionListener(editProductsPageController);
        view.getInsertButton().addActionListener(editProductsPageController);
        view.getDeleteButton().addActionListener(editProductsPageController);
        view.getUpdateButton().addActionListener(editProductsPageController);
        view.getTextField1().addActionListener(editProductsPageController);
        view.getTextField2().addActionListener(editProductsPageController);
        view.getTextField3().addActionListener(editProductsPageController);
        view.getTextField4().addActionListener(editProductsPageController);
        view.getTextField5().addActionListener(editProductsPageController);
    }
    /**
     * Set the listeners for the buttons
     *
     * @param  view is the current view
     */
    public void setCalculatorListeners(Page view){
        if(view instanceof HomePage) {
            addActionsHomePage((HomePage) view);
        } else if(view instanceof EditOrdersPage) {
            addActionsEditOrdersPage((EditOrdersPage) view);
        } else if(view instanceof EditClientsPage) {
            addActionsEditClientsPage((EditClientsPage) view);
        } else if(view instanceof EditProductsPage) {
            addActionsEditProductsPage((EditProductsPage) view);
        }
    }
}
