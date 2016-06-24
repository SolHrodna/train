package com.springapp.mvc.DAO;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MainDAO<T> {

    
    public List<T> showAll();

    @Transactional
    public void save(T item);

    
    T findById(Integer id);

    @Transactional
    public void delete(T item);


    @Transactional
    void deletebyId(Integer id);
}
