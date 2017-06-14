package com.dabai.dao;

import com.dabai.domain.Customer;
import com.dabai.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * 持久层
 */
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

    /**
     * 统计客户的来源
     * @return
     */
    public List<Object[]> findBySource() {
        //定义HQL
        //分组查询 select *　from cst_customer c,base_dict d where d.dict_id=c.cust_source group by d.dict_id
        //查询内容 select d.dict_item_name,count(*)　from cst_customer c,base_dict d where d.dict_id=c.cust_source group by d.dict_id
        String hql="select c.source.dict_item_name,count(*) from Customer c inner join c.source group by c.source";
        //查询
        return (List<Object[]>) this.getHibernateTemplate().find(hql);
    }
}
