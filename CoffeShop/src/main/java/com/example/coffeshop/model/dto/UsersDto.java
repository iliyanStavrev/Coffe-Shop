package com.example.coffeshop.model.dto;

public class UsersDto {

    private String username;
    private Integer countOrders;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCountOrders() {
        return countOrders;
    }

    public void setCountOrders(Integer countOrders) {
        this.countOrders = countOrders;
    }
}
