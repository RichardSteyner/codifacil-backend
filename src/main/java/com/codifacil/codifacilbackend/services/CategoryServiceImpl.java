package com.codifacil.codifacilbackend.services;

import com.codifacil.codifacilbackend.common.CommonServiceImpl;
import com.codifacil.codifacilbackend.models.entity.Category;
import com.codifacil.codifacilbackend.models.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl extends CommonServiceImpl<Category, Integer, CategoryRepository>
        implements CategoryService {

    @Transactional(readOnly = true)
    @Override
    public Iterable<Category> findVigentes() {
        return this.repository.findVigentes();
    }

    @Override
    public Iterable<Category> findAll() {
        return this.repository.findVigentes();
    }

    @Override
    public Category insert(Category entity) {
        entity.setVigencia(true);
        return super.insert(entity);
    }
}
