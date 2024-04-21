package com.appli_banking.bankingAPP.validators;

import com.appli_banking.bankingAPP.exceptions.ObjectValidationException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class ObjectsValidator<T> {
    //ici on demande a validation de builder(fournir) un validator factory
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    //récupérer validateur de factory validator
    private final Validator validator = factory.getValidator();

    public void validate(T objectToValidate) {
        //cette methode represente les violations de contraintes détectées pendant la validation
        Set<ConstraintViolation<T>>  violations = validator.validate(objectToValidate);
        //vérifie si l'ensemble de violations n'est pas vide
        if (!violations.isEmpty()){
            Set<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjectValidationException(errorMessages, objectToValidate.getClass().getName());
        }
    }

}
