package com.example.authorization.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authorization.Dto.UserDto;
import com.example.authorization.Model.User;
import com.example.authorization.Repositories.UserRepository;

@Service
public class UserSeviceImpl implements UserService{

    @Autowired
    PasswordEncoder passwordEncoder;


    private UserRepository userRepository;

    public UserSeviceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getFullname());
        return userRepository.save(user);
    }
    
}
