package com.crm_module.repositories;

import java.util.Optional;

public interface BaseRepo<T> {
    Optional<T> findById(long id);

    T save(T entity);

    T update(T entity);

    void delete(T entity);

    long generateId();

    boolean isExistsById(long id);
}
