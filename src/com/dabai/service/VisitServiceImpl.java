package com.dabai.service;

import com.dabai.dao.VisitDao;
import com.dabai.domain.PageBean;
import com.dabai.domain.Visit;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 客户拜访的业务层
 */
@Service(value = "visitService")
@Transactional
public class VisitServiceImpl implements VisitService {

    @Resource(name = "visitDao")
    private VisitDao visitDao;

    /**
     * 分页查询客户拜访记录
     * @param pageCode
     * @param pageSize
     * @param criteria
     * @return
     */
    public PageBean<Visit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        return visitDao.findByPage(pageCode,pageSize,criteria);
    }

    /**
     * 保存拜访记录
     * @param visit
     */
    public void save(Visit visit) {
        visitDao.save(visit);
    }
}
