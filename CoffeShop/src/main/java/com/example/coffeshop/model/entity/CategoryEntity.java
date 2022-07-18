package com.example.coffeshop.model.entity;

import com.example.coffeshop.model.enums.CategoryEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "categories")
public class CategoryEntity extends BaseEntity{

    private CategoryEnum name;
    private Integer neededTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public CategoryEnum getName() {
        return name;
    }

    public void setName(CategoryEnum name) {
        this.name = name;
    }

    @Column(nullable = false,name = "needed_time")
    public Integer getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
    }
}
