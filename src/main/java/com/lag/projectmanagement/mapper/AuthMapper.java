package com.lag.projectmanagement.mapper;

import com.lag.projectmanagement.dto.request.RegisterRequest;
import com.lag.projectmanagement.entity.AccountEntity;
import com.lag.projectmanagement.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public AccountEntity toAccountEntity(RegisterRequest registerRequest) {
        return new AccountEntity(
                registerRequest.getNickName(),
                registerRequest.getEmail(),
                registerRequest.getPassword());
    }

    public PersonEntity toPersonEntity(RegisterRequest registerRequest) {
        return new PersonEntity(
                registerRequest.getFirstName(),
                registerRequest.getLastName());
    }
}
