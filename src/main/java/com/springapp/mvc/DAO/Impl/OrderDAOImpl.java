package com.springapp.mvc.DAO.Impl;

import com.springapp.mvc.DAO.OrderDAO;
import com.springapp.mvc.domain.Order;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
public class OrderDAOImpl extends MainDAOImpl<Order> implements OrderDAO {

    public OrderDAOImpl() {
        super(Order.class);
    }

}
