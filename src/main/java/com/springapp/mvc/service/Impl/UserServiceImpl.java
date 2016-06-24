package com.springapp.mvc.service.Impl;

import com.springapp.mvc.DAO.UserDAO;
import com.springapp.mvc.domain.User;
import com.springapp.mvc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void save(User user){

		user.setRole("ROLE_USER");
		userDAO.save(user);
			

		}



	@Override
	public User initializeUser(String name) {
		
		User user = new User();
		
		for (User users : this.showAll()) {

			if (users.getLogin().equals(name)) {
				
				user = users;
				
			}

		}
		
		return user;
	}

	@Override
	public List<User> showAll() {

		return userDAO.showAll();

	}

	@Override
	public void deleteById(Integer id) {

		userDAO.deletebyId(id);

	}

	@Override
	public User findById(Integer id) {

		return userDAO.findById(id);
	}
}
