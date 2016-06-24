package com.springapp.mvc.DAO.Impl;


import com.springapp.mvc.DAO.UserDAO;
import com.springapp.mvc.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserDAOImpl extends MainDAOImpl<User> implements UserDAO {


    @Autowired
    private SessionFactory sessionFactory;

    public UserDAOImpl() {
        super(User.class);
    }


    public UserDAOImpl(SessionFactory sessionFactory) {
    }

    
}
