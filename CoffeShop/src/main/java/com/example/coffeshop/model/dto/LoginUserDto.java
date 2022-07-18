package com.example.coffeshop.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginUserDto {

    private Long id;
    private String username;
    private String password;

    @NotBlank
    @Size(min = 5, max = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank
    @Size(min = 3)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
