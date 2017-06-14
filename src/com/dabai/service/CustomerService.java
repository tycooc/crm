package com.dabai.service;


import com.dabai.domain.Customer;
import com.dabai.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;


public interface CustomerService {

    void save(Customer customer);

    PageBean<Customer> findByCode(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

    Customer findById(Long cust_id);

    void delete(Customer customer);

    void update(Customer customer);

    List<Customer> findAll();

    List<Object[]> findBySource();
}
