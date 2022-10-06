package com.vti.validation;

import com.vti.repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartmentNotExistsByNameValidator implements ConstraintValidator<DepartmentNotExistsByName, String> {
    @Autowired
    private IDepartmentRepository repository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !repository.existsByName(username);
    }
}
