package com.dabai.service;

import com.dabai.dao.CustomerDao;
import com.dabai.domain.Customer;
import com.dabai.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客户的业务层
 */
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * 保存客户
     * @param customer
     */
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    /**
     * 分页查询
     * @param pageCode
     * @param pageSize
     * @param criteria
     * @return
     */
    public PageBean<Customer> findByCode(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        return customerDao.findByPage(pageCode,pageSize,criteria);
    }

    /**
     * 通过主键,查询客户
     * @param cust_id
     * @return
     */
    public Customer findById(Long cust_id) {
        return customerDao.findById(cust_id);
    }

    /**
     * 删除客户
     * @param customer
     */
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    /**
     * 修改客户
     * @param customer
     */
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    /**
     * 查询所有客户
     * @return
     */
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    /**
     * 统计客户来源
     * @return
     */
    public List<Object[]> findBySource() {

        return (List<Object[]>) customerDao.findBySource();
    }
}
