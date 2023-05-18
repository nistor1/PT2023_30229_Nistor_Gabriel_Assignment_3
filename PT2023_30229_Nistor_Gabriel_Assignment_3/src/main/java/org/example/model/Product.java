package org.example.model;

public class Product {
    private int id;
    private String brand;
    private String description;
    private int stock_quantity;
    private double price_c;

    public Product() {}
    /**
     * @param id
     * @param brand
     * @param description
     * @param stock_quantity
     * @param price_c
     */
    public Product(int id, String brand, String description, int stock_quantity, double price_c) {
        this.id = id;
        this.brand = brand;
        this.description = description;
        this.stock_quantity = stock_quantity;
        this.price_c = price_c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public double getPrice_c() {
        return price_c;
    }

    public void setPrice_c(double price_c) {
        this.price_c = price_c;
    }
    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + id +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", stockQuantity=" + stock_quantity +
                ", price=" + price_c +
                '}';
    }
}
