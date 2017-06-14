package com.dabai.service;

import com.dabai.dao.LinkmanDao;
import com.dabai.domain.Linkman;
import com.dabai.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class LinkmanServiceImpl implements LinkmanService {

    private LinkmanDao linkmanDao;

    public void setLinkmanDao(LinkmanDao linkmanDao) {
        this.linkmanDao = linkmanDao;
    }

    /**
     * 分页的方法
     * @param pageCode
     * @param pageSize
     * @param criteria
     * @return
     */
    public PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        return linkmanDao.findByPage(pageCode,pageSize,criteria);
    }

    /**
     * 添加
     * @param linkman
     */
    public void save(Linkman linkman) {
        linkmanDao.save(linkman);
    }
}
