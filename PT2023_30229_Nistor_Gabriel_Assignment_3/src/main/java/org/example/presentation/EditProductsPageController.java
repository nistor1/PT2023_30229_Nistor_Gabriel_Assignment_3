package org.example.presentation;

import org.example.businessLogic.ClientBLL;
import org.example.businessLogic.OrderBLL;
import org.example.businessLogic.ProductBLL;
import org.example.model.Product;
import org.example.presentation.pages.EditProductsPage;
import org.example.presentation.pages.HomePage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EditProductsPageController extends Controller implements ActionListener {
    EditProductsPage view;
    ClientBLL clientBLL = new ClientBLL();
    ProductBLL productBLL = new ProductBLL();
    OrderBLL orderBLL = new OrderBLL();
    public EditProductsPageController(EditProductsPage view) {
        super();
        this.view = view;
    }
    /**
     * Set the listeners for the buttons
     *
     * @param  e is the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == view.getInsertButton()) {
            String id = view.getTextField1().getText();
            String brand = view.getTextField2().getText();
            String description = view.getTextField3().getText();
            String stock = view.getTextField4().getText();
            String price = view.getTextField5().getText();

            Integer idProduct = Integer.parseInt(id);
            Integer stockProduct = Integer.parseInt(stock);
            Double priceProduct = Double.parseDouble(price);

            productBLL.insert(new Product(idProduct, brand, description, stockProduct, priceProduct));
        } else if(e.getSource() == view.getUpdateButton()){
            String id = view.getTextField1().getText();
            String brand = view.getTextField2().getText();
            String description = view.getTextField3().getText();
            String stock = view.getTextField4().getText();
            String price = view.getTextField5().getText();

            Integer idProduct = Integer.parseInt(id);
            Integer stockProduct = Integer.parseInt(stock);
            Double priceProduct = Double.parseDouble(price);

            productBLL.update(new Product(idProduct, brand, description, stockProduct, priceProduct));
        } else if(e.getSource() == view.getDeleteButton()){
            String id = view.getTextField1().getText();
            int idP = Integer.parseInt(id);
            productBLL.delete(idP);
        } else if(e.getSource() == view.getButton()){
            view.dispose();

            setCalculatorListeners( new HomePage());
            return;
        }
        view.dispose();
        view = new EditProductsPage(productBLL.findAll());
        setCalculatorListeners(view);
    }

}

