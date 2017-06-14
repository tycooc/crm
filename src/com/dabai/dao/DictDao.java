package com.dabai.dao;

import com.dabai.domain.Dict;

import java.util.List;


public interface DictDao {
    List<Dict> findByCode(String dict_type_code);
}
