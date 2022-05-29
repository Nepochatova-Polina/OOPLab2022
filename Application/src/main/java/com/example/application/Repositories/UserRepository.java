package com.example.application.Repositories;

import com.example.application.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Long>{
    User findByUsername(String username);
}
