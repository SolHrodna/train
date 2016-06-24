package com.springapp.mvc.service.Impl;


import com.springapp.mvc.DAO.OrderDAO;
import com.springapp.mvc.domain.Order;
import com.springapp.mvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;


    @Override
    public void addOrder(Order order) {
        orderDAO.save(order);
    }

    @Override
    public List<Order> showAll() {
        return orderDAO.showAll();
    }

    @Override
    public Order find(Integer id) {

        return orderDAO.findById(id);

    }

    @Override
    public void delete(Order order) {

        orderDAO.delete(order);
    }
}
