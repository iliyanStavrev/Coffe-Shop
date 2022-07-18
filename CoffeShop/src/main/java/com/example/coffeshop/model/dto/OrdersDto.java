package com.example.coffeshop.model.dto;

import com.example.coffeshop.model.entity.CategoryEntity;
import com.example.coffeshop.model.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrdersDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private UserEntity employee;
    private CategoryEntity category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UserEntity getEmployee() {
        return employee;
    }

    public void setEmployee(UserEntity employee) {
        this.employee = employee;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
