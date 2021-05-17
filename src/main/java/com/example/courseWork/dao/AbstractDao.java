package com.example.courseWork.dao;

import java.util.Collection;

public interface AbstractDao<T> {

    T get(String name);

    Collection<T> findAll();
}