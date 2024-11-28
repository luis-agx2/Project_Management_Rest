package com.lag.projectmanagement.validation;

import com.lag.projectmanagement.repository.AccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsNickNameValidator implements ConstraintValidator<ExistsNickName, String> {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void initialize(ExistsNickName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !accountRepository.existsByNickName(value);
    }
}
