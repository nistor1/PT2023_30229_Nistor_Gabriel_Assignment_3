package org.example.businessLogic;

import org.example.businessLogic.validators.AgeValidator;
import org.example.businessLogic.validators.ClientNameValidator;
import org.example.businessLogic.validators.EmailValidator;
import org.example.businessLogic.validators.Validator;
import org.example.dataAccess.ClientDAO;
import org.example.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new AgeValidator());
        validators.add(new EmailValidator());
        validators.add(new ClientNameValidator());

        clientDAO = new ClientDAO();
    }
    /**
     * @param id the id of the client to be found
     * @return the client with the given id
     */
    public Client findClientById(int id) {
        Client cl = clientDAO.findById(id);
        if(cl == null) {
            throw new IllegalArgumentException("The client with id =" + id + " was not found!");
        }
        return cl;
    }
    /**
     * @return List<Client> clients
     */
    public List<Client> findAll() {
        List<Client> clients = clientDAO.findAll();
        if(clients == null) {
            throw new IllegalArgumentException("There are no clients in the database!");
        }
        return clients;
    }
    /**
     * @param t the client to be inserted
     * @return the inserted client
     */
    public Client insert(Client t) {
        for(Validator<Client> v : validators) {
            v.validate(t);
        }
        return clientDAO.insert(t);
    }
    /**
     * @param t the client to be updated
     * @return the updated client
     */
    public Client update(Client t) {
        for(Validator<Client> v : validators) {
            v.validate(t);
        }
        return clientDAO.update(t);
    }
    /**
     * @param id the id of the client to be deleted
     */
    public void delete(int id) {
        Client cl = clientDAO.findById(id);
        if(cl == null) {
            throw new IllegalArgumentException("The client with id =" + id + " was not found!");
        }
        clientDAO.delete(id);
    }
}

