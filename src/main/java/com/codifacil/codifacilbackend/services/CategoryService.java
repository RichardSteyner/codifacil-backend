package com.codifacil.codifacilbackend.services;

import com.codifacil.codifacilbackend.common.CommonService;
import com.codifacil.codifacilbackend.models.entity.Category;

public interface CategoryService extends CommonService<Category, Integer> {

    public Iterable<Category> findVigentes();

}
