package com.example.authorization.Service;

import com.example.authorization.Model.User;
import com.example.authorization.Dto.UserDto;



public interface UserService {

    User findByUsername(String username);
    User save (UserDto userDto);
    
}
