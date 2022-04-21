package com.example.rmg.domain.common.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericPersistence<T, ID extends Serializable> {

    void save(T entity);

    void update(T entity);

    List<T> findAll();

    Optional<T> findById(ID id);

    void deleteById(ID id);
}
