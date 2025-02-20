package com.example.authorization;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.authorization.Model.Post;
import com.example.authorization.Model.User;
import com.example.authorization.Repositories.PostRepository;
import com.example.authorization.Repositories.UserRepository;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/posts")
    public String showPosts(Model model) {
        List<Post> posts = postRepository.findAll(); // retrieves all posts from the database
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm d.MMMM yyyy");
    
        // Format the createdAt field for each post
        for (Post post : posts) {
            String formattedDate = post.getCreatedAt().format(formatter);
            post.setFormattedDate(formattedDate);
        }
        
        
        
        model.addAttribute("posts", posts);
        return "posts"; 
    }

    @PostMapping("/posts")
    public String createPost(@RequestParam String content, Principal principal) { // @RequestParam to extract the content of the post from the submitted form
        // currently logged-in user
        User user = userRepository.findByUsername(principal.getName());
        Post post = new Post(content, user);
        postRepository.save(post);
        return "redirect:/posts";
    }

}

