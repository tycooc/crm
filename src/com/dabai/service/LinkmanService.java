package com.dabai.service;

import com.dabai.domain.Linkman;
import com.dabai.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;


public interface LinkmanService  {
    PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

    void save(Linkman linkman);
}
