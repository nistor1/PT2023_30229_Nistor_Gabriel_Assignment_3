package org.example;

import org.example.businessLogic.ClientBLL;
import org.example.businessLogic.OrderBLL;
import org.example.businessLogic.ProductBLL;
import org.example.presentation.Controller;

public class Main {
    public static void main(String[] args) {
        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        OrderBLL orderBLL = new OrderBLL();

        Controller controller = new Controller(clientBLL, productBLL, orderBLL);


    }
}