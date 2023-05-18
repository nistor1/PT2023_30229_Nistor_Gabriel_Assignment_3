package org.example.businessLogic.validators;

import org.example.model.Client;

public class ClientNameValidator implements Validator<Client>{
    private static final int MAX_NAME_LENGTH = 45;

    public void validate(Client client){
        if (client.getName() == null || client.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (client.getName().length() < 2) {
            throw new IllegalArgumentException("Name should have at least 2 characters");
        }

        if (client.getName().length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name cannot exceed " + MAX_NAME_LENGTH + " characters");
        }
        if (!client.getName().matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Name can only contain alphabetic characters and spaces");
        }

    }
}
