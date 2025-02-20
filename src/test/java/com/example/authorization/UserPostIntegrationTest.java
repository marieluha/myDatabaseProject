package com.example.authorization;

import com.example.authorization.Model.Post;
import com.example.authorization.Model.User;
import com.example.authorization.Repositories.UserRepository;
import com.example.authorization.Repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserPostIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void testUserWithPosts() {
        // Create a new user
        User user = new User("johndoe", "hashedpassword", "John Doe");
        userRepository.save(user);

        // Create a post and associate it with the user
        Post post = new Post("Hello, this is my first post!", user);
        user.addPost(post);

        // Save user (cascade saves the post too)
        userRepository.save(user);

        // Retrieve the user and check posts
        User savedUser = userRepository.findById(user.getId()).orElseThrow();
        assertEquals("johndoe", savedUser.getUsername());
        assertEquals(1, savedUser.getPosts().size());
        assertEquals("Hello, this is my first post!", savedUser.getPosts().get(0).getContent());

        // Retrieve the post directly and verify
        List<Post> posts = postRepository.findAll();
        assertEquals(1, posts.size());
        assertEquals("Hello, this is my first post!", posts.get(0).getContent());
        assertEquals(user.getId(), posts.get(0).getUser().getId());
    }
}

