package com.crm_module.repositories;

import java.util.Optional;

public interface BaseCrudRepo<T> {
    Optional<T> findById(long id);

    T save(T entity);

    T update(T entity);

    boolean deleteById(long id);
}
