package com.codifacil.codifacilbackend.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class CommonServiceImpl<T, I, R extends JpaRepository<T, I>> implements CommonService<T, I> {

    @Autowired
    protected R repository;

    @Override
    @Transactional(readOnly = true)
    public Iterable findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(I id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public T insert(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(I id) {
        repository.deleteById(id);
    }
}
