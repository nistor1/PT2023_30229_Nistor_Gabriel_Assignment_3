package org.example.businessLogic;

import org.example.businessLogic.validators.QuantityBillValidator;
import org.example.businessLogic.validators.Validator;
import org.example.dataAccess.AbstractDAO;
import org.example.dataAccess.BillDAO;
import org.example.dataAccess.ClientDAO;
import org.example.dataAccess.ProductDAO;
import org.example.model.Bill;
import org.example.model.Client;
import org.example.model.Orders;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderBLL {
    private List<Validator<Orders>> validators;
    private BillDAO billDAO;

    public OrderBLL() {
        validators = new ArrayList<Validator<Orders>>();
        validators.add(new QuantityBillValidator());

        billDAO = new BillDAO();
    }
    /**
     * @param id the id of the order to be found
     * @return the order with the given id
     */
    public Bill findOrderById(int id) {
        Bill or = billDAO.findById(id);
        if(or == null) {
            throw new IllegalArgumentException("The order with id =" + id + " was not found!");
        }
        return or;
    }
    /**
     * @return List<Bill> orders
     */
    public List<Bill> findAll() {
        List<Bill> orders = billDAO.findAll();
        if(orders == null) {
            throw new IllegalArgumentException("There are no orders in the database!");
        }
        return orders;
    }
    /**
     * @param t the order to be inserted
     * @return the inserted order
     */
    public Bill insert(Orders t) {
        for(Validator<Orders> v : validators) {
            v.validate(t);
        }

        AbstractDAO<Client> clientDAO = new ClientDAO();
        AbstractDAO<Product> productDAO = new ProductDAO();
        Client client = clientDAO.findById(t.getId_c());
        Product product = productDAO.findById(t.getId_p());

        product.setStock_quantity(product.getStock_quantity() - t.getQuantity());
        productDAO.update(product);

        Bill bill = new Bill(0, client.getName(), product.getBrand() + " " + product.getDescription(), t.getQuantity());

        return billDAO.insert(bill);
    }

}
