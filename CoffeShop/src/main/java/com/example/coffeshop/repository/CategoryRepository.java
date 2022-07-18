package com.example.coffeshop.repository;

import com.example.coffeshop.model.entity.CategoryEntity;
import com.example.coffeshop.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByName(CategoryEnum name);
}
