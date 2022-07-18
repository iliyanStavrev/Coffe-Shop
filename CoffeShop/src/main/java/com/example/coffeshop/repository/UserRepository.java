package com.example.coffeshop.repository;

import com.example.coffeshop.model.dto.UsersDto;
import com.example.coffeshop.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM users u " +
            "ORDER BY u.orders.size DESC")
    List<UserEntity> findByOrdersDesc();
}
