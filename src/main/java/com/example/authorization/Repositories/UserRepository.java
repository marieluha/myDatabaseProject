package com.example.authorization.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.authorization.Model.User;

// for managing User entities

@Repository
public interface UserRepository extends JpaRepository<User, Long>{ // gets access to built-in methods, save(), findAll(), findById(), CRUD

    User findByUsername (String username);
    
}