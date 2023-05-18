package org.example.businessLogic.validators;

import org.example.model.Product;

public class PriceValidator implements Validator<Product>{
    public void validate(Product product) {
        if (product.getPrice_c() < 0) {
            throw new IllegalArgumentException("Price must be positive!");
        }
    }
}
