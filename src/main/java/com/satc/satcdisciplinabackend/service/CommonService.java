package com.satc.satcdisciplinabackend.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CommonService<T> {
    List<T> findAll(String filter);

    Page<T> findPaginated(int page, int size, String filter);

    T findById(Integer id);

    T save(T entity);

    T update(Integer id, T entity);

    void deleteOne(Integer id);
}
