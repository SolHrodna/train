package com.springapp.mvc.service;

import com.springapp.mvc.domain.Order;

import java.util.List;

public interface OrderService {


    void addOrder(Order order);

    public List<Order> showAll();

    Order find(Integer id);

    void delete(Order order);
}
