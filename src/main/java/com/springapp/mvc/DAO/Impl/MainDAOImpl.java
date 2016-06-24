package com.springapp.mvc.DAO.Impl;

import com.springapp.mvc.DAO.MainDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
public abstract class MainDAOImpl<T> implements MainDAO<T> {


    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> type;

    MainDAOImpl() {

    }

    MainDAOImpl(Class<T> type) {
        this.type = type;
    }


    @Override
    @Transactional
    public List<T> showAll() {


        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(type);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List returns = criteria.list();

        return returns;
    }

    @Override
    @Transactional
    public void save(T item) {


        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(item);


    }

    @Override
    @Transactional
    public T findById(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        T item = (T) session.get(type, id);

        return item;

    }

    @Override
    @Transactional
    public void delete(T item) {

        Session session = sessionFactory.getCurrentSession();
        session.delete(item);

    }

    @Override
    @Transactional
    public void deletebyId(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        T item = this.findById(id);

        this.delete(item);

    }


}
