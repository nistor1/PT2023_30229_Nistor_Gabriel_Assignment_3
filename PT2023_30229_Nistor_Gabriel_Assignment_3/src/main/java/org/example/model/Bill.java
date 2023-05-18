package org.example.model;

public record Bill(int id, String name, String productName, int quantity) {
    /**
     * @param id
     * @param name
     * @param productName
     * @param quantity
     */
    public Bill {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be less than or equal to zero");
        }
    }
}
