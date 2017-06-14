package com.dabai.service;

import com.dabai.dao.DictDao;
import com.dabai.domain.Dict;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典的业务层
 */
@Transactional
public class DictServiceImpl implements DictService {

    private DictDao dictDao;

    public void setDictDao(DictDao dictDao) {
        this.dictDao = dictDao;
    }

    /**
     * 通过客户类别编码查询字段
     * @param dict_type_code
     * @return
     */
    public List<Dict> findByCode(String dict_type_code) {

        return dictDao.findByCode(dict_type_code);
    }
}
