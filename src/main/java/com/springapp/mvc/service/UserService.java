package com.springapp.mvc.service;


import com.springapp.mvc.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    void save(User user);

    List<User> showAll();

    void deleteById(Integer id);

    User findById(Integer id);
    
    User initializeUser(String name);
}
