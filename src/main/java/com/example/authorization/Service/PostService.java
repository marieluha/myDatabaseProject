package com.example.authorization.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.authorization.Model.Post;
import com.example.authorization.Model.User;
import com.example.authorization.Repositories.PostRepository;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // creates and saves to database
    public Post createPost(String content, User user) {
        Post post = new Post();
        post.setContent(content);
        post.setUser(user);
        return postRepository.save(post);
    }
}