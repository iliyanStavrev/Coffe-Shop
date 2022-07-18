package com.example.coffeshop.init;

import com.example.coffeshop.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DateBaseInit implements CommandLineRunner {

    private final CategoryService categoryService;

    public DateBaseInit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategory();
    }
}
