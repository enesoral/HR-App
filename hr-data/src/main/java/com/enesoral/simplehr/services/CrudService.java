package com.enesoral.simplehr.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T, ID> {
    T save(T object);
    T findById(ID id);
    Page<T> findAll(Pageable pageable);
    void delete(T object);
    void deleteById(ID id);
}
