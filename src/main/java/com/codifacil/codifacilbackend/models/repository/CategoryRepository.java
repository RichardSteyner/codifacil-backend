package com.codifacil.codifacilbackend.models.repository;

import com.codifacil.codifacilbackend.models.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select c from Category c where c.vigencia=1")
    public Iterable<Category> findVigentes();

    @Query("select c from Category c where c.vigencia=1")
    public Page<Category> findVigentes(Pageable pageable);

}