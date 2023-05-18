package org.example.businessLogic.validators;

import org.example.model.Client;

public class AgeValidator implements Validator<Client>{
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 157;

    public void validate(Client t) {

        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("Age limit is not respected!");
        }

    }

}
