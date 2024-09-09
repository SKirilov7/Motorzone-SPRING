package com.example.motorzone.models.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

public class UpdateUserDTO {

    @Size(min = 2, max = 20)
    @JsonProperty("first_name")
    private String firstName;

    @Size(min = 2, max = 20)
    @JsonProperty("last_name")
    private String lastName;

    @Size(min = 5)
    private String password;

    @JsonProperty("confirm_password")
    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public UpdateUserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UpdateUserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UpdateUserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UpdateUserDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

}
