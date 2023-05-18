package org.example.presentation;

import org.example.businessLogic.ClientBLL;
import org.example.businessLogic.OrderBLL;
import org.example.businessLogic.ProductBLL;
import org.example.model.Client;
import org.example.presentation.pages.EditClientsPage;
import org.example.presentation.pages.HomePage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditClientsPageController extends Controller implements ActionListener  {
    EditClientsPage view;
    ClientBLL clientBLL = new ClientBLL();
    ProductBLL productBLL = new ProductBLL();
    OrderBLL orderBLL = new OrderBLL();
    public EditClientsPageController(EditClientsPage view) {
        super();
        this.view = view;
    }

    @Override
    /**
     * Set the listeners for the buttons
     *
     * @param  e is the event
     */
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == view.getInsertButton()) {
            String id = view.getTextField1().getText();
            String name = view.getTextField2().getText();
            String address = view.getTextField3().getText();
            String email = view.getTextField4().getText();
            String age = view.getTextField5().getText();

            Integer idClient = Integer.parseInt(id);
            Integer ageClient = Integer.parseInt(age);

            clientBLL.insert(new Client(idClient, name, address, email, ageClient));
        } else if(e.getSource() == view.getUpdateButton()){
            String id = view.getTextField1().getText();
            String name = view.getTextField2().getText();
            String address = view.getTextField3().getText();
            String email = view.getTextField4().getText();
            String age = view.getTextField5().getText();

            Integer idClient = Integer.parseInt(id);
            Integer ageClient = Integer.parseInt(age);
            clientBLL.update(new Client(idClient, name, address, email, ageClient));
        } else if(e.getSource() == view.getDeleteButton()){
            String id = view.getTextField1().getText();
            int idClient = Integer.parseInt(id);
            clientBLL.delete(idClient);
        } else if(e.getSource() == view.getButton()){
            view.dispose();

            setCalculatorListeners( new HomePage());
            return;
        }
        view.dispose();
        view = new EditClientsPage(clientBLL.findAll());
        setCalculatorListeners(view);
        }
}

