package org.example.businessLogic.validators;

import org.example.model.Product;

public class ProductNameValidator  implements Validator<Product>{

    private static final int MAX_NAME_LENGTH = 90;

    public void validate(Product product) {
        if (product.getBrand() == null || product.getBrand().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty");
        }
        if (product.getBrand().length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Brand cannot exceed " + MAX_NAME_LENGTH + " characters");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (product.getDescription().length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Description cannot exceed " + MAX_NAME_LENGTH + " characters");
        }
    }
}
