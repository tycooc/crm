package com.dabai.service;

import com.dabai.domain.Dict;

import java.util.List;


public interface DictService {
    List<Dict> findByCode(String dict_type_code);
}
