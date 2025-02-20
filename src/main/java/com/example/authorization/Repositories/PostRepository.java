package com.example.authorization.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.authorization.Model.Post;

public interface PostRepository extends JpaRepository<Post, Long> { // gets access to built-in methods.  crud operations
    List<Post> findAllByOrderByCreatedAtDesc();
}