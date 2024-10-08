package com.example.library_management.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator<T> {  // This class is not Used to implement this type of validation I have to do some research ,
    private final ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
    private final Validator validator= factory.getValidator();

    public Set<String> validate(T objectValidate){
        Set<ConstraintViolation<T>> violations = validator.validate(objectValidate);
        if(! violations.isEmpty()){
            return violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}
