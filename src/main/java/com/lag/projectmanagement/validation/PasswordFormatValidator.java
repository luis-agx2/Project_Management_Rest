package com.lag.projectmanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordFormatValidator implements ConstraintValidator<PasswordFormat, String> {
    @Override
    public void initialize(PasswordFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Pattern upperCaseLetterPatter = Pattern.compile(".*[A-Z].*");
        Pattern lowerCaseLetterPattern = Pattern.compile(".*[a-z].*");
        Pattern numberPattern = Pattern.compile(".*\\d.*");
        Pattern symbolPattern = Pattern.compile(".*\\W.*");

        if (!upperCaseLetterPatter.matcher(value).matches()) {
            setMessage(constraintValidatorContext, "Password must contain at least one uppercase letter");
            return false;
        } else if (!lowerCaseLetterPattern.matcher(value).matches()) {
            setMessage(constraintValidatorContext, "Password must contain at least one lowercase letter");
            return false;
        } else if (!numberPattern.matcher(value).matches()) {
            setMessage(constraintValidatorContext, "Password must contain at least one number");
            return false;
        } else if (!symbolPattern.matcher(value).matches()) {
            setMessage(constraintValidatorContext, "Password must contain at least one symbol");
            return false;
        }

        return true;
    }

    private void setMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
