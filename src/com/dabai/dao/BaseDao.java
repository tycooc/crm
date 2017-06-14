package com.dabai.dao;


import com.dabai.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 以后所有的DAO接口都需要继承BaseDao接口
 * 自定义泛型接口
 */
public interface BaseDao<T> {

    void save(T t);

    void delete(T t);

    void update(T t);

    T findById(Long id);

    List<T> findAll();

    PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
