package com.example.springbootcrud.service;

import java.util.List;

public interface ServiceLayer<T> {

    T save(T t);

    T findById(Long id);

    List<T> findAll();

    T update(Long id, T t);

    String delete(Long id);

}
