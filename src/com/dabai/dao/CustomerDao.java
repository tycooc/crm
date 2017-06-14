package com.dabai.dao;


import com.dabai.domain.Customer;
import com.dabai.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerDao extends BaseDao<Customer> {

    List<Object[]> findBySource();
}
