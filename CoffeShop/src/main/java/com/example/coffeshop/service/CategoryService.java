package com.example.coffeshop.service;

import com.example.coffeshop.model.entity.CategoryEntity;
import com.example.coffeshop.model.enums.CategoryEnum;
import com.example.coffeshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void initCategory() {
        if (categoryRepository.count() > 0){
            return;
        }

        Arrays.stream(CategoryEnum.values())
                .forEach(categoryEnum -> {
                    CategoryEntity category = new CategoryEntity();
                    category.setName(categoryEnum);
                    switch (categoryEnum){
                        case Drink -> category.setNeededTime(1);
                        case Coffee -> category.setNeededTime(2);
                        case Other -> category.setNeededTime(5);
                        case Cake -> category.setNeededTime(10);
                    }
                    categoryRepository.save(category);
                });
    }

    public CategoryEntity findByName(String category) {
        return categoryRepository
                .findByName(CategoryEnum.valueOf(category));
    }
}
