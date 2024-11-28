package com.lag.projectmanagement.validation;

import com.lag.projectmanagement.repository.AccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsEmailValidator implements ConstraintValidator<ExistsEmail, String> {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void initialize(ExistsEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !accountRepository.existsByEmail(value);
    }
}
