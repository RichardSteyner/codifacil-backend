package com.codifacil.codifacilbackend.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommonService<T, I> {

    public Iterable<T> findAll();

    public Page<T> findAll(Pageable pageable);

    public Optional<T> findById(I id);

    public T insert(T entity);

    public T update(T entity);

    public void deleteById(I id);

}
