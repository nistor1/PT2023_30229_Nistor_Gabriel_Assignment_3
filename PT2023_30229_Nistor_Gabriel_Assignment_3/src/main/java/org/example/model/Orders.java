package org.example.model;

import org.example.dataAccess.AbstractDAO;
import org.example.dataAccess.ClientDAO;
import org.example.dataAccess.BillDAO;
import org.example.dataAccess.ProductDAO;

public class Orders {
    int id;
    private int id_c;
    private int id_p;
    private int quantity;
    private String name;
    private String productName;
    private Bill bill;

    public Orders() {}
    /**
     * @param id_c
     * @param id_p
     * @param quantity
     */
    public Orders(int id_c, int id_p, int quantity) {
        this.id_c = id_c;
        this.id_p = id_p;
        this.quantity = quantity;
    }



    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Bill getBill() {
        return bill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
