package org.example.businessLogic.validators;

import org.example.businessLogic.ClientBLL;
import org.example.businessLogic.ProductBLL;
import org.example.dataAccess.AbstractDAO;
import org.example.dataAccess.BillDAO;
import org.example.dataAccess.ClientDAO;
import org.example.dataAccess.ProductDAO;
import org.example.model.Bill;
import org.example.model.Client;
import org.example.model.Orders;
import org.example.model.Product;

public class QuantityBillValidator implements Validator<Orders>{
    private static final int MAX_QUANTITY = 1000;

    public void validate(Orders t) {

        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        Client client = clientBLL.findClientById(t.getId_c());
        Product product = productBLL.findProductById(t.getId_p());
        if(product.getStock_quantity() < t.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock available");
        }
    }

}
