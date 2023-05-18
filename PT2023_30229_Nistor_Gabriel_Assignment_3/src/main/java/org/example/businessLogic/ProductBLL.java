package org.example.businessLogic;

import org.example.businessLogic.validators.PriceValidator;
import org.example.businessLogic.validators.ProductNameValidator;
import org.example.businessLogic.validators.QuantityValidator;
import org.example.businessLogic.validators.Validator;
import org.example.dataAccess.ProductDAO;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new QuantityValidator());
        validators.add(new PriceValidator());
        validators.add(new ProductNameValidator());

        productDAO = new ProductDAO();
    }
    /**
     * @param id
     * @return
     */
    public Product findProductById(int id) {
        Product pr = productDAO.findById(id);
        if(pr == null) {
            throw new IllegalArgumentException("The product with id =" + id + " was not found!");
        }
        return pr;
    }
    /**
     * @return List<Product> products
     */
    public List<Product> findAll() {
        List<Product> products = productDAO.findAll();
        if(products == null) {
            throw new IllegalArgumentException("There are no products in the database!");
        }
        return products;
    }
    /**
     * @param t the product to be inserted
     * @return the inserted product
     */
    public Product insert(Product t) {
        for(Validator<Product> v : validators) {
            v.validate(t);
        }
        return productDAO.insert(t);
    }
    /**
     * @param t the product to be updated
     * @return the updated product
     */
    public Product update(Product t) {
        for(Validator<Product> v : validators) {
            v.validate(t);
        }
        return productDAO.update(t);
    }
    /**
     * @param id the id of the product to be deleted
     */
    public void delete(int id) {
        Product pr = productDAO.findById(id);
        if(pr == null) {
            throw new IllegalArgumentException("The product with id =" + id + " was not found!");
        }
        productDAO.delete(id);
    }


}
