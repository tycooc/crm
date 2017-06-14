package com.dabai.dao;

import com.dabai.domain.Dict;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * 字典的持久层
 */
public class DictDaoImpl extends HibernateDaoSupport implements DictDao {

    /**
     * 通过客户类别编码查询字段
     * @param dict_type_code
     * @return
     */
    public List<Dict> findByCode(String dict_type_code) {
        return (List<Dict>) this.getHibernateTemplate().find("from Dict where dict_type_code=?", dict_type_code);
    }
}
