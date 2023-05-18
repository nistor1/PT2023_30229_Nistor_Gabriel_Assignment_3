package org.example.businessLogic.validators;

import org.example.model.Client;
import org.example.model.Product;

public class QuantityValidator implements Validator<Product>{
    private static final int MAX_QUANTITY = 1000;

    public void validate(Product t) {

        if (t.getStock_quantity() < 1 || t.getStock_quantity() > MAX_QUANTITY) {
            throw new IllegalArgumentException("Quantity limit is not respected!");
        }

    }

}
