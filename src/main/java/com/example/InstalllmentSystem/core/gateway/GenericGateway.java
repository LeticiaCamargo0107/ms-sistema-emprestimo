package com.example.InstalllmentSystem.core.gateway;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericGateway<T> {

    T save(T object);

    void deleteById(String id);

    boolean existById (String id);

    T findById(String id);

    Page<T> findAll(Pageable pageable);
}
