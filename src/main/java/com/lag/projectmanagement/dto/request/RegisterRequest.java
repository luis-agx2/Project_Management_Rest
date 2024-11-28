package com.lag.projectmanagement.dto.request;

import com.lag.projectmanagement.validation.ExistsEmail;
import com.lag.projectmanagement.validation.ExistsNickName;
import com.lag.projectmanagement.validation.PasswordFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public class RegisterRequest {
    @NotBlank
    @Length(min = 3, max = 20)
    private String firstName;

    @NotBlank
    @Length(min = 3, max = 25)
    private String lastName;

    @NotBlank
    @Length(min = 5, max = 30)
    @ExistsNickName
    private String nickName;

    @NotBlank
    @Email
    @ExistsEmail
    private String email;

    @NotBlank
    @Size(min = 8)
    @PasswordFormat
    private String password;

    @NotNull
    private Long roleId;

    public RegisterRequest() {
    }

    public RegisterRequest(
            String firstName,
            String lastName,
            String nickName,
            String email,
            String password,
            Long roleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RegisterRequest[" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                ']';
    }
}
